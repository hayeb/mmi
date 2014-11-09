import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class MyEllipse extends MyShape {

	int rx, ry = 0;

	public MyEllipse() {
		super();
	}

	public MyEllipse(int x, int y, int x2, int y2) {
		// Call the parent of this class (MyShape) with these arguments. This
		// ensures we
		// can use it's methods and variables.
		super(x, y, x2, y2);
	}

	@Override
	public int inResizeArea(int x, int y) {
		// Standard resize area of standard shape: Each of the four corners, a
		// filled box of 15 * 15.
		boolean sq1 = (x1 - 10 < x && x < x1 + 10 && ry - 10 < y && y < ry + 10); // Topleft
		boolean sq2 = (rx - 10 < x && x < rx + 10 && y2 - 10 < y && y < y2 + 10); // bottomleft
		boolean sq3 = (x2 - 10 < x && x < x2 + 10 && ry - 10 < y && y < ry + 10); // bottomright
		boolean sq4 = (rx - 10 < x && x < rx + 10 && y1 - 10 < y && y < y1 + 10); // topright

		if (sq1) {
			return 1;
		} else if (sq2) {
			return 2;
		} else if (sq3) {
			return 3;
		} else if (sq4) {
			return 4;
		} else { // if not in one of the resize squares, return 0
			return 0;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		Ellipse2D.Double ellipse = new Ellipse2D.Double(startx, starty, width,
				height);
		g.setColor(basecolor);
		g.draw(ellipse);
		if (isfilled) {
			g.setColor(fillcolor);
			g.fill(ellipse);
		}

		if (isselected && !drawing) {
			// Also show that the user can resize the shape
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(2));
			rx = (x1 + x2) / 2;
			ry = (y1 + y2) / 2;

			g.fillRect(rx - 10, y1 - 10, 20, 20);
			g.fillRect(x1 - 10, ry - 10, 20, 20);
			g.fillRect(rx - 10, y2 - 10, 20, 20);
			g.fillRect(x2 - 10, ry - 10, 20, 20);
			g.setColor(basecolor);
			g.setStroke(stroke);
		} else {
			g.setColor(basecolor);
		}
	}

}
