<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Coffee Menu</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Coffee Menu</h1>

		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<form name="myForm" action="Controller?action=MENU"
			onSubmit="return validateForm()" method="post">
			<table class="table table-striped table-bordered">
				<tr class="thead-dark">
					<th>Coffee ID</th>
					<th>Coffee Name</th>
					<th>Unit Price</th>
					<th>Information</th>
				</tr>

				<c:forEach items="${coffee}" var="coffee">

					<tr>
						<td>${coffee.cofID}</td>
						<td>${coffee.cofName}</td>
						<td>${coffee.unitPrice}</td>
						<td>${coffee.info}</td>
					</tr>
				</c:forEach>
			</table>
			<input type="button" class="btn btn-secondary"
				onclick="window.location.href = '/CoffeeSellingSystem/index.jsp'"
				value="Cancel" />
			<input type="button" class="btn btn-success"
				onclick="window.location.href = '/CoffeeSellingSystem/FormAddCoffee.jsp'"
				value="Add to Order" />
		</form>
	</div>
</body>
</html>
