import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <code>Toolbar</code> class. Contains the different tools (i.e. Pencil) the user can use.
 */
public class Toolbar extends JPanel implements ActionListener {
	// CONSTANTS //
	final public static int NUM_TOOLS = 5;
	final private static Dimension DEFAULT_BUTTON_SIZE = new Dimension(75, 10);
	
	// FIELDS //
	private Frame parent;
	private JButton[] buttons;
	
	Toolbar(Frame parent) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.parent = parent;
		this.buttons = new JButton[NUM_TOOLS];
		for(int i = 0; i < Tool.values().length; i++)
			buttons[i] = new JButton(Tool.toString(Tool.values()[i]));
		for(JButton b : buttons) {
			b.setPreferredSize(DEFAULT_BUTTON_SIZE);
			b.setToolTipText(Tool.getDescription(Tool.toTool(b.getText())));
			this.add(b);
		}
	}
	
	/**
	 * Allows for changing of tools based on button press
	 * @param e ActionEvent from button press
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.changeTool(((JButton) e.getSource()).getText());
	}
}
