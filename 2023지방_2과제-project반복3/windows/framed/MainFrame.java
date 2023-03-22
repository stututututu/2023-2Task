package framed;

import javax.swing.JLabel;

import base.comp.BaseFrame;
import base.comp.ImageLabel;

public class MainFrame extends BaseFrame{

	private ImageLabel jlTop;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("메인", 500, 500, null);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jlTop = new ImageLabel("로그인 후 이용해주세요.", "메인3", 500, 500);
		
	}

	@Override
	public void setDeisgn() {
		// TODO Auto-generated method stub
		jpTop.add(jlTop);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}

}
