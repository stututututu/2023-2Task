package jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.ImageIcon;

import model.ImageModel;

public class DbManagerTraning {

	private String url = "jdbc:mysql://localhost/?"
			+ "CharacterEncoding=UTF-8&"
			+ "serverTimezone=UTC&"
			+ "allowPublicKeyRetrieval=true&"
			+ "allowLoadLocalInfile=true&"
			+ "allowMultiQueries=true";
	private String id;
	private String pw;
	private Connection con;
	private PreparedStatement pstmt;

	public DbManagerTraning() {
		// TODO Auto-generated constructor stub
		try {
			con  = DriverManager.getConnection(url, id, pw);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("연결 실패");
		}
	}
	public int setData(String sql, Object...val) {
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i+1, val[i]);
			}
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("setData Faild");
		}
		return -1;
	}
	public Vector<Vector<String>> getData(String sql, Object...val) {
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
			System.out.println("getData Faild");
		}
		return data;
	}
	public ImageModel getImageData(String sql,int colIndex, Object...val) {
		Vector<Vector<String>> data = new Vector<>();
		Vector<ImageIcon> icon= new Vector<>();
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
				Blob blob = rs.getBlob(colIndex + 1);
				icon.add(new ImageIcon(blob.getBinaryStream().readAllBytes()));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("getData Faild");
		}
		return new ImageModel(data, icon);
	}
	

}
