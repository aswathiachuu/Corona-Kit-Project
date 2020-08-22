package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import com.iiht.evaluation.coronokit.Exception.CpkException;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;

import com.iiht.evaluation.coronokit.model.Item;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.service.ItemService;
import com.iiht.evaluation.coronokit.service.ItemServiceImpl; 

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	private ItemService itemService =new ItemServiceImpl(); 
	
	
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		String viewName = "";
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		   
		        HttpSession session = request.getSession();
		       if(session!=null)
		       {	   
		          session.invalidate();
		       }

		        request.setAttribute("login", 0);
		        
		return "index.jsp";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) throws CpkException {
		
		
		List<Item> titems =itemService.getTItems();
		
		
		request.setAttribute("itemList", titems);
		request.setAttribute("login", 1);
		return "listproducts.jsp";
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		
		String itemNo = request.getParameter("itemNo");
		String itemName = request.getParameter("itemName");
		String itemDesc = request.getParameter("itemDesc");
		String itemPrice = request.getParameter("itemPrice");
		
		Item item = new Item(Integer.valueOf(itemNo),itemName,itemDesc,Double.valueOf(itemPrice));
		try {
			itemService.validateAndEdit(item);
		} catch (CpkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("login", 1);
		return "admin?action=list";
		
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws CpkException {
		
		String operaction = request.getParameter("operaction");
		List<Item> items  =new ArrayList<Item>();
		List<String> itemCodes =itemService.itemCodes();
		request.setAttribute("itemCodes", itemCodes);
		request.setAttribute("login", 1);
		if(operaction.equals("deleteproduct"))
				{
					
					return "deleteproduct.jsp";
				}
		else
		{
				return "editproduct.jsp";
		}
		
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		
		
		String itemNo = request.getParameter("itemNo");
		try {
			itemService.deleteItem(Integer.valueOf(itemNo));
		} catch (CpkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("login", 1);
		return "admin?action=list";
		
		
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
		String itemNo = request.getParameter("itemNo");
		String itemName = request.getParameter("itemName");
		String itemDesc = request.getParameter("itemDesc");
		String itemPrice = request.getParameter("itemPrice");
		
		Item item = new Item(Integer.valueOf(itemNo),itemName,itemDesc,Double.valueOf(itemPrice));
		try {
			itemService.validateAndAdd(item);
		} catch (CpkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("login", 1);
		return "admin?action=list";
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("login", 1);
		
		return "newproduct.jsp";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String userName = request.getParameter("loginid");
		String password = request.getParameter("password");
		String screenName = null;
		ServletContext context = request.getServletContext();
		context.setAttribute("User", userName);
		HttpSession session =request.getSession(); 
		String sessionId = session.getId();
		
		if(userName.equals("admin") && password.equals("admin"))
		{
			session.setAttribute("User", userName);
			screenName = "admin?action=list";
		}
		else
		{
			screenName = "index.jsp";
		}
		
		request.setAttribute("login", 1);
		return screenName;
	}

	
}