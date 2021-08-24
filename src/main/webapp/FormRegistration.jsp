<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<title>User Registration</title>
<script type="text/javascript">
	function validateForm() {
		if (document.myForm.name.value == null
				|| document.myForm.name.value == "") {
			alert("Please key-in name");
			return false;
		}
		if (document.myForm.phone.value == null
				|| document.myForm.phone.value == "") {
			alert("Please key-in phone");
			return false;
		}
		if (document.myForm.password1.value == null
				|| document.myForm.password1.value == "") {
			alert("Please key-in password");
			return false;
		} else {
			if (document.myForm.password2.value == null
					|| document.myForm.password2.value == "") {
				alert("Please confirm password");
				return false;
			} else {
				if (document.myForm.password1.value != document.myForm.password2.value) {
					alert("Password does not match");
					return false;
				}
			}
		}
	}
</script>
<body>
	<div class="container">
		<h2>User Registration Details</h2>
		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="myForm" action="Controller?action=REGISTER"
					onSubmit="return validateForm()" method="post">
					<div class="form-group">
						Customer ID: <input type="text" class="form-control" name="custID"
							placeholder="The ID will be created after register"
							value="${customer.custID}" />
					</div>
					<div class="form-group">
						Name: <input type="text" class="form-control" name="name"
							maxlength='40' placeholder="Enter name" value="${customer.name}" />
					</div>
					<div class="form-group">
						Phone: <input type="text" class="form-control" name="phone"
							maxlength='10' placeholder="Enter phone"
							value="${customer.phone}"
							onkeyup="value=value.replace(/[^(\d)]/g,'')" />
					</div>

					<div class="form-group">
						Password: <input type="password" class="form-control"
							name="password1" maxlength='20' placeholder="Enter Password"
							value="${customer.password}" />
					</div>
					<div class="form-group">
						Confirm Password: <input type="password" class="form-control"
							name="password2" placeholder="Enter Password"
							value="${customer.password}" />
					</div>
					<input type="reset" class="btn btn-danger" name="reset"
						value="Reset" /> <input type="submit" class="btn btn-success"
						name="submit" value="Submit" /> <input type="button"
						class="btn btn-secondary"
						onclick="window.location.href = '/CoffeeSellingSystem/FormLogin.jsp'"
						value="Back" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>