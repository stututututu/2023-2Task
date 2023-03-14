package base.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BaseTable extends JScrollPane {

	private DefaultTableModel dtm;
	public JTable jTable;
	public Vector<Vector<String>> datas;

	public BaseTable(Vector<Vector<String>> datas, String... cols) {
		// TODO Auto-generated constructor stub
		this.datas = datas;
		Vector<String> newData = new Vector<>();
		newData.add("%");
		newData.add("전체");
		
		datas.add(0,newData);
		
		Vector<String> col = new Vector<>();
		for (int i = 0; i < cols.length; i++) {
			col.add(cols[i]);
		}
		dtm = new DefaultTableModel(datas, col);
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

	public BaseTable setwidth(int... width) {

		for (int i = 0; i < width.length; i++) {
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
}
