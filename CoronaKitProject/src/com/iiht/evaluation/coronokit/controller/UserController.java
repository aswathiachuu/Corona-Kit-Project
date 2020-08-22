package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.Exception.CpkException;
import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.Item;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.service.ItemService;
import com.iiht.evaluation.coronokit.service.ItemServiceImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;
	private ItemService itemService =new ItemServiceImpl(); 
	private int i = 1;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
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

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) throws CpkException {

		HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("User");
		String contact = (String) session.getAttribute("contact");
		String eamil = (String) session.getAttribute("eamil");
		
		List<KitDetail> kitDetails = (List<KitDetail>) session.getAttribute("kitDetails");
		
		request.setAttribute("User", user);
		request.setAttribute("contact", contact);
		request.setAttribute("eamil", eamil);
		double tamount = itemService.getCartTotalAmount(kitDetails);
		
		request.setAttribute("kitDetails", kitDetails);

		
		request.setAttribute("tamount", tamount);
		
		return "ordersummary.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, CpkException {
		
		String itemNumber = request.getParameter("itemNumber");
		String quantity = request.getParameter("quantity");
		HttpSession session = request.getSession();
		List<KitDetail> kitDetails = null;
		
		if(session.isNew())
		 {
		     kitDetails =new ArrayList<KitDetail>();
		 }
		else
		{
			kitDetails = (List<KitDetail>) session.getAttribute("kitDetails");
			if(kitDetails == null)
			{
				 kitDetails =new ArrayList<KitDetail>();
			}
		}
			
		
				List<Item> items =itemService.getItemById(Integer.valueOf(itemNumber));
				
		for (Item tItem : items) {
			
		 	KitDetail detail = new  KitDetail(i,tItem.getItemName(),Integer.valueOf(quantity),Double.valueOf(tItem.getItemPrice()));
		 	kitDetails.add(detail);
		 	i++;
		}
		
		
		
		List<Item> titems =itemService.getTItems();
		
		
		
		double tamount = itemService.getCartTotalAmount(kitDetails);
		session =request.getSession(true);
		String user = session.getAttribute("User").toString();
		
		
		request.setAttribute("itemList", titems);
		request.setAttribute("User", user);
		
		
		session.setAttribute("kitDetails", kitDetails);
		request.setAttribute("noofcart", kitDetails.size());
		
		request.setAttribute("tamount", tamount);
		
		return "showproductstoadd.jsp";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) throws CpkException {
		
		HttpSession session =request.getSession();
		
		String user = (String) session.getAttribute("User");
		String contact = (String) session.getAttribute("contact");
		String email = (String) session.getAttribute("email");
		
		List<KitDetail> kitDetails = (List<KitDetail>) session.getAttribute("kitDetails");
		
		
		double tamount = itemService.getCartTotalAmount(kitDetails);
		
		request.setAttribute("kitDetails", kitDetails);

		request.setAttribute("User", user);
		request.setAttribute("contact", contact);
		request.setAttribute("email", email );
		request.setAttribute("tamount", tamount);
		
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) throws CpkException {
		// TODO Auto-generated method stub
		
      HttpSession session = request.getSession();
      String productId = request.getParameter("productId");
		
		List<KitDetail> kitDetails = (List<KitDetail>) session.getAttribute("kitDetails");
		Iterator<KitDetail> iterator =kitDetails.iterator();
		while (iterator.hasNext()) {
			KitDetail kitDetail = (KitDetail) iterator.next();
			
			if(kitDetail.getProductId() == Integer.valueOf(productId))
			{
				iterator.remove();
			}
			
		}
				
		double tamount = itemService.getCartTotalAmount(kitDetails);
		
		request.setAttribute("tamount", tamount);
		request.setAttribute("kitDetails", kitDetails);
		request.setAttribute("login", 1);
		return "ordersummary.jsp";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws CpkException {
	
		
		List<Item> titems =itemService.getTItems();
		
		
		HttpSession session =request.getSession(true);
		request.setAttribute("login", 1);
		String user = session.getAttribute("User").toString();
		
		
		request.setAttribute("itemList", titems);
		request.setAttribute("User", user);
		request.setAttribute("noofcart", 0);
		request.setAttribute("tamount", 0);
		return "showproductstoadd.jsp";				
		
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		
		String user = request.getParameter("patients");
		String contact = request.getParameter("contact");
		String eamil = request.getParameter("email");
		
		HttpSession  session = request.getSession(true);		
		session.setAttribute("User",user);
		session.setAttribute("contact",contact);
		session.setAttribute("eamil",eamil);
		request.setAttribute("login", 1);
		return "user?action=showproducts";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) throws CpkException {
		
		return "newuser.jsp";
	}
}