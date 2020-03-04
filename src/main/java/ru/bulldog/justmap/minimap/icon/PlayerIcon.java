package ru.bulldog.justmap.minimap.icon;

import net.minecraft.entity.player.PlayerEntity;
import ru.bulldog.justmap.client.config.ClientParams;
import ru.bulldog.justmap.minimap.EntityModelRenderer;
import ru.bulldog.justmap.minimap.Minimap;
import ru.bulldog.justmap.util.Colors;
import ru.bulldog.justmap.util.DrawHelper;

public class PlayerIcon extends MapIcon<PlayerIcon> {
	
	protected boolean self;
	protected PlayerEntity player;
	
	public PlayerIcon(Minimap map, PlayerEntity player, boolean self) {
		super(map);
		this.self = self;
		this.player = player;
	}

	@Override
	public void draw(int mapX, int mapY, float rotation) {
		
		int drawX = mapX + x;
		int drawY = mapY + y;
		
		int size = ClientParams.entityIconSize;
		if (ClientParams.showPlayerHeads) {
			if (ClientParams.renderEntityModel) {
				EntityModelRenderer.renderModel(player, drawX, drawY);
			} else {
				PlayerHeadIcon.getIcon(player).draw(drawX, drawY);
			}
		} else {
			DrawHelper.fill(drawX, drawY, drawX + size, drawY + size, Colors.GREEN);
		}
			
		if (ClientParams.showPlayerNames) {
			DrawHelper.drawBoundedString(client.textRenderer, player.getName().getString(), drawX + size / 2, drawY - size / 2 - 10, 0, client.getWindow().getScaledWidth(), Colors.WHITE);
		}
	}
}
