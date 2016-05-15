/**
 * Date : Mar 24, 2016 12:45:12 PM
 */
package team.csca.view.link.signIn;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
/**
 *
 * @author  来自一只可爱的小萝莉。我爱你wwww~
 *
 */

@SuppressWarnings("serial")
public class PlainpswField extends JPasswordField{
	
	public PlainpswField(Color idleColor, Color hangingColor){

		border[IDLE] = new LineBorder(idleColor);
		border[HANGING] = new LineBorder(hangingColor);
		
		init();
		
	}
	
	final int IDLE = 0;
	final int HANGING = 1;
	
	LineBorder[] border = { new LineBorder(Color.WHITE), new LineBorder(Color.ORANGE)};
	
	public PlainpswField(int locateX , int locateY , int width , int height){
		
		setBounds(locateX, locateY, width, height);
		setOpaque(false);
		init();
		setForeground(Color.BLACK);
	}
	
	private void init(){
		
		this.setBackground(new Color(0, 0, 0));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setForeground(Color.WHITE);
		this.setBorder(border[IDLE]);
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(getPassword().length != 0){
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

