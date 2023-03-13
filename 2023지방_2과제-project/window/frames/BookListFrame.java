package frames;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseTable;
import jdbc.Dbmanager;

public class BookListFrame extends BaseFrame{

	private BaseCombo jcSearch;
	private JTextField jtSearch;
	private JButton jbSearch;
	private Vector<Vector<String>> divisionData;
	private BaseTable jLeftTable; 	

	public BookListFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("도서 목록", 766, 429, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jcSearch = new BaseCombo();
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");
		
		divisionData = Dbmanager.db.getData("SELECT * FROM 2023지방_2.division;");
		jLeftTable = new BaseTable(divisionData, "dasd", "dd").setwidth(1).tableInit().pSize(80, 50);
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpLeft.add(jLeftTable);
	}

	@Override
	public void events() {

	}

}
