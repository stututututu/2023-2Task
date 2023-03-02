package db;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.mysql.cj.jdbc.Blob;

import image.ImageModel;

public class DbManager {

	private String url = "jdbc:mysql://localhost/?"
			+ "CharacterEncoding=UTF-8&"
			+ "serverTimezone=UTC&"
			+ "allowPublicKeyRetrieval=true&"
			+ "allowLoadLocalInfile=true&"
			+ "allowMultiQueries=true&";
	private String id = "root";
	private String pw = "1234";
	private Connection con;
	private PreparedStatement pstmt;

	public DbManager() {

		try {
			con = DriverManager.getConnection(url, id, pw);

			System.err.println("연결성공");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("연결실패");
		}

	}

	public int setData(String sql, Object... val) {
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i + 1, val[i]);
			}
			return pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("SetData Faild");
		}

		return -1;

	}

	public Vector<Vector<String>> getData(String sql, Object... val) {
		Vector<Vector<String>> data = new Vector<>();
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i+1, val[i]);
			}
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Vector<String> row = new Vector<>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getString(i+1));
				}
				
				data.add(row);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("GetData Faild");
			return null;
			
		}

		return data;
	}
	public Vector<ImageModel> getImageData(String sql, int imageColIndex, Object...val){
		Vector<ImageModel> data = new Vector<>();
		try {
			pstmt = con.prepareStatement(sql);
			
			int cnt = 1;
			
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i+1, val[i]);
			}
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Vector<String> row = new Vector<>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getString(i+1));
				}
//				Blob blob = rs.getBlob(imageColIndex + 1);
				ImageIcon icon = null;
				
				
				
//				data.add(row);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("GetData Faild");
			return null;
			
		}

		return data;
	}
	
	
}
