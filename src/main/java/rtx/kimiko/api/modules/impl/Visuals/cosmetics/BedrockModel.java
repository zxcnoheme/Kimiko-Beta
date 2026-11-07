package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jetbrains.annotations.Nullable;

/**
 * Независимый парсер Bedrock-моделей (geometry + animation) для внешних косметик.
 * Формат — как в SpaceVisuals model.json: per-face UV (в том числе отрицательные
 * uv_size = зеркало), иерархия костей с pivot/rotation, каналы анимации rotation/position
 * с покадровыми ключами {"0":[x,y,z], "0.033":[...]}.
 */
public final class BedrockModel {

    public static final class Cube {
        public float[] origin;
        public float[] size;
        public float[] pivot;
        public float[] rotation;
        /** face -> {uv:[u,v], size:[w,h]} (знак сохраняется). */
        public final Map<String, float[]> faceUv = new HashMap<>();
        public final Map<String, float[]> faceUvSize = new HashMap<>();
        public float inflate;
    }

    public static final class Bone {
        public String name;
        public String parent;
        public float[] pivot;
        public float[] rotation;
        public final List<Cube> cubes = new ArrayList<>();
        public final List<Bone> children = new ArrayList<>();
    }

    /** Канал анимации кости: отсортированные ключи времени -> [x,y,z]. */
    public static final class Channel extends TreeMap<Float, float[]> {
    }

    public static final class Anim {
        public String name;
        public boolean loop = true;
        public float length = 1.0f;
        /** bone -> channel("rotation"/"position") -> keys. */
        public final Map<String, Map<String, Channel>> bones = new HashMap<>();
    }

    public final List<Bone> rootBones = new ArrayList<>();
    public final Map<String, Bone> bonesByName = new HashMap<>();
    public float textureWidth = 64.0f;
    public float textureHeight = 64.0f;
    public final Map<String, Anim> animations = new HashMap<>();

    @Nullable
    public static BedrockModel parse(JsonObject geometryRoot, JsonObject animationRoot) {
        try {
            BedrockModel model = new BedrockModel();
            if (geometryRoot != null) {
                model.parseGeometry(geometryRoot);
            }
            if (animationRoot != null) {
                model.parseAnimations(animationRoot);
            }
            return model.rootBones.isEmpty() ? null : model;
        } catch (Exception e) {
            return null;
        }
    }

    private static void addBoxUv(Cube cube, String face, float u, float v, float w, float h) {
        cube.faceUv.put(face, new float[]{u, v});
        cube.faceUvSize.put(face, new float[]{w, h});
    }

    private void parseGeometry(JsonObject root) {
        JsonArray geometries = root.getAsJsonArray("minecraft:geometry");
        if (geometries == null || geometries.isEmpty()) {
            return;
        }
        JsonObject geo = geometries.get(0).getAsJsonObject();
        JsonObject desc = geo.getAsJsonObject("description");
        if (desc != null) {
            this.textureWidth = num(desc, "texture_width", this.textureWidth);
            this.textureHeight = num(desc, "texture_height", this.textureHeight);
        }
        JsonArray bonesArr = geo.getAsJsonArray("bones");
        if (bonesArr == null) {
            return;
        }
        for (JsonElement el : bonesArr) {
            JsonObject b = el.getAsJsonObject();
            Bone bone = new Bone();
            bone.name = str(b, "name", "");
            bone.parent = b.has("parent") && b.get("parent").isJsonPrimitive() ? b.get("parent").getAsString() : null;
            bone.pivot = vec3(b, "pivot");
            bone.rotation = vec3(b, "rotation");
            JsonArray cubesArr = b.getAsJsonArray("cubes");
            if (cubesArr != null) {
                for (JsonElement ce : cubesArr) {
                    JsonObject c = ce.getAsJsonObject();
                    Cube cube = new Cube();
                    cube.origin = vec3(c, "origin");
                    cube.size = vec3(c, "size");
                    cube.pivot = vec3(c, "pivot");
                    cube.rotation = vec3(c, "rotation");
                    cube.inflate = num(c, "inflate", 0.0f);
                    JsonElement uvEl = c.get("uv");
                    if (uvEl != null && uvEl.isJsonObject()) {
                        JsonObject uv = uvEl.getAsJsonObject();
                        for (Map.Entry<String, JsonElement> e : uv.entrySet()) {
                            JsonObject face = e.getValue().getAsJsonObject();
                            JsonArray uvArr = face.getAsJsonArray("uv");
                            if (uvArr != null && uvArr.size() >= 2) {
                                float u0 = uvArr.get(0).getAsFloat();
                                float v0 = uvArr.get(1).getAsFloat();
                                float du = 0.0f;
                                float dv = 0.0f;
                                JsonArray sizeArr = face.getAsJsonArray("uv_size");
                                if (sizeArr != null && sizeArr.size() >= 2) {
                                    du = sizeArr.get(0).getAsFloat();
                                    dv = sizeArr.get(1).getAsFloat();
                                }
                                cube.faceUv.put(e.getKey(), new float[]{u0, v0});
                                cube.faceUvSize.put(e.getKey(), new float[]{du, dv});
                            }
                        }
                    } else if (uvEl != null && uvEl.isJsonArray() && cube.origin != null && cube.size != null) {
                        // box-UV: uv:[u,v] — стандартная развёртка Bedrock-куба
                        float u = uvEl.getAsJsonArray().get(0).getAsFloat();
                        float v = uvEl.getAsJsonArray().get(1).getAsFloat();
                        float w = Math.abs(cube.size[0]);
                        float h = Math.abs(cube.size[1]);
                        float d = Math.abs(cube.size[2]);
                        addBoxUv(cube, "east", u, v + d, d, h);
                        addBoxUv(cube, "north", u + d, v + d, w, h);
                        addBoxUv(cube, "west", u + d + w, v + d, d, h);
                        addBoxUv(cube, "south", u + d + w + d, v + d, w, h);
                        addBoxUv(cube, "up", u + d, v, w, d);
                        addBoxUv(cube, "down", u + d + w, v, w, d);
                    }
                    if (cube.origin != null && cube.size != null) {
                        bone.cubes.add(cube);
                    }
                }
            }
            this.bonesByName.put(bone.name, bone);
        }
        for (Bone bone : this.bonesByName.values()) {
            Bone parent = bone.parent != null ? this.bonesByName.get(bone.parent) : null;
            if (parent != null) {
                parent.children.add(bone);
            } else {
                this.rootBones.add(bone);
            }
        }
    }

    private void parseAnimations(JsonObject root) {
        JsonObject anims = root.getAsJsonObject("animations");
        if (anims == null) {
            return;
        }
        for (Map.Entry<String, JsonElement> e : anims.entrySet()) {
            JsonObject a = e.getValue().getAsJsonObject();
            Anim anim = new Anim();
            anim.name = e.getKey();
            anim.loop = bool(a, "loop", true);
            anim.length = num(a, "animation_length", 1.0f);
            JsonObject bones = a.getAsJsonObject("bones");
            if (bones != null) {
                for (Map.Entry<String, JsonElement> be : bones.entrySet()) {
                    JsonObject b = be.getValue().getAsJsonObject();
                    Map<String, Channel> channels = new HashMap<>();
                    for (String channelName : new String[]{"rotation", "position"}) {
                        JsonElement ch = b.get(channelName);
                        if (ch == null) {
                            continue;
                        }
                        Channel channel = new Channel();
                        if (ch.isJsonArray()) {
                            // компактный формат: [[t, x, y, z], ...]
                            JsonArray arr = ch.getAsJsonArray();
                            for (JsonElement ke : arr) {
                                JsonArray kv = ke.getAsJsonArray();
                                if (kv.size() >= 4) {
                                    channel.put(kv.get(0).getAsFloat(), new float[]{kv.get(1).getAsFloat(), kv.get(2).getAsFloat(), kv.get(3).getAsFloat()});
                                }
                            }
                        } else if (ch.isJsonObject()) {
                            // формат SpaceVisuals: {"0":[x,y,z], "0.033":[...], "1": {...data...}}
                            for (Map.Entry<String, JsonElement> ke : ch.getAsJsonObject().entrySet()) {
                                float time;
                                try {
                                    time = Float.parseFloat(ke.getKey());
                                } catch (NumberFormatException nf) {
                                    continue;
                                }
                                JsonElement val = ke.getValue();
                                float[] vec = null;
                                if (val.isJsonArray() && val.getAsJsonArray().size() >= 3) {
                                    JsonArray av = val.getAsJsonArray();
                                    vec = new float[]{av.get(0).getAsFloat(), av.get(1).getAsFloat(), av.get(2).getAsFloat()};
                                } else if (val.isJsonObject()) {
                                    JsonObject obj = val.getAsJsonObject();
                                    JsonArray inner = obj.getAsJsonArray("data");
                                    if (inner != null && inner.size() >= 3) {
                                        vec = new float[]{inner.get(0).getAsFloat(), inner.get(1).getAsFloat(), inner.get(2).getAsFloat()};
                                    } else {
                                        vec = new float[]{num(obj, "x", 0.0f), num(obj, "y", 0.0f), num(obj, "z", 0.0f)};
                                    }
                                }
                                if (vec != null) {
                                    channel.put(time, vec);
                                }
                            }
                        }
                        if (!channel.isEmpty()) {
                            channels.put(channelName, channel);
                        }
                    }
                    if (!channels.isEmpty()) {
                        anim.bones.put(be.getKey(), channels);
                    }
                }
            }
            this.animations.put(anim.name, anim);
        }
    }

    /** Минимальный Y геометрии в px (Y-up, для авто-подъёма моделей с базой в 0). */
    public float minYPx() {
        float min = Float.MAX_VALUE;
        for (Bone root : this.rootBones) {
            min = Math.min(min, minYPx(root));
        }
        return min == Float.MAX_VALUE ? 0.0f : min;
    }

    private static float minYPx(Bone bone) {
        float min = Float.MAX_VALUE;
        for (Cube cube : bone.cubes) {
            min = Math.min(min, Math.min(cube.origin[1], cube.origin[1] + cube.size[1]));
        }
        for (Bone child : bone.children) {
            min = Math.min(min, minYPx(child));
        }
        return min;
    }

    /** Ищет анимацию по суффиксу имени (walk/idle/gui), независимо от префикса. */
    @Nullable
    public Anim findAnim(String suffix) {
        String want = "animation." + suffix;
        Anim exact = this.animations.get(want);
        if (exact != null) {
            return exact;
        }
        for (Anim anim : this.animations.values()) {
            if (anim.name.endsWith("." + suffix)) {
                return anim;
            }
        }
        return null;
    }

    /** Семплирует канал кости в момент t (сек): [x,y,z] или null, если канала нет. */
    @Nullable
    public static float[] sample(@Nullable Anim anim, @Nullable String bone, @Nullable String channel, float t) {
        if (anim == null || bone == null) {
            return null;
        }
        Map<String, Channel> channels = anim.bones.get(bone);
        if (channels == null) {
            return null;
        }
        Channel keys = channels.get(channel);
        if (keys == null || keys.isEmpty()) {
            return null;
        }
        float time = anim.loop && anim.length > 0.001f ? ((t % anim.length) + anim.length) % anim.length : t;
        Map.Entry<Float, float[]> prev = keys.floorEntry(time);
        Map.Entry<Float, float[]> next = keys.ceilingEntry(time);
        if (prev == null && next == null) {
            return null;
        }
        if (prev == null) {
            float[] v = next.getValue();
            return new float[]{v[0], v[1], v[2]};
        }
        if (next == null || next.getKey().equals(prev.getKey())) {
            float[] v = prev.getValue();
            return new float[]{v[0], v[1], v[2]};
        }
        float span = next.getKey() - prev.getKey();
        float f = span > 1.0E-5f ? (time - prev.getKey()) / span : 0.0f;
        float[] a = prev.getValue();
        float[] b = next.getValue();
        return new float[]{a[0] + (b[0] - a[0]) * f, a[1] + (b[1] - a[1]) * f, a[2] + (b[2] - a[2]) * f};
    }

    private static float num(JsonObject obj, String key, float fallback) {
        try {
            JsonElement el = obj.get(key);
            return el != null && el.isJsonPrimitive() ? el.getAsFloat() : fallback;
        } catch (Exception e) {
            return fallback;
        }
    }

    private static boolean bool(JsonObject obj, String key, boolean fallback) {
        try {
            JsonElement el = obj.get(key);
            return el != null && el.isJsonPrimitive() ? el.getAsBoolean() : fallback;
        } catch (Exception e) {
            return fallback;
        }
    }

    private static String str(JsonObject obj, String key, String fallback) {
        try {
            JsonElement el = obj.get(key);
            return el != null && el.isJsonPrimitive() ? el.getAsString() : fallback;
        } catch (Exception e) {
            return fallback;
        }
    }

    @Nullable
    public static float[] vec3(JsonObject obj, String key) {
        try {
            JsonElement el = obj.get(key);
            if (el == null || !el.isJsonArray() || el.getAsJsonArray().size() < 3) {
                return null;
            }
            JsonArray arr = el.getAsJsonArray();
            return new float[]{arr.get(0).getAsFloat(), arr.get(1).getAsFloat(), arr.get(2).getAsFloat()};
        } catch (Exception e) {
            return null;
        }
    }
}
