package base.comp;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BaseTable1 extends JScrollPane{

	private Vector<String> cols;
	private Vector<Vector<String>> data;
	private DefaultTableModel dtm;
	private JTable jTable;
	public BaseTable1(Vector<Vector<String>> divisionData, int i, String...val) {
		// TODO Auto-generated constructor tub
		cols = new Vector<String>();
		this.data = divisionData;
		
		for (String vals: val) {
			cols.add(vals);
		}
		
		dtm = new DefaultTableModel(data, cols);
		
		jTable = new JTable(dtm);
		
//		jTable.getTableHeader().setBackground(Col or.white);
//		super.setvie
		
		
		super.setViewportView(jTable);
	}
	public BaseTable1() {
		//TODO Auto-generated method stub
		

	}

}
