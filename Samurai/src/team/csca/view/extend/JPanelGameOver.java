package team.csca.view.extend;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelGameOver extends JPanel{
	
	protected LayerBackground background;
	
	public JPanelGameOver(JButton buttonToAdd) {
		this.setLayout(null);
		repaint();
		this.add(buttonToAdd);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		background.createWindow(g);
		super.paintComponent(g);
	}
	
}
