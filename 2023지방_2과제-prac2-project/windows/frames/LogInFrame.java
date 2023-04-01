package frames;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.msg;
import jdbc.DbManager;
import model.Logmodel;

public class LogInFrame extends BaseFrame {

	private JTextField jtId;
	private JPasswordField jtPw;
	private JButton jbLogIn;
	private MainFrame mainFrame;

	public LogInFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		super.BaseFrame("로그인", 400, 220, mainFrame);
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
		jpTop.add(new BaseLabel("로그인").setTitle(30).setCenter());

		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(2, 1, 10, 10).add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));
		jpCenter.jpCenter.setGrid(2, 1, 10, 10).add(jtId);
		jpCenter.jpCenter.add(jtPw);

		jpBottom.add(jbLogIn);
		jpBottom.setEmpty(5, 5, 5, 5);
		jpCenter.setEmpty(5, 5, 5, 5);
		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		jpCenter.jpRight.setEmpty(5, 5, 5, 5);
	}

	int cnt = 0;

	public void setEvents() {
		// TODO Auto-generated method stub
		jbLogIn.addActionListener(e -> {
			String id = jtId.getText();
			String pw = jtPw.getText();

			Vector<Vector<String>> check = DbManager.db
					.getData("SELECT * FROM 2023지방_2.user where u_id =? and u_pw = ?;", id, pw);

			if (cnt >= 3) {
				msg.error("3회 오류로 종료합니다.");
				dispose();
				return;
			}
			if (id.isBlank() || pw.isBlank()) {

				msg.error("빈칸이 있습니다.");
				cnt += 1;
				return;
			}
			if (check.size() == 0) {
				msg.error("아이디 또는 비밀번호를 확인하세요.");
				jtId.setText("");
				jtPw.setText("");
				jtId.requestFocus();
				cnt += 1;
				return;
			}
			if (id.equals("admin") && pw.equals("1234")) {
				dispose();
				return;
			}

			Logmodel.LogState = check.get(0);
			mainFrame.LogInState();
			dispose();

		});

	}

}
