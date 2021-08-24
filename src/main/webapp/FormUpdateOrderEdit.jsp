<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<title>Order Update Edit</title>

<body>

	<div class="container">

		<h2>Order Details</h2>
		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="myForm" action="Controller?action=EDITORDER"
					method="post">

					<div class="form-group">
						Order ID: <input type="text" class="form-control" name="orderID"
							placeholder="Order ID" value="${order.orderID}" />
					</div>
					<div class="form-group">
						Total Price: <input type="text" class="form-control"
							name="totalPrice" placeholder="Total Price"
							value="${order.totalPrice}" />
					</div>
					<div class="form-group">
						Order Address: <input type="text" class="form-control"
							name="orderAddress" maxlength='40' placeholder="Order Address"
							value="${order.orderAddress}" />
					</div>
					<div class="form-group">
						Order Date: <input type="text" class="form-control"
							name="orderDate" placeholder="Order Date"
							value="${order.orderDate}" />
					</div>

					<c:set var="paymentMethod" value="${order.paymentMethod}" />
					Payment Method:
					<div class="form-group">
						<input type="radio" name="paymentMethod" value="AliPay"
							<c:if test="${order.paymentMethod=='AliPay'}">checked</c:if>>AliPay
					</div>
					<div class="form-group">
						<input type="radio" name="paymentMethod" value="WechatPay"
							<c:if test="${order.paymentMethod=='WechatPay'}">checked</c:if>>WechatPay
					</div>
					<div class="form-group">
						<input type="radio" name="paymentMethod" value="Cash"
							<c:if test="${order.paymentMethod=='Cash'}">checked</c:if>>Cash
					</div>

					<input type="submit" class="btn btn-success"
						onclick="return confirm('Are you sure you want to update the data?');"
						name="submit" value="Update" /> <input type="button"
						class="btn btn-secondary"
						onclick="window.location.href = '/CoffeeSellingSystem/index.jsp'"
						value="Main Menu" />

				</form>
			</div>
		</div>
	</div>
</body>
</html>