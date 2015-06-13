package de.blacktigers.lom.champions.skills.ashe;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import de.blacktigers.lom.LeagueOfMinecraft;
import de.blacktigers.lom.champions.skills.Skill;

public class EnchantedCrystalArrow extends Skill{

	public EnchantedCrystalArrow(Player player2, int mana, int slot, int cooldown) {
		super(player2, mana, new ItemStack(Material.SNOW_BALL), slot, cooldown);
		setItemSlot("Enchanted Crystal Arrow");
		getIconItem().addEnchantment(Enchantment.ARROW_DAMAGE, 1);
	}

	@Override
	public void useSkill() {
		Arrow arrow1 = (Arrow)getPlayer().launchProjectile(Arrow.class);
		arrow1.setVelocity(arrow1.getVelocity().multiply(3));
		arrow1.setMetadata("EnchantedCrystalArrow", new FixedMetadataValue(LeagueOfMinecraft.getPlugin(), "EnchantedCrystalArrow"));
		getPlayer().playSound(getPlayer().getLocation(), Sound.FIREWORK_LAUNCH, 10, 1);
	}

}
