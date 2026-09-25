/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 *  org.objectweb.asm.tree.ClassNode
 *  org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
 *  org.spongepowered.asm.mixin.extensibility.IMixinInfo
 */
package mixin.mixinplugin;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class KimikoMixinPlugin
implements IMixinConfigPlugin {
    private static final Map<String, String> REQUIRES_MOD = Map.of(
        "mixin.SodiumSectionFadeMixin", "sodium",
        "mixin.SodiumWastedCullingMixin", "sodium",
        "mixin.SodiumTerrainHooksMixin", "sodium",
        "mixin.compat.IrisHandRendererHandsMixin", "iris",
        "mixin.compat.IrisRenderingPipelineHandsMixin", "iris"
    );
    private static final Map<String, String> DISABLED_WITH_MOD = Map.of("mixin.portallive.LevelRendererPortalVanillaCullMixin", "sodium");
    private static final String KILL_SWITCH = System.getProperty("kimiko.mixins.off", "").trim().toLowerCase(Locale.ROOT);
    private static final Set<String> ENTITY_RENDER_MIXINS = Set.of("mixin.LivingEntityRendererMixin", "mixin.EntityRendererMixin", "mixin.AvatarRendererMixin", "mixin.AvatarRenderStateMixin", "mixin.LivingEntityRenderStateRevealMixin", "mixin.HumanoidModelMixin", "mixin.EquipmentLayerRendererMixin", "mixin.ItemEntityRendererMixin", "mixin.ItemModelResolverMixin", "mixin.OptimizationEntityCullingMixin", "mixin.CustomPetEntityRenderDispatcherMixin", "mixin.AbstractClientPlayerFovMixin", "mixin.TextVisitFactoryMixin");
    private static final Set<String> ENTITY_RENDER_PACKAGES = Set.of("mixin.waveycapes.", "mixin.emotions.", "mixin.chathads.");
    private static final String[] GUI_KEYWORDS = new String[]{"Gui", "Screen", "Chat", "Toast", "TabOverlay", "BossHealth", "Hud", "Inventory"};
    private static final String[] WORLD_KEYWORDS = new String[]{"LevelRenderer", "Section", "Cloud", "Sky", "Weather", "Fog", "LightTexture", "Particle", "WorldBorder", "GameRenderer", "Camera", "Culling", "Sodium", "portallive", "ShaderManager", "Biome"};
    private static int disabledCount = 0;

    public void onLoad(String mixinPackage) {
    }

    public String getRefMapperConfig() {
        return null;
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (KimikoMixinPlugin.killed(mixinClassName)) {
            System.out.println("[kimiko] mixin disabled by kimiko.mixins.off=" + KILL_SWITCH + " (" + ++disabledCount + "): " + mixinClassName);
            return false;
        }
        String requiredMod = REQUIRES_MOD.get(mixinClassName);
        if (requiredMod != null && !KimikoMixinPlugin.isLoaded(requiredMod)) {
            return false;
        }
        String conflictingMod = DISABLED_WITH_MOD.get(mixinClassName);
        return conflictingMod == null || !KimikoMixinPlugin.isLoaded(conflictingMod);
    }

    private static boolean killed(String mixinClassName) {
        if (KILL_SWITCH.isEmpty()) {
            return false;
        }
        if (mixinClassName.contains("Accessor") || mixinClassName.startsWith("mixin.accessor.")) {
            return false;
        }
        if (mixinClassName.equals("mixin.mixinplugin.KimikoMixinPlugin")) {
            return false;
        }
        if (KILL_SWITCH.equals("all")) {
            return true;
        }
        if (KILL_SWITCH.equals("entity")) {
            if (ENTITY_RENDER_MIXINS.contains(mixinClassName)) {
                return true;
            }
            for (String prefix : ENTITY_RENDER_PACKAGES) {
                if (!mixinClassName.startsWith(prefix)) continue;
                return true;
            }
            return false;
        }
        if (KILL_SWITCH.equals("gui")) {
            return KimikoMixinPlugin.matchesAny(mixinClassName, GUI_KEYWORDS);
        }
        if (KILL_SWITCH.equals("world")) {
            return KimikoMixinPlugin.matchesAny(mixinClassName, WORLD_KEYWORDS);
        }
        for (String token : KILL_SWITCH.split(",")) {
            String trimmed = token.trim();
            if (trimmed.isEmpty() || !mixinClassName.toLowerCase(Locale.ROOT).contains(trimmed)) continue;
            return true;
        }
        return false;
    }

    private static boolean matchesAny(String mixinClassName, String[] keywords) {
        for (String keyword : keywords) {
            if (!mixinClassName.contains(keyword)) continue;
            return true;
        }
        return false;
    }

    private static boolean isLoaded(String modId) {
        try {
            return FabricLoader.getInstance().isModLoaded(modId);
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    public List<String> getMixins() {
        return null;
    }

    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}

