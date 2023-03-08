package frames;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BasePanel;
import base.comp.BaseTableRe;
import db.DbManager;

public class BookListReMake extends BaseFrame{

	private BaseCombo jcSearch;
	private JTextField jtSearch;
	private JButton jbSearch;
	private Vector<Vector<String>> divisionData;
	private BaseTableRe jLeftTable;
	private BasePanel jpGrid;
	private Vector<Vector<String>> img;

	public BookListReMake() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("도서목록", 800, 477);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jcSearch = new BaseCombo("도서명", "저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");
		
		divisionData = DbManager.db.getData("SELECT d_name, d_no FROM 2023지방_2.division;");
		
		jLeftTable = new BaseTableRe(divisionData, 1, "분류", "").pSize(80, 0);
		
		jpGrid = new BasePanel().setGrid(0, 4, 10, 10);
		
//		img = DbManager.db.getData("select d.d_no, b.b_name, d.d_name,  b.b_img, b.b_author, b.b_exp from 2023지방_2.book as b\\r\\n\"\r\n"
//				+ "								+ \"join division as d\\r\\n\" + \"	on b.d_no = d.d_no\\r\\n\" + \"    join rental as r \\r\\n\"\r\n"
//				+ "								+ \"    on b.b_no = r.b_no\\r\\n\" + \"	where d.d_no like ?\"");
//		for (Vector<String> imgs : img) {
//			jpGrid.addChild();
//			jpGrid.add(imgs.get(3));
//			
//		}
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpCenter.add(new BaseLabel("도서 목록").setTitle(30).setCenter());;
		
		jpTop.jpBottom.setFlowRight().add(new JLabel("검색"));
		jpTop.jpBottom.add(jcSearch);
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
