package team.csca.view.gameOver;

import java.awt.Component;
import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;

public class JButtonReturnToDraw extends DynamicButton{
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	private JPanelRankingListDraw rankingList;
	
	public JButtonReturnToDraw(){
		super(840, 162, 71, 71, null,null,null);
	}
	
	public void mouseClicked(MouseEvent e){
		frameMain.setContentPane(new JPanelGameDraw());
		frameMain.revalidate();
	}
}
