import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class MyEllipse extends MyShape {
	
	public MyEllipse() {
		super() ;
	}
	
	public MyEllipse(int x, int y, int x2, int y2) {
		// Call the parent of this class (MyShape) with these arguments. This ensures we 
		// can use it's methods and variables.
		super(x, y, x2, y2);
	}
	@Override
	public void draw(Graphics2D g) {
		super.draw(g) ;
		Ellipse2D.Double ellipse = new Ellipse2D.Double(startx, starty, width, height) ;
		g.draw(ellipse) ;
		if (isfilled) {
		g.setColor(fillcolor);
		g.fill(ellipse);
		}
	}

}
