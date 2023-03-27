package frames;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class LogInFrame extends BaseFrame {

	private JTextField jtId;
	private JTextField jtPw;
	private JButton jbLogIn;
	private int errorCnt;
	private MainFrame mainFrame;

	public LogInFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		super.BaseFrame("로그인", 393, 237, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtPw = new JPasswordField(10);

		jbLogIn = new JButton("로그인");

	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("로그인").setCenter().setTitle(20));

		jpCenter.addChild();
		jpCenter.setEmpty(10, 10, 10, 10);

		jpCenter.jpLeft.setGrid(2, 1, 10, 10).add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));
		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		jpCenter.jpCenter.setGrid(2, 1, 10, 10).add(jtId);
		jpCenter.jpCenter.setGrid(2, 1, 10, 10).add(jtPw);
		jpCenter.jpCenter.setEmpty(5, 5, 5, 5);

		jpBottom.add(jbLogIn);
		jpBottom.setEmpty(5, 10, 5, 10);

	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
		errorCnt = 0;
		
		jbLogIn.addActionListener(e -> {
			String id = jtId.getText();
			String pw = jtPw.getText();

			Vector<Vector<String>> check = DbManager.db
					.getData("SELECT * FROM 2023지방_2.user where u_id = ? and u_pw = ?;", id, pw);

			if (errorCnt >= 3) {
				message.error("3회오류로 종료합니다.");
				dispose();
				return;
			}
			if (id.isBlank() || pw.isBlank()) {
				message.error("빈칸이 있습니다.");
				errorCnt+=1;
				return;
			}
			if (check.size() == 0) {
				message.error("아이디 또는 비밀번호를 확인하세요.");
				jtId.setText("");
				jtPw.setText("");
				jtId.requestFocus();
				errorCnt+=1;
				return;
			}
			if (id.equals("admin") && pw.equals("1234")) {
				dispose();
				return;
			}
			
			model.logstate = check.get(0);
			mainFrame.logInState();
			dispose();
		});

	}

}
