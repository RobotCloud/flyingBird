package flyingBird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @描述：柱子类
 *
 */
public class Column {

	BufferedImage img;
	int x;// 柱子中心点的坐标
	int y;
	int width;
	int height;
	int gap;// 垭口
	int distance;// 两个柱子之间的距离

	// n---代表当前建的是第几根柱子
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

	// 柱子移动
	public void move() {
		x -= 5;

		// 如果柱子出界
		if (x <= -width / 2) {
			x = -width / 2 + 2 * distance;
			// 柱子的y，中心点，重新随机
			y = (int) (Math.random() * (500 - 80 - gap / 2 - (80 + gap / 2)) + (80 + gap / 2));
		}
	}

	public boolean crash(Bird d) {
		// x碰撞范围
		int left = this.x - this.width / 2 - d.width / 2;
		int right = this.x + this.width / 2 + d.width / 2;

		if (d.x >= left && d.x <= right) {
			int up = this.y - this.gap / 2 + d.height / 2;
			int down = this.y + this.gap / 2 - d.height / 2;

			if (d.y > up && d.y < down) {
				return false;
			} else {
				return true;// 碰上了
			}
		} else {
			return false;
		}
	}

}
