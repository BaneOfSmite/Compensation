package me.baneofsmite.compensation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.baneofsmite.compensation.Main;

public class ccommandlistener implements CommandExecutor {
    private final Main main;

    public ccommandlistener(Main main) {
        this.main = main;
    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		Player p = (Player) sender;
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("enable")) {
				p.sendMessage(Main.getPrefix() + ChatColor.GRAY + " You Enabled Compensation!");
	            main.addDiamondRecipe();
				Main.compensationv = true;
			} else if (args[0].equalsIgnoreCase("disable")) {
				p.sendMessage(Main.getPrefix() + ChatColor.GRAY + " You Disabled Compensation!");
				main.removeDiamondRecipe();
				Main.compensationv = false;
			}
		}
		return true;
	}

}