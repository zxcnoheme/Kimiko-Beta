/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package mods.acountswiher.ru.vidtu.ias.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.HexFormat;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recovery.privacy.NetworkPolicy;

public final class IUtils {
    private static final int @NotNull [] TRY_BIND_PORTS = new int[]{59125, 59126, 59127, 59128, 59129, 59130, 59131, 59132, 59133, 59134, 59135, 1234, 1235, 1236, 1237, 80, 8080, 19364, 19365, 19366, 27930, 27931, 27932, 27933, 27934, 42069};

    @Contract(value="-> fail", pure=true)
    private IUtils() {
        throw new AssertionError((Object)"No instances.");
    }

    @Contract(pure=true)
    @Nullable
    public static String warnKey(@NotNull String name) {
        if (name.isBlank()) {
            return "ias.nick.blank";
        }
        int length = name.length();
        if (length < 3) {
            return "ias.nick.short";
        }
        if (length > 16) {
            return "ias.nick.long";
        }
        for (int i = 0; i < length; ++i) {
            int c = name.codePointAt(i);
            if (c == 95 || c >= 48 && c <= 57 || c >= 97 && c <= 122 || c >= 65 && c <= 90) continue;
            return "ias.nick.chars";
        }
        return null;
    }

    @Contract(pure=true)
    public static boolean anyInCausalChain(@Nullable Throwable root, @NotNull Predicate<Throwable> tester) {
        Set dejaVu = Collections.newSetFromMap(new IdentityHashMap(8));
        for (int i = 0; i < 256 && root != null && dejaVu.add(root); root = root.getCause(), ++i) {
            if (!tester.test(root)) continue;
            return true;
        }
        return false;
    }

    public static boolean canUseSunServer() {
        return SunServerAvailability.AVAILABLE;
    }

    @Contract(pure=true)
    public static int @NotNull [] tryBindPorts() {
        return (int[])TRY_BIND_PORTS.clone();
    }

    private static final class SunServerAvailability {
        private static final boolean AVAILABLE;

        private static void bindToSupportedPort(@NotNull ServerSocket socket) {
            LinkedList<RuntimeException> thrown = new LinkedList<RuntimeException>();
            for (int port : TRY_BIND_PORTS) {
                try {
                    socket.bind(new InetSocketAddress(port), 0);
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

        @Contract(value="-> fail", pure=true)
        private SunServerAvailability() {
            throw new AssertionError((Object)"No instances.");
        }

        static {
            boolean available;
            Logger logger = LoggerFactory.getLogger((String)"IAS/IUtils/SunServerAvailability");
            logger.info("IAS: Testing Sun HTTP server availability...");
            try {
                Class.forName("com.sun.net.httpserver.HttpServer");
                logger.info("IAS: Sun HTTP server class found, testing firewall by binding TCP server.");
                try (ServerSocket server = new ServerSocket();
                     Socket client = new Socket();){
                    server.setSoTimeout(2000);
                    SunServerAvailability.bindToSupportedPort(server);
                    logger.info("IAS: TCP server bound, connecting...");
                    int port = server.getLocalPort();
                    client.setSoTimeout(2000);
                    client.setTcpNoDelay(true);
                    NetworkPolicy.socketConnect(client, new InetSocketAddress(port), 2000);
                    logger.info("IAS: Connected to TCP server, trying to exchange data bidirectionally.");
                    try (Socket accepted = server.accept();
                         InputStream clientIn = client.getInputStream();
                         OutputStream clientOut = client.getOutputStream();
                         InputStream serverIn = accepted.getInputStream();
                         OutputStream serverOut = accepted.getOutputStream();){
                        SecureRandom random = new SecureRandom();
                        byte[] data = new byte[256];
                        ((Random)random).nextBytes(data);
                        serverOut.write(data);
                        serverOut.flush();
                        byte[] read = clientIn.readNBytes(256);
                        if (!Arrays.equals(data, read)) {
                            throw new IllegalStateException("S2C data doesn't match, sent " + HexFormat.of().formatHex(data) + ", got " + HexFormat.of().formatHex(read));
                        }
                        ((Random)random).nextBytes(data);
                        clientOut.write(data);
                        clientOut.flush();
                        read = serverIn.readNBytes(256);
                        if (!Arrays.equals(data, read)) {
                            throw new IllegalStateException("C2S data doesn't match, sent " + HexFormat.of().formatHex(data) + ", got " + HexFormat.of().formatHex(read));
                        }
                    }
                    logger.info("IAS: Exchanged TCP data, setting Sun server as available...");
                    available = true;
                }
            }
            catch (Throwable t) {
                logger.warn("IAS: Sun server is not available or is not accessible.", t);
                available = false;
            }
            AVAILABLE = available;
        }
    }
}

