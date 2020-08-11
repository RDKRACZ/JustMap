package ru.bulldog.justmap.client.widget;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.AbstractButtonWidget;


public class TitledButtonWidget<W extends AbstractButtonWidget> extends AbstractButtonWidget {
	public final W widget;
	public final String title;
	private final TextRenderer font;
	
	private int spacing = 3;
	
	public TitledButtonWidget(TextRenderer font, W widget, int x, int y, int width, int height, String message, String title) {
		super(x, y, width, height, message);
		this.widget = widget;
		this.title = title;
		this.font = font;
		
		update();
	}
	
	private void update() {
		int titleWidth = font.getStringWidth(title);
		int widgetWidth = widget.getWidth();
		int wx = x + width - widgetWidth;
		if (x + titleWidth + spacing > wx) {
			wx = x + titleWidth + spacing;
			widget.setWidth((x + width) - wx);
		}
		
		this.widget.x = wx;
		this.widget.y = y;
	}
	
	@Override
	public void render(int int_1, int int_2, float float_1) {
		this.drawString(font, title, x, y, 0xFFFFFFFF);
		widget.render(int_1, int_2, float_1);
	}
	
	@Override
	public void mouseMoved(double double_1, double double_2) {
		this.widget.mouseMoved(double_1, double_2);
	}
	
	@Override
	public boolean mouseScrolled(double double_1, double double_2, double double_3) {
		return this.widget.mouseScrolled(double_1, double_2, double_3);
	}
	
	@Override
	public boolean keyPressed(int int_1, int int_2, int int_3) {
		return this.widget.keyPressed(int_1, int_2, int_3);
	}
	
	@Override
	public boolean keyReleased(int int_1, int int_2, int int_3) {
		return this.widget.keyReleased(int_1, int_2, int_3);
	}
	
	@Override
	public boolean charTyped(char char_1, int int_1) {
		return this.widget.charTyped(char_1, int_1);
	}
	
	@Override
	public boolean changeFocus(boolean boolean_1) {
		return this.widget.changeFocus(boolean_1);
	}
	
	@Override
	public void renderButton(int int_1, int int_2, float float_1) {
		widget.renderButton(int_1, int_2, float_1);
	}
	
	@Override
	public void onClick(double double_1, double double_2) {
		this.widget.onClick(double_1, double_2);
	}
	
	@Override
	public void onRelease(double double_1, double double_2) {
		this.widget.onRelease(double_1, double_2);
	}
	
	@Override
	public boolean mouseClicked(double double_1, double double_2, int int_1) {
		return this.widget.mouseClicked(double_1, double_2, int_1);
	}
	
	@Override
	public boolean mouseReleased(double double_1, double double_2, int int_1) {
		return this.widget.mouseReleased(double_1, double_2, int_1);
	}
	
	@Override
	public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
		return this.widget.mouseDragged(double_1, double_2, int_1, double_3, double_4);
	}
	
	@Override
	public boolean isHovered() {
		return this.widget.isHovered();
	}
	
	@Override
	public boolean isMouseOver(double double_1, double double_2) {
		return this.widget.isMouseOver(double_1, double_2);
	}
	
	@Override
	public void renderToolTip(int int_1, int int_2) {
		widget.renderToolTip(int_1, int_2);
	}
	
	@Override
	public void playDownSound(SoundManager soundManager_1) {
		this.widget.playDownSound(soundManager_1);
	}
}
