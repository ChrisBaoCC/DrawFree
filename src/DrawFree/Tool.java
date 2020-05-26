package DrawFree;

/**
 * <code>DrawFree.Tool</code> enum. Represents each of the kinds of tools the user can use.
 */
public enum Tool {
	PENCIL,
	ERASER,
	ELLIPSE,
	RECTANGLE,
	POLYGON;
	
	public static String toString(Tool t) {
		switch(t) {
			case PENCIL: {
				return "Pencil";
			} case ERASER: {
				return "Eraser";
			} case ELLIPSE: {
				return "Ellipse";
			} case RECTANGLE: {
				return "Rect";
			} case POLYGON: {
				return "Poly";
			} default: {
				System.err.println("Invalid tool converted.");
				return null;
			}
		}
	}
	
	public static String getDescription(Tool t) {
		switch(t) {
			case PENCIL: {
				return "Click and drag to draw freehand.";
			} case ERASER: {
				return "Click and drag to paint out marks.";
			} case ELLIPSE: {
				return "Click and drag to create ellipses. Shift ⇧: circle";
			} case RECTANGLE: {
				return "Click and drag to create rectangles. Shift ⇧: square";
			} case POLYGON: {
				return "Click to place points. Click the first point to finish.";
			} default: {
				System.err.println("Invalid tool selected.");
				return null;
			}
		}
	}
	
	public static Tool toTool(String s) {
		switch(s) {
			case "Rect": {
				return Tool.RECTANGLE;
			} case "Poly": {
				return Tool.POLYGON;
			} default: {
				return Tool.valueOf(s.toUpperCase());
			}
		}
	}
}
