package flyingBird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @������������
 *
 */
public class Column {

	BufferedImage img;
	int x;// �������ĵ������
	int y;
	int width;
	int height;
	int gap;// ���
	int distance;// ��������֮��ľ���

	// n---����ǰ�����ǵڼ�������
	public Column(int n) {
		try {
			img = ImageIO.read(getClass().getResource("column.png"));

			width = img.getWidth();
			height = img.getHeight();

			gap = 180;
			distance = 300;

			x = 432 + width / 2 + (n - 1) * distance;
			y = (int) (Math.random() * (500 - 80 - gap / 2 - (80 + gap / 2)) + (80 + gap / 2));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �����ƶ�
	public void move() {
		x -= 5;

		// ������ӳ���
		if (x <= -width / 2) {
			x = -width / 2 + 2 * distance;
			// ���ӵ�y�����ĵ㣬�������
			y = (int) (Math.random() * (500 - 80 - gap / 2 - (80 + gap / 2)) + (80 + gap / 2));
		}
	}

	public boolean crash(Bird d) {
		// x��ײ��Χ
		int left = this.x - this.width / 2 - d.width / 2;
		int right = this.x + this.width / 2 + d.width / 2;

		if (d.x >= left && d.x <= right) {
			int up = this.y - this.gap / 2 + d.height / 2;
			int down = this.y + this.gap / 2 - d.height / 2;

			if (d.y > up && d.y < down) {
				return false;
			} else {
				return true;// ������
			}
		} else {
			return false;
		}
	}

}
