package base.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class imageLabel extends JLabel {

	public imageLabel(String title, String path, int w, int d) {
		// TODO Auto-generated constructor stub
		super(title);
//		ImageIcon icon = new ImageIcon(path);
		ImageIcon icon = new ImageIcon("./datafiles/" + path + ".jpg");

		Image img = icon.getImage();

		img = img.getScaledInstance(w, d, Image.SCALE_SMOOTH);

		super.setIcon(new ImageIcon(img));

	}

	public imageLabel(String title, ImageIcon icon, int w, int h) {
		// TODO Auto-generated constructor stub
		super(title);

		Image img = icon.getImage();

		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		super.setIcon(new ImageIcon(img));

	}
	public imageLabel onlyImg() {
		
		
		
		
		return this;
	}

	public imageLabel setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

	public imageLabel setTitle(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}

	public imageLabel setWhite() {
		super.setForeground(Color.white);
		return this;
	}

	public imageLabel setTxtCenter() {
		super.setVerticalTextPosition(JLabel.CENTER);
		super.setHorizontalTextPosition(JLabel.CENTER);
		return this;
	}

	public imageLabel setbottom() {
		// TODO Auto-generated method stub
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;

	}

	public imageLabel setLine() {
		// TODO Auto-generated method stub
		super.setBorder(new LineBorder(Color.black));
		return this;

	}

}
