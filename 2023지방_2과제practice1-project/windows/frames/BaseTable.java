package frames;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BaseTable extends JScrollPane{

	private Vector<Vector<String>> data;
	private JTable jTable;

	public BaseTable(Vector<Vector<String>> divisionData, String...val) {
		// TODO Auto-generated constructor stub
		this.data = divisionData;
		
		Vector<String> all = new Vector<String>();
		all.add("%");
		all.add("전체");
		data.add(0,all);
		
		Vector<String> col = new Vector<>();
		for (String vals : val) {
			col.add(vals);
		}
		DefaultTableModel dtm = new DefaultTableModel(data,col);
		 jTable = new JTable(dtm);
		
		super.setViewportView(jTable);
		
	}
	public BaseTable tableInit() {
		jTable.getTableHeader().setBackground(Color.white);
		super.getViewport().setBackground(Color.white);
		super.setBackground(Color.white);
		jTable.getTableHeader().setReorderingAllowed(false);
		return this;
	}
	public BaseTable setHeader(int num) {

		for (int i = 0; i < num; i++) {
			jTable.getColumnModel().getColumn(i).setMinWidth(i);
			jTable.getColumnModel().getColumn(i).setMaxWidth(i);
			jTable.getColumnModel().getColumn(i).setWidth(i);
			
		}
		return this;
	}

}
