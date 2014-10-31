import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class MyShape {

	protected int x1, y1, x2, y2;
	protected int width, height, startx, starty;
	protected int orientation; // 1, 2, 3, 4

	boolean isselected = false;
	boolean isfilled = false;

	BasicStroke stroke = new BasicStroke(3);

	Color basecolor = Color.BLACK;
	Color selectedcolor = Color.RED;
	Color fillcolor = null;

	public MyShape() {
		this(0, 0, 0, 0);
	}

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public MyShape(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return true if inside, false if not inside
	 */
	public boolean contains(int x, int y) {
		Float strokesize = this.stroke.getLineWidth();

		// Correct for size if shape is very small
		int surface = this.width * this.height;
		Double factor = 1.0;
		//System.out.println("Surface:" + surface);
		if (surface < 250) {
			factor = 1.5;
		}

		// Make shape "bigger" with 1/2 * strokesize
		strokesize = (float) (0.5 * strokesize * factor);

		// Only do calculations necessary according to orientation of the shape.
		switch (orientation) {
		case 1:
			return ((x1 - strokesize <= x) && (x <= x2 + strokesize))
					&& ((y1 - strokesize <= y) && (y <= y2 + strokesize));
		case 2:
			return ((x1 - strokesize <= x) && (x <= x2 + strokesize))
					&& ((y2 - strokesize <= y) && (y <= y1 + strokesize));
		case 3:
			return ((x2 - strokesize <= x) && (x <= x1 + strokesize))
					&& ((y2 - strokesize <= y) && (y <= y1 + strokesize));
		case 4:
			return ((x2 - strokesize <= x) && (x <= x1 + strokesize))
					&& ((y1 - strokesize <= y) && (y <= y2 + strokesize));
		default:
			System.err.println("There is an error in MyShape contains(x, y)");
			return true;
		}
	}

	/**
	 * 
	 * @param gs
	 */
	public void draw(Graphics2D g) {
		width = Math.abs(this.x1 - this.x2);
		height = Math.abs(this.y1 - this.y2);
		startx = Math.min(this.x1, this.x2);
		starty = Math.min(this.y1, this.y2);
		g.setStroke(stroke);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if (isselected) {
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(3));
			switch (orientation) {
			case 1:
				g.drawLine(x1 - 10, y1 - 10, x1 + 20, y1 - 10);
				g.drawLine(x1 - 10, y1 - 10, x1 - 10, y1 + 20);
				g.drawLine(x2 + 10, y2 + 10, x2 + 10, y2 - 20);
				g.drawLine(x2 + 10, y2 + 10, x2 - 20, y2 + 10);
				break;
			case 2:
				g.drawLine(x1 - 10, y2 - 10, x1 + 20, y2 - 10);
				g.drawLine(x1 - 10, y2 - 10, x1 - 10, y2 + 20);
				g.drawLine(x2 + 10, y1 + 10, x2 + 10, y1 - 20);
				g.drawLine(x2 + 10, y1 + 10, x2 - 20, y1 + 10);
				break;
			case 3:
				g.drawLine(x2 - 10, y2 - 10, x2 + 20, y2 - 10);
				g.drawLine(x2 - 10, y2 - 10, x2 - 10, y2 + 20);
				g.drawLine(x1 + 10, y1 + 10, x1 + 10, y1 - 20);
				g.drawLine(x1 + 10, y1 + 10, x1 - 20, y1 + 10);
				break;
			case 4:
				g.drawLine(x2 - 10, y1 - 10, x2 + 20, y1 - 10);
				g.drawLine(x2 - 10, y1 - 10, x2 - 10, y1 + 20);
				g.drawLine(x1 + 10, y2 + 10, x1 + 10, y2 - 20);
				g.drawLine(x1 + 10, y2 + 10, x1 - 20, y2 + 10);
				break;
			}
			g.setColor(basecolor);
			g.setStroke(stroke);
		}
		else {
			g.setColor(basecolor);
		}
	}

	/**
	 * Resets/recalculates the orientation of this object.
	 */
	public void resetOrientation() {
		if (x1 <= x2 && y1 <= y2) {
			orientation = 1;
		}
		else if (x1 <= x2 && y1 >= y2) {
			orientation = 2;
		}
		else if (x1 >= x2 && y1 >= y2) {
			orientation = 3;
		}
		else if (x1 >= x2 && y1 <= y2) {
			orientation = 4;
		}
		else {
			orientation = 0;
			System.err.print("There's something wrong with the orientation!");
		}
	}
	
	public boolean inSelected(int x, int y){
		
		switch (orientation) {
		case 1:
			
			break ;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		default:
			System.err.println("There is an error in iSelected");
		}
		return false ;
	}

	public void setStroke(int t) {
		stroke = new BasicStroke(t);
	}

	public void setColor(Color color) {
		basecolor = color;
	}

	public Color getFillColor() {
		return fillcolor;
	}

	public void setFillColor(Color c) {
		fillcolor = c;
	}

	public void setSelected() {
		isselected = true;
	}

	public void setNotSelected() {
		isselected = false;
	}

	public void setCoords(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public BasicStroke getStroke() {
		return this.stroke;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
}
