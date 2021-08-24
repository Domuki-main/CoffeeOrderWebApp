<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Order List</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Order List</h1>

		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<table class="table table-striped table-bordered">
			<tr class="thead-dark">
				<th>Order ID</th>
				<th>Delivery Address</th>
				<th>Order Date</th>
				<th>Payment Method</th>
				<th>Total Price</th>
			</tr>

			<c:forEach items="${order}" var="order">

				<tr>
					<td>${order.orderID}</td>
					<td>${order.orderAddress}</td>
					<td>${order.orderDate}</td>
					<td>${order.paymentMethod}</td>
					<td>${order.totalPrice}</td>
				</tr>
			</c:forEach>
		</table>
		<input type="button" class="btn btn-secondary"
			onclick="window.location.href = '/CoffeeSellingSystem/index.jsp'"
			value="Back" /> <input type="button" class="btn btn-success"
			onclick="window.location.href = '/CoffeeSellingSystem/FormCheckCof.jsp'"
			value="Check Detail" /> <input type="button" class="btn btn-success"
			onclick="window.location.href = '/CoffeeSellingSystem/FormUpdateOrder.jsp'"
			value="Edit Order" /> <input type="button" class="btn btn-danger"
			onclick="window.location.href = '/CoffeeSellingSystem/FormDeleteOrder.jsp'"
			value="Delete Order" />
	</div>
</body>
</html>
