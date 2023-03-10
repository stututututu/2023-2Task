package base.comp;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class BaseLable extends JLabel {

	public BaseLable(String text) {
		// TODO Auto-generated constructor stub
		super(text);
	}
	public BaseLable setCenter() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}
	public BaseLable setFont(int size) {
		// TODO Auto-generated method stub

		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}

}
