/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.funtime;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00060\u0001j\u0002`\u0002B#\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\nJ\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0010R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeApiException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "status", "", "type", "message", "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "()I", "()Ljava/lang/String;", "", "isRateLimited", "()Z", "isUnauthorized", "I", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
public final class FunTimeApiException
extends Exception {
    private final int status;
    @NotNull
    private final String type;

    public FunTimeApiException(int status, @Nullable String type, @Nullable String message) {
        super(message);
        this.status = status;
        String string = type;
        if (string == null) {
            string = "";
        }
        this.type = string;
    }

    public final int status() {
        return this.status;
    }

    @NotNull
    public final String type() {
        return this.type;
    }

    public final boolean isRateLimited() {
        return this.status == 402 || this.status == 429;
    }

    public final boolean isUnauthorized() {
        return this.status == 401;
    }
}

