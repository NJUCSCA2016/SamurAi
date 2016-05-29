package team.csca.view.gameOver;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgGameOver;

public class JButtonReturnToLose extends DynamicButton{
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	private JPanelRankingListLose rankingList;
	
	public JButtonReturnToLose(){
		super(840, 162, 71, 71, null,null,null);
	}
	
	public void mouseClicked(MouseEvent e){
		frameMain.setContentPane(new JPanelGameLose(new JButtonGameBackToMain()));
		frameMain.revalidate();
	}
}
