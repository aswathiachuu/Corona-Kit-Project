package com.iiht.evaluation.coronokit.dao;


import java.util.List;

import com.iiht.evaluation.coronokit.Exception.CpkException;
import com.iiht.evaluation.coronokit.model.Item;


public interface ItemDao {
	void add (Item item) throws CpkException ;
	void edit (Item item) throws CpkException;
	List<Item> itemlist() throws CpkException;
	boolean DeletebyId(Integer itemno) throws CpkException;
	List<Item> getItemById(int id) throws CpkException; 
	List<String> itemCodes() throws CpkException;
	}


