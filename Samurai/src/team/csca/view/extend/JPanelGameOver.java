package team.csca.view.extend;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelGameOver extends JPanel{
	
	
	public JPanelGameOver(JButton buttonToAdd) {
		this.setLayout(null);
		repaint();
		this.add(buttonToAdd);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
	}
	
}
