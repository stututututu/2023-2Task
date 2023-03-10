package frames;

import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;


import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.message;
import db.DbManager;

public class SignUpFrame extends BaseFrame {

	private JTextField jtId;
	private JTextField jtPw;
	private JTextField jtName;
	private JButton jbSignUp;

	public SignUpFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("회원가입", 393, 290);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtName = new JTextField(10);
		jtId = new JTextField(10);
		jtPw = new JTextField(10);

		jbSignUp = new JButton("회원가입");
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		
		jpTop.add(new BaseLable("회원가입").setCenter().setFont(30));
		
		jpCenter.addChild();
		jpCenter.setBorder(5,10,5,10);
		jpCenter.jpLeft.setGrid(3, 1, 10, 10).add(new BaseLable("이름").setCenter());
		jpCenter.jpLeft.add(new BaseLable("아이디").setCenter());
		jpCenter.jpLeft.add(new BaseLable("비밀번호").setCenter());

		jpCenter.jpCenter.setBorder(5,10,5,10);
		jpCenter.jpCenter.setGrid(3, 1, 20, 20);
		jpCenter.jpCenter.add(jtName);
		jpCenter.jpCenter.add(jtId);
		jpCenter.jpCenter.add(jtPw);
		
		jpBottom.add(jbSignUp);
		jpBottom.setBorder(5,10,5,10);
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbSignUp.addActionListener(e -> {
			String name = jtName.getText();
			String id = jtId.getText();
			String pw = jtPw.getText();
			
			String kPattern = "^(.*[ㄱ-ㅎㅏ-ㅣ가-힣]).{1,}$";
			boolean kRs = Pattern.matches(kPattern, name);
			String idPattern = "^(.*[a-zA-z])$";
			boolean idRs = Pattern.matches(idPattern, id);
			String pwPattern = "^(?=.*[1-9])(?=.*[a-zA-Z])(?=.*[!@#$]).{6,}$";
			boolean pwRs = Pattern.matches(pwPattern, pw);
			Vector<Vector<String>> check = DbManager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ?;", id);
			
			if (name.isBlank() || id.isBlank() || pw.isBlank()) {
				message.error("빈칸이 있습니다.");
				return;
			}
			if (!kRs) {
				message.error("이름은 한글로 2글자 이상만 가능합니다.");
				jtName.setText("");
				jtName.requestFocus();
				return;
			}
			if (check.size() != 0 || id == "admin") {
				message.error("이미 있는 아이디 입니다.");
				return;
			}
			if (!idRs) {

				message.error("아이디에 한글은 사용이 불가능합니다.");
				return;
			}
			if (!pwRs) {
				message.error("비밀번호 형식이 일치하지 않습니다.");
				return;
			}
			DbManager.db.setData("INSERT INTO `2023지방_2`.`user` (`u_name`, `u_id`, `u_pw`) VALUES ( ?, ?, ?);\r\n"
					+ "", name, id, pw);
			message.info(name+"님 회원가입이 완료되었습니다.");
			dispose();
			
			
			
			
		});
		
	}

}
