import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ColorButtonHandler implements ActionListener {
	DrawingPanel drawpanel;
	
	public ColorButtonHandler(DrawingPanel dp) {
		drawpanel = dp ;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Color c = Utils.StringToColor(e.getActionCommand().toString());
		drawpanel.setStrokeColor(c);
		drawpanel.setFillColor(c);
	}
	

}
