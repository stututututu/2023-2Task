package base.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class BaseLabel extends JLabel{

	public BaseLabel(String text) {
		// TODO Auto-generated constructor stub
		super(text);
		
		
	}

	public BaseLabel(Vector<String> vector) {
		// TODO Auto-generated constructor stub
	}

	public BaseLabel setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}
	public BaseLabel setTitle(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}
	public BaseLabel setLine() {
		// TODO Auto-generated method stub
		super.setBorder(new LineBorder(Color.black));
		return this;
	}

	public BaseLabel setMatte(int t, int l, int b, int r) {
		
		super.setBorder(new MatteBorder(t,l,b,r,Color.black));
		return this;
	}

	public BaseLabel setTitleborder(String title) {
		// TODO Auto-generated method stub
		
		super.setBorder(new TitledBorder(new LineBorder(Color.black), title));
		
		return this;
	}

}
