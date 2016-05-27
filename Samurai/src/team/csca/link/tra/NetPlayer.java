package team.csca.link.tra;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import team.csca.control.netControl.NetGameControl;

public class NetPlayer extends KeyAdapter{
	
	private int index;
	private NetGameControl control;
	
	public NetPlayer(int index , NetGameControl control) {
		this.index = index;
		this.control = control;
	}

	public int getIndex() {
		return this.index;
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				this.control.takeAction(5);
				break;
			case KeyEvent.VK_DOWN:
				this.control.takeAction(6);
				break;
			case KeyEvent.VK_LEFT:
				this.control.takeAction(7);
				break;
			case KeyEvent.VK_RIGHT:
				this.control.takeAction(8);
				break;
			case KeyEvent.VK_Q:
				this.control.takeAction(0);
				break;
			case KeyEvent.VK_R:
				this.control.takeAction(9);
				break;
			case KeyEvent.VK_E:
				this.control.takeAction(10);
				break;
			case KeyEvent.VK_W:
				this.control.takeAction(1);
				break;
			case KeyEvent.VK_S:
				this.control.takeAction(2);
				break;
			case KeyEvent.VK_A:
				this.control.takeAction(3);
				break;
			case KeyEvent.VK_D:
				this.control.takeAction(4);
				break;
			default:
				break;
			}

	}

	public KeyListener getListener() {
		return this;
	}

	
}
