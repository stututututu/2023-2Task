package frames;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.ImageLabel;
import jdbc.DbManager;
import model.ImageModel;
import model.Logmodel;

public class MainFrame extends BaseFrame{

	private ImageLabel jlTop;
	private BaseCombo jcDivision;
	private Vector<Vector<String>> divisionData;
	private JButton jbLogin;
	private JButton jbReadBook;
	private JButton jbBookList;
	private JButton jbMyPage;
	private JButton jbSignUp;
	private JButton jbEnd;
	private JButton jbLogOut;
	private JButton jbrental;
	private JButton jbUpBook;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("메인", 1014, 858, null);
		
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
		
		divisionData = DbManager.db.getData("SELECT d_name FROM 2023지방_2.division;");
		jcDivision = new BaseCombo(divisionData);
		
		jbLogin = new JButton("로그인");
		jbSignUp= new JButton("회원가입");
		jbBookList = new JButton("도서목록");
		jbMyPage = new JButton("마이페이지");
		jbReadBook = new JButton("책 읽기");
		jbEnd= new JButton("종료");
		
		jbLogOut = new JButton("로그아웃");
		jbUpBook = new JButton("도서등록");
		jbrental = new JButton("대출통계");
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		LogOutState();
		
		jpCenter.addChild();
		jpCenter.setTitleBorder("인기 도서");
		jpCenter.jpTop.setFlowleft().add(jcDivision);
		
		jpCenter.jpCenter.setGrid(1, 5, 10,10);
		
		comboChange();
		
	}


	@Override
	public void setEvents() {
		// TODO Auto-generated method stub
		jcDivision.addActionListener(e -> {
			comboChange();
		});
		jbLogin.addActionListener(e -> {
			new LogInFrame(this);
		});
		jbSignUp.addActionListener(e -> {
			new SignUpFrame(this);
		});
	}
	
	private void LogOutState() {
		// TODO Auto-generated method stub
		jpTop.removeAll();
		jpBottom.removeAll();
		
		jlTop = new ImageLabel("로그인 후 이용해주세요.", "메인3.jpg", 1000, 500).setWhite().setSize(30).setCenter().setTextCenter();
		
		jpTop.add(jlTop);
		
		jpBottom.setFlowCenter().add(jbLogin);
		jpBottom.setFlowCenter().add(jbSignUp);
		jpBottom.setFlowCenter().add(jbBookList);
		jpBottom.setFlowCenter().add(jbMyPage);
		jpBottom.setFlowCenter().add(jbReadBook);
		jpBottom.setFlowCenter().add(jbEnd);
		
		super.refresh();
		
	}
	public void LogInState() {
		// TODO Auto-generated method stub
		jpTop.removeAll();
		jpBottom.removeAll();
		
		jlTop = new ImageLabel(Logmodel.LogState.get(1)+"님 환영합니다.", "메인2.jpg", 1000, 500).setWhite().setCenter().setTextCenter().setSize(30);
		
		jpTop.add(jlTop);
		
		jbSignUp.setEnabled(false);
		
		jpBottom.setFlowCenter().add(jbLogOut);
		jpBottom.setFlowCenter().add(jbSignUp);
		jpBottom.setFlowCenter().add(jbBookList);
		jpBottom.setFlowCenter().add(jbMyPage);
		jpBottom.setFlowCenter().add(jbReadBook);
		jpBottom.setFlowCenter().add(jbEnd);
		
		super.refresh();
		
		
	}
	private void comboChange() {
		// TODO Auto-generated method stub
		jpCenter.jpCenter.removeAll();
		String comboIndex = jcDivision.getSelectedIndex() + "";
		if (comboIndex.equals("0")) {
			comboIndex = "%";
		}
		
		 ImageModel data = DbManager.db.getImageData("SELECT d.*, b.* FROM 2023지방_2.rental as r\r\n"
		 		+ "join 2023지방_2.book as b\r\n"
		 		+ "join 2023지방_2.division as d\r\n"
		 		+ "on d.d_no = b.b_no\r\n"
		 		+ "on r.b_no = b.b_no\r\n"
		 		+ "where d.d_no like ?\r\n"
		 		+ "group by r.b_no\r\n"
		 		+ "\r\n"
		 		+ "order by count(r.b_no) limit 5;",9, comboIndex);
		
		Vector<Vector<String>> datas = data.datas;
		Vector<ImageIcon> icons = data.icons;
		
		for (int i = 0; i < icons.size(); i++) {
			
			jpCenter.jpCenter.add(new ImageLabel(datas.get(i).get(3), icons.get(i), 200, 200).setLine().setTextBottom());
			
		}
		super.refresh();
		
	}


}
