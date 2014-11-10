import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class MyEllipse extends MyShape {

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
			g.fillOval(x1 - 10, y1 - 10, 20, 20);
			g.fillOval(x2 - 10, y2 - 10, 20, 20);
			g.fillOval(x1 - 10, y2 - 10, 20, 20);
			g.fillOval(x2 - 10, y1 - 10, 20, 20);
			g.setColor(Color.PINK);
			g.fillOval(x1 - 5, y1 - 5, 10, 10);
			g.fillOval(x2 - 5, y2 - 5, 10, 10);
			g.fillOval(x1 - 5, y2 - 5, 10, 10);
			g.fillOval(x2 - 5, y1 - 5, 10, 10);
			g.setColor(basecolor);
			g.setStroke(stroke);
		} else {
			g.setColor(basecolor);
		}
	}

}
