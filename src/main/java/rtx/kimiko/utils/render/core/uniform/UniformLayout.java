/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.core.uniform;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 #2\u00020\u0001:\u0004$%&#B'\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u001d\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\u000fJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001aR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b!\u0010\"\u00a8\u0006'"}, d2={"Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "", "", "structName", "", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout$Field;", "fields", "", "strideBytes", "<init>", "(Ljava/lang/String;Ljava/util/List;I)V", "fieldName", "offsetOf", "(Ljava/lang/String;)I", "index", "(Ljava/lang/String;I)I", "Ljava/nio/ByteBuffer;", "buffer", "elementIndex", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "(Ljava/nio/ByteBuffer;I)Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "elementCount", "byteSize", "(I)I", "toGlslStruct", "()Ljava/lang/String;", "Ljava/lang/String;", "getStructName", "Ljava/util/List;", "getFields", "()Ljava/util/List;", "I", "getStrideBytes", "()I", "Companion", "Field", "FieldType", "Builder", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nUniformLayout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UniformLayout.kt\nrtx/kimiko/utils/render/core/uniform/UniformLayout\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,141:1\n296#2,2:142\n*S KotlinDebug\n*F\n+ 1 UniformLayout.kt\nrtx/kimiko/utils/render/core/uniform/UniformLayout\n*L\n105#1:142,2\n*E\n"})
public final class UniformLayout {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String structName;
    @NotNull
    private final List<Field> fields;
    private final int strideBytes;

    private UniformLayout(String structName, List<Field> fields, int strideBytes) {
        this.structName = structName;
        this.fields = fields;
        this.strideBytes = strideBytes;
    }

    @NotNull
    public final String getStructName() {
        return this.structName;
    }

    @NotNull
    public final List<Field> getFields() {
        return this.fields;
    }

    public final int getStrideBytes() {
        return this.strideBytes;
    }

    public final int offsetOf(@NotNull String fieldName) {
        Intrinsics.checkNotNullParameter((Object)fieldName, (String)"fieldName");
        for (Field field : this.fields) {
            if (Intrinsics.areEqual(field.getName(), fieldName)) {
                return field.getOffsetBytes();
            }
        }
        throw new IllegalStateException(this.structName + ": unknown field '" + fieldName + "'");
    }

    public final int offsetOf(@NotNull String fieldName, int index) {
        Intrinsics.checkNotNullParameter((Object)fieldName, (String)"fieldName");
        return this.offsetOf(fieldName) + index * 16;
    }

    @NotNull
    public final UniformWriter writer(@NotNull ByteBuffer buffer, int elementIndex) {
        Intrinsics.checkNotNullParameter((Object)buffer, (String)"buffer");
        return new UniformWriter(this, buffer, elementIndex * this.strideBytes);
    }

    public final int byteSize(int elementCount) {
        return elementCount * this.strideBytes;
    }

    @NotNull
    public final String toGlslStruct() {
        StringBuilder stringBuilder;
        StringBuilder $this$toGlslStruct_u24lambda_u240 = stringBuilder = new StringBuilder();
        boolean bl = false;
        $this$toGlslStruct_u24lambda_u240.append("struct ").append(this.structName).append(" {\n");
        for (Field field : this.fields) {
            $this$toGlslStruct_u24lambda_u240.append("    ").append(field.getType().getGlsl()).append(' ').append(field.getName()).append(";\n");
        }
        $this$toGlslStruct_u24lambda_u240.append("};\n");
        return stringBuilder.toString();
    }

    @JvmStatic
    @NotNull
    public static final UniformLayout build(@NotNull String structName, @NotNull Function1<? super Builder, Unit> block) {
        return Companion.build(structName, block);
    }

    public /* synthetic */ UniformLayout(String structName, List fields, int strideBytes, DefaultConstructorMarker $constructor_marker) {
        this(structName, fields, strideBytes);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\bJ\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\bJ\u001d\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J)\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001cR$\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u001e0\u001dj\b\u0012\u0004\u0012\u00020\u001e`\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#\u00a8\u0006$"}, d2={"Lrtx/kimiko/utils/render/core/uniform/UniformLayout$Builder;", "", "", "structName", "<init>", "(Ljava/lang/String;)V", "name", "float", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/core/uniform/UniformLayout$Builder;", "int", "vec2", "vec4", "color", "", "count", "vec4Array", "(Ljava/lang/String;I)Lrtx/kimiko/utils/render/core/uniform/UniformLayout$Builder;", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "build", "()Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout$FieldType;", "type", "add", "(Ljava/lang/String;Lrtx/kimiko/utils/render/core/uniform/UniformLayout$FieldType;I)Lrtx/kimiko/utils/render/core/uniform/UniformLayout$Builder;", "value", "alignment", "align", "(II)I", "Ljava/lang/String;", "Ljava/util/ArrayList;", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout$Field;", "Lkotlin/collections/ArrayList;", "fields", "Ljava/util/ArrayList;", "cursor", "I", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nUniformLayout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UniformLayout.kt\nrtx/kimiko/utils/render/core/uniform/UniformLayout$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,141:1\n1#2:142\n2945#3,3:143\n*S KotlinDebug\n*F\n+ 1 UniformLayout.kt\nrtx/kimiko/utils/render/core/uniform/UniformLayout$Builder\n*L\n89#1:143,3\n*E\n"})
    public static final class Builder {
        @NotNull
        private final String structName;
        @NotNull
        private final ArrayList<Field> fields;
        private int cursor;

        public Builder(@NotNull String structName) {
            Intrinsics.checkNotNullParameter((Object)structName, (String)"structName");
            this.structName = structName;
            this.fields = new ArrayList();
        }

        @NotNull
        public final Builder float_(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            return Builder.add$default(this, name, FieldType.FLOAT, 0, 4, null);
        }

        @NotNull
        public final Builder int_(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            return Builder.add$default(this, name, FieldType.INT, 0, 4, null);
        }

        @NotNull
        public final Builder vec2(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            return Builder.add$default(this, name, FieldType.VEC2, 0, 4, null);
        }

        @NotNull
        public final Builder vec4(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            return Builder.add$default(this, name, FieldType.VEC4, 0, 4, null);
        }

        @NotNull
        public final Builder color(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            return Builder.add$default(this, name, FieldType.COLOR, 0, 4, null);
        }

        @NotNull
        public final Builder vec4Array(@NotNull String name, int count) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            if (!(count > 0)) {
                boolean bl = false;
                String string = this.structName + ": array '" + name + "' must be non-empty";
                throw new IllegalArgumentException(string.toString());
            }
            return this.add(name, FieldType.VEC4, count);
        }

        @NotNull
        public final UniformLayout build() {
            if (!(!((Collection)this.fields).isEmpty())) {
                boolean bl = false;
                String string = this.structName + ": layout is empty";
                throw new IllegalStateException(string.toString());
            }
            return new UniformLayout(this.structName, new ArrayList(this.fields), this.align(this.cursor, 16), null);
        }

        private final Builder add(String name, FieldType type, int count) {
            boolean bl;
            block4: {
                Iterable $this$none$iv = this.fields;
                boolean $i$f$none = false;
                if ($this$none$iv instanceof Collection && ((Collection)$this$none$iv).isEmpty()) {
                    bl = true;
                } else {
                    for (Object element$iv : $this$none$iv) {
                        Field it = (Field)element$iv;
                        boolean bl2 = false;
                        if (!Intrinsics.areEqual((Object)it.getName(), (Object)name)) continue;
                        bl = false;
                        break block4;
                    }
                    bl = true;
                }
            }
            if (!bl) {
                boolean bl3 = false;
                String string = this.structName + ": duplicate field '" + name + "'";
                throw new IllegalArgumentException(string.toString());
            }
            int offset = this.align(this.cursor, type.getAlignment());
            this.fields.add(new Field(name, type, offset, count));
            this.cursor = offset + type.getBytes() * count;
            return this;
        }

        static /* synthetic */ Builder add$default(Builder builder, String string, FieldType fieldType, int n, int n2, Object object) {
            if ((n2 & 4) != 0) {
                n = 1;
            }
            return builder.add(string, fieldType, n);
        }

        private final int align(int value, int alignment) {
            return (value + alignment - 1) / alignment * alignment;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J4\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\b\tH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/utils/render/core/uniform/UniformLayout.Companion;", "", "<init>", "()V", "", "structName", "Lkotlin/Function1;", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout$Builder;", "", "Lkotlin/ExtensionFunctionType;", "block", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "Lkotlin/jvm/JvmStatic;", "build", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final UniformLayout build(@NotNull String structName, @NotNull Function1<? super Builder, Unit> block) {
            Intrinsics.checkNotNullParameter((Object)structName, (String)"structName");
            Intrinsics.checkNotNullParameter(block, (String)"block");
            Builder builder = new Builder(structName);
            block.invoke(builder);
            return builder.build();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/core/uniform/UniformLayout$Field;", "", "", "name", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout$FieldType;", "type", "", "offsetBytes", "count", "<init>", "(Ljava/lang/String;Lrtx/kimiko/utils/render/core/uniform/UniformLayout$FieldType;II)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout$FieldType;", "getType", "()Lrtx/kimiko/utils/render/core/uniform/UniformLayout$FieldType;", "I", "getOffsetBytes", "()I", "getCount", "rtx.kimiko:kimiko"})
    public static final class Field {
        @NotNull
        private final String name;
        @NotNull
        private final FieldType type;
        private final int offsetBytes;
        private final int count;

        public Field(@NotNull String name, @NotNull FieldType type, int offsetBytes, int count) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            this.name = name;
            this.type = type;
            this.offsetBytes = offsetBytes;
            this.count = count;
        }

        public /* synthetic */ Field(String string, FieldType fieldType, int n, int n2, int n3, DefaultConstructorMarker defaultConstructorMarker) {
            this(string, fieldType, n, ((n3 & 8) != 0 ? 1 : n2));
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final FieldType getType() {
            return this.type;
        }

        public final int getOffsetBytes() {
            return this.offsetBytes;
        }

        public final int getCount() {
            return this.count;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u000e\u0010\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/core/uniform/UniformLayout$FieldType;", "", "", "bytes", "alignment", "", "glsl", "<init>", "(Ljava/lang/String;IIILjava/lang/String;)V", "I", "getBytes", "()I", "getAlignment", "Ljava/lang/String;", "getGlsl", "()Ljava/lang/String;", "FLOAT", "INT", "VEC2", "VEC4", "COLOR", "rtx.kimiko:kimiko"})
    public static enum FieldType {
        FLOAT(4, 4, "float"),
        INT(4, 4, "int"),
        VEC2(8, 8, "vec2"),
        VEC4(16, 16, "vec4"),
        COLOR(16, 16, "vec4");
private final int bytes;
        private final int alignment;
        @NotNull
        private final String glsl;
        
        
        
        
        
        
        private FieldType(int bytes, int alignment, String glsl) {
            this.bytes = bytes;
            this.alignment = alignment;
            this.glsl = glsl;
        }

        public final int getBytes() {
            return this.bytes;
        }

        public final int getAlignment() {
            return this.alignment;
        }

        @NotNull
        public final String getGlsl() {
            return this.glsl;
        }

        

        

        @NotNull
        public static EnumEntries<FieldType> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

