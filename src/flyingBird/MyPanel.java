package flyingBird;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//①继承   ② 3个结构
public class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	int state = 0;// 游戏状态 0 1 2

	BufferedImage background;
	BufferedImage ready;
	BufferedImage over;

	Bird bird;// 小鸟变量

	Ground ground;// 地面变量

	Column column1;// 第一个柱子
	Column column2;// 第二个柱子

	public MyPanel() {
		// 读取3张背景图片
		try {
			background = ImageIO.read(getClass().getResource("bg.png"));
			ready = ImageIO.read(getClass().getResource("start.png"));
			over = ImageIO.read(getClass().getResource("gameover.png"));

			bird = new Bird();// 面板初始化时，建一个小鸟对象给bd
			ground = new Ground();// 建一个地面对象，给ground

			column1 = new Column(1);// 建第一个柱子
			column2 = new Column(2);// 建第二个柱子

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(background, 0, 0, null);
		if (state == 0) {
			g.drawImage(ready, 0, 0, null);
			column1.x = 432 + column1.width / 2 + (1 - 1) * column1.distance;
			column1.y = (int) (Math.random() * (500 - 80 - column1.gap / 2 - (80 + column1.gap / 2)) + (80 + column1.gap / 2));
			column2.x = 432 + column2.width / 2 + (2 - 1) * column1.distance;
			column2.y = (int) (Math.random() * (500 - 80 - column2.gap / 2 - (80 + column2.gap / 2)) + (80 + column2.gap / 2));
		} else if (state == 2) {
			g.drawImage(over, 0, 0, null);
		}
		// 画鸟
		g.drawImage(bird.img, bird.x - bird.width / 2, bird.y - bird.height / 2, null);

		// 画柱子： 先算坐标(柱子中心点的左上角)
		int x1 = column1.x - column1.width / 2;
		int y1 = column1.y - column1.height / 2;

		int x2 = column2.x - column2.width / 2;
		int y2 = column2.y - column2.height / 2;

		g.drawImage(column1.img, x1, y1, null);
		g.drawImage(column2.img, x2, y2, null);

		// 画地面
		g.drawImage(ground.img, ground.x, ground.y, null);

	}

	// 3---动态效果
	public void action() {

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);

				if (state == 0) {
					state = 1;
					
				} else if (state == 1) {
					// 小鸟速度还原
					bird.v = bird.v0; // 让小鸟的当前速度，重新等于初始速度
				} else if (state == 2) {
					state = 0;
					bird.x = 216;
					bird.y = 322;
					bird.v = bird.v0;// 小鸟的速度还原
//					
				}
			}
		});

		while (true) {
			// 挥动翅膀
			bird.wing();

			// 运行状态下，小鸟自由落体
			if (state == 1) {
				bird.fly();// 让小鸟自然下落
				ground.move();// 用地面，调用move方法，让地面滚动

				column1.move();
				column2.move();

				// 有没有发生碰撞
				if (bird.y >= 500 - bird.height / 2 || column1.crash(bird) || column2.crash(bird)) {
					state = 2;
				}
			}

			repaint();

			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}

	}

}
