import java.awt.Color;

public class Utils {
	/*
	 * Utility class. Add any functions that don't belong to another
	 * class/object here.
	 */

	public static Color StringToColor(String s) {
		System.out.println(s);

		switch (s) {
		case "Black":
			return Color.BLACK;
		case "Dark Gray":
			return Color.DARK_GRAY;
		case "Gray":
			return Color.GRAY;
		case "Light Gray":
			return Color.LIGHT_GRAY;
		case "White":
			return Color.WHITE;
		case "Pink":
			return Color.PINK;
		case "Red":
			return Color.RED;
		case "Orange":
			return Color.ORANGE;
		case "Yellow":
			return Color.YELLOW;
		case "Green":
			return Color.GREEN;
		case "Cyan":
			return Color.CYAN;
		case "Blue":
			return Color.BLUE;
		case "Magenta":
			return Color.MAGENTA;
		default:
			return Color.BLACK;
		}
	}
}
