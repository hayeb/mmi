import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Window extends JFrame {

	public Window() {

		// super() creates a function inherited from JFrame
		super();
		// initialize width & height to be used for windows size & internal
		// padding of the canvas
		int width = 1024;
		int height = 768;

		super.setTitle("BLABLA TITEL");
		super.setSize(new Dimension(width, height));

		// Centre the button on screen
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		DrawingPanel drawpanel = new DrawingPanel(width, height);
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
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		ColorButtonPanel cbp = new ColorButtonPanel();
		cbp.setLayout(new GridBagLayout()) ;
		sidebar.add(cbp);
		sidebar.add(new ButtonClass(drawpanel, "Import image.."));

		/*
		 * Populate the ColorButtonPanel with square buttons which will
		 * represent the colors.  TODO: Should work correctly.
		 */
		CreateColorButtons(drawpanel, cbp);

		/* Set the settings for the Bottom Panel */
		bottompanel.setLayout(new BorderLayout());

		/* Add the slider to the BottomPane; */
		StrokeSlider strokeSlider = new StrokeSlider(drawpanel);
		strokeSlider.addChangeListener(new StrokeSliderHandler(drawpanel));
		strokeSlider.setValue(3);
		strokeSlider.setMaximum(100) ;
		strokeSlider.setMinimum(1) ;
		bottompanel.add(strokeSlider);

		// TODO: Adjust border width/size
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
		c.fill = GridBagConstraints.HORIZONTAL ;
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

	private void CreateButtons(DrawingPanel rp, Panel ButtonBar) {
		String[] NameList = { "Select", "Draw Rectangle", "Draw Ellipse",
				"Draw Line", "Fill", "Delete", "Clear" };
		for (String s : NameList) {
			ButtonBar.add(new ButtonClass(rp, s));
		}
	}

	private void CreateColorButtons(DrawingPanel dp, ColorButtonPanel cp) {
		Color[] colorlist = {Color.BLACK, Color.WHITE, Color.BLUE, Color.YELLOW, Color.GRAY, Color.RED, Color.CYAN};
		String[] namelist = {"Black", "White", "Blue", "Yellow", "Gray", "Red", "Cyan"} ;
		int x = 0;
		int y = 0 ;
		
		for (int i = 0 ; i < colorlist.length; i += 1) {
			GridBagConstraints gbc = new GridBagConstraints() ;
			Color c = colorlist[i] ;
			gbc.gridx = x ;
			gbc.gridy = y ;
			gbc.ipadx = 40;
			gbc.ipady = 40 ;
			ColorButton cb = new ColorButton(dp, c) ;
			cb.setActionCommand(namelist[i]);
			cb.setBackground(c);
			cb.setOpaque(true);
			cb.setPreferredSize(new Dimension(40, 40));
			cp.add(cb, gbc) ;
		
			if (x >= 1) {
				x = 0 ;
				y += 1 ;
			}
			else {
				x += 1 ;
			}
		}
	}
}