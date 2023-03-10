package base.comp;

import java.util.Vector;

import javax.swing.JComboBox;

import db.DbManager;

public class BaseCombo extends JComboBox<String>{

	public Vector<Vector<String>> data;

	public BaseCombo(Vector<Vector<String>> jcData) {
		// TODO Auto-generated constructor stub
		this.data = jcData;
		super.addItem("전체");
		for (Vector<String> vals : jcData) {
			
			super.addItem(vals.get(0));
		}
	}

}
