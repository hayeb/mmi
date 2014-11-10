import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	/**
	 * @param dp
	 *            : This button's parent
	 * @param title
	 *            : The text displayed on this button
	 */
	public ButtonPanel(DrawingPanel dp, String title) {
		super();
		JButton b = new JButton(title);
		this.add(b);
		b.addActionListener(new ButtonHandler(dp));
		//b.setSelectedIcon(selectedIcon);
		//b.setIcon(defaultIcon);

	}

}
