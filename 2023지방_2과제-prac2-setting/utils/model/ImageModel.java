package model;

import java.util.Vector;

import javax.swing.ImageIcon;

public class ImageModel {

	public Vector<Vector<String>> datas = new Vector<>();
	public Vector<ImageIcon> icons = new Vector<>();
	
	public ImageModel(Vector<Vector<String>> datas, Vector<ImageIcon> icons) {
		this.datas = datas;
		this.icons = icons;
	}

	public Vector<Vector<String>> getDatas() {
		return datas;
	}

	public void setDatas(Vector<Vector<String>> datas) {
		this.datas = datas;
	}

	public Vector<ImageIcon> getIcons() {
		return icons;
	}

	public void setIcons(Vector<ImageIcon> icons) {
		this.icons = icons;
	}


}
