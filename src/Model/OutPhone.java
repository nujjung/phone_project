package Model;

import java.sql.Date;

public class OutPhone {
	private String client;
	private String company;
	private String model;
	private String serial;
	private String color;
	private String outdate;
	private int price;
	private String memo;

	public OutPhone(String client, String company, String model, String serial, String color, String outdate, int price,
			String memo) {
		super();
		this.client = client;
		this.company = company;
		this.model = model;
		this.serial = serial;
		this.color = color;
		this.outdate = outdate;
		this.price = price;
		this.memo = memo;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOutdate() {
		return outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
