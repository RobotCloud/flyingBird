package flyingBird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @������С����
 *
 */
public class Bird {

	BufferedImage img;

	BufferedImage[] imgs = new BufferedImage[8];// ����Ϊ8��ͼƬ����

	int x; // ������
	int y; // ������
	int width; // ���
	int height; // �߶�

	double v0; // ��ʼ�ٶ�
	double v; // ��ǰ�ٶ�
	double g; // �����ٶ�
	double t; // ��λʱ��

	int times = 0;// ��ʱ

	public Bird() {

		try {

			img = ImageIO.read(getClass().getResource("0.png"));

			// ��ȡ8��ͼƬ�ŵ�����
			for (int i = 0; i < 8; i++) {
				imgs[i] = ImageIO.read(getClass().getResource(i + ".png"));
			}

			width = img.getWidth();
			height = img.getHeight();
			x = 216;
			y = 322;

			v0 = 20;
			v = v0;// ��ǰ�ٶȣ�һ��ʼ��Ӧ�úͳ�ʼ�ٶ���ͬ
			g = 4.5;
			t = 0.25;

		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	public void fly() {
		// ���s(�����˶�-��������)
		double s = v * t - g * t * t / 2;

		// ����С��y����ı仯 
		y = (int) (y - s);

		// С��ǰ�ٶȵı仯
		v = v - g * t;
	}

	// �Ӷ����ķ���
	public void wing() {
		times++;
		img = imgs[times / 8 % 8];// �������ĳ��λ�ã�ȡ��ͼƬ����imgȥ��ʾ
	}

}
