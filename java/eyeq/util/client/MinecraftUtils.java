package eyeq.util.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.server.integrated.IntegratedServer;

public class MinecraftUtils {
    public static final Minecraft mc = Minecraft.getMinecraft();

    public static String getSkinType(EntityPlayer player) {
        if(player == null) {
            return "";
        }
        if(player instanceof AbstractClientPlayer) {
            return ((AbstractClientPlayer) player).getSkinType();
        }
        return DefaultPlayerSkin.getSkinType(player.getUniqueID());
    }

    public static EntityPlayerMP getPlayerMP() {
        return getPlayerMP(mc.player);
    }

    public static EntityPlayerMP getPlayerMP(EntityPlayerSP playerSP) {
        if(playerSP == null) {
            return null;
        }
        IntegratedServer server = mc.getIntegratedServer();
        if(server == null) {
            return null;
        }
        return server.getPlayerList().getPlayerByUUID(playerSP.getUniqueID());
    }

    // Exit Game
    // Use on ClientTickEvent
    public static void displayGameMenu() {
        // GuiIngameMenu ## actionPerformed(1)
        Minecraft mc = Minecraft.getMinecraft();
        boolean isServer = mc.isIntegratedServerRunning();
        boolean isRealms = mc.isConnectedToRealms();
        mc.world.sendQuittingDisconnectingPacket();
        mc.loadWorld(null);
        if(isServer) {
            mc.displayGuiScreen(new GuiMainMenu());
        } else if(isRealms) {
            RealmsBridge realmsbridge = new RealmsBridge();
            realmsbridge.switchToRealms(new GuiMainMenu());
        } else {
            mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
        }
    }
}
