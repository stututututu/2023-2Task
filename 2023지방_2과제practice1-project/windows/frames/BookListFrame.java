package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.BaseScroll;
import base.comp.ImageLabel;
import base.comp.NullPanel;
import base.comp.message;
import jdbc.DbManager;
import model.ImageModel;
import model.model;

public class BookListFrame extends BaseFrame{

	private BaseCombo jcBook;
	private JTextField jtSearch;
	private JButton jbSearch;
	private BaseTable jLeftTable;
	private Vector<Vector<String>> divisionData;
	private BasePanel jpGridCenter;
	private BaseScroll jpCenterTable;
	private BookListFrame BookListFrame;

	public BookListFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("도서목록", 1045, 706, mainFrame);
		BookListFrame = this;
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		divisionData = DbManager.db.getData("SELECT * FROM 2023지방_2.division;");
		
		jcBook = new BaseCombo("도서명","저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");
		
		jLeftTable = new BaseTable(divisionData, "", "분류").tableInit().setHeader(1);
		
		jpGridCenter = new BasePanel().setGrid(0, 4, 10, 10);
		jpCenterTable = new BaseScroll();
		
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
		jpCenter.jpLeft.setPreferredSize(new Dimension(80,80));
		
		jLeftTable.jTable.changeSelection(0, 0, false, false);
		
		searchChange();
	}



	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbSearch.addActionListener(e -> {
			searchChange();
		});
		
	}
	private void searchChange() {
		// TODO Auto-generated method stub
		String row = jLeftTable.jTable.getSelectedRow() + "";
		int comboIndex = jcBook.getSelectedIndex();
		String b_name = "%";
		String b_author = "%";
		
		
		if (comboIndex == 0) {
			 b_name = jtSearch.getText();
		}
		else {
			b_author = jtSearch.getText();
		}
		
		if (row.equals("0")) {
			row = "%";
		}
		
		ImageModel bookData = DbManager.db.getImageData("SELECT * FROM 2023지방_2.book as b\r\n"
				+ "\r\n"
				+ "where b.b_name like concat('%', ?, '%')\r\n"
				+ "and b.b_author like concat('%', ?, '%')\r\n"
				+ "and b.d_no like ?;", 7,b_name, b_author, row);
		Vector<Vector<String>> datas = bookData.datas;
		Vector<ImageIcon> icons = bookData.icons;
		
		jpCenterTable.setViewportView(jpGridCenter);
		jpCenter.jpCenter.add(jpCenterTable);
		
		for (int i = 0; i < icons.size(); i++) {
			
			ImageLabel jlImg = new ImageLabel(datas.get(i).get(1), icons.get(i), 200, 200).setTextBottom().setline();
			BaseLabel likeBook = new BaseLabel("♡").setTitle(20);
			likeBook.setForeground(Color.red);
			
			Vector<Vector<String>> likeData = DbManager.db.getData("SELECT * FROM 2023지방_2.likebook where b_no = ? and u_no = ?;", datas.get(0), model.logstate.get(0));
			if (likeData.size() != 0) {
				likeBook.setText("♥");
			}
			
			NullPanel jpTemp = new NullPanel(280, 280, likeBook, 5, 10, 30, 30, jlImg, 0, 0, 250, 250);
			likeBook.addMouseListener(new MouseAdapter() {
		
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if (likeBook.getText().equals("♥")) {
						DbManager.db.setData("DELETE FROM `2023지방_2`.`likebook` WHERE (`u_no` = ?);",model.logstate.get(0));
						likeBook.setText("♡");
						return;
						
					}
					else {
						DbManager.db.setData("INSERT INTO `2023지방_2`.`likebook` (`u_no`, `b_no`) VALUES (?, ?);\r\n"
								+ "",model.logstate.get(0),datas.get(0));
						likeBook.setText("♡");
						return;
						
					}
				}
				
			});
			int ii = i;
			jlImg.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					new BookInfo(datas.get(ii),icons.get(ii), BookListFrame);
					
				}
			});
			
			
			
			jpGridCenter.add(jpTemp);
			
		}
	}
	
	

}
