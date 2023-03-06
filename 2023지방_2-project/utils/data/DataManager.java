package data;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTextField;

import base.comp.message;

public class DataManager {
	
	public static boolean isBlank(Vector<JTextField> jts) {
		for (JTextField jt : jts) {
			if (jt.getText().isBlank()) {
				message.error("빈칸이 있습니다.");
				return true;
			}
		}
		return false;
	}
	
	public static Vector<Vector<String>> firstDatAdd(Vector<Vector<String>> data, String...vals){
		Vector<String> row = new Vector<>();
		
		for (String val: vals) {
			row.add(val);
		}
		data.add(0, row);
		
		return data;
	}
	public static String subString(String text, int size, String newtext) {
		// TODO Auto-generated method stub
		return (text.length() <= 13? text: text.substring(0,13)) + newtext;
		
	}
	public static boolean jtInit(Vector<JTextField> jts) {
		for (JTextField jt : jts) {
			jt.setText("");
		}
		jts.get(0).requestFocus();
		return false;
		
	}
	
}
