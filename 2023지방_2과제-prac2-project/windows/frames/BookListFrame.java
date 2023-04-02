package frames;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.BaseScroll;
import base.comp.BaseTable;
import base.comp.ImageLabel;
import base.comp.NullPanel;
import jdbc.DbManager;
import model.ImageModel;
import model.Logmodel;

public class BookListFrame extends BaseFrame{

	private BaseCombo jcSearch;
	private JTextField jtSearch;
	private JButton jbSearch;
	private BaseTable jpLeftTable;
	private Vector<Vector<String>> divisionData;
	private BasePanel jpGridCenter;
	private BaseScroll jTableCenter;

	public BookListFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor 
		super.BaseFrame("도서 목록 ", 1000, 640, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jcSearch = new BaseCombo("도서명","저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");
		
		divisionData = DbManager.db.getData("SELECT * FROM 2023지방_2.division;");
		
		jpLeftTable = new BaseTable(divisionData,"", "분류").tableInit().setHeader(1).pSize(80,80).setCenter();
		jpGridCenter = new BasePanel().setGrid(0, 4, 10, 10);
		jTableCenter = new BaseScroll();
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.add(new BaseLabel("도서 목록").setTitle(30));
		jpTop.jpBottom.setFlowRight().add(new BaseLabel("검색"));
		jpTop.jpBottom.add(jcSearch);
		jpTop.jpBottom.add(jtSearch);
		jpTop.jpBottom.add(jbSearch);
		
		jpCenter.addChild();
		jpCenter.jpLeft.add(jpLeftTable);
		jpLeftTable.jTable.changeSelection(0, 0, false  , false);
	
		ImageChange();
	}

	private void ImageChange() {
		// TODO Auto-generated method stub
		String row = jpLeftTable.jTable.getSelectedRow() + "";
		if (row.equals("0")) {
			row = "%";
		}
		String b_name = "%";
		String b_author = "%";
		
		int colIndex = jcSearch.getSelectedIndex();
		if (colIndex == 0) {
			b_name = jtSearch.getText().replace(" ", "");
		}
		else {
			b_author = jtSearch.getText().replace(" ", "");;
		}
		
		System.out.println(b_author);
		System.out.println(row);
		
		ImageModel data = DbManager.db.getImageData("SELECT * FROM 2023지방_2.book as b\r\n"
				+ "\r\n"
				+ "where replace(b.b_name, ' ', '') like concat('%', ?, '%') \r\n"
				+ "and replace(b.b_author, ' ', '') like concat('%', ?, '%')\r\n"
				+ " and b.d_no like ?;",7,b_name, b_author,row);
		Vector<Vector<String>> datas = data.datas;
		Vector<ImageIcon> icons = data.icons;
		
		jTableCenter.setViewportView(jpGridCenter);
		jpCenter.jpCenter.add(jTableCenter);
		
		for (int i = 0; i < icons.size(); i++) {
			String b_no = datas.get(i).get(0);
			ImageLabel jlImg = new ImageLabel(datas.get(i).get(1), icons.get(i), 180, 180).setTextBottom().setLine();
			
			Vector<Vector<String>> likeData = DbManager.db.getData("SELECT * FROM 2023지방_2.likebook where u_no = ? and b_no = ?;",Logmodel.LogState.get(0), b_no);
			BaseLabel jlLike = new BaseLabel("♡").setTitle(20);
			jlLike.setForeground(Color.red);
			if (likeData.size() != 0) {
				jlLike.setText("♥");
			}
			NullPanel jlTmp = new NullPanel(200,200,jlLike,5,10,20,20,jlImg,1,1,1,1);
			jlLike.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				
					super.mousePressed(e);
					if (jlLike.getText().equals("♡")) {
						jlLike.setText("♥");
						DbManager.db.setData("INSERT INTO `2023지방_2`.`likebook` (`u_no`, `b_no`) VALUES (?, ?);\r\n"
								+ "", Logmodel.LogState.get(0), b_no);
					}else {
						jlLike.setText("♡");
						DbManager.db.setData("DELETE FROM `2023지방_2`.`likebook` WHERE (u_no = ? and b_no = ?);", Logmodel.LogState.get(0), b_no);
						
					}
				}
			});
			
			
			jpGridCenter.add(jlTmp);
			
		}
		
		super.refresh();
	}

	@Override
	public void setEvents() {
		// TODO Auto-generated method stub
		
	}

}
