/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
 */
package recovery.privacy;

import java.util.UUID;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public final class PrivacyPreLaunch
implements PreLaunchEntrypoint {
    public void onPreLaunch() {
        long l = UUID.randomUUID().getLeastSignificantBits();
        StringBuilder stringBuilder = new StringBuilder("02");
        for (int i = 0; i < 7; ++i) {
            int n = (int)(l >>> 8 * i) & 0xFF;
            stringBuilder.append(':');
            if (n < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(n));
        }
        System.setProperty("io.netty.machineId", stringBuilder.toString());
        System.out.println("[Kimiko Privacy] Backend telemetry, capture and hardware inventory disabled.");
    }
}

