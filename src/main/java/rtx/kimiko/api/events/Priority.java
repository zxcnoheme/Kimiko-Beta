/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import sigil.protect.Level;
import sigil.protect.Protect;

@Protect(value=Level.OFF)
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00ca\u0001\u000e\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/events/Priority;", "", "<init>", "(Ljava/lang/String;I)V", "HIGHEST", "HIGH", "NORMAL", "LOW", "LOWEST", "MONITOR", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "OFF", "rtx.kimiko:kimiko"})
public enum Priority {
    HIGHEST,
    HIGH,
    NORMAL,
    LOW,
    LOWEST,
    MONITOR;

    @NotNull
    public static EnumEntries<Priority> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    
}

