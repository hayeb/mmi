import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	/**
	 * @param rp: This button's parent
	 * @param title: The text displayed on this button
	 */
	public ButtonPanel(DrawingPanel rp, String title) {
		super();
		JButton b = new JButton(title) ;
		this.add(b) ;
		b.addActionListener(new ButtonHandler(rp)) ;
		
	}

}
