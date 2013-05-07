package zyke.am.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

	public boolean itemInteractionForEntity(ItemStack itemStack, EntityLiving entityLiving) {
		if (!(entityLiving instanceof EntityPlayer) || !((EntityPlayer)entityLiving).capabilities.isCreativeMode) {
			entityLiving.setDead();
			entityLiving.renderBrokenItemStack(itemStack);
			--itemStack.stackSize;
		}
		return true;
	}

}
