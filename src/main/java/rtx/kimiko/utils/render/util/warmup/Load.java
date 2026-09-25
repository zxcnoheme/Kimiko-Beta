/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.util.warmup;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetWarmup;
import rtx.kimiko.utils.render.fonts.Fonts;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0007\u0010\u0003R\u001b\u0010\n\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000b\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/render/util/warmup/Load;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "runStartupWarmup", "warmupFonts", "", "Lkotlin/jvm/JvmField;", "startupWarmupDone", "Z", "initialReloadSeen", "rtx.kimiko:kimiko"})
public final class Load {
    @NotNull
    public static final Load INSTANCE = new Load();
    @JvmField
    public static volatile boolean startupWarmupDone;
    @JvmField
    public static volatile boolean initialReloadSeen;

    private Load() {
    }

    @JvmStatic
    public static final void runStartupWarmup() {
        if (startupWarmupDone) {
            return;
        }
        startupWarmupDone = true;
        Load.warmupFonts();
        try {
            CustomPetWarmup.warmup();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final void warmupFonts() {
        String ascii = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,:;!?-_()[]{}<>/\\|@#$%^&*+=\"'`~";
        String cyrillic = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String all = ascii + cyrillic;
        Fonts[] fontsArray = new Fonts[]{Fonts.MEDIUM, Fonts.REGULAR, Fonts.SEMIBOLD, Fonts.BOLD, Fonts.SF, Fonts.SF_MEDIUM, Fonts.KIMIKO, Fonts.MAINMENU};
        for (Fonts f : fontsArray) {
            try {
                f.width(all, 8.0f);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            try {
                f.msdfWidth(all, 8.0f);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }
}

