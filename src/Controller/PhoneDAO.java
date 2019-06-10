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

	// 재고 테이블에 데이터 삽입
	public static int makeStockData(Stock stock) {
		// 1.1 데이터베이스 재고 테이블에 입력하는 쿼리문
		StringBuffer insertClient = new StringBuffer();
		insertClient.append("insert into stockdb ");
		insertClient.append("(company,model,color,quantity) ");
		insertClient.append("values ");
		insertClient.append("(?,?,?,?) ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClient.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.
			psmt.setString(1, stock.getCompany());
			psmt.setString(2, stock.getModel());
			psmt.setString(3, stock.getColor());
			psmt.setInt(4, stock.getQuantity());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 거래처 테이블에 데이터 삽입
	public static int makeClientData(Client client) {
		// 1.1 데이터베이스 거래처 테이블에 입력하는 쿼리문
		StringBuffer insertClient = new StringBuffer();
		insertClient.append("insert into clientdb ");
		insertClient.append("(account,ceo,address,phone,date) ");
		insertClient.append("values ");
		insertClient.append("(?,?,?,?,?) ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClient.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.
			psmt.setString(1, client.getAccount());
			psmt.setString(2, client.getCeo());
			psmt.setString(3, client.getAddress());
			psmt.setString(4, client.getPhone());
			psmt.setString(5, client.getDate());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 모델관리 테이블에 데이터 삽입
	public static int insertPhoneData(RegPhone phone) {
		// 1.1 데이터베이스 모델관리 테이블에 입력하는 쿼리문
		StringBuffer insertPhone = new StringBuffer();
		insertPhone.append("insert into regphonedb ");
		insertPhone.append("(regCompany,regModel,regColor,regIndate,regSerial,regPrice,regMemo,regMplusC) ");
		insertPhone.append("values ");
		insertPhone.append("(?,?,?,?,?,?,?,?) ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertPhone.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.
			psmt.setString(1, phone.getRegCompany());
			psmt.setString(2, phone.getRegModel());
			psmt.setString(3, phone.getRegColor());
			psmt.setString(4, phone.getRegIndate());
			psmt.setString(5, phone.getRegSerial());
			psmt.setInt(6, phone.getRegPrice());
			psmt.setString(7, phone.getRegMemo());
			psmt.setString(8, phone.getRegModel() + phone.getRegColor());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 출고 테이블에 데이터 삽입
	public static int outPhoneData(OutPhone phone) {
		// 1.1 데이터베이스 학생 테이블에 입력하는 쿼리문
		StringBuffer insertPhone = new StringBuffer();
		insertPhone.append("insert into outphonedb ");
		insertPhone.append("(client,company,model,serial,color,outdate,price,memo) ");
		insertPhone.append("values ");
		insertPhone.append("(?,?,?,?,?,?,?,?) ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertPhone.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.
			psmt.setString(1, phone.getClient());
			psmt.setString(2, phone.getCompany());
			psmt.setString(3, phone.getModel());
			psmt.setString(4, phone.getSerial());
			psmt.setString(5, phone.getColor());
			psmt.setString(6, phone.getOutdate());
			psmt.setInt(7, phone.getPrice());
			psmt.setString(8, phone.getMemo());
			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 거래처 테이블에 데이터 삽입
	public static int insertClientData(Client client) {
		// 1.1 데이터베이스 거래처 테이블에 입력하는 쿼리문
		StringBuffer insertClient = new StringBuffer();
		insertClient.append("insert into clientdb ");
		insertClient.append("(account,ceo,address,phone,date) ");
		insertClient.append("values ");
		insertClient.append("(?,?,?,?,?) ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClient.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.
			psmt.setString(1, client.getAccount());
			psmt.setString(2, client.getCeo());
			psmt.setString(3, client.getAddress());
			psmt.setString(4, client.getPhone());
			psmt.setString(5, client.getDate());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 출고 테이블에 데이터 삽입
	public static int insertOutPhoneData(OutPhone outPhone) {
		// 1.1 데이터베이스 학생 테이블에 입력하는 쿼리문
		StringBuffer insertClient = new StringBuffer();
		insertClient.append("insert into outphonedb ");
		insertClient.append("(client,company,model,serial,color,outdate,price,memo) ");
		insertClient.append("values ");
		insertClient.append("(?,?,?,?,?,?,?,?) ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClient.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.
			psmt.setString(1, outPhone.getClient());
			psmt.setString(2, outPhone.getCompany());
			psmt.setString(3, outPhone.getModel());
			psmt.setString(4, outPhone.getSerial());
			psmt.setString(5, outPhone.getColor());
			psmt.setString(6, outPhone.getOutdate());
			psmt.setInt(7, outPhone.getPrice());
			psmt.setString(8, outPhone.getMemo());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 1. 휴대폰모델 등록하는 함수
	public static int makePhoneData(Phone phone) {
		// 1.1 데이터베이스 학생 테이블에 입력하는 쿼리문
		StringBuffer insertPhone = new StringBuffer();
		insertPhone.append("insert into modeldb ");
		insertPhone.append("(company,model,color,price,indate,image,pk) ");
		insertPhone.append("values ");
		insertPhone.append("(?,?,?,?,?,?,?) ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertPhone.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.
			psmt.setString(1, phone.getCompany());
			psmt.setString(2, phone.getModel());
			psmt.setString(3, phone.getColor());
			psmt.setInt(4, phone.getPrice());
			psmt.setString(5, phone.getIndate());
			psmt.setString(6, phone.getImage());
			psmt.setString(7, phone.getModel() + phone.getColor());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 2. 테이블에서 전체내용을 모두 가져오는 함수
	public static ArrayList<RegPhone> getPhoneTotalData() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectPhone = "select * from regphonedb";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
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
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return dbArrayList;
	}

	// 출고 테이블에서 전체 내용을 가져오는 함수
	public static ArrayList<OutPhone> getOutPhoneTotalData() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectPhone = "select * from outphonedb";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
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
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return outPhoneArrayList;
	}

	public static ArrayList<Stock> getTotalStockData() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectStock = "select * from stockdb";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectStock);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
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
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return stockArrayList;
	}

	// 3. 테이블뷰에서 선택한 레코드를 데이터베이스에서 삭제하는 함수
	public static int deleteRegModelData(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 삭제하는 쿼리문
		String deleteRegSerial = "delete from regphonedb where regserial = '" + str + "' ";
		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteRegSerial);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("delete 실패 : delete 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("delete 실패 : delete 쿼리문 실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	public static int truncateStock() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 삭제하는 쿼리문
		String deleteRegSerial = "truncate table stockdb ";
		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteRegSerial);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			count = psmt.executeUpdate();

		} catch (SQLException e) {
			MainController.callAlert("delete 실패 : delete 쿼리문 실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 4. 테이블뷰에서 수정한 레코드를 데이터베이스 테이블에 수정하는 함수
	public static int updatePhoneModelData(Phone phone) {
		// 1.1 데이터베이스 학생 테이블을 수정하는 쿼리문
		StringBuffer updateStudent = new StringBuffer();
		updateStudent.append("update modeldb set ");
		updateStudent.append("company=?,model=?,color=?,price=?,indate=?,image=? ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateStudent.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.

			psmt.setString(1, phone.getCompany());
			psmt.setString(2, phone.getModel());
			psmt.setString(3, phone.getColor());
			psmt.setInt(4, phone.getPrice());
			psmt.setString(5, phone.getIndate());
			psmt.setString(6, phone.getImage());
			// psmt.setString(7, phone.getModel()+phone.getColor());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("수정 쿼리실패 : 수정 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("수정실패 : 데이터베이스 수정실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}

		return count;
	}

	// DB에 저장된 가격을 출고 탭 테이블뷰의 가격 컬럼으로 가져오기
	public static int DBtoOutTableViewPrice(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		int regPrice = 0;
		String selectRegPrice = "select price from modeldb where pk='" + str + "' group by price ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectRegPrice);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return 0;
			}
			outPrice.clear();
			while (rs.next()) {
				regPrice = rs.getInt("price");
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return regPrice;

	}

	// DB에 저장된 거래처 이름을 출고 탭의 거래처 콤보박스로 가져오기
	public static ObservableList<String> DBtoOutCmbAccount() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectAccount = "select account from clientdb group by account ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return null;
			}
			outClient.clear();
			while (rs.next()) {
				String cmbClient = rs.getString("account");

				outClient.add(cmbClient);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return outClient;
	}

	// DB에 저장된 거래처 이름을 출고 탭의 제조사 콤보박스로 가져오기
	public static ObservableList<String> DBtoOutCmbCompany() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectAccount = "select regcompany from regphonedb group by regcompany ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return null;
			}
			outCompany.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("regcompany");

				outCompany.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return outCompany;
	}

	// DB에 저장된 거래처 이름을 출고 탭의 거래처 콤보박스로 가져오기
	public static ObservableList<String> DBtoOutCmbModel(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectAccount = "select regmodel from regphonedb where regcompany='" + str + "' group by regmodel ";
		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return null;
			}
			outModel.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("regmodel");

				outModel.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return outModel;
	}

	public static ObservableList<String> DBtoOutCmbColor(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectAccount = "select regcolor from regphonedb where regmodel='" + str + "' group by regcolor ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return null;
			}
			outColor.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("regcolor");

				outColor.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return outColor;
	}

	// DB에 저장된 거래처 이름을 출고 탭의 거래처 콤보박스로 가져오기
	public static ObservableList<String> DBtoOutCmbSerial(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectAccount = "select regserial from regphonedb where regMplusC='" + str + "' group by regserial  ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectAccount);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return null;
			}
			outSerial.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("regserial");

				outSerial.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return outSerial;
	}

	public static ObservableList<String> DBtoCmbCompany() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectPhone = "select company from modeldb group by company ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return null;
			}
			company.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("company");

				company.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return company;
	}

	// DB에 저장된 제조사에 해당하는 모델명만 가져오기
	public static ObservableList<String> DBtoCmbModel(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectPhone = "select model from modeldb where company='" + str + "' group by model";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return null;
			}
			model.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("model");

				model.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return model;
	}

	public static ObservableList<String> DBtoCmbColor(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectPhone = "select color from modeldb where model='" + str + "' group by color ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return null;
			}
			color.clear();
			while (rs.next()) {
				String cmbCompany = rs.getString("color");

				color.add(cmbCompany);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return color;
	}

	public static int DBtoCmbPrice(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		int cmbCompany = 0;
		String selectPhone = "select price from modeldb where model='" + str + "' group by price ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return 0;
			}
			price.clear();
			while (rs.next()) {
				cmbCompany = rs.getInt("price");
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return cmbCompany;

	}

	// regphonedb에 저장된 regMplusC에 해당하는 수량을 가져오기
	public static int DBtoStockQuantity(String str) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		int stockQuantity = 0;
		String selectPhone = "select count(*) from regphonedb where regMplusC = '" + str + "' ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
				return 0;
			}
			quantity.clear();
			while (rs.next()) {
				stockQuantity = rs.getInt("count(*)");
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return stockQuantity;

	}

	public static ArrayList<Stock> DBtoStockData() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		stockArrayList.clear();
		String selectStockData = "select regcompany,regmodel,regcolor,count(regmplusc) from regphonedb group by regmplusc ";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectStockData);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
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
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return stockArrayList;

	}

	public static ArrayList<Phone> getRegPhoneTotalData() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectPhone = "select * from modeldb";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
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
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return regArrayList;
	}

	public static ArrayList<Client> getClientTotalData() {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 모두 가져오는 쿼리문
		String selectPhone = "select * from clientdb";

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPhone);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패");
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
			MainController.callAlert("삽입실패 : 데이터베이스 삽입실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return clientArrayList;
	}

	public static int updateClientData(Client client) {
		// 1.1 데이터베이스 학생 테이블을 수정하는 쿼리문
		StringBuffer updateClient = new StringBuffer();
		updateClient.append("update clientdb set ");
		updateClient.append("ceo=?,address=?,phone=?,date=? where account=? ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateClient.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.

			psmt.setString(1, client.getCeo());
			psmt.setString(2, client.getAddress());
			psmt.setString(3, client.getPhone());
			psmt.setString(4, client.getDate());
			psmt.setString(5, client.getAccount());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("수정 쿼리실패 : 수정 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("수정실패 : 데이터베이스 수정실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}

		return count;
	}

	public static int updateModelData(Phone phone) {
		// 1.1 데이터베이스 학생 테이블을 수정하는 쿼리문
		StringBuffer updateClient = new StringBuffer();
		updateClient.append("update modeldb set ");
		updateClient.append("company=?,model=?,color=?,price=? ,indate=? ,image=?,pk=? where pk=? ");

		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		int count = 0;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateClient.toString());
			// 1.4 쿼리문에 실제 데이터를 연결한다.

			psmt.setString(1, phone.getCompany());
			psmt.setString(2, phone.getModel());
			psmt.setString(3, phone.getColor());
			psmt.setInt(4, phone.getPrice());
			psmt.setString(5, phone.getIndate());
			psmt.setString(6, phone.getImage());
			psmt.setString(7, phone.getModel() + phone.getColor());
			psmt.setString(8, phone.getModel() + phone.getColor());

			// 1.5 실제 데이터를 연결한 쿼리문을 실행하라.
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("수정 쿼리실패 : 수정 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("수정실패 : 데이터베이스 수정실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}

		return count;
	}

	// 3. 테이블뷰에서 선택한 레코드를 데이터베이스에서 삭제하는 함수
	public static int deleteClientData(String account) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 삭제하는 쿼리문
		String deleteClient = "delete from clientdb where account=? ";
		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteClient);
			psmt.setString(1, account);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("delete 실패 : delete 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("delete 실패 : delete 쿼리문 실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

	// 3. 테이블뷰에서 선택한 레코드를 데이터베이스에서 삭제하는 함수
	public static int deleteModelData(String account) {
		// 1.1 데이터베이스 학생 테이블에 있는 레코드를 삭제하는 쿼리문
		String deleteModel = "delete from modeldb where pk=? ";
		// 2. 데이터베이스 Connection을 가져와야 한다.
		Connection con = null;
		// 1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
		PreparedStatement psmt = null;
		// 1.4 쿼리문을 실행하고나서 가져와야 할 레코드를 담고있는 보자기 객체
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteModel);
			psmt.setString(1, account);

			// 1.5 실제 데이터를 연결한 쿼리문을 실행한다.
			// executeQuery() 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
			// executeUpdate() 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문

			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("delete 실패 : delete 쿼리문 실패");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("delete 실패 : delete 쿼리문 실패");
		} finally {
			// 1.6 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("자원닫기 실패 : 최종 자원닫기 실패");
			}
		}
		return count;
	}

}
