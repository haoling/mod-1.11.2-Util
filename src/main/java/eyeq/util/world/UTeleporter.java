package eyeq.util.world;

import java.util.*;

import eyeq.util.block.portal.IPortalPattern;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class UTeleporter extends Teleporter {
    public final WorldServer world;
    public final IPortalPattern pattern;
    protected final Random rand;
    protected final Long2ObjectMap<PortalPosition> coordinateCache = new Long2ObjectOpenHashMap<>(4096);

    public UTeleporter(WorldServer world, IPortalPattern pattern) {
        super(world);
        this.world = world;
        this.rand = new Random(world.getSeed());
        this.pattern = pattern;
        world.customTeleporters.add(this);
    }

    protected boolean isPortal(IBlockState state) {
        return this.pattern.isPortal(state);
    }

    protected IBlockState getFrame() {
        return this.pattern.getFrame();
    }

    protected IBlockState getAir() {
        return Blocks.AIR.getDefaultState();
    }

    protected IBlockState getPortal(EnumFacing.Axis axis) {
        return this.pattern.getPortal(axis);
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
        int poxX = MathHelper.floor(entity.posX);
        int posZ = MathHelper.floor(entity.posZ);
        long chunk = ChunkPos.asLong(poxX, posZ);
        boolean isMakeCatch = true;
        double distance = Double.MAX_VALUE;
        BlockPos portalPos = BlockPos.ORIGIN;
        if(this.coordinateCache.containsKey(chunk)) {
            isMakeCatch = false;
            distance = 0.0;
            Teleporter.PortalPosition portalPosition = this.coordinateCache.get(chunk);
            portalPosition.lastUpdateTime = this.world.getTotalWorldTime();
            portalPos = portalPosition;
        } else {
            BlockPos entityPos = new BlockPos(entity);
            int search = 128;
            for(int x = -search; x <= search; x++) {
                for(int z = -search; z <= search; z++) {
                    BlockPos pos = new BlockPos(entityPos.getX() + x, this.world.getActualHeight() - 1, entityPos.getZ() + z);
                    while(pos.getY() >= 0) {
                        if(isPortal(this.world.getBlockState(pos))) {
                            while(isPortal(this.world.getBlockState(pos.down()))) {
                                pos = pos.down();
                            }
                            double d1 = pos.distanceSq(entityPos);
                            if(d1 < distance) {
                                distance = d1;
                                portalPos = pos;
                            }
                        }
                        pos = pos.down();
                    }
                }
            }
        }
        if(distance == Double.MAX_VALUE) {
            return false;
        }
        if(isMakeCatch) {
            this.coordinateCache.put(chunk, new Teleporter.PortalPosition(portalPos, this.world.getTotalWorldTime()));
        }
        BlockPattern.PatternHelper patternHelper = Blocks.PORTAL.createPatternHelper(this.world, portalPos);
        BlockPos frontTopLeft = patternHelper.getFrontTopLeft();
        double y = (frontTopLeft.getY() + 1) - entity.getLastPortalVec().yCoord * patternHelper.getHeight();

        EnumFacing forwards = patternHelper.getForwards();
        double d0 = forwards.rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? 1 : 0;
        double d1 = (1.0 - entity.getLastPortalVec().xCoord) * patternHelper.getWidth() * forwards.rotateY().getAxisDirection().getOffset();
        double x;
        double z;
        if(forwards.getAxis() == EnumFacing.Axis.X) {
            x = portalPos.getX() + 0.5;
            z = d0 + d1 + frontTopLeft.getZ();
        } else {
            x = d0 + d1 + frontTopLeft.getX();
            z = portalPos.getZ() + 0.5;
        }

        EnumFacing entityDirection = entity.getTeleportDirection();
        int f1 = 0;
        int f2 = 0;
        if(forwards == entityDirection) {
            f1 = 1;
        } else if(forwards == entityDirection.getOpposite()) {
            f1 = -1;
        } else if(forwards == entityDirection.rotateY()) {
            f2 = 1;
        } else {
            f2 = -1;
        }
        double motionX = entity.motionX;
        double motionZ = entity.motionZ;
        entity.motionX = motionX * f1 - motionZ * f2;
        entity.motionZ = motionX * f2 + motionZ * f1;
        entity.rotationYaw = rotationYaw - (entityDirection.getOpposite().getHorizontalIndex() * 90) + (forwards.getHorizontalIndex() * 90);

        if(entity instanceof EntityPlayerMP) {
            ((EntityPlayerMP) entity).connection.setPlayerLocation(x, y, z, entity.rotationYaw, entity.rotationPitch);
        } else {
            entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
        }
        return true;
    }

    @Override
    public boolean makePortal(Entity entity) {
        int entityX = MathHelper.floor(entity.posX);
        int entityY = MathHelper.floor(entity.posY);
        int entityZ = MathHelper.floor(entity.posZ);
        int x0 = entityX;
        int y0 = entityY;
        int z0 = entityZ;
        int l1 = 0;
        int i2 = this.rand.nextInt(4);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        double distance = Double.MAX_VALUE;
        int search = 16;
        for(int x = entityX - search; x <= entityX + search; ++x) {
            double dx = x + 0.5 - entity.posX;
            for(int z = entityZ - search; z <= entityZ + search; ++z) {
                double dz = z + 0.5 - entity.posZ;
label0:
                for(int y = this.world.getActualHeight() - 1; y >= 0; --y) {
                    if(this.world.isAirBlock(mutableBlockPos.setPos(x, y, z))) {
                        while(y > 0 && this.world.isAirBlock(mutableBlockPos.setPos(x, y - 1, z))) {
                            --y;
                        }
                        for(int k3 = i2; k3 < i2 + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;
                            if(k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }
                            for(int i = 0; i < 3; ++i) {
                                for(int j = 0; j < 4; ++j) {
                                    for(int k = -1; k < 4; ++k) {
                                        int x1 = x + (j - 1) * l3 + i * i4;
                                        int y1 = y + k;
                                        int z1 = z + (j - 1) * i4 - i * l3;
                                        mutableBlockPos.setPos(x1, y1, z1);
                                        if(k < 0 && !this.world.getBlockState(mutableBlockPos).getMaterial().isSolid() || k >= 0 && !this.world.isAirBlock(mutableBlockPos)) {
                                            continue label0;
                                        }
                                    }
                                }
                            }
                            double dy = y + 0.5 - entity.posY;
                            double d0 = dx * dx + dy * dy + dz * dz;
                            if(d0 < distance) {
                                distance = d0;
                                x0 = x;
                                y0 = y;
                                z0 = z;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }
        if(distance == Double.MAX_VALUE) {
            for(int x = entityX - search; x <= entityX + search; ++x) {
                double dx = x + 0.5 - entity.posX;
                for(int z = entityZ - search; z <= entityZ + search; ++z) {
                    double dz = z + 0.5 - entity.posZ;
label1:
                    for(int y = this.world.getActualHeight() - 1; y >= 0; --y) {
                        if(this.world.isAirBlock(mutableBlockPos.setPos(x, y, z))) {
                            while(y > 0 && this.world.isAirBlock(mutableBlockPos.setPos(x, y - 1, z))) {
                                --y;
                            }
                            for(int i = i2; i < i2 + 2; ++i) {
                                int j8 = i % 2;
                                int j9 = 1 - j8;
                                for(int j = 0; j < 4; ++j) {
                                    for(int k = -1; k < 4; ++k) {
                                        int x1 = x + (j - 1) * j8;
                                        int y1 = y + k;
                                        int z1 = z + (j - 1) * j9;
                                        mutableBlockPos.setPos(x1, y1, z1);
                                        if(k < 0 && !this.world.getBlockState(mutableBlockPos).getMaterial().isSolid() || k >= 0 && !this.world.isAirBlock(mutableBlockPos)) {
                                            continue label1;
                                        }
                                    }
                                }
                                double dy = y + 0.5 - entity.posY;
                                double d0 = dx * dx + dy * dy + dz * dz;
                                if(d0 < distance) {
                                    distance = d0;
                                    x0 = x;
                                    y0 = y;
                                    z0 = z;
                                    l1 = i % 2;
                                }
                            }
                        }
                    }
                }
            }
        }
        int l6 = l1 % 2;
        int i3 = 1 - l6;
        if(l1 % 4 >= 2) {
            l6 = -l6;
            i3 = -i3;
        }
        if(distance == Double.MAX_VALUE) {
            y0 = MathHelper.clamp(y0, 70, this.world.getActualHeight() - 10);
            for(int i = -1; i <= 1; ++i) {
                for(int j = 1; j < 3; ++j) {
                    for(int k = -1; k < 3; ++k) {
                        int x = x0 + (j - 1) * l6 + i * i3;
                        int y = y0 + k;
                        int z = z0 + (j - 1) * i3 - i * l6;
                        boolean flag = k < 0;
                        this.world.setBlockState(new BlockPos(x, y, z), flag ? getFrame() : getAir());
                    }
                }
            }
        }
        IBlockState portal = getPortal(l6 == 0 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; ++j) {
                for(int k = -1; k < 4; ++k) {
                    int x = x0 + (j - 1) * l6;
                    int y = y0 + k;
                    int z = z0 + (j - 1) * i3;
                    boolean flag1 = j == 0 || j == 3 || k == -1 || k == 3;
                    this.world.setBlockState(new BlockPos(x, y, z), flag1 ? getFrame() : portal, 2);
                }
            }
            for(int j = 0; j < 4; ++j) {
                for(int k = -1; k < 4; ++k) {
                    int x = x0 + (j - 1) * l6;
                    int y = y0 + k;
                    int z = z0 + (j - 1) * i3;
                    BlockPos pos = new BlockPos(x, y, z);
                    this.world.notifyNeighborsOfStateChange(pos, this.world.getBlockState(pos).getBlock(), false);
                }
            }
        }
        return true;
    }

    @Override
    public void removeStalePortalLocations(long worldTime) {
        if(worldTime % 100L == 0L) {
            long i = worldTime - 300L;
            ObjectIterator<PortalPosition> iterator = this.coordinateCache.values().iterator();
            while(iterator.hasNext()) {
                Teleporter.PortalPosition portalPosition = iterator.next();
                if(portalPosition == null || portalPosition.lastUpdateTime < i) {
                    iterator.remove();
                }
            }
        }
    }
}