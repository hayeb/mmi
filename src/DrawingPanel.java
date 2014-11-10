import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawingPanel extends JPanel {
	/*
	 * TODO: Add move to background function/button (first in list) TODO:
	 * Possibly: Draw fixed shapes sizes/angels etc. TODO: Multiselect
	 */

	int width;
	int height;
	Window window;
	int mode = 0; // 0=nomode, 1=select, 2=drawrect, 3=drawellipse, 4=drawline,
					// 5=delete, 6=fill, 7=image, 8=text
	public int mousex = 0;
	public int mousey = 0;
	File[] list;
	Image cursorimages[] = {};

	Image cursorimage = null;
	int selected = -1;
	int selectedstroke = 3;

	int dx1, dy1, dx2, dy2;

	public Color fillcolor = null;
	public Color linecolor = Color.BLACK;

	List<MyShape> shapeslist = new ArrayList<MyShape>();

	/**
	 * Create a new object of class DrawingPanel
	 * 
	 * @param width
	 *            The width of the panel
	 * @param height
	 *            The height of the panel
	 */
	public DrawingPanel(Window w, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.window = w;
		this.addMouseListener(new MouseButtonHandler(this, w));
		this.addMouseMotionListener(new MouseMovementHandler(this));
		this.addMouseWheelListener(new MouseWheelHandler(this));

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		File cursorfile = new File("cursors//tranparent_cursor.png");
		cursorimage = toolkit.getImage(cursorfile.getAbsolutePath());

		Point hotspot = new Point(0, 0);
		Cursor cursor = toolkit.createCustomCursor(cursorimage, hotspot,
				"defaultdraw");
		setCursor(cursor);

		// Set image to the default cursor image
		FillCursorImageList();
	}

	public void FillCursorImageList() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		File f = new File("cursors");
		list = f.listFiles();

		cursorimage = toolkit.getImage(list[4].getPath());
	}

	/**
	 * Draw a rectangle at point (x, y) 1 pixel wide and 1 pixel high
	 * 
	 * @param x
	 *            x1-coordinate of the rectangle
	 * @param y
	 *            y1-coordinate of the rectangle
	 */
	public void drawRect(int x, int y) {
		MyRectangle rectangle = new MyRectangle(x, y, x, y);
		rectangle.setStroke(selectedstroke);
		rectangle.setColor(linecolor);
		shapeslist.add(rectangle);
		repaint();
	}

	/**
	 * Draw a ellipse at point (x, y) 1 pixel wide and 1 pixel high
	 * 
	 * @param x
	 *            x1-coordinate of the ellipse
	 * @param y
	 *            y1-coordinate of the ellipse
	 */
	public void drawElli(int x, int y) {
		MyEllipse ellipse = new MyEllipse(x, y, x, y);
		ellipse.setStroke(selectedstroke);
		ellipse.setColor(linecolor);
		shapeslist.add(ellipse);
		repaint();
	}

	/**
	 * Draw a line at point (x, y) 1 pixel wide and 1 pixel high
	 * 
	 * @param x
	 *            x1-coordinate of the line
	 * @param y
	 *            y1-coordinate of the line
	 */
	public void drawLine(int x, int y) {
		MyLine line = new MyLine(x, y, x, y);
		line.setStroke(selectedstroke);
		line.setColor(linecolor);
		shapeslist.add(line);
		repaint();
	}

	public void insertText(int x, int y) {
		String s = (String) JOptionPane.showInputDialog(window,
				"Please enter text:");
		if (s != null) {
			System.out.println("The string is: " + s);
			MyText t = new MyText(s, x, y);
			t.calcText();
			shapeslist.add(t);
			repaint();
		}
	}

	/**
	 * Shows a file chooser dialog and import an image (Should only be run when
	 * the "Import image" button is clicked
	 */
	public void importImage() {
		final JFileChooser fdialog = new JFileChooser();

		FileFilter imageFilter = new FileNameExtensionFilter("Image files",
				ImageIO.getReaderFileSuffixes());
		fdialog.addChoosableFileFilter(imageFilter);
		fdialog.setAcceptAllFileFilterUsed(false);

		File imagefile = null;
		fdialog.setDialogTitle("Choose an image..");
		int fileval = fdialog.showOpenDialog(window);

		if (fileval == JFileChooser.APPROVE_OPTION) {
			imagefile = fdialog.getSelectedFile();
		} else {
			System.err.println("Opening file cancelled of failed");
		}

		if (imagefile != null) {
			BufferedImage myPicture;
			try {
				myPicture = ImageIO.read(imagefile);
				MyImage image = new MyImage(myPicture, 0, 0,
						myPicture.getWidth(), myPicture.getHeight());
				shapeslist.add(image);
				repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Failed to load selected file as image");
			}
		}

	}

	/**
	 * Selects an existing object which contains the position (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public void toolSelect(int x, int y) {
		boolean notfound = true;
		for (int i = 0; i < shapeslist.size() && notfound; i += 1) {
			if (shapeslist.get(i).contains(x, y)
					|| shapeslist.get(i).inResizeArea(x, y) > 0) {
				System.out.println("Found an object..");
				// System.out.println("The index of the object is: " + i);
				// Deselect previous selected objects
				removeSelections();
				// Select currently clicked object
				selectShape(shapeslist.get(i));
				MyShape s = shapeslist.remove(i);
				shapeslist.add(s);
				selected = shapeslist.size() - 1;
				notfound = false;
			}
		}
		if (!(notfound)) {
			repaint();
		} else {
			removeSelections();
			selected = -1;
			repaint();
		}

	}

	public MyShape getSelected() {
		if (selected >= 0) {
			return shapeslist.get(selected);
		} else {
			return null;
		}
	}

	/**
	 * Deselects everyting on the screen
	 */
	public void removeSelections() {
		if (selected >= 0) {
			shapeslist.get(selected).setNotSelected();
			selected = -1;
		}
	}

	/**
	 * Delete the shape which is currently selected
	 * 
	 * @param x
	 * @param y
	 */
	public void deleteSelectedShape() {
		if (selected >= 0) {
			shapeslist.remove(selected);
			selected = -1;
			repaint();
		}
	}

	/**
	 * Sets the fill color to the color c.
	 * 
	 * @param c
	 */
	public void setFillColor(Color c) {
		fillcolor = c;
	}

	/**
	 * Fill the selected shape with the currently selected color
	 */
	public void fillSelectedShape() {
		if (selected >= 0) {
			shapeslist.get(selected).setFillColor(fillcolor);
			shapeslist.get(selected).isfilled = true;
			repaint();
		}
	}

	/**
	 * Set the coordinates of the bottom right of the last object in the
	 * shapeslist to (x2, y2)
	 * 
	 * @param x2
	 *            x coordinate of the bottom right corner
	 * @param y2
	 *            y coordinate of the bottom right corner
	 */
	public void reShape(int x2, int y2) {
		int i = shapeslist.size() - 1;
		MyShape s = shapeslist.get(i);
		s.setX2(x2);
		s.setY2(y2);
		removeSelections();
		selectShape(s);
		repaint();
	}

	public void resizeShape(int x, int y, int corner) {
		MyShape s = shapeslist.get(selected);

		switch (corner) {
		case 1:
			s.setX1(x);
			s.setY1(y);
			repaint();
			break;
		case 2:
			s.setX1(x);
			s.setY2(y);
			repaint();
			break;
		case 3:
			s.setX2(x);
			s.setY2(y);
			repaint();
			break;
		case 4:
			s.setX2(x);
			s.setY1(y);
			repaint();
			break;
		default:
			System.err
					.println("There is an error in DrawingPanel resizeShape()");
		}

	}

	/**
	 * Save the current difference between x and x1, x and x2, y and y1, y and
	 * y2. This code should only be run when a mouse button is pressed inside a
	 * object. The differences depend on the orientation of the shape. This is
	 * so to ensure a shape stays the same size when moved across the canvas.
	 * 
	 * @param x
	 *            x-coordinate of the mouse
	 * @param y
	 *            y-coordinate of the mouse
	 */
	public void saveDXY(int x, int y) {
		if (selected >= 0) {
			MyShape selectedshape = getSelected();
			if (selectedshape.contains(x, y)) {
				System.out.println("This shape indeed contains x, y");
				dx1 = x - selectedshape.getX1();
				dy1 = y - selectedshape.getY1();
				dx2 = selectedshape.getX2() - x;
				dy2 = selectedshape.getY2() - y;
			}
		}
	}

	/**
	 * Move the currently selected shape according to the position of the mouse.
	 * 
	 * @param x
	 *            x-coordinate of the mouse
	 * @param y
	 *            y-coordinate of the mouse
	 */
	public void moveShape(int x, int y) {
		MyShape selectedshape = shapeslist.get(selected);
		// System.out.println("Orientation: " + selectedshape.orientation);
		if (selectedshape.contains(x, y)) {
			selectedshape.setX1(x - dx1);
			selectedshape.setY1(y - dy1);
			selectedshape.setX2(x + dx2);
			selectedshape.setY2(y + dy2);
		}
		repaint();
	}

	/**
	 * Select the shape s, and change the value of strokeslider to
	 * 
	 * @param s
	 */
	public void selectShape(MyShape s) {
		s.setSelected();
		// We also need to set the value of the slider to the stroke thickness
		// of the selected shape
		selectedstroke = (int) s.getStroke().getLineWidth();
	}

	/**
	 * Changes the strokesize of the currently selected MyShape object to
	 * strokesize
	 */
	public void strokeTool() {
		if (selected >= 0 && selectedstroke > 0 && selectedstroke <= 100) {
			shapeslist.get(selected).setStroke(selectedstroke);
			repaint();
		}
	}

	/**
	 * Returns the currently selected color.
	 * 
	 * @return the currently selected color to draw shapes
	 */
	public Color getStrokeColor() {
		return linecolor;
	}

	/**
	 * Sets the color which is used to draw shapes to Color c. Also sets the
	 * color of the currently selected shape to c.
	 * 
	 * @param c
	 *            A color of type Color
	 */
	public void setStrokeColor(Color c) {
		this.linecolor = c;
		if (selected >= 0) {
			shapeslist.get(selected).setColor(c);
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		for (MyShape s : shapeslist) {
			s.draw(g2d);

		}
		// Draw the cursor
		g2d.drawImage(cursorimage, mousex - 13, mousey - 12, null);
	}

	/**
	 * Clears the whole canvas, deselects everything
	 */
	public void clearAll() {
		shapeslist.clear();
		selected = -1;
		mode = 0;
		repaint();
	}
}
