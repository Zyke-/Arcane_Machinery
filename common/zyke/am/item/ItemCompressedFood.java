package zyke.am.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemCompressedFood extends ItemFood {
	
	public ItemCompressedFood(int id, int maxStackSize, CreativeTabs tab, int hunger, float saturation, boolean isWolfMeat, String name) {
        super(id, hunger, saturation, isWolfMeat);
        setMaxStackSize(maxStackSize);
        setCreativeTab(tab);
        setUnlocalizedName(name);
        textureName = name;
	}
	
	private String textureName;
	
	public void updateIcons(IconRegister iconRegister) {
		iconIndex = iconRegister.registerIcon("ArcaneMachinery:" + textureName);
	}
	
}
