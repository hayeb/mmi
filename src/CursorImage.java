import java.awt.Toolkit;

public class CursorImage {
	/*
	 * Functie: - Lijst aanmaken met alle mogelijke cursorimages
	 * 
	 */
	
	DrawingPanel drawpanel ;
	public CursorImage(DrawingPanel dp) {
		drawpanel = dp ;
	}
	public void foo(int imageindex) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		drawpanel.cursorimage = toolkit.getImage(drawpanel.list[imageindex]
				.getAbsolutePath());
		drawpanel.repaint();
	}
}
