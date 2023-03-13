package frames;

import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.message;
import jdbc.Dbmanager;

public class SignUpFrame extends BaseFrame
{

	private JTextField jtName;
	private JTextField jtId;
	private JPasswordField jtPw;
	private JButton jbSignUp;

	public SignUpFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("회원가입", 390, 364, mainFrame);
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
		jpTop.add(new BaseLable("회원가입").setCenter().setText(30));
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(3, 1, 10, 10).add(new BaseLable("이름"));
		jpCenter.jpLeft.add(new BaseLable("아이디"));
		jpCenter.jpLeft.add(new BaseLable("비밀번호"));
		
		jpCenter.jpRight.setGrid(3, 1, 10, 10).add(jtName);
		jpCenter.jpRight.add(jtId);
		jpCenter.jpRight.add(jtPw);
		
		
		jpBottom.add(jbSignUp);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbSignUp.addActionListener(e -> {
			String name = jtName.getText();
			String id= jtId.getText();
			String pw = jtPw.getText();
			
			String kPattern = "^(.*[ㄱ-ㅎㅏ-ㅣ가-힣]).{1,}$";
			boolean kMatch = Pattern.matches(kPattern, name);
			String ePattern = "^(?=.*[a-zA-Z])(?=.*[1-9])(?=.*[!@#$]).{5,}$";
			boolean pwRs = Pattern.matches(ePattern, pw);
			String idPattern = "^(.*[a-zA-Z])$";
			boolean idRs = Pattern.matches(idPattern, id);
			
			String StringCk = "^.*.(.)(\\1)*$";
			boolean StringRs = Pattern.matches(StringCk, pw);
			
			Vector<Vector<String>> check = Dbmanager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ?;", id);
			
			if (name.isBlank() || id.isBlank() || pw.isBlank()) {
				message.error("빈칸이 있습니다.");
				return;
			}
			if (!kMatch) {
				message.error("이름은 한글로 두글자 이상 가능합니다");
				return;
			}
			if (!idRs) {
				message.error("아이디에 한글은 사용이 불가능합니다.");
				return;
			}
			if (!pwRs) {
				message.error("비밀번호 형식이 일치하지 않습니다");
				return;
			}
			if (StringRs) {
				message.error("비밀번호는 연속된 글자가 올 수 없습니다.");
			}
			if (check.size() != 0 || id.equals("admin")) {
				message.error("이미 있는 아이디 입니다.");
				return;
			}
			
			Dbmanager.db.setData("INSERT INTO `2023지방_2`.`user` (`u_name`, `u_id`, `u_pw`) VALUES (?, ?, ?);", name, id, pw);
			message.info(name+"님 회원가입이 완료되었습니다.");
			super.close();
		});
		
		
	}

}
