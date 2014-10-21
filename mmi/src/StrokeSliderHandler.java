import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class StrokeSliderHandler implements ChangeListener {
	
	DrawingPanel DrawPanel;
	public StrokeSliderHandler(DrawingPanel dp) {
		DrawPanel = dp ;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSlider source = (JSlider)e.getSource() ;
		if (source.getValueIsAdjusting()) {
			int strokesize = (int) source.getValue() ;
			DrawPanel.selectedstroke = strokesize ;
			DrawPanel.strokeTool() ;
		}
		
	}

}
