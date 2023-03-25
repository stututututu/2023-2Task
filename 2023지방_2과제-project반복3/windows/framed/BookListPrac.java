package framed;

import java.util.Vector;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import base.comp.BaeScrollPrac;
import base.comp.BaseComboPrac;
import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.BaseTablePrac;
import base.comp.ImageLabel;
import jdbc.DbManager;
import model.ImageModel;

public class BookListPrac extends BaseFrame {

	private BaseComboPrac jcBook;
	private JTextField jtSearch;
	private JButton jbSearch;
	private BaseTablePrac jLeftTable;
	private Vector<Vector<String>> divisionData;
	private BasePanel jpGrigCenter;
	private BaeScrollPrac jpcCenterScroll;

	public BookListPrac(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("도서목록", 1024, 582, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jcBook = new BaseComboPrac("도서명", "저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");
		divisionData = DbManager.db.getData("SELECT * FROM 2023지방_2.division;");
		jLeftTable = new BaseTablePrac(divisionData, "", "분류").tableInit().setHeather(1);
		
		jpGrigCenter = new BasePanel().setGrid(0, 4, 10, 10);
		jpcCenterScroll = new BaeScrollPrac();
	}

	@Override
	public void setDeisgn() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpCenter.add(new BaseLabel("도서 목록").setTitle(30).setCenter());
		jpTop.jpBottom.setFlowRight().add(new JLabel("검색"));
		jpTop.jpBottom.add(jcBook);
		jpTop.jpBottom.add(jtSearch);
		jpTop.jpBottom.add(jbSearch);

		jpCenter.addChild();
		jpCenter.jpLeft.add(jLeftTable);

		SearchChnge();

	}

	private void SearchChnge() {
		// TODO Auto-generated method stub
		String comboIndex = jcBook.getSelectedIndex() + "";
		String row = jLeftTable.jt.getSelectedRow()+ "";
		
		String b_name = "%";
		String b_author = "%";
		
		if (comboIndex.equals("0")) {
			b_name = jtSearch.getText().replace(" ", "");
		} else {
			b_author= jtSearch.getText().replace(" ", "");
		}
		
		if (row.equals("0")) {
			row = "%";
		}
		
		ImageModel data = DbManager.db.getImageData("SELECT * FROM 2023지방_2.book as b\r\n"
				+ "\r\n"
				+ "where replace(b.b_name, ' ', '') like concat('%', ?, '%') \r\n"
				+ "and replace(b.b_author, ' ', '') like concat('%', ?, '%')\r\n"
				+ " and b.d_no like ?;", 7, b_name, b_author, row);
		Vector<Vector<String>> datas = data.datas;
		Vector<ImageIcon> icons = data.icons;
		
		for (int i = 0; i < icons.size(); i++) {
			jpGrigCenter.add(new ImageLabel(datas.get(i).get(1), icons.get(i), 120, 120).setCenter().setline());
			
		}
		jpcCenterScroll.setViewportView(jpGrigCenter);
		jpCenter.jpCenter.add(jpcCenterScroll);
		
		super.refresh();
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbSearch.addActionListener(e -> {
			SearchChnge();
		});
	}

}
