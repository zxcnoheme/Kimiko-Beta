/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.option.ServerList
 *  net.minecraft.client.network.ServerInfo
 *  net.minecraft.client.network.ServerInfo$ServerType
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import java.util.Iterator;
import java.util.List;
import net.minecraft.client.option.ServerList;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.utils.network.PinnedServers;

@Mixin(value={ServerList.class})
public abstract class ServerListPinnedServerMixin {
    @Shadow
    @Final
    private List<ServerInfo> servers;
    @Shadow
    @Final
    private List<ServerInfo> hiddenServers;

    @Inject(method={"loadFile"}, at={@At(value="RETURN")})
    private void kimiko$pinPartnerServers(CallbackInfo ci) {
        boolean ordered;
        int index;
        ServerInfo server;
        ServerInfo[] pinned = new ServerInfo[PinnedServers.ADDRESSES.length];
        boolean changed = false;
        Iterator<ServerInfo> iterator = this.servers.iterator();
        while (iterator.hasNext()) {
            server = iterator.next();
            index = PinnedServers.indexOf(server.address);
            if (index < 0) continue;
            if (pinned[index] == null) {
                pinned[index] = server;
                continue;
            }
            iterator.remove();
            changed = true;
        }
        iterator = this.hiddenServers.iterator();
        while (iterator.hasNext()) {
            server = iterator.next();
            index = PinnedServers.indexOf(server.address);
            if (index < 0) continue;
            iterator.remove();
            if (pinned[index] == null) {
                pinned[index] = server;
            }
            changed = true;
        }
        for (int i = 0; i < pinned.length; ++i) {
            String name = PinnedServers.NAMES[i];
            String address = PinnedServers.ADDRESSES[i];
            if (pinned[i] == null) {
                pinned[i] = new ServerInfo(name, address, ServerInfo.ServerType.OTHER);
                changed = true;
            }
            if (name.equals(pinned[i].name) && address.equals(pinned[i].address)) continue;
            pinned[i].name = name;
            pinned[i].address = address;
            changed = true;
        }
        boolean bl = ordered = this.servers.size() >= pinned.length;
        if (ordered) {
            for (int i = 0; i < pinned.length; ++i) {
                if (this.servers.get(i) == pinned[i]) continue;
                ordered = false;
                break;
            }
        }
        if (!ordered) {
            for (ServerInfo server2 : pinned) {
                this.servers.remove(server2);
            }
            for (int i = pinned.length - 1; i >= 0; --i) {
                this.servers.addFirst(pinned[i]);
            }
            changed = true;
        }
        if (changed) {
            ((ServerList)(Object)this).saveFile();
        }
    }

    @Inject(method={"add"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$preventDuplicate(ServerInfo server, boolean hidden, CallbackInfo ci) {
        int index = PinnedServers.indexOf(server.address);
        if (index < 0) {
            return;
        }
        for (ServerInfo existing : this.servers) {
            if (PinnedServers.indexOf(existing.address) != index) continue;
            ci.cancel();
            return;
        }
    }

    @Inject(method={"remove"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$preventRemoval(ServerInfo server, CallbackInfo ci) {
        if (PinnedServers.isPinned(server.address)) {
            ci.cancel();
        }
    }

    @Inject(method={"swapEntries"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$preventMove(int first, int second, CallbackInfo ci) {
        if (PinnedServers.isPinned(this.servers.get((int)first).address) || PinnedServers.isPinned(this.servers.get((int)second).address)) {
            ci.cancel();
        }
    }

    @Inject(method={"set"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$preventEdit(int index, ServerInfo replacement, CallbackInfo ci) {
        if (PinnedServers.isPinned(this.servers.get((int)index).address)) {
            ci.cancel();
        }
    }
}

