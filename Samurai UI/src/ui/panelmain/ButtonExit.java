/**
 * Date : Apr 4, 2016 6:10:44 PM
 */
package ui.panelmain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.SuperButton;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonExit extends SuperButton{
	
	
	public ButtonExit() {
		
		super(0,0,0,0);
		
		//TODO : 设置Icon . 
		this.setIcon(null);
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	
	
}
