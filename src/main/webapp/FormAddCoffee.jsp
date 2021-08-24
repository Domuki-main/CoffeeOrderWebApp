<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<title>Add Coffee to Order</title>
<script type="text/javascript">
	function validateForm(){
		if (document.myForm.cofID.value == null
				|| document.myForm.cofID.value == "") {
			alert("Please select coffee");
			return false;
		}
		if (document.myForm.quantity.value == null
				|| document.myForm.quantity.value == "") {
			alert("Please key-in quantity of coffee");
			return false;
		}
		if (document.myForm.orderID.value == null
				|| document.myForm.orderID.value == "") {
			alert("Please key-in order ID");
			return false;
		}
	}
</script>
<body>

	<div class="container">

		<h2>Add Coffee to Order</h2>
		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="myForm" action="Controller?action=ADDCOF" 
				onSubmit="return validateForm()" method="post">

					<c:set var="cofID" value="${coffee.cofID}" />
					Coffee:
					<div class="form-group">
						<select name="cofID">
							<option value="1"
								<c:if test="${coffee.cofID=='1'}">selected</c:if>>
								Espresso</option>
							<option value="2"
								<c:if test="${coffee.cofID=='2'}">selected</c:if>>
								Latte</option>
							<option value="3"
								<c:if test="${coffee.cofID=='3'}">selected</c:if>>
								Cappuccino</option>
							<option value="4"
								<c:if test="${coffee.cofID=='4'}">selected</c:if>>
								Americano</option>
							<option value="5"
								<c:if test="${coffee.cofID=='5'}">selected</c:if>>Flat
								White</option>
							<option value="6"
								<c:if test="${coffee.cofID=='6'}">selected</c:if>>
								Caramel Macchiato</option>
							<option value="7"
								<c:if test="${coffee.cofID=='7'}">selected</c:if>>
								Mocha</option>
							<option value="8"
								<c:if test="${coffee.cofID=='8'}">selected</c:if>>Cold
								Brew</option>
						</select>
					</div>

					<div class="form-group">
						Quantity: <input type="text" class="form-control" name="quantity"
							placeholder="Enter quantity" value="${coffee.quantity}" onkeyup="value=value.replace(/[^(\d)]/g,'')"/>
					</div>
					
					<div class="form-group">
						Order ID: <input type="text" class="form-control" name="orderID"
							placeholder="Enter Order ID" value="${coffee.quantity}" />
					</div>

					<input type="submit" class="btn btn-success" name="submit"
						value="Submit" /> 
					<input type="button" class="btn btn-secondary"
						onclick="window.location.href = '/CoffeeSellingSystem/index.jsp'"
						value="Cancel" />

				</form>
			</div>
		</div>
	</div>
</body>
</html>