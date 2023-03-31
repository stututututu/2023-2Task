package frames;

import java.awt.print.Book;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.ImageLabel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class BookInfo extends BaseFrame{

	private Vector<String> data;
	private ImageIcon icon;
	private Vector<Vector<String>> divisionData;
	private BaseLabel jldivision;
	private JButton jbBorrow;
	private Vector<Vector<String>> borrowCk;

	public BookInfo(Vector<String> vector, ImageIcon imageIcon, BookListFrame bookListFrame) {
		// TODO Auto-generated constructor stub
		this.data = vector;
		this.icon = imageIcon;
		super.BaseFrame("도서 정보", 458, 475, bookListFrame);
		
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		borrowCk = DbManager.db.getData("SELECT * FROM 2023지방_2.rental where u_no = ? and b_no = ?;", model.logstate.get(0),data.get(0));
		divisionData = DbManager.db.getData("SELECT * FROM 2023지방_2.division where d_no = ?;", data.get(2));
		jldivision = new BaseLabel(divisionData.get(0).get(1)).setline().setTitle(15).setCenter().pSize(10,10);
		
		jbBorrow = new JButton("대출");
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel(data.get(1)).setTitle(30));
		jpCenter.addChild();
		jpCenter.jpLeft.add(new ImageLabel(null, icon, 250, 280));
		jpCenter.jpCenter.setGrid(3, 1, 10, 10).add(jldivision);
		jpCenter.jpCenter.add(new BaseLabel("저자:" + data.get(3)).setMatte(0, 0, 3, 0));
		jpCenter.jpCenter.add(new BaseLabel("재고:" + data.get(4)+ "/" + "페이지:" + data.get(5) + "권").setMatte(0, 0, 3, 0));
		jpBottom.addChild();
		jpBottom.jpCenter.add(new BaseLabel(data.get(6)).settitleLine("설명"));
		
		if (data.get(4).equals("0")) {
			jbBorrow.setEnabled(false);
			jpBottom.jpBottom.setFlowRight().add(jbBorrow);
			return;
		}
		
		jpBottom.jpBottom.setFlowRight().add(jbBorrow);
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
		jbBorrow.addActionListener(e -> {
			
			
			if (borrowCk.size() != 0) {
				message.error("이미 대출 중인 도서입니다.");
				return;
			}
			message.info("대출이 완료되었습니다.");
			DbManager.db.setData("UPDATE `2023지방_2`.`book` SET `b_count` = b_count -1  WHERE (`b_no` = ?);\r\n"
					+ "",data.get(0));
			
			
		});
		
	}

}
