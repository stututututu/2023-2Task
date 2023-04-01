package base.comp;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComboBox;

public class BaseCombo extends JComboBox<String>{

	public BaseCombo(String... val) {
		// TODO Auto-generated constructor stub
		
		for (String vals : val) {
			super.addItem(vals);
		}
	}
	public BaseCombo(Vector<Vector<String>> divisionData) {
		// TODO Auto-generated constructor stub
		
		super.addItem("전체");
		
		for (Vector<String> data : divisionData) {
			super.addItem(data.get(0));
		}
	}

}
