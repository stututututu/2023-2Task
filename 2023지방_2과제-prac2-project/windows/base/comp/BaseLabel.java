package base.comp;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;


public class BaseLabel extends JLabel {

	public BaseLabel(String text) {
		// TODO Auto-generated constructor stub
		super(text);
	}
	public BaseLabel setTitle(int size) {
		// TODO Auto-generated constructor stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}
	public BaseLabel setCenter() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}

}
