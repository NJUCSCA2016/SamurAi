/**
 * Date : Apr 3, 2016 5:34:55 PM
 */
package ui.startmovie;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.panelmain.PanelMain;

/**
 * @author Alone
 * Written by YYM
 */
public class PanelStartMovie extends JPanel implements Runnable{
	
	/**
	 * ��ǰͼƬ��
	 */
	private int num = 0;
	
	private JFrame frame ; 
	
	private PanelMain panelMain = null;
	
	/**
	 * ��ʼ���ſ�������
	 * ���Ž���������ɾ����Panel
	 */
	public PanelStartMovie(JFrame frame) {
		
		this.frame = frame;
		//���󽹵�
		this.requestFocus();
		//��ʼ����
		new Thread(this).start();
		
	}
	
	public void paint(Graphics g){
		System.out.println("����������Ļ�");
		//g.drawImage(imgMovie.image[num], 0, 0, null);
		
	}
	
	
	@Override
	public void run() {
		//���������棬�������棬����if()�д���ʱ��̫������sleep�ӳ�
		this.panelMain = new PanelMain();
		
		//��ʹ��forѭ����ֱ��while(true)ѭ�����˴�ֱ��Ӳ���롣
		while(true){
			
			
			repaint();
			//��һ��ʼ�ͷ�����
			
			if(this.num  == 0){
				//Ŷ��û��music �� ˭˵��
				System.out.println("Play Music");
				//this.playMusic();
			}
			System.out.println("Biu~Biu~Biu~");
			//���Կ��Ǹ����߳��ٶȡ��˴��ݲ��������Ҹ㶨��movie����Ū��
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.num++;
			//TODO : Ū��imgMovie �� �� ����num > imgMovie.length
			if(num >= 10){
				//ȡ������
				this.setFocusable(false);
				//�Ƴ���Panel
				this.frame.remove(this);
				this.frame.setContentPane(this.panelMain);
				//�Ƴ���contentpane������ػ�
				this.frame.revalidate();
				
				break;
			}
		}
		System.out.println("Movie finished . ");
	}
	
	
	

}
