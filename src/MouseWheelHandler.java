import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelHandler implements MouseWheelListener {

	DrawingPanel DrawPanel;

	public MouseWheelHandler(DrawingPanel dp) {
		DrawPanel = dp;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		int strokesize = DrawPanel.selectedstroke;
		strokesize = strokesize + e.getWheelRotation();
		if (strokesize > 0 && strokesize <= 100) {
			DrawPanel.selectedstroke = strokesize;
			DrawPanel.strokeTool();
		}
		if (DrawPanel.selected >= 0) {
			int fontsize = DrawPanel.getSelected().getFontSize();
			fontsize = fontsize + e.getWheelRotation();
			if (fontsize > 4 && strokesize <= 480) {
				DrawPanel.getSelected().setFontSize(fontsize);
			}
		}
	}

}
