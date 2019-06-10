package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
	
	public static Connection getConnection()  {
		Connection con=null;
		// 1. MySQL database class 로드한다
		try {
			Class.forName("com.mysql.jdbc.Driver");	
		// 2.주소, 아이디, 비밀번호를 통해서 접속요청한다.		
			con = DriverManager.getConnection("jdbc:mysql://localhost/phonedb", "root", "123456");
			//MainController.callAlert("연결성공 : 데이터베이스 연결성공");
			
		} catch (Exception e) {
		MainController.callAlert("연결실패 : 데이터베이스 연결실패");
			e.printStackTrace();
			return null;
		}

		return con;
	}

}
