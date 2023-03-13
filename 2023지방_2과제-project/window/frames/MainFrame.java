package frames;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BasePanel;
import base.comp.imageLable;
import jdbc.Dbmanager;
import model.model;
import models.ImageDataModel;

public class MainFrame extends BaseFrame{

	private imageLable jlTop;
	private BaseCombo jcAll;
	private Vector<Vector<String>> divisionData;
	private JButton jbLogIn;
	private JButton jbSignUp;
	private JButton jbBookList;
	private JButton jbMyPage;
	private JButton jbRead;
	private JButton jbEnd;
	private ImageDataModel data;
	private imageLable jlImg;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("메인", 884, 731, null);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jlTop = new imageLable("로그인 후 이용해주세요.", "메인3",958,400).setCenter().setTextCenter().setTextSize(40).setColor();
		divisionData = Dbmanager.db.getData("SELECT d_name FROM 2023지방_2.division;");
		jcAll = new BaseCombo().setDivision(divisionData);
		
		jbLogIn = new JButton("로그인");
		jbSignUp = new JButton("회원가입");
		jbBookList = new JButton("도서 목록");
		jbMyPage = new JButton("마이페이지");
		jbRead = new JButton("책 일기");
		jbEnd = new JButton("종료");
		
		}
		

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(jlTop);
		jpCenter.addChild();
		jpCenter.setTitleLine("인기 도서");
		jpCenter.jpTop.setFlowLeft().add(jcAll);
		
		
		jpCenter.jpCenter.setGrid(1, 5, 10, 10);
		imageChange();
		
		jpBottom.setFlowCenter().add(jbLogIn);
		jpBottom.add(jbSignUp);
		jpBottom.add(jbBookList);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbRead);
		jpBottom.add(jbEnd);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbLogIn.addActionListener(e -> {
			new LogInFrame(this)	;
		});
		jbSignUp.addActionListener(e -> {
			new SignUpFrame(this);
		});
		
		jbEnd.addActionListener(e -> {
			super.close(); 
		});
		jcAll.addActionListener(e -> {
			imageChange();
			super.refresh();
		});
		jbBookList.addActionListener(e -> {
			new BookListFrame(this);
		});
	}

	private void imageChange() {
		// TODO Auto-generated method stub
		jpCenter.jpCenter.removeAll();
		String cNum = jcAll.getSelectedIndex() + "";
		if (cNum.equals("0")) {
			cNum = "%";
		}
		
		System.out.println(cNum);

		data = Dbmanager.db.getImageData("SELECT b.*, d.* FROM 2023지방_2.rental as r\r\n"
				+ "				join 2023지방_2.book as b \r\n"
				+ "                join 2023지방_2.division as d \r\n"
				+ "                on r.b_no = b.b_no\r\n"
				+ "				and b.d_no = d.d_no "
				+ "				where d.d_no like ?\r\n"
				+ "                group by r.b_no \r\n"
				+ "				order by count(r.b_no) desc limit 5;", 7, cNum);
		Vector<Vector<String>> datas = data.datas;
		Vector<ImageIcon> icons = data.icons;
		
		for (int i = 0; i < 5; i++) {
			String Title = datas.get(i).get(1);
			
			jlImg = new imageLable(Title, icons.get(i), 165, 170).setCenter().setTextBottom().setline().setTextSize(11);
			
			jpCenter.jpCenter.add(jlImg);
		}
		
		super.refresh();
	}

	public void logInState() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();
		jpTop.removeAll();
		jlTop = new imageLable(model.LogState.get(1) + "님 환영합니다.", "메인3", 1000, 500).setTextSize(30).setTextCenter().setColor();
		jpTop.add(jlTop);
		jbSignUp.setEnabled(false);
		jbBookList.setEnabled(true);
		jbMyPage.setEnabled(true);
		jbRead.setEnabled(true);

		jpBottom.setFlowCenter().add(jbLogIn);
		jpBottom.add(jbSignUp);
		jpBottom.add(jbSignUp);
		jpBottom.add(jbBookList);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbRead);
		jpBottom.add(jbEnd);

		super.refresh();
	}

}
