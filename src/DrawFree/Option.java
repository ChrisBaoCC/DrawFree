package DrawFree;

/**
 * <code>DrawFree.Option</code> enum. Represents each of the buttons on the side panel.
 */
public enum Option {
	UNDO,
	REDO,
	OPEN,
	SAVE,
	EXPORT;
	
	public static String toString(Option t) {
		switch(t) {
			case UNDO: {
				return "Undo";
			} case REDO: {
				return "Redo";
			} case OPEN: {
				return "Open";
			} case SAVE: {
				return "Save";
			} case EXPORT: {
				return "Export";
			} default: {
				System.err.println("Invalid option converted.");
				return null;
			}
		}
	}
	
	public static String getDescription(Option t) {
		switch(t) {
			case UNDO: {
				return "Undo an action.";
			} case REDO: {
				return "Redo an undone action.";
			} case OPEN: {
				return "Open a .df file.";
			} case SAVE: {
				return "Save your art as a .df file.";
			} case EXPORT: {
				return "Save your art as a .png image.";
			} default: {
				System.err.println("Invalid tool selected.");
				return null;
			}
		}
	}
	
	public static Option toOption(String s) {
		return Option.valueOf(s.toUpperCase());
	}
}
