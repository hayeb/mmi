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

		if (SwingUtilities.isLeftMouseButton(e)) {
			switch (DrawPanel.mode) {
			case 1:
				if (DrawPanel.selected >= 0) {
					// Move the currently selected object
					int corner = DrawPanel.getSelected().inResizeArea(x, y);
					if (DrawPanel.selected >= 0 && corner == 0 && !DrawPanel.resizing ) {
						System.out.println("Moving found shape.. "
								+ DrawPanel.selected);
						DrawPanel.moveShape(x, y);
					} // Resize the selected object
					else if (DrawPanel.selected >= 0 && DrawPanel.resizing) {
						System.out
								.println("Resizing currently selected object..");
						DrawPanel.reShape(x, y, DrawPanel.corner);
					}
				}
				break;
			case 2:
				DrawPanel.shape(x, y);
				break;
			case 3:
				DrawPanel.shape(x, y);
				break;
			case 4:
				DrawPanel.shape(x, y);
				break;
			}
		}
		DrawPanel.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
