package net.positron.prpgdrops.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.positron.prpgdrops.block.entity.ModBlockEntities;
import net.positron.prpgdrops.block.entity.TreasureChestBlockEntity;
import org.jetbrains.annotations.Nullable;

public class TreasureChestBlock extends ChestBlock {
    private static boolean TIMER = false;

    public TreasureChestBlock(Properties pProperties) {
        super(pProperties, ModBlockEntities.TREASURE_CHEST_BE::get);
    }

    public static void scheduleTick(ServerLevel pLevel, BlockPos pPos, Block block) {
        pLevel.scheduleTick(pPos, block, 20 * 60);
        TIMER = true;
        System.out.println("Timer set to " + TIMER);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof TreasureChestBlockEntity entity) {
            if (entity.isEmpty() || TIMER) {
                pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 2);
            }
            else {
                ((TreasureChestBlockEntity)blockentity).recheckOpen();
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof TreasureChestBlockEntity entity) {
            if (entity.isEmpty()) {
                pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 2);
                System.out.println(this + " randomTick removing empty chest.");
            }
        }
    }

    @Override
    public InteractionResult use( BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if ((pLevel.getBlockEntity(pPos) instanceof TreasureChestBlockEntity entity) && entity.isEmpty()) {
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 2);
            return InteractionResult.CONSUME;
        }
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider menuprovider = this.getMenuProvider(pState, pLevel, pPos);
            if (menuprovider != null) {
                pPlayer.openMenu(menuprovider);
                pPlayer.awardStat(this.getOpenChestStat());
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TreasureChestBlockEntity(pPos, pState);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? createTickerHelper(pBlockEntityType, ModBlockEntities.TREASURE_CHEST_BE.get(),
                TreasureChestBlockEntity::lidAnimateTick) : null;
    }
}