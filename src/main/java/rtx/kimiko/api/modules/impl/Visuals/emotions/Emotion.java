/*
 * Decompiled with CFR 0.152.
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPose;

@Metadata(mv={2, 4, 0}, k=1, xi=48)
public enum Emotion {
    WAVE("WAVE", 3.6f),
    JERK("JERK", 3.0f),
    SHY("SHY", 3.4f),
    DANCE("DANCE", 4.2f),
    CLAP("CLAP", 3.0f),
    BOW("BOW", 4.2f),
    FACEPALM("FACEPALM", 3.4f),
    POINT("POINT", 2.6f),
    TWERK("TWERK", 3.6f);

    @NotNull
    public static final Companion Companion;
    @NotNull
    private final String displayName;
    private final float duration;

    private Emotion(String displayName, float duration) {
        this.displayName = displayName;
        this.duration = duration;
    }

    @JvmName(name="displayName")
    @NotNull
    public final String displayName() {
        return this.displayName;
    }

    @JvmName(name="duration")
    public final float duration() {
        return this.duration;
    }

    public void apply(@NotNull EmotionPose pose, float time) {
        Intrinsics.checkNotNullParameter(pose, "pose");
        switch (this) {
            case WAVE -> {
                float swing = (float)Math.sin(time * 7.0f);
                pose.rightArmRotX = -2.95f;
                pose.rightArmRotZ = -0.2f + swing * 0.34f;
                pose.leftArmRotZ = -0.09f;
                pose.leftArmRotX = 0.06f;
                pose.headRotZ = -0.1f;
                pose.headRotY = (float)Math.sin(time * 3.5f) * 0.16f;
                pose.headRotX = -0.06f;
                pose.bodyRotY = 0.1f;
                pose.upperOffY = swing * 0.12f;
            }
            case JERK -> {
                float shake = (float)Math.sin(time * 19.0f);
                pose.rightArmRotX = -0.78f + shake * 0.32f;
                pose.rightArmRotZ = -0.42f;
                pose.rightArmRotY = 0.28f;
                pose.leftArmRotZ = -0.17f;
                pose.leftArmRotX = 0.12f;
                pose.bodyRotX = 0.07f + shake * 0.05f;
                pose.headRotX = -0.28f + shake * 0.1f;
                pose.headRotZ = 0.09f;
                pose.rightLegRotZ = 0.09f;
                pose.leftLegRotZ = -0.09f;
                pose.upperOffY = shake * 0.3f;
            }
            case SHY -> {
                float sway = (float)Math.sin(time * 2.4f);
                float fidget = (float)Math.sin(time * 5.0f) * 0.045f;
                pose.rightArmRotX = -2.36f + fidget;
                pose.rightArmRotZ = 0.46f;
                pose.rightArmRotY = -0.14f;
                pose.leftArmRotX = -2.36f - fidget;
                pose.leftArmRotZ = -0.46f;
                pose.leftArmRotY = 0.14f;
                pose.headRotX = 0.3f;
                pose.headRotY = sway * 0.26f;
                pose.headRotZ = 0.05f;
                pose.bodyRotX = 0.1f;
                pose.bodyRotY = sway * 0.12f;
                pose.rightLegRotZ = 0.13f;
                pose.leftLegRotZ = -0.13f;
                pose.rightLegRotY = 0.16f;
                pose.leftLegRotY = -0.16f;
                pose.upperOffY = 0.45f;
            }
            case DANCE -> {
                float beat = (float)Math.sin(time * 6.0f);
                float slow = (float)Math.sin(time * 3.0f);
                pose.rightArmRotZ = 2.15f + beat * 0.45f;
                pose.rightArmRotX = -0.35f + beat * 0.25f;
                pose.leftArmRotZ = -2.15f + beat * 0.45f;
                pose.leftArmRotX = -0.35f - beat * 0.25f;
                pose.bodyRotY = slow * 0.34f;
                pose.bodyRotZ = beat * 0.1f;
                pose.headRotY = slow * 0.42f;
                pose.headRotZ = beat * 0.16f;
                pose.rightLegRotX = beat * 0.34f;
                pose.leftLegRotX = -beat * 0.34f;
                pose.upperOffY = -Math.abs(beat) * 0.8f;
            }
            case CLAP -> {
                float clap = ((float)Math.sin(time * 9.0f) + 1.0f) * 0.5f;
                pose.rightArmRotX = -1.45f;
                pose.leftArmRotX = -1.45f;
                pose.rightArmRotY = -0.36f - clap * 0.3f;
                pose.leftArmRotY = 0.36f + clap * 0.3f;
                pose.rightArmRotZ = 0.22f;
                pose.leftArmRotZ = -0.22f;
                pose.headRotX = 0.1f + clap * 0.07f;
                pose.bodyRotX = 0.06f;
                pose.upperOffY = clap * 0.22f;
            }
            case BOW -> {
                float phase = 0.5f - (float)Math.cos(time * 1.5f) * 0.5f;
                float bow = phase * phase * (3.0f - 2.0f * phase);
                pose.lean(1.15f * bow);
                pose.rightArmRotX += -1.15f * bow;
                pose.rightArmRotZ = -0.72f * bow;
                pose.leftArmRotX += 0.25f * bow;
                pose.leftArmRotZ = -0.2f * bow;
                pose.headRotX += 0.3f * bow;
                pose.rightLegRotX = -0.1f * bow;
            }
            case FACEPALM -> {
                float shake = (float)Math.sin(time * 1.8f);
                pose.rightArmRotX = -2.52f;
                pose.rightArmRotZ = 0.34f;
                pose.rightArmRotY = -0.14f;
                pose.leftArmRotZ = -0.12f;
                pose.leftArmRotX = 0.1f;
                pose.headRotX = 0.42f;
                pose.headRotY = shake * 0.2f;
                pose.bodyRotX = 0.14f;
                pose.upperOffY = 0.25f;
            }
            case POINT -> {
                float jab = (float)Math.sin(time * 4.0f);
                pose.rightArmRotX = -1.52f + jab * 0.09f;
                pose.rightArmRotY = -0.16f;
                pose.rightArmRotZ = 0.06f;
                pose.leftArmRotZ = -0.11f;
                pose.leftArmRotX = 0.08f;
                pose.headRotY = -0.1f;
                pose.bodyRotY = -0.16f;
                pose.upperOffY = jab * 0.07f;
            }
            case TWERK -> {
                float shake = (float)Math.sin(time * 14.0f);
                float squat = 0.24f;
                pose.lean(1.02f + shake * 0.1f);
                pose.rightLegRotX = -squat;
                pose.leftLegRotX = -squat;
                pose.rightLegRotZ = 0.22f;
                pose.leftLegRotZ = -0.22f;
                pose.upperOffY += 12.0f - 12.0f * (float)Math.cos(squat);
                pose.upperOffZ = 1.7f + shake * 1.4f;
                pose.rightArmRotX += -0.55f + shake * 0.1f;
                pose.leftArmRotX += -0.55f + shake * 0.1f;
                pose.rightArmRotZ = 0.32f;
                pose.leftArmRotZ = -0.32f;
                pose.headRotX -= 0.8f;
            }
        }
    }

    @NotNull
    public static EnumEntries<Emotion> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    @JvmStatic
    @Nullable
    public static final Emotion byDisplayName(@Nullable String name) {
        return Companion.byDisplayName(name);
    }

    @JvmStatic
    @NotNull
    public static final String[] displayNames() {
        return Companion.displayNames();
    }

    static {
        Companion = new Companion(null);
    }

    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final Emotion byDisplayName(@Nullable String name) {
            if (name == null) {
                return null;
            }
            for (Emotion emotion : Emotion.values()) {
                if (!StringsKt.equals((String)emotion.displayName(), (String)name, (boolean)true)) continue;
                return emotion;
            }
            return null;
        }

        @JvmStatic
        @NotNull
        public final String[] displayNames() {
            Emotion[] values = Emotion.values();
            String[] names = new String[values.length];
            for (int i = 0; i < values.length; ++i) {
                names[i] = values[i].displayName();
            }
            return names;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
