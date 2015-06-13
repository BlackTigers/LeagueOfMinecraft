package de.blacktigers.lom.data;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.blacktigers.lom.LeagueOfMinecraft;

public class InvSave {

	public InvSave() {
	}

	public static void saveInventory(Player player) {
		LeagueOfMinecraft.getPlugin().getInventorys().reloadConfig("inventorys.yml");
		LeagueOfMinecraft.getPlugin().getInventorys().getConfig("inventorys.yml").set(
				player.getName() + ".inventory",
				player.getInventory().getContents());
		LeagueOfMinecraft.getPlugin().getInventorys().getConfig("inventorys.yml").set(
				player.getName() + ".armor",
				player.getInventory().getArmorContents());
		LeagueOfMinecraft.getPlugin().getInventorys().getConfig("inventorys.yml").set(
				player.getName() + ".level", player.getLevel());
		LeagueOfMinecraft.getPlugin().getInventorys().saveCustomConfig();
	}

	public static void reloadInventory(Player player) {
		LeagueOfMinecraft.getPlugin().getInventorys().reloadConfig("inventorys.yml");
		if (LeagueOfMinecraft.getPlugin().getInventorys().getConfig("inventorys.yml").get(
				player.getName() + ".inventory") != null
				&& LeagueOfMinecraft.getPlugin().getInventorys().getConfig("inventorys.yml").get(
						player.getName() + ".armor") != null
				&& LeagueOfMinecraft.getPlugin().getInventorys().getConfig("inventorys.yml").get(
						player.getName() + ".level") != null) {
			player.getInventory().setArmorContents(
					configToItemStack("inventorys.yml", player, ".armor"));
			player.getInventory().setContents(
					configToItemStack("inventorys.yml", player, ".inventory"));
			player.setLevel((int) LeagueOfMinecraft.getPlugin().getInventorys().getConfig("inventorys.yml")
					.get(player.getName() + ".level"));
		}
	}

	private static ItemStack[] configToItemStack(String yml, Player player,
			String section) {
		@SuppressWarnings("unchecked")
		List<ItemStack> list = (List<ItemStack>) LeagueOfMinecraft.getPlugin().getInventorys().getConfig(
				yml).get(player.getName() + section);
		ItemStack[] stack = list.toArray(new ItemStack[0]);
		return stack;
	}

}
