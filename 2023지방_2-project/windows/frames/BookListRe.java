package frames;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.graalvm.compiler.core.amd64.AMD64NodeMatchRules;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseTable1;
import db.DbManager;

public class BookListRe extends BaseFrame{

	private BaseCombo jcSearch;
	private JTextField jtSearch;
	private JButton jbSearch;
	private Vector<Vector<String>> divisionData;
	private BaseTable1 jLeftTable;

	public BookListRe() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("도서목록", 800, 500);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jcSearch = new BaseCombo("도서명", "저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");
		
//		divisionData = DbManager.db.getData("SELECT d_name, d_no FROM 2023지방_2.division;");
		
	//	jLeftTable = new BaseTable1(divisionData, 1, "분류","");
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}

}
