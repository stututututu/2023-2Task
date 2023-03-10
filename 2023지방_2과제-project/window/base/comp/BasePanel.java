package base.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class BasePanel extends JPanel{
	public BasePanel jpCenter;
	public BasePanel jpTop;
	public BasePanel jpBottom;
	public BasePanel jpLeft;
	public BasePanel jpRight;

	
	public BasePanel() {
		// TODO Auto-generated constructor stub
		super.setLayout(new BorderLayout());
	}
	public BasePanel setGrid(int r, int c, int hg, int vg) {
		// TODO Auto-generated constructor stub
		super.setLayout(new GridLayout(r, c, hg, vg));
		return this;
	}
	public BasePanel setFlowCenter() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		return this;
	}
	public BasePanel setFlowLeft() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		return this;
	}
	public BasePanel setFlowRight() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.RIGHT));
		return this;
	}
	
	public BasePanel setLine() {
		// TODO Auto-generated constructor stub
		super.setBorder(new LineBorder(Color.black));
		
		return this;
	}
	public BasePanel setTitleLine(String title) {
		// TODO Auto-generated constructor stub
		super.setBorder(new TitledBorder(new LineBorder(Color.black), title));
		
		return this;
	}
	
	public BasePanel addChild() {
		// TODO Auto-generated constructor stub

		jpCenter = new BasePanel();
		jpTop = new BasePanel();
		jpBottom  = new BasePanel();
		jpLeft = new BasePanel();
		jpRight = new BasePanel();
		
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpBottom, BorderLayout.SOUTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpRight, BorderLayout.EAST);
		
		return this;
	}
	public BasePanel setBorder(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		super.setBorder(new EmptyBorder(i, j, k, l));
		return this;
	}
	
	

}
