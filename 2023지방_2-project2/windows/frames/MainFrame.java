package frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BasePanel;
import base.comp.imageLabel;
import db.DbManager;
import image.ImageModel;

public class MainFrame extends BaseFrame{

	private imageLabel jlImg;
	private Vector<Vector<String>> jcData;
	private BaseCombo jcBook;
	private JButton jbLogIn;
	private JButton jbSignUp;
	private JButton jbBookList;
	private JButton jbMyPage;
	private JButton jbRead;
	private JButton jbEnd;
	private imageLabel jcImg;

	public MainFrame() {
		// TOO Auto-generated constructor stub
		super.BaseFrame("메인", 786, 646);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
		jlImg = new imageLabel("로그인 후 이용해주세요.","메인3",800,400).setCenter().setTitle(40).setWhite().setTxtCenter();
		
		jcData = DbManager.db.getData("SELECT d_name FROM 2023지방_2.division;");
		jcBook = new BaseCombo(jcData);
		
		jbLogIn = new JButton("로그인");
		jbSignUp = new JButton("회원가입");
		jbBookList = new JButton("도서 목록");
		jbMyPage = new JButton("마이페이지");
		jbRead= new JButton("책 읽기");
		jbEnd= new JButton("종료");
		
		
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(jlImg);
		
		jpCenter.addChild();
		jpCenter.setLine("인기 도서");
		jpCenter.jpTop.setFlowLeft().add(jcBook);
		jpCenter.jpCenter.addChild();
		jpCenter.jpCenter.setGrid(1, 5, 30, 0);
		
		jpBottom.addChild();
		jpBottom.setFlowCenter().add(jbLogIn);
		jpBottom.add(jbSignUp);
		jpBottom.add(jbBookList);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbRead);
		jpBottom.add(jbEnd);

		imgChange();
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}
	private void imgChange() {
		// TODO Auto-generated method stub
		jpCenter.jpCenter.removeAll();
		String num = jcBook.getSelectedIndex() + "";
		if (num == "0") {
			num = "%";
		}
		
//		String d_no = jcBook.data.get(num).get(1);
		
		Vector<ImageModel> data = DbManager.db.getImageData("SELECT b.*, d.* FROM 2023지방_2.rental as r\r\n"
				+ "join book as b \r\n"
				+ "join 2023지방_2.division as d\r\n"
				+ "on r.b_no = b.b_no\r\n"
				+ "and b.d_no = d.d_no\r\n"
				+ "where d.d_no like ?\r\n"
				+ "group by r.b_no\r\n"
				+ "order by count(r.b_no) desc, b.b_no\r\n"
				+ "limit 5;", 6, num);
		
		for (ImageModel img : data) {
			String no = img.getData().get(0);
			String title = img.getData().get(1);
			String author = img.getData().get(3);
			String exp = img.getData().get(6);
		
			jcImg = new imageLabel(title, img.getIcon(), 150, 180).setbottom().setLine().setCenter().setTitle(14);
			

			jcImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
				}
			});
			jpCenter.jpCenter.add(jcImg);
		}
		
		super.refresh();

	}

}
