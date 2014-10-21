import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ImportImageButtonHandler implements ActionListener {
	DrawingPanel drawpanel ;
	public ImportImageButtonHandler(DrawingPanel dp) {
		drawpanel = dp ;
	}
	
	@Override	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	    drawpanel.importImage();
	   
	}

}