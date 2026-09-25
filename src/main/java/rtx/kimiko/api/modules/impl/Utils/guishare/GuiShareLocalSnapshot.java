/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareBindPopup;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareChatState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareConfigRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiSharePopupRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareThemeState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u00ed\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f\u0012\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0018\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\u001e\u001a\u00020\u0002\u0012\u0006\u0010\u001f\u001a\u00020\u0006\u0012\u0006\u0010!\u001a\u00020 \u0012\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u000f\u0012\u0006\u0010$\u001a\u00020\u0006\u0012\u0006\u0010%\u001a\u00020\u0006\u00a2\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010-J\u0010\u0010/\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010-J\u0010\u00100\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010-J\u0010\u00101\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b1\u0010)J\u0010\u00102\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b2\u0010-J\u0010\u00103\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b3\u0010-J\u0010\u00104\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b4\u0010-J\u0016\u00105\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u00c6\u0003\u00a2\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020\u0012H\u00c6\u0003\u00a2\u0006\u0004\b7\u00108J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u0010)J\u0010\u0010:\u001a\u00020\u0015H\u00c6\u0003\u00a2\u0006\u0004\b:\u0010;J\u0016\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\b<\u00106J\u001c\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0018H\u00c6\u0003\u00a2\u0006\u0004\b=\u0010>J\u0010\u0010?\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b?\u0010)J\u0010\u0010@\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b@\u0010)J\u0010\u0010A\u001a\u00020\u001cH\u00c6\u0003\u00a2\u0006\u0004\bA\u0010BJ\u0010\u0010C\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bC\u0010)J\u0010\u0010D\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\bD\u0010-J\u0010\u0010E\u001a\u00020 H\u00c6\u0003\u00a2\u0006\u0004\bE\u0010FJ\u0016\u0010G\u001a\b\u0012\u0004\u0012\u00020\"0\u000fH\u00c6\u0003\u00a2\u0006\u0004\bG\u00106J\u0010\u0010H\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\bH\u0010-J\u0010\u0010I\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\bI\u0010-J\u00a8\u0002\u0010J\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010!\u001a\u00020 2\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u000f2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\bJ\u0010KJ\u001b\u0010N\u001a\u00020\u00152\b\u0010M\u001a\u0004\u0018\u00010LH\u00d6\u0083\u0004\u00a2\u0006\u0004\bN\u0010OJ\u0011\u0010P\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\bP\u0010+J\u0011\u0010Q\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\bQ\u0010)R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0003\u0010SR\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0005\u0010TR\u0019\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0007\u0010UR\u0019\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\b\u0010UR\u0019\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\t\u0010UR\u0019\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\n\u0010UR\u0019\u0010\u000b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u000b\u0010SR\u0019\u0010\f\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\f\u0010UR\u0019\u0010\r\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\r\u0010UR\u0019\u0010\u000e\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u000e\u0010UR\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0011\u0010VR\u0019\u0010\u0013\u001a\u00020\u00128\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0013\u0010WR\u0019\u0010\u0014\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0014\u0010SR\u0019\u0010\u0016\u001a\u00020\u00158\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0016\u0010XR\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0017\u0010VR%\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00188\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u0019\u0010YR\u0019\u0010\u001a\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u001a\u0010SR\u0019\u0010\u001b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u001b\u0010SR\u0019\u0010\u001d\u001a\u00020\u001c8\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u001d\u0010ZR\u0019\u0010\u001e\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u001e\u0010SR\u0019\u0010\u001f\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b\u001f\u0010UR\u0019\u0010!\u001a\u00020 8\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b!\u0010[R\u001f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b#\u0010VR\u0019\u0010$\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b$\u0010UR\u0019\u0010%\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\bR\u00a2\u0006\u0006\n\u0004\b%\u0010U\u00a8\u0006\\"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareLocalSnapshot;", "Ljava/lang/Record;", "", "category", "", "eventsSub", "", "eventsScroll", "listScroll", "mouseX", "mouseY", "popupModule", "popupScroll", "popupX", "popupY", "", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "popupRows", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "bindPopup", "search", "", "searchFocused", "enabledModules", "", "moduleBadges", "role", "avatarUrl", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "theme", "selectedTheme", "themesScroll", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "chat", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareConfigRow;", "configs", "configsScroll", "screenHeight", "<init>", "(Ljava/lang/String;IFFFFLjava/lang/String;FFFLjava/util/List;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;Ljava/lang/String;ZLjava/util/List;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;Ljava/lang/String;FLrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;Ljava/util/List;FF)V", "component1", "()Ljava/lang/String;", "component2", "()I", "component3", "()F", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "()Ljava/util/List;", "component12", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "component13", "component14", "()Z", "component15", "component16", "()Ljava/util/Map;", "component17", "component18", "component19", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "component20", "component21", "component22", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "component23", "component24", "component25", "copy", "(Ljava/lang/String;IFFFFLjava/lang/String;FFFLjava/util/List;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;Ljava/lang/String;ZLjava/util/List;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;Ljava/lang/String;FLrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;Ljava/util/List;FF)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareLocalSnapshot;", "", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmField;", "Ljava/lang/String;", "I", "F", "Ljava/util/List;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "Z", "Ljava/util/Map;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "rtx.kimiko:kimiko"})
public final class GuiShareLocalSnapshot
{
    @JvmField
    @NotNull
    public final String category;
    @JvmField
    public final int eventsSub;
    @JvmField
    public final float eventsScroll;
    @JvmField
    public final float listScroll;
    @JvmField
    public final float mouseX;
    @JvmField
    public final float mouseY;
    @JvmField
    @NotNull
    public final String popupModule;
    @JvmField
    public final float popupScroll;
    @JvmField
    public final float popupX;
    @JvmField
    public final float popupY;
    @JvmField
    @NotNull
    public final List<GuiSharePopupRow> popupRows;
    @JvmField
    @NotNull
    public final GuiShareBindPopup bindPopup;
    @JvmField
    @NotNull
    public final String search;
    @JvmField
    public final boolean searchFocused;
    @JvmField
    @NotNull
    public final List<String> enabledModules;
    @JvmField
    @NotNull
    public final Map<String, String> moduleBadges;
    @JvmField
    @NotNull
    public final String role;
    @JvmField
    @NotNull
    public final String avatarUrl;
    @JvmField
    @NotNull
    public final GuiShareThemeState theme;
    @JvmField
    @NotNull
    public final String selectedTheme;
    @JvmField
    public final float themesScroll;
    @JvmField
    @NotNull
    public final GuiShareChatState chat;
    @JvmField
    @NotNull
    public final List<GuiShareConfigRow> configs;
    @JvmField
    public final float configsScroll;
    @JvmField
    public final float screenHeight;

    public GuiShareLocalSnapshot(@NotNull String category, int eventsSub, float eventsScroll, float listScroll, float mouseX, float mouseY, @NotNull String popupModule, float popupScroll, float popupX, float popupY, @NotNull List<GuiSharePopupRow> popupRows, @NotNull GuiShareBindPopup bindPopup, @NotNull String search, boolean searchFocused, @NotNull List<String> enabledModules, @NotNull Map<String, String> moduleBadges, @NotNull String role, @NotNull String avatarUrl, @NotNull GuiShareThemeState theme, @NotNull String selectedTheme, float themesScroll, @NotNull GuiShareChatState chat, @NotNull List<GuiShareConfigRow> configs, float configsScroll, float screenHeight) {
        Intrinsics.checkNotNullParameter((Object)category, (String)"category");
        Intrinsics.checkNotNullParameter((Object)popupModule, (String)"popupModule");
        Intrinsics.checkNotNullParameter(popupRows, (String)"popupRows");
        Intrinsics.checkNotNullParameter((Object)bindPopup, (String)"bindPopup");
        Intrinsics.checkNotNullParameter((Object)search, (String)"search");
        Intrinsics.checkNotNullParameter(enabledModules, (String)"enabledModules");
        Intrinsics.checkNotNullParameter(moduleBadges, (String)"moduleBadges");
        Intrinsics.checkNotNullParameter((Object)role, (String)"role");
        Intrinsics.checkNotNullParameter((Object)avatarUrl, (String)"avatarUrl");
        Intrinsics.checkNotNullParameter((Object)theme, (String)"theme");
        Intrinsics.checkNotNullParameter((Object)selectedTheme, (String)"selectedTheme");
        Intrinsics.checkNotNullParameter((Object)chat, (String)"chat");
        Intrinsics.checkNotNullParameter(configs, (String)"configs");
        this.category = category;
        this.eventsSub = eventsSub;
        this.eventsScroll = eventsScroll;
        this.listScroll = listScroll;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.popupModule = popupModule;
        this.popupScroll = popupScroll;
        this.popupX = popupX;
        this.popupY = popupY;
        this.popupRows = popupRows;
        this.bindPopup = bindPopup;
        this.search = search;
        this.searchFocused = searchFocused;
        this.enabledModules = enabledModules;
        this.moduleBadges = moduleBadges;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.theme = theme;
        this.selectedTheme = selectedTheme;
        this.themesScroll = themesScroll;
        this.chat = chat;
        this.configs = configs;
        this.configsScroll = configsScroll;
        this.screenHeight = screenHeight;
    }

    @NotNull
    public final String component1() {
        return this.category;
    }

    public final int component2() {
        return this.eventsSub;
    }

    public final float component3() {
        return this.eventsScroll;
    }

    public final float component4() {
        return this.listScroll;
    }

    public final float component5() {
        return this.mouseX;
    }

    public final float component6() {
        return this.mouseY;
    }

    @NotNull
    public final String component7() {
        return this.popupModule;
    }

    public final float component8() {
        return this.popupScroll;
    }

    public final float component9() {
        return this.popupX;
    }

    public final float component10() {
        return this.popupY;
    }

    @NotNull
    public final List<GuiSharePopupRow> component11() {
        return this.popupRows;
    }

    @NotNull
    public final GuiShareBindPopup component12() {
        return this.bindPopup;
    }

    @NotNull
    public final String component13() {
        return this.search;
    }

    public final boolean component14() {
        return this.searchFocused;
    }

    @NotNull
    public final List<String> component15() {
        return this.enabledModules;
    }

    @NotNull
    public final Map<String, String> component16() {
        return this.moduleBadges;
    }

    @NotNull
    public final String component17() {
        return this.role;
    }

    @NotNull
    public final String component18() {
        return this.avatarUrl;
    }

    @NotNull
    public final GuiShareThemeState component19() {
        return this.theme;
    }

    @NotNull
    public final String component20() {
        return this.selectedTheme;
    }

    public final float component21() {
        return this.themesScroll;
    }

    @NotNull
    public final GuiShareChatState component22() {
        return this.chat;
    }

    @NotNull
    public final List<GuiShareConfigRow> component23() {
        return this.configs;
    }

    public final float component24() {
        return this.configsScroll;
    }

    public final float component25() {
        return this.screenHeight;
    }

    @NotNull
    public final GuiShareLocalSnapshot copy(@NotNull String category, int eventsSub, float eventsScroll, float listScroll, float mouseX, float mouseY, @NotNull String popupModule, float popupScroll, float popupX, float popupY, @NotNull List<GuiSharePopupRow> popupRows, @NotNull GuiShareBindPopup bindPopup, @NotNull String search, boolean searchFocused, @NotNull List<String> enabledModules, @NotNull Map<String, String> moduleBadges, @NotNull String role, @NotNull String avatarUrl, @NotNull GuiShareThemeState theme, @NotNull String selectedTheme, float themesScroll, @NotNull GuiShareChatState chat, @NotNull List<GuiShareConfigRow> configs, float configsScroll, float screenHeight) {
        Intrinsics.checkNotNullParameter((Object)category, (String)"category");
        Intrinsics.checkNotNullParameter((Object)popupModule, (String)"popupModule");
        Intrinsics.checkNotNullParameter(popupRows, (String)"popupRows");
        Intrinsics.checkNotNullParameter((Object)bindPopup, (String)"bindPopup");
        Intrinsics.checkNotNullParameter((Object)search, (String)"search");
        Intrinsics.checkNotNullParameter(enabledModules, (String)"enabledModules");
        Intrinsics.checkNotNullParameter(moduleBadges, (String)"moduleBadges");
        Intrinsics.checkNotNullParameter((Object)role, (String)"role");
        Intrinsics.checkNotNullParameter((Object)avatarUrl, (String)"avatarUrl");
        Intrinsics.checkNotNullParameter((Object)theme, (String)"theme");
        Intrinsics.checkNotNullParameter((Object)selectedTheme, (String)"selectedTheme");
        Intrinsics.checkNotNullParameter((Object)chat, (String)"chat");
        Intrinsics.checkNotNullParameter(configs, (String)"configs");
        return new GuiShareLocalSnapshot(category, eventsSub, eventsScroll, listScroll, mouseX, mouseY, popupModule, popupScroll, popupX, popupY, popupRows, bindPopup, search, searchFocused, enabledModules, moduleBadges, role, avatarUrl, theme, selectedTheme, themesScroll, chat, configs, configsScroll, screenHeight);
    }

    public static /* synthetic */ GuiShareLocalSnapshot copy$default(GuiShareLocalSnapshot guiShareLocalSnapshot, String string, int n, float f, float f2, float f3, float f4, String string2, float f5, float f6, float f7, List list, GuiShareBindPopup guiShareBindPopup, String string3, boolean bl, List list2, Map map, String string4, String string5, GuiShareThemeState guiShareThemeState, String string6, float f8, GuiShareChatState guiShareChatState, List list3, float f9, float f10, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string = guiShareLocalSnapshot.category;
        }
        if ((n2 & 2) != 0) {
            n = guiShareLocalSnapshot.eventsSub;
        }
        if ((n2 & 4) != 0) {
            f = guiShareLocalSnapshot.eventsScroll;
        }
        if ((n2 & 8) != 0) {
            f2 = guiShareLocalSnapshot.listScroll;
        }
        if ((n2 & 0x10) != 0) {
            f3 = guiShareLocalSnapshot.mouseX;
        }
        if ((n2 & 0x20) != 0) {
            f4 = guiShareLocalSnapshot.mouseY;
        }
        if ((n2 & 0x40) != 0) {
            string2 = guiShareLocalSnapshot.popupModule;
        }
        if ((n2 & 0x80) != 0) {
            f5 = guiShareLocalSnapshot.popupScroll;
        }
        if ((n2 & 0x100) != 0) {
            f6 = guiShareLocalSnapshot.popupX;
        }
        if ((n2 & 0x200) != 0) {
            f7 = guiShareLocalSnapshot.popupY;
        }
        if ((n2 & 0x400) != 0) {
            list = guiShareLocalSnapshot.popupRows;
        }
        if ((n2 & 0x800) != 0) {
            guiShareBindPopup = guiShareLocalSnapshot.bindPopup;
        }
        if ((n2 & 0x1000) != 0) {
            string3 = guiShareLocalSnapshot.search;
        }
        if ((n2 & 0x2000) != 0) {
            bl = guiShareLocalSnapshot.searchFocused;
        }
        if ((n2 & 0x4000) != 0) {
            list2 = guiShareLocalSnapshot.enabledModules;
        }
        if ((n2 & 0x8000) != 0) {
            map = guiShareLocalSnapshot.moduleBadges;
        }
        if ((n2 & 0x10000) != 0) {
            string4 = guiShareLocalSnapshot.role;
        }
        if ((n2 & 0x20000) != 0) {
            string5 = guiShareLocalSnapshot.avatarUrl;
        }
        if ((n2 & 0x40000) != 0) {
            guiShareThemeState = guiShareLocalSnapshot.theme;
        }
        if ((n2 & 0x80000) != 0) {
            string6 = guiShareLocalSnapshot.selectedTheme;
        }
        if ((n2 & 0x100000) != 0) {
            f8 = guiShareLocalSnapshot.themesScroll;
        }
        if ((n2 & 0x200000) != 0) {
            guiShareChatState = guiShareLocalSnapshot.chat;
        }
        if ((n2 & 0x400000) != 0) {
            list3 = guiShareLocalSnapshot.configs;
        }
        if ((n2 & 0x800000) != 0) {
            f9 = guiShareLocalSnapshot.configsScroll;
        }
        if ((n2 & 0x1000000) != 0) {
            f10 = guiShareLocalSnapshot.screenHeight;
        }
        return guiShareLocalSnapshot.copy(string, n, f, f2, f3, f4, string2, f5, f6, f7, list, guiShareBindPopup, string3, bl, list2, map, string4, string5, guiShareThemeState, string6, f8, guiShareChatState, list3, f9, f10);
    }

    @Override
    @NotNull
    public String toString() {
        return "GuiShareLocalSnapshot(category=" + this.category + ", eventsSub=" + this.eventsSub + ", eventsScroll=" + this.eventsScroll + ", listScroll=" + this.listScroll + ", mouseX=" + this.mouseX + ", mouseY=" + this.mouseY + ", popupModule=" + this.popupModule + ", popupScroll=" + this.popupScroll + ", popupX=" + this.popupX + ", popupY=" + this.popupY + ", popupRows=" + this.popupRows + ", bindPopup=" + this.bindPopup + ", search=" + this.search + ", searchFocused=" + this.searchFocused + ", enabledModules=" + this.enabledModules + ", moduleBadges=" + this.moduleBadges + ", role=" + this.role + ", avatarUrl=" + this.avatarUrl + ", theme=" + this.theme + ", selectedTheme=" + this.selectedTheme + ", themesScroll=" + this.themesScroll + ", chat=" + this.chat + ", configs=" + this.configs + ", configsScroll=" + this.configsScroll + ", screenHeight=" + this.screenHeight + ")";
    }

    @Override
    public int hashCode() {
        int result = this.category.hashCode();
        result = result * 31 + Integer.hashCode(this.eventsSub);
        result = result * 31 + Float.hashCode(this.eventsScroll);
        result = result * 31 + Float.hashCode(this.listScroll);
        result = result * 31 + Float.hashCode(this.mouseX);
        result = result * 31 + Float.hashCode(this.mouseY);
        result = result * 31 + this.popupModule.hashCode();
        result = result * 31 + Float.hashCode(this.popupScroll);
        result = result * 31 + Float.hashCode(this.popupX);
        result = result * 31 + Float.hashCode(this.popupY);
        result = result * 31 + ((Object)this.popupRows).hashCode();
        result = result * 31 + this.bindPopup.hashCode();
        result = result * 31 + this.search.hashCode();
        result = result * 31 + Boolean.hashCode(this.searchFocused);
        result = result * 31 + ((Object)this.enabledModules).hashCode();
        result = result * 31 + ((Object)this.moduleBadges).hashCode();
        result = result * 31 + this.role.hashCode();
        result = result * 31 + this.avatarUrl.hashCode();
        result = result * 31 + this.theme.hashCode();
        result = result * 31 + this.selectedTheme.hashCode();
        result = result * 31 + Float.hashCode(this.themesScroll);
        result = result * 31 + this.chat.hashCode();
        result = result * 31 + ((Object)this.configs).hashCode();
        result = result * 31 + Float.hashCode(this.configsScroll);
        result = result * 31 + Float.hashCode(this.screenHeight);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuiShareLocalSnapshot)) {
            return false;
        }
        GuiShareLocalSnapshot guiShareLocalSnapshot = (GuiShareLocalSnapshot)other;
        if (!Intrinsics.areEqual((Object)this.category, (Object)guiShareLocalSnapshot.category)) {
            return false;
        }
        if (this.eventsSub != guiShareLocalSnapshot.eventsSub) {
            return false;
        }
        if (Float.compare(this.eventsScroll, guiShareLocalSnapshot.eventsScroll) != 0) {
            return false;
        }
        if (Float.compare(this.listScroll, guiShareLocalSnapshot.listScroll) != 0) {
            return false;
        }
        if (Float.compare(this.mouseX, guiShareLocalSnapshot.mouseX) != 0) {
            return false;
        }
        if (Float.compare(this.mouseY, guiShareLocalSnapshot.mouseY) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.popupModule, (Object)guiShareLocalSnapshot.popupModule)) {
            return false;
        }
        if (Float.compare(this.popupScroll, guiShareLocalSnapshot.popupScroll) != 0) {
            return false;
        }
        if (Float.compare(this.popupX, guiShareLocalSnapshot.popupX) != 0) {
            return false;
        }
        if (Float.compare(this.popupY, guiShareLocalSnapshot.popupY) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual(this.popupRows, guiShareLocalSnapshot.popupRows)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.bindPopup, (Object)guiShareLocalSnapshot.bindPopup)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.search, (Object)guiShareLocalSnapshot.search)) {
            return false;
        }
        if (this.searchFocused != guiShareLocalSnapshot.searchFocused) {
            return false;
        }
        if (!Intrinsics.areEqual(this.enabledModules, guiShareLocalSnapshot.enabledModules)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.moduleBadges, guiShareLocalSnapshot.moduleBadges)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.role, (Object)guiShareLocalSnapshot.role)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.avatarUrl, (Object)guiShareLocalSnapshot.avatarUrl)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.theme, (Object)guiShareLocalSnapshot.theme)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.selectedTheme, (Object)guiShareLocalSnapshot.selectedTheme)) {
            return false;
        }
        if (Float.compare(this.themesScroll, guiShareLocalSnapshot.themesScroll) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.chat, (Object)guiShareLocalSnapshot.chat)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.configs, guiShareLocalSnapshot.configs)) {
            return false;
        }
        if (Float.compare(this.configsScroll, guiShareLocalSnapshot.configsScroll) != 0) {
            return false;
        }
        return Float.compare(this.screenHeight, guiShareLocalSnapshot.screenHeight) == 0;
    }
}

