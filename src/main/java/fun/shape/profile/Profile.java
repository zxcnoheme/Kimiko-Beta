/*
 * Decompiled with CFR 0.152.
 */
package fun.shape.profile;

import fun.shape.profile.Role;
import recovery.privacy.NetworkPolicy;

public class Profile {
    public static String username = "Guest";
    public static int uid = 0;
    public static Role role = Role.GUEST;
    public static String hwid = "";
    public static String subscriptionEndDate = "";
    public static String avatarUrl = "";

    private static String read(String string) {
        return NetworkPolicy.profileSetting(string);
    }

    private static Role parseRole(String s) {
        if (s == null || s.isEmpty()) {
            return Role.GUEST;
        }
        try {
            return Role.valueOf(s.trim().toUpperCase());
        }
        catch (Throwable ignored) {
            return Role.GUEST;
        }
    }

    private Profile() {
    }

    public static String getUsername() {
        return username;
    }

    public static int getUid() {
        return uid;
    }

    public static Role getRole() {
        return role;
    }

    public static String getHwid() {
        return "";
    }

    public static String getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public static String getAvatarUrl() {
        return avatarUrl;
    }

    static {
        try {
            String keyCodec;
            String s;
            String n;
            String u = Profile.read("shape.profile.uid");
            if (u != null && !u.isEmpty()) {
                try {
                    uid = Math.max(0, Integer.parseInt(u.trim()));
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
            if ((n = Profile.read("shape.profile.name")) != null && !n.isEmpty()) {
                username = n;
            }
            role = Profile.parseRole(Profile.read("shape.profile.role"));
            String h = Profile.read("shape.profile.hwid");
            if (h != null && !h.isEmpty()) {
                hwid = h;
            }
            if ((s = Profile.read("shape.profile.subEnds")) != null && !s.isEmpty()) {
                subscriptionEndDate = s;
            }
            if ((keyCodec = Profile.read("shape.profile.avatar")) != null && !keyCodec.isEmpty()) {
                avatarUrl = keyCodec;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}

