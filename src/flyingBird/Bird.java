package flyingBird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @描述：小鸟类
 *
 */
public class Bird {

	BufferedImage img;

	BufferedImage[] imgs = new BufferedImage[8];// 长度为8的图片数组

	int x; // 横坐标
	int y; // 纵坐标
	int width; // 宽度
	int height; // 高度

	double v0; // 初始速度
	double v; // 当前速度
	double g; // 重力速度
	double t; // 单位时间

	int times = 0;// 计时

	public Bird() {

		try {

			img = ImageIO.read(getClass().getResource("0.png"));

			// 读取8张图片放到数组
			for (int i = 0; i < 8; i++) {
				imgs[i] = ImageIO.read(getClass().getResource(i + ".png"));
			}

			width = img.getWidth();
			height = img.getHeight();
			x = 216;
			y = 322;

			v0 = 20;
			v = v0;// 当前速度，一开始，应该和初始速度相同
			g = 4.5;
			t = 0.25;

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void fly() {
		// 落差s(匀速运动-自由落体)
		double s = v * t - g * t * t / 2;

		// 计算小鸟y坐标的变化 
		y = (int) (y - s);

		// 小鸟当前速度的变化
		v = v - g * t;
	}

	// 挥动翅膀的方法
	public void wing() {
		times++;
		img = imgs[times / 8 % 8];// 从数组的某个位置，取出图片，给img去显示
	}

}
