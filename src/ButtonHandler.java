import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {

	DrawingPanel rect;

	/**
	 * The handler acts upon the DrawingPanel.
	 * @param rp: The DrawingPanel
	 */
	public ButtonHandler(DrawingPanel rp) {
		rect = rp;
	}
	
	public void actionPerformed(ActionEvent e) {
		// Get the name of the button from the ActionEvent.
		String cmd = e.getActionCommand();
		// Perform corresponding action: Change mode of cursor
		// "Draw", "Draw Rectangle", "Draw ellipse", "Draw line", "Delete", "no mode"
		switch (cmd) {
		case "No Mode":
			this.rect.mode = 0 ;
			break ;
		case "Select":
			this.rect.mode = 1;
			break ;
		case "Draw Rectangle":
			this.rect.mode = 2;
			break ;
		case "Draw Ellipse":
			this.rect.mode = 3;
			break ;
		case "Draw Line":
			this.rect.mode = 4;
			break;
		case "Delete":
			rect.mode = 5 ;
			rect.deleteSelectedShape() ;
			break;
		case "Clear":
			rect.clearAll();
			break;
		case "Fill":
			rect.mode = 6;
			rect.fillSelectedShape();
			break ;
		}
	}
}
