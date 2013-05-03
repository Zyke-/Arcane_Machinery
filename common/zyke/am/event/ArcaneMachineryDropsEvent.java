package zyke.am.event;

import zyke.am.ArcaneMachinery;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class ArcaneMachineryDropsEvent {

	public static double rand;
	
	@ForgeSubscribe
    public void onEntityDrop(LivingDropsEvent event) {
		if (event.source.getDamageType().equals("player")) {
			rand = Math.random();
			if (event.entityLiving instanceof EntitySkeleton) {
				if (rand < 0.5d) {  
					event.entityLiving.dropItem(ArcaneMachinery.ItemNecroticHeart.itemID, 1);
				}
			}
		}
	}
}
	    

	            

	            
	 