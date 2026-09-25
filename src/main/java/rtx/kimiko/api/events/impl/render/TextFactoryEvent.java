/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.impl.render;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.Event;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\t\u0010\u0005J!\u0010\f\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\f\u0010\rR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/events/impl/render/TextFactoryEvent;", "Lrtx/kimiko/api/events/Event;", "", "text", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "", "setText", "target", "replacement", "replaceText", "(Ljava/lang/String;Ljava/lang/String;)V", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
public final class TextFactoryEvent
extends Event {
    @NotNull
    private String text;

    public TextFactoryEvent(@Nullable String text) {
        String string = text;
        if (string == null) {
            string = "";
        }
        this.text = string;
    }

    @NotNull
    public final String getText() {
        return this.text;
    }

    public final void setText(@Nullable String text) {
        String string = text;
        if (string == null) {
            string = "";
        }
        this.text = string;
    }

    public final void replaceText(@Nullable String target, @Nullable String replacement) {
        CharSequence charSequence = target;
        if (charSequence == null || charSequence.length() == 0 || replacement == null) {
            return;
        }
        this.text = String.valueOf(this.text).replace(target, replacement);
    }
}

