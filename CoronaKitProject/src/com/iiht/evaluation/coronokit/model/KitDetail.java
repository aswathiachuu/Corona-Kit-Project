package com.iiht.evaluation.coronokit.model;

public class KitDetail {

	private String coronaKitName;
	private int productId;
	private int quantity;
	private double amount;
	
	public KitDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public KitDetail(int productId, String coronaKitName, int quantity, double amount) {
		
		this.productId = productId;
		this.coronaKitName = coronaKitName;
	    this.quantity = quantity;
		this.amount = amount;
	}
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCoronaKitName() {
		return coronaKitName;
	}

	public void setCoronaKitName(String coronaKitName) {
		this.coronaKitName = coronaKitName;
	}
	
	
}
