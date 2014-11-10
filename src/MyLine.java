import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class MyLine extends MyShape {
	public MyLine() {
		super();
	}

	public MyLine(int x1, int y1, int x2, int y2) {
		/*
		 * Call the parent of this class (MyShape) with these arguments. This
		 * ensures we can use it's methods and variables. In this case: line
		 * from (x1, y1) to (x2, y2)
		 */
		super(x1, y1, x2, y2);
	}
	

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
		g.setColor(basecolor);
		g.draw(line);

		if (isselected) {
			g.setColor(Color.RED);
			g.fillOval(x1 - 10, y1 - 10, 20, 20);
			g.fillOval(x2 - 10, y2 - 10, 20, 20);
			g.setColor(Color.PINK);
			g.fillOval(x1 - 5, y1 - 5, 10, 10);
			g.fillOval(x2 - 5, y2 - 5, 10, 10);
		}
	}

	@Override
	public void resetOrientation() {
		System.out.println("Now uses this method..") ;
	}

	@Override
	public boolean contains(int x, int y) {	
		boolean case1, case2, case3, case4 = false ;
		
		case1 = (x1 < x && x < x2 && y1 < y && y < y2);
		case2 = (x1 < x && x < x2 && y1 > y && y > y2) ;
		case3 = (x1 > x && x > x2 && y1 > y && y > y2) ;
		case4 = (x1 > x && x > x2 && y1 < y && y < y2) ;
				
		return case1 || case2 || case3 || case4 ;

	}
}
