package jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.ImageIcon;

import models.ImageDataModel;

public class DbmanagerTraning {

	
	
	private String url;
	private String id;
	private String pw;
	private Connection con;
	private PreparedStatement pstmt;

	public DbmanagerTraning() {
		// TODO Auto-generated constructor stub
		try {
			con = DriverManager.getConnection(url, id, pw);
			
		 System.out.println("연결 성공");
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
			System.out.println("SetData Faild");
			return -1;
		}
	}
	public Vector<Vector<String>> getData(String sql, Object... val){
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
					row.add(rs.getObject(i+1)+"");
					
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
	public ImageDataModel getImageData(String sql,int imgeColIndex, Object... val){
		Vector<Vector<String>> datas = new Vector<>();
		Vector<ImageIcon> icons = new Vector<>();
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
					row.add(rs.getObject(i+1)+"");
					
				}
				datas.add(row);
				
				Blob blob = rs.getBlob(imgeColIndex + 1);
				
				icons.add(new ImageIcon(blob.getBinaryStream().readAllBytes()));				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("GetImageData Faild");
			return null;
		}
		return new ImageDataModel(datas, icons);
	}

}
