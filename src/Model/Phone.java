package Model;

import java.sql.Date;

public class Phone {
	private String company;
	private String model;
	private String color;
	private int price;
	private String indate;
	private String image;
	private String pk;
	public Phone(String company, String model, String color, int price, String indate, String image, String pk) {
		super();
		this.company = company;
		this.model = model;
		this.color = color;
		this.price = price;
		this.indate = indate;
		this.image = image;
		this.pk = pk;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	
}
