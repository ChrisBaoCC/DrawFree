package DrawFree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <code>DrawFree.Optionbar</code> class. Contains the different options (i.e. Save) the user can use.
 */
public class Optionbar extends JPanel implements ActionListener {
	// CONSTANTS //
	final public static int NUM_OPTIONS = 5;
	final private static Dimension DEFAULT_BUTTON_SIZE = new Dimension(75, 10);
	
	// FIELDS //
	private Frame parent;
	private JButton[] buttons;
	
	Optionbar(Frame parent) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.parent = parent;
		this.buttons = new JButton[NUM_OPTIONS];
		for(int i = 0; i < Option.values().length; i++)
			buttons[i] = new JButton(Option.toString(Option.values()[i]));
		for(JButton b : buttons) {
			b.setPreferredSize(DEFAULT_BUTTON_SIZE);
			b.setToolTipText(Option.getDescription(Option.toOption(b.getText())));
			this.add(b);
			b.addActionListener(this);
		}
	}
	
	/**
	 * Allows for changing/execution of options based on button press
	 * @param e <code>ActionEvent</code> from button press
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.doOption(((JButton) e.getSource()).getText());
	}
}
