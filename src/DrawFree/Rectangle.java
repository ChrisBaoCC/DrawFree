package DrawFree;

import java.awt.*;

/**
 * <code>Rectangle</code> class. Can draw itself on the canvas.
 */
public class Rectangle extends java.awt.Rectangle implements Shape {
	/**
	 * Creates a new <code>Rectangle</code> with the specified corner points.
	 * @param x1 X coordinate of the first corner.
	 * @param y1 Y coordinate of the first corner.
	 * @param x2 X coordinate of the second corner.
	 * @param y2 Y coordinate of the second corner.
	 */
	Rectangle(int x1, int y1, int x2, int y2) {
		// Converts corners format to x, y, width, height format
		super(Math.min(x1, x2), Math.min(y1, y2),
				Math.max(x1, x2)-Math.min(x1, x2),
				Math.max(y1, y2)-Math.min(y1, y2));
	}
	
	/**
	 * Returns information about the <code>Rectangle</code>'s points.
	 * @return A <code>String</code> representation of this <code>Rectangle</code>.
	 */
	@Override
	public String toString() {
		return String.format("Rectangle (%d, %d, %d, %d)", x, y, x+width, y+height);
	}
	
	/**
	 * Draws this <code>Rectangle</code>.
	 * @param g The <code>Graphics2D</code> instance used to draw.
	 */
	@Override
	public void drawShape(Graphics2D g) {
		g.fillRect(x, y, width, height);
	}
}
