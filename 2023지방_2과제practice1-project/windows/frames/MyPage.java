package frames;

import java.util.Vector;

import javax.swing.JRadioButton;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import jdbc.DbManager;
import model.model;

public class MyPage extends BaseFrame{

	private JRadioButton jrRental;
	private JRadioButton jrLike;
	private Vector<Vector<String>> data;
	private BaseTable jCenterTable;

	public MyPage(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("마이페이지", 1000, 500, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
		
		jrRental = new JRadioButton("대출내역");
		jrLike = new JRadioButton("관심도서");
		
		data = DbManager.db.getData("SELECT b.b_name,\r\n"
				+ "		concat(r.r_reading, '/', b.b_page),   r_date,   if(\r\n"
				+ "		r.r_returnday = '0000-00-00' or isnull(r.r_returnday),\r\n"
				+ "		date_add(r.r_date, interval 14 + r_count day),r.r_returnday),\r\n"
				+ "        if(r.r_returnday = '0000-00-00' or isnull(r.r_returnday),\r\n"
				+ "		if(date_add(r.r_date, interval 14 + r_count day) > now(),'연체중', '대출중'), '반납완료'),\r\n"
				+ "        r.r_no, b.b_no \r\n"
				+ "        FROM 2023지방_2.rental as r\r\n"
				+ "		join 2023지방_2.book as b\r\n"
				+ "        join 2023지방_2.user as u\r\n"
				+ "        on r.b_no = b.b_no\r\n"
				+ "        where u.u_no = ?;",model.logstate.get(0));
		
		jCenterTable = new BaseTable(data, "도서명", "읽은페이지", "대출일", "반납일", "대출상태");
		
		
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.setFlowleft().add(new BaseLabel("회원:"+model.logstate.get(3)));
		
		
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}

}
