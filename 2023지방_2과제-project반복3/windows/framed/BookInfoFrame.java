package framed;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ImageIcon;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.ImageLabel;
import jdbc.DbManager;
import model.ImageModel;

public class BookInfoFrame extends BaseFrame {

//	private ImageModel imgData;
	private BaseLabel jName;
	private ImageLabel jlImg;
	private Vector<Vector<String>> divisionData;
	private BaseLabel jlBookType;
	private BaseLabel jlAuthor;
	private Vector<String> data;
	private ImageIcon icon;
	private BaseLabel jlBook;
	private BaseLabel BookInfo;

//	public BookInfoFrame(ImageModel imgData) {
//		// TODO Auto-generated constructor stub
//		this.imgData = imgData;
//		super.BaseFrame("도서정보", 500, 400, null);
//	
//	}
	public BookInfoFrame(Vector<String> data, ImageIcon icon, BookList bookList) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.icon = icon;
		super.BaseFrame("도서정보", 403, 450, bookList);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		divisionData = DbManager.db.getData("SELECT d_name FROM 2023지방_2.division where d_no = ?;",
				data.get(2));

		jName = new BaseLabel(data.get(1)).setTitle(25);
		jlImg = new ImageLabel(null, icon, 200, 250);
		jlBookType = new BaseLabel(divisionData.get(0).get(0)).setTitle(18).setLine();
		jlAuthor = new BaseLabel("저자 :" + data.get(3)).setMatte(0, 0, 3, 0).setTitle(10);
		jlBook= new BaseLabel("재고 :" + data.get(4) + "/" + "페이지 :" + data.get(5) + "권").setMatte(0, 0, 3, 0).setTitle(10);
		
		BookInfo = new BaseLabel(data.get(6)).setTitleborder("설명");;
		
	}

	@Override
	public void setDeisgn() {
		// TODO Auto-generated method stub
		jpTop.setFlowLeft().add(jName);
		jpCenter.addChild();
		jpCenter.jpCenter.add(jlImg);
		jpCenter.jpRight.setPreferredSize(new Dimension(200,80));
		jpCenter.jpRight.setGrid(3, 1, 10, 10).add(jlBookType);
		jpCenter.jpRight.add(jlAuthor);
		jpCenter.jpRight.add(jlBook);
		jpCenter.setEmpty(10, 10, 10, 10);
		
		jpBottom.add(BookInfo);

	}

	@Override
	public void events() {
		// TODO Auto-generated method stub

	}

}
