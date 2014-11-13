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
	
	int width;
	int height;
	Window window;
	int mode = 0; // 0=nomode, 1=select, 2=drawrect, 3=drawellipse, 4=drawline,
					// 5=delete, 6=fill, 7=image, 8=text

	int corner = 0;
	int mousemode = 0;
	File[] list;
	Image cursorimages[] = {};

	public Point mousepoint = new Point(27, 22);
	public Point transmousepoint = new Point(0, 0);
	Cursor transcursor = null;
	Cursor mousecursor = null;

	Image cursorimage = null;
	Image transimage = null;
	Image cursor = null;
	Image cursorMet = null;
	Image cursorZonder = null;
	Image optionFill = null;
	Image optionLine = null;
	Image optionDelete = null;
	Image optionBackground = null;
	Image cursorLine = null;
	Image cursorFill = null;
	Image cursorBackground = null;
	Image cursorDelete = null;

	int selected = -1;
	int selectedstroke = 3;
	boolean resizing = false;
	boolean moving = false;

	int dx1, dy1, dx2, dy2;

	public Color fillcolor = null;
	public Color linecolor = Color.BLACK;

	public boolean isMouseOptions = false;
	public int mousex, mousey = 0;

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
		File cursorfile = new File("cursors//Cursor_zonder.png");
		cursorimage = toolkit.getImage(cursorfile.getAbsolutePath());
		transimage = toolkit.getImage(new File(
				"cursors//transparent_cursor.png").getAbsolutePath());
		mousecursor = toolkit.createCustomCursor(cursorimage, mousepoint,
				"defaultdraw");
		transcursor = toolkit.createCustomCursor(transimage, transmousepoint,
				"transcursor");
		cursorimage = toolkit.getImage(new File("cursors//Cursor_met.png")
				.getAbsolutePath());
		setCursor(mousecursor);
		loadImages();
	}

	public void loadImages() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		transimage = toolkit.getImage(new File(
				"cursors//transparent_cursor.png").getAbsolutePath());
		cursor = toolkit.getImage(new File("cursors//Cursor.png")
				.getAbsolutePath());
		cursorMet = toolkit.getImage(new File("cursors//Cursor_met.png")
				.getAbsolutePath());
		cursorZonder = toolkit.getImage(new File("cursors//Cursor_zonder.png")
				.getAbsolutePath());
		optionFill = toolkit.getImage(new File("cursors//O_fill.png")
				.getAbsolutePath());
		optionLine = toolkit.getImage(new File("cursors//O_line.png")
				.getAbsolutePath());
		optionBackground = toolkit.getImage(new File(
				"cursors//O_Background.png").getAbsolutePath());
		optionDelete = toolkit.getImage(new File("cursors//O_Bin.png")
				.getAbsolutePath());
		cursorFill = toolkit.getImage(new File("cursors//Cursor_fill.png")
				.getAbsolutePath());
		cursorLine = toolkit.getImage(new File("cursors//Cursor_line.png")
				.getAbsolutePath());
		cursorDelete = toolkit.getImage(new File("cursors//Cursor_bin.png")
				.getAbsolutePath());
		cursorBackground = toolkit.getImage(new File(
				"cursors//Cursor_background.png").getAbsolutePath());

	}

	public void rmbDelete(int x, int y) {
		int sel = rmbSelect(x, y);
		if (sel >= 0) {
			shapeslist.remove(sel);
			if (shapeslist.size() == 0 ) {
				selected = -1 ;
			}
		}
	}

	public void rmbFill(int x, int y) {
		int sel = rmbSelect(x, y);
		if (sel >= 0) {
			shapeslist.get(sel).isfilled = true ;
			shapeslist.get(sel).setFillColor(fillcolor);
		}
	}

	public void rmbFillLine(int x, int y) {
		int sel = rmbSelect(x, y);
		if (sel >= 0) {
			shapeslist.get(sel).setColor(linecolor);
		}
	}

	public void rmbBackground(int x, int y) {
		int sel = rmbSelect(x, y);
		if (sel>= 0) {
			shapeslist.add(0, shapeslist.remove(sel));
		}
	}

	public int rmbSelect(int x, int y) {
		System.out.println("Selecting.." + x + " " + y + ".") ;
		int index = -1;
		boolean notfound = false ;
		for (int i = 0; i < shapeslist.size() && !notfound; i += 1) {
			if (shapeslist.get(i).contains(x, y)) {
				MyShape s = shapeslist.remove(i);
				shapeslist.add(s);
				index = i ;
			}
		}
		return index;
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
			MyText t = new MyText(s, x, y);
			t.calcText();
			shapeslist.add(t);
			repaint();
		}
		setCursor(mousecursor);
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
		}
		else {
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
		setCursor(mousecursor);
	}

	public void removeMouseCursor() {
		mousex = -1;
		mousey = -1;
		isMouseOptions = false;
		setCursor(mousecursor);
	}

	/**
	 * Draws the current mouse cursor image at x, y and makes the actual cursor
	 * invisible.
	 * 
	 * @param x
	 * @param y
	 */
	public void drawMouseCursor(int x, int y) {
		mousex = x - 27;
		mousey = y - 21;
		isMouseOptions = true;
		cursorimage = cursorMet;
		setCursor(transcursor);
	}

	public void drawOptionCursor() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		System.out.println("Arrived in drawOptionsCursor");
		switch (mousemode) {
		case 1:
			Cursor c1 = tk.createCustomCursor(cursorLine, mousepoint,
					"lineCursor");
			setCursor(c1);
			isMouseOptions = false;
			break;
		case 2:
			Cursor c2 = tk.createCustomCursor(cursorFill, mousepoint,
					"lineCursor");
			setCursor(c2);
			isMouseOptions = false;
			break;
		case 3:
			Cursor c3 = tk.createCustomCursor(cursorBackground, mousepoint,
					"lineCursor");
			setCursor(c3);
			isMouseOptions = false;
			break;
		case 4:
			Cursor c4 = tk.createCustomCursor(cursorDelete, mousepoint,
					"lineCursor");
			setCursor(c4);
			isMouseOptions = false;
			break;
		default:
			System.out
					.println("There is an error in DrawingPanel drawOptionsCursor");

		}

	}

	public void mouseOptionChooser(int x, int y) {
		Double dist1 = Math.sqrt(Math.pow(x - 111, 2) + Math.pow(y - 60, 2));
		Double dist2 = Math.sqrt(Math.pow(x - 93, 2) + Math.pow(y - 89, 2));
		Double dist3 = Math.sqrt(Math.pow(x - 64, 2) + Math.pow(y - 110, 2));
		Double dist4 = Math.sqrt(Math.pow(x - 28, 2) + Math.pow(y - 115, 2));

		Double shortest = Math.min(Math.min(dist1, dist2),
				Math.min(dist3, dist4));

		if (Double.compare(shortest, dist1) == 0) {
			isMouseOptions = true;
			cursorimage = optionLine;
			mousemode = 1;
			mode = 0 ;
		}
		else if (Double.compare(shortest, dist2) == 0) {
			cursorimage = optionFill;
			mousemode = 2;
			mode = 0 ;
		}
		else if (Double.compare(shortest, dist3) == 0) {
			cursorimage = optionBackground;
			mousemode = 3;
			mode = 0 ;
		}
		else if (Double.compare(shortest, dist4) == 0) {
			cursorimage = optionDelete;
			mousemode = 4;
			mode = 0 ;
		}
		else {
			System.out.println("BAAAAAAAAAAAAAAAAAh");
			mousemode = 0;
		}
	}

	/**
	 * Selects an existing object which contains the position (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public void toolSelect(int x, int y) {
		System.out.println("toolSelect: " + x + "," + y );
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
		}
		else {
			removeSelections();
			selected = -1;
			repaint();
		}

	}

	public MyShape getSelected() {
		if (selected >= 0) {
			return shapeslist.get(selected);
		}
		else {
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
	public void shape(int x2, int y2) {
		int i = shapeslist.size() - 1;
		MyShape s = shapeslist.get(i);
		s.setX2(x2);
		s.setY2(y2);
		removeSelections();
		selectShape(s);
		repaint();
	}

	public void reShape(int x, int y, int corner) {
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
			System.err.println("There is an error in DrawingPanel reShape()");
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

		selectedshape.setX1(x - dx1);
		selectedshape.setY1(y - dy1);
		selectedshape.setX2(x + dx2);
		selectedshape.setY2(y + dy2);
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

		// Draw the image of the cursor if isMouseOption at x - 27, y - 21
		if (isMouseOptions) {
			g2d.drawImage(cursorimage, mousex, mousey, null);
		}
		// Draw the cursor
		// g2d.drawImage(cursorimage, mousex - 13, mousey - 12, null);
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
