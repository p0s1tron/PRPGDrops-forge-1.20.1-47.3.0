package net.positron.prpgdrops.block.entity.renderer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.positron.prpgdrops.PRPGDrops;
import net.positron.prpgdrops.block.entity.TreasureChestBlockEntity;

public class TreasureChestRenderer extends ChestRenderer<TreasureChestBlockEntity> {
    private final boolean xmasTextures;

    public TreasureChestRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
        this.xmasTextures = false;
    }

    @Override
    protected Material getMaterial(TreasureChestBlockEntity blockEntity, ChestType chestType) {

        return Sheets.chooseMaterial(blockEntity, chestType, this.xmasTextures);
    }
}
