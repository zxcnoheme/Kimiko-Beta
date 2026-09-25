/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.MediaPlayerModule;
import rtx.kimiko.api.modules.impl.Visuals.particles.lyrics.LyricParticles;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.media.MediaPlayer;
import rtx.kimiko.utils.render.fonts.Fonts;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"lyricstext"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001f\u0010\u000b\u001a\u00020\nH\u0017b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\r\u0010\u0003J\u001b\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0003b\u0002\b\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001cR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0014\u0010'\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010\u0019R\u0014\u0010(\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010!R\u0014\u0010)\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010\u001cR\u0014\u0010*\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010\u0019R\u0014\u0010+\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010!R\u0014\u0010,\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010!R\u0014\u0010-\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010!R\u0014\u0010.\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010!R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107\u00ca\u0001\u0010\b9\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(:\u00a8\u0006;"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/LyricsTextModule;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "", "fadeOutSeconds", "()F", "onDisable", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "syncVocalDetect", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;", "options", "()Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "textSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "lyricMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "lyricLayout", "lyricFont", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "lyricSize", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "lyricOpacity", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "lyricThroughWalls", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "lyricTranscripts", "styleSeparator", "lyricGlow", "lyricExit", "placementSeparator", "lyricRadius", "lyricHeight", "lyricLimit", "lyricSync", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles;", "lyrics", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles;", "", "vocalProcess", "Ljava/lang/String;", "", "vocalRunning", "Z", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "lyricstext", "rtx.kimiko:kimiko"})
public final class LyricsTextModule
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting textSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Текст"));
    @NotNull
    private final ModeSetting lyricMode;
    @NotNull
    private final ModeSetting lyricLayout;
    @NotNull
    private final ModeSetting lyricFont;
    @NotNull
    private final NumberSetting lyricSize;
    @NotNull
    private final NumberSetting lyricOpacity;
    @NotNull
    private final BooleanSetting lyricThroughWalls;
    @NotNull
    private final BooleanSetting lyricTranscripts;
    @NotNull
    private final SeparatorSetting styleSeparator;
    @NotNull
    private final NumberSetting lyricGlow;
    @NotNull
    private final ModeSetting lyricExit;
    @NotNull
    private final SeparatorSetting placementSeparator;
    @NotNull
    private final NumberSetting lyricRadius;
    @NotNull
    private final NumberSetting lyricHeight;
    @NotNull
    private final NumberSetting lyricLimit;
    @NotNull
    private final NumberSetting lyricSync;
    @NotNull
    private final LyricParticles lyrics;
    @Nullable
    private String vocalProcess;
    private boolean vocalRunning;
    @NotNull
    private static final String EXIT_FADE = "Затухание";
    @NotNull
    private static final String EXIT_FALL = "Распад";
    @NotNull
    private static final String FONT_SF = "SF";
    @NotNull
    private static final String FONT_MANASCO = "Manasco";

    public LyricsTextModule() {
        super("Lyrics Text", "Строки играющей песни висят в мире перед вами.", Category.VISUALS);
        String[] stringArray = new String[]{"Строки", "Слова"};
        this.lyricMode = (ModeSetting)this.register((Setting)new ModeSetting("Показывать", "Целыми строками или отдельными словами.", "Строки", stringArray));
        stringArray = new String[]{"Стандарт", "Вразброс", "360"};
        this.lyricLayout = (ModeSetting)this.register((Setting)new ModeSetting("Раскладка", "Как слова расставляются вокруг игрока.", "Стандарт", stringArray).visibleWhen(() -> LyricsTextModule.lyricLayout$lambda$0(this)));
        stringArray = new String[]{FONT_MANASCO, FONT_SF};
        this.lyricFont = (ModeSetting)this.register((Setting)new ModeSetting("Шрифт", "Каким шрифтом рисовать текст.", FONT_MANASCO, stringArray));
        this.lyricSize = (NumberSetting)this.register((Setting)new NumberSetting("Размер строки", "Высота строки в блоках.", 0.3, 0.05, 2.0, 0.01));
        this.lyricOpacity = (NumberSetting)this.register((Setting)new NumberSetting("Прозрачность", "Общая непрозрачность текста.", 1.0, 0.1, 1.0, 0.05));
        this.lyricThroughWalls = (BooleanSetting)this.register((Setting)new BooleanSetting("Сквозь стены", "Не прятать текст за блоками.", false));
        this.lyricTranscripts = (BooleanSetting)this.register((Setting)new BooleanSetting("Авто-титры", "Брать автоматические субтитры YouTube, когда точного текста нигде нет.", true));
        this.styleSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Кастомизация"));
        this.lyricGlow = (NumberSetting)this.register((Setting)new NumberSetting("Свечение", "Ореол вокруг поющегося слова.", 1.0, 0.0, 3.0, 0.05));
        stringArray = new String[]{EXIT_FADE, EXIT_FALL};
        this.lyricExit = (ModeSetting)this.register((Setting)new ModeSetting("Уход", "Что происходит со строкой, когда она заканчивается.", EXIT_FADE, stringArray));
        this.placementSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Размещение"));
        this.lyricRadius = (NumberSetting)this.register((Setting)new NumberSetting("Радиус", "Насколько далеко от игрока встаёт текст.", 3.5, 1.0, 16.0, 0.1));
        this.lyricHeight = (NumberSetting)this.register((Setting)new NumberSetting("Высота", "Высота текста относительно ног игрока.", 1.8, -2.0, 8.0, 0.1));
        this.lyricLimit = (NumberSetting)this.register((Setting)new NumberSetting("Кусков", "Сколько кусков текста висит одновременно.", 3.0, 1.0, 8.0, 1.0));
        this.lyricSync = (NumberSetting)this.register((Setting)new NumberSetting("Смещение", "Правка под задержку звука своей системы, мс.", 0.0, -1500.0, 1500.0, 10.0));
        this.lyrics = new LyricParticles();
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.lyrics.reset();
    }

    @Override
    @Protect(value=Level.CROWN)
    public float fadeOutSeconds() {
        return 0.8f;
    }

    @Override
    protected void onDisable() {
        this.lyrics.reset();
        MediaPlayer.stopVocalDetect();
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        if (!this.isVisuallyActive() || this.mc.world == null || this.mc.player == null || this.mc.gameRenderer == null) {
            return;
        }
        if (this.isEnabled()) {
            MediaPlayer.setTranscriptFallback(this.lyricTranscripts.getValue());
            this.syncVocalDetect();
        }
        this.lyrics.render(event, this.mc, this.options());
    }

    private final void syncVocalDetect() {
        String string = MediaPlayerModule.Companion.processFor(MediaPlayer.getAppId());
        if (string == null) {
            return;
        }
        String process = string;
        if (!Intrinsics.areEqual((Object)process, (Object)this.vocalProcess) || !this.vocalRunning) {
            this.vocalRunning = MediaPlayer.startVocalDetect(process);
            this.vocalProcess = process;
        }
    }

    private final LyricParticles.Options options() {
        return new LyricParticles.Options(this.lyricMode.is("Слова"), this.lyricLayout.getSelected(), LyricsTextModule.Companion.fontId(this.lyricFont.getSelected()), this.lyricSize.getFloat(), this.lyricOpacity.getFloat() * this.visualAlpha(), this.lyricThroughWalls.getValue(), this.lyricGlow.getFloat() * this.visualAlpha(), this.lyricRadius.getFloat(), this.lyricHeight.getFloat(), Math.round(this.lyricLimit.getFloat()), Math.round(this.lyricSync.getFloat()), this.lyricExit.is(EXIT_FALL));
    }

    private static final Boolean lyricLayout$lambda$0(LyricsTextModule this$0) {
        return this$0.lyricMode.is("Слова");
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\n\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/LyricsTextModule.Companion;", "", "<init>", "()V", "", "option", "Lrtx/kimiko/utils/render/fonts/Fonts;", "fontId", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/fonts/Fonts;", "EXIT_FADE", "Ljava/lang/String;", "EXIT_FALL", "FONT_SF", "FONT_MANASCO", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final Fonts fontId(String option) {
            return Intrinsics.areEqual((Object)LyricsTextModule.FONT_SF, (Object)option) ? Fonts.SF_MEDIUM : Fonts.MANASCO;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

