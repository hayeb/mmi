import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseButtonHandler implements MouseListener {

	DrawingPanel DrawPanel;

	public MouseButtonHandler(DrawingPanel dp) {
		DrawPanel = dp;
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
		System.out.println("Current mode is: " + DrawPanel.mode
				+ ". Mouse clicked on location: " + x + "," + y); 
		
		/* Check if mousebutton is not RMB. */
		if (e.getButton() != 3) {
			switch (DrawPanel.mode) {
			case 1:
				System.out.println("Selecting object..");
				DrawPanel.toolSelect(x, y);
				if (DrawPanel.selected >= 0){
					DrawPanel.saveDXY(x, y);
				}
				break;
			case 2:
				//System.out.println("Drawing Rectangle..");
				DrawPanel.drawRect(x, y);
				break;
			case 3:
				//System.out.println("Drawing Ellipse..");
				DrawPanel.drawElli(x, y);
				break;
			case 4:
				//System.out.println("Drawing Line..");
				DrawPanel.drawLine(x, y);
				break;
			default:
				break;

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
