package de.blacktigers.lom.champions.skills;

import java.util.Date;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RangedBasicAttack extends Skill {
	long lastlaunched = 0;

	public RangedBasicAttack(Player player2, int mana, int slot) {
		super(player2, mana, new ItemStack(Material.BOW), slot, 3);
		setItemSlot("Ranged Basic Attack");
	}

	@Override
	public void useSkill() {
		//if (new Date().getTime() - lastlaunched <= 2000) {
			// Note to self: try spawning arrow as entity so we can change the damage of the snowball
			player.launchProjectile(Arrow.class).setShooter(player);
			lastlaunched = new Date().getTime();
		//}
	}
}
