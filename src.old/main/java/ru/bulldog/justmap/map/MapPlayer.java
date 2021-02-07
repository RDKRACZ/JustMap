package ru.bulldog.justmap.map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.PlayerSkinTexture;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.Player;
import net.minecraft.util.ChatUtil;
import net.minecraft.util.ResourceLocation;

import ru.bulldog.justmap.map.icon.PlayerHeadIcon;

public class MapPlayer extends AbstractClientPlayerEntity {

	private final PlayerHeadIcon icon;
	
	public MapPlayer(ClientWorld world, Player player) {
		super(world, player.getGameProfile());
		
		this.icon = new PlayerHeadIcon();
		this.icon.getPlayerSkin(this);
	}
	
	public PlayerHeadIcon getIcon() {
		long now = System.currentTimeMillis();		
		if (!icon.success) {
			if (now - icon.lastCheck >= icon.delay) {
				this.icon.updatePlayerSkin(this);
			}
		} else if (now - icon.lastCheck >= 300000) {
			this.icon.updatePlayerSkin(this);
		}
		
		return this.icon;
	}
	
	public static ResourceTexture loadSkinTexture(ResourceLocation id, String playerName) {
		TextureManager textureManager = Minecraft.getInstance().getTextureManager();
		AbstractTexture abstractTexture = textureManager.getTexture(id);
		if (abstractTexture == null) {
			abstractTexture = new PlayerSkinTexture(null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", ChatUtil.stripTextFormat(playerName)), DefaultSkinHelper.getTexture(getOfflinePlayerUuid(playerName)), true, null);
			textureManager.registerTexture(id, (AbstractTexture) abstractTexture);
		}
		return (ResourceTexture) abstractTexture;
	}
}
