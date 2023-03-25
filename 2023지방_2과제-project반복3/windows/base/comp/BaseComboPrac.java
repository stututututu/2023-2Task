package base.comp;

import javax.swing.JComboBox;

public class BaseComboPrac extends JComboBox<String>{

	public BaseComboPrac(String... val) {
		// TODO Auto-generated constructor stub
		for (String vals : val) {
			super.addItem(vals);
			
		}
	}

}
