package team.csca.view.gameOver;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;

public class JButtonRankingListWin extends DynamicButton{
	
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	public JButtonRankingListWin(){
		super(492, 440, 200, 180, 
				ImgButton.WATCH_RANKING_LIST_1,
				ImgButton.WATCH_RANKING_LIST_2, 
				ImgButton.WATCH_RANKING_LIST_3);
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
//		frameMain.remove(gameWin);
		frameMain.setContentPane(new JPanelRankingListWin());

		frameMain.revalidate();
	}
}

