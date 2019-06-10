package Model;

import java.sql.Date;

public class Stock {
	private String company;
	private String model;
	private String color;
	private int quantity;
	
		public Stock(String company, String model, String color, int quantity) {
		super();
		this.company = company;
		this.model = model;
		this.color = color;
		this.quantity = quantity;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
