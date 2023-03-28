package base.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class BaseLabel extends JLabel {

	private int b;

	public BaseLabel(String text) {
		// TODO Auto-generated constructor stub
		super(text);
	}

	public BaseLabel setTitle(int size) {
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}
	public BaseLabel setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		
		return this;
	}

	public BaseLabel setline() {
		// TODO Auto-generated method stub
		super.setBorder(new LineBorder(Color.black));
		return this;
	}

	public BaseLabel setMatte(int t, int l, int b, int r) {
		// TODO Auto-generated method stub
		super.setBorder(new MatteBorder(t, l, b, r,Color.black));
		return this;
	}

	public BaseLabel pSize(int w, int d) {
		// TODO Auto-generated method stub
		super.setPreferredSize(new Dimension(w,d));
		return this;
	}
	public BaseLabel settitleLine(String title) {
		// TODO Auto-generated method stub
		super.setBorder(new TitledBorder(new LineBorder(Color.black), title));
		return this;
	}

}
