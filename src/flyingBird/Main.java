package flyingBird;

import javax.swing.JFrame;

/**
 * 
 * @author MaxRobot
 * @邮箱：15141954116@163.com 
 * @修改日期：2020.3.31
 * @描述：飞翔的小鸟
 *
 */
public class Main {

	public static void main(String[] args) {

		JFrame jFrame = new JFrame();

		jFrame.setTitle("飞翔的小鸟");
		jFrame.setSize(432, 644);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyPanel mp = new MyPanel();
		jFrame.add(mp);

		jFrame.setVisible(true);

		mp.action();// 调用action方法，让画面动起来

	}

}
