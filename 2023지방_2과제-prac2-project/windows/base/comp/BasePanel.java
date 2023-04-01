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
		super.setLayout(new GridLayout(r, c, hg, vg));
		return this;
	}
	public BasePanel setFlowCenter() {
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		return this;
	}
	public BasePanel setFlowleft() {
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		return this;
	}
	public BasePanel setFlowRight() {
		super.setLayout(new FlowLayout(FlowLayout.RIGHT));
		return this;
	}
	public BasePanel setTitleBorder(String title) {
		super.setBorder(new TitledBorder(new LineBorder(Color.black), title));
		return this;
	}
	
	public BasePanel addChild() {
		jpCenter = new BasePanel();
		jpTop = new BasePanel();
		jpBottom = new BasePanel();
		jpLeft = new BasePanel();
		jpRight = new BasePanel();
		
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpTop , BorderLayout.NORTH);
		super.add(jpBottom, BorderLayout.SOUTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpRight, BorderLayout.EAST);
		
		return this;
	}
	public BasePanel setEmpty(int t, int l, int b, int r) {
		// TODO Auto-generated method stub
		super.setBorder(new EmptyBorder(t,l,b,r));

		return this;
		
	}
	
	

}
