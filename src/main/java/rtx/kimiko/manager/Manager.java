/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
 *  net.fabricmc.fabric.api.resource.v1.ResourceLoader
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.ResourceType
 *  net.minecraft.resource.ResourceManager
 *  net.minecraft.resource.ResourceReloader
 *  net.minecraft.resource.SynchronousResourceReloader
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.manager;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import mods.chatanim.ChatAnimationMod;
import mods.chathads.ChatHeads;
import mods.shulkerview.ShulkerViewMod;
import mods.waveycapes.WaveyCapesMod;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.resource.SynchronousResourceReloader;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.cards.CardsClient;
import rtx.kimiko.api.chat.commands.CommandManager;
import rtx.kimiko.api.chat.messenger.MessengerClient;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.events.funtime.FunTimeEventsClient;
import rtx.kimiko.api.liteapi.LiteApiClient;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.MediaPlayerModule;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetWarmup;
import rtx.kimiko.api.party.PartyClient;
import rtx.kimiko.utils.animations.AnimationUtil;

import rtx.kimiko.utils.input.GuiMovementHandler;
import rtx.kimiko.utils.media.MediaPlayer;
import rtx.kimiko.utils.media.Radio;
import rtx.kimiko.utils.net.ClientPresence;
import rtx.kimiko.utils.render.modules.post.customsky.CustomSkyRenderer;
import rtx.kimiko.utils.render.modules.post.fogblur.FogBlurRenderer;
import rtx.kimiko.utils.render.modules.post.themeshock.ThemeShockwaveRenderer;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.gif.GifRenderer;
import rtx.kimiko.utils.render.render2d.image.ImageRenderer;
import rtx.kimiko.utils.render.render3d.Text3D;
import rtx.kimiko.utils.render.util.warmup.Load;
import rtx.kimiko.utils.render.util.warmup.Render2DWarmup;
import rtx.kimiko.utils.sounds.SoundManager;
import rtx.kimiko.utils.storage.macro.MacroHandler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0007\u0010\u0003\u00a8\u0006\b"}, d2={"Lrtx/kimiko/manager/Manager;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "init", "shutdown", "rtx.kimiko:kimiko"})
public final class Manager {
    @NotNull
    public static final Manager INSTANCE = new Manager();

    private Manager() {
    }

    @JvmStatic
    public static final void init() {
        Render2D.init();
        Render2DWarmup.Companion.init();
        AnimationUtil.Companion.init();
        GuiMovementHandler.Companion.init();
        ModuleManager.Companion.get().init();
        ConfigManager.Companion.init();
        CommandManager.Companion.get().init();
        MacroHandler.Companion.init();
        DragSystem.Companion.get().init();
        SoundManager.init();
        ClientLifecycleEvents.CLIENT_STOPPING.register(Manager::init$lambda$0);
        WaveyCapesMod.INSTANCE.init();
        ShulkerViewMod.init();
        ChatHeads.init();
        ChatAnimationMod.init();
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Runnable warmup = Manager::init$lambda$2;
        mc.execute(warmup);
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"ui_font_rewarmup");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        Identifier reloadId = identifier2;
        ResourceLoader.get((ResourceType)ResourceType.CLIENT_RESOURCES).registerReloader(reloadId, (ResourceReloader)((SynchronousResourceReloader)Manager::init$lambda$3));
    }

    @JvmStatic
    public static final void shutdown() {
        try {
            MediaPlayerModule media = MediaPlayerModule.Companion.getInstance();
            if (media != null) {
                media.releaseMute();
            }
        }
        catch (Throwable ignored) {
        }
        try {
            Radio.saveNow();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            Radio.stop();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            MediaPlayer.shutdown();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        ConfigManager.Companion.saveAll();
        FunTimeEventsClient.INSTANCE.shutdown();
        MessengerClient.INSTANCE.stop();
        PartyClient.INSTANCE.stop();
        CardsClient.INSTANCE.stop();
        LiteApiClient.INSTANCE.stop();
        Render2D.close();
    }

    private static final void init$lambda$0(MinecraftClient client) {
        Intrinsics.checkNotNullParameter((Object)client, (String)"client");
        Manager.shutdown();
    }

    private static final void init$lambda$2() {
        GifRenderer.preload("kimiko:gif/kity.gif");
        Load.runStartupWarmup();
    }

    private static final void init$lambda$3$0() {
        boolean firstReload = !Load.initialReloadSeen;
        Load.initialReloadSeen = true;
        if (firstReload) {
            Load.runStartupWarmup();
            return;
        }
        Load.warmupFonts();
        try {
            CustomSkyRenderer.invalidate();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            FogBlurRenderer.invalidate();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            ThemeShockwaveRenderer.invalidate();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            Text3D.invalidate();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            Render2DWarmup.Companion.reset();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            ImageRenderer.Companion.getInstance().invalidate();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            CustomPetWarmup.warmup();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static final void init$lambda$3(ResourceManager resourceManager) {
        Intrinsics.checkNotNullParameter((Object)resourceManager, (String)"resourceManager");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return;
        }
        MinecraftClient m = minecraftClient2;
        m.execute(Manager::init$lambda$3$0);
    }
}

