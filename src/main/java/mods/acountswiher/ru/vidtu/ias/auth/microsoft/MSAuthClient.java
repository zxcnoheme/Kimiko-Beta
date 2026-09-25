/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount
 *  mods.acountswiher.ru.vidtu.ias.crypt.Crypt
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.auth.microsoft;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.http.HttpTimeoutException;
import java.nio.channels.UnresolvedAddressException;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import mods.acountswiher.ru.vidtu.ias.IAS;
import mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount;
import mods.acountswiher.ru.vidtu.ias.auth.handlers.CreateHandler;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.MSAuth;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields.*;
import mods.acountswiher.ru.vidtu.ias.crypt.Crypt;
import mods.acountswiher.ru.vidtu.ias.utils.Holder;
import mods.acountswiher.ru.vidtu.ias.utils.IUtils;
import mods.acountswiher.ru.vidtu.ias.utils.exceptions.DevicePendingException;
import mods.acountswiher.ru.vidtu.ias.utils.exceptions.FriendlyException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MSAuthClient
implements Closeable {
    @NotNull
    private final Crypt crypt;
    @NotNull
    private final CreateHandler handler;
    private DeviceAuth auth;
    @Nullable
    private Instant expire;
    @Nullable
    private Instant poll;
    @Nullable
    private ScheduledFuture<?> task;

    @Contract(pure=true)
    public MSAuthClient(@NotNull Crypt crypt, @NotNull CreateHandler handler) {
        this.crypt = crypt;
        this.handler = handler;
    }

    @NotNull
    public CompletableFuture<DeviceAuth> start() {
        if (this.handler.cancelled()) {
            return CompletableFuture.completedFuture(null);
        }
        return MSAuth.requestDac().thenApplyAsync(auth -> {
            if (this.handler.cancelled()) {
                return null;
            }
            Duration interval = auth.interval();
            this.auth = auth;
            this.expire = Instant.now().plus(auth.expire());
            this.poll = Instant.now().plus(interval);
            this.close();
            this.task = IAS.executor().scheduleWithFixedDelay(this::tick, interval.toMillis(), interval.toMillis(), TimeUnit.MILLISECONDS);
            return auth;
        }, (Executor)IAS.executor());
    }

    private void tick() {
        try {
            MSTokens ms;
            if (this.handler.cancelled()) {
                this.close();
                return;
            }
            try {
                ms = MSAuth.dacToMsaMsr(this.auth.device());
            }
            catch (Throwable t2) {
                if (IUtils.anyInCausalChain(t2, DevicePendingException.class::isInstance)) {
                    return;
                }
                this.close();
                this.handler.error(new RuntimeException("HTTP polling error.", t2));
                return;
            }
            this.close();
            if (this.handler.cancelled()) {
                return;
            }
            this.handler.stage("ias.login.processing", new Object[0]);
            Holder<String> access = new Holder<>();
            Holder<byte[]> data = new Holder<>();
            CompletableFuture.supplyAsync(() -> {
                if (this.handler.cancelled()) {
                    return null;
                }
                return ms.access();
            }, IAS.executor()).thenComposeAsync((String token) -> {
                if (token == null || this.handler.cancelled()) {
                    return CompletableFuture.completedFuture((XHashedToken)null);
                }
                this.handler.stage("ias.login.msaToXbl", new Object[0]);
                return MSAuth.msaToXbl(token);
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
                            out.writeUTF(ms.refresh());
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
        catch (Throwable t3) {
            this.handler.error(new RuntimeException("Unable to finalize MS auth.", t3));
        }
    }

    @Override
    public void close() {
        if (this.task != null) {
            this.task.cancel(false);
            this.task = null;
        }
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return "MSAuthClient{crypt=" + String.valueOf(this.crypt) + ", expire=" + String.valueOf(this.expire) + ", poll=" + String.valueOf(this.poll) + "}";
    }
}

