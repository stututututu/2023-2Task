package framed;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.Border;

import base.comp.BasePanel;

public class NullPanel extends BasePanel{
	public NullPanel(int w, int d, JLabel jl1, int x1, int y1, int w1, int h1, JLabel jl2, int x2, int y2, int w2, int h2) {
		// TODO Auto-generated constructor stub
		
		super.setLayout(null);
		super.setPreferredSize(new Dimension(w,d));
		
		jl1.setBounds(x1,y1,w1,h1);
		jl2.setBounds(x2,y2,w2,h2);
		
		super.add(jl1);
		super.add(jl2);
		
		
	}
}
