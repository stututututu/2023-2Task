package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DB {

	public static Connection con;
	public static Statement stmt;
	private PreparedStatement pstmt;

	public DB() {
		// TODO Auto-generated constructor stub
		try {
			DbCon();
			DbSet();
			JOptionPane.showConfirmDialog(null, "셋팅성공", "정보", 1);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showConfirmDialog(null, "셋팅성공", "정보", 0);
		}
	}

	private void DbCon() throws SQLException {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost/?" + "serverTimezone=UTC&" + "allowLoadLocalInfile=true";
		con = DriverManager.getConnection(url, "root", "1234");
		stmt = con.createStatement();

	}

	private void DbSet() throws SQLException, FileNotFoundException {
		// TODO Auto-generated method stub
		DB.stmt.execute("drop database if exists 2023지방_2");
		DB.stmt.execute("drop user if exists 'user'@'localhost'");
		DB.stmt.execute("create database 2023지방_2");
		DB.stmt.execute("use 2023지방_2");

		DB.stmt.execute("set global local_infile = 1");

		DB.stmt.execute("create user 'user'@'localhost' identified by '1234'");
		DB.stmt.execute("grant select,insert,delete,update on 2023지방_2.* to 'user'@'localhost'");
		DB.stmt.execute("use 2023지방_2");

		DB.stmt.execute("create table division(" 
		+ "d_no int primary key not null auto_increment,"
		+ "d_name varchar(50)" 
		+ ")");
		DB.stmt.execute("create table user(" 
		+ "u_no int primary key not null auto_increment,"
		+ "u_name varchar(5),"
		+ "u_id varchar(10)," 
		+ "u_pw varchar(10)"
		+ ")");
		DB.stmt.execute("create table book(" 
		+ "b_no int primary key not null auto_increment," + "b_name varchar(50),"
		+ "d_no int," 
		+ "b_author varchar(50)," 
		+ "b_count int," 
		+ "b_page int," 
		+ "b_exp varchar(500),"
		+ "b_img longblob," 
		+ "constraint foreign key (d_no) references division (d_no) on delete cascade"
		+ ")");
		DB.stmt.execute("create table likebook(" 
		+ "l_no int primary key not null auto_increment," 
		+ "u_no int,"
		+ "b_no int," 
		+ "constraint foreign key (u_no) references user (u_no),"
		+ "constraint foreign key (b_no) references book (b_no) on delete cascade" 
		+ ")");
		DB.stmt.execute("create table rental(" 
		+ "r_no int primary key not null auto_increment," 
		+ "u_no int,"
		+ "b_no int," 
		+ "r_date date,"
		+ "r_count int," 
		+ "r_reding int," 
		+ "r_returnday date,"
		+ "constraint foreign key (u_no) references user (u_no),"
		+ "constraint foreign key (b_no) references book (b_no) on delete cascade" 
		+ ")");
		String[] table = "division,user,book,likebook,rental".split(",");
		for (int i = 0; i < table.length; i++) {
		String sql = "load data local infile'datafiles/"+table[i]+".txt' "
		+ "into table " +table[i]+ " lines terminated by '\r\n' ignore 1 lines";
		DB.stmt.execute(sql);

		}
		ResultSet rs = DB.stmt.executeQuery("select * from book");
		while (rs.next()) {

			String sql = "update book set b_img = ? where b_no = ?";
			pstmt = DB.con.prepareStatement(sql);
			FileInputStream fis = new FileInputStream(new File("datafiles/book/"+rs.getString(1)+".jpg"));
			pstmt.setBinaryStream(1, fis);
			pstmt.setInt(2, rs.getInt(1));
			pstmt.execute();
		}
	}

}
