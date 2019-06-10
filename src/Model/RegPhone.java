package Model;

import java.sql.Date;

public class RegPhone {
	private String regCompany;
	private String regModel;
	private String regColor;
	private String regIndate;
	private String regSerial;
	private int regPrice;
	private String regMemo;
	public RegPhone(String regCompany, String regModel, String regColor, String regIndate, String regSerial,
			int regPrice, String regMemo) {
		super();
		this.regCompany = regCompany;
		this.regModel = regModel;
		this.regColor = regColor;
		this.regIndate = regIndate;
		this.regSerial = regSerial;
		this.regPrice = regPrice;
		this.regMemo = regMemo;
	}
	public String getRegCompany() {
		return regCompany;
	}
	public void setRegCompany(String regCompany) {
		this.regCompany = regCompany;
	}
	public String getRegModel() {
		return regModel;
	}
	public void setRegModel(String regModel) {
		this.regModel = regModel;
	}
	public String getRegColor() {
		return regColor;
	}
	public void setRegColor(String regColor) {
		this.regColor = regColor;
	}
	public String getRegIndate() {
		return regIndate;
	}
	public void setRegIndate(String regIndate) {
		this.regIndate = regIndate;
	}
	public String getRegSerial() {
		return regSerial;
	}
	public void setRegSerial(String regSerial) {
		this.regSerial = regSerial;
	}
	public int getRegPrice() {
		return regPrice;
	}
	public void setRegPrice(int regPrice) {
		this.regPrice = regPrice;
	}
	public String getRegMemo() {
		return regMemo;
	}
	public void setRegMemo(String regMemo) {
		this.regMemo = regMemo;
	}

}
