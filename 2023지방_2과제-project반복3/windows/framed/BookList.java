package framed;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Book;
import java.rmi.server.LogStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.ImageLabel;
import base.comp.message;
import jdbc.DbManager;
import model.ImageModel;
import model.model;

public class BookList extends BaseFrame{

	private BaseCombo jcSearch;
	private JTextField jtSearch;
	private JButton jbSearch;
	private BaseTable jLeftTable;
	private Vector<Vector<String>> divisionData;
	private BasePanel jlBook;
	private BasePanel jpGrid;
	private BaseScroll jpScrollCenter;
	private BookList BookList;

	public BookList(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		BookList = this;
		super.BaseFrame("도서목록", 1024, 582, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jcSearch = new BaseCombo("도서명","저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");
		
		divisionData = DbManager.db.getData("SELECT * FROM 2023지방_2.division;");
		jLeftTable  = new BaseTable(divisionData,"","분류").setInit().setHeader(1).setCenter().pSize(80, 50);
		jpScrollCenter = new BaseScroll();
		jpGrid = new BasePanel().setGrid(0, 4, 10, 10);
	}

	@Override
	public void setDeisgn() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpCenter.add(new BaseLabel("도서 목록").setTitle(30).setCenter());
		jpTop.jpBottom.setFlowRight().add(new JLabel("검색"));
		jpTop.jpBottom.add(jcSearch);
		jpTop.jpBottom.add(jtSearch);
		jpTop.jpBottom.add(jbSearch);
		
		jLeftTable.jTable.changeSelection(0, 0, false, false);
		
		jpCenter.addChild();
		BookChange();
		jpCenter.jpLeft.add(jLeftTable);
		
		
	}


	private void BookChange() {
		// TODO Auto-generated method stub
		jpGrid.removeAll();
		jpBottom.removeAll();
		
		int comboIndex = jcSearch.getSelectedIndex();
		String row = jLeftTable.jTable.getSelectedRow() + "";
		String b_name = "%";
		String b_author = "%";
		
		if (comboIndex == 0) {
			b_name = jtSearch.getText().replace(" ", "");
		}else {
			b_author = jtSearch.getText().replace(" ", "");
			
		}
		if (row.equals("0")) {
			row = "%";
		}
		
		ImageModel ImgData = DbManager.db.getImageData("SELECT * FROM 2023지방_2.book as b\r\n"
				+ "\r\n"
				+ "where replace(b.b_name, ' ', '') like concat('%', ?, '%') \r\n"
				+ "and replace(b.b_author, ' ', '') like concat('%', ?, '%')\r\n"
				+ " and b.d_no like ?;", 7, b_name, b_author, row);
		Vector<Vector<String>> datas = ImgData.datas;
		Vector<ImageIcon> icons = ImgData.icons;
		
		jpScrollCenter.setViewportView(jpGrid);
		jpCenter.jpCenter.add(jpScrollCenter);
		
		
		if (icons.size() == 0) {
			
			message.error("검색결과가 없습니다.");
		}
		for (int i = 0; i < icons.size(); i++) {
			String b_no = datas.get(i).get(0);
			
			ImageLabel jlImg = new ImageLabel(datas.get(i).get(1), icons.get(i), 120, 160).setCenter().setTextBottom().setline();
			
			BaseLabel likeLabel = new BaseLabel("♡").setTitle(20);
			likeLabel.setForeground(Color.red);
			
			Vector<Vector<String>> likeData = DbManager.db.getData("SELECT * FROM 2023지방_2.likebook where u_no =? and b_no = ?;", model.LogState.get(0), b_no);
			
			if (likeData.size() != 0) {
				likeLabel.setText("♥");
			}
			
			NullPanel tempPanel = new NullPanel(160, 180, jlImg, 0, 0, 160, 180, likeLabel, 5, 10, 20, 20);
			
			int ii = i;
			jlImg.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				super.mousePressed(e);
				new BookInfoFrame(datas.get(ii), icons.get(ii),BookList);
				}
			});
			
			
			likeLabel.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if (likeLabel.getText().equals("♥")) {
						DbManager.db.setData("DELETE FROM `2023지방_2`.`likebook` WHERE (u_no = ? and b_no = ?);", model.LogState.get(0), b_no);
						likeLabel.setText("♡");
					}else {
						DbManager.db.setData("INSERT INTO `2023지방_2`.`likebook` (`u_no`, `b_no`) VALUES (?, ?)", model.LogState.get(0), b_no);
						likeLabel.setText("♥");
						
					}
					
				}
			});
			jpGrid.add(tempPanel);
			
			
		}
		
		
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbSearch.addActionListener(e -> {
			BookChange();
			
		});
	}

}
