import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class MouseMovementHandler implements MouseMotionListener {

	DrawingPanel DrawPanel;

	public MouseMovementHandler(DrawingPanel dp) {
		DrawPanel = dp;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Change the last object created

		int x2 = e.getX();
		int y2 = e.getY();
		DrawPanel.mousex = x2 - 16;
		DrawPanel.mousey = y2 - 11;
		if (SwingUtilities.isLeftMouseButton(e)) {
			switch (DrawPanel.mode) {
			case 1:
				// Move the currently selected object
				if (DrawPanel.selected >= 0) {
					DrawPanel.moveShape(x2, y2);
					if (DrawPanel.getSelected().inSelected(e.getX(), e.getY())) {
						
					}
				}
				break;
			case 2:
				DrawPanel.reShape(x2, y2);
				break;
			case 3:
				DrawPanel.reShape(x2, y2);
				break;
			case 4:
				DrawPanel.reShape(x2, y2);
				break;
			}
		}
		DrawPanel.repaint() ;

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		DrawPanel.mousex = x - 16 ;
		DrawPanel.mousey = y - 11 ;
		DrawPanel.repaint() ;
	}

}
