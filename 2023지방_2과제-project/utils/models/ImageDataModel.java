package models;

import java.util.Vector;

import javax.swing.ImageIcon;

public class ImageDataModel {

	public Vector<Vector<String>> datas;
	public Vector<ImageIcon> icons;

	public ImageDataModel(Vector<Vector<String>> datas, Vector<ImageIcon> icons) {
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
