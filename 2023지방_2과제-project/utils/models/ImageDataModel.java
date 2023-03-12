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

}
