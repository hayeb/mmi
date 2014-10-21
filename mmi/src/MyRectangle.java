import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class MyRectangle extends MyShape {

	public MyRectangle() {
		super();
	}

	public MyRectangle(int x1, int y1, int x2, int y2) {
		// Call the parent of this class (MyShape) with these arguments. This
		// ensures we
		// can use it's methods and variables.
		super(x1, y1, x2, y2);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		Rectangle2D.Double rect = new Rectangle2D.Double(startx, starty, width,
				height);
		g.draw(rect);
		if (isfilled) {
			g.setColor(fillcolor);
			g.fill(rect);
		}
	}
}
