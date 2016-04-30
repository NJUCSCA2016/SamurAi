package team.csca.view;

import java.awt.Graphics;
import java.awt.Image;

import team.csca.view.*;

public class LayerBackground extends Layer {
	
	private Image img;
	
	public LayerBackground(int x, int y, int w, int h, Image img) {
		super(x, y, w, h);
		this.img = img;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	
}
