package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
	
	public static Connection getConnection()  {
		Connection con=null;
		// 1. MySQL database class �ε��Ѵ�
		try {
			Class.forName("com.mysql.jdbc.Driver");	
		// 2.�ּ�, ���̵�, ��й�ȣ�� ���ؼ� ���ӿ�û�Ѵ�.		
			con = DriverManager.getConnection("jdbc:mysql://localhost/phonedb", "root", "123456");
			//MainController.callAlert("���Ἲ�� : �����ͺ��̽� ���Ἲ��");
			
		} catch (Exception e) {
		MainController.callAlert("������� : �����ͺ��̽� �������");
			e.printStackTrace();
			return null;
		}

		return con;
	}

}
