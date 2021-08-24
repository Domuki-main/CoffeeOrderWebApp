package com.ssk3408;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssk3408.DAO.RegisterDAO;
import com.ssk3408.model.Cof_Order;
import com.ssk3408.model.Coffee;
import com.ssk3408.model.Customer;
import com.ssk3408.model.Order;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd = null;
	RegisterDAO regDAO = null;
	Customer operater = null;
	String openning = null;
	String clickDelete = "";
	String clickUpdate = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
    	regDAO = new RegisterDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		switch (action) {
		case "LOGIN":
			if(operater==null) {
				rd = request.getRequestDispatcher("/FormLogin.jsp");
				rd.forward(request, response);
			}else {
				listCustomer(request, response);
			}
			break;
		case "ORDER":
			if(operater==null) {
				rd = request.getRequestDispatcher("/FormLogin.jsp");
				rd.forward(request, response);
			}else {
				listOrder(request, response);
			}
			break;
		case "EDITORDER":
			if(operater==null) {
				rd = request.getRequestDispatcher("/FormLogin.jsp");
				rd.forward(request, response);
			}else {
				rd = request.getRequestDispatcher("/FormUpdateOrder.jsp");
				rd.forward(request, response);
			}
			break;
		case "NEWORDER":
			if(operater==null) {
				rd = request.getRequestDispatcher("/FormLogin.jsp");
				rd.forward(request, response);
			}else {
				rd = request.getRequestDispatcher("/FormNewOrder.jsp");
				rd.forward(request, response);
			}
			break;
		case "MENU":
			listCoffee(request, response);
			break;
		case "EXIT":
			operater = null;
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			break;
		default:
			//listUser(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		switch (action) {
		case "LOGIN":
			loginCustomer(request, response);
			break;
		case "REGISTER":
			registerCustomer(request, response);
			break;
		case "NEWORDER":
			newOrder(request, response);
			break;
		case "UPDATE":
			updateCustomer(request, response);
			break;
		case "ADDCOF":
			if(operater==null) {
				rd = request.getRequestDispatcher("/FormLogin.jsp");
				rd.forward(request, response);
			}else {
				addCoffee(request, response);
			}
			break;
		case "EDITORDER":
			getSingleOrderUpdate(request, response);
			break;
		case "DISPLAYCOF":
			listCof_Order(request, response);
			break;
		case "DELETEORDER":
			deleteOrder(request, response);
			break;
		case "DELETECUSTOMER":
			deleteCustomer(request, response);
			break;
		default:
			listOrder(request, response);
			break;
		}
	}
	
	protected void registerCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Customer c = new Customer();
		//c.setCustID(request.getParameter("custID"));
		c.setCustID(regDAO.getRandomID("CUSTOMER"));
		c.setName(request.getParameter("name"));
		c.setPhone(request.getParameter("phone"));
		c.setPassword(request.getParameter("password2"));

		if (regDAO.saveCustomer(c)) {
			request.setAttribute("NOTIFICATION", "User Registered Successfully!");
		}

		request.setAttribute("customer", c);
		RequestDispatcher rd = request.getRequestDispatcher("/FormRegistration.jsp");
		rd.forward(request, response);
	}

	protected void newOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order o = new Order();
		o.setCustID(operater.getCustID());
		o.setOrderID(regDAO.getRandomID("ORDER"));
		o.setOrderDate(regDAO.getDate());
		o.setTotalPrice(0);
		o.setOrderAddress(request.getParameter("orderAddress"));
		o.setPaymentMethod(request.getParameter("paymentMethod"));

		if (regDAO.saveOrder(o)) {
			request.setAttribute("NOTIFICATION", "Order added Successfully!");
		}

		request.setAttribute("order", o);
		RequestDispatcher rd = request.getRequestDispatcher("/FormNewOrder.jsp");
		rd.forward(request, response);
	}
	
	protected void addCoffee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cof_Order c = new Cof_Order();
		c.setCofID(request.getParameter("cofID"));
		c.setOrderID(request.getParameter("orderID"));
		c.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		if(regDAO.orderUnderCustomer(operater.getCustID(),c.getOrderID())) {
			if (regDAO.addCoffee(c)) {
				request.setAttribute("NOTIFICATION", "Coffee Added Successfully!");
			}
		}else
			request.setAttribute("NOTIFICATION", "The Order is not under your control!");

		request.setAttribute("coffee", c);
		RequestDispatcher rd = request.getRequestDispatcher("/FormAddCoffee.jsp");
		rd.forward(request, response);
	}
	
	private void loginCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = (request.getParameter("custID"));
		String pw = (request.getParameter("password"));
		if (regDAO.loginCustomer(id,pw)) {
			request.setAttribute("NOTIFICATION", "User Login Successfully!");
			operater = regDAO.getCustomer(id);
			listCustomer(request, response);
		}else {
			request.setAttribute("NOTIFICATION", "Invailed customer ID or Password!");
			request.setAttribute("custID", id);
			request.setAttribute("password", pw);
			RequestDispatcher rd = request.getRequestDispatcher("/FormLogin.jsp");
			rd.forward(request, response);
		}
	}

	private void listCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("customer", operater);
		RequestDispatcher rd = request.getRequestDispatcher("/FormPersonalPage.jsp");
		rd.forward(request, response);

	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		operater.setCustID(request.getParameter("custID"));
		operater.setName(request.getParameter("name"));
		operater.setPhone(request.getParameter("phone"));
		operater.setPassword(request.getParameter("password"));
		if (regDAO.update(operater)) {
			request.setAttribute("NOTIFICATION", "User Updated Successfully!");
		}
		request.setAttribute("customer", operater);
		RequestDispatcher rd = request.getRequestDispatcher("/FormPersonalPage.jsp");
		rd.forward(request, response);

	}

	private void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RegisterDAO dao = new RegisterDAO();
		List<Order> theList = dao.listOrder(operater.getCustID());
		request.setAttribute("order", theList);
		RequestDispatcher rd = request.getRequestDispatcher("/FormOrderList.jsp");
		rd.forward(request, response);
	}
	
	private void listCof_Order(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RegisterDAO dao = new RegisterDAO();
		List<Coffee> theList = dao.listCof_Order(request.getParameter("orderID"));
		System.out.print(theList.toString());
		request.setAttribute("coffee", theList);
		clickUpdate = "";
		RequestDispatcher rd = request.getRequestDispatcher("/FormCof_Order.jsp");
		rd.forward(request, response);
	}
	
	private void listCoffee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RegisterDAO dao = new RegisterDAO();
		List<Coffee> theList = dao.listCoffee();
		request.setAttribute("coffee", theList);
		RequestDispatcher rd = request.getRequestDispatcher("/FormMenu.jsp");
		rd.forward(request, response);
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order o = new Order();
		o.setOrderID(request.getParameter("orderID"));
		o.setTotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
		o.setOrderAddress(request.getParameter("orderAddress"));
		o.setOrderDate(request.getParameter("orderDate"));
		o.setPaymentMethod(request.getParameter("paymentMethod"));
		if(regDAO.orderUnderCustomer(operater.getCustID(),o.getOrderID())) {
			if (regDAO.editOrder(o)) {
				request.setAttribute("NOTIFICATION", "Order Updated Successfully!");
				clickUpdate = "";
			}
		}else
			request.setAttribute("NOTIFICATION", "The Order is not under your control!");
		Order order = regDAO.getOrder(request.getParameter("orderID"));
		request.setAttribute("order", order);
		RequestDispatcher rd = request.getRequestDispatcher("/FormUpdateOrderEdit.jsp");
		rd.forward(request, response);
	}
	
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderID = request.getParameter("orderID");
		System.out.println("delete");
		if(regDAO.orderUnderCustomer(operater.getCustID(),orderID)) {
			if (regDAO.deleteOrder(orderID)) {
				request.setAttribute("NOTIFICATION", "Order Deleted Successfully!");
				clickDelete = "";
			}
		}else
			request.setAttribute("NOTIFICATION", "The Order is not under your control!");
		RequestDispatcher rd = request.getRequestDispatcher("/FormDeleteOrder.jsp");
		rd.forward(request, response);
	}
	
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String custID = operater.getCustID();
		if (regDAO.delete(custID)) {
			request.setAttribute("NOTIFICATION", "Customer cancelled Successfully!");
			operater = null;
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/FormPersonalPage.jsp");
		rd.forward(request, response);
	}
	
	private void getSingleOrderUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (clickUpdate == "") {
			String orderID = request.getParameter("orderID");
			boolean orderFound = regDAO.checkOrder(orderID);
			if(regDAO.orderUnderCustomer(operater.getCustID(),orderID)) {
				if (orderFound) {
					Order order = regDAO.getOrder(orderID);
					request.setAttribute("order", order);
					clickUpdate = "Display";
					rd = request.getRequestDispatcher("/FormUpdateOrderEdit.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("NOTIFICATION", "Order Not Found!");
					rd = request.getRequestDispatcher("/FormUpdateOrder.jsp");
					rd.forward(request, response);
				}
			}else {
				request.setAttribute("NOTIFICATION", "The Order is not under your control!");
				rd = request.getRequestDispatcher("/FormUpdateOrder.jsp");
				rd.forward(request, response);
			}

		} else {
			updateOrder(request, response);
		}
	}
}
