/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\b\u001a\u00020\u0002H\u0096\u0080\u0004\u00a2\u0006\u0004\b\b\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/Category;", "", "", "displayName", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "toString", "Ljava/lang/String;", "VISUALS", "DISPLAY", "UTILS", "EVENTS", "CONFIGS", "THEMES", "rtx.kimiko:kimiko"})
public enum Category {
    VISUALS("Visuals"),
    DISPLAY("Display"),
    UTILS("Utils"),
    EVENTS("Events"),
    CONFIGS("Configs"),
    THEMES("Themes");

    @NotNull
    private final String displayName;
    Category(String displayName) {
        this.displayName = displayName;
    }

    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }

    @NotNull
    public String toString() {
        return this.displayName;
    }

    @NotNull
    public static EnumEntries<Category> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    
}

