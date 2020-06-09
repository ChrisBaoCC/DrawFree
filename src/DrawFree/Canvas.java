package DrawFree;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

/**
 * <code>DrawFree.Canvas</code> class. The "canvas" the user "paints" on.
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
	// CONSTANTS //
	final private static int DEFAULT_WIDTH = 500;
	final private static int DEFAULT_HEIGHT = 500;
	final private static Object INTERPOLATION_HINT = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
	final private static Object ANTIALIASING_HINT = RenderingHints.VALUE_ANTIALIAS_ON;
	
	// FIELDS //
	private int width;
	private int height;
	private ArrayList<Shape> done;
	private ArrayDeque<Shape> undone;
	private Tool cursor;
	private ArrayList<Integer> xCoords;
	private ArrayList<Integer> yCoords;
	private boolean mousePressed;
	private final JFileChooser jfc;
	
	/**
	 * Default constructor. Initializes a <code>DrawFree.Canvas</code> with default width and height.
	 */
	Canvas(Scroller parent) {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		this.setPreferredSize(new Dimension(width, height));
		
		this.done = new ArrayList<>();
		this.undone = new ArrayDeque<>();
		xCoords = new ArrayList<>();
		yCoords = new ArrayList<>();
		
		mousePressed = false;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		jfc = new JFileChooser();
	}
	
	/**
	 * Redraws the canvas.
	 * @param g The <code>Graphics</code> instance used to draw.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, INTERPOLATION_HINT);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, ANTIALIASING_HINT);
		
		// Actual background
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		
		// Stroke & fill
		g2.setStroke(new BasicStroke(0));
		g2.setColor(Color.BLACK);
		
		// Draw objects
		for(Shape s : done)
			s.drawShape(g2);
	}
	
	void updateCursor(Tool t) {
		cursor = t;
		xCoords.clear();
		yCoords.clear();
	}
	
	/**
	 * Undoes an action.
	 */
	void undo() {
		if(!done.isEmpty())
			undone.push(done.remove(done.size()-1));
		this.repaint();
	}
	
	/**
	 * Redoes an action.
	 */
	void redo() {
		if(!undone.isEmpty())
			done.add(undone.pop());
		this.repaint();
	}
	
	/**
	 * Opens a file.
	 */
	void open() {
		jfc.setDialogType(JFileChooser.OPEN_DIALOG);
		FileNameExtensionFilter fnef =
				new FileNameExtensionFilter(".df files", "df");
		jfc.setFileFilter(fnef);
		int returnVal = jfc.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			readFile(file);
		}
	}
	
	/**
	 * Reads file to add shapes
	 * @param f the <code>File</code> to read
	 */
	void readFile(File f) {
		try {
			this.done.clear();
			this.undone.clear();
			Scanner in = new Scanner(f);
			while(in.hasNext()) {
				String type = in.next();
				switch(type) {
					case "Pencil": {
						ArrayList<Integer> x = new ArrayList<>();
						ArrayList<Integer> y = new ArrayList<>();
						while(in.hasNextInt()) {
							x.add(in.nextInt());
							y.add(in.nextInt());
						}
						done.add(new Pencil(x, y));
						break;
					} case "Eraser": {
						ArrayList<Integer> x = new ArrayList<>();
						ArrayList<Integer> y = new ArrayList<>();
						while(in.hasNextInt()) {
							x.add(in.nextInt());
							y.add(in.nextInt());
						}
						done.add(new Eraser(x, y));
						break;
					} case "Rectangle": {
						int x1 = in.nextInt();
						int y1 = in.nextInt();
						int x2 = in.nextInt();
						int y2 = in.nextInt();
						done.add(new Rectangle(x1, y1, x2, y2));
						break;
					} case "Ellipse": {
						int x1 = in.nextInt();
						int y1 = in.nextInt();
						int x2 = in.nextInt();
						int y2 = in.nextInt();
						done.add(new Ellipse(x1, y1, x2, y2));
						break;
					} case "Polygon": {
						ArrayList<Integer> x = new ArrayList<>();
						ArrayList<Integer> y = new ArrayList<>();
						while(in.hasNextInt()) {
							x.add(in.nextInt());
							y.add(in.nextInt());
						}
						done.add(new Polygon(x, y));
						break;
					}
				}
			}
			in.close();
		} catch(FileNotFoundException e) {
			System.err.println("File not valid.");
		}
		this.repaint();
	}
	
	/**
	 * Saves the current state as a file.
	 */
	void save() {
		jfc.setDialogType(JFileChooser.SAVE_DIALOG);
		FileNameExtensionFilter fnef =
				new FileNameExtensionFilter(".df files", "df");
		jfc.setFileFilter(fnef);
		int returnVal = jfc.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			writeFile(file);
		}
	}
	
	/**
	 * Writes shapes to file
	 * @param f the <code>File</code> to write
	 */
	void writeFile(File f) {
		try {
			PrintWriter out = new PrintWriter(f);
			for(Shape s : done)
				out.println(s.toCode());
			out.close();
		} catch(FileNotFoundException e) {
			System.err.println("An error ocurred.");
		}
		this.repaint();
	}
	
	/**
	 * Saves the current picture as a .png file.
	 */
	void export() {
		jfc.setDialogType(JFileChooser.SAVE_DIALOG);
		FileNameExtensionFilter fnef =
				new FileNameExtensionFilter(".png files", "png");
		jfc.setFileFilter(fnef);
		int returnVal = jfc.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			drawFile(file);
		}
	}
	
	/**
	 * Saves image as file
	 * @param f the <code>File</code> to write
	 */
	void drawFile(File f) {
		try {
			BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
			this.paint(image.getGraphics());
			ImageIO.write(image, "png", f);
		} catch(IOException e) {
			System.err.println("An error ocurred.");
		}
		this.repaint();
	}
	
	/**
	 * Handles mouse-clicked events.
	 * @param e the <code>MouseEvent</code> given.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(cursor == Tool.POLYGON) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				xCoords.add(e.getX());
				yCoords.add(e.getY());
			} else {
				if(xCoords.isEmpty())
					return;
				done.add(new Polygon(xCoords, yCoords));
				this.repaint();
				xCoords.clear();
				yCoords.clear();
			}
		}
	}
	
	/**
	 * Handles mouse-pressed events.
	 * @param e the <code>MouseEvent</code> given.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(cursor == Tool.RECTANGLE || cursor == Tool.ELLIPSE) {
			xCoords.clear();
			xCoords.add(e.getX());
			yCoords.clear();
			yCoords.add(e.getY());
		} else if(cursor == Tool.PENCIL || cursor == Tool.ERASER) {
			xCoords.clear();
			xCoords.add(e.getX());
			yCoords.clear();
			yCoords.add(e.getY());
			mousePressed = true;
		}
	}
	
	/**
	 * Handles mouse-released events.
	 * @param e the <code>MouseEvent</code> given.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		switch(cursor) {
			case RECTANGLE: {
				done.add(new Rectangle(
						xCoords.get(0),
						yCoords.get(0),
						e.getX(),
						e.getY()
				));
				this.repaint();
				break;
			} case ELLIPSE: {
				done.add(new Ellipse(
						xCoords.get(0),
						yCoords.get(0),
						e.getX(),
						e.getY()
				));
				this.repaint();
				break;
			} case PENCIL: {
				mousePressed = false;
				done.add(new Pencil(xCoords, yCoords));
				this.repaint();
				break;
			} case ERASER: {
				mousePressed = false;
				done.add(new Eraser(xCoords, yCoords));
				this.repaint();
				break;
			}
		}
	}
	
	/**
	 * Not used
	 * @param e -
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	/**
	 * Not used
	 * @param e -
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * Handles mouse-dragged events.
	 * @param e the <code>MouseEvent</code> given.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if((cursor == Tool.PENCIL || cursor == Tool.ERASER) && mousePressed) {
			xCoords.add(e.getX());
			yCoords.add(e.getY());
		}
	}
	
	/**
	 * Not used.
	 * @param e -
	 */
	@Override
	public void mouseMoved(MouseEvent e) {}
}
