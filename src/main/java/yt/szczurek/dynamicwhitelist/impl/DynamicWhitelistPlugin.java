package yt.szczurek.dynamicwhitelist.impl;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import yt.szczurek.dynamicwhitelist.api.WhitelistCheckEvent;

import java.util.EventListener;

public final class DynamicWhitelistPlugin extends JavaPlugin implements EventListener, Listener {

    private Component defaultKickMessage;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        String kickStr = getConfig().getString("defaultKickMessage");
        if (kickStr == null) {
            kickStr = "Please set defaultKickMessage in settings!";
        }
        defaultKickMessage = MiniMessage.miniMessage().deserialize(kickStr);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        WhitelistCheckEvent event = new WhitelistCheckEvent(player, defaultKickMessage);
        if (!event.isAllowed()) {
            joinEvent.joinMessage(Component.empty());
            player.kick(event.getKickMessage());
        }
    }


}
