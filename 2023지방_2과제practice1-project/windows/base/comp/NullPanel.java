package base.comp;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NullPanel extends JPanel{

	public NullPanel(int w, int d, BaseLabel jl1, int x1, int y1, int w1, int h1, ImageLabel jl2, int x2, int y2, int w2, int h2) {
		// TODO Auto-generated constructor stub
		super.setLayout(null);
		super.setPreferredSize(new Dimension(w,d));
		
		jl1.setBounds(x1, y1, w1, h1);
		jl2.setBounds(x2, y2, w2, h2);
		
		super.add(jl1);
		super.add(jl2);
	}


}
