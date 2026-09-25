/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.text.StyleSpriteSource
 *  net.minecraft.text.StyleSpriteSource.Font
 *  net.minecraft.util.Formatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.scoreboard.Team
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.RaycastContext
 *  net.minecraft.world.RaycastContext.FluidHandling
 *  net.minecraft.world.RaycastContext.ShapeType
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.text.TextVisitFactory
 *  net.minecraft.text.MutableText
 *  net.minecraft.text.TextColor
 *  net.minecraft.text.StringVisitable
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.entity.decoration.DisplayEntity.TextDisplayEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals;

import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.text.StyleSpriteSource;
import net.minecraft.util.Formatting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.scoreboard.Team;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.text.TextVisitFactory;
import net.minecraft.text.MutableText;
import net.minecraft.text.TextColor;
import net.minecraft.text.StringVisitable;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.decoration.DisplayEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.drags.RenderUnderHand;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.events.impl.render.UnderHandRenderEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Utils.Globals;
import rtx.kimiko.api.modules.impl.Utils.StreamerMode;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.net.ClientPresence;
import rtx.kimiko.utils.network.Network;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.fonts.core.msdf.GlyphNormalizer;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFont;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.util.underhand.UnderHand2D;

@RenderUnderHand
@Feature(value={"nametags"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ee\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 u2\u00020\u0001:\u0005vwxyuB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ'\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001f\u0010 JA\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\u0006\u0010)\u001a\u00020&H\u0002\u00a2\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b-\u0010.J\u001f\u00101\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u00100\u001a\u00020/H\u0002\u00a2\u0006\u0004\b1\u00102J'\u00105\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u00100\u001a\u00020/2\u0006\u00104\u001a\u000203H\u0002\u00a2\u0006\u0004\b5\u00106J'\u00109\u001a\u00020\u001e2\u0006\u00107\u001a\u00020$2\u0006\u00108\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b9\u0010:J\u0017\u0010;\u001a\u00020\u00042\u0006\u00104\u001a\u000203H\u0002\u00a2\u0006\u0004\b;\u0010<J\u001b\u0010>\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020=H\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b>\u0010?J'\u0010D\u001a\u00020\u00042\u0006\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u00020\u00062\u0006\u0010C\u001a\u00020/H\u0002\u00a2\u0006\u0004\bD\u0010EJ\u001b\u0010G\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020FH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\bG\u0010HJ\u0017\u0010I\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bI\u0010JJ\u001f\u0010M\u001a\u00020\u00042\u0006\u0010L\u001a\u00020K2\u0006\u0010B\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bM\u0010NJ+\u0010S\u001a\b\u0012\u0004\u0012\u00020R0O2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020P0O2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bS\u0010TJ!\u0010X\u001a\u00020\u00122\u0006\u0010U\u001a\u00020\u00162\b\u0010W\u001a\u0004\u0018\u00010VH\u0002\u00a2\u0006\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010`\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b`\u0010_R\u0014\u0010b\u001a\u00020a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u001a\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00060d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010fR\u001a\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00060d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010fR\u0016\u0010h\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010iR \u0010k\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e0j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010m\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010nR\u0016\u0010o\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u0016\u0010q\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010pR\u0016\u0010r\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0016\u0010t\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010s\u00ca\u0001\u0002\bz\u00ca\u0001\u0010\b{\u0012\f\b|\u0012\b\b\fJ\u0004\b\b(}\u00a8\u0006~"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NameTags;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Tag;", "obtainTag", "()Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Tag;", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags$VisibilityCache;", "cache", "Lnet/minecraft/Text;", "displayName", "", "nameSize", "ensureLayout", "(Lrtx/kimiko/api/modules/impl/Visuals/NameTags$VisibilityCache;Lnet/minecraft/Text;I)V", "", "healthText", "healthSize", "heartSize", "ensureHealthLayout", "(Lrtx/kimiko/api/modules/impl/Visuals/NameTags$VisibilityCache;Ljava/lang/String;II)V", "Lnet/minecraft/PlayerEntity;", "player", "", "isValid", "(Lnet/minecraft/PlayerEntity;)Z", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "Lnet/minecraft/Vec3d;", "cameraPos", "", "x", "y", "z", "Lorg/joml/Vector4f;", "project", "(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;DDD)Lorg/joml/Vector4f;", "resolveName", "(Lnet/minecraft/PlayerEntity;)Lnet/minecraft/Text;", "", "partialTicks", "computeVisible", "(Lnet/minecraft/PlayerEntity;F)Z", "", "now", "isVisible", "(Lnet/minecraft/PlayerEntity;FJ)Z", "from", "to", "rayClear", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/PlayerEntity;)Z", "pruneCache", "(J)V", "Lrtx/kimiko/api/events/impl/render/UnderHandRenderEvent;", "onUnderHand", "(Lrtx/kimiko/api/events/impl/render/UnderHandRenderEvent;)V", "Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;", "ctx", "tag", "s", "drawTagUnderHand", "(Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Tag;F)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "drawTagBackground", "(Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Tag;)V", "Lnet/minecraft/DrawContext;", "graphics", "drawTagText", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Tag;)V", "", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags$TextRun;", "runs", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Seg;", "buildSegments", "(Ljava/util/List;I)Ljava/util/List;", "seg", "Lnet/minecraft/StyleSpriteSource;", "nativeFont", "nativeWidth", "(Ljava/lang/String;Lnet/minecraft/StyleSpriteSource;)I", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "displaySeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "showHealth", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "selfTag", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "scale", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "", "tags", "Ljava/util/List;", "tagPool", "tagIndex", "I", "", "visibilityCache", "Ljava/util/Map;", "projectionScratch", "Lorg/joml/Vector4f;", "lastFrameMs", "J", "frameDeltaMs", "lastNameConfident", "Z", "underHandDrawn", "Companion", "Seg", "TextRun", "Tag", "VisibilityCache", "Lrtx/kimiko/api/drags/RenderUnderHand;", "Lrtx/kimiko/api/liteapi/Feature;", "value", "nametags", "rtx.kimiko:kimiko"})
public final class NameTags
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting displaySeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Отображение"));
    @NotNull
    private final BooleanSetting showHealth = (BooleanSetting)this.register((Setting)new BooleanSetting("Здоровье", "Показывать здоровье игрока рядом с именем.", true));
    @NotNull
    private final BooleanSetting selfTag = (BooleanSetting)this.register((Setting)new BooleanSetting("Себя", "Также отображать свою табличку от третьего лица.", false));
    @NotNull
    private final SliderSetting scale = (SliderSetting)this.register((Setting)new SliderSetting("Масштаб", "Общий размер табличек.").range(0.5f, 2.0f).setValue(1.0f));
    @NotNull
    private final List<Tag> tags = new ArrayList();
    @NotNull
    private final List<Tag> tagPool = new ArrayList();
    private int tagIndex;
    @NotNull
    private final Map<Integer, VisibilityCache> visibilityCache = new HashMap();
    @NotNull
    private final Vector4f projectionScratch = new Vector4f();
    private long lastFrameMs = System.currentTimeMillis();
    private long frameDeltaMs = 1L;
    private boolean lastNameConfident;
    private boolean underHandDrawn;
    private static final boolean UNDER_HAND = NameTags.class.isAnnotationPresent(RenderUnderHand.class);
    @NotNull
    private static final String HEART_GLYPH = "A";
    @NotNull
    private static final String LIGHTNING_GLYPH = "L";
    private static final int LIGHTNING_CP = 9889;
    @NotNull
    private static final String LOGO_GLYPH = "x";
    private static final float BADGE_GAP = 2.0f;
    private static final float NAME_SIZE = 7.0f;
    private static final float HEALTH_SIZE = 6.0f;
    private static final float HEART_SIZE = 6.0f;
    private static final float HEART_GAP = 2.0f;
    private static final int HEART_COLOR = -42386;
    private static final float NATIVE_CAP_H = 7.5f;
    @NotNull
    private static final StyleSpriteSource.Font DEFAULT_FONT;
    private static final float PAD_X = 3.0f;
    private static final float PAD_Y = 2.0f;
    private static final float HEALTH_GAP = 3.0f;
    private static final float RADIUS = 4.0f;
    private static final int NAME_COLOR = -1;
    private static final int HEALTH_COLOR = -5196096;
    private static final int BACKGROUND_COLOR = -1778384896;
    private static final int BADGE_LEFT_COLOR = -7695373;
    private static final int BADGE_RIGHT_COLOR = -4938241;
    private static final double MAX_DISTANCE_SQR = 4096.0;
    private static final long VISIBILITY_REFRESH_MS = 150L;
    private static final long VISIBILITY_PRUNE_MS = 1000L;
    private static final float HIDDEN_SCALE = 0.85f;
    private static final float ANIM_MS = 150.0f;
    private static final long LAYOUT_TTL_MS = 500L;
    private static final long HIDE_MEMO_MS = 100L;
    private static final long NAME_RESOLVE_TTL_MS = 500L;
    private static final Pattern EMPTY_TOKEN;
    @JvmField
    @Nullable
    public static NameTags INSTANCE;
    @NotNull
    private static final Int2BooleanOpenHashMap hideMemo;
    private static long hideMemoAtMs;
    @NotNull
    private static final ArrayList<Entity> hologramEntities;
    @NotNull
    private static final ArrayList<Text> hologramLines;
    private static boolean hologramReady;
    private static boolean hologramReuse;

    public NameTags() {
        super("Name Tags", "Рисует таблички с именами только над видимыми игроками.", Category.VISUALS);
        INSTANCE = this;
    }

    @Override
    protected void onDisable() {
        this.tags.clear();
        this.tagPool.clear();
        this.tagIndex = 0;
        this.visibilityCache.clear();
    }

    private final Tag obtainTag() {
        if (this.tagIndex < this.tagPool.size()) {
            int n = this.tagIndex;
            this.tagIndex = n + 1;
            return this.tagPool.get(n);
        }
        Tag tag = new Tag();
        this.tagPool.add(tag);
        int n = this.tagIndex;
        this.tagIndex = n + 1;
        return tag;
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        if (event.isPortalPass()) {
            return;
        }
        this.tags.clear();
        this.tagIndex = 0;
        NameTags.Companion.resetHologramFrame();
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            return;
        }
        long now = System.currentTimeMillis();
        this.frameDeltaMs = Math.max(1L, Math.min(now - this.lastFrameMs, 100L));
        this.lastFrameMs = now;
        float partialTicks = event.getPartialTicks();
        Vec3d vec3d2 = event.getCamera() == null ? this.mc.gameRenderer.getCamera().getCameraPos() : event.getCamera().getCameraPos();
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d cameraPos = vec3d2;
        float screenWidth = Position.Companion.screenWidth();
        float screenHeight = Position.Companion.screenHeight();
        Matrix4f positionMatrix = event.getPositionMatrix();
        Matrix4f projectionMatrix = event.getProjectionMatrix();
        boolean health = this.showHealth.getValue();
        boolean badgeEnabled = Globals.Companion.tagsBadge();
        float userScale = this.scale.getFloat();
        int nameSize = NameTags.Companion.roundSize(7.0f * userScale);
        int healthSize = NameTags.Companion.roundSize(6.0f * userScale);
        int heartSize = NameTags.Companion.roundSize(6.0f * userScale);
        int padX = Math.round(3.0f * userScale);
        int padY = Math.round(2.0f * userScale);
        int heartGap = Math.round(2.0f * userScale);
        int healthGap = Math.round(3.0f * userScale);
        int radius = Math.round(4.0f * userScale);
        int nudge = Math.max(1, Math.round(userScale));
        for (AbstractClientPlayerEntity other : level.getPlayers()) {
            if (!this.isValid((PlayerEntity)other) || player.squaredDistanceTo((Entity)other) > 4096.0) continue;
            boolean visible = this.isVisible((PlayerEntity)other, partialTicks, now);
            VisibilityCache cache2 = this.visibilityCache.get(other.getId());
            float tagAlpha = cache2 != null ? cache2.getAlpha() : 0.0f;
            if (tagAlpha <= 0.01f && !visible) continue;
            Vec3d pos = other.getLerpedPos(partialTicks);
            double topY = pos.y + (double)other.getHeight() + 0.35;
            Vector4f projected = this.project(positionMatrix, projectionMatrix, cameraPos, pos.x, topY, pos.z);
            if (projected == null) continue;
            float screenX = (projected.x / projected.w * 0.5f + 0.5f) * screenWidth;
            float screenY = (1.0f - (projected.y / projected.w * 0.5f + 0.5f)) * screenHeight;
            if (Float.isNaN(screenX) || Float.isNaN(screenY) || cache2 == null) continue;
            Text cached = cache2.getResolvedName();
            Text displayName = null;
            if (cached != null && now - cache2.getResolvedAtMs() < 500L) {
                displayName = cached;
            } else {
                displayName = this.resolveName((PlayerEntity)other);
                cache2.setResolvedAtMs(now);
                if (this.lastNameConfident) {
                    cache2.setResolvedName(displayName);
                } else {
                    if (cached == null) continue;
                    displayName = cached;
                }
            }
            this.ensureLayout(cache2, displayName, nameSize);
            String healthText = health ? NameTags.Companion.formatHealth(Network.getResolvedHealth((LivingEntity)other, true)) : null;
            float heartW = 0.0f;
            float healthNumW = 0.0f;
            if (healthText != null) {
                this.ensureHealthLayout(cache2, healthText, healthSize, heartSize);
                heartW = cache2.getHeartWidth();
                healthNumW = cache2.getHealthNumberWidth();
            }
            Tag tag = this.obtainTag();
            tag.setSegs(cache2.getSegs());
            tag.setNameWidth(cache2.getNameWidth());
            tag.setHealth(healthText);
            tag.setHeartWidth(heartW);
            tag.setHealthNumberWidth(healthNumW);
            tag.setNameSize(nameSize);
            tag.setHealthSize(healthSize);
            tag.setHeartSize(heartSize);
            tag.setPadX(padX);
            tag.setPadY(padY);
            tag.setHeartGap(heartGap);
            tag.setHealthGap(healthGap);
            tag.setRadius(radius);
            tag.setNudge(nudge);
            tag.setAlpha(tagAlpha);
            tag.setScale(cache2.getScale());
            boolean badge = badgeEnabled && ClientPresence.INSTANCE.isKimikoUser(other.getGameProfile().name());
            float badgeWidth = badge ? Fonts.KIMIKO.msdfWidth(LOGO_GLYPH, nameSize) + 2.0f : 0.0f;
            tag.setBadge(badge);
            tag.setBadgeWidth(badgeWidth);
            float healthWidth = healthText == null ? 0.0f : heartW + (float)heartGap + healthNumW;
            tag.setBoxWidth((float)padX * 2.0f + badgeWidth + cache2.getNameWidth() + (healthText == null ? 0.0f : (float)healthGap) + healthWidth);
            tag.setBoxHeight((float)nameSize + (float)padY * 2.0f);
            tag.setBoxX(screenX - tag.getBoxWidth() * 0.5f);
            tag.setBoxY(screenY - tag.getBoxHeight());
            tag.setDepth(projected.w);
            this.tags.add(tag);
        }
        CollectionsKt.sortWith(this.tags, NameTags::onWorldRender$lambda$0);
        this.pruneCache(now);
    }

    private final void ensureLayout(VisibilityCache cache2, Text displayName, int nameSize) {
        long now = System.currentTimeMillis();
        if (cache2.getSegs() != null && cache2.getBuiltSize() == nameSize && now - cache2.getLayoutAtMs() < 500L) {
            return;
        }
        String string = displayName.getString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
        String name = string;
        if (cache2.getSegs() != null && cache2.getBuiltSize() == nameSize && Intrinsics.areEqual((Object)name, (Object)cache2.getNameSig())) {
            cache2.setLayoutAtMs(now);
            return;
        }
        cache2.setNameSig(name);
        cache2.setBuiltSize(nameSize);
        cache2.setLayoutAtMs(now);
        List<Seg> segs = this.buildSegments(NameTags.Companion.parseRuns(displayName, name), nameSize);
        float total = 0.0f;
        for (Seg seg : segs) {
            total += seg.getWidth();
        }
        cache2.setSegs(segs);
        cache2.setNameWidth(total);
    }

    private final void ensureHealthLayout(VisibilityCache cache2, String healthText, int healthSize, int heartSize) {
        int sizeKey = healthSize << 16 | heartSize;
        if (sizeKey == cache2.getHealthBuiltSize() && Intrinsics.areEqual((Object)healthText, (Object)cache2.getHealthSig())) {
            return;
        }
        cache2.setHealthSig(healthText);
        cache2.setHealthBuiltSize(sizeKey);
        cache2.setHeartWidth(Fonts.HEART.msdfWidth(HEART_GLYPH, heartSize));
        cache2.setHealthNumberWidth(Fonts.MEDIUM.msdfWidth(healthText, healthSize));
    }

    private final boolean isValid(PlayerEntity player) {
        ClientPlayerEntity local = this.mc.player;
        if (Intrinsics.areEqual((Object)player, (Object)local)) {
            return this.selfTag.getValue() && !this.mc.options.getPerspective().isFirstPerson();
        }
        return player.isAlive() && !player.isRemoved() && player.getHealth() > 0.0f && !player.isInvisible();
    }

    private final Vector4f project(Matrix4f positionMatrix, Matrix4f projectionMatrix, Vec3d cameraPos, double x, double y, double z) {
        Vector4f vec = this.projectionScratch.set((float)(x - cameraPos.x), (float)(y - cameraPos.y), (float)(z - cameraPos.z), 1.0f);
        positionMatrix.transform(vec);
        projectionMatrix.transform(vec);
        if (vec.w <= 1.0E-4f) {
            return null;
        }
        return vec;
    }

    private final Text resolveName(PlayerEntity player) {
        Text resolved = Companion.resolveDisplayName(player, true);
        this.lastNameConfident = resolved != null;
        Text text2 = resolved;
        if (text2 == null) {
            MutableText mutableText2 = Text.literal((String)"NPC");
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"literal(...)");
            text2 = (Text)mutableText2;
        }
        return text2;
    }

    private final boolean computeVisible(PlayerEntity player, float partialTicks) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity local = clientPlayerEntity2;
        Vec3d vec3d2 = local.getCameraPosVec(partialTicks);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d eyes = vec3d2;
        Vec3d vec3d3 = player.getCameraPosVec(partialTicks);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getEyePosition(...)");
        if (this.rayClear(eyes, vec3d3, player)) {
            return true;
        }
        Vec3d vec3d4 = player.getLerpedPos(partialTicks).add(0.0, (double)player.getHeight() * 0.5, 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"add(...)");
        Vec3d center = vec3d4;
        return this.rayClear(eyes, center, player);
    }

    private final boolean isVisible(PlayerEntity player, float partialTicks, long now) {
        VisibilityCache cache2 = this.visibilityCache.get(player.getId());
        if (cache2 == null) {
            cache2 = new VisibilityCache();
            this.visibilityCache.put(player.getId(), cache2);
        }
        if (now - cache2.getCheckedAtMs() >= 150L) {
            cache2.setVisible(this.computeVisible(player, partialTicks));
            cache2.setCheckedAtMs(now);
        }
        cache2.setTouchedAtMs(now);
        float dir = cache2.getVisible() ? 1.0f : -1.0f;
        cache2.setAnim(MathHelper.clamp((float)(cache2.getAnim() + dir * ((float)this.frameDeltaMs / 150.0f)), (float)0.0f, (float)1.0f));
        float eased = cache2.getAnim() * cache2.getAnim() * (3.0f - 2.0f * cache2.getAnim());
        cache2.setAlpha(eased);
        cache2.setScale(0.85f + 0.14999998f * eased);
        return cache2.getVisible();
    }

    private final boolean rayClear(Vec3d from, Vec3d to, PlayerEntity player) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return false;
        }
        ClientWorld level = clientWorld3;
        BlockHitResult blockHitResult2 = level.raycast(new RaycastContext(from, to, RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.NONE, (Entity)player));
        Intrinsics.checkNotNullExpressionValue((Object)blockHitResult2, (String)"clip(...)");
        BlockHitResult hit = blockHitResult2;
        return hit.getType() == HitResult.Type.MISS;
    }

    private final void pruneCache(long now) {
        if (this.visibilityCache.isEmpty()) {
            return;
        }
        this.visibilityCache.values().removeIf(cache -> now - cache.getTouchedAtMs() > 1000L);
    }

    @EventHandler
    private final void onUnderHand(UnderHandRenderEvent event) {
        if (!UNDER_HAND || this.tags.isEmpty()) {
            return;
        }
        this.underHandDrawn = true;
        UnderHand2D ctx = event.ctx();
        for (Tag tag : this.tags) {
            float s = NameTags.Companion.isSettled(tag) ? 1.0f : tag.getScale();
            this.drawTagUnderHand(ctx, tag, s);
            ctx.barrier();
        }
    }

    private final void drawTagUnderHand(UnderHand2D ctx, Tag tag, float s) {
        float originX = tag.getBoxX() + tag.getBoxWidth() * 0.5f;
        float originY = tag.getBoxY() + tag.getBoxHeight() * 0.5f;
        ctx.rect(NameTags.Companion.sx(originX, tag.getBoxX(), s), NameTags.Companion.sy(originY, tag.getBoxY(), s), tag.getBoxWidth() * s, tag.getBoxHeight() * s, (float)tag.getRadius() * s, ColorEngine.multAlpha(-1778384896, tag.getAlpha()));
        float textY = tag.getBoxY() + (float)tag.getPadY();
        float nameY = textY - (float)tag.getNudge();
        if (tag.getBadge()) {
            ctx.msdfText(Fonts.KIMIKO, LOGO_GLYPH, NameTags.Companion.sx(originX, tag.getBoxX() + (float)tag.getPadX(), s), NameTags.Companion.sy(originY, nameY + 1.0f, s), (float)tag.getNameSize() * s, NameTags.Companion.badgeLeft(tag.getAlpha()), NameTags.Companion.badgeRight(tag.getAlpha()), NameTags.Companion.badgeRight(tag.getAlpha()), NameTags.Companion.badgeLeft(tag.getAlpha()));
        }
        float cursor = tag.getBoxX() + (float)tag.getPadX() + tag.getBadgeWidth();
        List<Seg> list = tag.getSegs();
        if (list == null) {
            return;
        }
        List<Seg> segs = list;
        for (Seg seg : segs) {
            int color = ColorEngine.multAlpha(seg.getColor(), tag.getAlpha());
            if (seg.getKind() == 0) {
                boolean icon = seg.getMsdfFont() == Fonts.KIMIKO;
                float nudgeX = icon ? 2.0f : 0.0f;
                float nudgeY = icon ? 1.0f : 0.0f;
                Fonts fonts = seg.getMsdfFont();
                if (fonts == null) {
                    fonts = Fonts.SEMIBOLD;
                }
                Fonts msdfFont = fonts;
                ctx.msdfText(msdfFont, seg.getText(), NameTags.Companion.sx(originX, cursor + nudgeX, s), NameTags.Companion.sy(originY, nameY + nudgeY, s), (float)tag.getNameSize() * s, color);
            } else {
                MutableText mutableText2 = seg.getNativeFont() == null || Intrinsics.areEqual((Object)seg.getNativeFont(), (Object)DEFAULT_FONT) ? Text.literal((String)seg.getText()) : Text.literal((String)seg.getText()).setStyle(Style.EMPTY.withFont(seg.getNativeFont()));
                Intrinsics.checkNotNull((Object)mutableText2);
                MutableText comp = mutableText2;
                ctx.nativeText((Text)comp, NameTags.Companion.sx(originX, cursor, s), NameTags.Companion.sy(originY, nameY + 1.0f, s), seg.getNativeScale() * s, color);
            }
            cursor += seg.getWidth();
        }
        if (tag.getHealth() != null) {
            float hpX = tag.getBoxX() + (float)tag.getPadX() + tag.getBadgeWidth() + tag.getNameWidth() + (float)tag.getHealthGap();
            ctx.msdfText(Fonts.HEART, HEART_GLYPH, NameTags.Companion.sx(originX, hpX, s), NameTags.Companion.sy(originY, textY + (float)tag.getNudge(), s), (float)tag.getHeartSize() * s, ColorEngine.multAlpha(-42386, tag.getAlpha()));
            String string = tag.getHealth();
            Intrinsics.checkNotNull((Object)string);
            ctx.msdfText(Fonts.MEDIUM, string, NameTags.Companion.sx(originX, hpX += tag.getHeartWidth() + (float)tag.getHeartGap(), s), NameTags.Companion.sy(originY, textY, s), (float)tag.getHealthSize() * s, ColorEngine.multAlpha(-5196096, tag.getAlpha()));
        }
    }

    @EventHandler
    private final void onHud(HudRenderEvent event) {
        boolean drawnUnderHand = this.underHandDrawn;
        this.underHandDrawn = false;
        if (UNDER_HAND && drawnUnderHand || this.tags.isEmpty()) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        for (Tag tag : this.tags) {
            boolean scaled;
            boolean bl = scaled = !NameTags.Companion.isSettled(tag);
            if (scaled) {
                float originX = tag.getBoxX() + tag.getBoxWidth() * 0.5f;
                float originY = tag.getBoxY() + tag.getBoxHeight() * 0.5f;
                graphics.getMatrices().pushMatrix();
                graphics.getMatrices().translate(originX, originY);
                graphics.getMatrices().scale(tag.getScale(), tag.getScale());
                graphics.getMatrices().translate(-originX, -originY);
            }
            Render2D.beginFrame(graphics);
            this.drawTagBackground(tag);
            Render2D.flush();
            Render2D.beginFrame(graphics);
            this.drawTagText(graphics, tag);
            Render2D.flush();
            if (!scaled) continue;
            graphics.getMatrices().popMatrix();
        }
    }

    private final void drawTagBackground(Tag tag) {
        Render2D.rect(tag.getBoxX(), tag.getBoxY(), tag.getBoxWidth(), tag.getBoxHeight(), tag.getRadius(), ColorEngine.multAlpha(-1778384896, tag.getAlpha()));
    }

    private final void drawTagText(DrawContext graphics, Tag tag) {
        TextRenderer textRenderer2 = this.mc.textRenderer;
        Intrinsics.checkNotNullExpressionValue((Object)textRenderer2, (String)"font");
        TextRenderer font = textRenderer2;
        float textY = tag.getBoxY() + (float)tag.getPadY();
        float nameY = textY - (float)tag.getNudge();
        if (tag.getBadge()) {
            Fonts.KIMIKO.msdf(LOGO_GLYPH, tag.getBoxX() + (float)tag.getPadX(), nameY + 1.0f, (float)tag.getNameSize(), NameTags.Companion.badgeLeft(tag.getAlpha()), NameTags.Companion.badgeRight(tag.getAlpha()), NameTags.Companion.badgeRight(tag.getAlpha()), NameTags.Companion.badgeLeft(tag.getAlpha()));
        }
        float cursor = tag.getBoxX() + (float)tag.getPadX() + tag.getBadgeWidth();
        List<Seg> list = tag.getSegs();
        if (list == null) {
            return;
        }
        List<Seg> segs = list;
        for (Seg seg : segs) {
            int color = ColorEngine.multAlpha(seg.getColor(), tag.getAlpha());
            if (seg.getKind() == 0) {
                boolean icon = seg.getMsdfFont() == Fonts.KIMIKO;
                float nudgeX = icon ? 2.0f : 0.0f;
                float nudgeY = icon ? 1.0f : 0.0f;
                Fonts fonts = seg.getMsdfFont();
                if (fonts == null) {
                    fonts = Fonts.SEMIBOLD;
                }
                Fonts msdfFont = fonts;
                msdfFont.msdf(seg.getText(), cursor + nudgeX, nameY + nudgeY, tag.getNameSize(), color);
            } else {
                float gis = Render2DCoordinateSpace.guiIndependentScale();
                graphics.getMatrices().pushMatrix();
                graphics.getMatrices().scale(gis, gis);
                graphics.getMatrices().translate(cursor, nameY + 1.0f);
                graphics.getMatrices().scale(seg.getNativeScale(), seg.getNativeScale());
                if (seg.getNativeFont() == null || Intrinsics.areEqual((Object)seg.getNativeFont(), (Object)DEFAULT_FONT)) {
                    graphics.drawText(font, seg.getText(), 0, 0, color, false);
                } else {
                    graphics.drawText(font, (Text)Text.literal((String)seg.getText()).setStyle(Style.EMPTY.withFont(seg.getNativeFont())), 0, 0, color, false);
                }
                graphics.getMatrices().popMatrix();
            }
            cursor += seg.getWidth();
        }
        if (tag.getHealth() != null) {
            float hpX = tag.getBoxX() + (float)tag.getPadX() + tag.getBadgeWidth() + tag.getNameWidth() + (float)tag.getHealthGap();
            Fonts.HEART.msdf(HEART_GLYPH, hpX, textY + (float)tag.getNudge(), tag.getHeartSize(), ColorEngine.multAlpha(-42386, tag.getAlpha()));
            String string = tag.getHealth();
            Intrinsics.checkNotNull((Object)string);
            Fonts.MEDIUM.msdf(string, hpX += tag.getHeartWidth() + (float)tag.getHeartGap(), textY, tag.getHealthSize(), ColorEngine.multAlpha(-5196096, tag.getAlpha()));
        }
    }

    private final List<Seg> buildSegments(List<TextRun> runs, int nameSize) {
        List segs = new ArrayList();
        MsdfFont msdf = MsdfFonts.get(Fonts.SEMIBOLD);
        float nativeScale = Math.max(1.0f, (float)Math.round((float)nameSize / 7.5f));
        for (TextRun run : runs) {
            if (run.getIconFont() != null) {
                float w = run.getIconFont().msdfWidth(run.getText(), nameSize);
                segs.add(new Seg(0, run.getText(), run.getIconFont(), null, run.getColor(), w, 1.0f));
                continue;
            }
            String text = run.getText();
            int i = 0;
            while (i < text.length()) {
                int start = i;
                boolean cov = NameTags.Companion.covered(run.getFont(), msdf, text.codePointAt(i));
                while (i < text.length() && NameTags.Companion.covered(run.getFont(), msdf, text.codePointAt(i)) == cov) {
                    i += Character.charCount(text.codePointAt(i));
                }
                String part = text.substring(start, i);
                boolean bl = cov ? segs.add(new Seg(0, part, Fonts.SEMIBOLD, null, run.getColor(), Fonts.SEMIBOLD.msdfWidth(part, nameSize), 1.0f)) : segs.add(new Seg(1, part, null, run.getFont(), run.getColor(), (float)this.nativeWidth(part, run.getFont()) * nativeScale, nativeScale));
            }
        }
        return segs;
    }

    private final int nativeWidth(String seg, StyleSpriteSource nativeFont) {
        TextRenderer textRenderer2 = this.mc.textRenderer;
        Intrinsics.checkNotNullExpressionValue((Object)textRenderer2, (String)"font");
        TextRenderer font = textRenderer2;
        if (nativeFont == null || Intrinsics.areEqual((Object)nativeFont, (Object)DEFAULT_FONT)) {
            return font.getWidth(seg);
        }
        return font.getWidth((StringVisitable)Text.literal((String)seg).setStyle(Style.EMPTY.withFont(nativeFont)));
    }

    private static final int onWorldRender$lambda$0(Tag a, Tag b) {
        return Float.compare(b.getDepth(), a.getDepth());
    }

    private static final boolean pruneCache$lambda$0(long $now, VisibilityCache cache2) {
        Intrinsics.checkNotNullParameter((Object)cache2, (String)"cache");
        return $now - cache2.getTouchedAtMs() > 1000L;
    }

    private static final boolean pruneCache$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    @JvmStatic
    @Nullable
    public static final NameTags getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean hidesVanillaNameTag(@Nullable PlayerEntity player) {
        return Companion.hidesVanillaNameTag(player);
    }

    @JvmStatic
    public static final boolean hidesNameTagFor(@Nullable Entity entity) {
        return Companion.hidesNameTagFor(entity);
    }

    @JvmStatic
    @NotNull
    public static final Text resolveDisplayName(@NotNull PlayerEntity player) {
        return Companion.resolveDisplayName(player);
    }

    @JvmStatic
    @NotNull
    public static final Text resolveDisplayName(@NotNull PlayerEntity player, boolean reuseFrameScan) {
        return Companion.resolveDisplayName(player, reuseFrameScan);
    }

    @JvmStatic
    public static final boolean isStylized(@Nullable String raw) {
        return Companion.isStylized(raw);
    }

    @JvmStatic
    @NotNull
    public static final String displayGlyph(int codePoint, boolean stylized) {
        return Companion.displayGlyph(codePoint, stylized);
    }

    @JvmStatic
    @NotNull
    public static final String displayGlyphCovered(int codePoint, boolean stylized) {
        return Companion.displayGlyphCovered(codePoint, stylized);
    }

    @JvmStatic
    public static final int copyUnderHandBounds(@Nullable float[] output) {
        return Companion.copyUnderHandBounds(output);
    }

    static {
        StyleSpriteSource.Font font2 = StyleSpriteSource.DEFAULT;
        Intrinsics.checkNotNullExpressionValue((Object)font2, (String)"DEFAULT");
        DEFAULT_FONT = font2;
        EMPTY_TOKEN = Pattern.compile("[<\\[({\u2039\u3008«\uff1c][\\s]*empty[\\s]*[>\\])}\u203a\u3009»\uff1e]", 2);
        hideMemo = new Int2BooleanOpenHashMap();
        hologramEntities = new ArrayList();
        hologramLines = new ArrayList();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000f\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0003J\u000f\u0010\u0016\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u001b\u0010\u0018\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019J#\u0010\u0018\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0019J\u001d\u0010\u001f\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J#\u0010$\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b&\u0010%J\u0019\u0010'\u001a\u0004\u0018\u00010\u00172\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b'\u0010\u0019J\u001b\u0010)\u001a\u0004\u0018\u00010\u00172\b\u0010(\u001a\u0004\u0018\u00010\u0017H\u0002\u00a2\u0006\u0004\b)\u0010*J\u0019\u0010+\u001a\u00020\n2\b\u0010(\u001a\u0004\u0018\u00010\u0017H\u0002\u00a2\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b.\u0010/J\u0019\u0010+\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u00010\u001dH\u0002\u00a2\u0006\u0004\b+\u0010 J!\u00102\u001a\u00020\u00172\b\u00101\u001a\u0004\u0018\u00010\u00172\u0006\u00100\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b2\u00103J\u001d\u00106\u001a\u00020!2\b\u00105\u001a\u0004\u0018\u000104H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b6\u00107J\u0017\u0010:\u001a\u00020\u001d2\u0006\u00109\u001a\u000208H\u0002\u00a2\u0006\u0004\b:\u0010;J\u0017\u0010=\u001a\u00020!2\u0006\u0010<\u001a\u000208H\u0002\u00a2\u0006\u0004\b=\u0010>J'\u0010@\u001a\u0002082\u0006\u0010?\u001a\u0002082\u0006\u0010<\u001a\u0002082\u0006\u0010-\u001a\u000208H\u0002\u00a2\u0006\u0004\b@\u0010AJ'\u0010B\u001a\u0002082\u0006\u0010?\u001a\u0002082\u0006\u0010<\u001a\u0002082\u0006\u0010-\u001a\u000208H\u0002\u00a2\u0006\u0004\bB\u0010AJ\u0017\u0010E\u001a\u00020\n2\u0006\u0010D\u001a\u00020CH\u0002\u00a2\u0006\u0004\bE\u0010FJ+\u0010K\u001a\u00020\n2\b\u0010H\u001a\u0004\u0018\u00010G2\b\u0010J\u001a\u0004\u0018\u00010I2\u0006\u0010\"\u001a\u00020!H\u0002\u00a2\u0006\u0004\bK\u0010LJ-\u0010N\u001a\u0004\u0018\u00010\u001d2\b\u0010J\u001a\u0004\u0018\u00010I2\b\u0010M\u001a\u0004\u0018\u00010G2\u0006\u0010\"\u001a\u00020!H\u0002\u00a2\u0006\u0004\bN\u0010OJ\u0017\u0010Q\u001a\u00020!2\u0006\u0010P\u001a\u000208H\u0002\u00a2\u0006\u0004\bQ\u0010>J\u0017\u0010R\u001a\u00020!2\u0006\u0010P\u001a\u000208H\u0002\u00a2\u0006\u0004\bR\u0010>J%\u0010V\u001a\b\u0012\u0004\u0012\u00020U0T2\u0006\u0010S\u001a\u00020\u00172\u0006\u00100\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bV\u0010WJ\u001d\u0010Z\u001a\u00020\u00142\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020U0XH\u0002\u00a2\u0006\u0004\bZ\u0010[J\u0017\u0010]\u001a\u00020\n2\u0006\u0010\\\u001a\u00020UH\u0002\u00a2\u0006\u0004\b]\u0010^J\u0017\u0010_\u001a\u00020\n2\u0006\u0010\\\u001a\u00020UH\u0002\u00a2\u0006\u0004\b_\u0010^J\u0017\u0010`\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b`\u0010/J\u0017\u0010a\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\ba\u0010/R\u0014\u0010b\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010d\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010f\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bf\u0010eR\u0014\u0010g\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0014\u0010i\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bi\u0010eR\u0014\u0010j\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bj\u0010kR\u0014\u0010l\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bl\u0010kR\u0014\u0010m\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bm\u0010kR\u0014\u0010n\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bn\u0010kR\u0014\u0010o\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bo\u0010kR\u0014\u0010p\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bp\u0010hR\u0014\u0010q\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bq\u0010kR\u0014\u0010s\u001a\u00020r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010tR\u0014\u0010u\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bu\u0010kR\u0014\u0010v\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bv\u0010kR\u0014\u0010w\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bw\u0010kR\u0014\u0010x\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bx\u0010kR\u0014\u0010y\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\by\u0010hR\u0014\u0010z\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bz\u0010hR\u0014\u0010{\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b{\u0010hR\u0014\u0010|\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b|\u0010hR\u0014\u0010}\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010hR\u0015\u0010\u007f\u001a\u00020~8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0018\u0010\u0082\u0001\u001a\u00030\u0081\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0018\u0010\u0084\u0001\u001a\u00030\u0081\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0083\u0001R\u0016\u0010\u0085\u0001\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010kR\u0016\u0010\u0086\u0001\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010kR\u0018\u0010\u0087\u0001\u001a\u00030\u0081\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0083\u0001R\u0018\u0010\u0088\u0001\u001a\u00030\u0081\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0083\u0001R\u0018\u0010\u0089\u0001\u001a\u00030\u0081\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u0083\u0001R\"\u0010\u008c\u0001\u001a\r \u008b\u0001*\u0005\u0018\u00010\u008a\u00010\u008a\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R!\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0003\b\u008e\u0001\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0018\u0010\u0092\u0001\u001a\u00030\u0091\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R\u001a\u0010\u0094\u0001\u001a\u00030\u0081\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0083\u0001R\u001e\u0010\u0096\u0001\u001a\t\u0012\u0004\u0012\u00020\r0\u0095\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0097\u0001R\u001e\u0010\u0098\u0001\u001a\t\u0012\u0004\u0012\u00020\u00170\u0095\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0097\u0001R\u0018\u0010\u0099\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0099\u0001\u0010cR\u0018\u0010\u009a\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009a\u0001\u0010c\u00a8\u0006\u009b\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NameTags.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/NameTags;", "Lnet/minecraft/PlayerEntity;", "player", "", "hidesVanillaNameTag", "(Lnet/minecraft/PlayerEntity;)Z", "Lnet/minecraft/Entity;", "entity", "hidesNameTagFor", "(Lnet/minecraft/Entity;)Z", "module", "computeHidesNameTagFor", "(Lrtx/kimiko/api/modules/impl/Visuals/NameTags;Lnet/minecraft/Entity;)Z", "", "resetHologramFrame", "ensureHologramCandidates", "Lnet/minecraft/Text;", "resolveDisplayName", "(Lnet/minecraft/PlayerEntity;)Lnet/minecraft/Text;", "reuseFrameScan", "(Lnet/minecraft/PlayerEntity;Z)Lnet/minecraft/Text;", "resolveDisplayName0", "", "raw", "isStylized", "(Ljava/lang/String;)Z", "", "codePoint", "stylized", "displayGlyph", "(IZ)Ljava/lang/String;", "displayGlyphCovered", "findTopHologramLine", "component", "firstReadableLine", "(Lnet/minecraft/Text;)Lnet/minecraft/Text;", "isMissingName", "(Lnet/minecraft/Text;)Z", "s", "stripEmptyTokens", "(Ljava/lang/String;)Ljava/lang/String;", "name", "prefix", "buildPlayerName", "(Lnet/minecraft/Text;Ljava/lang/String;)Lnet/minecraft/Text;", "", "output", "copyUnderHandBounds", "([F)I", "", "hp", "formatHealth", "(F)Ljava/lang/String;", "v", "roundSize", "(F)I", "origin", "sx", "(FFF)F", "sy", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Tag;", "tag", "isSettled", "(Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Tag;)Z", "Lnet/minecraft/StyleSpriteSource;", "nativeFont", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "msdf", "covered", "(Lnet/minecraft/StyleSpriteSource;Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;I)Z", "font", "normalizeCodePoint", "(Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;Lnet/minecraft/StyleSpriteSource;I)Ljava/lang/String;", "alpha", "badgeLeft", "badgeRight", "displayName", "", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags$TextRun;", "parseRuns", "(Lnet/minecraft/Text;Ljava/lang/String;)Ljava/util/List;", "", "built", "trimRuns", "(Ljava/util/List;)V", "run", "edgeBlank", "(Lrtx/kimiko/api/modules/impl/Visuals/NameTags$TextRun;)Z", "plainText", "stripLeading", "stripTrailing", "UNDER_HAND", "Z", "HEART_GLYPH", "Ljava/lang/String;", "LIGHTNING_GLYPH", "LIGHTNING_CP", "I", "LOGO_GLYPH", "BADGE_GAP", "F", "NAME_SIZE", "HEALTH_SIZE", "HEART_SIZE", "HEART_GAP", "HEART_COLOR", "NATIVE_CAP_H", "Lnet/minecraft/StyleSpriteSource$Font;", "DEFAULT_FONT", "Lnet/minecraft/StyleSpriteSource$Font;", "PAD_X", "PAD_Y", "HEALTH_GAP", "RADIUS", "NAME_COLOR", "HEALTH_COLOR", "BACKGROUND_COLOR", "BADGE_LEFT_COLOR", "BADGE_RIGHT_COLOR", "", "MAX_DISTANCE_SQR", "D", "", "VISIBILITY_REFRESH_MS", "J", "VISIBILITY_PRUNE_MS", "HIDDEN_SCALE", "ANIM_MS", "LAYOUT_TTL_MS", "HIDE_MEMO_MS", "NAME_RESOLVE_TTL_MS", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "EMPTY_TOKEN", "Ljava/util/regex/Pattern;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags;", "Lit/unimi/dsi/fastutil/ints/Int2BooleanOpenHashMap;", "hideMemo", "Lit/unimi/dsi/fastutil/ints/Int2BooleanOpenHashMap;", "hideMemoAtMs", "Ljava/util/ArrayList;", "hologramEntities", "Ljava/util/ArrayList;", "hologramLines", "hologramReady", "hologramReuse", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final NameTags getInstance() {
            NameTags module = ModuleManager.Companion.get().get(NameTags.class);
            NameTags nameTags = module;
            if (nameTags == null) {
                nameTags = INSTANCE;
            }
            return nameTags;
        }

        @JvmStatic
        public final boolean hidesVanillaNameTag(@Nullable PlayerEntity player) {
            NameTags module = this.getInstance();
            if (module == null || !module.isEnabled() || player == null) {
                return false;
            }
            ClientPlayerEntity clientPlayerEntity2 = ((NameTags)module).mc.player;
            if (clientPlayerEntity2 == null) {
                return false;
            }
            ClientPlayerEntity local = clientPlayerEntity2;
            if (!module.isValid(player)) {
                return false;
            }
            return local.squaredDistanceTo((Entity)player) <= 4096.0;
        }

        @JvmStatic
        public final boolean hidesNameTagFor(@Nullable Entity entity) {
            if (entity instanceof PlayerEntity) {
                return this.hidesVanillaNameTag((PlayerEntity)entity);
            }
            NameTags module = this.getInstance();
            if (module == null || !module.isEnabled() || entity == null) {
                return false;
            }
            long stamp = System.currentTimeMillis();
            if (stamp - hideMemoAtMs > 100L) {
                hideMemo.clear();
                hideMemoAtMs = stamp;
            }
            int id = entity.getId();
            if (hideMemo.containsKey(id)) {
                return hideMemo.get(id);
            }
            boolean computed = this.computeHidesNameTagFor(module, entity);
            hideMemo.put(id, computed);
            return computed;
        }

        private final boolean computeHidesNameTagFor(NameTags module, Entity entity) {
            ClientPlayerEntity clientPlayerEntity2 = ((NameTags)module).mc.player;
            if (clientPlayerEntity2 == null) {
                return false;
            }
            ClientPlayerEntity local = clientPlayerEntity2;
            ClientWorld clientWorld3 = ((NameTags)module).mc.world;
            if (clientWorld3 == null) {
                return false;
            }
            ClientWorld level = clientWorld3;
            double ex = entity.getX();
            double ey = entity.getY();
            double ez = entity.getZ();
            for (AbstractClientPlayerEntity player : level.getPlayers()) {
                double headY;
                double dz;
                double dx;
                if (!module.isValid((PlayerEntity)player) || local.squaredDistanceTo((Entity)player) > 4096.0 || (dx = ex - player.getX()) * dx + (dz = ez - player.getZ()) * dz > 2.25 || !(ey >= (headY = player.getY() + (double)player.getHeight()) - 1.0) || !(ey <= headY + 7.0)) continue;
                return true;
            }
            return false;
        }

        private final void resetHologramFrame() {
            hologramReady = false;
            hologramEntities.clear();
            hologramLines.clear();
        }

        private final void ensureHologramCandidates() {
            if (hologramReuse && hologramReady) {
                return;
            }
            hologramEntities.clear();
            hologramLines.clear();
            ClientWorld clientWorld3 = MinecraftClient.getInstance().world;
            if (clientWorld3 == null) {
                return;
            }
            ClientWorld level = clientWorld3;
            for (Entity entity : level.getEntities()) {
                if (entity instanceof PlayerEntity) continue;
                Text candidate = entity instanceof DisplayEntity.TextDisplayEntity ? ((DisplayEntity.TextDisplayEntity)entity).getText() : entity.getCustomName();
                Text line = this.firstReadableLine(candidate);
                if (line == null) continue;
                hologramEntities.add(entity);
                hologramLines.add(line);
            }
            hologramReady = hologramReuse;
        }

        @JvmStatic
        @NotNull
        public final Text resolveDisplayName(@NotNull PlayerEntity player) {
            Intrinsics.checkNotNullParameter((Object)player, (String)"player");
            return this.resolveDisplayName(player, false);
        }

        @JvmStatic
        @NotNull
        public final Text resolveDisplayName(@NotNull PlayerEntity player, boolean reuseFrameScan) {
            Intrinsics.checkNotNullParameter((Object)player, (String)"player");
            hologramReuse = reuseFrameScan;
            Text base = this.resolveDisplayName0(player);
            if (Intrinsics.areEqual((Object)player, (Object)MinecraftClient.getInstance().player)) {
                return StreamerMode.Companion.applySelfRank(base);
            }
            return base;
        }

        private final Text resolveDisplayName0(PlayerEntity player) {
            Team team;
            Team team2 = team = player.getScoreboardTeam();
            Text prefix = team2 != null ? team2.getPrefix() : null;
            boolean prefixOk = prefix != null && !this.isMissingName(prefix.getString());
            String string = player.getName().getString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
            String name = string;
            if (!this.isMissingName(name)) {
                return this.buildPlayerName((Text)(prefixOk ? prefix : null), name);
            }
            if (prefixOk) {
                Text text2 = prefix;
                Intrinsics.checkNotNull((Object)text2);
                MutableText mutableText2 = text2.copy();
                Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"copy(...)");
                return (Text)mutableText2;
            }
            Text hologramName = this.findTopHologramLine(player);
            if (hologramName != null) {
                return hologramName;
            }
            Text customName = player.getCustomName();
            if (!this.isMissingName(customName)) {
                Text text3 = customName;
                Intrinsics.checkNotNull((Object)text3);
                MutableText mutableText3 = text3.copy();
                Intrinsics.checkNotNullExpressionValue((Object)mutableText3, (String)"copy(...)");
                return (Text)mutableText3;
            }
            String profileName = player.getGameProfile().name();
            if (!this.isMissingName(profileName)) {
                Intrinsics.checkNotNull((Object)profileName);
                return this.buildPlayerName(null, profileName);
            }
            String string2 = player.getNameForScoreboard();
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getScoreboardName(...)");
            String scoreboardName = string2;
            if (!this.isMissingName(scoreboardName)) {
                return this.buildPlayerName(null, scoreboardName);
            }
            MutableText mutableText4 = Text.empty();
            Intrinsics.checkNotNullExpressionValue((Object)mutableText4, (String)"empty(...)");
            return (Text)mutableText4;
        }

        @JvmStatic
        public final boolean isStylized(@Nullable String raw) {
            return raw != null && raw.codePoints().anyMatch(GlyphNormalizer::isSmallCap);
        }

        @JvmStatic
        @NotNull
        public final String displayGlyph(int codePoint, boolean stylized) {
            String repl = this.normalizeCodePoint(MsdfFonts.get(Fonts.SEMIBOLD), (StyleSpriteSource)DEFAULT_FONT, codePoint);
            if (repl != null) {
                return repl;
            }
            char[] cArray = Character.toChars(stylized ? Character.toUpperCase(codePoint) : codePoint);
            Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toChars(...)");
            char[] cArray2 = cArray;
            return new String(cArray2);
        }

        @JvmStatic
        @NotNull
        public final String displayGlyphCovered(int codePoint, boolean stylized) {
            int cp;
            String glyph = this.displayGlyph(codePoint, stylized);
            MsdfFont msdfFont = MsdfFonts.get(Fonts.SEMIBOLD);
            if (msdfFont == null) {
                return glyph;
            }
            MsdfFont msdf = msdfFont;
            for (int i = 0; i < glyph.length(); i += Character.charCount(cp)) {
                cp = glyph.codePointAt(i);
                if (Character.isWhitespace(cp) || msdf.hasGlyph(cp)) continue;
                return "";
            }
            return glyph;
        }

        private final Text findTopHologramLine(PlayerEntity player) {
            this.ensureHologramCandidates();
            Text result = null;
            double highestY = Double.NEGATIVE_INFINITY;
            double headY = player.getY() + (double)player.getHeight();
            int n = ((Collection)hologramEntities).size();
            for (int index = 0; index < n; ++index) {
                Object e = hologramEntities.get(index);
                Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
                Entity entity = (Entity)e;
                if (Intrinsics.areEqual((Object)entity, (Object)player)) continue;
                double dx = entity.getX() - player.getX();
                double dz = entity.getZ() - player.getZ();
                double y = entity.getY();
                if (dx * dx + dz * dz > 2.25 || y < headY - 1.0 || y > headY + 7.0 || !(y > highestY)) continue;
                result = (Text)hologramLines.get(index);
                highestY = y;
            }
            return result;
        }

        private final Text firstReadableLine(Text component) {
            if (component == null) {
                return null;
            }
            List<MutableText> lines = new ArrayList<>();
            MutableText[] class_5250Array = new MutableText[]{Text.empty()};
            MutableText[] current = class_5250Array;
            component.visit((arg_0, arg_1) -> Companion.firstReadableLine$lambda$0(current, lines, arg_0, arg_1), Style.EMPTY);
            MutableText mutableText2 = current[0];
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"get(...)");
            lines.add(mutableText2);
            for (MutableText line : lines) {
                if (this.isMissingName((Text)line)) continue;
                return (Text)line;
            }
            return null;
        }

        private final boolean isMissingName(Text component) {
            return component == null || this.isMissingName(component.getString());
        }

        private final String stripEmptyTokens(String s) {
            String string = EMPTY_TOKEN.matcher(s).replaceAll("");
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"replaceAll(...)");
            return ((Object)StringsKt.trim((CharSequence)string)).toString();
        }

        private final boolean isMissingName(String name) {
            if (name == null) {
                return true;
            }
            String s = name.replaceAll("(?i)\u00a7[0-9a-fk-or]", "");
            s = this.stripEmptyTokens(s.trim());
            if (s.isEmpty()) {
                return true;
            }
            String core = s.replaceAll("^[\\p{P}\\p{S}\\s]+", "").replaceAll("[\\p{P}\\p{S}\\s]+$", "");
            return core.isEmpty() || StringsKt.equals(core, "empty", true);
        }

        private final Text buildPlayerName(Text prefix, String name) {
            MutableText mutableText2 = Text.empty();
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"empty(...)");
            MutableText result = mutableText2;
            if (prefix != null) {
                String string = prefix.getString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
                if (!(((CharSequence)string).length() == 0)) {
                    result.append((Text)prefix.copy());
                    String string2 = prefix.getString();
                    Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getString(...)");
                    if (!String.valueOf(string2).endsWith(" ")) {
                        result.append((Text)Text.literal((String)" "));
                    }
                }
            }
            result.append((Text)Text.literal((String)this.stripEmptyTokens(name)).formatted(Formatting.WHITE));
            return (Text)result;
        }

        @JvmStatic
        public final int copyUnderHandBounds(@Nullable float[] output) {
            NameTags module = this.getInstance();
            if (output == null || output.length < 4 || module == null || !module.isEnabled() || !module.underHandDrawn) {
                return 0;
            }
            int count = 0;
            for (Tag tag : module.tags) {
                if (tag.getAlpha() <= 0.01f || count * 4 + 3 >= output.length) continue;
                float scale = this.isSettled(tag) ? 1.0f : tag.getScale();
                float centerX = tag.getBoxX() + tag.getBoxWidth() * 0.5f;
                float centerY = tag.getBoxY() + tag.getBoxHeight() * 0.5f;
                int offset = count * 4;
                output[offset] = this.sx(centerX, tag.getBoxX(), scale) - 2.0f;
                output[offset + 1] = this.sy(centerY, tag.getBoxY(), scale) - 2.0f;
                output[offset + 2] = tag.getBoxWidth() * scale + 4.0f;
                output[offset + 3] = tag.getBoxHeight() * scale + 4.0f;
                ++count;
            }
            return count;
        }

        private final String formatHealth(float hp) {
            String string = Integer.toString(MathHelper.ceil((float)hp));
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            return string;
        }

        private final int roundSize(float v) {
            return Math.max(1, Math.round(v));
        }

        private final float sx(float origin, float v, float s) {
            return origin + (v - origin) * s;
        }

        private final float sy(float origin, float v, float s) {
            return origin + (v - origin) * s;
        }

        private final boolean isSettled(Tag tag) {
            return Math.abs(tag.getScale() - 1.0f) < 0.001f;
        }

        private final boolean covered(StyleSpriteSource nativeFont, MsdfFont msdf, int codePoint) {
            if (nativeFont != null && !Intrinsics.areEqual((Object)nativeFont, (Object)DEFAULT_FONT)) {
                return false;
            }
            if (codePoint == 32 || Character.isWhitespace(codePoint)) {
                return true;
            }
            return msdf != null && msdf.hasGlyph(codePoint);
        }

        private final String normalizeCodePoint(MsdfFont msdf, StyleSpriteSource font, int codePoint) {
            int cp;
            if (msdf == null || font != null && !Intrinsics.areEqual((Object)font, (Object)DEFAULT_FONT) || msdf.hasGlyph(codePoint)) {
                return null;
            }
            String string = GlyphNormalizer.normalize(codePoint);
            if (string == null) {
                return null;
            }
            String repl = string;
            for (int i = 0; i < repl.length(); i += Character.charCount(cp)) {
                cp = repl.codePointAt(i);
                if (Character.isWhitespace(cp) || msdf.hasGlyph(cp)) continue;
                return null;
            }
            return repl;
        }

        private final int badgeLeft(float alpha) {
            return ColorEngine.multAlpha(-7695373, alpha);
        }

        private final int badgeRight(float alpha) {
            return ColorEngine.multAlpha(-4938241, alpha);
        }

        private final List<TextRun> parseRuns(Text displayName, String name) {
            List built = new ArrayList();
            MsdfFont nameMsdf = MsdfFonts.get(Fonts.SEMIBOLD);
            boolean stylized = name.codePoints().anyMatch(GlyphNormalizer::isSmallCap);
            StringBuilder segment = new StringBuilder();
            int[] nArray = new int[]{-1};
            int[] segColor = nArray;
            StyleSpriteSource.Font[] class_11721Array = new StyleSpriteSource.Font[]{DEFAULT_FONT};
            StyleSpriteSource.Font[] segFont = class_11721Array;
            displayName.visit((arg_0, arg_1) -> Companion.parseRuns$lambda$1(segment, built, segColor, segFont, nameMsdf, stylized, arg_0, arg_1), Style.EMPTY);
            if (((CharSequence)segment).length() > 0) {
                String string = segment.toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                built.add(new TextRun(string, segColor[0], (StyleSpriteSource)segFont[0], null, 8, null));
            }
            this.trimRuns(built);
            if (built.isEmpty()) {
                built.add(new TextRun(name, -1, (StyleSpriteSource)DEFAULT_FONT, null, 8, null));
            }
            return built;
        }

        private final void trimRuns(List<TextRun> built) {
            String trailing;
            int lastIdx;
            TextRun last;
            String leading;
            while (!((Collection)built).isEmpty() && this.edgeBlank(built.get(0))) {
                built.remove(0);
            }
            while (!((Collection)built).isEmpty() && this.edgeBlank(built.get(built.size() - 1))) {
                built.remove(built.size() - 1);
            }
            if (built.isEmpty()) {
                return;
            }
            TextRun first = built.get(0);
            if (this.plainText(first) && !Intrinsics.areEqual((Object)(leading = this.stripLeading(first.getText())), (Object)first.getText())) {
                built.set(0, new TextRun(leading, first.getColor(), first.getFont(), first.getIconFont()));
            }
            if (this.plainText(last = built.get(lastIdx = built.size() - 1)) && !Intrinsics.areEqual((Object)(trailing = this.stripTrailing(last.getText())), (Object)last.getText())) {
                built.set(lastIdx, new TextRun(trailing, last.getColor(), last.getFont(), last.getIconFont()));
            }
            while (!((Collection)built).isEmpty() && this.edgeBlank(built.get(0))) {
                built.remove(0);
            }
            while (!((Collection)built).isEmpty() && this.edgeBlank(built.get(built.size() - 1))) {
                built.remove(built.size() - 1);
            }
        }

        private final boolean edgeBlank(TextRun run) {
            if (run.getIconFont() != null) {
                return false;
            }
            if (run.getFont() != null && !Intrinsics.areEqual((Object)run.getFont(), (Object)DEFAULT_FONT)) {
                return ((CharSequence)run.getText()).length() == 0;
            }
            return StringsKt.isBlank((CharSequence)run.getText());
        }

        private final boolean plainText(TextRun run) {
            return run.getIconFont() == null && (run.getFont() == null || Intrinsics.areEqual((Object)run.getFont(), (Object)DEFAULT_FONT));
        }

        private final String stripLeading(String s) {
            int i;
            for (i = 0; i < s.length() && Character.isWhitespace(s.charAt(i)); ++i) {
            }
            String string = s.substring(i);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            return string;
        }

        private final String stripTrailing(String s) {
            int i;
            for (i = s.length(); i > 0 && Character.isWhitespace(s.charAt(i - 1)); --i) {
            }
            String string = s.substring(0, i);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            return string;
        }

        private static final boolean isStylized$lambda$0(int it) {
            return GlyphNormalizer.isSmallCap(it);
        }

        private static final Optional firstReadableLine$lambda$0(MutableText[] $current, List $lines, Style style, String text) {
            Intrinsics.checkNotNullParameter((Object)style, (String)"style");
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            int start = 0;
            int n = text.length();
            for (int i = 0; i < n; ++i) {
                if (text.charAt(i) != '\n') continue;
                String part = text.substring(start, i);
                if (!(((CharSequence)part).length() == 0)) {
                    $current[0].append((Text)Text.literal((String)part).setStyle(style));
                }
                MutableText mutableText2 = $current[0];
                Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"get(...)");
                $lines.add(mutableText2);
                $current[0] = Text.empty();
                start = i + 1;
            }
            String string = text.substring(start);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            String tail = string;
            if (!(((CharSequence)tail).length() == 0)) {
                $current[0].append((Text)Text.literal((String)tail).setStyle(style));
            }
            return Optional.empty();
        }

        private static final boolean parseRuns$lambda$0(int it) {
            return GlyphNormalizer.isSmallCap(it);
        }

        private static final boolean parseRuns$lambda$1$0(StringBuilder $segment, List $built, int[] $segColor, StyleSpriteSource.Font[] $segFont, MsdfFont $nameMsdf, boolean $stylized, int index, Style charStyle, int codePoint) {
            int n;
            Intrinsics.checkNotNullParameter((Object)charStyle, (String)"charStyle");
            if (charStyle.getColor() != null) {
                TextColor textColor2 = charStyle.getColor();
                Intrinsics.checkNotNull((Object)textColor2);
                n = 0xFF000000 | textColor2.getRgb();
            } else {
                n = -1;
            }
            int color = n;
            StyleSpriteSource.Font font = DEFAULT_FONT;
            if (codePoint == 9889) {
                if (((CharSequence)$segment).length() > 0) {
                    String string = $segment.toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                    $built.add(new TextRun(string, $segColor[0], (StyleSpriteSource)$segFont[0], null, 8, null));
                    $segment.setLength(0);
                }
                $built.add(new TextRun(NameTags.LIGHTNING_GLYPH, color, (StyleSpriteSource)DEFAULT_FONT, Fonts.KIMIKO));
                return true;
            }
            if ((color != $segColor[0] || !Intrinsics.areEqual((Object)font, (Object)$segFont[0])) && ((CharSequence)$segment).length() > 0) {
                String string = $segment.toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                $built.add(new TextRun(string, $segColor[0], (StyleSpriteSource)$segFont[0], null, 8, null));
                $segment.setLength(0);
            }
            $segColor[0] = color;
            $segFont[0] = font;
            String repl = Companion.normalizeCodePoint($nameMsdf, (StyleSpriteSource)font, codePoint);
            StringBuilder stringBuilder = repl != null ? $segment.append(repl) : ($stylized ? $segment.appendCodePoint(Character.toUpperCase(codePoint)) : $segment.appendCodePoint(codePoint));
            return true;
        }

        private static final Optional parseRuns$lambda$1(StringBuilder $segment, List $built, int[] $segColor, StyleSpriteSource.Font[] $segFont, MsdfFont $nameMsdf, boolean $stylized, Style style, String text) {
            Intrinsics.checkNotNullParameter((Object)style, (String)"style");
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            TextVisitFactory.visitFormatted((String)text, (Style)style, (arg_0, arg_1, arg_2) -> Companion.parseRuns$lambda$1$0($segment, $built, $segColor, $segFont, $nameMsdf, $stylized, arg_0, arg_1, arg_2));
            return Optional.empty();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0017\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!BC\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\n\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0010\u001a\u0004\b\u001c\u0010\u0012R\u0017\u0010\f\u001a\u00020\u000b8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\r\u001a\u00020\u000b8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u001d\u001a\u0004\b \u0010\u001f\u00a8\u0006\""}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Seg;", "", "", "kind", "", "text", "Lrtx/kimiko/utils/render/fonts/Fonts;", "msdfFont", "Lnet/minecraft/StyleSpriteSource;", "nativeFont", "color", "", "width", "nativeScale", "<init>", "(ILjava/lang/String;Lrtx/kimiko/utils/render/fonts/Fonts;Lnet/minecraft/StyleSpriteSource;IFF)V", "I", "getKind", "()I", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "Lrtx/kimiko/utils/render/fonts/Fonts;", "getMsdfFont", "()Lrtx/kimiko/utils/render/fonts/Fonts;", "Lnet/minecraft/StyleSpriteSource;", "getNativeFont", "()Lnet/minecraft/StyleSpriteSource;", "getColor", "F", "getWidth", "()F", "getNativeScale", "Companion", "rtx.kimiko:kimiko"})
    private static final class Seg {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final int kind;
        @NotNull
        private final String text;
        @Nullable
        private final Fonts msdfFont;
        @Nullable
        private final StyleSpriteSource nativeFont;
        private final int color;
        private final float width;
        private final float nativeScale;
        public static final int MSDF = 0;
        public static final int NATIVE = 1;

        public Seg(int kind, @NotNull String text, @Nullable Fonts msdfFont, @Nullable StyleSpriteSource nativeFont, int color, float width, float nativeScale) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            this.kind = kind;
            this.text = text;
            this.msdfFont = msdfFont;
            this.nativeFont = nativeFont;
            this.color = color;
            this.width = width;
            this.nativeScale = nativeScale;
        }

        public final int getKind() {
            return this.kind;
        }

        @NotNull
        public final String getText() {
            return this.text;
        }

        @Nullable
        public final Fonts getMsdfFont() {
            return this.msdfFont;
        }

        @Nullable
        public final StyleSpriteSource getNativeFont() {
            return this.nativeFont;
        }

        public final int getColor() {
            return this.color;
        }

        public final float getWidth() {
            return this.width;
        }

        public final float getNativeScale() {
            return this.nativeScale;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Seg.Companion;", "", "<init>", "()V", "", "MSDF", "I", "NATIVE", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b3\n\u0002\u0010\u000b\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R*\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\"\u0010\u001d\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u0012R\"\u0010!\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010'\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010\"\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\"\u0010*\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b*\u0010\"\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\"\u0010-\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010\"\u001a\u0004\b.\u0010$\"\u0004\b/\u0010&R\"\u00100\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u0010\"\u001a\u0004\b1\u0010$\"\u0004\b2\u0010&R\"\u00103\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u0010\"\u001a\u0004\b4\u0010$\"\u0004\b5\u0010&R\"\u00106\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u0010\"\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R\"\u00109\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010\"\u001a\u0004\b:\u0010$\"\u0004\b;\u0010&R\"\u0010<\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010\"\u001a\u0004\b=\u0010$\"\u0004\b>\u0010&R\"\u0010?\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b?\u0010\u000e\u001a\u0004\b@\u0010\u0010\"\u0004\bA\u0010\u0012R\"\u0010B\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010\u000e\u001a\u0004\bC\u0010\u0010\"\u0004\bD\u0010\u0012R\"\u0010E\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bE\u0010\u000e\u001a\u0004\bF\u0010\u0010\"\u0004\bG\u0010\u0012R\"\u0010H\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bH\u0010\u000e\u001a\u0004\bI\u0010\u0010\"\u0004\bJ\u0010\u0012R\"\u0010K\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bK\u0010\u000e\u001a\u0004\bL\u0010\u0010\"\u0004\bM\u0010\u0012R\"\u0010N\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bN\u0010\u000e\u001a\u0004\bO\u0010\u0010\"\u0004\bP\u0010\u0012R\"\u0010Q\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bQ\u0010\u000e\u001a\u0004\bR\u0010\u0010\"\u0004\bS\u0010\u0012R\"\u0010U\u001a\u00020T8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\"\u0010[\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b[\u0010\u000e\u001a\u0004\b\\\u0010\u0010\"\u0004\b]\u0010\u0012\u00a8\u0006^"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Tag;", "", "<init>", "()V", "", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Seg;", "segs", "Ljava/util/List;", "getSegs", "()Ljava/util/List;", "setSegs", "(Ljava/util/List;)V", "", "nameWidth", "F", "getNameWidth", "()F", "setNameWidth", "(F)V", "", "health", "Ljava/lang/String;", "getHealth", "()Ljava/lang/String;", "setHealth", "(Ljava/lang/String;)V", "heartWidth", "getHeartWidth", "setHeartWidth", "healthNumberWidth", "getHealthNumberWidth", "setHealthNumberWidth", "", "nameSize", "I", "getNameSize", "()I", "setNameSize", "(I)V", "healthSize", "getHealthSize", "setHealthSize", "heartSize", "getHeartSize", "setHeartSize", "padX", "getPadX", "setPadX", "padY", "getPadY", "setPadY", "heartGap", "getHeartGap", "setHeartGap", "healthGap", "getHealthGap", "setHealthGap", "radius", "getRadius", "setRadius", "nudge", "getNudge", "setNudge", "alpha", "getAlpha", "setAlpha", "scale", "getScale", "setScale", "boxX", "getBoxX", "setBoxX", "boxY", "getBoxY", "setBoxY", "boxWidth", "getBoxWidth", "setBoxWidth", "boxHeight", "getBoxHeight", "setBoxHeight", "depth", "getDepth", "setDepth", "", "badge", "Z", "getBadge", "()Z", "setBadge", "(Z)V", "badgeWidth", "getBadgeWidth", "setBadgeWidth", "rtx.kimiko:kimiko"})
    private static final class Tag {
        @Nullable
        private List<Seg> segs;
        private float nameWidth;
        @Nullable
        private String health;
        private float heartWidth;
        private float healthNumberWidth;
        private int nameSize;
        private int healthSize;
        private int heartSize;
        private int padX;
        private int padY;
        private int heartGap;
        private int healthGap;
        private int radius;
        private int nudge;
        private float alpha;
        private float scale;
        private float boxX;
        private float boxY;
        private float boxWidth;
        private float boxHeight;
        private float depth;
        private boolean badge;
        private float badgeWidth;

        @Nullable
        public final List<Seg> getSegs() {
            return this.segs;
        }

        public final void setSegs(@Nullable List<Seg> list) {
            this.segs = list;
        }

        public final float getNameWidth() {
            return this.nameWidth;
        }

        public final void setNameWidth(float f) {
            this.nameWidth = f;
        }

        @Nullable
        public final String getHealth() {
            return this.health;
        }

        public final void setHealth(@Nullable String string) {
            this.health = string;
        }

        public final float getHeartWidth() {
            return this.heartWidth;
        }

        public final void setHeartWidth(float f) {
            this.heartWidth = f;
        }

        public final float getHealthNumberWidth() {
            return this.healthNumberWidth;
        }

        public final void setHealthNumberWidth(float f) {
            this.healthNumberWidth = f;
        }

        public final int getNameSize() {
            return this.nameSize;
        }

        public final void setNameSize(int n) {
            this.nameSize = n;
        }

        public final int getHealthSize() {
            return this.healthSize;
        }

        public final void setHealthSize(int n) {
            this.healthSize = n;
        }

        public final int getHeartSize() {
            return this.heartSize;
        }

        public final void setHeartSize(int n) {
            this.heartSize = n;
        }

        public final int getPadX() {
            return this.padX;
        }

        public final void setPadX(int n) {
            this.padX = n;
        }

        public final int getPadY() {
            return this.padY;
        }

        public final void setPadY(int n) {
            this.padY = n;
        }

        public final int getHeartGap() {
            return this.heartGap;
        }

        public final void setHeartGap(int n) {
            this.heartGap = n;
        }

        public final int getHealthGap() {
            return this.healthGap;
        }

        public final void setHealthGap(int n) {
            this.healthGap = n;
        }

        public final int getRadius() {
            return this.radius;
        }

        public final void setRadius(int n) {
            this.radius = n;
        }

        public final int getNudge() {
            return this.nudge;
        }

        public final void setNudge(int n) {
            this.nudge = n;
        }

        public final float getAlpha() {
            return this.alpha;
        }

        public final void setAlpha(float f) {
            this.alpha = f;
        }

        public final float getScale() {
            return this.scale;
        }

        public final void setScale(float f) {
            this.scale = f;
        }

        public final float getBoxX() {
            return this.boxX;
        }

        public final void setBoxX(float f) {
            this.boxX = f;
        }

        public final float getBoxY() {
            return this.boxY;
        }

        public final void setBoxY(float f) {
            this.boxY = f;
        }

        public final float getBoxWidth() {
            return this.boxWidth;
        }

        public final void setBoxWidth(float f) {
            this.boxWidth = f;
        }

        public final float getBoxHeight() {
            return this.boxHeight;
        }

        public final void setBoxHeight(float f) {
            this.boxHeight = f;
        }

        public final float getDepth() {
            return this.depth;
        }

        public final void setDepth(float f) {
            this.depth = f;
        }

        public final boolean getBadge() {
            return this.badge;
        }

        public final void setBadge(boolean bl) {
            this.badge = bl;
        }

        public final float getBadgeWidth() {
            return this.badgeWidth;
        }

        public final void setBadgeWidth(float f) {
            this.badgeWidth = f;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u0001B3\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u001a\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NameTags$TextRun;", "", "", "text", "", "color", "Lnet/minecraft/StyleSpriteSource;", "font", "Lrtx/kimiko/utils/render/fonts/Fonts;", "iconFont", "Lkotlin/jvm/JvmOverloads;", "<init>", "(Ljava/lang/String;ILnet/minecraft/StyleSpriteSource;Lrtx/kimiko/utils/render/fonts/Fonts;)V", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "I", "getColor", "()I", "Lnet/minecraft/StyleSpriteSource;", "getFont", "()Lnet/minecraft/StyleSpriteSource;", "Lrtx/kimiko/utils/render/fonts/Fonts;", "getIconFont", "()Lrtx/kimiko/utils/render/fonts/Fonts;", "rtx.kimiko:kimiko"})
    private static final class TextRun {
        @NotNull
        private final String text;
        private final int color;
        @Nullable
        private final StyleSpriteSource font;
        @Nullable
        private final Fonts iconFont;

        @JvmOverloads
        public TextRun(@NotNull String text, int color, @Nullable StyleSpriteSource font, @Nullable Fonts iconFont) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            this.text = text;
            this.color = color;
            this.font = font;
            this.iconFont = iconFont;
        }

        public /* synthetic */ TextRun(String string, int n, StyleSpriteSource styleSpriteSource2, Fonts fonts, int n2, DefaultConstructorMarker defaultConstructorMarker) {
            this(string, n, styleSpriteSource2, ((n2 & 8) != 0 ? null : fonts));
        }

        @NotNull
        public final String getText() {
            return this.text;
        }

        public final int getColor() {
            return this.color;
        }

        @Nullable
        public final StyleSpriteSource getFont() {
            return this.font;
        }

        @Nullable
        public final Fonts getIconFont() {
            return this.iconFont;
        }

        @JvmOverloads
        public TextRun(@NotNull String text, int color, @Nullable StyleSpriteSource font) {
            this(text, color, font, null, 8, null);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\"\u0010\u001f\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u0017\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR$\u0010#\u001a\u0004\u0018\u00010\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010)\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010\r\u001a\u0004\b*\u0010\u000f\"\u0004\b+\u0010\u0011R$\u0010-\u001a\u0004\u0018\u00010,8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u00104\u001a\u0002038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\"\u0010:\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b:\u0010\r\u001a\u0004\b;\u0010\u000f\"\u0004\b<\u0010\u0011R*\u0010?\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010=8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\"\u0010E\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bE\u0010\u0017\u001a\u0004\bF\u0010\u0019\"\u0004\bG\u0010\u001bR$\u0010H\u001a\u0004\u0018\u00010,8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bH\u0010.\u001a\u0004\bI\u00100\"\u0004\bJ\u00102R\"\u0010K\u001a\u0002038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bK\u00105\u001a\u0004\bL\u00107\"\u0004\bM\u00109R\"\u0010N\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bN\u0010\u0017\u001a\u0004\bO\u0010\u0019\"\u0004\bP\u0010\u001bR\"\u0010Q\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bQ\u0010\u0017\u001a\u0004\bR\u0010\u0019\"\u0004\bS\u0010\u001b\u00a8\u0006T"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NameTags$VisibilityCache;", "", "<init>", "()V", "", "visible", "Z", "getVisible", "()Z", "setVisible", "(Z)V", "", "checkedAtMs", "J", "getCheckedAtMs", "()J", "setCheckedAtMs", "(J)V", "touchedAtMs", "getTouchedAtMs", "setTouchedAtMs", "", "anim", "F", "getAnim", "()F", "setAnim", "(F)V", "alpha", "getAlpha", "setAlpha", "scale", "getScale", "setScale", "Lnet/minecraft/Text;", "resolvedName", "Lnet/minecraft/Text;", "getResolvedName", "()Lnet/minecraft/Text;", "setResolvedName", "(Lnet/minecraft/Text;)V", "resolvedAtMs", "getResolvedAtMs", "setResolvedAtMs", "", "nameSig", "Ljava/lang/String;", "getNameSig", "()Ljava/lang/String;", "setNameSig", "(Ljava/lang/String;)V", "", "builtSize", "I", "getBuiltSize", "()I", "setBuiltSize", "(I)V", "layoutAtMs", "getLayoutAtMs", "setLayoutAtMs", "", "Lrtx/kimiko/api/modules/impl/Visuals/NameTags$Seg;", "segs", "Ljava/util/List;", "getSegs", "()Ljava/util/List;", "setSegs", "(Ljava/util/List;)V", "nameWidth", "getNameWidth", "setNameWidth", "healthSig", "getHealthSig", "setHealthSig", "healthBuiltSize", "getHealthBuiltSize", "setHealthBuiltSize", "heartWidth", "getHeartWidth", "setHeartWidth", "healthNumberWidth", "getHealthNumberWidth", "setHealthNumberWidth", "rtx.kimiko:kimiko"})
    private static final class VisibilityCache {
        private boolean visible;
        private long checkedAtMs;
        private long touchedAtMs;
        private float anim;
        private float alpha;
        private float scale = 0.85f;
        @Nullable
        private Text resolvedName;
        private long resolvedAtMs;
        @Nullable
        private String nameSig;
        private int builtSize = -1;
        private long layoutAtMs;
        @Nullable
        private List<Seg> segs;
        private float nameWidth;
        @Nullable
        private String healthSig;
        private int healthBuiltSize = -1;
        private float heartWidth;
        private float healthNumberWidth;

        public final boolean getVisible() {
            return this.visible;
        }

        public final void setVisible(boolean bl) {
            this.visible = bl;
        }

        public final long getCheckedAtMs() {
            return this.checkedAtMs;
        }

        public final void setCheckedAtMs(long l) {
            this.checkedAtMs = l;
        }

        public final long getTouchedAtMs() {
            return this.touchedAtMs;
        }

        public final void setTouchedAtMs(long l) {
            this.touchedAtMs = l;
        }

        public final float getAnim() {
            return this.anim;
        }

        public final void setAnim(float f) {
            this.anim = f;
        }

        public final float getAlpha() {
            return this.alpha;
        }

        public final void setAlpha(float f) {
            this.alpha = f;
        }

        public final float getScale() {
            return this.scale;
        }

        public final void setScale(float f) {
            this.scale = f;
        }

        @Nullable
        public final Text getResolvedName() {
            return this.resolvedName;
        }

        public final void setResolvedName(@Nullable Text text2) {
            this.resolvedName = text2;
        }

        public final long getResolvedAtMs() {
            return this.resolvedAtMs;
        }

        public final void setResolvedAtMs(long l) {
            this.resolvedAtMs = l;
        }

        @Nullable
        public final String getNameSig() {
            return this.nameSig;
        }

        public final void setNameSig(@Nullable String string) {
            this.nameSig = string;
        }

        public final int getBuiltSize() {
            return this.builtSize;
        }

        public final void setBuiltSize(int n) {
            this.builtSize = n;
        }

        public final long getLayoutAtMs() {
            return this.layoutAtMs;
        }

        public final void setLayoutAtMs(long l) {
            this.layoutAtMs = l;
        }

        @Nullable
        public final List<Seg> getSegs() {
            return this.segs;
        }

        public final void setSegs(@Nullable List<Seg> list) {
            this.segs = list;
        }

        public final float getNameWidth() {
            return this.nameWidth;
        }

        public final void setNameWidth(float f) {
            this.nameWidth = f;
        }

        @Nullable
        public final String getHealthSig() {
            return this.healthSig;
        }

        public final void setHealthSig(@Nullable String string) {
            this.healthSig = string;
        }

        public final int getHealthBuiltSize() {
            return this.healthBuiltSize;
        }

        public final void setHealthBuiltSize(int n) {
            this.healthBuiltSize = n;
        }

        public final float getHeartWidth() {
            return this.heartWidth;
        }

        public final void setHeartWidth(float f) {
            this.heartWidth = f;
        }

        public final float getHealthNumberWidth() {
            return this.healthNumberWidth;
        }

        public final void setHealthNumberWidth(float f) {
            this.healthNumberWidth = f;
        }
    }
}

