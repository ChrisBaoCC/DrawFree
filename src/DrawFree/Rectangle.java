package DrawFree;

import java.awt.*;

/**
 * <code>Rectangle</code> class. Can draw itself on the canvas.
 */
public class Rectangle implements Shape {
	// FIELDS //
	private final int x1, y1, x2, y2;
	
	Rectangle(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public String toString() {
		return String.format("Rectangle (%d, %d, %d, %d)", x1, y1, x2, y2);
	}
	
	@Override
	public void drawShape(Graphics2D g) {
		g.drawRect(x1, y1, x2-x1, y2-y1);
	}
}
