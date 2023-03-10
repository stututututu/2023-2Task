package base.comp;

import java.util.Vector;

import javax.swing.JComboBox;

public class BaseCombo extends JComboBox<String>{

	public BaseCombo() {
		// TODO Auto-generated constructor stub
	}
	public BaseCombo(String...val) {
		// TODO Auto-generated constructor stub
		for (String vals : val) {
			super.addItem(vals);
		}
	}
	public BaseCombo setDivision(Vector<Vector<String>>divisionData) {
		// TODO Auto-generated constructor stub
		super.addItem("전체");
		for (Vector<String> vals : divisionData) {
			super.addItem(vals.get(0));
		}
		
		return this;
	}

}
