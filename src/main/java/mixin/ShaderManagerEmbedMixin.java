/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gl.ShaderLoader
 *  net.minecraft.util.Identifier
 *  net.minecraft.resource.ResourcePack
 *  net.minecraft.resource.Resource
 *  net.minecraft.resource.ResourceManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package mixin;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraft.client.gl.ShaderLoader;
import net.minecraft.util.Identifier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rtx.kimiko.utils.render.shaders.EmbeddedShaders;

@Mixin(value={ShaderLoader.class})
public abstract class ShaderManagerEmbedMixin {
    @Redirect(method={"prepare"}, at=@At(value="INVOKE", target="Lnet/minecraft/resource/ResourceManager;findResources(Ljava/lang/String;Ljava/util/function/Predicate;)Ljava/util/Map;"))
    private Map<Identifier, Resource> kimiko$injectEmbeddedShaders(ResourceManager manager, String path, Predicate<Identifier> filter) {
        Map resources = manager.findResources(path, filter);
        if (EmbeddedShaders.SOURCES.isEmpty()) {
            return resources;
        }
        ResourcePack pack = null;
        Iterator iterator = resources.values().iterator();
        if (iterator.hasNext()) {
            Resource resource = (Resource)iterator.next();
            pack = resource.getPack();
        }
        if (pack == null) {
            return resources;
        }
        HashMap<Identifier, Resource> merged = new HashMap<Identifier, Resource>(resources);
        for (Map.Entry<String, String> entry : EmbeddedShaders.SOURCES.entrySet()) {
            Identifier id = Identifier.of((String)"kimiko", (String)("shaders/" + entry.getKey()));
            byte[] bytes = entry.getValue().getBytes(StandardCharsets.UTF_8);
            merged.putIfAbsent(id, new Resource(pack, () -> new ByteArrayInputStream(bytes)));
        }
        return merged;
    }
}

