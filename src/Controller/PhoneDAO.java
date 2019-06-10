package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Client;
import Model.OutPhone;
import Model.Phone;
import Model.RegPhone;
import Model.Stock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PhoneDAO {
	static ObservableList<String> company = FXCollections.observableArrayList();
	static ObservableList<String> model = FXCollections.observableArrayList();
	static ObservableList<String> color = FXCollections.observableArrayList();
	static ObservableList<Integer> price = FXCollections.observableArrayList();
	static ObservableList<String> outClient = FXCollections.observableArrayList();
	static ObservableList<String> outCompany = FXCollections.observableArrayList();
	static ObservableList<String> outModel = FXCollections.observableArrayList();
	static ObservableList<String> outColor = FXCollections.observableArrayList();
	static ObservableList<String> outSerial = FXCollections.observableArrayList();
	static ObservableList<Integer> outPrice = FXCollections.observableArrayList();
	static ObservableList<Integer> quantity = FXCollections.observableArrayList();

	public static ArrayList<RegPhone> dbArrayList = new ArrayList<RegPhone>();
	public static ArrayList<Phone> regArrayList = new ArrayList<Phone>();
	public static ArrayList<Client> clientArrayList = new ArrayList<Client>();
	public static ArrayList<OutPhone> outPhoneArrayList = new ArrayList<OutPhone>();
	public static ArrayList<Stock> stockArrayList = new ArrayList<Stock>();

	// ��� ���̺� ������ ����
	public static int makeStockData(Stock stock) {
		// 1.1 �����ͺ��̽� ��� ���̺� �Է��ϴ� ������
		StringBuffer insertClient = new StringBuffer();
		insertClient.append("insert into stockdb ");
		insertClient.append("(company,model,color,quantity) ");
		insertClient.append("values ");
		insertClient.append("(?,?,?,?) ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClient.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, stock.getCompany());
			psmt.setString(2, stock.getModel());
			psmt.setString(3, stock.getColor());
			psmt.setInt(4, stock.getQuantity());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// �ŷ�ó ���̺� ������ ����
	public static int makeClientData(Client client) {
		// 1.1 �����ͺ��̽� �ŷ�ó ���̺� �Է��ϴ� ������
		StringBuffer insertClient = new StringBuffer();
		insertClient.append("insert into clientdb ");
		insertClient.append("(account,ceo,address,phone,date) ");
		insertClient.append("values ");
		insertClient.append("(?,?,?,?,?) ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClient.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, client.getAccount());
			psmt.setString(2, client.getCeo());
			psmt.setString(3, client.getAddress());
			psmt.setString(4, client.getPhone());
			psmt.setString(5, client.getDate());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// �𵨰��� ���̺� ������ ����
	public static int insertPhoneData(RegPhone phone) {
		// 1.1 �����ͺ��̽� �𵨰��� ���̺� �Է��ϴ� ������
		StringBuffer insertPhone = new StringBuffer();
		insertPhone.append("insert into regphonedb ");
		insertPhone.append("(regCompany,regModel,regColor,regIndate,regSerial,regPrice,regMemo,regMplusC) ");
		insertPhone.append("values ");
		insertPhone.append("(?,?,?,?,?,?,?,?) ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertPhone.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, phone.getRegCompany());
			psmt.setString(2, phone.getRegModel());
			psmt.setString(3, phone.getRegColor());
			psmt.setString(4, phone.getRegIndate());
			psmt.setString(5, phone.getRegSerial());
			psmt.setInt(6, phone.getRegPrice());
			psmt.setString(7, phone.getRegMemo());
			psmt.setString(8, phone.getRegModel() + phone.getRegColor());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// ��� ���̺� ������ ����
	public static int outPhoneData(OutPhone phone) {
		// 1.1 �����ͺ��̽� �л� ���̺� �Է��ϴ� ������
		StringBuffer insertPhone = new StringBuffer();
		insertPhone.append("insert into outphonedb ");
		insertPhone.append("(client,company,model,serial,color,outdate,price,memo) ");
		insertPhone.append("values ");
		insertPhone.append("(?,?,?,?,?,?,?,?) ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertPhone.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, phone.getClient());
			psmt.setString(2, phone.getCompany());
			psmt.setString(3, phone.getModel());
			psmt.setString(4, phone.getSerial());
			psmt.setString(5, phone.getColor());
			psmt.setString(6, phone.getOutdate());
			psmt.setInt(7, phone.getPrice());
			psmt.setString(8, phone.getMemo());
			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// �ŷ�ó ���̺� ������ ����
	public static int insertClientData(Client client) {
		// 1.1 �����ͺ��̽� �ŷ�ó ���̺� �Է��ϴ� ������
		StringBuffer insertClient = new StringBuffer();
		insertClient.append("insert into clientdb ");
		insertClient.append("(account,ceo,address,phone,date) ");
		insertClient.append("values ");
		insertClient.append("(?,?,?,?,?) ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClient.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, client.getAccount());
			psmt.setString(2, client.getCeo());
			psmt.setString(3, client.getAddress());
			psmt.setString(4, client.getPhone());
			psmt.setString(5, client.getDate());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// ��� ���̺� ������ ����
	public static int insertOutPhoneData(OutPhone outPhone) {
		// 1.1 �����ͺ��̽� �л� ���̺� �Է��ϴ� ������
		StringBuffer insertClient = new StringBuffer();
		insertClient.append("insert into outphonedb ");
		insertClient.append("(client,company,model,serial,color,outdate,price,memo) ");
		insertClient.append("values ");
		insertClient.append("(?,?,?,?,?,?,?,?) ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClient.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, outPhone.getClient());
			psmt.setString(2, outPhone.getCompany());
			psmt.setString(3, outPhone.getModel());
			psmt.setString(4, outPhone.getSerial());
			psmt.setString(5, outPhone.getColor());
			psmt.setString(6, outPhone.getOutdate());
			psmt.setInt(7, outPhone.getPrice());
			psmt.setString(8, outPhone.getMemo());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// 1. �޴����� ����ϴ� �Լ�
	public static int makePhoneData(Phone phone) {
		// 1.1 �����ͺ��̽� �л� ���̺� �Է��ϴ� ������
		StringBuffer insertPhone = new StringBuffer();
		insertPhone.append("insert into modeldb ");
		insertPhone.append("(company,model,color,price,indate,image,pk) ");
		insertPhone.append("values ");
		insertPhone.append("(?,?,?,?,?,?,?) ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertPhone.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, phone.getCompany());
			psmt.setString(2, phone.getModel());
			psmt.setString(3, phone.getColor());
			psmt.setInt(4, phone.getPrice());
			psmt.setString(5, phone.getIndate());
			psmt.setString(6, phone.getImage());
			psmt.setString(7, phone.getModel() + phone.getColor());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// 2. ���̺��� ��ü������ ��� �������� �Լ�
	public static ArrayList<RegPhone> getPhoneTotalData() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectPhone = "select * from regphonedb";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			while (rs.next()) {
				RegPhone phone = new RegPhone(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5), 
						rs.getInt(6), 
						rs.getString(7));

				dbArrayList.add(phone);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return dbArrayList;
	}

	// ��� ���̺��� ��ü ������ �������� �Լ�
	public static ArrayList<OutPhone> getOutPhoneTotalData() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectPhone = "select * from outphonedb";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			while (rs.next()) {
				OutPhone phone = new OutPhone(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5), 
						rs.getString(6), 
						rs.getInt(7), 
						rs.getString(8));

				outPhoneArrayList.add(phone);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return outPhoneArrayList;
	}

	public static ArrayList<Stock> getTotalStockData() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectStock = "select * from stockdb";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectStock);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			while (rs.next()) {
				Stock stock = new Stock(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4));

				stockArrayList.add(stock);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return stockArrayList;
	}

	// 3. ���̺�信�� ������ ���ڵ带 �����ͺ��̽����� �����ϴ� �Լ�
	public static int deleteRegModelData(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 �����ϴ� ������
		String deleteRegSerial = "delete from regphonedb where regserial = '" + str + "' ";
		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteRegSerial);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("delete ���� : delete ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("delete ���� : delete ������ ����");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	public static int truncateStock() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 �����ϴ� ������
		String deleteRegSerial = "truncate table stockdb ";
		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteRegSerial);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			count = psmt.executeUpdate();

		} catch (SQLException e) {
			MainController.callAlert("delete ���� : delete ������ ����");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// 4. ���̺�信�� ������ ���ڵ带 �����ͺ��̽� ���̺� �����ϴ� �Լ�
	public static int updatePhoneModelData(Phone phone) {
		// 1.1 �����ͺ��̽� �л� ���̺��� �����ϴ� ������
		StringBuffer updateStudent = new StringBuffer();
		updateStudent.append("update modeldb set ");
		updateStudent.append("company=?,model=?,color=?,price=?,indate=?,image=? ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateStudent.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.

			psmt.setString(1, phone.getCompany());
			psmt.setString(2, phone.getModel());
			psmt.setString(3, phone.getColor());
			psmt.setInt(4, phone.getPrice());
			psmt.setString(5, phone.getIndate());
			psmt.setString(6, phone.getImage());
			// psmt.setString(7, phone.getModel()+phone.getColor());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("�������� : �����ͺ��̽� ��������");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}

		return count;
	}

	// DB�� ����� ������ ��� �� ���̺���� ���� �÷����� ��������
	public static int DBtoOutTableViewPrice(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		int regPrice = 0;
		String selectRegPrice = "select price from modeldb where pk='" + str + "' group by price ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectRegPrice);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return 0;
			}
			outPrice.clear();
			while (rs.next()) {
				regPrice = rs.getInt("price");
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return regPrice;

	}

	// DB�� ����� �ŷ�ó �̸��� ��� ���� �ŷ�ó �޺��ڽ��� ��������
	public static ObservableList<String> DBtoOutCmbAccount() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectAccount = "select account from clientdb group by account ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			outClient.clear();
			while (rs.next()) {
				String cmbClient = rs.getString("account");

				outClient.add(cmbClient);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return outClient;
	}

	// DB�� ����� �ŷ�ó �̸��� ��� ���� ������ �޺��ڽ��� ��������
	public static ObservableList<String> DBtoOutCmbCompany() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectAccount = "select regcompany from regphonedb group by regcompany ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			outCompany.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("regcompany");

				outCompany.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return outCompany;
	}

	// DB�� ����� �ŷ�ó �̸��� ��� ���� �ŷ�ó �޺��ڽ��� ��������
	public static ObservableList<String> DBtoOutCmbModel(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectAccount = "select regmodel from regphonedb where regcompany='" + str + "' group by regmodel ";
		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			outModel.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("regmodel");

				outModel.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return outModel;
	}

	public static ObservableList<String> DBtoOutCmbColor(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectAccount = "select regcolor from regphonedb where regmodel='" + str + "' group by regcolor ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			outColor.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("regcolor");

				outColor.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return outColor;
	}

	// DB�� ����� �ŷ�ó �̸��� ��� ���� �ŷ�ó �޺��ڽ��� ��������
	public static ObservableList<String> DBtoOutCmbSerial(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectAccount = "select regserial from regphonedb where regMplusC='" + str + "' group by regserial  ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			outSerial.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("regserial");

				outSerial.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return outSerial;
	}

	public static ObservableList<String> DBtoCmbCompany() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectPhone = "select company from modeldb group by company ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			company.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("company");

				company.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return company;
	}

	// DB�� ����� �����翡 �ش��ϴ� �𵨸� ��������
	public static ObservableList<String> DBtoCmbModel(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectPhone = "select model from modeldb where company='" + str + "' group by model";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			model.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("model");

				model.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return model;
	}

	public static ObservableList<String> DBtoCmbColor(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectPhone = "select color from modeldb where model='" + str + "' group by color ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			color.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("color");

				color.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return color;
	}

	public static int DBtoCmbPrice(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		int cmbCompany = 0;
		String selectPhone = "select price from modeldb where model='" + str + "' group by price ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return 0;
			}
			price.clear();
			while (rs.next()) {
				cmbCompany = rs.getInt("price");
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return cmbCompany;

	}

	// regphonedb�� ����� regMplusC�� �ش��ϴ� ������ ��������
	public static int DBtoStockQuantity(String str) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		int stockQuantity = 0;
		String selectPhone = "select count(*) from regphonedb where regMplusC = '" + str + "' ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return 0;
			}
			quantity.clear();
			while (rs.next()) {
				stockQuantity = rs.getInt("count(*)");
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return stockQuantity;

	}

	public static ArrayList<Stock> DBtoStockData() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		stockArrayList.clear();
		String selectStockData = "select regcompany,regmodel,regcolor,count(regmplusc) from regphonedb group by regmplusc ";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectStockData);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			// quantity.clear();
			while (rs.next()) {
				Stock stock = new Stock(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4));
				stockArrayList.add(stock);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return stockArrayList;

	}

	public static ArrayList<Phone> getRegPhoneTotalData() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectPhone = "select * from modeldb";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			while (rs.next()) {
				Phone phone = new Phone(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4),
						rs.getString(5), 
						rs.getString(6), 
						rs.getString(7));

				regArrayList.add(phone);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return regArrayList;
	}

	public static ArrayList<Client> getClientTotalData() {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 ��� �������� ������
		String selectPhone = "select * from clientdb";

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ����");
				return null;
			}
			while (rs.next()) {
				Client client = new Client(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5));

				clientArrayList.add(client);
			}

		} catch (SQLException e) {
			MainController.callAlert("���Խ��� : �����ͺ��̽� ���Խ���");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return clientArrayList;
	}

	public static int updateClientData(Client client) {
		// 1.1 �����ͺ��̽� �л� ���̺��� �����ϴ� ������
		StringBuffer updateClient = new StringBuffer();
		updateClient.append("update clientdb set ");
		updateClient.append("ceo=?,address=?,phone=?,date=? where account=? ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateClient.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.

			psmt.setString(1, client.getCeo());
			psmt.setString(2, client.getAddress());
			psmt.setString(3, client.getPhone());
			psmt.setString(4, client.getDate());
			psmt.setString(5, client.getAccount());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("�������� : �����ͺ��̽� ��������");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}

		return count;
	}

	public static int updateModelData(Phone phone) {
		// 1.1 �����ͺ��̽� �л� ���̺��� �����ϴ� ������
		StringBuffer updateClient = new StringBuffer();
		updateClient.append("update modeldb set ");
		updateClient.append("company=?,model=?,color=?,price=? ,indate=? ,image=?,pk=? where pk=? ");

		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		int count = 0;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateClient.toString());
			// 1.4 �������� ���� �����͸� �����Ѵ�.

			psmt.setString(1, phone.getCompany());
			psmt.setString(2, phone.getModel());
			psmt.setString(3, phone.getColor());
			psmt.setInt(4, phone.getPrice());
			psmt.setString(5, phone.getIndate());
			psmt.setString(6, phone.getImage());
			psmt.setString(7, phone.getModel() + phone.getColor());
			psmt.setString(8, phone.getModel() + phone.getColor());

			// 1.5 ���� �����͸� ������ �������� �����϶�.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("�������� : �����ͺ��̽� ��������");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}

		return count;
	}

	// 3. ���̺�信�� ������ ���ڵ带 �����ͺ��̽����� �����ϴ� �Լ�
	public static int deleteClientData(String account) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 �����ϴ� ������
		String deleteClient = "delete from clientdb where account=? ";
		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteClient);
			psmt.setString(1, account);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("delete ���� : delete ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("delete ���� : delete ������ ����");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

	// 3. ���̺�信�� ������ ���ڵ带 �����ͺ��̽����� �����ϴ� �Լ�
	public static int deleteModelData(String account) {
		// 1.1 �����ͺ��̽� �л� ���̺� �ִ� ���ڵ带 �����ϴ� ������
		String deleteModel = "delete from modeldb where pk=? ";
		// 2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		PreparedStatement psmt = null;
		// 1.4 �������� �����ϰ��� �����;� �� ���ڵ带 ����ִ� ���ڱ� ��ü
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteModel);
			psmt.setString(1, account);

			// 1.5 ���� �����͸� ������ �������� �����Ѵ�.
			// executeQuery() �������� �����ؼ� ����� ������ �� ����ϴ� ������
			// executeUpdate() �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������

			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("delete ���� : delete ������ ����");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("delete ���� : delete ������ ����");
		} finally {
			// 1.6 �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ڿ��ݱ� ���� : ���� �ڿ��ݱ� ����");
			}
		}
		return count;
	}

}
