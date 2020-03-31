package flyingBird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @√Ë ˆ£∫µÿ√Ê¿‡
 *
 */
public class Ground {

	BufferedImage img;

	int x;
	int y;
	int width;
	int height;

	public Ground() {
		try {
			img = ImageIO.read(getClass().getResource("ground.png"));
			width = img.getWidth();
			height = img.getHeight();
			x = 0;
			y = 500;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void move() {
		x -= 10;
		if (x <= -368) {
			x = 0;
		}
	}

}
