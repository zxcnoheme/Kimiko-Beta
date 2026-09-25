/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  net.minecraft.util.crash.CrashReport
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.hit.HitResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.option.GameOptions
 *  net.minecraft.client.session.Session
 *  net.minecraft.sound.SoundCategory
 *  net.minecraft.client.gui.screen.DeathScreen
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Coerce
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import mods.acountswiher.IasService;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.session.Session;
import net.minecraft.sound.SoundCategory;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.modules.impl.Utils.Optimization;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;
import rtx.kimiko.api.ui.window.WindowTitleAnimation;
import rtx.kimiko.utils.crash.CrashReporter;
import rtx.kimiko.utils.network.Network;
import rtx.kimiko.utils.session.SessionChanger;

@Mixin(value={MinecraftClient.class})
public abstract class MinecraftMixin {
    private static final String KIMIKO_DEFAULTS_MARKER = ".kimiko-defaults-applied";
    @Shadow
    @Final
    public GameOptions options;
    @Shadow
    @Final
    public File runDirectory;
    @Shadow
    @Mutable
    private Session session;

    private void kimiko$setSession(Session newSession) {
        this.session = newSession;
    }

    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    private void kimiko$initIas(CallbackInfo ci) {
        SessionChanger.setSessionSetter(this::kimiko$setSession);
        IasService.ensureInitialized();
    }

    @Inject(method={"stop"}, at={@At(value="HEAD")})
    private void kimiko$closeIas(CallbackInfo ci) {
        IasService.close();
    }

    @Inject(method={"onInitFinished"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$skipOnboarding(@Coerce Object cookie, CallbackInfoReturnable<Runnable> cir) {
        File marker = new File(this.runDirectory, KIMIKO_DEFAULTS_MARKER);
        if (!marker.exists()) {
            this.options.getSoundVolumeOption(SoundCategory.MUSIC).setValue(0.0);
            this.options.getSoundVolumeOption(SoundCategory.WEATHER).setValue(0.0);
            this.options.getGuiScale().setValue(2);
            if (this.options.onboardAccessibility) {
                this.options.setAccessibilityOnboarded();
            } else {
                this.options.write();
            }
            try {
                marker.createNewFile();
            }
            catch (IOException iOException) {
                // empty catch block
            }
            return;
        }
        if (this.options.onboardAccessibility) {
            this.options.setAccessibilityOnboarded();
        }
    }

    @Inject(method={"isMultiplayerEnabled"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$enableMultiplayer(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void kimiko$preTickEvent(CallbackInfo ci) {
        Network.tick();
        WindowTitleAnimation.get().tick();
        EventBus.get().post(TickEvent.PRE);
    }

    @Inject(method={"tick"}, at={@At(value="RETURN")})
    private void kimiko$postTickEvent(CallbackInfo ci) {
        EventBus.get().post(TickEvent.POST);
    }

    @Inject(method={"doAttack"}, at={@At(value="HEAD")})
    private void kimiko$clientAttackFallback(CallbackInfoReturnable<Boolean> cir) {
        try {
            MinecraftClient mc = (MinecraftClient)(Object)this;
            ClientPlayerEntity player = mc.player;
            if (player == null || mc.world == null) {
                return;
            }
            if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.ENTITY) {
                return;
            }
            double reach = player.getEntityInteractionRange();
            if (reach <= 0.0) {
                return;
            }
            Vec3d eye = player.getCameraPosVec(1.0f);
            Vec3d look = player.getRotationVec(1.0f);
            Vec3d end = eye.add(look.x * reach, look.y * reach, look.z * reach);
            Entity best = null;
            double bestDist = Double.MAX_VALUE;
            for (Entity entity : mc.world.getOtherEntities((Entity)player, player.getBoundingBox().stretch(look.x * reach, look.y * reach, look.z * reach).expand(1.0), e -> e != null && e != player && !e.isSpectator() && !(e instanceof CustomPetEntity))) {
                double d;
                Optional clip = entity.getBoundingBox().expand(0.1).raycast(eye, end);
                if (!clip.isPresent() || !((d = eye.squaredDistanceTo((Vec3d)clip.get())) < bestDist)) continue;
                bestDist = d;
                best = entity;
            }
            if (best != null) {
                EventBus.get().post(new AttackEntityEvent(best, true));
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Inject(method="printCrashReport(Lnet/minecraft/client/MinecraftClient;Ljava/io/File;Lnet/minecraft/util/crash/CrashReport;)V", at=@At(value="HEAD"))
    private static void kimiko$onCrash(MinecraftClient minecraft, File file, CrashReport report, CallbackInfo ci) {
        try {
            CrashReporter.report(report.getMessage(), report.getCause());
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Inject(method={"setScreen"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$interceptDeathScreen(Screen screen, CallbackInfo ci) {
        if (!(screen instanceof DeathScreen)) {
            return;
        }
        if (WastedDeath.interceptDeathScreen(screen) || WastedDeath.blocksDeathScreen()) {
            ci.cancel();
        }
    }

    @ModifyExpressionValue(method={"setScreen"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isDead()Z")}, require=0)
    private boolean kimiko$skipDeathScreenFallback(boolean original) {
        return original && !WastedDeath.blocksDeathScreen();
    }

    @ModifyReturnValue(method={"usesImprovedTransparency"}, at={@At(value="RETURN")}, require=0)
    private static boolean kimiko$forceFastTransparency(boolean original) {
        return Optimization.shaderTransparency(original);
    }
}

