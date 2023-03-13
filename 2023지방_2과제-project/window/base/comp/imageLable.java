package base.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;


public class imageLable extends JLabel {

	public imageLable() {
		// TODO Auto-generated constructor stub
		
	}
	public imageLable(String text, String path, int w, int h) {
		// TODO Auto-generated constructor stub
		super(text);
		ImageIcon icon = new ImageIcon("./datafiles/"+ path + ".jpg");
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
	}
		public imageLable(String text, ImageIcon icon, int w, int h) {
			// TODO Auto-generated constructor stub
			super(text);
			Image img = icon.getImage();
			
			img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			super.setIcon(new ImageIcon(img));
		
	}
	public imageLable Book(int i, int w, int h) {
		// TODO Auto-generated constructor stub
		ImageIcon icon = new ImageIcon("./datafiles/book/"+ i + ".jpg");
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
		return this;
	}
	public imageLable setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}
	public imageLable setTextCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.CENTER);
		
		return this;
	}
	public imageLable setTextSize(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY견고딕M", Font.BOLD, size));
		return this;
	}
	public imageLable setColor() {
		// TODO Auto-generated method stub
		super.setForeground(Color.white);
		return this;
	}
	public imageLable setline() {
		
		// TODO Auto-generated method stub
		super.setBorder(new LineBorder(Color.black));
		return this;
	}
	public imageLable setTextBottom() {
		// TODO Auto-generated method stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;
	}
}
	
	


