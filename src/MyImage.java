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

	@Override
	public int inResizeArea(int x, int y) {
		return 0;
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		g.drawImage(image, x1, y1, null);

		if (isselected) {
			g.setColor(Color.PINK);
			g.setStroke(new BasicStroke(6));
			g.drawLine(x1 - 10, y1 - 10, x1 + 20, y1 - 10);
			g.drawLine(x1 - 10, y1 - 10, x1 - 10, y1 + 20);
			g.drawLine(x2 + 10, y2 + 10, x2 + 10, y2 - 20);
			g.drawLine(x2 + 10, y2 + 10, x2 - 20, y2 + 10);
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(3));
			g.drawLine(x1 - 10, y1 - 10, x1 + 20, y1 - 10);
			g.drawLine(x1 - 10, y1 - 10, x1 - 10, y1 + 20);
			g.drawLine(x2 + 10, y2 + 10, x2 + 10, y2 - 20);
			g.drawLine(x2 + 10, y2 + 10, x2 - 20, y2 + 10);

		}

	}
}
