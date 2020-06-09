package DrawFree;

import java.awt.*;

/**
 * <code>Shape</code> interface. All things that can be drawn must implement this.
 */
interface Shape {
	void drawShape(Graphics2D g);
	String toCode();
}
