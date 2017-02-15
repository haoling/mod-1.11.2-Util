package eyeq.util.client.renderer.block.statemap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class StateMapperNormal extends StateMapperBase {
    protected final ModelResourceLocation resource;

    public StateMapperNormal(ModelResourceLocation resource) {
        this.resource = resource;
    }

    public StateMapperNormal(ResourceLocation resource) {
        this(new ModelResourceLocation(resource, "normal"));
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
        return resource;
    }
}
