package zTest;

import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ChessBoard extends JFrame implements Runnable{

	int x,y;
	Image img;
	int[] area_flag = new int[9];

	
	public static void main(String[] args) {
		ChessBoard cb = new ChessBoard();
	}
	
	public ChessBoard() {
		super("ChessBoard");
		setSize(250,280);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("src/zTest/Logo.png");
		
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		
		setVisible(true);
		new Thread(this).start();
	}
	
	public void processWindowEvent(WindowEvent e){
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}
	
	public void processMouseEvent(MouseEvent e){
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			x = e.getX();
			y = e.getY();
		}
	}
	
	public void run(){
		while(true){
			if ((x>30)&&(x<90)&&(y>70)&&(y<130)&&(area_flag[0])==0) {
				area_flag[0]=1;
			}else if ((x>90)&&(x<150)&&(y>70)&&(y<130)&&(area_flag[1])==0) {
				area_flag[1]=1;
			}else if ((x>150)&&(x<210)&&(y>70)&&(y<130)&&(area_flag[2])==0) {
				area_flag[2]=1;
			}else if ((x>30)&&(x<90)&&(y>130)&&(y<190)&&(area_flag[3])==0) {
				area_flag[3]=1;
			}else if ((x>90)&&(x<150)&&(y>130)&&(y<190)&&(area_flag[4])==0) {
				area_flag[4]=1;
			}else if ((x>150)&&(x<210)&&(y>130)&&(y<190)&&(area_flag[5])==0) {
				area_flag[5]=1;
			}else if ((x>30)&&(x<90)&&(y>190)&&(y<250)&&(area_flag[6])==0) {
				area_flag[6]=1;
			}else if ((x>90)&&(x<150)&&(y>190)&&(y<250)&&(area_flag[7])==0){
				area_flag[7]=1;
			}else if ((x>150)&&(x<210)&&(y>190)&&(y<250)&&(area_flag[8])==0) {
				area_flag[8]=1;
			}
			repaint();
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}
	public void paint(Graphics g){
		g.drawLine(90, 50, 90, 230);
		g.drawLine(150, 50, 150, 230);
		g.drawLine(30, 110, 210, 110);
		g.drawLine(30, 170, 210, 170);
//		g.drawImage(img, 50, 50, null);
		
		for (int j = 0; j < area_flag.length; j++) {
			if (area_flag[0]==1) {
				g.drawImage(img, 42, 60, this);
			}
			if (area_flag[1]==1) {
				g.drawImage(img, 102, 60, this);
			}
			if (area_flag[2]==1) {
				g.drawImage(img, 164, 60, this);
			}
			if (area_flag[3]==1) {
				g.drawImage(img, 42, 125, this);
			}
			if (area_flag[4]==1) {
				g.drawImage(img, 102, 125, this);
			}
			if (area_flag[5]==1) {
				g.drawImage(img, 164, 125, this);
			}
			if (area_flag[6]==1) {
				g.drawImage(img, 42, 187, this);
			}
			if (area_flag[7]==1) {
				g.drawImage(img, 102, 187, this);
			}
			if (area_flag[8]==1) {
				g.drawImage(img, 164, 187, this);
			}
		}
	}
}
