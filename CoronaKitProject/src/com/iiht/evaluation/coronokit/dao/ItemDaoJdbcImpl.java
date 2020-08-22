package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;



import com.iiht.evaluation.coronokit.Exception.CpkException;
import com.iiht.evaluation.coronokit.model.Item;




public class ItemDaoJdbcImpl implements ItemDao{
	
	
	public static final String INS_IEM_QRY="INSERT INTO item_master(itemNo,itemName,itemDesc,itemPrice) Values(?,?,?,?)";
	public static final String EDT_IEM_QRY="UPDATE item_master SET itemName=?,itemDesc=?,itemPrice=? where itemNo =?";
	public static final String DEl_IEM_QRY="DELETE FROM item_master WHERE itemNo=?";
	public static final String SEL_IEM_QRY="Select itemNo, itemName, itemDesc, itemPrice FROM item_master ";
	public static final String SEL_IEM_NO="Select itemNo FROM item_master ";
	public static final String SEL_IEM_BY_ID="Select itemNo, itemName, itemDesc, itemPrice FROM item_master where itemNo = ? ";
	
			
	@Override
	public void add(Item tItem) throws CpkException {

		Connection con =null;
		PreparedStatement pst = null;
		
		try{ 
			con = ConnectionFactory.getConnection();
			 pst = con.prepareStatement(INS_IEM_QRY);
				pst.setInt(1, tItem.getItemNo());
				pst.setString(2,tItem.getItemName());
				pst.setString(3,tItem.getItemDesc());
				pst.setDouble(4, tItem.getItemPrice());
				pst.executeUpdate();
			} catch (SQLException | NamingException exp) {
				
				exp.printStackTrace();
			
				throw new CpkException("Adding the Item failed");

			}finally {
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pst!=null)
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		
	}

	@Override
	public void edit(Item tItem) throws CpkException {
		Connection con =null;
		PreparedStatement pst = null;
		
			try{
			
				con = ConnectionFactory.getConnection();
			    pst = con.prepareStatement(EDT_IEM_QRY);
				
				pst.setString(1,tItem.getItemName());
				pst.setString(2,tItem.getItemDesc());
				pst.setDouble(3, tItem.getItemPrice());
				pst.setInt(4, tItem.getItemNo());
				pst.executeUpdate();
			} catch (SQLException | NamingException exp) {
				
				exp.printStackTrace();
			
				throw new CpkException("editing the the Item failed");

			}
			finally {
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pst!=null)
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		
		

	}

	@Override
	public boolean DeletebyId(Integer itemno) throws CpkException{
		boolean isDeleted = false;
		Connection con =null;
		PreparedStatement pst = null;
		try
		{
			con = ConnectionFactory.getConnection();
		    pst = con.prepareStatement(DEl_IEM_QRY);
			pst.setInt(1,itemno);
			int rowsCount = pst.executeUpdate();
			isDeleted = rowsCount > 0;
		} catch (SQLException | NamingException exp) {
			
			exp.printStackTrace();
		
			throw new CpkException("Deleting the Item failed");

		}
		finally {
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pst!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		
		
		return isDeleted;
	}

	@Override
	public List<Item> itemlist() throws CpkException {
		
		Connection con =null;
		PreparedStatement pst = null;
		ResultSet   resultSet =null;
		List<Item> items =new ArrayList<>();
		try
		{
			con = ConnectionFactory.getConnection();
		    pst = con.prepareStatement(SEL_IEM_QRY);
			
		    resultSet = pst.executeQuery();
			
		    while(resultSet.next())
		    {
		    	Item item = new Item();
		    	item.setItemNo(resultSet.getInt("itemNo"));
		    	item.setItemName(resultSet.getString("itemName"));
		    	item.setItemDesc(resultSet.getString("itemDesc"));
		    	item.setItemPrice(resultSet.getDouble("itemPrice"));
		    	items.add(item);
		    	
		    }
		    
		    
		} catch (SQLException | NamingException exp) {
			
			exp.printStackTrace();
		
			throw new CpkException("Deleting the Item failed");

		}
		finally {
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pst!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		return items;
	}
	
	
	
	@Override
	public List<String> itemCodes() throws CpkException {
		
		Connection con =null;
		PreparedStatement pst = null;
		ResultSet   resultSet =null;
		List<String> itemCodes =new ArrayList<String>();
		try
		{
			con = ConnectionFactory.getConnection();
		    pst = con.prepareStatement(SEL_IEM_QRY);
			
		    resultSet = pst.executeQuery();
			
		    while(resultSet.next())
		    {
		    	String  itemNumber = String.valueOf(resultSet.getInt("itemNo"));
		    	itemCodes.add(itemNumber);
		    }
		    
		    
		} catch (SQLException | NamingException exp) {
			
			exp.printStackTrace();
		
			throw new CpkException("Deleting the Item failed");

		}
		finally {
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pst!=null)
			{
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return itemCodes;
	}
	
@Override
public List<Item> getItemById(int id) throws CpkException {
		
		Connection con =null;
		PreparedStatement pst = null;
		ResultSet   resultSet =null;
		List<Item> items =new ArrayList<Item>();
		try
		{
			con = ConnectionFactory.getConnection();
		    pst = con.prepareStatement(SEL_IEM_BY_ID);
		    pst.setInt(1, id);
			
		    resultSet = pst.executeQuery();
			
		    while(resultSet.next())
		    {
		    	Item item = new Item();
		    	item.setItemNo(resultSet.getInt("itemNo"));
		    	item.setItemName(resultSet.getString("itemName"));
		    	item.setItemDesc(resultSet.getString("itemDesc"));
		    	item.setItemPrice(resultSet.getDouble("itemPrice"));
		    	items.add(item);
		    }
		    
		    
		} catch (SQLException | NamingException exp) {
			
			exp.printStackTrace();
		
			throw new CpkException("Deleting the Item failed");

		}
		finally {
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pst!=null)
			{
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return items;
	}
}
