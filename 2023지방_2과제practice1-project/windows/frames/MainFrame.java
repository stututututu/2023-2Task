package frames;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.ImageLabel;
import jdbc.DbManager;
import model.ImageModel;
import model.model;

public class MainFrame extends BaseFrame {

	private ImageLabel jlTop;
	private BaseCombo jcDivision;
	private Vector<Vector<String>> divisionData;
	private JButton jbLogIn;
	private JButton jbEnd;
	private JButton jbSignUp;
	private JButton jbBookList;
	private JButton jbMyPage;
	private JButton jbReadBook;
	private JButton jbLogOut;
	private JButton jbBookManage;
	private JButton jbRental;
	private JButton jbBookUpDate;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("메인", 820, 766, null);

	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub

		divisionData = DbManager.db.getData("SELECT d_name FROM 2023지방_2.division;");
		jcDivision = new BaseCombo(divisionData);

		jbLogIn = new JButton("로그인");
		jbSignUp = new JButton("회원가입");
		jbBookList = new JButton("도서목록");
		jbMyPage = new JButton("마이페이지");
		jbReadBook = new JButton("책 읽기");
		jbEnd = new JButton("종료");

		jbLogOut = new JButton("로그아웃");

		jbBookManage = new JButton("도서관리");
		jbBookUpDate = new JButton("도서등록");
		jbRental = new JButton("대출 통계");

	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		logOutState();

		jpCenter.addChild();
		jpCenter.settitleBorder("인기 도서");
		jpCenter.jpTop.setFlowleft().add(jcDivision);
		jpCenter.jpCenter.setGrid(1, 5, 10, 10);

		imgeChange();

	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jcDivision.addActionListener(e -> {
			imgeChange();
		});
		jbLogIn.addActionListener(e -> {
			new LogInFrame(this);
		});
		jbSignUp.addActionListener(e -> {
			new SignUpFrame(this);
		});
		jbBookList.addActionListener(e -> {
			new BookListFrame(this);
		});
	}

	private void imgeChange() {
		// TODO Auto-generated method stub
		jpCenter.jpCenter.removeAll();

		String comboIndex = jcDivision.getSelectedIndex() + "";

		if (comboIndex.equals("0")) {
			comboIndex = "%";
		}

		ImageModel bookData = DbManager.db.getImageData(
				"SELECT b.*, d.* FROM 2023지방_2.book as b\r\n" + "join 2023지방_2.division as d\r\n"
						+ "join 2023지방_2.rental as r\r\n" + "on b.d_no = d.d_no\r\n" + "and r.b_no = b.b_no\r\n"
						+ "where d.d_no like ?\r\n" + "group by r.b_no\r\n" + "order by count(r.b_no) desc limit 5;",
				7, comboIndex);

		Vector<Vector<String>> datas = bookData.datas;
		Vector<ImageIcon> icons = bookData.icons;
		for (int i = 0; i < icons.size(); i++) {
			ImageLabel jlImge = new ImageLabel(datas.get(i).get(1), icons.get(i), 180, 160).setCenter().setTextBottom()
					.setline();
			jpCenter.jpCenter.add(jlImge);
		}
		super.refresh();

	}

	public void logOutState() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();
		jpTop.removeAll();
		jlTop = new ImageLabel("로그인 후 이용해주세요.", "메인3", 918, 450).setCenter().settextCenter().setColor().setTitle(30);
		jpTop.add(jlTop);
		jbBookList.setEnabled(false);
		jbMyPage.setEnabled(false);
		jbReadBook.setEnabled(false);

		jpBottom.setFlowCenter().add(jbLogIn);
		jpBottom.setFlowCenter().add(jbSignUp);
		jpBottom.setFlowCenter().add(jbBookList);
		jpBottom.setFlowCenter().add(jbMyPage);
		jpBottom.setFlowCenter().add(jbReadBook);
		jpBottom.setFlowCenter().add(jbEnd);

		super.refresh();
	}

	public void logInState() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();
		jpTop.removeAll();

		jlTop = new ImageLabel(model.logstate.get(1) + "님 환영합니다.", "메인2", 918, 450).setCenter().settextCenter()
				.setColor().setTitle(30);

		jpTop.add(jlTop);

		jbSignUp.setEnabled(false);
		jbBookList.setEnabled(true);
		jbMyPage.setEnabled(true);
		jbReadBook.setEnabled(true);

		jpBottom.setFlowCenter().add(jbLogOut);
		jpBottom.setFlowCenter().add(jbSignUp);
		jpBottom.setFlowCenter().add(jbBookList);
		jpBottom.setFlowCenter().add(jbMyPage);
		jpBottom.setFlowCenter().add(jbReadBook);
		jpBottom.setFlowCenter().add(jbEnd);

		super.refresh();
	}

	public void ManagerState() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();

		jpTop.removeAll();

		jlTop = new ImageLabel("관리자님 환영합니다.", "메인1", 918, 450).setCenter().settextCenter().setColor().setTitle(30);

		jpBottom.setFlowCenter().add(jbLogOut);
		jpBottom.setFlowCenter().add(jbBookManage);
		jpBottom.setFlowCenter().add(jbBookUpDate);
		jpBottom.setFlowCenter().add(jbRental);
		jpBottom.setFlowCenter().add(jbEnd);

		super.refresh();
	}

}
