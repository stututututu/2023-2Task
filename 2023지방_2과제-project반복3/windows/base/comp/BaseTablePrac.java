package base.comp;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import framed.BaseTable;

public class BaseTablePrac extends JScrollPane{

	public JTable jt;
	public BaseTablePrac(Vector<Vector<String>> divisionData, String...col ) {
		
		Vector<String> cols = new Vector<String>();
		Vector<String> all = new Vector<String>();
		all.add("%");
		all.add("전체");
		divisionData.add(0,all);
		
		for (String val : col) {
			cols.add(val);
		}
		DefaultTableModel dtm = new DefaultTableModel(divisionData, cols);
		jt = new JTable(dtm);
		super.setViewportView(jt);
		
		
	}
	public BaseTablePrac tableInit() {
		jt.getTableHeader().setBackground(Color.white);
		super.getViewport().setBackground(Color.white);
		super.setBackground(Color.white);
		
		jt.getTableHeader().setReorderingAllowed(false);
		
		return this;
	};
	public BaseTablePrac setHeather(int num) {
		for (int i = 0; i < num; i++) {
			jt.getColumnModel().getColumn(i).setMinWidth(i);
			jt.getColumnModel().getColumn(i).setMaxWidth(i);
			jt.getColumnModel().getColumn(i).setWidth(i);
		}
		return this;
	}
	public BaseTablePrac setCenter() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jt.setDefaultRenderer(Object.class, dtcr);
		return this;
	}
	

}
