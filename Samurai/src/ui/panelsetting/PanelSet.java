package ui.panelsetting;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.Background;
import sounds.Sounds;

public class PanelSet extends JPanel{

	private Sounds sound = Sounds.SOUNDS;
	
	private Background border;
	
	private Background center;
	
	private Background inside;
	
	private ButtonSound gameOn;
//	private ButtonSound gameOff;
	
	private ButtonSound backOn;
//	private ButtonSound backOff;
	
	public PanelSet(){
		
		this.setLayout(null);
		
		this.border = new Background(null);
		this.center = new Background(null);
		this.inside = new Background(null);
		
		this.add(this.border);
		this.add(this.center);
		this.add(this.inside);
		
		this.gameOn = new ButtonSound(0, 0);
		this.gameOn.addActionListener(new GameOn());
		
//		this.gameOff = new ButtonSound(0,0);
		
		this.backOn = new ButtonSound(0, 0);
		this.backOn.addActionListener(new BackOn());
		
//		this.backOff = new ButtonSound(0,0);
		
		this.add(gameOn);
//		this.add(gameOff);
		this.add(backOn);
//		this.add(backOff);
		
	}
	
	public void paint(Graphics g){
		
		this.border.creatBack(g);
		this.center.creatBack(g);
		this.inside.creatBack(g);
		
		super.paintComponents(g);
		
	}
	
	protected class GameOn implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			ButtonSound source = (ButtonSound)ae.getSource();
			if(sound.getGameSounds()){
				sound.setGameSounds(false);
				source.setButtonImg(null);
			}else{
				sound.setGameSounds(true);
				source.setButtonImg(null);
			}
		}
	}
	
	protected class BackOn implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			ButtonSound source = (ButtonSound)ae.getSource();
			if(sound.getBackSounds()){
				sound.setBackSounds(false);
				source.setButtonImg(null);
			}else{
				sound.setBackSounds(true);
				source.setButtonImg(null);
			}
		}
	}
	
}
