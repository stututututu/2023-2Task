package frames;

import java.util.PrimitiveIterator.OfDouble;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.message;
import db.DbManager;
import model.model;

public class LoginFrame extends BaseFrame{

	private JTextField jtId;
	private JTextField jtPw;
	private JButton jbLogIn;
	private MainFrame mainFrame;

	public LoginFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("로그인", 420, 231);
		this.mainFrame = mainFrame;
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtPw = new JTextField(10);
		
		jbLogIn = new JButton("로그인");
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		
		jpTop.add(new BaseLable("로그인").setCenter().setFont(20));
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(2, 1, 10, 10).add(new BaseLable("아이디").setCenter());
		jpCenter.jpLeft.add(new BaseLable("비밀번호").setCenter());
		
		jpCenter.jpCenter.setGrid(2, 1, 15, 15).add(jtId);
		jpCenter.jpCenter.add(jtPw);
		
		jpCenter.setBorder(5,10,5,10);
		
		jpCenter.jpLeft.setBorder(5,5,5,5);
		
		jpBottom.add(jbLogIn);
		jpBottom.setBorder(5,10,5,10);
		
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbLogIn.addActionListener(e -> {
			String id = jtId.getText();
			String pw = jtPw.getText();
			Vector<Vector<String>> check = DbManager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ? and u_pw = ?;", id, pw);
			if (id.isBlank() || pw.isBlank()) {
				message.error("빈칸이 있습니다.");
				return;
			}
			if (check.size() == 0) {
				message.error("아이디 또는 비밀번호를 확인하세요.");
				jtId.setText("");
				jtPw.setText("");
				jtId.requestFocus();
				return;
			}
			if (id == "admin" && pw == "1234") {
				mainFrame.ManagerState();
				dispose();
			}
			model.LogState = check.get(0);
			mainFrame.LogState();
			dispose();
		});
	}

}
