package base.comp;

import java.awt.Dimension;

public class NullPanel extends BasePanel {

	public NullPanel(int i, int j, BaseLabel jlLike, int k, int l, int m, int n, ImageLabel jlImg, int o, int p, int q, int r) {
		// TODO Auto-generated constructor stub
	super.setLayout(null);
	super.setPreferredSize(new Dimension(i,j));
		
	jlLike.setBounds(k, l, m, n);
	jlImg.setBounds(o, p, q, r);
	
	super.add(jlLike);
	super.add(jlImg);
	
	}

}
