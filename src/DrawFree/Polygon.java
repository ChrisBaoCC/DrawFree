package DrawFree;

import java.awt.*;
import java.util.*;

/**
 * <code>Rectangle</code> class. Can draw itself on the canvas.
 */
public class Polygon extends java.awt.Polygon implements Shape {
	/**
	 * Creates a new <code>Polygon</code> with the specified parameters.
	 * @param x X coordinates for the points.
	 * @param y Y coordinates for the points.
	 */
	Polygon(ArrayList<Integer> x, ArrayList<Integer> y) {
		super(toArray(x), toArray(y), x.size());
	}
	
	/**
	 * Helper method to convert from an <code>ArrayList<Integer></code> to <code>int[]</code>.
	 * @param a The <code>ArrayList<Integer></code> to convert.
	 * @return The <code>int[]</code> representation of it.
	 */
	private static int[] toArray(ArrayList<Integer> a) {
		int[] b = new int[a.size()];
		for(int i = 0; i < a.size(); i++)
			b[i] = a.get(i);
		return b;
	}
	
	/**
	 * Returns information about the <code>Polygon</code>'s points.
	 * @return A <code>String</code> representation of this <code>Polygon</code>.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Polygon (");
		for(int i = 0; i < npoints-1; i++)
			sb.append(xpoints[i]).append(", ").append(ypoints[i]).append(", ");
		sb.append(xpoints[npoints-1]).append(", ").append(ypoints[npoints-1]);
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * Returns information about the <code>Pencil</code>.
	 * @return A <code>String</code> representation of this <code>Pencil</code>.
	 */
	@Override
	public String toCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("Polygon ");
		for(int i = 0; i < npoints; i++)
			sb.append(xpoints[i]).append(" ").append(ypoints[i]).append(" ");
		return sb.toString();
	}
	
	/**
	 * Draws this <code>Polygon</code>.
	 * @param g The <code>Graphics2D</code> instance used to draw.
	 */
	@Override
	public void drawShape(Graphics2D g) {
		g.fillPolygon(this);
	}
}
