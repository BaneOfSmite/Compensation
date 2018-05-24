package me.baneofsmite.compensation;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static String prefix = ChatColor.GOLD + "UHC" + ChatColor.DARK_GRAY + " »";
	public static boolean compensationv = false;
	private final ShapedRecipe diamond, diamonddefault;

	public Main() {
		diamond = new ShapedRecipe(new ItemStack(Material.ARROW, 16)).shape(new String[] { "A", "B", "C" });
		diamond.setIngredient('A', Material.FLINT);
		diamond.setIngredient('B', Material.STICK);
		diamond.setIngredient('C', Material.FEATHER);
		diamonddefault = new ShapedRecipe(new ItemStack(Material.ARROW, 4)).shape(new String[] { "A", "B", "C" });
		diamonddefault.setIngredient('A', Material.FLINT);
		diamonddefault.setIngredient('B', Material.STICK);
		diamonddefault.setIngredient('C', Material.FEATHER);
	}

	@Override
	public void onEnable() {
		getCommand("compensation").setExecutor(new ccommandlistener(this));
		getServer().getPluginManager().registerEvents((Listener) new clistener(), this);
	}

	public static String getPrefix() {
		return prefix;
	}

	public static boolean getCompensationv() {
		return compensationv;
	}

	public void addDiamondRecipe() {
		Iterator<Recipe> iterator = getServer().recipeIterator();
		while (iterator.hasNext()) {
			Recipe recipe = iterator.next();
			if (recipe.getResult().getType() != Material.ARROW) continue; {
				iterator.remove();
			}
		}
		getServer().addRecipe(diamond);
	}

	public void removeDiamondRecipe() {

		Iterator<Recipe> iterator = getServer().recipeIterator();

		while (iterator.hasNext()) {
			Recipe recipe = iterator.next();
			if (recipe.getResult().getType() != Material.ARROW) continue; {
				iterator.remove();
			}
			getServer().addRecipe(diamonddefault);
		}
	}
}
