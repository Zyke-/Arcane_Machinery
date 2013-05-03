package zyke.am.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOverheater extends Item {
	
	public ItemOverheater(int id, int maxStackSize, CreativeTabs tab, String name) {
		super(id);
		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
		textureName = name;
	}
		
	private String textureName;
		
	public void updateIcons(IconRegister iconRegister) {
		iconIndex = iconRegister.registerIcon("ArcaneMachinery:" + textureName);
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		System.out.println("Somethig happened!");
		if(!world.isRemote) {
			if (world.getBlockId(x, y, z) == Block.obsidian.blockID) {
				itemStack.damageItem(1, entityPlayer);
				world.destroyBlock(x, y, z, true);
			}
		}
		return true;
    }
}
