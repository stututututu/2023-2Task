package framed;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class LogInFrame extends BaseFrame{

	private JTextField jtId;
	private JPasswordField jtpw;
	private JButton jbLogIn;
	private MainFrame mainFrame;

	public LogInFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		super.BaseFrame("로그인", 420, 273, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtpw = new JPasswordField(10);
		jbLogIn = new JButton("로그인");
	}

	@Override
	public void setDeisgn() {
		// TODO Auto-generated method stub
		
		jpTop.add(new BaseLabel("로그인").setCenter().setTitle(30));
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(2, 1, 10, 10).add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));
		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);

		jpCenter.jpCenter.setGrid(2, 1, 10, 10).add(jtId);
		jpCenter.jpCenter.add(jtpw);
		jpCenter.jpCenter.setEmpty(5, 5, 5, 5);
		
		jpBottom.add(jbLogIn);
		jpBottom.setEmpty(10, 10, 10, 10);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbLogIn.addActionListener(e -> {
			String id = jtId.getText();
			String pw = jtpw.getText();
			
			Vector<Vector<String>> check = DbManager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ? and u_pw = ?;", id, pw);
			
			int ckError = 0;
			if (ckError >= 3) {
				message.error("3회오류로 종료합니다.");
				super.close();
				return;
			}
			
			if (id.equals("admin") && pw.equals("1234")) {
				mainFrame.ManagerState();
				return;
			}
			if (id.isBlank() || pw.isBlank()) {
				message.error("빈칸이 있습니다.");
				ckError+=1;
				return;
			}
			if (check.size() == 0) {
				message.error("아이디 또는 비밀번호를 확인하세요.");
				ckError+=1;
				jtId.setText("");
				jtpw.setText("");
				jtId.requestFocus();
				return;
			}
			model.LogState = check.get(0);
			mainFrame.LogInState();
			super.close();
		});
		
	}

}
