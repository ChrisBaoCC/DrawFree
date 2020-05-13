import javax.swing.*;
import java.awt.*;

/**
 * <code>Frame</code> class. Contains all the GUI for the app.
 */
public class Frame extends JFrame {
	// CONSTANTS //
	final private static int DEFAULT_WIDTH = 500;
	final private static int DEFAULT_HEIGHT = 500;
	
	final private static int MIN_WIDTH = 250;
	final private static int MIN_HEIGHT = 250;
	
	// FIELDS //
	private Scroller scroller;
	
	/**
	 * Constructor. Initializes settings.
	 */
	Frame() {
		this.setLayout(new BorderLayout());
		
		scroller = new Scroller();
		this.add(scroller, BorderLayout.CENTER);
		
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}
