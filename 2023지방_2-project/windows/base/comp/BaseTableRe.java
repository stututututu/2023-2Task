package base.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BaseTableRe extends JScrollPane{

	private Vector<String> cols;
	public Vector<Vector<String>> data;
	private int divisionColIndex;
	private DefaultTableModel dtm;
	private JTable jTable;
	public BaseTableRe() {
		// TODO Auto-generated constructor stub
	}
	public BaseTableRe(Vector<Vector<String>> sql,int cd, String...val) {
		// TODO Auto-generated constructor stub
		cols = new Vector<String>();
		this.data = sql;
		this.divisionColIndex = cd;
		
		for (String vals : val) {
			cols.add(vals);
		}
		dtm = new DefaultTableModel(data, cols);
		
		jTable = new JTable(dtm);
		jTable.getTableHeader().setBackground(Color.white);
		super.getViewport().setBackground(Color.white);
		jTable.getColumnModel().getColumn(cd).setMinWidth(0);
		jTable.getColumnModel().getColumn(cd).setMaxWidth(0);
		jTable.getColumnModel().getColumn(cd).setWidth(0);
		
		super.setViewportView(jTable);
		
		
		}
	public BaseTableRe pSize(int w, int d) {
		// TODO Auto-generated method stub
		super.setPreferredSize(new Dimension(w,d));
		return this;

	}
}
