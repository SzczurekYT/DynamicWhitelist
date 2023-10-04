package yt.szczurek.dynamicwhitelist.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class WhitelistCheckEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean allowed;
    private Component kickMessage;
    private final Player player;

    public WhitelistCheckEvent(Player player, Component defaultKickMessage) {
        this.allowed = false;
        this.kickMessage = defaultKickMessage;
        this.player = player;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public Component getKickMessage() {
        return kickMessage;
    }

    public Player getPlayer() {
        return player;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public void setKickMessage(Component kickMessage) {
        this.kickMessage = kickMessage;
    }

    public void setKickMessage(String kickMessage) {
        this.kickMessage = MiniMessage.miniMessage().deserialize(kickMessage);
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
