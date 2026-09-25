/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount
 *  mods.acountswiher.ru.vidtu.ias.crypt.Crypt
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.auth.microsoft;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.URI;
import java.net.http.HttpTimeoutException;
import java.nio.channels.UnresolvedAddressException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mods.acountswiher.ru.vidtu.ias.IAS;
import mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount;
import mods.acountswiher.ru.vidtu.ias.auth.handlers.CreateHandler;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.MSAuth;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields.*;
import mods.acountswiher.ru.vidtu.ias.config.IASConfig;
import mods.acountswiher.ru.vidtu.ias.crypt.Crypt;
import mods.acountswiher.ru.vidtu.ias.utils.Holder;
import mods.acountswiher.ru.vidtu.ias.utils.IUtils;
import mods.acountswiher.ru.vidtu.ias.utils.exceptions.FriendlyException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class MSAuthServer
implements Runnable,
Closeable {
    @NotNull
    private static final String MICROSOFT_AUTH_URL = "https://login.live.com/oauth20_authorize.srf?client_id=54fd49e4-2103-4044-9603-2b028c814ec3&response_type=code&scope=XboxLive.signin%20XboxLive.offline_access&redirect_uri=http://localhost:%%port%%/in_game_account_switcher_long_enough_uri_to_prevent_accidental_leaks_on_screensharing_even_if_you_have_like_extremely_big_screen_though_it_might_not_mork_but_we_will_try_it_anyway_to_prevent_funny_things_from_happening_or_something&prompt=select_account&state=%%state%%";
    @NotNull
    private static final String REDIRECT_URI = "http://localhost:%s/in_game_account_switcher_long_enough_uri_to_prevent_accidental_leaks_on_screensharing_even_if_you_have_like_extremely_big_screen_though_it_might_not_mork_but_we_will_try_it_anyway_to_prevent_funny_things_from_happening_or_something";
    @NotNull
    private static final String END_URI = "http://localhost:%s/end";
    @NotNull
    private static final String STATE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.-_";
    @NotNull
    private static final Pattern DATA_EXTRACT_PATTERN = Pattern.compile("^code=([^&]*)&state=([^&]*)$");
    @NotNull
    private static final Pattern CODE_OBFUSCATE_PATTERN = Pattern.compile("code=[^&]*", 2);
    @NotNull
    private final String doneMessage;
    @NotNull
    private final Crypt crypt;
    @NotNull
    private final CreateHandler handler;
    @NotNull
    private final HttpServer server;
    @NotNull
    private final String state;
    private int port;
    private boolean once;

    public MSAuthServer(@NotNull String doneMessage, @NotNull Crypt crypt, @NotNull CreateHandler handler) {
        try {
            this.doneMessage = doneMessage;
            this.crypt = crypt;
            this.handler = handler;
            this.server = HttpServer.create();
            SecureRandom random = SecureRandom.getInstanceStrong();
            int length = random.nextInt(96, 128);
            StringBuilder builder = new StringBuilder(length);
            for (int i = 0; i < length; ++i) {
                builder.appendCodePoint(STATE_CHARACTERS.codePointAt(random.nextInt(STATE_CHARACTERS.length())));
            }
            this.state = builder.toString();
        }
        catch (Throwable t) {
            throw new RuntimeException("Unable to create HTTP server for MS auth.", t);
        }
    }

    @Override
    public void run() {
        try {
            if (this.handler.cancelled()) {
                return;
            }
            this.handler.stage("ias.login.server", new Object[0]);
            this.server.createContext("/", ex -> {
                try {
                    byte[] data;
                    if (this.once) {
                        ex.close();
                        return;
                    }
                    if (!ex.getRemoteAddress().getAddress().isLoopbackAddress()) {
                        ex.close();
                        return;
                    }
                    this.once = true;
                    URI uri = ex.getRequestURI();
                    try (InputStream in = MSAuthServer.class.getResourceAsStream("/ias_auth.html");){
                        Objects.requireNonNull(in, "Auth page is null.");
                        String page = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                        page = page.replace("%%ias_icon%%", IASConfig.unexpectedPigs ? "\ud83d\udc37\ud83d\udc4d" : "\u2705").replace("%%ias_message%%", this.doneMessage);
                        data = page.getBytes(StandardCharsets.UTF_8);
                    }
                    Headers headers = ex.getResponseHeaders();
                    headers.add("Content-Type", "text/html; charset=UTF-8");
                    headers.add("Content-Length", Integer.toString(data.length));
                    headers.add("Server", IAS.USER_AGENT);
                    headers.add("Location", END_URI.formatted(this.port));
                    ex.sendResponseHeaders(302, data.length);
                    try (OutputStream out = ex.getResponseBody();){
                        out.write(data);
                    }
                    ex.close();
                    this.auth(uri);
                    IAS.executor().schedule(this::close, 10L, TimeUnit.SECONDS);
                }
                catch (Throwable t) {
                    try {
                        ex.close();
                    }
                    catch (Throwable th) {
                        t.addSuppressed(th);
                    }
                    try {
                        this.close();
                    }
                    catch (Throwable th) {
                        t.addSuppressed(th);
                    }
                    this.handler.error(new RuntimeException("Unexpected exception on '/': " + String.valueOf(ex), t));
                }
            });
            this.server.createContext("/end", ex -> {
                try {
                    byte[] data;
                    if (!ex.getRemoteAddress().getAddress().isLoopbackAddress()) {
                        ex.close();
                        return;
                    }
                    try (InputStream in = MSAuthServer.class.getResourceAsStream("/ias_auth.html");){
                        Objects.requireNonNull(in, "Auth page is null.");
                        String page = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                        page = page.replace("%%ias_icon%%", IASConfig.unexpectedPigs ? "\ud83d\udc37\ud83d\udc4d" : "\u2705").replace("%%ias_message%%", this.doneMessage);
                        data = page.getBytes(StandardCharsets.UTF_8);
                    }
                    Headers headers = ex.getResponseHeaders();
                    headers.add("Content-Type", "text/html; charset=UTF-8");
                    headers.add("Content-Length", Integer.toString(data.length));
                    headers.add("Server", IAS.USER_AGENT);
                    ex.sendResponseHeaders(200, data.length);
                    try (OutputStream out = ex.getResponseBody();){
                        out.write(data);
                    }
                    ex.close();
                    IAS.executor().schedule(this::close, 10L, TimeUnit.SECONDS);
                }
                catch (Throwable t) {
                    try {
                        ex.close();
                    }
                    catch (Throwable th) {
                        t.addSuppressed(th);
                    }
                    try {
                        this.close();
                    }
                    catch (Throwable th) {
                        t.addSuppressed(th);
                    }
                    this.handler.error(new RuntimeException("Unexpected exception on '/end': " + String.valueOf(ex), t));
                }
            });
            if (this.handler.cancelled()) {
                return;
            }
            this.bindToSupportedPort();
            this.server.start();
        }
        catch (Throwable t) {
            try {
                this.close();
            }
            catch (Throwable th) {
                t.addSuppressed(th);
            }
            throw new RuntimeException("Unable to start the server.", t);
        }
    }

    private void bindToSupportedPort() {
        LinkedList<RuntimeException> thrown = new LinkedList<RuntimeException>();
        for (int port : IUtils.tryBindPorts()) {
            try {
                this.server.bind(new InetSocketAddress(port), 0);
                this.port = port;
                return;
            }
            catch (Throwable t) {
                thrown.add(new RuntimeException("Unable to bind: " + port, t));
            }
        }
        RuntimeException holder = new RuntimeException("Unable to bind to any port.");
        thrown.forEach(holder::addSuppressed);
        throw holder;
    }

    @Contract(pure=true)
    @NotNull
    public String authUrl() {
        return MICROSOFT_AUTH_URL.replace("%%port%%", Integer.toString(this.port)).replace("%%state%%", this.state);
    }

    private void auth(@NotNull URI uri) {
        try {
            if (this.handler.cancelled()) {
                return;
            }
            this.handler.stage("ias.login.processing", new Object[0]);
            String query = uri.getQuery();
            Holder<String> access = new Holder<>();
            Holder<String> refresh = new Holder<>();
            Holder<byte[]> data = new Holder<>();
            CompletableFuture.supplyAsync(() -> {
                if (this.handler.cancelled()) {
                    return null;
                }
                if (query == null) {
                    throw new FriendlyException("Null query.", "ias.error.query");
                }
                if (query.toLowerCase(Locale.ROOT).contains("access_denied")) {
                    throw new FriendlyException("Invalid query (access denied): " + CODE_OBFUSCATE_PATTERN.matcher(query).replaceAll("code=[CODE]"), "ias.error.cancel");
                }
                Matcher matcher = DATA_EXTRACT_PATTERN.matcher(query);
                if (!matcher.matches()) {
                    throw new IllegalStateException("Invalid query: " + CODE_OBFUSCATE_PATTERN.matcher(query).replaceAll("code=[CODE]"));
                }
                String state = matcher.group(2);
                if (!this.state.equals(state)) {
                    throw new IllegalStateException("Expected state " + state + ", got " + this.state);
                }
                return matcher.group(1);
            }, IAS.executor()).thenComposeAsync((String code) -> {
                if (code == null || this.handler.cancelled()) {
                    return CompletableFuture.completedFuture((MSTokens)null);
                }
                this.handler.stage("ias.login.msacToMsaMsr", new Object[0]);
                return MSAuth.msacToMsaMsr(code, REDIRECT_URI.formatted(this.port));
            }, IAS.executor()).thenComposeAsync((MSTokens ms) -> {
                if (ms == null || this.handler.cancelled()) {
                    return CompletableFuture.completedFuture((XHashedToken)null);
                }
                refresh.set(ms.refresh());
                this.handler.stage("ias.login.msaToXbl", new Object[0]);
                return MSAuth.msaToXbl(ms.access());
            }, IAS.executor()).thenComposeAsync((XHashedToken xbl) -> {
                if (xbl == null || this.handler.cancelled()) {
                    return CompletableFuture.completedFuture((XHashedToken)null);
                }
                this.handler.stage("ias.login.xblToXsts", new Object[0]);
                return MSAuth.xblToXsts(xbl.token(), xbl.hash());
            }, IAS.executor()).thenComposeAsync((XHashedToken xsts) -> {
                if (xsts == null || this.handler.cancelled()) {
                    return CompletableFuture.completedFuture((String)null);
                }
                this.handler.stage("ias.login.xstsToMca", new Object[0]);
                return MSAuth.xstsToMca(xsts.token(), xsts.hash());
            }, IAS.executor()).thenComposeAsync((String token) -> {
                if (token == null || this.handler.cancelled()) {
                    return CompletableFuture.completedFuture((MCProfile)null);
                }
                access.set(token);
                this.handler.stage("ias.login.mcaToMcp", new Object[0]);
                return MSAuth.mcaToMcp(token);
            }, IAS.executor()).exceptionallyAsync((Throwable t) -> {
                if (IUtils.anyInCausalChain(t, err -> err instanceof UnresolvedAddressException || err instanceof NoRouteToHostException || err instanceof HttpTimeoutException || err instanceof ConnectException)) {
                    throw new FriendlyException("Unable to connect to MS servers.", t, "ias.error.connect");
                }
                throw new RuntimeException("Unable to perform MS auth.", t);
            }, IAS.executor()).thenApplyAsync((MCProfile profile) -> {
                byte[] unencrypted;
                DataOutputStream out;
                ByteArrayOutputStream byteOut;
                if (profile == null || this.handler.cancelled()) {
                    return null;
                }
                this.handler.stage("ias.login.encrypting", new Object[0]);
                try {
                    byteOut = new ByteArrayOutputStream();
                    try {
                        out = new DataOutputStream(byteOut);
                        try {
                            out.writeUTF((String)access.get());
                            out.writeUTF((String)refresh.get());
                            unencrypted = byteOut.toByteArray();
                        }
                        finally {
                            out.close();
                        }
                    }
                    finally {
                        byteOut.close();
                    }
                }
                catch (Throwable t) {
                    throw new RuntimeException("Unable to write the tokens.", t);
                }
                try {
                    byteOut = new ByteArrayOutputStream();
                    try {
                        out = new DataOutputStream(byteOut);
                        try {
                            byte[] encrypted = this.crypt.encrypt(unencrypted);
                            out.writeUTF(this.crypt.type());
                            out.write(encrypted);
                            data.set(byteOut.toByteArray());
                        }
                        finally {
                            out.close();
                        }
                    }
                    finally {
                        byteOut.close();
                    }
                }
                catch (Throwable t) {
                    throw new RuntimeException("Unable to encrypt the tokens.", t);
                }
                return profile;
            }, IAS.executor()).thenAcceptAsync((MCProfile profile) -> {
                if (profile == null || this.handler.cancelled()) {
                    return;
                }
                UUID uuid = profile.uuid();
                String name = profile.name();
                this.handler.stage("ias.login.finalizing", new Object[0]);
                MicrosoftAccount account = new MicrosoftAccount(this.crypt.insecure(), uuid, name, (byte[])data.get());
                this.handler.success(account);
            }, IAS.executor()).exceptionallyAsync((Throwable t) -> {
                this.handler.error(new RuntimeException("Unable to create an MS account.", t));
                return null;
            }, IAS.executor());
        }
        catch (Throwable t2) {
            this.handler.error(new RuntimeException("Unable to finalize MS auth.", t2));
        }
    }

    @Override
    public void close() {
        this.server.stop(0);
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return "MSAuthServer{crypt=" + String.valueOf(this.crypt) + ", port=" + this.port + "}";
    }
}

