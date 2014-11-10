import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class MyText extends MyShape {

	private String text;
	private Font font;

	public MyText() {
		super();
	}

	public MyText(String s, int x, int y) {
		super(x, y, 0, 0);
		this.text = s;
	}

	/**
	 * This function should be called each time text is changed
	 */
	public void calcText() {

	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		g.setFont(new Font("ComicSans", Font.PLAIN, fontsize));
		g.setColor(this.basecolor);
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		int hgt = metrics.getHeight();
		int adv = metrics.stringWidth(text);
		x2 = x1 + adv;
		y2 = y1 + hgt;
		g.drawString(text, x1, y1);

		if (isselected) {
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(3));
			g.drawLine(x1 - 10, y1 - 10, x1 + 20, y1 - 10);
			g.drawLine(x1 - 10, y1 - 10, x1 - 10, y1 + 20);
			g.drawLine(x2 + 10, y2 + 10, x2 + 10, y2 - 20);
			g.drawLine(x2 + 10, y2 + 10, x2 - 20, y2 + 10);
		}
	}
}
