import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class MouseButtonHandler implements MouseListener {

	DrawingPanel DrawPanel;
	Window window;

	public MouseButtonHandler(DrawingPanel dp, Window w) {
		DrawPanel = dp;
		window = w;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		/* Check if mousebutton is not RMB. */
		if (e.getButton() != 3) {
			switch (DrawPanel.mode) {
			case 1:
				break;
			case 6:
				System.out.println("Filling object.. ");
				break;
			case 8:
				DrawPanel.insertText( x, y);
				break;
			default:
				DrawPanel.toolSelect(x, y);
				break;
			}
		} else {
			DrawPanel.mode = 1;
			DrawPanel.removeSelections();
			DrawPanel.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		// System.out.println("Current mode is: " + DrawPanel.mode
		// + ". Mouse clicked on location: " + x + "," + y);

		/* Check if mousebutton is not RMB. */
		if (e.getButton() != 3) {
			switch (DrawPanel.mode) {
			case 1:
				System.out.println("Selecting object..");
				DrawPanel.toolSelect(x, y);
				if (DrawPanel.selected >= 0) {
					DrawPanel.saveDXY(x, y);
				}
				break;
			case 2:
				// System.out.println("Drawing Rectangle..");
				DrawPanel.drawRect(x, y);
				break;
			case 3:
				// System.out.println("Drawing Ellipse..");
				DrawPanel.drawElli(x, y);
				break;
			case 4:
				// System.out.println("Drawing Line..");
				DrawPanel.drawLine(x, y);
				break;
			default:
				break;

			}

		}
		if (e.getButton() == 3) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			DrawPanel.cursorimage = toolkit.getImage(DrawPanel.list[5]
					.getAbsolutePath());
			DrawPanel.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 3) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			DrawPanel.cursorimage = toolkit.getImage(DrawPanel.list[4]
					.getAbsolutePath());
			DrawPanel.repaint();
		} else if (e.getButton() == 1) {
			/*
			 * Reset the shape to our standard orientation. 1. Get the currently
			 * selected object 2. Reset its orientation so that (x1, y1) is the
			 * upperleft corner and (x2, y2) is the lowerright corner
			 */
			if (DrawPanel.selected >= 0) {
				DrawPanel.getSelected().drawing = false ;
				DrawPanel.getSelected().resetOrientation();
				DrawPanel.repaint() ;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		File cursorfile = new File("cursors//transparent_cursor.png");
		Image cursorimage = toolkit.getImage(cursorfile.getAbsolutePath());
		Point hotspot = new Point(12, 12);
		Cursor cursor = toolkit.createCustomCursor(cursorimage, hotspot,
				"InsideDrawPanel");
		window.setCursor(cursor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		File cursorfile = new File("cursors//Cursor.png");
		Image cursorimage = toolkit.getImage(cursorfile.getAbsolutePath());
		Point hotspot = new Point(12, 12);
		Cursor cursor = toolkit.createCustomCursor(cursorimage, hotspot,
				"outsideDrawPanel");
		window.setCursor(cursor);
	}

}
