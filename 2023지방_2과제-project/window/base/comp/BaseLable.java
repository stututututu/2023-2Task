package base.comp;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class BaseLable extends JLabel{

	public BaseLable(String text) {
		// TODO Auto-generated constructor stub
		super(text);
	}
	public BaseLable setText(int size) {
		// TODO Auto-generated constructor stub
		super.setFont(new Font("hy견고딕m", Font.BOLD, size));
		return this;
	}
	public BaseLable setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

}
