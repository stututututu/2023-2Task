package framed;

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
	private JPasswordField jtPw;
	private JButton jbSignUp;
	private String ckName;

	public SignUpFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("회원가입", 462, 334, mainFrame);
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
	public void setDeisgn() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("회원가입").setCenter().setTitle(30));
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(3, 1, 10, 10).add(new BaseLabel("이름"));
		jpCenter.jpLeft.add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));
		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		
		jpCenter.jpCenter.setGrid(3, 1, 10, 10).add(jtName);
		jpCenter.jpCenter.add(jtId);
		jpCenter.jpCenter.add(jtPw);
		jpCenter.jpCenter.setEmpty(5, 5, 5, 5);
		
		jpBottom.add(jbSignUp);
		jpBottom.setEmpty(10, 5, 10, 5);
		
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbSignUp.addActionListener(e -> {
			String name = jtName.getText();
			String id = jtId.getText();
			String pw = jtPw.getText();
			ckName = "^(.*[ㄱ-ㅎ ㅏ-ㅣ 가-힣]).{1,}$";
			boolean NameRs = Pattern.matches(ckName, name);
			String ckId = "^(.*[ㄱ-ㅎ ㅏ-ㅣ 가-힣])$";
			boolean idRs = Pattern.matches(ckId, id);
			String ckPw = "^(?=.*[a-zA-z])(?=.*[1-9])(?=.*[!@#$]).{5,}$";
			boolean pwRs = Pattern.matches(ckPw, pw);
			String ckSpace= "^.*.(.)(\\1)*$";
			boolean spaceRs = Pattern.matches(ckSpace, pw);
			
			
			Vector<Vector<String>> check = DbManager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ?;", id);
			if (name.isBlank() || id.isBlank() || pw.isBlank()) {
				message.error("빈칸이 있습니다.");
				return;
			}
			if (!NameRs) {
				message.error("이름은 한글로 2글자 이상만 가능합니다.");
				jtName.setText("");
				jtName.requestFocus();
				return;
			}
			if (id.equals("admin") || check.size() != 0) {
				message.error("이미 있는 아이디 입니다.");
				jtId.setText("");
				jtId.requestFocus();
				return;
			}
			if (idRs) {
				message.error("아이디에 한글은 사용이 불가합니다.");
				return;
			}
			if (!pwRs) {
				message.error("비밀번호 형식이 일치하지 않습니다.");
				return;
			}
			if (spaceRs) {
				message.error("비밀번호는 연속으로 같은 글자가 올 수 없습니다.");
				return;
			}
			DbManager.db.setData("INSERT INTO `2023지방_2`.`user` (`u_name`, `u_id`, `u_pw`) VALUES ( ?, ?, ?);\r\n"
					+ "", name, id, pw);
			message.info(name+"님 회원가입이 완료되었습니다.");
			super.close();
		});
		
	}

}
