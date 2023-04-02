package frames;

import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.msg;
import jdbc.DbManager;

public class SignUpFrame extends BaseFrame {

	private JTextField jtName;
	private JTextField jtId;
	private JPasswordField jtPw;
	private JButton jbSignUp;

	public SignUpFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("회원가입", 384, 315, mainFrame);
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
		jpTop.add(new BaseLabel("회원가입").setCenter().setTitle(30));
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(3, 1, 10, 10).add(new BaseLabel("이름"));
		jpCenter.jpLeft.add(new BaseLabel("아이디"));		
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));		

		jpCenter.jpCenter.setGrid(3, 1, 10, 10).add(jtName);
		jpCenter.jpCenter.add(jtId);
		jpCenter.jpCenter.add(jtPw);
		
		jpBottom.add(jbSignUp);
		
		jpCenter.setEmpty(5, 5, 5, 5);
		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		jpCenter.jpRight.setEmpty(5, 5, 5, 5);
		jpBottom.setEmpty(5, 5, 5, 5);
		
	}

	@Override
	public void setEvents() {
		// TODO Auto-generated method stub
		jbSignUp.addActionListener(e -> {
			
			String name = jtName.getText();
			String id = jtId.getText();
			String pw = jtPw.getText();
			
			String ckName = "^(.*[ㄱ-ㅎㅏ-ㅣ 가-힣]).{1,}$";
			boolean rsName = Pattern.matches(ckName, name);
			
			
			
			Vector<Vector<String>> check = DbManager.db
					.getData("SELECT * FROM 2023지방_2.user where u_id =?;", id);
			
			if (id.isBlank()||pw.isBlank()) {
				msg.error("빈칸이 있습니다.");
				return;
			}
			if (check.size() != 0 || id.equals("admin")) {
				msg.error("이미 있는 아이디 입니다.");
				return;
			}
			if (!rsName) {
				msg.error("이름은 한글로 2 글자 이상만 가능합니다.");
				return;
			}
			String ckId= "^(.*[ㄱ-ㅎㅏ-ㅣ 가-힣])$";
			if (Pattern.matches(ckId, id)) {
				msg.error("아이디에 한글은 사용이 불가능합니다.");
				return;
			}
			String ckPw = "^(?=.*[a-zA-Z])(?=.*[1-9])(?=.*[!@#$]).{5,}$";
			if (!Pattern.matches(ckPw, pw)) {
				msg.error("비밀번호형식이 일치하지 않습니다.");
				return;
			}
			String ck = "^(.)\1$";
			if (Pattern.matches(ck, pw)) {
				msg.error("연속 불가.");
				return;
			}
			
			
			DbManager.db.setData("INSERT INTO `2023지방_2`.`user` (`u_name`, `u_id`, `u_pw`) VALUES (?, ?, ?);\r\n"
					+ "",name,id,pw);
			
			
		});
		
	}

}
