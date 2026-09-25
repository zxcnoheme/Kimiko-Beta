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
import java.util.concurrent.CompletableFuture;

public final class NetworkPolicy {
    private NetworkPolicy() {
    }

    public static void socketConnect(Socket socket, InetSocketAddress address, int timeout) throws IOException {
        socket.connect(address, timeout);
    }

    public static boolean allowedLink(String link) {
        return true;
    }

    public static String profileSetting(String setting) {
        return null;
    }

    public static HttpClient.Builder redirects(HttpClient.Builder builder, HttpClient.Redirect redirect) {
        return builder.followRedirects(redirect);
    }

    public static <T> HttpResponse<T> send(HttpClient client, HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        return client.send(request, responseBodyHandler);
    }

    public static <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpClient client, HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
        return client.sendAsync(request, responseBodyHandler);
    }

    public static URLConnection openConnection(URL url) throws IOException {
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
        return builder.buildAsync(uri, listener);
    }
}