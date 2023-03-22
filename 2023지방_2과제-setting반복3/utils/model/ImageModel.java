package model;

import java.util.Vector;

import javax.swing.ImageIcon;

public class ImageModel {

	Vector<Vector<String>> datas;
	Vector<ImageIcon> icons;

	public ImageModel(Vector<Vector<String>> data, Vector<ImageIcon> icon) {
		this.datas = datas;
		this.icons = icons;
	}

}
