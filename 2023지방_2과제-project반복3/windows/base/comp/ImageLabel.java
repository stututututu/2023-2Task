package base.comp;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {

	public ImageLabel(String text, String path, int w, int h) {
		// TODO Auto-generated constructor stub
		super(text);
		ImageIcon icon = new ImageIcon("./datafiles/" + path + ".jpg");

		Image img = icon.getImage();

		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		super.setIcon(new ImageIcon(img));

	}
	public ImageLabel(String text, ImageIcon icon, int w, int h) {
		// TODO Auto-generated constructor stub
		super(text);
		
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		
		super.setIcon(new ImageIcon(img));
		
	}


}
