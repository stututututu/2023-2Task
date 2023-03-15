package frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.BasePanel;
import base.comp.BaseTable;
import base.comp.imageLable;
import jdbc.Dbmanager;
import models.ImageDataModel;

public class BookListFrame extends BaseFrame {

	private BaseCombo jcSearch;
	private JTextField jtSearch;
	private JButton jbSearch;
	private Vector<Vector<String>> divisionData;
	private imageLable jlImg;
	private BaseTable jLeftTable;
	private JTable jTable;
	private BaseScroll jCenterScroll;
	private BasePanel jpCenterGrid;
	private ImageDataModel allData;

	public BookListFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("도서 목록", 1121, 602, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jcSearch = new BaseCombo("도서명", "저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");

		divisionData = Dbmanager.db.getData("SELECT * FROM 2023지방_2.division;");
		jLeftTable = new BaseTable(divisionData, "","분류").setwidth(0).tableInit().pSize(80, 50);
		
		jCenterScroll = new BaseScroll();
		jpCenterGrid = new BasePanel().setGrid(0, 4, 10, 10);
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.add(new BaseLable("도서목록").setCenter().setText(25));
		
		jpTop.jpBottom.setFlowRight().add(new BaseLable("검색"));
		jpTop.jpBottom.add(jcSearch);
		jpTop.jpBottom.add(jtSearch);
		jpTop.jpBottom.add(jbSearch);
		
//		jpCenter.jpCenter.add(jpCenterGrid);
		jLeftTable.jTable.changeSelection(0, 0, false, false);
		imgSearch();
		jpCenter.setBorder(15, 15, 15, 15);
		
		jpLeft.addChild();
		jpLeft.jpLeft.add(jLeftTable);
		
		jpCenterGrid.setBorder(5, 5, 5, 5);

	}

	public void imgSearch() {
		// TODO Auto-generated method stub
		jpCenterGrid.removeAll();
	 	
		String row = jLeftTable.jTable.getSelectedRow() + "";
		String comboIndex = jcSearch.getSelectedIndex() + "";
		String b_name = "%";
		String b_author= "%";
		
		if (jcSearch.getSelectedIndex() == 0) {
			b_name = jtSearch.getText().replaceAll(" ", "");
		} else {
			b_author = jtSearch.getText().replaceAll(" ", "");
		}
		
		System.out.println(row);
		if (row.equals("0")) {
			row = "%";
		}
		
		allData = Dbmanager.db.getImageData("SELECT * FROM 2023지방_2.book as b\r\n"
				+ "\r\n"
				+ "where replace(b.b_name, ' ', '') like concat('%', ?, '%')\r\n"
				+ "and replace(b.b_author, ' ', '') like concat('%', ?, '%')\r\n"
				+ "and b.d_no like ?;", 7, b_name, b_author ,row);
		jCenterScroll.setViewportView(jpCenterGrid);
		jpCenter.add(jCenterScroll);
		
		Vector<Vector<String>> datas = allData.datas;
		Vector<ImageIcon> icons = allData.icons;

		for (int i = 0; i < icons.size() ; i++) {
			String title = datas.get(i).get(1);
			
			jpCenterGrid.setBorder(10, 10, 10, 10);
			jpCenterGrid.add(new imageLable(title, icons.get(i), 160, 160).setline().setTextBottom().setCenter());
			
		}		
		
		super.refresh();
	}

	@Override
	public void events() {
		jbSearch.addActionListener(e -> {
			imgSearch();
		});
		
	}

}
