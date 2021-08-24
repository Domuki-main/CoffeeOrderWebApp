<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<title>Order Delete</title>

<body>

	<div class="container">

		<h2>Order Delete</h2>
		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="myForm" action="Controller?action=DELETEORDER"
					method="post">

					<div class="form-group">
						Order ID: <input type="text" class="form-control"
							name="orderID" placeholder="Enter Order ID"
							value="${order.orderID}" />
					</div>

					<input type="submit" class="btn btn-Danger" name="submit"
						onclick="return confirm('Are you sure you want to update the data?');"
						value="Delete" /> 
					<input type="button" class="btn btn-secondary"
						onclick="window.location.href = '/CoffeeSellingSystem/index.jsp'"
						value="Cancel" />

				</form>
			</div>
		</div>
	</div>
</body>
</html>