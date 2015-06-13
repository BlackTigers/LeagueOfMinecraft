package de.blacktigers.lom;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import de.blacktigers.lom.champions.Champion;
import de.blacktigers.lom.data.Arena;
import de.blacktigers.lom.sign.LoM_Sign;
import de.blacktigers.lom.sign.LoM_TowerSign;

public class Engine {
	public void startEngine() {

		Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin) LeagueOfMinecraft.getPlugin(),
				new Runnable() {

					@Override
					public void run() {
						for (Arena arena : LeagueOfMinecraft.getPlugin().Arenas.values()) {
							if (arena.isActive()) {
								for (Champion champ : arena.getChamps()
										.values()) {
									
									if ((champ.getMana() + champ.getManaRegen()) <= champ
											.getMaxMana()) {
										champ.setMana((int) (champ.getMana() + champ
												.getManaRegen()));
									} else {
										champ.setMana(champ.getMaxMana());
									}
									arena.getChamps().put(champ.getPlayerName(),
											champ);
									continue;
								}
								LeagueOfMinecraft.getPlugin().Arenas.put(arena.getName(), arena);
							}
							continue;
						}

					}
				}, 200, 40);
		Bukkit.getScheduler().runTaskTimer((Plugin) LeagueOfMinecraft.getPlugin(), new Runnable() {

			@Override
			public void run() {
				for (LoM_Sign sign : LeagueOfMinecraft.getPlugin().Signs) {
					sign.setArenas(LeagueOfMinecraft.getPlugin().Arenas);
					sign.updateSign();
				}
				for(LoM_TowerSign sign : LeagueOfMinecraft.getPlugin().towerSigns){
					sign.updateSign();
				}
				for (Arena arena : LeagueOfMinecraft.getPlugin().Arenas.values()) {
					if (arena.isActive()) {
						for (Champion champ : arena.getChamps()
								.values()) {
							Player player = champ.getPlayer();
							champ.setMoney((int) (champ.getMoney() + 1 + champ
									.getGoldRegen()));
							player.getInventory().setItem(35, new ItemStack(Material.GOLD_INGOT));
							ItemStack ingot = player.getInventory().getItem(35);
							ItemMeta meta = ingot.getItemMeta();
							List<String> list = new ArrayList<String>();
							list.add((String) "" + champ.getMoney());
							meta.setLore(list);
							ingot.setItemMeta(meta);
							player.getInventory().setItem(35, ingot);
						}
					}
				}

			}

		}, 200, 40);
		
		//Minionwaves
		Bukkit.getScheduler().runTaskTimer((Plugin) LeagueOfMinecraft.getPlugin(), new Runnable() {
			@Override
			public void run() {
				for (Arena arena : LeagueOfMinecraft.getPlugin().Arenas.values()) {
					if (arena.isActive()) {
						if(arena.getNexusRed() != null && arena.getNexusBlue() != null){
							ItemStack leatherarmor = new ItemStack(Material.LEATHER_CHESTPLATE);
							LeatherArmorMeta meta = (LeatherArmorMeta)leatherarmor.getItemMeta();
							leatherarmor.setItemMeta(meta);
							//Zombieswarm blue
							//mid
							for(int i = 0; i<5; i++){
								Zombie zom = (Zombie) arena.getWorld().spawnEntity(arena.getNexusBlue().getLoc().getLocation(), EntityType.ZOMBIE);
								zom.setBaby(true);
								zom.setRemoveWhenFarAway(false);
								zom.setMetadata("isLoMMob", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), true));
								zom.setMetadata("Lane", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "mid"));
								zom.setMetadata("Team", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "blue"));
								zom.getEquipment().setChestplate(leatherarmor);
								zom.getEquipment().setChestplateDropChance((float) 0.0);
							}
							//top
							for(int i = 0; i<5; i++){
								Zombie zom = (Zombie) arena.getWorld().spawnEntity(arena.getNexusBlue().getLoc().getLocation(), EntityType.ZOMBIE);
								zom.setBaby(true);
								zom.setRemoveWhenFarAway(false);
								zom.setMetadata("isLoMMob", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), true));
								zom.setMetadata("Lane", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "top"));
								zom.setMetadata("Team", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "blue"));
								zom.getEquipment().setChestplate(leatherarmor);
								zom.getEquipment().setChestplateDropChance((float) 0.0);
							}
							//bot
							for(int i = 0; i<5; i++){
								Zombie zom = (Zombie) arena.getWorld().spawnEntity(arena.getNexusBlue().getLoc().getLocation(), EntityType.ZOMBIE);
								zom.setBaby(true);
								zom.setRemoveWhenFarAway(false);
								zom.setMetadata("isLoMMob", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), true));
								zom.setMetadata("Lane", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "bot"));
								zom.setMetadata("Team", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "blue"));
								zom.getEquipment().setChestplate(leatherarmor);
								zom.getEquipment().setChestplateDropChance((float) 0.0);
							}
							//Zombieswarm red
							//mid
							for(int i = 0; i<5; i++){
								Zombie zom = (Zombie) arena.getWorld().spawnEntity(arena.getNexusRed().getLoc().getLocation(), EntityType.ZOMBIE);
								zom.setBaby(true);
								zom.setRemoveWhenFarAway(false);
								zom.setMetadata("isLoMMob", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), true));
								zom.setMetadata("Lane", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "mid"));
								zom.setMetadata("Team", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "red"));
								zom.getEquipment().setChestplate(leatherarmor);
								zom.getEquipment().setChestplateDropChance((float) 0.0);
							}
							//top
							for(int i = 0; i<5; i++){
								Zombie zom = (Zombie) arena.getWorld().spawnEntity(arena.getNexusRed().getLoc().getLocation(), EntityType.ZOMBIE);
								zom.setBaby(true);
								zom.setRemoveWhenFarAway(false);
								zom.setMetadata("isLoMMob", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), true));
								zom.setMetadata("Lane", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "top"));
								zom.setMetadata("Team", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "red"));
								zom.getEquipment().setChestplate(leatherarmor);
								zom.getEquipment().setChestplateDropChance((float) 0.0);
							}
							//bot
							for(int i = 0; i<5; i++){
								Zombie zom = (Zombie) arena.getWorld().spawnEntity(arena.getNexusRed().getLoc().getLocation(), EntityType.ZOMBIE);
								zom.setBaby(true);
								zom.setRemoveWhenFarAway(false);
								zom.setMetadata("isLoMMob", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), true));
								zom.setMetadata("Lane", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "bot"));
								zom.setMetadata("Team", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "red"));
								zom.getEquipment().setChestplate(leatherarmor);
								zom.getEquipment().setChestplateDropChance((float) 0.0);
							}
						}
					}
				}
				
			}
		}, 4000, 600);
		
	}
}
