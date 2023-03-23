package base.comp;

import java.util.Vector;

import javax.swing.JComboBox;

public class BaseCombo extends JComboBox<String>{

	public BaseCombo(Vector<Vector<String>> data)  {
		// TODO Auto-generated constructor stub
		super.addItem("전체");
		for (Vector<String> vals : data) {
		super.addItem(vals.get(1));
		
		}
		
	}

}
