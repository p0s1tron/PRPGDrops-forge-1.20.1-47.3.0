package net.positron.prpgdrops;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = PRPGDrops.MODID)
public class ModDeathEvent {

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity().getType().getCategory().isFriendly() || !(event.getSource().getEntity() instanceof Player player)) {
            return;
        }
        spawnChest(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
    }

    public static void spawnChest(LevelAccessor world, double x, double y, double z) {
        spawnChest(null, world, x, y, z);
    }

    private static void spawnChest(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
        if (world instanceof ServerLevel _level) {
            world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.CHEST.defaultBlockState(), 2);
        }
    }
}