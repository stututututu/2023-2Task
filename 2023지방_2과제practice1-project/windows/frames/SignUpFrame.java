package frames;

import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.message;
import jdbc.DbManager;

public class SignUpFrame extends BaseFrame{
	private JTextField jtName;
	private JTextField jtId;
	private JTextField jtPw;
	private JButton jbSignUp;

	public SignUpFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("회원가입", 400, 300, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtName = new JTextField(10);
		jtId = new JTextField(10);
		jtPw = new JPasswordField(10);
		
		jbSignUp = new JButton("회원가입");
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("회원가입").setCenter().setTitle(25));
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(3, 1, 10, 10).add(new BaseLabel("이름"));
		jpCenter.jpLeft.add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));
		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		jpCenter.setEmpty(5, 10, 5, 10);

		jpCenter.jpCenter.setGrid(3, 1, 10, 10).add(jtName);
		jpCenter.jpCenter.add(jtId);
		jpCenter.jpCenter.add(jtPw);
		jpCenter.jpCenter.setEmpty(5, 5, 5, 5);
		
		jpBottom.add(jbSignUp);
		jpBottom.setEmpty(5, 10, 5, 10);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbSignUp.addActionListener(e ->{
			String name = jtName.getText();
			String id = jtId.getText();
			String pw = jtPw.getText();
			
			String ckName = "^(.*[ㄱ-ㅎ ㅏ-ㅣ 가-힣]).{1,}$";
			boolean rsName = Pattern.matches(ckName, name);
			String ckid = "^(.*[ㄱ-ㅎ ㅏ-ㅣ 가-힣])$";
			boolean rsId = Pattern.matches(ckid, id);
			String ckPw = "^(?=.*[a-zA-Z])(?=.*[1-9])(?=.*[!@#$]).{5,}$";
			boolean rsPw = Pattern.matches(ckPw, pw);
			String ckPw2 = "^(.)\1$";
			boolean rsPw2 = Pattern.matches(ckPw2, pw);
			Vector<Vector<String>> check = DbManager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ?;",id);
			
			if (name.isBlank() || id.isBlank() || pw.isBlank()) {
				message.error("빈칸이 있습니다.");
				return;
			}
			if (!rsName) {
				message.error("이름은 한글로 2글자이상만 가능합니다.");
				jtName.setText("");
				jtName.requestFocus();
				return;
			}
			
			if (check.size() != 0 || id.equals("admin")) {
				message.error("이미 있는 아이디 입니다.");
				jtId.setText("");
				jtId.requestFocus();
				return;
			}
			if (rsId) {
				message.error("아이디에 한글은 사용이 불가능 합니다.");
				jtId.setText("");
				jtId.requestFocus();
				return;
			}
			if (!rsPw) {
				message.error("비밀번호 형식이 일치하지 않습니다.");
				jtPw.setText("");
				jtPw.requestFocus();
				return;
			}
			if (rsPw2) {
				message.error("비밀번호는 연속으로 같은 글자가 올 수 없습니다.");
				jtPw.setText("");
				jtPw.requestFocus();
				return;
			}
			
			DbManager.db.setData("INSERT INTO `2023지방_2`.`user` (`u_name`, `u_id`, `u_pw`) VALUES (?, ?, ?);\r\n"
					+ "", name,id,pw);
			message.info(name+"님 회원가입이 완료되었습니다.");
			dispose();
			
			
			
		});
		
	}

}
