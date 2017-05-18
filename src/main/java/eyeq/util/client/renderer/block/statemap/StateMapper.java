package eyeq.util.client.renderer.block.statemap;

import com.google.common.collect.Maps;
import eyeq.util.client.renderer.ResourceLocationFactory;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class StateMapper extends StateMapperBase {
    protected final ResourceLocationFactory resource;
    protected final IProperty prefix;
    protected final IProperty postfix;
    protected final String name;
    protected final List<IProperty> ignores;

    public StateMapper(ResourceLocationFactory resource, IProperty prefix, IProperty postfix, String name, List<IProperty> ignores) {
        this.resource = resource;
        this.prefix = prefix;
        this.postfix = postfix;
        this.name = name;
        this.ignores = ignores;
    }

    public StateMapper(ResourceLocationFactory resource, IProperty prefix, String name, List<IProperty> ignores) {
        this(resource, prefix, null, name, ignores);
    }

    public StateMapper(ResourceLocationFactory resource, IProperty prefix, String name) {
        this(resource, prefix, name, new ArrayList<>());
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
        Map<IProperty<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
        String pre = "";
        if(prefix != null) {
            pre = prefix.getName(map.remove(prefix));
        }
        String post = "";
        if(postfix != null) {
            post = postfix.getName(map.remove(postfix));
        }
        ignores.forEach(map::remove);
        return resource.createModelResourceLocation(pre + name + post, getPropertyString(map));
    }
}
