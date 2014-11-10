import java.awt.Color;

import javax.swing.JButton;

public class ColorButton extends JButton {
	DrawingPanel DrawPanel;

	public ColorButton(DrawingPanel dp, Color c) {
		DrawPanel = dp;
		this.addActionListener(new ColorButtonHandler(dp));
	}
}
