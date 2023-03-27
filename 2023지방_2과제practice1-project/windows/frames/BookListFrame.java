package frames;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseLabel;
import jdbc.DbManager;

public class BookListFrame extends BaseFrame{

	private BaseCombo jcBook;
	private JTextField jtSearch;
	private JButton jbSearch;
	private BaseTable jLeftTable;
	private Vector<Vector<String>> divisionData;

	public BookListFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("도서목록", 600, 700, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		divisionData = DbManager.db.getData("SELECT * FROM 2023지방_2.division;");
		
		jcBook = new BaseCombo("도서명","저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");
		
		jLeftTable = new BaseTable(divisionData, "", "분류").tableInit().setHeader(1);
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpCenter.add(new BaseLabel("도서 목록").setCenter().setTitle(30));
		jpTop.jpBottom.setFlowRight().add(new BaseLabel("검색"));
		jpTop.jpBottom.add(jcBook);
		jpTop.jpBottom.add(jtSearch);
		jpTop.jpBottom.add(jbSearch);
		
		jpCenter.addChild();
		jpCenter.jpLeft.add(jLeftTable);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}

}
