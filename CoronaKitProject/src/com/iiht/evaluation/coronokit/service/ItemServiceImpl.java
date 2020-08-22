package com.iiht.evaluation.coronokit.service;

import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.Exception.CpkException;
import com.iiht.evaluation.coronokit.dao.ItemDao;
import com.iiht.evaluation.coronokit.dao.ItemDaoJdbcImpl;
import com.iiht.evaluation.coronokit.model.Item;
import com.iiht.evaluation.coronokit.model.KitDetail;



public class ItemServiceImpl implements ItemService{
	
	ItemDao itemDao;
	public ItemServiceImpl() {
		itemDao = new ItemDaoJdbcImpl();
	}
	
	private boolean isItemnoValid(Integer itemNo) {
		return itemNo > 0;
		
	}
	
	
	private boolean isItemNameValid(String  itemName) {
		return itemName!=null && (itemName.length()<=20);
		
	}
	
	private boolean isItemDescValid(String itemDesc) {
		return itemDesc!=null && (itemDesc.length()<=50);
		
	}
	
	private boolean isItemPriceValid(Double itemPrice ) {
		return itemPrice >= 0;
		
	}
	
	
	private boolean isvalidItem(Item item) {
		List<String> errMsg = new ArrayList<>();
		
		boolean isValid = true;
		if (!isItemnoValid(item.getItemNo())){
			isValid=false;
			errMsg.add("Item number cannot be null or negative or zero");
		}
		
		
		if (!isItemNameValid(item.getItemName())){
			isValid=false;
			errMsg.add("Item name cannot be null, name cannot be more than 20 characters in length");
		}
		
		
		if (!isItemDescValid(item.getItemDesc())){
			isValid=false;
			errMsg.add("Item decription cannot be null, cannot be more than 50 characters in length");
		}
		
		
		if (!isItemPriceValid(item.getItemPrice())){
			isValid=false;
			errMsg.add("Item price cannot be zero or negative");
		}
		return isValid;
		
		
	}
	
	

	@Override
	public void validateAndAdd(Item item) throws CpkException {
		if (item!=null) {
			if (isvalidItem(item)) {
				itemDao.add(item);
			}
		}
		
	}

	@Override
	public void validateAndEdit(Item item) throws CpkException {
		
		if (item!=null) {
			if (isvalidItem(item)) {
				itemDao.edit(item);
			}
		}
		
	}

	@Override
	public boolean deleteItem(int itemNo) throws CpkException {
		
		return itemDao.DeletebyId(itemNo);
	}

	@Override
	public List<Item> getTItems() throws CpkException {
		
		List<Item> items = itemDao.itemlist();
		
		return items;
	}

	@Override
	public List<String> itemCodes() throws CpkException {
		
		return itemDao.itemCodes();
	}

	@Override
	public List<Item> getItemById(int id) throws CpkException {
		// TODO Auto-generated method stub
		return itemDao.getItemById(id);
	}
	
	@Override
	public double getCartTotalAmount(List<KitDetail> kitDetails) throws CpkException
	{
		double totalAmount = 0.0;
		
		for (KitDetail kitDetail : kitDetails) {
			
			totalAmount =totalAmount+(kitDetail.getAmount()*kitDetail.getQuantity());
		}
		
		return totalAmount;
	}

}
