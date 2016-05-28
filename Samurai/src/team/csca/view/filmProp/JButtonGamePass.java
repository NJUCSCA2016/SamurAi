package team.csca.view.filmProp;

import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;

/**
 * 道具模式 
 * 返回按钮
 * 
 * @author Water
 *
 */
public class JButtonGamePass extends DynamicButton {
	private JPanelPropPattern fatherPanel;

	public JButtonGamePass(JPanelPropPattern fatherPanel){
		// TODO:调整位置
		super(865, 635, 140, 50, ImgButton.BACK_INIT, ImgButton.BACK_ENTER, ImgButton.BACK_CLICKED);
		this.fatherPanel = fatherPanel;
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);

		this.frame.remove(this.fatherPanel);
		this.frame.setContentPane(new JPanelHurdle());
		this.frame.revalidate();
		Player.stopMusic();
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("bgm1");
		}
		

	}
}

