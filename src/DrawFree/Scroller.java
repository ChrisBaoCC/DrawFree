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
	private int vertScrollBarPolicy;
	private int horiScrollBarPolicy;
	private int width;
	private int height;
	
	/**
	 * Constructor. Initializes default settings.
	 */
	Scroller() {
		canvas = new Canvas();
		this.setViewportView(canvas);
		
		this.vertScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		this.horiScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		this.setVerticalScrollBarPolicy(vertScrollBarPolicy);
		this.setHorizontalScrollBarPolicy(horiScrollBarPolicy);
		
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		this.setPreferredSize(new Dimension(width, height));
	}
}
