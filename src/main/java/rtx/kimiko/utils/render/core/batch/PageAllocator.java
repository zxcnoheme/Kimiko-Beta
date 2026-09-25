/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 */
package rtx.kimiko.utils.render.core.batch;

import kotlin.Metadata;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0014\u0010\bR\u0017\u0010\u0015\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\bR\u0016\u0010\u0017\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0012\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/core/batch/PageAllocator;", "", "", "pageSize", "maxPages", "<init>", "(II)V", "next", "()I", "used", "", "reset", "()V", "slot", "pageOf", "(I)I", "offsetInPage", "pagesUsed", "I", "getPageSize", "getMaxPages", "capacity", "getCapacity", "cursor", "rtx.kimiko:kimiko"})
public final class PageAllocator {
    private final int pageSize;
    private final int maxPages;
    private final int capacity;
    private int cursor;

    public PageAllocator(int pageSize, int maxPages) {
        this.pageSize = pageSize;
        this.maxPages = maxPages;
        this.capacity = this.pageSize * this.maxPages;
    }

    public final int getPageSize() {
        return this.pageSize;
    }

    public final int getMaxPages() {
        return this.maxPages;
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public final int next() {
        if (this.cursor >= this.capacity) {
            return -1;
        }
        int n = this.cursor;
        this.cursor = n + 1;
        return n;
    }

    public final int used() {
        return this.cursor;
    }

    public final void reset() {
        this.cursor = 0;
    }

    public final int pageOf(int slot) {
        return slot / this.pageSize;
    }

    public final int offsetInPage(int slot) {
        return slot % this.pageSize;
    }

    public final int pagesUsed() {
        return this.cursor <= 0 ? 0 : (this.cursor - 1) / this.pageSize + 1;
    }
}

