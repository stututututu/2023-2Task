package image;

import java.util.Vector;

import javax.swing.ImageIcon;

public class ImageModel {

	public Vector<String> data;
	public ImageIcon icon;

	public ImageModel(Vector<String> data, ImageIcon icon) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.icon = icon;
	}
	
	public Vector<String> getData(){
		return data;
	}
	public void setData(Vector<String> data) {
		this.data = data;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}


}
