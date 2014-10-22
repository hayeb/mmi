import javax.swing.JButton;


public class ImportImageButton extends JButton {
	DrawingPanel drawpanel ;
	
	public ImportImageButton(DrawingPanel dp, String string) {
		drawpanel = dp ;
		this.setText(string);
		this.addActionListener(new ImportImageButtonHandler(drawpanel));
	}

}
