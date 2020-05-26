package DrawFree;

import javax.swing.*;
import java.awt.*;

/**
 * <code>DrawFree.Frame</code> class. Contains all the GUI for the app.
 */
public class Frame extends JFrame {
	// CONSTANTS //
	final private static int DEFAULT_WIDTH = 750;
	final private static int DEFAULT_HEIGHT = 500;
	
	final private static int MIN_WIDTH = 250;
	final private static int MIN_HEIGHT = 250;
	
	// FIELDS //
	private Scroller scroller;
	private Toolbar toolbar;
	private Optionbar optionbar;
	
	private Tool cursor;
	
	/**
	 * Constructor. Initializes settings.
	 */
	Frame() {
		this.setLayout(new BorderLayout());
		
		initComponents();
		
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Creates components and adds them.
	 */
	private void initComponents() {
		toolbar = new Toolbar(this);
		this.add(toolbar, BorderLayout.WEST);
		
		scroller = new Scroller();
		this.add(scroller, BorderLayout.CENTER);
		
		optionbar = new Optionbar(this);
		this.add(optionbar, BorderLayout.EAST);
	}
	
	/**
	 * Changes the selected tool based on command given; called in DrawFree.Toolbar.
	 * @param selected the tool to switch to
	 */
	public void changeTool(String selected) {
		cursor = Tool.toTool(selected);
	}
	
	public void doOption(String selected) {
		
	}
}
