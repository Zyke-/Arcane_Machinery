package zyke.am;

import zyke.am.block.*;
import zyke.am.common.ArcaneMachineryPacketHandler;
import zyke.am.common.GuiHandler;
import zyke.am.item.*;
import zyke.am.event.ArcaneMachineryDropsEvent;
import zyke.am.lib.Reference;
import zyke.am.proxy.CommonProxy;
import zyke.am.tileentity.TileEntityElementalCompressor;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(	modid = Reference.MOD_ID,
		name = Reference.MOD_NAME, 
		version = Reference.MOD_VERSION
)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false, 
		channels = "ArcaneMachinery", 
		packetHandler = ArcaneMachineryPacketHandler.class
)

public class ArcaneMachinery {
	@Instance("ArcaneMachinery")
	public static ArcaneMachinery instance;
	@SidedProxy(clientSide="client.ClientProxy", serverSide="common.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tabAM = new CreativeTabs("tabAM") {
		public ItemStack getIconItemStack() {
        	return new ItemStack(ArcaneMachinery.BlockElementalCompressorIdle, 1, 0);
		}
	};
	
	private GuiHandler guihandler = new GuiHandler();
	
	public static Item ItemCompressedFood;
	public static Item ItemNecroticHeart;
	public static Item ItemHeatCondenser;
	public static Item ItemGoldShears;
	public static Item ItemOverheater;
	public static Item ItemVoidWand;
	public static Block BlockElementalCompressorIdle;
	public static Block BlockElementalCompressorBurning;
		
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		ItemCompressedFood = new ItemCompressedFood(5001, 64, tabAM, 18, 1.0F, false, "itemCompressedFood");
		ItemNecroticHeart = new ItemNecroticHeart(5002, 16, tabAM, "itemNecroticHeart");
		ItemHeatCondenser = new ItemHeatCondenser(5003, 1, tabAM, "itemHeatCondenser");
		
		ItemGoldShears = new ItemGoldShears(5010, 1, tabAM, "itemGoldShears");
		ItemOverheater = new ItemOverheater(5011, 1, tabAM, "itemOverheater");
		ItemVoidWand = new ItemVoidWand(5012, 1, tabAM, "itemVoidWand");

		BlockElementalCompressorIdle = new BlockElementalCompressor(500, false).setHardness(3.5F).setUnlocalizedName("Elemental Compressor Idle").setCreativeTab(tabAM);
		BlockElementalCompressorBurning = new BlockElementalCompressor(501, true).setHardness(3.5F).setUnlocalizedName("Elemental Compressor Burning").setCreativeTab(tabAM);
		
		registerBlocks();
		registerNames();
		registerRecieps();
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabAM", "en_US", "Arcane Machinery");
		MinecraftForge.EVENT_BUS.register(new ArcaneMachineryDropsEvent());
	}
		        
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	private void registerRecieps(){
		ItemStack dirtStack = new ItemStack(Block.dirt);
		ItemStack cobbleStack = new ItemStack(Block.cobblestone);
		ItemStack goldStack = new ItemStack(Item.ingotGold);
		ItemStack shearsStack = new ItemStack(Item.shears);
        
        GameRegistry.addShapelessRecipe(new ItemStack(ArcaneMachinery.ItemCompressedFood), 
        		dirtStack, dirtStack, dirtStack,
        		dirtStack, dirtStack, dirtStack);
        // Food Compressor
        GameRegistry.addRecipe(new ItemStack(ArcaneMachinery.BlockElementalCompressorIdle), "ccc", "p p", "ccc",
        		'c', cobbleStack, 'p', new ItemStack(Block.pistonBase));
        
        // Gold Shears
        GameRegistry.addRecipe(new ItemStack(ArcaneMachinery.ItemGoldShears), 
        		"g  ", 
        		"sg ", 
        		"   ",
        		'g', goldStack, 's', shearsStack);
        
        GameRegistry.addRecipe(new ItemStack(ArcaneMachinery.ItemGoldShears), 
        		" g ", 
        		" sg", 
        		"   ",
        		'g', goldStack, 's', shearsStack);
        
        GameRegistry.addRecipe(new ItemStack(ArcaneMachinery.ItemGoldShears),
        		"   ", 
        		" g ", 
        		" sg",
        		'g', goldStack, 's', shearsStack);
        
        GameRegistry.addRecipe(new ItemStack(ArcaneMachinery.ItemGoldShears), 
        		"   ", 
        		"g  ", 
        		"sg ", 
        		'g', goldStack, 's', shearsStack);
        
        GameRegistry.addRecipe(new ItemStack(ArcaneMachinery.ItemOverheater),
        		"o o",
        		"oho",
        		" p ",
        		'o', new ItemStack(Block.obsidian), 'h', new ItemStack(ArcaneMachinery.ItemHeatCondenser), 'p', new ItemStack(Item.pickaxeDiamond));
        
        GameRegistry.addRecipe(new ItemStack(ArcaneMachinery.ItemHeatCondenser),
        		" r ",
        		"fbf",
        		" r ",
        		'r', new ItemStack(Item.redstone), 'b', new ItemStack(Item.blazePowder), 'f', new ItemStack(Item.flintAndSteel));
      
        GameRegistry.addRecipe(new ItemStack(ArcaneMachinery.ItemHeatCondenser),
        		" f ",
        		"rbr",
        		" f ",
        		'r', new ItemStack(Item.redstone), 'b', new ItemStack(Item.blazePowder), 'f', new ItemStack(Item.flintAndSteel));
	}
	
	private void registerNames(){
		LanguageRegistry.addName(ItemCompressedFood, "Compressed Food");
		LanguageRegistry.addName(ItemNecroticHeart, "Necrotic Heart");
		LanguageRegistry.addName(ItemHeatCondenser, "Heat Condenser");
		
		LanguageRegistry.addName(ItemGoldShears, "Gold Shears");
		LanguageRegistry.addName(ItemOverheater, "Overheater");
		LanguageRegistry.addName(ItemVoidWand, "Void Wand");
				
		LanguageRegistry.addName(BlockElementalCompressorIdle, "Elemental Compressor");
		LanguageRegistry.addName(BlockElementalCompressorBurning, "Elemental Compressor Burning");
	}
	
	private void registerBlocks(){
		GameRegistry.registerBlock(BlockElementalCompressorIdle, "blockEC.furnaceIdle");
		GameRegistry.registerBlock(BlockElementalCompressorBurning, "blockEC.furnaceBurning");
		GameRegistry.registerTileEntity(TileEntityElementalCompressor.class, "tileentityelementalcompressor");
		NetworkRegistry.instance().registerGuiHandler(this, guihandler);
	}
}


//Types 
		/*
		GameRegistry.addShapelessRecipe(new ItemStack(ZykeMod.ItemCompressedFood), 
			dirtStack, dirtStack, dirtStack,
			dirtStack, dirtStack, dirtStack);

		GameRegistry.addRecipe(new ItemStack(Block.stone), "xyx", "y y", "xyx",
      	'x', dirtStack, 'y', dirtStack);

		GameRegistry.addSmelting(Block.stone.blockID, new ItemStack(
      	Block.stoneBrick), 0.1f);

		FurnaceRecipes.smelting().addSmelting(355,
      	new ItemStack(Block.cloth, 1, 14), 0.1f); 
      */