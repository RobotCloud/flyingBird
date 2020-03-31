package flyingBird;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//�ټ̳�   �� 3���ṹ
public class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	int state = 0;// ��Ϸ״̬ 0 1 2

	BufferedImage background;
	BufferedImage ready;
	BufferedImage over;

	Bird bird;// С�����

	Ground ground;// �������

	Column column1;// ��һ������
	Column column2;// �ڶ�������

	public MyPanel() {
		// ��ȡ3�ű���ͼƬ
		try {
			background = ImageIO.read(getClass().getResource("bg.png"));
			ready = ImageIO.read(getClass().getResource("start.png"));
			over = ImageIO.read(getClass().getResource("gameover.png"));

			bird = new Bird();// ����ʼ��ʱ����һ��С������bd
			ground = new Ground();// ��һ��������󣬸�ground

			column1 = new Column(1);// ����һ������
			column2 = new Column(2);// ���ڶ�������

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
		// ����
		g.drawImage(bird.img, bird.x - bird.width / 2, bird.y - bird.height / 2, null);

		// �����ӣ� ��������(�������ĵ�����Ͻ�)
		int x1 = column1.x - column1.width / 2;
		int y1 = column1.y - column1.height / 2;

		int x2 = column2.x - column2.width / 2;
		int y2 = column2.y - column2.height / 2;

		g.drawImage(column1.img, x1, y1, null);
		g.drawImage(column2.img, x2, y2, null);

		// ������
		g.drawImage(ground.img, ground.x, ground.y, null);

	}

	// 3---��̬Ч��
	public void action() {

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);

				if (state == 0) {
					state = 1;
					
				} else if (state == 1) {
					// С���ٶȻ�ԭ
					bird.v = bird.v0; // ��С��ĵ�ǰ�ٶȣ����µ��ڳ�ʼ�ٶ�
				} else if (state == 2) {
					state = 0;
					bird.x = 216;
					bird.y = 322;
					bird.v = bird.v0;// С����ٶȻ�ԭ
//					
				}
			}
		});

		while (true) {
			// �Ӷ����
			bird.wing();

			// ����״̬�£�С����������
			if (state == 1) {
				bird.fly();// ��С����Ȼ����
				ground.move();// �õ��棬����move�������õ������

				column1.move();
				column2.move();

				// ��û�з�����ײ
				if (bird.y >= 500 - bird.height / 2 || column1.crash(bird) || column2.crash(bird)) {
					state = 2;
				}
			}

			repaint();

			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

		}

	}

}
