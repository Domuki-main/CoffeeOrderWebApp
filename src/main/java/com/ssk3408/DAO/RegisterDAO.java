package com.ssk3408.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.ssk3408.model.Cof_Order;
import com.ssk3408.model.Coffee;
import com.ssk3408.model.Customer;
import com.ssk3408.model.Order;

public class RegisterDAO {
	Connection connection=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement =null;

	public boolean saveCustomer(Customer c) {
		boolean flag = false;

		try {
			String sql = "INSERT INTO CUSTOMER(custid, custname, custphone, custpassword)VALUES" + "('" 
		+ c.getCustID() + "','" + c.getName() + "', '" + c.getPhone() + "', '" + c.getPassword() +"')";

			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}
	
	public boolean saveOrder(Order o) {
		boolean flag = false;

		try {
			String sql = "INSERT INTO orders(custid, orderid, orderaddress, totalprice, paymentmethod, orderdate)VALUES" + "('" 
		+ o.getCustID() + "','" + o.getOrderID() + "', '" + o.getOrderAddress() + "', '" + o.getTotalPrice() + "', '" + o.getPaymentMethod()+ "', TO_DATE('" + o.getOrderDate() +"','DD/MM/YYYY'))";

			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}
	
	public boolean addCoffee(Cof_Order c) {
		boolean flag = false;

		try {
			String sql = "INSERT INTO cof_order(cofid, orderid, quantity)VALUES" + "('" 
		+ c.getCofID() + "','" + c.getOrderID() + "', '" + c.getQuantity() + "')";
			//System.out.println(sql2);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			String sql2 = "UPDATE ORDERS SET TOTALPRICE ="+calcTotalPrice(c.getOrderID())+" WHERE ORDERID = '"+c.getOrderID()+"'";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	public List<Customer> listCustomer() {
		List<Customer> list = null;
		Customer customer = new Customer();

		try {
			list = new ArrayList<Customer>();
			String sql = "SELECT * FROM customer order by custID asc";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				customer = new Customer();
				customer.setCustID(resultSet.getString("custID"));
				customer.setName(resultSet.getString("custname"));
				customer.setPhone(resultSet.getString("custphone"));
				customer.setPassword(resultSet.getString("custpassword"));
				list.add(customer);
				}
			} catch (Exception e) {
		}

		return list;
	}
	
	public List<Order> listAllOrder() {
		List<Order> list = null;
		Order order = new Order();
		try {
			list = new ArrayList<Order>();
			String sql = "SELECT * FROM orders order by orderID asc";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				order = new Order();
				order.setOrderID(resultSet.getString("orderID"));
				order.setOrderAddress(resultSet.getString("orderAddress"));
				order.setOrderDate(resultSet.getString("orderDate"));
				order.setPaymentMethod(resultSet.getString("paymentMethod"));
				list.add(order);
			}
		} catch (Exception e) {
		}
		for(int i = 0; i<list.size(); i++) {
			list.get(i).setTotalPrice(calcTotalPrice(list.get(i).getOrderID()));
		}
		return list;
	}

	public List<Order> listOrder(String id) {
		List<Order> list = null;
		Order order = new Order();
		try {
			list = new ArrayList<Order>();
			String sql = "SELECT * FROM orders where custID='"+id+"' order by orderID asc";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				order = new Order();
				order.setOrderID(resultSet.getString("orderID"));
				order.setOrderAddress(resultSet.getString("orderAddress"));
				order.setOrderDate(resultSet.getString("orderDate"));
				order.setPaymentMethod(resultSet.getString("paymentMethod"));
				//order.setTotalPrice(calcTotalPrice(order.getOrderID()));
				list.add(order);
			}
		} catch (Exception e) {
		}
		for(int i = 0; i<list.size(); i++) {
			list.get(i).setTotalPrice(calcTotalPrice(list.get(i).getOrderID()));
		}
		return list;
	}
	
	public String RandomID() {
		Random r = new Random();
		String id = "";
		for(int i = 0; i < 6; i++) {
			id = id + r.nextInt(9);
		}
		return id;
	}
	
	public String getRandomID(String choose) {
		String id = RandomID();
		switch(choose) {
		case "CUSTOMER": 
			while(IDSame(id,"CUSTOMER")) {
				id = RandomID();
			}
			break;
		case "ORDER": 
			while(IDSame(id,"ORDER")) {
				id = RandomID();
			}
			break;
		}
		return id;
	}
	
	public boolean IDSame(String id, String choose) {
		boolean same = false;
		switch(choose) {
		case "CUSTOMER": 
			List<Customer> list1 = listCustomer();
			for(int i = 0; i < list1.size(); i++) {
				if(id.equals(list1.get(i).getCustID()))
					same = true;
			}
			break;
		case "ORDER": 
			List<Order> list2 = listAllOrder();
			for(int i = 0; i < list2.size(); i++) {
				if(id.equals(list2.get(i).getOrderID()))
					same = true;
			}
			break;
		}
		return same;
	}
	
	public String getDate() {
		SimpleDateFormat df = new SimpleDateFormat("DD/MM/YYYY"); 
		return df.format(new Date());
	}
	
	public List<Coffee> listCof_Order(String orderID){
		List<Cof_Order> list = null;
		List<Coffee> output = new ArrayList<Coffee>();
		//List<Coffee> menu = listCoffee();
		Cof_Order co = new Cof_Order();
		try {
			list = new ArrayList<Cof_Order>();
			String sql = "SELECT * FROM cof_order where orderID='"+orderID+"' order by cofID asc";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				co = new Cof_Order();
				co.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
				co.setCofID(resultSet.getString("cofid"));
				co.setOrderID(orderID);
				list.add(co);
			}
			
			for(int i = 0; i<list.size(); i++) {
				output.add(transferCof_Order(list.get(i)));
			}
			
		} catch (Exception e) {
		}
		return output;
	}
	
	public Coffee transferCof_Order(Cof_Order co) {
		List<Coffee> menu = listCoffee();
		Coffee c = new Coffee();
		switch(co.getCofID()) {
			case "1":
				c = menu.get(0);
				break;
			case "2":
				c = menu.get(1);
				break;
			case "3":
				c = menu.get(2);
				break;
			case "4":
				c = menu.get(3);
				break;
			case "5":
				c = menu.get(4);
				break;
			case "6":
				c = menu.get(5);
				break;
			case "7":
				c = menu.get(6);
				break;
			case "8":
				c = menu.get(7);
				break;
		
		}
		c.setQuantity(co.getQuantity());
		c.setPrice(c.getUnitPrice()*c.getQuantity());
		return c;
	}
	
	public List<Coffee> listCoffee(){
		List<Coffee> list = null;
		Coffee coffee = new Coffee();
		try {
			list = new ArrayList<Coffee>();
			String sql = "SELECT * FROM coffee order by cofID asc";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				coffee = new Coffee();
				coffee.setCofID(resultSet.getString("cofid"));
				coffee.setCofName(resultSet.getString("cofname"));
				coffee.setUnitPrice(Double.parseDouble(resultSet.getString("unitprice")));
				coffee.setInfo(resultSet.getString("info"));
				list.add(coffee);
				}
			} catch (Exception e) {
		}
		return list;
	}
	
	public double calcTotalPrice(String orderID) {
		List<Order> list = null;
		Order order = new Order();
		double tp = 0;
		try {
			list = new ArrayList<Order>();
			String sql = "select o.orderid \"orderID\", sum(co.quantity * c.unitprice) \"totalPrice\"\r\n"
					+ "from orders o\r\n"
					+ "join cof_order co on(co.orderid = o.orderid)\r\n"
					+ "join coffee c on (co.cofid = c.cofid)\r\n"
					+ "group by o.orderid";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				order = new Order();
				order.setOrderID(resultSet.getString("orderid"));
				order.setTotalPrice(Double.parseDouble(resultSet.getString("totalprice")));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i=0; i<list.size(); i++)
			if(list.get(i).getOrderID().equals(orderID))
				tp = list.get(i).getTotalPrice();
		return tp;
	}
	
	public boolean loginCustomer(String id, String pw) {
		Boolean enter = false;
		try {
			String sql = "SELECT * FROM customer where custID='" + id +"' and custpassword='"+ pw +"'";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				enter = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enter;
	}
	
	public boolean checkCustomer(String custID) {
		Boolean found = false;
		try {
			String sql = "SELECT * FROM customer where custID='" + custID +"'";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				found = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
	
	public boolean checkOrder(String orderID) {
		Boolean found = false;
		try {
			String sql = "SELECT * FROM orders where orderID='" + orderID +"'";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				found = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}

	public Customer getCustomer(String custID) {
		Customer customer = null;
		try {
			customer = new Customer();
			String sql = "SELECT * FROM customer where custid='" + custID +"'";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				customer.setCustID(resultSet.getString("custid"));
				customer.setName(resultSet.getString("custname"));
				customer.setPhone(resultSet.getString("custphone"));
				customer.setPassword(resultSet.getString("custpassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public boolean update(Customer customer) {
		boolean flag = false;
		try {
			String sql = "UPDATE customer SET custname = '" + customer.getName() + "', "
					+ "custpassword = '" + customer.getPassword() + "', custphone = '" 
					+ customer.getPhone() + "' where custid ='"+ customer.getCustID()+"'";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean editOrder(Order order) {
		boolean flag = false;
		try {
			String sql = "UPDATE orders SET totalprice ='" + order.getTotalPrice()  + "', orderaddress = '" + order.getOrderAddress() + "', "
					+ "paymentmethod = '" + order.getPaymentMethod() + "' where orderid = '" +order.getOrderID()+"'";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean delete(String custID) {
		boolean flag = false;
		try {
			String sql = "delete from cof_order where orderid in (select orderid from orders where custid = '" +custID+"')";
			String sql2 ="delete from orders where custid = '"+custID+"'";
			String sql3 ="delete from customer where custid = '"+custID+"'";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(sql3);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public Order getOrder(String orderID) {
		Order order = null;
		try {
			order = new Order();
			String sql = "SELECT * FROM orders where orderid='" + orderID +"'";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				order.setOrderID(resultSet.getString("orderid"));
				order.setOrderAddress(resultSet.getString("orderAddress"));
				order.setTotalPrice(Double.parseDouble(resultSet.getString("totalPrice")));
				order.setPaymentMethod(resultSet.getString("paymentMethod"));
				order.setOrderDate(resultSet.getString("orderDate"));
			}
			order.setCoffeeList(listCof_Order(orderID));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	public boolean deleteOrder(String orderID) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM orders where orderid='" + orderID +"'";
			String sql2 = "DELETE FROM COF_ORDER where orderid='" + orderID +"'";
			System.out.println(sql);
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean orderUnderCustomer(String custID, String orderID) {
		boolean output = false;
		List<Order> list = listOrder(custID);
		for (int i = 0; i < list.size(); i++) {
			if (orderID.equals(list.get(i).getOrderID()))
				output = true;
		}
		return output;
	}
	
}
