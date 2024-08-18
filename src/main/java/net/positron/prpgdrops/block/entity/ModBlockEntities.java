package net.positron.prpgdrops.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.positron.prpgdrops.PRPGDrops;
import net.positron.prpgdrops.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PRPGDrops.MODID);


    public static final RegistryObject<BlockEntityType<TreasureChestBlockEntity>> TREASURE_CHEST_BE =
            BLOCK_ENTITIES.register("treasure_chest_be", () ->
                    BlockEntityType.Builder.of(TreasureChestBlockEntity::new,
                            ModBlocks.TREASURE_CHEST_BLOCK.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
