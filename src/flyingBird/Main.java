package flyingBird;

import javax.swing.JFrame;

/**
 * 
 * @author MaxRobot
 * @���䣺15141954116@163.com 
 * @�޸����ڣ�2020.3.31
 * @�����������С��
 *
 */
public class Main {

	public static void main(String[] args) {

		JFrame jFrame = new JFrame();

		jFrame.setTitle("�����С��");
		jFrame.setSize(432, 644);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyPanel mp = new MyPanel();
		jFrame.add(mp);

		jFrame.setVisible(true);

		mp.action();// ����action�������û��涯����

	}

}
