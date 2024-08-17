package net.positron.prpgdrops.event;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.positron.prpgdrops.PRPGDrops;
import net.positron.prpgdrops.block.ModBlocks;
import net.positron.prpgdrops.block.custom.TreasureChestBlock;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = PRPGDrops.MODID)
public class ModDeathEvent {

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getType().getCategory().isFriendly() || !(entity.getLastHurtByMob() instanceof Player)) {
            return;
        }
        BlockPos blockPos = entity.getOnPos().above(1);
        spawnChest(event, entity.level(), blockPos);
    }

    public static void spawnChest(LevelAccessor world, BlockPos blockPos) {
        spawnChest(null, world, blockPos);
    }

    public static void spawnChest(@Nullable Event event, LevelAccessor world, BlockPos blockpos) {
        if (world instanceof ServerLevel level) {
            world.setBlock(blockpos, ModBlocks.TREASURE_CHEST_BLOCK.get().defaultBlockState(), 2);
            if (ModBlocks.TREASURE_CHEST_BLOCK.get() instanceof TreasureChestBlock chest) {
                TreasureChestBlock.scheduleTick(level, blockpos, chest);
                RandomizableContainerBlockEntity.setLootTable(level, RandomSource.createNewThreadLocalInstance(), blockpos,
                        new ResourceLocation(PRPGDrops.MODID, "entities/common_drop"));
            }
        }
    }
}