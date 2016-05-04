package team.csca.view;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ui.panelmain.PanelMain;

/**
 * 
 * @author YYM
 *
 */
public class JButtonPlay extends DynamicButton{

	private JPanel fatherPanel;
	
	public JButtonPlay(JPanel fatherPanel){
		super(350, 485, 300, 80, ImgButton.PLAY_1, ImgButton.PLAY_2, ImgButton.PLAY_3);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
//		this.setBounds(390, 495, 220, 60);
		
		this.setFocusable(false);
		//移除该Panel
		this.frame.remove(this);
		this.frame.setContentPane(new PanelMain());
		//移除旧contentpane后进行重画
		this.frame.revalidate();
		
	}
}
