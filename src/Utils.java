import java.awt.Color;


public class Utils {

	
	public static Color StringToColor(String s) {
		System.out.println(s) ;
		
		switch (s) {
		case "Black":
			return Color.BLACK ;
		case "Blue":
			return Color.BLUE ;
		case "White":
			return Color.WHITE ;
		case "Yellow":
			return Color.YELLOW ;
		case "Red":
			return Color.RED ;
		case "Gray":
			return Color.GRAY ;
		case "Cyan":
			return Color.CYAN ;
		default:
			return Color.BLACK ;
		}
	}
}
