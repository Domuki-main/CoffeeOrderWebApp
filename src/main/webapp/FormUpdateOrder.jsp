<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<title>Order Check</title>

<body>

	<div class="container">

		<h2>Order Check</h2>
		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="myForm" action="Controller?action=EDITORDER"
					method="post">

					<div class="form-group">
						Order ID: <input type="text" class="form-control" name="orderID"
							placeholder="Enter Order ID" value="${order.orderID}" />
					</div>

					<input type="submit" class="btn btn-success" name="submit"
						value="Submit" /> <input type="button" class="btn btn-secondary"
						onclick="window.location.href = '/CoffeeSellingSystem/index.jsp'"
						value="Cancel" />

				</form>
			</div>
		</div>
	</div>
</body>
</html>