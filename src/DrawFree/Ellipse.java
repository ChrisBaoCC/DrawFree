package DrawFree;

import java.awt.*;

/**
 * <code>Rectangle</code> class. Can draw itself on the canvas.
 */
public class Ellipse implements Shape {
	// FIELDS //
	private final int x, y, w, h;
	
	/**
	 * Creates a new <code>Ellipse</code> with the specified coordinates, width, and height.
	 * @param x X coordinate of the leftmost point.
	 * @param y Y coordinate of the topmost point.
	 * @param w Width.
	 * @param h Height.
	 */
	Ellipse(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	/**
	 * Returns information about the <code>Ellipse</code>'s points.
	 * @return A <code>String</code> representation of this <code>Ellipse</code>.
	 */
	@Override
	public String toString() {
		return String.format("Ellipse (%d, %d, %d, %d)", x, y, w, h);
	}
	
	/**
	 * Draws this <code>Ellipse</code>.
	 * @param g The <code>Graphics2D</code> instance used to draw.
	 */
	@Override
	public void drawShape(Graphics2D g) {
		g.drawOval(x, y, w, h);
	}
}
