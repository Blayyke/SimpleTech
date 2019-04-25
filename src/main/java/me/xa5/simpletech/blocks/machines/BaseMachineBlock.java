package me.xa5.simpletech.blocks.machines;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class BaseMachineBlock extends Block implements BlockEntityProvider {
    private static DirectionProperty FACING = Properties.FACING_HORIZONTAL;
    private final Identifier containerId;

    protected BaseMachineBlock(Settings settings, Identifier containerId) {
        super(settings);
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH));
        this.containerId = containerId;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        return this.getDefaultState().with(FACING, placementContext.getPlayerHorizontalFacing().getOpposite());
    }

    @Override
    public final boolean activate(BlockState blockState_1, World world, BlockPos pos, PlayerEntity player, Hand hand_1, BlockHitResult blockHitResult_1) {
        if (world.isClient) {
            return true;
        }

        ContainerProviderRegistry.INSTANCE.openContainer(containerId, player, packet -> packet.writeBlockPos(pos));
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.with(FACING);
    }

    @Override
    public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState blockState_2, boolean boolean_1) {
        Machine machine = (Machine) world.getBlockEntity(pos);
        for (int i = 0; i < machine.getInventorySize(); i++) {
            ItemStack stack = machine.getInventory().getInvStack(i);
            if (!stack.isEmpty()) {
                dropStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }
    }

    public static void dropStack(World world, double sourceX, double sourceY, double sourceZ, ItemStack stack) {
        double entityWidth = (double) EntityType.ITEM.getWidth();
        double double_5 = 1.0D - entityWidth;
        double double_6 = entityWidth / 2.0D;
        double x = Math.floor(sourceX) + world.random.nextDouble() * double_5 + double_6;
        double y = Math.floor(sourceY) + world.random.nextDouble() * double_5;
        double z = Math.floor(sourceZ) + world.random.nextDouble() * double_5 + double_6;

        while (!stack.isEmpty()) {
            ItemEntity entity = new ItemEntity(world, x, y, z, stack.split(world.random.nextInt(21) + 10));
            entity.setVelocity(world.random.nextGaussian() * 0.05D, world.random.nextGaussian() * 0.05D + 0.2D, world.random.nextGaussian() * 0.05D);
            world.spawnEntity(entity);
        }

    }
}