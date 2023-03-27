package base.comp;

import java.util.Vector;

import javax.swing.JComboBox;

public class BaseCombo extends JComboBox<String>{

	public BaseCombo(Vector<Vector<String>> divisionData) {
		// TODO Auto-generated constructor stub
		super.addItem("전체");
		for (Vector<String> val : divisionData) {
			super.addItem(val.get(0));
		}
		
	}

	public BaseCombo(String...val) {
		// TODO Auto-generated constructor stub
		for (String vals : val) {
			super.addItem(vals);
		}
	}

}
