package Model;

import java.sql.Date;

public class Client {
	private String account;
	private String ceo;
	private String address;
	private String phone;
	private String date;

	public Client(String account, String ceo, String address, String phone, String date) {
		super();
		this.account = account;
		this.ceo = ceo;
		this.address = address;
		this.phone = phone;
		this.date = date;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
