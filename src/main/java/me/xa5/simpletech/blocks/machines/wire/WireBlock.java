package me.xa5.simpletech.blocks.machines.wire;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.ArrayList;

public class WireBlock extends Block implements WireConnectable, BlockEntityProvider {
    private static BooleanProperty ATTACHED_UP = BooleanProperty.create("attached_up");
    private static BooleanProperty ATTACHED_DOWN = BooleanProperty.create("attached_down");
    private static BooleanProperty ATTACHED_NORTH = BooleanProperty.create("attached_north");
    private static BooleanProperty ATTACHED_EAST = BooleanProperty.create("attached_east");
    private static BooleanProperty ATTACHED_SOUTH = BooleanProperty.create("attached_south");
    private static BooleanProperty ATTACHED_WEST = BooleanProperty.create("attached_west");

    //@formatter:off
    // If we start at 8,8,8 and subtract/add to/from 8, we do operations starting from the centre.
    private static final VoxelShape NORTH = createCuboidShape(8 - 3, 8 - 3, 0, 8 + 3, 8 + 3, 8 + 3);
    private static final VoxelShape EAST = createCuboidShape(8 - 3, 8 - 3, 8 - 3, 16, 8 + 3, 8 + 3);
    private static final VoxelShape SOUTH = createCuboidShape(8 - 3, 8 - 3, 8 - 3, 8 + 3, 8 + 3, 16);
    private static final VoxelShape WEST = createCuboidShape(0, 8 - 3, 8 - 3, 8 + 3, 8 + 3, 8 + 3);
    private static final VoxelShape UP = createCuboidShape(8 - 3, 8 - 3, 8 - 3, 8 + 3, 16, 8 + 3);
    private static final VoxelShape DOWN = createCuboidShape(8 - 3, 0, 8 - 3, 8 + 3, 8 + 3, 8 + 3);
    private static final VoxelShape NONE = createCuboidShape(8 - 3, 8 - 3, 8 - 3, 8 + 3, 8 + 3, 8 + 3);    // 6x6x6 box in the center.
    //@formatter:on

    public WireBlock(Settings block$Settings_1) {
        super(block$Settings_1);
        setDefaultState(this.getStateFactory().getDefaultState().with(ATTACHED_NORTH, false).with(ATTACHED_EAST, false).with(ATTACHED_SOUTH, false).with(ATTACHED_WEST, false).with(ATTACHED_UP, false).with(ATTACHED_DOWN, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = this.getDefaultState();
        state.with(ATTACHED_NORTH, false);
        state.with(ATTACHED_EAST, false);
        state.with(ATTACHED_SOUTH, false);
        state.with(ATTACHED_WEST, false);
        state.with(ATTACHED_UP, false);
        state.with(ATTACHED_DOWN, false);

        return state;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, VerticalEntityPosition verticalEntityPosition) {
        ArrayList<VoxelShape> shapes = new ArrayList<>();

        if (blockState.get(ATTACHED_NORTH)) {
            shapes.add(NORTH);
        }
        if (blockState.get(ATTACHED_SOUTH)) {
            shapes.add(SOUTH);
        }
        if (blockState.get(ATTACHED_EAST)) {
            shapes.add(EAST);
        }
        if (blockState.get(ATTACHED_WEST)) {
            shapes.add(WEST);
        }
        if (blockState.get(ATTACHED_UP)) {
            shapes.add(UP);
        }
        if (blockState.get(ATTACHED_DOWN)) {
            shapes.add(DOWN);
        }
        if (shapes.isEmpty()) {
            return NONE;
        } else {
            return VoxelShapes.union(NONE, shapes.toArray(new VoxelShape[0]));
        }
    }

    private BooleanProperty getPropForDirection(Direction dir) {
        switch (dir) {
            case SOUTH:
                return (ATTACHED_SOUTH);
            case EAST:
                return (ATTACHED_EAST);
            case WEST:
                return (ATTACHED_WEST);
            case NORTH:
                return (ATTACHED_NORTH);
            case UP:
                return ATTACHED_UP;
            case DOWN:
                return ATTACHED_DOWN;
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction_1, BlockState neighbourState, IWorld world, BlockPos thisWire, BlockPos otherConnectable) {
        return state.with(getPropForDirection(direction_1), (
                !(neighbourState).isAir()
                        && neighbourState.getBlock() instanceof WireConnectable
                        // get opposite of direction so the WireConnectable can check from its perspective.
                        && ((WireConnectable) neighbourState.getBlock()).canWireConnect(world, direction_1.getOpposite(), thisWire, otherConnectable)
        ));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        super.appendProperties(stateFactory$Builder_1);
        stateFactory$Builder_1.with(ATTACHED_NORTH, ATTACHED_EAST, ATTACHED_SOUTH, ATTACHED_WEST, ATTACHED_UP, ATTACHED_DOWN);
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.MIPPED_CUTOUT;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public float getAmbientOcclusionLightLevel(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return 1.0F;
    }

    @Override
    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

    @Override
    public boolean canSuffocate(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return false;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return false;
    }

    @Override
    public boolean allowsSpawning(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityType<?> entityType_1) {
        return false;
    }

    @Override
    public boolean canWireConnect(IWorld world, Direction opposite, BlockPos thisWire, BlockPos otherConnectable) {
        return true;
    }

    @Override
    public void onBlockRemoved(BlockState blockState_1, World world, BlockPos pos, BlockState blockState_2, boolean boolean_1) {
        WireNetwork network = WireNetwork.getNetwork(world, pos);
        WireNetwork.NETWORKS.remove(network);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState blockState_1, LivingEntity livingEntity_1, ItemStack itemStack_1) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof WireNetworkPart) {
            ((WireNetworkPart) blockEntity).setNetwork(WireNetwork.findOrCreateNetwork(world, pos));
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new WireBlockEntity();
    }
}