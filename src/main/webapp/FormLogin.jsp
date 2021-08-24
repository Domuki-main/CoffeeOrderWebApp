<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<title>Login Page</title>
<script type="text/javascript">
	function validateForm() {
		if (document.myForm.custID.value == null
				|| document.myForm.custID.value == "") {
			alert("Please key-in customer ID");
			return false;
		}
		if (document.myForm.password.value == null
				|| document.myForm.password.value == "") {
			alert("Please key-in password");
			return false;
		} 
	}
</script>
<body>
	<div class="container">
		<h2>Login Page</h2>
		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="myForm" action="Controller?action=LOGIN"
					onSubmit="return validateForm()" method="post">
					
					<div class="form-group">
						User ID: <input type="text" class="form-control"
							name="custID" placeholder="Enter Firstname"
							value="${customer.custID}" />
					</div>
					<div class="form-group">
						Password: <input type="password" class="form-control" name="password"
							placeholder="Enter Password" value="${customer.password}" />
					</div>
					<input type="reset" 
						class="btn btn-danger" 
						name="reset"
						value="Reset" /> 
					<input type="submit" 
						class="btn btn-success"
						name="login" 
						value="Login" /> 
						<input type="button"
						class="btn btn-success"
						onclick="window.location.href = '/CoffeeSellingSystem/FormRegistration.jsp'"
						value="Register" />
					<input type="button"
						class="btn btn-secondary"
						onclick="window.location.href = '/CoffeeSellingSystem/index.jsp'"
						value="Back" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>