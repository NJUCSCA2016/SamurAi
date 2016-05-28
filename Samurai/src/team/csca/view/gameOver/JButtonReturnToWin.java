package team.csca.view.gameOver;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;

public class JButtonReturnToWin  extends DynamicButton{
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	private JPanelRankingListLose rankingList;
	
	public JButtonReturnToWin(){
		super(840, 162, 71, 71, null,null,null);
	}
	
	public void mouseClicked(MouseEvent e){
		frameMain.setContentPane(new JPanelGameWin());
		frameMain.revalidate();
	}
}
