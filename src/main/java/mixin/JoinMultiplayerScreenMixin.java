/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.text.Text
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.gui.widget.EntryListWidget$Entry
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.widget.ButtonWidget
 *  net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget
 *  net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget$ServerEntry
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen
 *  net.minecraft.client.network.ServerInfo
 *  net.minecraft.client.network.ServerInfo$ServerType
 *  net.minecraft.client.gui.tooltip.Tooltip
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.utils.network.PinnedServers;

@Mixin(value={MultiplayerScreen.class})
public abstract class JoinMultiplayerScreenMixin
extends Screen {
    @Shadow
    protected MultiplayerServerListWidget serverListWidget;
    @Shadow
    private ButtonWidget buttonEdit;
    @Shadow
    private ButtonWidget buttonDelete;

    protected JoinMultiplayerScreenMixin(Text title) {
        super(title);
    }

    @Inject(method={"updateButtonActivationStates"}, at={@At(value="TAIL")})
    private void kimiko$protectPinnedServerControls(CallbackInfo ci) {
        MultiplayerServerListWidget.Entry entry2 = this.serverListWidget.getSelectedOrNull();
        if (entry2 instanceof MultiplayerServerListWidget.ServerEntry) {
            MultiplayerServerListWidget.ServerEntry entry = (MultiplayerServerListWidget.ServerEntry)entry2;
            if (PinnedServers.isPinned(entry.getServer().address)) {
                this.buttonEdit.active = false;
                this.buttonDelete.active = false;
            }
        }
    }
}

