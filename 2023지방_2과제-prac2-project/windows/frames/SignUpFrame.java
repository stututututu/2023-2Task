package frames;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;

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

	}

}
