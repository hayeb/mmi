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
	boolean drawn = false;

	public MouseButtonHandler(DrawingPanel dp, Window w) {
		DrawPanel = dp;
		window = w;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		/* Check if mousebutton is not RMB. */
		if (DrawPanel.mousemode == 0) {
			System.out.println("Mousemode in mouseClicked (1)" + DrawPanel.mousemode) ;
			if (e.getButton() != 3) {
				switch (DrawPanel.mode) {
				case 1:
					break;
				case 6:
					System.out.println("Filling object.. ");
					break;
				case 8:
					DrawPanel.insertText(x, y);
					DrawPanel.mode = 1;
					break;
				default:
					DrawPanel.toolSelect(x, y);

					break;
				}
			}
			else {
				DrawPanel.mode = 1;
				DrawPanel.removeSelections();
				DrawPanel.repaint();
			}
		}
		else {
			System.out.println("Mousemode in mouseClicked (2)" + DrawPanel.mousemode) ;
			switch (DrawPanel.mousemode) {
			
			case 1:
				// set line color of object which contains (x, y)
				DrawPanel.rmbFillLine(x, y);
				DrawPanel.repaint();
				break;
			case 2:
				// set fill color of object which contains (x, y)
				DrawPanel.rmbFill(x, y);
				DrawPanel.repaint();
				break;
			case 3:
				// Send the object to background which contains (x, y)
				DrawPanel.rmbBackground(x, y);
				DrawPanel.repaint();
				break;
			case 4:
				// Delete object which containt (x, y)
				DrawPanel.rmbDelete(x, y);
				DrawPanel.repaint();
				break;
			default:
				System.err
						.println("There is an error in MouseButtonHandler MouseClicked");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Check if in resize
		int x = e.getX();
		int y = e.getY();
		// System.out.println("Current mode is: " + DrawPanel.mode
		// + ". Mouse clicked on location: " + x + "," + y);

		/* Check if mousebutton is not RMB. */
		if (e.getButton() != 3) {
			System.out.println("Mousemode in mousePressed (1)" + DrawPanel.mousemode) ;
			switch (DrawPanel.mode) {
			case 1:
				if (DrawPanel.mousemode == 0) {
					System.out.println("Selecting object..");
					DrawPanel.toolSelect(x, y);
					if (DrawPanel.selected >= 0) {
						DrawPanel.saveDXY(x, y);
						if (DrawPanel.getSelected().inResizeArea(x, y) > 0) {
							DrawPanel.resizing = true;
							DrawPanel.corner = DrawPanel.getSelected()
									.inResizeArea(x, y);
						}
						else {
							DrawPanel.resizing = false;
							DrawPanel.corner = 0;
						}
					}
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
			// Draw the current mouse image at this position.
			System.out.println("Mousemode in mousePressed (2)" + DrawPanel.mousemode) ;
			if (!drawn) {
				DrawPanel.drawMouseCursor(x, y);
				// MAke the cursor invisible
				DrawPanel.mode = 1;
				DrawPanel.mousemode = 0 ;
				DrawPanel.repaint();
				drawn = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 3) {
			System.out.println("Mousemode in mouseReleased (1)" + DrawPanel.mousemode) ;
			// Remove the image drawing at position
			drawn = false;
			if (DrawPanel.mousemode == 0) {
				DrawPanel.removeMouseCursor();
				DrawPanel.cursorimage = DrawPanel.cursorZonder;
			}
			else if (DrawPanel.mousemode > 0) {
				DrawPanel.drawOptionCursor();
			}
			DrawPanel.repaint();
			// Reset the cursor image.
		}
		else if (e.getButton() == 1) {
			/*
			 * Reset the shape to our standard orientation. 1. Get the currently
			 * selected object 2. Reset its orientation so that (x1, y1) is the
			 * upperleft corner and (x2, y2) is the lowerright corner
			 */
			System.out.println("Mousemode in mouseREleased (2)" + DrawPanel.mousemode) ;
			if (DrawPanel.selected >= 0) {
				DrawPanel.getSelected().drawing = false;
				DrawPanel.getSelected().resetOrientation();
				DrawPanel.repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		File cursorfile = new File("cursors//Cursor_zonder.png");
		Image cursorimage = toolkit.getImage(cursorfile.getAbsolutePath());
		Cursor cursor = toolkit.createCustomCursor(cursorimage,
				DrawPanel.mousepoint, "InsideDrawPanel");
		window.setCursor(cursor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		File cursorfile = new File("cursors//Cursor.png");
		Image cursorimage = toolkit.getImage(cursorfile.getAbsolutePath());
		Cursor cursor = toolkit.createCustomCursor(cursorimage,
				DrawPanel.mousepoint, "outsideDrawPanel");
		window.setCursor(cursor);
	}

}
