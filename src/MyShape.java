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
	boolean drawing = true;

	BasicStroke stroke = new BasicStroke(3);
	int fontsize = 20;

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

	public int inResizeArea(int x, int y) {
		// Standard resize area of standard shape: Each of the four corners, a
		// filled box of 15 * 15.
		boolean sq1 = (x1 - 10 < x && x1 + 10 > x && y1 - 10 < y && y1 + 10 > y); // Topleft
		boolean sq2 = (x1 - 10 < x && x1 + 10 > x && y2 - 10 < y && y2 + 10 > y); // bottomleft
		boolean sq3 = (x2 - 10 < x && x2 + 10 > x && y2 - 10 < y && y2 + 10 > y); // bottomright
		boolean sq4 = (x2 - 10 < x && x2 + 10 > x && y1 - 10 < y && y1 + 10 > y); // topright

		if (sq1) {
			return 1;
		} else if (sq2) {
			return 2;
		} else if (sq3) {
			return 3;
		} else if (sq4) {
			return 4;
		}
		// if not in one of the resize squares, return 0
		else {
			return 0;
		}
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
		// System.out.println("Surface:" + surface);
		if (surface < 250) {
			factor = 1.5;
		}

		// Make shape "bigger" with 1/2 * strokesize
		strokesize = (float) (0.5 * strokesize * factor);

		return ((x1 - strokesize <= x) && (x <= x2 + strokesize))
				&& ((y1 - strokesize <= y) && (y <= y2 + strokesize));

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
	}

	/**
	 * Resets/recalculates the orientation of this object. CHANGED: Now sets the
	 * orientation to our standard.
	 */
	public void resetOrientation() {
		if (x1 <= x2 && y1 <= y2) {
			// Do nothing, this is our standard orientation. Left this for
			// clarity.
		} else if (x1 <= x2 && y1 >= y2) {
			int tempy1 = y1;
			y1 = y2;
			y2 = tempy1;
		} else if (x1 >= x2 && y1 >= y2) {
			int tempx1 = x1;
			int tempy1 = y1;
			x1 = x2;
			y1 = y2;
			x2 = tempx1;
			y2 = tempy1;
		} else if (x1 >= x2 && y1 <= y2) {
			int tempx1 = x1;
			x1 = x2;
			x2 = tempx1;
		} else {
			System.err.print("There's something wrong with the orientation!");
		}
	}

	public void setStroke(int t) {
		stroke = new BasicStroke(t);
	}
	
	public void setFontSize(int f){
		fontsize = f;
	}
	
	public int getFontSize(){
		return fontsize;
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
