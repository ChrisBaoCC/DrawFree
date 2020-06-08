package DrawFree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * <code>DrawFree.Canvas</code> class. The "canvas" the user "paints" on.
 */
public class Canvas extends JPanel implements MouseListener, KeyListener {
	// CONSTANTS //
	final private static int DEFAULT_WIDTH = 500;
	final private static int DEFAULT_HEIGHT = 500;
	final private static Object INTERPOLATION_HINT = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
	final private static Object ANTIALIASING_HINT = RenderingHints.VALUE_ANTIALIAS_ON;
	
	// FIELDS //
	private int width;
	private int height;
	private ArrayDeque<Shape> done;
	private ArrayDeque<Shape> undone;
	private Tool cursor;
	private ArrayList<Integer> xCoords;
	private ArrayList<Integer> yCoords;
	private boolean shiftKeyPressed;
	
	/**
	 * Default constructor. Initializes a <code>DrawFree.Canvas</code> with default width and height.
	 */
	Canvas(Scroller parent) {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		this.setPreferredSize(new Dimension(width, height));
		
		this.done = new ArrayDeque<>();
		this.undone = new ArrayDeque<>();
		xCoords = new ArrayList<>();
		yCoords = new ArrayList<>();
		
		shiftKeyPressed = false;
		
		addMouseListener(this);
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
			undone.push(done.pop());
		this.repaint();
	}
	
	/**
	 * Redoes an action.
	 */
	void redo() {
		if(!undone.isEmpty())
			done.push(undone.pop());
		this.repaint();
	}
	
	/**
	 * Opens a file.
	 */
	void open() {
		// TODO: implement
	}
	
	/**
	 * Saves the current state as a file.
	 */
	void save() {
		// TODO: implement
	}
	
	/**
	 * Saves the current picture as a .png file.
	 */
	void export() {
		// TODO: implement
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
				done.push(new Polygon(xCoords, yCoords));
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
		switch(cursor) {
			case RECTANGLE: {
			} case ELLIPSE: {
				xCoords.clear();
				xCoords.add(e.getX());
				yCoords.clear();
				yCoords.add(e.getY());
				break;
			}
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
				done.push(new Rectangle(
						xCoords.get(0),
						yCoords.get(0),
						e.getX(),
						e.getY()
				));
				this.repaint();
				break;
			} case ELLIPSE: {
				done.push(new Ellipse(
						xCoords.get(0),
						yCoords.get(0),
						e.getX(),
						e.getY()
				));
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
	 * Not used
	 * @param e -
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Handles keypress events.
	 * @param e the <code>MouseEvent</code> given.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(1);
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			shiftKeyPressed = true;
		System.out.println(shiftKeyPressed);
	}
	
	/**
	 * Handles key-release events.
	 * @param e the <code>MouseEvent</code> given.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(0);
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			shiftKeyPressed = false;
		System.out.println(shiftKeyPressed);
	}
}
