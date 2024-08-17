package net.positron.prpgdrops.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.positron.prpgdrops.PRPGDrops;
import net.positron.prpgdrops.block.entity.ModBlockEntities;
import net.positron.prpgdrops.block.entity.renderer.TreasureChestRenderer;

@Mod.EventBusSubscriber(modid = PRPGDrops.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.TREASURE_CHEST_BE.get(), TreasureChestRenderer::new);
    }
}