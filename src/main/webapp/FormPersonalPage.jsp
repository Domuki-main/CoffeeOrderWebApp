<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<title>User Personal Page</title>

<body>

	<div class="container">

		<h2>User Personal Page</h2>
		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="myForm" action="Controller?action=UPDATE" method="post">

					<div class="form-group">
						Customer ID: <input type="text" class="form-control" name="custID"
							placeholder="---" value="${customer.custID}" />
					</div>
					<div class="form-group">
						Customer Name: <input type="text" class="form-control" name="name"
							maxlength='40' placeholder="---" value="${customer.name}" />
					</div>
					<div class="form-group">
						Customer Contact: <input type="text" class="form-control"
							name="phone" maxlength='10' placeholder="---" value="${customer.phone}" 
							onkeyup="value=value.replace(/[^(\d)]/g,'')"/>
					</div>
					<div class="form-group">
						Customer Password: <input type="text" class="form-control"
							name="password" maxlength='20' placeholder="---" value="${customer.password}" />
					</div>

					<input type="button" class="btn-secondary"
						onclick="window.location.href = '/CoffeeSellingSystem/index.jsp'"
						value="Main Menu" /> <input type="submit" class="btn btn-success"
						onclick="return confirm('Are you sure you want to update your information?');"
						name="submit" value="Update" />
				</form>
				<form name="myForm" action="Controller?action=DELETECUSTOMER" method="post">
					<input type="submit" class="btn btn-Danger" name="submit"
						onclick="return confirm('Are you sure you want to update the data?');"
						value="Delete" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>