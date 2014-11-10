import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;


public class MyText extends MyShape {

	private String text ;
	private Font font ;
	
	public MyText() {
		super() ;
	}
	
	public MyText(String s, int x, int y) {
		super(x,y,0,0) ;
		this.text = s ; 
	}
	/**
	 * This function should be called each time text is changed
	 */
	public void calcText() {
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g) ;
		int fontsize = 20 ;
		g.setFont(new Font("ComicSans", Font.PLAIN, fontsize));
		g.setColor(this.basecolor) ;
		FontMetrics metrics = g.getFontMetrics(g.getFont()) ;
		int hgt = metrics.getHeight() ;
		int adv = metrics.stringWidth(text) ;
		x2 = x1 + adv ;
		y2 = y1 + hgt ;
		g.drawString(text, x1, y1);
	}
}
