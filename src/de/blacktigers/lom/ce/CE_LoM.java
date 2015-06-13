package de.blacktigers.lom.ce;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.blacktigers.lom.LeagueOfMinecraft;
import de.blacktigers.lom.api.LoM_API;
import de.blacktigers.lom.champions.Alistar;
import de.blacktigers.lom.champions.Ashe;
import de.blacktigers.lom.champions.Champion;
import de.blacktigers.lom.champions.Garen;
import de.blacktigers.lom.champions.Jax;
import de.blacktigers.lom.champions.Veigar;
import de.blacktigers.lom.data.Arena;
import de.blacktigers.lom.data.Config;
import de.blacktigers.lom.data.InvSave;

public class CE_LoM implements CommandExecutor {
	Config mConfig;
	FileConfiguration mFileConfig;
	public CE_LoM(LeagueOfMinecraft t) {
		mConfig = new Config();
		mFileConfig = mConfig.getConfig("champions.yml");
	}
	
	@Override
	public boolean onCommand(CommandSender pSender, Command pCommand, String arg2,
			String[] pArgs) {
		Player lPlayer = (Player) pSender;
		if (pCommand.getName().equalsIgnoreCase("lom")) {
			if (!(pSender instanceof Player)) {
				pSender.sendMessage(ChatColor.RED
						+ "Sorry but this Commands are only for Players!");
				return true;
			}
			/*
			 * 2 Arguments
			 */
			if (pArgs.length == 2) {
				/*
				 * Creates a new Arena
				 */
				if (pArgs[0].equalsIgnoreCase("create")) {
					if (pSender.hasPermission("lom.arena.create")) {
						if (!LeagueOfMinecraft.getPlugin().Arenas.containsKey(pArgs[1].toLowerCase())) {
							WorldCreator lWorldCreator = new WorldCreator(
									pArgs[1].toLowerCase());
							World lWorld = lWorldCreator.createWorld();
							lWorld.setKeepSpawnInMemory(true);
							Arena lArena = new Arena(pArgs[1].toLowerCase());
							LeagueOfMinecraft.getPlugin().Arenas.put(pArgs[1].toLowerCase(), lArena);
							pSender.sendMessage(ChatColor.GREEN
									+ "Succesfully created!");
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "This arena already exist!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				/*
				 * Teleports you to the spawn of this arena
				 */
				if (pArgs[0].equalsIgnoreCase("goto")) {
					if (pSender.hasPermission("lom.arena.goto")) {
						if (Bukkit.getWorld(pArgs[1].toLowerCase()) != null) {
							lPlayer.teleport(Bukkit.getWorld(pArgs[1])
									.getSpawnLocation());
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "This world doesnt exist!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				/*
				 * Delete a existing arena
				 */
				if (pArgs[0].equalsIgnoreCase("delete")) {
					if (pSender.hasPermission("lom.arena.delete")) {
						if (Bukkit.getWorld(pArgs[1].toLowerCase()) != null) {
							Bukkit.unloadWorld(pArgs[1].toLowerCase(), true);
							if (LeagueOfMinecraft.getPlugin().Arenas.containsKey(pArgs[1])) {
								LeagueOfMinecraft.getPlugin().Arenas.remove(pArgs[1].toLowerCase());
							}
							pSender.sendMessage(ChatColor.GREEN
									+ "Arena removed!");
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "This arena doesn't exist!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				/*
				 * Sets the lobby for a Team in your Arena
				 */
				if (pArgs[0].equalsIgnoreCase("setLobby")) {
					if (pSender.hasPermission("lom.arena.setLobby")) {
						if (LoM_API.getArenaW(lPlayer.getWorld()) != null) {
							Arena arena = LoM_API.getArenaW(lPlayer.getWorld());
							if (pArgs[1].equalsIgnoreCase("red")) {
								arena.setLobbyRed(lPlayer.getLocation());
								LeagueOfMinecraft.getPlugin().Arenas.put(arena.getName(), arena);
								pSender.sendMessage(ChatColor.RED + "RED " + ChatColor.GREEN
										+ "Lobby set!");
								return true;
							}
							if (pArgs[1].equalsIgnoreCase("blue")) {
								arena.setLobbyBlue(lPlayer.getLocation());
								LeagueOfMinecraft.getPlugin().Arenas.put(arena.getName(), arena);
								pSender.sendMessage(ChatColor.BLUE + "BLUE " + ChatColor.GREEN
										+ "Lobby set!");
								return true;
							}
							pSender.sendMessage(ChatColor.RED
									+ "/lom setlobby <Team: red/blue>");
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "This is not an arena!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				/*
				 * Sets the spawn for a Team in your Arena
				 */
				if (pArgs[0].equalsIgnoreCase("setSpawn")) {
					if (pSender.hasPermission("lom.arena.setSpawn")) {
						if (LoM_API.getArenaW(lPlayer.getWorld()) != null) {
							Arena arena = LoM_API.getArenaW(lPlayer.getWorld());
							if (pArgs[1].equalsIgnoreCase("red")) {
								arena.setSpawnRed(lPlayer.getLocation());
								LeagueOfMinecraft.getPlugin().Arenas.put(arena.getName(), arena);
								pSender.sendMessage(ChatColor.RED + "RED " + ChatColor.GREEN
										+ "Spawn set!");
								return true;
							}
							if (pArgs[1].equalsIgnoreCase("blue")) {
								arena.setSpawnBlue(lPlayer.getLocation());
								LeagueOfMinecraft.getPlugin().Arenas.put(arena.getName(), arena);
								pSender.sendMessage(ChatColor.BLUE + "BLUE " + ChatColor.GREEN
										+ "Spawn set!");
								return true;
							}
							pSender.sendMessage(ChatColor.RED
									+ "/lom setspawn <Team: red/blue>");
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "This is not an arena!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				/*
				 * Join a specific arena
				 */
				if (pArgs[0].equalsIgnoreCase("join")) {
					if (pSender.hasPermission("lom.arena.join")) {
						if (!LoM_API.isInArena(lPlayer)) {
							if (LeagueOfMinecraft.getPlugin().Arenas
									.containsKey(pArgs[1].toLowerCase())) {
								int maxPlayer;
								if(mFileConfig.getInt("arena.maxPlayer") > 10 ){
									maxPlayer = 10;
								}else if(mFileConfig.getInt("arena.maxPlayer") < 2 ){
									maxPlayer = 2;
								} else{
									maxPlayer = mFileConfig.getInt("arena.maxPlayer");
								}
								if (!(LeagueOfMinecraft.getPlugin().Arenas.get(pArgs[1].toLowerCase())
										.getPlayers().size() == maxPlayer)) {
									if (!LeagueOfMinecraft.getPlugin().Arenas.get(pArgs[1]).isActive()) {
										if (LeagueOfMinecraft.getPlugin().Arenas.get(
												pArgs[1].toLowerCase())
												.getLobbyBlue() != null
												&& LeagueOfMinecraft.getPlugin().Arenas.get(
														pArgs[1].toLowerCase())
														.getLobbyRed() != null
												&& LeagueOfMinecraft.getPlugin().Arenas.get(
														pArgs[1].toLowerCase())
														.getSpawnBlue() != null
												&& LeagueOfMinecraft.getPlugin().Arenas.get(
														pArgs[1].toLowerCase())
														.getSpawnBlue() != null) {
											InvSave.saveInventory(lPlayer);
											Arena arena = LeagueOfMinecraft.getPlugin().Arenas
													.get(pArgs[1].toLowerCase());
											joinTeam(arena, lPlayer);
											pSender.sendMessage(ChatColor.GREEN
													+ "Success!");
											return true;
										}
										pSender.sendMessage(ChatColor.RED
												+ "This arena isn't ready yet!");
										return true;
									}
									pSender.sendMessage(ChatColor.RED
											+ "This arena is already active!");
									return true;
								}
								pSender.sendMessage(ChatColor.RED
										+ "This arena is full!");
								return true;
							}
							pSender.sendMessage(ChatColor.RED
									+ "This is not an arena!");
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "You are already in an arena!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				/*
				 * Choose a Champion
				 */
				if (pArgs[0].equalsIgnoreCase("choose")) {
					if (pSender.hasPermission("lom.champ.choose")) {
						if (LoM_API.isInArena(lPlayer)) {
								lPlayer.getInventory().clear();
								if (LoM_API.getArenaP(lPlayer).getChamps().get(
										lPlayer.getName()) != null) {
									if(LoM_API.getArenaP(lPlayer).getChamps().get(lPlayer.getName()).isReady()){
										lPlayer.sendMessage(ChatColor.RED + "Sorry but you already locked in!");
										return true;
									}
									Arena a = LoM_API.getArenaP(lPlayer);
									a.removeChamp(lPlayer);
									LeagueOfMinecraft.getPlugin().Arenas.put(a.getName(), a);
								}
								if(mFileConfig.get("champions."+ pArgs[1].toLowerCase() + ".active") != null){
									if(!mFileConfig.getBoolean("champions."+ pArgs[1].toLowerCase() + ".active")){
										lPlayer.sendMessage(ChatColor.RED + "Sorry, but this champ isn't activated!");
										return true;
									}
									if(mFileConfig.getBoolean("champions."+ pArgs[1].toLowerCase() + ".needspermission")){
										if(!lPlayer.hasPermission("lom.champion."+ pArgs[1].toLowerCase())){
											lPlayer.sendMessage(ChatColor.RED + "Sorry, but this Champ needs a permission!");
											return true;
										}
									}
								}
								Champion champ = null;
								switch (pArgs[1].toLowerCase()) {
								case "ashe":
									champ = new Ashe(lPlayer);
									break;
								case "garen":
									champ = new Garen(lPlayer);
									break;
								case "veigar":
									champ = new Veigar(lPlayer);
									break;
								case "alistar":
									champ = new Alistar(lPlayer);
									break;
								case "jax":
									champ = new Jax(lPlayer);
									break;
								default:
									break;
								}
	
								if (champ != null) {
									
									Arena a = LoM_API.getArenaP(lPlayer);
									a.addChamp(lPlayer, champ);
									LeagueOfMinecraft.getPlugin().Arenas.put(a.getName(), a);
									lPlayer.sendMessage(ChatColor.GREEN
											+ "You are now playing as: "
											+ ChatColor.GOLD + champ.getName());
									return true;
								} else {
									pSender.sendMessage(ChatColor.RED
											+ "This is not a valid chap!");
									return true;
								}				
											
						}
						pSender.sendMessage(ChatColor.RED
								+ "You are not in an arena!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				if (pArgs[0].equalsIgnoreCase("help")) {
					if (pSender.hasPermission("lom.help")) {
						if (pArgs[1].equalsIgnoreCase("admin")) {
							if (pSender.hasPermission("lom.help.admin")) {
								pSender.sendMessage(ChatColor.GOLD
										+ "League of Minecraft Help:");
								pSender.sendMessage(ChatColor.GREEN
										+ "/lom create <name> = Creates a new arena with this name");
								pSender.sendMessage(ChatColor.GREEN
										+ "/lom delete <name> = Deletes the arena with this name");
								pSender.sendMessage(ChatColor.GREEN
										+ "/lom goto <name> = Teleports you to the spawn of this world/arena");
								pSender.sendMessage(ChatColor.GREEN
										+ "/lom setLobby <red/blue> = Sets the lobby for this team");
								pSender.sendMessage(ChatColor.GREEN
										+ "/lom setSpawn <red/blue> = Sets the spawn for this team");
								return true;
							}
						}
						if (pArgs[2].equalsIgnoreCase("1")) {
							pSender.sendMessage(ChatColor.GOLD
									+ "League of Minecraft Help:");
							pSender.sendMessage(ChatColor.GREEN
									+ "/lom join <name> = Join this arena");
							pSender.sendMessage(ChatColor.GREEN
									+ "/lom leave <name> = Leave this arena");
							pSender.sendMessage(ChatColor.GREEN
									+ "/lom choose <champname> = select a champion");
							pSender.sendMessage(ChatColor.GREEN
									+ "/lom money = Your money");

							return true;
						}
						pSender.sendMessage(ChatColor.RED + "Wrong number!");
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				if (pArgs[0].equalsIgnoreCase("start")) {
					if (pSender.hasPermission("lom.arena.start")) {
						if (LeagueOfMinecraft.getPlugin().Arenas.containsKey(pArgs[1].toLowerCase())) {
							Arena arena = LeagueOfMinecraft.getPlugin().Arenas.get(pArgs[1]
									.toLowerCase());
							if (arena.getLobbyBlue() != null
									&& arena.getLobbyRed() != null
									&& arena.getSpawnBlue() != null
									&& arena.getSpawnBlue() != null) {
								boolean allchamps = true;
								ArrayList<String> ar = new ArrayList<String>();
								for (Champion champ : arena.getChamps().values()) {
									if(!champ.isReady()){
										ar.add(champ.getPlayerName());
										allchamps = false;
									}
									continue;
								}
								int minPlayer;
								if(mFileConfig.getInt("arena.minPlayer") > 10 ){
									minPlayer = 10;
								}else if (mFileConfig.getInt("arena.minPlayer") < 2 ){
									minPlayer = 2;
								}else{
									minPlayer = mFileConfig.getInt("arena.minPlayer");
								}
								if(arena.getPlayers().size() > minPlayer){
									if (allchamps) {
										arena.startGame();
										LeagueOfMinecraft.getPlugin().Arenas.put(arena.getName(), arena);
										return true;
									}
									pSender.sendMessage(ChatColor.RED
											+ "The following players aren't ready yet: ");
									for(int i = 0; i <= ar.size(); i++){
										lPlayer.sendMessage( ChatColor.GOLD +"- " + ar.get(i));
									}
									return true;
								}
								lPlayer.sendMessage(ChatColor.RED + "Not enough players!");
								return true;
								

							}
							pSender.sendMessage(ChatColor.RED
									+ "This arena isn't ready yet!");
							return true;
						}
						pSender.sendMessage(ChatColor.YELLOW
								+ "Sorry but this arena doesnt exist!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}

			}
			/*
			 * 
			 * 1 Argument
			 */
			if (pArgs.length == 1) {
				if (pArgs[0].equalsIgnoreCase("leave")) {
					if (pSender.hasPermission("lom.arena.leave")) {
						if (LoM_API.isInArena(lPlayer)) {
							Arena arena = LoM_API.getArenaP(lPlayer);
							if (!arena.isActive()) {
								arena.removePlayer(lPlayer);
								lPlayer.teleport(LeagueOfMinecraft.getPlugin()
										.getServer()
										.getWorld(
												Bukkit.getWorlds().get(0)
														.getName())
										.getSpawnLocation());
								InvSave.reloadInventory(lPlayer);
								lPlayer.setGameMode(GameMode.SURVIVAL);
								lPlayer.setMaxHealth(20.0);
								lPlayer.setFoodLevel(20);
								pSender.sendMessage(ChatColor.GREEN
										+ "You left the arena!");
								return true;
							}
							pSender.sendMessage(ChatColor.RED
									+ "Sorry but your game already started! You have to play this game to the end!");
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "You are not in an arena!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				if (pArgs[0].equalsIgnoreCase("help")) {
					if (pSender.hasPermission("lom.help")) {
						pSender.sendMessage(ChatColor.GOLD
								+ "League of Minecraft Help:");
						pSender.sendMessage(ChatColor.GREEN
								+ "/lom join <name> = Join this arena");
						pSender.sendMessage(ChatColor.GREEN
								+ "/lom leave <name> = Leave this arena");
						pSender.sendMessage(ChatColor.GREEN
								+ "/lom choose <champname> = select a champion");
						pSender.sendMessage(ChatColor.GREEN
								+ "/lom money = Your money");
						return true;

					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				if (pArgs[0].equalsIgnoreCase("money")) {
					if (LoM_API.isInArena(lPlayer)) {
						if (LoM_API.getArenaP(lPlayer).isActive()) {
							int money = LoM_API.getArenaP(lPlayer).getChamps()
									.get(lPlayer.getName()).getMoney();
							pSender.sendMessage(ChatColor.GOLD
									+ "You currently have: " + ChatColor.RED
									+ money + ChatColor.GOLD + " gold");
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "This Arena has not started yet!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You have to be in an Arena to use this!");
					return true;
				}
				if(pArgs[0].equalsIgnoreCase("shop")){
					if (pSender.hasPermission("lom.arena.shop")) {
						if (LoM_API.isInArena(lPlayer)) {
							lPlayer.openInventory(LeagueOfMinecraft.getPlugin().shop.getPage(0));
							return true;
						}
						pSender.sendMessage(ChatColor.RED
								+ "You have to be in an Arena to use this!");
						return true;
					}
					pSender.sendMessage(ChatColor.RED
							+ "You don't have the permission to do that!");
					return true;
				}
				

			}

			pSender.sendMessage(ChatColor.RED + "Use /lom help!");
			return true;
		}
		return true;
	}

	/*
	 * Random choosen Team
	 */
	public void joinTeam(Arena arena, Player player) {
		player.getInventory().clear();
		player.setLevel(1);
		player.setExp(0);
		player.setGameMode(GameMode.ADVENTURE);
		player.setSaturation((float) 20);
		if (arena.getTeamBlue().size() < 5 && arena.getTeamRed().size() < 5) {
			if(arena.getTeamBlue().size() < arena.getTeamRed().size()){
				arena.addPlayerBlue(player);
			}else{
				arena.addPlayerRed(player);
			}
		} else if (arena.getTeamBlue().size() < 5) {
			arena.addPlayerBlue(player);
			player.sendMessage(ChatColor.BLUE + "You are in Team Blue!");
			player.teleport(arena.getLobbyBlue().getLocation());
		} else if (arena.getTeamRed().size() < 5) {
			arena.addPlayerRed(player);
			player.sendMessage(ChatColor.RED + "You are in Team Red!");
			player.teleport(arena.getLobbyRed().getLocation());
		}
		LeagueOfMinecraft.getPlugin().Arenas.put(arena.getName(), arena);

	}

}
