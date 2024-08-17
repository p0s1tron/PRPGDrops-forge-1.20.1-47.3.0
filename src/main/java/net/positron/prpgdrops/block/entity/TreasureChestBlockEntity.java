package net.positron.prpgdrops.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TreasureChestBlockEntity extends ChestBlockEntity {
    public TreasureChestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TREASURE_CHEST_BE.get(), pPos, pBlockState);
    }

    @Override
    protected Component getDefaultName() {
        return (Component.translatable("Treasure Chest"));
    }
}