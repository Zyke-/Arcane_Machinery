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
		setHasSubtypes(false);
		setMaxDamage(5);
		textureName = name;
	}
		
	private String textureName;
		
	public void updateIcons(IconRegister iconRegister) {
		iconIndex = iconRegister.registerIcon("ArcaneMachinery:" + textureName);
	}
	
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float px, float py, float pz) {
		int blockId = world.getBlockId(x, y, z);	
		if (blockId == Block.obsidian.blockID) {
			itemStack.damageItem(1, entityPlayer);
			world.setBlock(x, y, z, Block.cobblestone.blockID);
			world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.fizz", 0.3F, 0.1F);	
			 return !world.isRemote;
		}
		return false;
	}
}
