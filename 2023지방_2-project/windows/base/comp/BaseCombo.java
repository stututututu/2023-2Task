package base.comp;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComboBox;

public class BaseCombo extends JComboBox<String> {

	public Vector<Vector<String>> data;

	public BaseCombo(String... vals) {
		// TODO Auto-generated constructor stub
		for (String val : vals) {
			super.addItem(val);
		}
	}

	public BaseCombo(Vector<Vector<String>> data) {
		this.data = data;

		for (Vector<String> row : data) {
			super.addItem(row.get(0));
		}
	}

}
