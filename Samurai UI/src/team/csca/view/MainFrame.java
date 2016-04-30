package team.csca.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * This class decides the main frame of this software and other classes about
 * GUI are based on it 这个文件确定了软件的主框架 并且其他关于人机交互的文件都是基于这个文件来写的
 * 
 * @author Water
 *
 */
public class MainFrame extends JFrame {
	/**
	 * 鼠标
	 */
	private Cursor cursor;

	public MainFrame() {
		// 设置标题栏
		this.setTitle("Samurai");
		// 设置可见
		this.setVisible(true);
		// 设置可以关闭窗口
		// TODO: 可能需要隐藏标题栏
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 设置尺寸
		this.setSize(1250, 700);

		// 设置屏幕居中
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width - this.getWidth()) >> 1;
		int y = ((screen.height - this.getHeight()) >> 1) - 20;
		this.setLocation(x, y);

		// 设置图标
		this.setIconImage(ImgSystem.logo);

		// 设置默认鼠标
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(ImgSystem.cursor, 
				new Point(0, 0), "mycursor");
		this.setCursor(cursor);
		
		this.jpanelStartMovie();

	}

	private void jpanelStartMovie() {
		this.setContentPane(new StartMovie(this));
		
	}

	

}
