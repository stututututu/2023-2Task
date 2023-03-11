package frames;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.message;
import jdbc.Dbmanager;
import model.model;

public class LogInFrame extends BaseFrame{

	private JTextField jtId;
	private JPasswordField jtPw;
	private JButton jbLogIn;
	private BaseLable jlTitle;
	private MainFrame mainFrame;

	public LogInFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		super.BaseFrame("로그인", 373, 219, mainFrame);
	}



	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtPw = new JPasswordField(10);
		jbLogIn = new JButton("로그인");
		
		jlTitle = new BaseLable("로그인").setText(30).setCenter();
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(jlTitle);
		jpTop.setBorder(10,0,0,0);
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(2, 1, 10, 10).add(new JLabel("아이디"));
		jpCenter.jpLeft.add(new JLabel("비밀번호"));
		jpCenter.jpLeft.setBorder(10,20,10,10);
		
		jpCenter.jpCenter.setGrid(2, 1, 10, 10).add(jtId);
		jpCenter.jpCenter.add(jtPw);
		jpCenter.jpCenter.setBorder(10, 20, 10, 20);
		
		jpBottom.add(jbLogIn);
		jpBottom.setBorder(10, 20, 10, 20);
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
		jbLogIn.addActionListener(e -> {
			String id = jtId.getText();
			String pw = jtPw.getText();
			Vector<Vector<String>> check =Dbmanager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ? and u_pw = ?;", id, pw);
			
			int cnt = 0;
				
			if (id.equals("admin") && pw.equals("1234")) {
				dispose();
				return;
			}
			if (check.size() != 0) {
				model.LogState = check.get(0);
				mainFrame.logInState();
				super.close();
				return;
			}
			if (id.isBlank() || pw.isBlank()) {
				cnt++;
				message.error("빈칸이 있습니다.");
				return;
			}
			if (cnt >= 2) {
				message.error("3회 초과");
				super.close();
			}
			System.out.println(cnt);
			cnt++;
			message.error("아이디 또는 비밀번호를 확인하세요.");
			jtId.setText("");
			jtPw.setText("");
			jtId.requestFocus();
			
		});
		
//		super.addWindowListener(new WindowAdapter() {
//		
//			@Override
//			public void windowClosing(WindowEvent e) {
//				// TODO Auto-generated method stub
//				super.windowClosing(e);
//				dispose();
//			}
//		});
	}

}
