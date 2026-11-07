/*
 * Decompiled with CFR 0.152.
 */
package recovery.privacy;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public final class NetworkPolicy {
    private static volatile boolean offline = true;

    public static boolean isOffline() {
        return offline;
    }

    public static void setOffline(boolean value) {
        offline = value;
    }

    private static boolean isAllowedHost(String host) {
        if (host == null) {
            return false;
        }
        String h = host.toLowerCase(Locale.ROOT);
        if (h.equals("localhost") || h.equals("127.0.0.1") || h.equals("::1") || h.equals("[::1]") || h.endsWith(".localhost")) {
            return true;
        }
        return h.equals("lrclib.net") || h.endsWith(".lrclib.net");
    }

    private static boolean isAllowed(URI uri) {
        if (!offline) {
            return true;
        }
        if (uri == null) {
            return false;
        }
        return isAllowedHost(uri.getHost());
    }

    private static void checkAllowed(URI uri) throws IOException {
        if (!isAllowed(uri)) {
            throw new IOException("Kimiko offline mode: blocked " + uri);
        }
    }

    private NetworkPolicy() {
    }

    public static void socketConnect(Socket socket, InetSocketAddress address, int timeout) throws IOException {
        if (offline && !isAllowedHost(address.getHostString())) {
            throw new IOException("Kimiko offline mode: blocked " + address);
        }
        socket.connect(address, timeout);
    }

    public static boolean allowedLink(String link) {
        if (!offline) {
            return true;
        }
        try {
            return isAllowed(URI.create(link));
        }
        catch (Throwable ignored) {
            return false;
        }
    }

    public static String profileSetting(String setting) {
        return null;
    }

    public static HttpClient.Builder redirects(HttpClient.Builder builder, HttpClient.Redirect redirect) {
        // в оффлайн-режиме авто-редиректы запрещены: NetworkPolicy.send идёт по ним вручную с перепроверкой
        return builder.followRedirects(offline ? HttpClient.Redirect.NEVER : redirect);
    }

    private static final int MAX_REDIRECTS = 5;

    public static <T> HttpResponse<T> send(HttpClient client, HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        HttpRequest current = request;
        for (int i = 0; i <= MAX_REDIRECTS; ++i) {
            checkAllowed(current.uri());
            HttpResponse<T> response = client.send(current, responseBodyHandler);
            HttpRequest next = redirectTarget(current, response);
            if (next == null) {
                return response;
            }
            current = next;
        }
        throw new IOException("Kimiko offline mode: too many redirects for " + request.uri());
    }

    /** Кросс-хостный 30х больше не уводит запрос мимо whitelist'а: каждый Location перепроверяется. */
    private static HttpRequest redirectTarget(HttpRequest previous, HttpResponse<?> response) throws IOException {
        int code = response.statusCode();
        if (code != 301 && code != 302 && code != 303 && code != 307 && code != 308) {
            return null;
        }
        String location = response.headers().firstValue("Location").orElse(null);
        if (location == null || location.isBlank()) {
            return null;
        }
        URI target;
        try {
            target = previous.uri().resolve(location.trim());
        }
        catch (Exception e) {
            throw new IOException("Kimiko offline mode: bad redirect " + location);
        }
        if (!isAllowed(target)) {
            throw new IOException("Kimiko offline mode: redirect blocked " + target);
        }
        boolean keepMethodAndBody = code == 307 || code == 308
                || "GET".equals(previous.method()) || "HEAD".equals(previous.method());
        HttpRequest.Builder builder = HttpRequest.newBuilder(target);
        if (keepMethodAndBody) {
            builder.method(previous.method(), previous.bodyPublisher().orElse(HttpRequest.BodyPublishers.noBody()));
        } else {
            builder.GET();
        }
        if (sameHost(previous.uri(), target)) {
            previous.headers().map().forEach((name, values) -> {
                if (!name.equalsIgnoreCase("Host") && !name.equalsIgnoreCase("Content-Length")) {
                    for (String value : values) {
                        builder.header(name, value);
                    }
                }
            });
        } else if (keepMethodAndBody) {
            // наружу не утекают ни Authorization, ни Cookie — переносим только Content-Type
            String contentType = previous.headers().firstValue("Content-Type").orElse(null);
            if (contentType != null) {
                builder.header("Content-Type", contentType);
            }
        }
        if (previous.timeout().isPresent()) {
            builder.timeout(previous.timeout().get());
        }
        return builder.build();
    }

    private static boolean sameHost(URI a, URI b) {
        String ha = a.getHost() == null ? "" : a.getHost().toLowerCase(Locale.ROOT);
        String hb = b.getHost() == null ? "" : b.getHost().toLowerCase(Locale.ROOT);
        return ha.equals(hb);
    }

    public static <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpClient client, HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
        try {
            checkAllowed(request.uri());
        }
        catch (IOException e) {
            return CompletableFuture.failedFuture(e);
        }
        return sendAsync(client, request, responseBodyHandler, 0);
    }

    private static <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpClient client, HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler, int depth) {
        CompletableFuture<HttpResponse<T>> result = new CompletableFuture<>();
        client.sendAsync(request, responseBodyHandler).whenComplete((response, error) -> {
            if (error != null) {
                result.completeExceptionally(error);
                return;
            }
            HttpRequest next;
            try {
                next = redirectTarget(request, response);
            }
            catch (IOException e) {
                result.completeExceptionally(e);
                return;
            }
            if (next == null) {
                result.complete(response);
                return;
            }
            if (depth >= MAX_REDIRECTS) {
                result.completeExceptionally(new IOException("Kimiko offline mode: too many redirects for " + request.uri()));
                return;
            }
            try {
                checkAllowed(next.uri());
            }
            catch (IOException e) {
                result.completeExceptionally(e);
                return;
            }
            sendAsync(client, next, responseBodyHandler, depth + 1).whenComplete((nextResponse, nextError) -> {
                if (nextError != null) {
                    result.completeExceptionally(nextError);
                } else {
                    result.complete(nextResponse);
                }
            });
        });
        return result;
    }

    public static URLConnection openConnection(URL url) throws IOException {
        try {
            checkAllowed(url.toURI());
        }
        catch (IOException e) {
            throw e;
        }
        catch (Exception e) {
            throw new IOException("Kimiko offline mode: blocked " + url);
        }
        return url.openConnection();
    }

    public static void redirects(URLConnection connection, boolean follow) {
        if (connection instanceof HttpURLConnection) {
            // в оффлайн-режиме авто-редиректы запрещены: кросс-хостный 30х уводил бы запрос мимо whitelist'а
            ((HttpURLConnection)connection).setInstanceFollowRedirects(follow && !offline);
        }
    }

    public static void httpConnect(URLConnection connection) throws IOException {
        connection.connect();
    }

    public static int responseCode(URLConnection connection) throws IOException {
        if (connection instanceof HttpURLConnection) {
            return ((HttpURLConnection)connection).getResponseCode();
        }
        return -1;
    }

    public static InputStream input(URLConnection connection) throws IOException {
        return connection.getInputStream();
    }

    public static CompletableFuture<WebSocket> webSocket(WebSocket.Builder builder, URI uri, WebSocket.Listener listener) {
        try {
            checkAllowed(uri);
        }
        catch (IOException e) {
            return CompletableFuture.failedFuture(e);
        }
        return builder.buildAsync(uri, listener);
    }
}