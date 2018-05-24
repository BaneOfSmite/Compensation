package me.baneofsmite.compensation;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.bukkit.scoreboard.Team;

public class clistener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Team team = Bukkit.getScoreboardManager().getMainScoreboard().getEntryTeam(player.getName());
        if (team != null) {
            team.removeEntry(player.getName());
            double compensation = player.getMaxHealth() / (double)team.getSize();
            for (String teammate : team.getEntries()) {
            	Player p = Bukkit.getServer().getPlayer(teammate);
                new Compensator(p, compensation).runTaskTimer((Plugin)this, 0, 5);
            }
        }
        player.setMaxHealth(20.0);
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item.getType() == Material.GOLDEN_APPLE) {
            double health = player.getMaxHealth() / 5.0;
            int hearts = (int)health;
            player.setHealth(player.getHealth() + health - (double)hearts);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, hearts * 25, 1), true);
        }
    }
    
}

