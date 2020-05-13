import javax.swing.*;

/**
 * <code>Scroller</code> class. Contains the Canvas; allows for scrolling and zooming.
 */
public class Scroller extends JScrollPane {
	// FIELDS //
	private Canvas canvas;
	private int vertScrollBarPolicy;
	private int horiScrollBarPolicy;
	
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
	}
}
