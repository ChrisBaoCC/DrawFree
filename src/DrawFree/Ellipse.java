package DrawFree;

import java.awt.*;

/**
 * <code>Rectangle</code> class. Can draw itself on the canvas.
 */
public class Ellipse implements Shape {
	// FIELDS //
	private final int x, y, w, h;
	
	/**
	 * Creates a new <code>Ellipse</code> with the specified bounding coordinates.
	 * @param x1 X coordinate of the first corner.
	 * @param y1 Y coordinate of the first corner.
	 * @param x2 X coordinate of the second corner.
	 * @param y2 Y coordinate of the second corner.
	 */
	Ellipse(int x1, int y1, int x2, int y2) {
		// Converts corners format to x, y, width, height format
		x = Math.min(x1, x2);
		y = Math.min(y1, y2);
		w = Math.max(x1, x2)-Math.min(x1, x2);
		h = Math.max(y1, y2)-Math.min(y1, y2);
	}
	
	/**
	 * Returns information about the <code>Ellipse</code>'s points.
	 * @return A <code>String</code> representation of this <code>Ellipse</code>.
	 */
	@Override
	public String toString() {
		return String.format("Ellipse (%d, %d, %d, %d)", x, y, x+w, y+h);
	}
	
	/**
	 * Draws this <code>Ellipse</code>.
	 * @param g The <code>Graphics2D</code> instance used to draw.
	 */
	@Override
	public void drawShape(Graphics2D g) {
		g.fillOval(x, y, w, h);
	}
	
	/**
	 * Returns information about the <code>Ellipse</code>.
	 * @return A <code>String</code> representation of this <code>Ellipse</code>.
	 */
	@Override
	public String toCode() {
		return String.format("Ellipse %d %d %d %d", x, y, x+w, y+h);
	}
}
