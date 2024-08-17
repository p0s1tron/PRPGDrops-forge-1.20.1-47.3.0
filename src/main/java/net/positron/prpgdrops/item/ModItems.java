package net.positron.prpgdrops.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.positron.prpgdrops.PRPGDrops;

public class ModItems {
    public  static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PRPGDrops.MODID);



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
