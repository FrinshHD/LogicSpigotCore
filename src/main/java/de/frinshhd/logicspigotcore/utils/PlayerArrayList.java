package de.frinshhd.logicspigotcore.utils;

import de.frinshhd.logicspigotcore.LogicSpigotCoreMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class PlayerArrayList<K> extends ArrayList<K> implements Listener {


    public PlayerArrayList() {
        LogicSpigotCoreMain.getInstance().getServer().getPluginManager().registerEvents(this, LogicSpigotCoreMain.getInstance());
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        this.remove(player.getUniqueId());
    }

    @Override
    public boolean add(K k) {
        return super.add(k);
    }
}
