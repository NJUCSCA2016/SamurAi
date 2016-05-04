package team.csca.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import team.csca.controller.Controller;

/**
 * This class decides the main frame of this software 
 * and other classes about GUI are based on it 
 * 这个文件确定了软件的主框架 
 * 并且其他关于人机交互的文件都是基于这个文件来写的
 * 
 * @author Water
 *
 */
public class JFrameMain extends JFrame {
	/**
	 * 单例模式。
	 */
	public final static JFrameMain J_FRAME_MAIN = new JFrameMain();
	/**
	 * 鼠标
	 */
	private Cursor cursor;
	
	
//	private MainFrame frame;
	/**
	 * @author With You
	 * 此处不进行初始化
	 */
	private JFrameMain() {
		super();
	}
	/**
	 * @author With You
	 * @author Wan Zong
	 * 初始化
	 */
	public void init(){
		// 设置标题栏
				this.setTitle("Samurai");
				
				// 设置可以关闭窗口
				// TODO: 可能需要隐藏标题栏
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				// 设置尺寸
				this.setSize(1250, 700);
				// 为控制器添加对Frame的引用
				Controller.getController().setFrame(this);
				// 设置为自由布局
				this.setLayout(null);
				//设置不可改变大小
				this.setResizable(false);
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
				// 设置起始panel
				this.jpanelStartMovie();	
				// 设置可见
				this.setVisible(true);
	}

	/**
	 * 设置初始panel为startMovie
	 */
	private void jpanelStartMovie() {
		this.setContentPane(new JPanelStartMovie());
	}
}
