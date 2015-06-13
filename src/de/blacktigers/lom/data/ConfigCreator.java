package de.blacktigers.lom.data;

import org.bukkit.configuration.file.FileConfiguration;

import de.blacktigers.lom.LeagueOfMinecraft;

public class ConfigCreator {
	Config LoMC;
	Config ChampC;
	public ConfigCreator(){
		createChampionConfig();
	}
	public void createChampionConfig(){
		ChampC = new Config();
		FileConfiguration fc = ChampC.getConfig("champions.yml");
		for(String s : LeagueOfMinecraft.getPlugin().champions){
			if(!fc.contains("champions."+ s.toLowerCase() + ".active")){
				fc.set("champions."+ s.toLowerCase() + ".active", true);
				fc.set("champions."+ s.toLowerCase() + ".needspermission", false);
			}
		}
		fc.options().copyDefaults(true);
		ChampC.setCustomConfig(fc);
		ChampC.saveCustomConfig();
	}

}
