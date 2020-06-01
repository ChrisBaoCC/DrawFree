package DrawFree;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * <code>DrawFree.Canvas</code> class. The "canvas" the user "paints" on.
 */
public class Canvas extends JPanel {
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
	
	/**
	 * Default constructor. Initializes a <code>DrawFree.Canvas</code> with default width and height.
	 */
	Canvas() {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		this.setPreferredSize(new Dimension(width, height));
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
		
		// Draw objects
		
	}
}
