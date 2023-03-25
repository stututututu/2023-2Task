package framed;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class BaseTable extends JScrollPane{

	public JTable jTable;
	public Vector<Vector<String>> data;
	public BaseTable(Vector<Vector<String>> data, String... col) {
		// TODO Auto-generated constructor stub
		this.data = data;
		Vector<String> cols = new Vector<String>();
		Vector all = new Vector<>();
		all.add("%");
		all.add("전체");
		
		data.add(0,all);
		for (String val : col) {
			cols.add(val);
		}
		DefaultTableModel dtm = new DefaultTableModel(data,cols);
		jTable = new JTable(dtm);
		super.setViewportView(jTable);
		
		
	}
	public BaseTable setInit(){
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
	public BaseTable pSize(int w, int d) {

		super.setPreferredSize(new Dimension(w, d));
		
		return this;
	}
	public BaseTable setCenter() {
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jTable.setDefaultRenderer(Object.class, dtcr);
		
		
		return this;
	}


}
