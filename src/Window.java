import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Window extends JFrame {

	public Window() {

		// super() creates a function inherited from JFrame
		super();
		// initialize width & height to be used for the drawpanel & internal
		// padding of the canvas
		int width = 1366;
		int height = 768;
		super.setTitle("Awesome Software of Awesomeness");
		super.setSize(new Dimension(width, height));
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		DrawingPanel drawpanel = new DrawingPanel(this, width, height);
		Panel buttonbar = new Panel();
		Panel sidebar = new Panel();
		Panel bottompanel = new Panel();
		Panel rightpanel = new Panel();
		drawpanel.setBackground(Color.WHITE);

		// Create the button bar and make it arrange its components
		// horizontally. Also, populate it with its buttons
		buttonbar.setLayout(new BoxLayout(buttonbar, BoxLayout.X_AXIS));
		CreateButtons(drawpanel, buttonbar);

		/* Create the left sidebar and make it arrange its components vertically */
		sidebar.setLayout(new GridLayout(0, 1));
		ColorButtonPanel cbp = new ColorButtonPanel();
		cbp.setLayout(new GridBagLayout());
		sidebar.add(cbp);

		/*
		 * Populate the ColorButtonPanel with square buttons which will
		 * represent the colors.
		 */
		CreateColorButtons(drawpanel, cbp);

		c.gridx = 1;
		c.gridy = 0;
		super.getContentPane().add(buttonbar, c);
		c.gridx = 0;
		c.gridy = 1;
		super.getContentPane().add(sidebar, c);
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 50;
		super.getContentPane().add(rightpanel, c);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		super.getContentPane().add(bottompanel, c);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weighty = 1.0;
		c.weightx = 1.0;
		c.ipady = (width - 100);
		c.ipadx = (height - 100);
		super.getContentPane().add(drawpanel, c);

		super.setVisible(true);
	}

	/**
	 * Creates a set of buttons with corresponding commands which change the
	 * selected mode of the drawingpanel or import an image/text.
	 * 
	 * @param drawpanel
	 *            Drawingpanel of which the selected color is changed
	 * @param ButtonBar
	 *            Panel which contains the buttons
	 */
	private void CreateButtons(DrawingPanel drawpanel, Panel ButtonBar) {
		String[] NameList = { "Draw Rectangle", "Draw Ellipse", "Draw Line",
				"Insert Text", "Import Image", "Clear" };
		for (String s : NameList) {
			ButtonPanel bc = new ButtonPanel(drawpanel, s);
			ButtonBar.add(bc);
		}
	}

	/**
	 * Creates a set of buttons in panel cp, which have a corresponding color
	 * and change the selected color in dp.
	 * 
	 * @param dp
	 *            DrawingPanel of which selected color is changed
	 * @param cp
	 *            Panel which contains the buttons
	 */
	private void CreateColorButtons(DrawingPanel dp, ColorButtonPanel cp) {
		Color[] colorlist = { Color.BLACK, Color.DARK_GRAY, Color.GRAY,
				Color.LIGHT_GRAY, Color.WHITE, Color.PINK, Color.RED,
				Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN,
				Color.BLUE, Color.MAGENTA };
		String[] namelist = { "Black", "Dark Gray", "Gray", "Light Gray",
				"White", "Pink", "Red", "Orange", "Yellow", "Green", "Cyan",
				"Blue", "Magenta" };
		int x = 0;
		int y = 0;

		for (int i = 0; i < colorlist.length; i += 1) {
			GridBagConstraints gbc = new GridBagConstraints();
			Color c = colorlist[i];
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.ipadx = 40;
			gbc.ipady = 40;
			ColorButton cb = new ColorButton(dp, c);
			cb.setActionCommand(namelist[i]);
			cb.setBackground(c);
			cb.setOpaque(true);
			cb.setPreferredSize(new Dimension(40, 40));
			cp.add(cb, gbc);

			if (x >= 1) {
				x = 0;
				y += 1;
			}
			else {
				x += 1;
			}
		}
	}
}