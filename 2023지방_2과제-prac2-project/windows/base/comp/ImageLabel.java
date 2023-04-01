package base.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class ImageLabel extends JLabel{

	public ImageLabel( String text,String path, int w, int h) {
		// TODO Auto-generated constructor stub
		super(text);
		ImageIcon icon = new ImageIcon("./datafiles/"+ path);
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		
		super.setIcon(new ImageIcon(img));
				
	}
	public ImageLabel( String text,ImageIcon icon, int w, int h) {
		// TODO Auto-generated constructor stub
		super(text);
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		
		super.setIcon(new ImageIcon(img));
		
		
	}
	public ImageLabel setLine() {
		super.setBorder(new LineBorder(Color.black));
		return this;
	}
	public ImageLabel setWhite() {
		super.setForeground(Color.white);
		return this;
	}
	public ImageLabel setSize(int size) {
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}
	public ImageLabel setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}
	public ImageLabel setTextCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.CENTER);
		return this;
	}
	public ImageLabel setTextBottom() {
		// TODO Auto-generated method stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;
	}

}
