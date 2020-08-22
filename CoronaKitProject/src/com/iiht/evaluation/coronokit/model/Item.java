package com.iiht.evaluation.coronokit.model;

public class Item {
	
			private Integer itemNo;
			private String itemName;
			private String itemDesc;
			private Double itemPrice;
			
			
	public Item() {
		
	}


	
 
	public Item(Integer itemNo, String itemName, String itemDesc, Double itemPrice) {
		super();
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemPrice = itemPrice;
	}




	public Double getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}


	public Integer getItemNo() {
		return itemNo;
	}


	


	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", itemName=" + itemName + ", itemDesc=" + itemDesc + ", itemPrice="
				+ itemPrice + "]";
	}




	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemDesc() {
		return itemDesc;
	}


	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
			
			
			
			

}
