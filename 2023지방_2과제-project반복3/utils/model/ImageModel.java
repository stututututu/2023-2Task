package model;

import java.util.Vector;

import javax.swing.ImageIcon;

public class ImageModel {

	public Vector<Vector<String>> datas;
	public Vector<ImageIcon> icons;

	public ImageModel(Vector<Vector<String>> datas, Vector<ImageIcon> icons) {
		this.datas = datas;
		this.icons = icons;
	}

}
