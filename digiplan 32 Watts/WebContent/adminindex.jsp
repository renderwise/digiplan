<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Alignwise Smile</title>

 <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
 

</head>
<body style="background-color: #deffff">
<%
if(session.getAttribute("adminusername")!=null){
	session.removeAttribute("adminusername");
	session.removeAttribute("adminpassword");
}

%>


<nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse"  >
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand col-lg-2 col-4" href="#" ><img alt="" src="http://alignwisesmile.com/images/logo.png" width="90%"> </a>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto" >
    	
    </ul>
     
   
  </div>
</nav>

<br>
<br>
<div class="" style="background: url('images/loginbackground.jpg'); background-position: center; background-size:cover; ">
<br>
<br>
<br>


<div class="container "  >



<div class=" col-md-3 col-lg-3" style="" >
<br>
<form action="servletadmin" method="post">
<div style="text-align: center" >
<label style="text-shadow: 4px 3px 7px rgba(6, 6, 6, 0.26);" ><h3>Admin Login</h3> </label>
</div>
<div class="form-group">
<label style="font-weight: bolder;" >Admin Name</label>
<input type="text" name="username" class="form-control" style="box-shadow: 3px 4px 9px rgb(226, 226, 226)">

</div>

<div class="form-group">
<label style="font-weight: bolder;" >Password</label>
<input type="password" name="password" class="form-control" style="box-shadow: 3px 4px 9px rgb(226, 226, 226)" >
<a style="text-align: right; margin-top: 15px; color: darkblue;" href=""  >Forgot password?</a>
</div>
<div style="text-align: center;" >
<button type="submit" " style="color: white;" class="btn btn-primary" name="loginButton" value="Login" >Login</button>&nbsp 

</div>

</form>
<br>

</div>


</div>
<br><br><br>

</div>



<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div  style="position: fixed; font-size:small;
    bottom: 0;
    width: 100%; z-index: 100; color: white; background-color: black; "><i class="fa fa-copyright "></i> All Rights Are Reserved With <strong> RenderWise Solution.</strong> </div>

</body>
</html>