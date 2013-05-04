package zyke.am.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVoidWand extends Item {
	
	public ItemVoidWand(int id, int maxStackSize, CreativeTabs tab, String name) {
		super(id);
		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
		setHasSubtypes(false);
		setMaxDamage(9);
		textureName = name;
	}
		
	private String textureName;
		
	public void updateIcons(IconRegister iconRegister) {
		iconIndex = iconRegister.registerIcon("ArcaneMachinery:" + textureName);
	}

	public boolean hitEntity(ItemStack itemStack, EntityLiving entityLiving, EntityPlayer entityPlayer, World world) {	
		System.out.println("Voiding!");
		entityLiving.setDead();
		itemStack.damageItem(1, entityPlayer);
		
		//this.triggerAchievement(AchievementList.overkill);
        return true;
	}
}
