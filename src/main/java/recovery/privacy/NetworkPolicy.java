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
        return builder.followRedirects(redirect);
    }

    public static <T> HttpResponse<T> send(HttpClient client, HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        checkAllowed(request.uri());
        return client.send(request, responseBodyHandler);
    }

    public static <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpClient client, HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
        try {
            checkAllowed(request.uri());
        }
        catch (IOException e) {
            return CompletableFuture.failedFuture(e);
        }
        return client.sendAsync(request, responseBodyHandler);
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
            ((HttpURLConnection)connection).setInstanceFollowRedirects(follow);
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