package framed;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import model.model;

public class myPageFrame extends BaseFrame{

	private JRadioButton jrRental;
	private JRadioButton jrLike;
	private JButton jbDelete;
	private JButton jb;
	private JButton jbPdf;
	private BaseLabel jlUserInfo;
	private String allData;
	private String returnData;
	private String noneReturn;
	private String returnIng;

	public myPageFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("마이페이지", 600, 500, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jrRental = new JRadioButton("대출내역");
		jrLike = new JRadioButton("관심도서");
		
		jlUserInfo = new BaseLabel("총 대여이력:" + allData + "권" +"반납완료:" + returnData + "권" +"연체 중:" + noneReturn + "권" +"대출 중" + returnIng + "권");
		
		jbDelete = new JButton("삭제하기");		
		jbPdf = new JButton("pdf출력");		
	}

	@Override
	public void setDeisgn() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpLeft.setFlowLeft().add(new BaseLabel("회원:" + model.LogState.get(1)));
		jpTop.jpRight.setFlowRight().add(jrRental);
		jpTop.jpRight.add(jrLike);
		
		
		jpBottom.addChild();
		jpBottom.jpLeft.setFlowLeft().add(jlUserInfo);
		
		jpBottom.jpRight.setFlowRight().add(jbDelete);
		jpBottom.jpRight.add(jbPdf);
		
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}

}
