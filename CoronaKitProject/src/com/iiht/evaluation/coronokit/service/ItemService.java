package com.iiht.evaluation.coronokit.service;

import java.util.List;

import com.iiht.evaluation.coronokit.Exception.CpkException;
import com.iiht.evaluation.coronokit.model.Item;
import com.iiht.evaluation.coronokit.model.KitDetail;


public interface ItemService {
	
	void validateAndAdd(Item tItem) throws CpkException;
	void validateAndEdit(Item tItem) throws CpkException;
	List<Item> getTItems()throws CpkException;
	List<String> itemCodes() throws CpkException;
	List<Item> getItemById(int id) throws CpkException; 
	boolean deleteItem(int itemNo) throws CpkException;
	double getCartTotalAmount(List<KitDetail> kitDetails) throws CpkException;
	

}
