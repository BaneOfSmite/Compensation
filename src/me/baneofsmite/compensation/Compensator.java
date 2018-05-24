package me.baneofsmite.compensation;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Compensator extends BukkitRunnable {
	private OfflinePlayer player;
	private double compensation;
	private int hearts;

	public Compensator(OfflinePlayer player, double compensation) {
		this.player = player;
		this.compensation = compensation;
		this.hearts = (int) compensation;
	}

	public void run() {
		if (this.player.isOnline()) {
			if (Main.getCompensationv()) {
				Player p = this.player.getPlayer();
				p.setMaxHealth(p.getMaxHealth() + this.compensation);
				p.setHealth(p.getHealth() + this.compensation - (double) this.hearts);
				p.addPotionEffect(
						new PotionEffect(PotionEffectType.REGENERATION, this.getDuration(p) + this.hearts * 50, 0),
						true);
				this.cancel();
			}
		}
	}

	private int getDuration(Player player) {
		if (Main.getCompensationv()) {
			for (PotionEffect effect : player.getActivePotionEffects()) {
				if (!effect.getType().equals((Object) PotionEffectType.REGENERATION))
					continue;
				return effect.getDuration();
			}
		}
		return 0;
	}
}
