package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;

/**
 * Статическая геометрия косметики для 3D-превью в GUI.
 * Парсит Bedrock geo.json (per-face UV и box UV) и Blockbench .bbmodel (кубы и меши),
 * а также принимает рукотворные кубы (шляпа, перья). Рендер — те же конвенции вершин,
 * что у игровых рендереров (raw UV px, v сверху вниз).
 */
public final class PreviewModel {
    /** Один текстурированный квад: 4 вершины (x,y,z), UV (u,v), нормаль. */
    public static final class Quad {
        public final float[] x = new float[4];
        public final float[] y = new float[4];
        public final float[] z = new float[4];
        public final float[] u = new float[4];
        public final float[] v = new float[4];
        public float nx;
        public float ny;
        public float nz;
    }

    public final List<Quad> quads = new ArrayList<>();
    public final Identifier texture;
    public float texWidth;
    public float texHeight;

    public float minX = Float.MAX_VALUE;
    public float minY = Float.MAX_VALUE;
    public float minZ = Float.MAX_VALUE;
    public float maxX = -Float.MAX_VALUE;
    public float maxY = -Float.MAX_VALUE;
    public float maxZ = -Float.MAX_VALUE;

    public PreviewModel(Identifier texture, float texWidth, float texHeight) {
        this.texture = texture;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
    }

    public float centerX() {
        return (this.minX + this.maxX) * 0.5f;
    }

    public float centerY() {
        return (this.minY + this.maxY) * 0.5f;
    }

    public float centerZ() {
        return (this.minZ + this.maxZ) * 0.5f;
    }

    public float maxDim() {
        return Math.max(this.maxX - this.minX, Math.max(this.maxY - this.minY, this.maxZ - this.minZ));
    }

    /* ========================= Парсинг geo.json ========================= */

    @Nullable
    public static PreviewModel parseGeoJson(Identifier modelId, Identifier texture, float texW, float texH) {
        try {
            com.google.gson.JsonObject root;
            try (BufferedReader reader = openReader(modelId)) {
                root = JsonParser.parseReader(reader).getAsJsonObject();
            }
            JsonArray geometries = root.getAsJsonArray("minecraft:geometry");
            if (geometries == null || geometries.size() == 0) {
                return null;
            }
            JsonObject geo = geometries.get(0).getAsJsonObject();
            JsonObject desc = geo.getAsJsonObject("description");
            float tw = texW > 0.0f ? texW : readFloat(desc, "texture_width", 64.0f);
            float th = texH > 0.0f ? texH : readFloat(desc, "texture_height", 64.0f);
            PreviewModel model = new PreviewModel(texture, tw, th);
            JsonArray bonesArr = geo.getAsJsonArray("bones");
            if (bonesArr == null) {
                return null;
            }
            Map<String, Bone> byName = new HashMap<>();
            List<Bone> order = new ArrayList<>();
            for (JsonElement el : bonesArr) {
                JsonObject b = el.getAsJsonObject();
                Bone bone = new Bone();
                bone.name = str(b, "name", "");
                bone.parent = str(b, "parent", null);
                bone.pivot = vec3(b, "pivot");
                bone.rotation = vec3(b, "rotation");
                JsonArray cubesArr = b.getAsJsonArray("cubes");
                if (cubesArr != null) {
                    for (JsonElement ce : cubesArr) {
                        bone.cubes.add(ce.getAsJsonObject());
                    }
                }
                byName.put(bone.name, bone);
                order.add(bone);
            }
            for (Bone bone : order) {
                Bone parent = bone.parent != null ? byName.get(bone.parent) : null;
                if (parent != null) {
                    parent.children.add(bone);
                }
            }
            for (Bone bone : order) {
                Bone parent = bone.parent != null ? byName.get(bone.parent) : null;
                if (parent == null) {
                    model.renderBone(new MatrixStack(), bone, byName);
                }
            }
            model.updateBounds();
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    private static final class Bone {
        String name;
        String parent;
        float[] pivot;
        float[] rotation;
        final List<JsonObject> cubes = new ArrayList<>();
        final List<Bone> children = new ArrayList<>();
    }

    private void renderBone(MatrixStack stack, Bone bone, Map<String, Bone> byName) {
        stack.push();
        float px = bone.pivot != null ? bone.pivot[0] : 0.0f;
        float py = bone.pivot != null ? bone.pivot[1] : 0.0f;
        float pz = bone.pivot != null ? bone.pivot[2] : 0.0f;
        stack.translate(px, py, pz);
        if (bone.rotation != null) {
            stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(bone.rotation[2]));
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(bone.rotation[0]));
            stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(bone.rotation[1]));
        }
        stack.translate(-px, -py, -pz);
        for (JsonObject cubeJson : bone.cubes) {
            stack.push();
            float[] cp = vec3(cubeJson, "pivot");
            float[] cr = vec3(cubeJson, "rotation");
            if (cp != null && cr != null) {
                // T(pivot)·R·T(−pivot): вращение вокруг пивота куба, а не вокруг центра модели
                stack.translate(cp[0], cp[1], cp[2]);
                stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(cr[2]));
                stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(cr[0]));
                stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(cr[1]));
                stack.translate(-cp[0], -cp[1], -cp[2]);
            }
            this.addGeoCube(cubeJson);
            stack.pop();
        }
        for (Bone child : bone.children) {
            this.renderBone(stack, child, byName);
        }
        stack.pop();
    }

    private void addGeoCube(JsonObject cube) {
        float[] origin = vec3(cube, "origin");
        float[] size = vec3(cube, "size");
        if (origin == null || size == null) {
            return;
        }
        float inflate = readFloat(cube, "inflate", 0.0f);
        float x1 = origin[0] - inflate;
        float y1 = origin[1] - inflate;
        float z1 = origin[2] - inflate;
        float x2 = origin[0] + size[0] + inflate;
        float y2 = origin[1] + size[1] + inflate;
        float z2 = origin[2] + size[2] + inflate;
        JsonElement uvEl = cube.get("uv");
        if (uvEl != null && uvEl.isJsonArray()) {
            // box uv: [u, v]
            JsonArray arr = uvEl.getAsJsonArray();
            float u = arr.get(0).getAsFloat();
            float v = arr.get(1).getAsFloat();
            float sx = size[0];
            float sy = size[1];
            float sz = size[2];
            this.addFaceRect("north", x1, y1, z1, x2, y2, z2, u + sz, v + sz, u + sz + sx, v + sz + sy);
            this.addFaceRect("south", x1, y1, z1, x2, y2, z2, u + sz + sx + sz, v + sz, u + 2.0f * (sz + sx), v + sz + sy);
            this.addFaceRect("east", x1, y1, z1, x2, y2, z2, u, v + sz, u + sz, v + sz + sy);
            this.addFaceRect("west", x1, y1, z1, x2, y2, z2, u + sz + sx, v + sz, u + sz + sx + sz, v + sz + sy);
            this.addFaceRect("up", x1, y1, z1, x2, y2, z2, u + sz, v, u + sz + sx, v + sz);
            this.addFaceRect("down", x1, y1, z1, x2, y2, z2, u + sz + sx, v, u + sz + sx + sx, v + sz);
        } else if (uvEl != null && uvEl.isJsonObject()) {
            JsonObject uvObj = uvEl.getAsJsonObject();
            for (Map.Entry<String, JsonElement> e : uvObj.entrySet()) {
                JsonObject face = e.getValue().getAsJsonObject();
                JsonArray uvArr = face.getAsJsonArray("uv");
                JsonArray sizeArr = face.getAsJsonArray("uv_size");
                if (uvArr == null || sizeArr == null) {
                    continue;
                }
                float u0 = uvArr.get(0).getAsFloat();
                float v0 = uvArr.get(1).getAsFloat();
                float u1 = u0 + sizeArr.get(0).getAsFloat();
                float v1 = v0 + sizeArr.get(1).getAsFloat();
                this.addFaceRect(e.getKey(), x1, y1, z1, x2, y2, z2, u0, v0, u1, v1);
            }
        }
    }

    /* ========================= Парсинг bbmodel ========================= */

    @Nullable
    public static PreviewModel parseBbModel(Identifier modelId, Identifier texture, float texW, float texH) {
        try {
            JsonObject root;
            try (BufferedReader reader = openReader(modelId)) {
                root = JsonParser.parseReader(reader).getAsJsonObject();
            }
            JsonObject res = root.getAsJsonObject("resolution");
            float tw = res != null ? readFloat(res, "width", texW) : texW;
            float th = res != null ? readFloat(res, "height", texH) : texH;
            if (tw <= 0.0f) {
                tw = 64.0f;
            }
            if (th <= 0.0f) {
                th = 64.0f;
            }
            PreviewModel model = new PreviewModel(texture, tw, th);
            // 1) элементы-кубы и меши (bbmodel: элементы бывают кубами from/to и мешами vertices/faces)
            JsonElement elements = root.get("elements");
            if (elements != null && elements.isJsonArray()) {
                for (JsonElement el : elements.getAsJsonArray()) {
                    JsonObject e = el.getAsJsonObject();
                    if (e.has("vertices") && e.has("faces")) {
                        continue; // меш — обработается ниже
                    }
                    JsonArray from = e.getAsJsonArray("from");
                    JsonArray to = e.getAsJsonArray("to");
                    JsonObject faces = e.getAsJsonObject("faces");
                    if (from == null || to == null || faces == null) {
                        continue;
                    }
                    model.addBbCube(from, to, faces);
                }
            }
            // 2) меши (vertices + faces): из elements (type=mesh) и из outliner
            Map<String, float[]> meshPositions = new HashMap<>();
            List<JsonObject> meshes = new ArrayList<>();
            if (elements != null && elements.isJsonArray()) {
                for (JsonElement el : elements.getAsJsonArray()) {
                    JsonObject e = el.getAsJsonObject();
                    if (e.has("vertices") && e.has("faces")) {
                        meshes.add(e);
                    }
                }
            }
            collectMeshes(root.get("outliner"), meshes);
            if (meshes.isEmpty() && elements != null && elements.isJsonObject()) {
                JsonObject els = elements.getAsJsonObject();
                if (els != null) {
                    for (Map.Entry<String, JsonElement> e : els.entrySet()) {
                        JsonObject obj = e.getValue().getAsJsonObject();
                        if (obj.has("vertices") && obj.has("faces")) {
                            meshes.add(obj);
                        }
                    }
                }
            }
            for (JsonObject mesh : meshes) {
                float[] origin = vec3(mesh, "origin");
                JsonObject vertices = mesh.getAsJsonObject("vertices");
                if (vertices == null) {
                    continue;
                }
                meshPositions.clear();
                for (Map.Entry<String, JsonElement> e : vertices.entrySet()) {
                    float[] rel = vec3(e.getValue().getAsJsonArray());
                    if (rel == null) {
                        continue;
                    }
                    float ox = origin != null ? origin[0] : 0.0f;
                    float oy = origin != null ? origin[1] : 0.0f;
                    float oz = origin != null ? origin[2] : 0.0f;
                    meshPositions.put(e.getKey(), new float[]{ox + rel[0], oy + rel[1], oz + rel[2]});
                    model.grow(ox + rel[0], oy + rel[1], oz + rel[2]);
                }
                JsonObject faces = mesh.getAsJsonObject("faces");
                if (faces == null) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> e : faces.entrySet()) {
                    JsonObject face = e.getValue().getAsJsonObject();
                    JsonArray order = face.getAsJsonArray("vertices");
                    JsonObject uv = face.getAsJsonObject("uv");
                    if (order == null || uv == null || order.size() < 3) {
                        continue;
                    }
                    float[] px = new float[4];
                    float[] py = new float[4];
                    float[] pz = new float[4];
                    float[] pu = new float[4];
                    float[] pv = new float[4];
                    boolean ok = true;
                    for (int i = 0; i < 4; ++i) {
                        int idx = Math.min(i, order.size() - 1);
                        String key = order.get(idx).getAsString();
                        float[] pos = meshPositions.get(key);
                        JsonArray vu = uv.getAsJsonArray(key);
                        if (pos == null || vu == null) {
                            ok = false;
                            break;
                        }
                        px[i] = pos[0];
                        py[i] = pos[1];
                        pz[i] = pos[2];
                        pu[i] = vu.get(0).getAsFloat();
                        pv[i] = vu.get(1).getAsFloat();
                    }
                    if (!ok) {
                        continue;
                    }
                    float n1x = px[1] - px[0];
                    float n1y = py[1] - py[0];
                    float n1z = pz[1] - pz[0];
                    float n2x = px[2] - px[0];
                    float n2y = py[2] - py[0];
                    float n2z = pz[2] - pz[0];
                    float nx = n1y * n2z - n1z * n2y;
                    float ny = n1z * n2x - n1x * n2z;
                    float nz = n1x * n2y - n1y * n2x;
                    float len = (float) Math.sqrt(nx * nx + ny * ny + nz * nz);
                    if (len < 1.0E-6f) {
                        nx = 0.0f;
                        ny = 1.0f;
                        nz = 0.0f;
                    } else {
                        nx /= len;
                        ny /= len;
                        nz /= len;
                    }
                    model.addQuad(px, py, pz, pu, pv, nx, ny, nz);
                }
            }
            model.updateBounds();
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    private static void collectMeshes(JsonElement node, List<JsonObject> out) {
        if (node == null) {
            return;
        }
        if (node.isJsonObject()) {
            JsonObject obj = node.getAsJsonObject();
            if (obj.has("vertices") && obj.has("faces")) {
                out.add(obj);
                return;
            }
            JsonElement children = obj.get("children");
            if (children != null && children.isJsonArray()) {
                for (JsonElement c : children.getAsJsonArray()) {
                    collectMeshes(c, out);
                }
            }
            return;
        }
        if (node.isJsonArray()) {
            for (JsonElement c : node.getAsJsonArray()) {
                collectMeshes(c, out);
            }
        }
    }

    /** Куб bbmodel: from/to + faces с uv [x1,y1,x2,y2]. */
    private void addBbCube(JsonArray from, JsonArray to, JsonObject faces) {
        float x0 = Math.min(from.get(0).getAsFloat(), to.get(0).getAsFloat());
        float y0 = Math.min(from.get(1).getAsFloat(), to.get(1).getAsFloat());
        float z0 = Math.min(from.get(2).getAsFloat(), to.get(2).getAsFloat());
        float x1 = Math.max(from.get(0).getAsFloat(), to.get(0).getAsFloat());
        float y1 = Math.max(from.get(1).getAsFloat(), to.get(1).getAsFloat());
        float z1 = Math.max(from.get(2).getAsFloat(), to.get(2).getAsFloat());
        for (String faceName : new String[]{"down", "up", "west", "north", "east", "south"}) {
            JsonObject face = faces.getAsJsonObject(faceName);
            if (face == null || !face.has("texture")) {
                continue;
            }
            JsonArray uv = face.getAsJsonArray("uv");
            float u0 = uv.get(0).getAsFloat();
            float v0 = uv.get(1).getAsFloat();
            float u1 = uv.get(2).getAsFloat();
            float v1 = uv.get(3).getAsFloat();
            this.addFaceRect(faceName, x0, y0, z0, x1, y1, z1, u0, v0, u1, v1);
        }
    }

    /* ========================= Рукотворные кубы ========================= */

    /** Упрощённый куб для превью (координаты y-вверх, UV в px текстуры). */
    public void addCube(float x1, float y1, float z1, float x2, float y2, float z2,
                        float nU0, float nV0, float nU1, float nV1,
                        float sU0, float sV0, float sU1, float sV1,
                        float eU0, float eV0, float eU1, float eV1,
                        float wU0, float wV0, float wU1, float wV1,
                        float uU0, float uV0, float uU1, float uV1,
                        float dU0, float dV0, float dU1, float dV1) {
        this.addFaceRect("north", x1, y1, z1, x2, y2, z2, nU0, nV0, nU1, nV1);
        this.addFaceRect("south", x1, y1, z1, x2, y2, z2, sU0, sV0, sU1, sV1);
        this.addFaceRect("east", x1, y1, z1, x2, y2, z2, eU0, eV0, eU1, eV1);
        this.addFaceRect("west", x1, y1, z1, x2, y2, z2, wU0, wV0, wU1, wV1);
        this.addFaceRect("up", x1, y1, z1, x2, y2, z2, uU0, uV0, uU1, uV1);
        this.addFaceRect("down", x1, y1, z1, x2, y2, z2, dU0, dV0, dU1, dV1);
    }

    /** Плоский квад (для перьев и других силуэтов). */
    public void addFlatQuad(float[][] pts, float u0, float v0, float u1, float v1, float nx, float ny, float nz) {
        Quad q = new Quad();
        for (int i = 0; i < 4; ++i) {
            q.x[i] = pts[i][0];
            q.y[i] = pts[i][1];
            q.z[i] = pts[i][2];
        }
        q.u[0] = u0;
        q.v[0] = v0;
        q.u[1] = u1;
        q.v[1] = v0;
        q.u[2] = u1;
        q.v[2] = v1;
        q.u[3] = u0;
        q.v[3] = v1;
        q.nx = nx;
        q.ny = ny;
        q.nz = nz;
        this.quads.add(q);
    }

    private void addFaceRect(String faceName, float x1, float y1, float z1, float x2, float y2, float z2,
                             float u0, float v0, float u1, float v1) {
        Quad q = new Quad();
        q.u[0] = u1;
        q.v[0] = v0;
        q.u[1] = u0;
        q.v[1] = v0;
        q.u[2] = u0;
        q.v[2] = v1;
        q.u[3] = u1;
        q.v[3] = v1;
        switch (faceName) {
            case "north" -> {
                this.set(q, 0, x2, y2, z1);
                this.set(q, 1, x1, y2, z1);
                this.set(q, 2, x1, y1, z1);
                this.set(q, 3, x2, y1, z1);
                q.nx = 0.0f;
                q.ny = 0.0f;
                q.nz = -1.0f;
            }
            case "south" -> {
                this.set(q, 0, x1, y2, z2);
                this.set(q, 1, x2, y2, z2);
                this.set(q, 2, x2, y1, z2);
                this.set(q, 3, x1, y1, z2);
                q.nx = 0.0f;
                q.ny = 0.0f;
                q.nz = 1.0f;
            }
            case "east" -> {
                this.set(q, 0, x1, y2, z1);
                this.set(q, 1, x1, y2, z2);
                this.set(q, 2, x1, y1, z2);
                this.set(q, 3, x1, y1, z1);
                q.nx = -1.0f;
                q.ny = 0.0f;
                q.nz = 0.0f;
            }
            case "west" -> {
                this.set(q, 0, x2, y2, z2);
                this.set(q, 1, x2, y2, z1);
                this.set(q, 2, x2, y1, z1);
                this.set(q, 3, x2, y1, z2);
                q.nx = 1.0f;
                q.ny = 0.0f;
                q.nz = 0.0f;
            }
            case "up" -> {
                this.set(q, 0, x2, y2, z2);
                this.set(q, 1, x1, y2, z2);
                this.set(q, 2, x1, y2, z1);
                this.set(q, 3, x2, y2, z1);
                q.nx = 0.0f;
                q.ny = 1.0f;
                q.nz = 0.0f;
            }
            case "down" -> {
                this.set(q, 0, x2, y1, z1);
                this.set(q, 1, x1, y1, z1);
                this.set(q, 2, x1, y1, z2);
                this.set(q, 3, x2, y1, z2);
                q.nx = 0.0f;
                q.ny = -1.0f;
                q.nz = 0.0f;
            }
            default -> {
                return;
            }
        }
        this.quads.add(q);
        this.grow(x1, y1, z1);
        this.grow(x2, y2, z2);
    }

    private void set(Quad q, int i, float x, float y, float z) {
        q.x[i] = x;
        q.y[i] = y;
        q.z[i] = z;
    }

    private void addQuad(float[] px, float[] py, float[] pz, float[] pu, float[] pv, float nx, float ny, float nz) {
        Quad q = new Quad();
        for (int i = 0; i < 4; ++i) {
            q.x[i] = px[i];
            q.y[i] = py[i];
            q.z[i] = pz[i];
            q.u[i] = pu[i];
            q.v[i] = pv[i];
        }
        q.nx = nx;
        q.ny = ny;
        q.nz = nz;
        this.quads.add(q);
    }

    private void grow(float x, float y, float z) {
        this.minX = Math.min(this.minX, x);
        this.minY = Math.min(this.minY, y);
        this.minZ = Math.min(this.minZ, z);
        this.maxX = Math.max(this.maxX, x);
        this.maxY = Math.max(this.maxY, y);
        this.maxZ = Math.max(this.maxZ, z);
    }

    private void updateBounds() {
        if (this.quads.isEmpty()) {
            this.minX = this.minY = this.minZ = 0.0f;
            this.maxX = this.maxY = this.maxZ = 0.0f;
            return;
        }
        this.minX = this.minY = this.minZ = Float.MAX_VALUE;
        this.maxX = this.maxY = this.maxZ = -Float.MAX_VALUE;
        for (Quad q : this.quads) {
            for (int i = 0; i < 4; ++i) {
                this.grow(q.x[i], q.y[i], q.z[i]);
            }
        }
    }

    /** Пересчитать границы после ручного добавления квадов. */
    public void updateBoundsManual() {
        this.updateBounds();
    }

    /* ========================= Парсинг BedrockModel (внешние косметики) ========================= */

    /** Превью из уже распарсенного BedrockModel; dyPx — сдвиг вверх в px, extraScale — множитель. */
    @Nullable
    public static PreviewModel fromBedrock(BedrockModel model, Identifier texture, float dyPx, float extraScale) {
        try {
            PreviewModel out = new PreviewModel(texture, model.textureWidth, model.textureHeight);
            MatrixStack stack = new MatrixStack();
            if (extraScale != 1.0f && extraScale > 0.0f) {
                stack.scale(extraScale, extraScale, extraScale);
            }
            stack.translate(0.0f, dyPx, 0.0f);
            for (BedrockModel.Bone root : model.rootBones) {
                renderBedrockBone(out, stack, root, model.bonesByName);
            }
            out.updateBounds();
            return out.quads.isEmpty() ? null : out;
        } catch (Exception e) {
            return null;
        }
    }

    private static void renderBedrockBone(PreviewModel out, MatrixStack stack, BedrockModel.Bone bone,
                                          Map<String, BedrockModel.Bone> byName) {
        stack.push();
        float px = bone.pivot != null ? bone.pivot[0] : 0.0f;
        float py = bone.pivot != null ? bone.pivot[1] : 0.0f;
        float pz = bone.pivot != null ? bone.pivot[2] : 0.0f;
        stack.translate(px, py, pz);
        if (bone.rotation != null) {
            stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(bone.rotation[2]));
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(bone.rotation[0]));
            stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(bone.rotation[1]));
        }
        stack.translate(-px, -py, -pz);
        for (BedrockModel.Cube cube : bone.cubes) {
            stack.push();
            if (cube.pivot != null && cube.rotation != null) {
                // T(pivot)·R·T(−pivot): вращение вокруг пивота куба, а не вокруг центра модели
                stack.translate(cube.pivot[0], cube.pivot[1], cube.pivot[2]);
                stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(cube.rotation[2]));
                stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(cube.rotation[0]));
                stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(cube.rotation[1]));
                stack.translate(-cube.pivot[0], -cube.pivot[1], -cube.pivot[2]);
            }
            addBedrockCube(out, cube);
            stack.pop();
        }
        for (BedrockModel.Bone child : bone.children) {
            renderBedrockBone(out, stack, child, byName);
        }
        stack.pop();
    }

    private static void addBedrockCube(PreviewModel out, BedrockModel.Cube cube) {
        float inf = cube.inflate;
        float x1 = cube.origin[0] - inf;
        float y1 = cube.origin[1] - inf;
        float z1 = cube.origin[2] - inf;
        float x2 = cube.origin[0] + cube.size[0] + inf;
        float y2 = cube.origin[1] + cube.size[1] + inf;
        float z2 = cube.origin[2] + cube.size[2] + inf;
        for (Map.Entry<String, float[]> e : cube.faceUv.entrySet()) {
            float[] uv = e.getValue();
            float[] sz = cube.faceUvSize.get(e.getKey());
            float w = sz != null ? sz[0] : 0.0f;
            float h = sz != null ? sz[1] : 0.0f;
            out.addFaceRect(e.getKey(), x1, y1, z1, x2, y2, z2, uv[0], uv[1], uv[0] + w, uv[1] + h);
        }
    }

    /* ========================= Рендер ========================= */

    /** Рисует модель: поворот по Y (облёт) уже применён снаружи; координаты — модельные. */
    public void render(MatrixStack.Entry pose, VertexConsumer buffer, int light, float alpha) {
        int a = Math.max(0, Math.min(255, (int) (alpha * 255.0f)));
        for (Quad q : this.quads) {
            float shade = this.faceShade(q);
            int g = (int) (255.0f * shade);
            int color = (a << 24 | g << 16 | g << 8 | g);
            for (int i = 0; i < 4; ++i) {
                buffer.vertex(pose, q.x[i], q.y[i], q.z[i])
                        .color(color)
                        .texture(q.u[i] / this.texWidth, q.v[i] / this.texHeight)
                        .overlay(OverlayTexture.DEFAULT_UV)
                        .light(light)
                        .normal(pose, q.nx, q.ny, q.nz);
            }
        }
    }

    private float faceShade(Quad q) {
        float ax = Math.abs(q.nx);
        float ay = Math.abs(q.ny);
        float az = Math.abs(q.nz);
        if (ay > 0.5f) {
            return q.ny > 0.0f ? 1.0f : 0.72f;
        }
        if (ax > 0.5f) {
            return 0.82f;
        }
        return 0.9f;
    }

    /* ========================= Утилиты ========================= */

    private static BufferedReader openReader(Identifier id) throws Exception {
        var resource = net.minecraft.client.MinecraftClient.getInstance().getResourceManager().getResource(id).orElseThrow();
        return new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
    }

    private static float readFloat(JsonObject obj, String key, float fallback) {
        if (obj == null) {
            return fallback;
        }
        try {
            JsonElement el = obj.get(key);
            return el != null && el.isJsonPrimitive() ? el.getAsFloat() : fallback;
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
    private static float[] vec3(JsonObject obj, String key) {
        try {
            JsonElement el = obj.get(key);
            if (el == null || !el.isJsonArray()) {
                return null;
            }
            return vec3(el.getAsJsonArray());
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private static float[] vec3(JsonArray arr) {
        if (arr == null || arr.size() < 3) {
            return null;
        }
        try {
            return new float[]{arr.get(0).getAsFloat(), arr.get(1).getAsFloat(), arr.get(2).getAsFloat()};
        } catch (Exception e) {
            return null;
        }
    }
}
