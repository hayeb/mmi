import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MyImage extends MyShape {
	BufferedImage image;

	public MyImage(BufferedImage im, int x, int y, int x1, int y1) {
		super(x, y, x1, y1);
		orientation = 1;
		image = im;
	}

	@Override
	public boolean contains(int x, int y) {
		return ((x1 <= x) && (x <= x2)) && ((y1 <= y) && (y <= y2));
	}

	/*
	 * @Override public int inResizeArea(int x, int y) { return 0; }
	 */

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		g.drawImage(image, x1, y1, width, height, null);

		if (isselected) {
			// Also show that the user can resize the shape
			g.setColor(Color.PINK);
			g.setStroke(new BasicStroke(2));
			g.fillOval(x1 - 10, y1 - 10, 20, 20);
			g.fillOval(x2 - 10, y2 - 10, 20, 20);
			g.fillOval(x1 - 10, y2 - 10, 20, 20);
			g.fillOval(x2 - 10, y1 - 10, 20, 20);
			g.setColor(Color.RED);
			g.fillOval(x1 - 8, y1 - 8, 16, 16);
			g.fillOval(x2 - 8, y2 - 8, 16, 16);
			g.fillOval(x1 - 8, y2 - 8, 16, 16);
			g.fillOval(x2 - 8, y1 - 8, 16, 16);
			g.setColor(basecolor);
			g.setStroke(stroke);
		}

	}
}
