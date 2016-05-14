package team.csca.view.link.signIn;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class PlaintfField extends JTextField{
	
	final int IDLE = 0;
	final int HANGING = 1;
	
	LineBorder[] border = { new LineBorder(Color.WHITE), new LineBorder(Color.ORANGE)};
	
	public PlaintfField(int locateX , int locateY , int width , int height){
		
		setBounds(locateX, locateY, width, height);
		setOpaque(false);
		init();
		
	}
	
	private void init(){
		
		this.setBackground(new Color(0, 0, 0));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setForeground(Color.WHITE);
		this.setBorder(border[IDLE]);
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(getText().length() != 0){
					setText("");
				}
				changeBorderColor(HANGING);
			}
			public void mouseEntered(MouseEvent e){
				changeBorderColor(HANGING);
			}
			public void mouseReleased(MouseEvent e){
				changeBorderColor(IDLE);
			}
			public void mouseExited(MouseEvent e){
				changeBorderColor(IDLE);
			}
		});
		
	}
	
	private void changeBorderColor(int colorIndex){
		this.setBorder(border[colorIndex]);
	}
	
}
