package DrawFree;

import javax.swing.*;
import java.awt.*;

/**
 * <code>DrawFree.Scroller</code> class. Contains the DrawFree.Canvas; allows for scrolling and zooming.
 */
public class Scroller extends JScrollPane {
	// CONSTANTS //
	final private static int DEFAULT_WIDTH = 500;
	final private static int DEFAULT_HEIGHT = 500;
	
	// FIELDS //
	private Canvas canvas;
	private int width;
	private int height;
	
	/**
	 * Constructor. Initializes default settings.
	 */
	Scroller() {
		canvas = new Canvas();
		this.setViewportView(canvas);
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		this.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Undoes an action in the canvas.
	 */
	void undo() {
		// TODO: implement
	}
	
	/**
	 * Redoes an action in the canvas.
	 */
	void redo() {
		// TODO: implement
	}
	
	/**
	 * Opens a file in the canvas.
	 */
	void open() {
		// TODO: implement
	}
	
	/**
	 * Saves the current state of the canvas.
	 */
	void save() {
		// TODO: implement
	}
	
	/**
	 * Saves a picture of the canvas.
	 */
	void export() {
		// TODO: implement
	}
}
