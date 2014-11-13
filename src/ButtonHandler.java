import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {

	DrawingPanel drawpanel;

	/**
	 * The handler acts upon the DrawingPanel.
	 * 
	 * @param dp
	 *            : The DrawingPanel
	 */
	public ButtonHandler(DrawingPanel dp) {
		drawpanel = dp;
	}

	public void actionPerformed(ActionEvent e) {
		// Get the name of the button from the ActionEvent.
		String cmd = e.getActionCommand();
		// Perform corresponding action: Change mode of cursor
		// "Draw", "Draw Rectangle", "Draw ellipse", "Draw line", "Delete",
		// "no mode"
		drawpanel.mousemode = 0 ;
		drawpanel.removeMouseCursor();
		switch (cmd) {
		case "No Mode":
			this.drawpanel.mode = 0;
			break;
		case "Select":
			this.drawpanel.mode = 1;
			break;
		case "Draw Rectangle":
			this.drawpanel.mode = 2;
			break;
		case "Draw Ellipse":
			this.drawpanel.mode = 3;
			break;
		case "Draw Line":
			this.drawpanel.mode = 4;
			break;
		/*case "Delete":
			drawpanel.mode = 5;
			drawpanel.deleteSelectedShape();
			break;
		case "Fill":
			drawpanel.mode = 6;
			drawpanel.fillSelectedShape();
			break;
		*/
		case "Import Image":
			drawpanel.mode = 7;
			drawpanel.importImage();
			drawpanel.mode = 1;
			break;
		case "Insert Text":
			drawpanel.mode = 8;
			break;
		case "Clear":
			drawpanel.clearAll();
			break;
		default:
			System.err
					.println("The is an error in Buttonhandler actionPerformed");
		}
	}
}
