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

		int x = e.getX();
		int y = e.getY();
		// Adjust image of the cursor accordingly
		DrawPanel.mousex = x - 16;
		DrawPanel.mousey = y - 11;

		if (SwingUtilities.isLeftMouseButton(e)) {
			switch (DrawPanel.mode) {
			case 1:
				if (DrawPanel.selected >= 0) {
					// Move the currently selected object
					int corner = DrawPanel.getSelected().inResizeArea(x, y);
					if (DrawPanel.selected >= 0 && !(corner > 0)) {
						System.out.println("Moving found shape.. "
								+ DrawPanel.selected);
						DrawPanel.moveShape(x, y);
					} // Resize the selected object
					else if (DrawPanel.selected >= 0 && corner > 0) {
						System.out
								.println("Resizing currently selected object..");
						DrawPanel.resizeShape(x, y, corner) ;
					}
				}
				break;
			case 2:
				DrawPanel.reShape(x, y);
				break;
			case 3:
				DrawPanel.reShape(x, y);
				break;
			case 4:
				DrawPanel.reShape(x, y);
				break;
			}
		}
		DrawPanel.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		DrawPanel.mousex = x - 16;
		DrawPanel.mousey = y - 11;
		DrawPanel.repaint();
	}

}
