package framed;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.ImageLabel;
import jdbc.DbManager;
import model.ImageModel;
import model.model;

public class MainFrame extends BaseFrame {

	private ImageLabel jlTop;
	private BaseCombo jcBook;
	private Vector<Vector<String>> divisionData;
	private JButton jbLogIn;
	private JButton jbSignUp;
	private JButton jbBookList;
	private JButton jbMyPage;
	private JButton jbReadBook;
	private JButton jbClose;
	private ImageLabel jlImg;
	private JButton jbLogOut;
	private JButton jbBookRegister;
	private JButton jbRental;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("메인", 950, 800, null);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jlTop = new ImageLabel("로그인 후 이용해주세요.", "메인3", 960, 500).setCenter().setTextCenter().setTitle(30);

		divisionData = DbManager.db.getData("SELECT * FROM 2023지방_2.division;");
		jcBook = new BaseCombo(divisionData);

		jbLogIn = new JButton("로그인");
		jbSignUp = new JButton("회원가입");
		jbBookList = new JButton("도서 목록");
		jbMyPage = new JButton("마이페이지");
		jbReadBook = new JButton("책 읽기");
		jbClose = new JButton("종료");

		jbLogOut = new JButton("로그아웃");
		jbBookRegister = new JButton("도서등록");
		jbRental = new JButton("대출통계");
	}

	@Override
	public void setDeisgn() {
		// TODO Auto-generated method stub
		jpTop.add(jlTop);

		jpCenter.addChild();
		jpCenter.setLine("인기도서");
		jpCenter.jpTop.setFlowLeft().add(jcBook);

		jpCenter.jpCenter.setGrid(1, 5, 10, 10);
		imageChange();
		jbBookList.setEnabled(false);
		jbMyPage.setEnabled(false);
		jbReadBook.setEnabled(false);
		
		jpBottom.setFlowCenter().add(jbLogIn);
		jpBottom.add(jbSignUp);
		jpBottom.add(jbBookList);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbReadBook);
		jpBottom.add(jbClose);
	}

	public void imageChange() {
		// TODO Auto-generated method stub
		jpCenter.jpCenter.removeAll();
		

		String colIndex = jcBook.getSelectedIndex() + "";
		if (colIndex.equals("0")) {
			colIndex = "%";
		}
		ImageModel imgData = DbManager.db.getImageData("SELECT b.*, d.* FROM 2023지방_2.division as d\r\n"
				+ "join 2023지방_2.book as b\r\n" + "join 2023지방_2.rental as r\r\n" + " on d.d_no = b.d_no\r\n"
				+ " and r.b_no = b.b_no\r\n" + " where d.d_no like ?\r\n" + " group by r.b_no\r\n"
				+ " order by count(r.b_no) desc limit 5;", 7, colIndex);
		Vector<Vector<String>> datas = imgData.datas;
		Vector<ImageIcon> icons = imgData.icons;
		for (int i = 0; i < 5; i++) {
			
			jlImg = new ImageLabel(datas.get(i).get(1), icons.get(i), 100, 120	).setCenter().setTextBottom().setline();
			
			jpCenter.jpCenter.add(jlImg);
		}
		super.refresh();
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jcBook.addActionListener(e -> {
			imageChange();
		});
		jbLogIn.addActionListener(e -> {
			new LogInFrame(this);
		});
		jbSignUp.addActionListener(e -> {
			new SignUpFrame(this);
		});
		jbLogOut.addActionListener(e -> {
			jpBottom.removeAll();
			jpTop.removeAll();
			jpTop.add(jlTop);

			jpCenter.addChild();
			jpCenter.setLine("인기도서");
			jpCenter.jpTop.setFlowLeft().add(jcBook);

			jpCenter.jpCenter.setGrid(1, 5, 10, 10);
			imageChange();
			jbBookList.setEnabled(false);
			jbMyPage.setEnabled(false);
			jbReadBook.setEnabled(false);
			
			jpBottom.setFlowCenter().add(jbLogIn);
			jpBottom.add(jbSignUp);
			jpBottom.add(jbBookList);
			jpBottom.add(jbMyPage);
			jpBottom.add(jbReadBook);
			jpBottom.add(jbClose);
			super.refresh();
		});
		
	}
	public void LogInState() {
		jpBottom.removeAll();
		jpTop.removeAll();
		jlTop = new ImageLabel(model.LogState.get(1)+"님 환영합니다.", "메인2", 950, 500).setCenter().setTextCenter().setTitle(30);
		jbSignUp.setEnabled(false);
		jpTop.add(jlTop);
		
		jpBottom.setFlowCenter().add(jbLogOut);
		jpBottom.add(jbSignUp);
		jpBottom.add(jbBookList);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbReadBook);
		jpBottom.add(jbClose);
		super.refresh();
	};

	public void ManagerState() {
		jpBottom.removeAll();
		jpTop.removeAll();
		jlTop = new ImageLabel("관리자님 환영합니다.", "메인1", 950, 500).setCenter().setTextCenter().setTitle(30);
		jbSignUp.setEnabled(false);
		jpTop.add(jlTop);
		
		jpBottom.setFlowCenter().add(jbLogOut);
		jpBottom.add(jbBookList);
		jpBottom.add(jbBookRegister);
		jpBottom.add(jbRental);
		jpBottom.add(jbClose);
		super.refresh();
	};

}
