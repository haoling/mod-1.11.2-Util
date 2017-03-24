package eyeq.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;

public abstract class UBlockDummy extends Block {
    public UBlockDummy(Material material) {
        super(material);
    }

    public abstract Block getOriginalBlock();

    @Override
    public MapColor getMapColor(IBlockState state) {
        return getOriginalBlock().getMapColor(state);
    }

    public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getOriginalBlock().getLightOpacity(state, world, pos);
    }

    @Override
    public int getLightOpacity(IBlockState state) {
        return getOriginalBlock().getLightOpacity(state);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return getOriginalBlock().getMetaFromState(state);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return getOriginalBlock().isFullCube(state);
    }

    @Override
    public boolean getTickRandomly() {
        return getOriginalBlock().getTickRandomly();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return getOriginalBlock().getBoundingBox(state, source, pos);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
        return getOriginalBlock().getSelectedBoundingBox(state, world, pos);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getOriginalBlock().getCollisionBoundingBox(state, world, pos);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return getOriginalBlock().isOpaqueCube(state);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState original = getOriginalBlock().getStateFromMeta(meta);
        IBlockState state = getDefaultState();
        for(IProperty property : original.getPropertyKeys()) {
            state = state.withProperty(property, original.getValue(property));
        }
        return state;
    }

    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState original = getOriginalBlock().getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
        IBlockState state = getDefaultState();
        for(IProperty property : original.getPropertyKeys()) {
            state = state.withProperty(property, original.getValue(property));
        }
        return state;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return getOriginalBlock().getBlockLayer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
        getOriginalBlock().getSubBlocks(item, tab, list);
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return getOriginalBlock().getItem(world, pos, state);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        Collection<IProperty<?>> properties = getOriginalBlock().getBlockState().getProperties();
        return new BlockStateContainer(this, properties.toArray(new IProperty[properties.size()]));
    }

    @Override
    public SoundType getSoundType() {
        return getOriginalBlock().getSoundType();
    }
}
