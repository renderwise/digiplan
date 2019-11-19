<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Invisible & Clear Braces | Alternative to Metal & Ceramic Braces</title>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="CSS/style.css">
 <link rel="icon" type="image/ico" href="images/favicon.ico" sizes="32x32" />
 
 
 <script src="script/jquery.js"></script>
 
<script type="text/javascript" src="script/angular.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload-shim.min.js" ></script>
<script type="text/javascript" src="script/FileSaver.js" ></script>
<script src="script/angular-route.js"></script>
 <script src="script/angular-animate.js"></script>
<script src="script/jquery.js" ></script>
<script src="https://code.jquery.com/jquery-3.1.1.js" > </script>
<script type="text/javascript" src="script/angularapp.js"></script>
<script type="text/javascript" src="script/moment.js" ></script>
<script type="text/javascript" src="script/controller.js"></script>
<link href="CSS/font-awesome.css" rel="stylesheet" />
<script src="script/tether.js"></script>
<script src="script/bootstrap.js"></script>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-107309764-2"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-107309764-2');
</script>
   
</head>
<body style="background-color: #deffff"  ng-app="myapp" ng-controller="cont">
<%
if(session.getAttribute("username")!=null){
	session.removeAttribute("username");
	session.removeAttribute("password");
}

if(session.getAttribute("supportuser")!=null){
	session.removeAttribute("supportuser");
	session.removeAttribute("supportpassword");
}



%>

<div class="modal fade" id="forgetpasswordmodal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content" style=" background: none; border: none;">
         <div class="modal-body"   >
         
			<div class="popup-box">
					 <button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3>Forgot your password?</h3>
					<h4>Not to worry, we got you! Let's get you a new password.</h4>
					<form ng-submit="forgetPassword();">
					<div class="form-group"><input type="text" required="required" placeholder="Username" class="form-control" ng-model="forgetPasswordJson.username">
					</div>
					<div class="form-group"><input type="text" required="required" placeholder="Phone Number" pattern="[0-9]{10}" class="form-control" ng-model="forgetPasswordJson.phoneNumber" >
					</div>
					<div class="form-group">
					<input type="password" required="required" placeholder="New Password" class="form-control" ng-model="forgetPasswordJson.newpassword" >
					</div>
					<div class="form-group">
					<input type="password" required="required" placeholder="Confirm New Password" class="form-control" ng-model="forgetPasswordJson.confirmnewpassword"  >
					</div>
					<label class="text-danger" ng-if="forgetPasswordJson.newpassword!=forgetPasswordJson.confirmnewpassword" >Password does not match</label>
					<div class="col-lg-12"><button type="submit" class="btn btn-changepwd"  >Change Password</button>
					</div>
					</form>
					</div>

        </div>
      </div>
    </div>
  </div>
<!--<nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse"  >
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand col-lg-2 col-4" href="#" ><img alt="ALIGNWISWE" src="http://alignwisesmile.com/images/logo.png" width="90%"> </a>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto" >
    	
    </ul>
     
   
  </div>
</nav>  -->


<header id="header">

        <div class="container">

            <div class="row">

                <div class="col-sm-2">

                    <div class="header-logo">

                        <a href="index.html">

                            <img src="images/logo.png" alt="32 Watts Clear Aligners" />

                        </a>

                    </div>

                </div>

                <div class="col-sm-10">

                    <nav id="header-nav-wrap">

                        <ul class="header-main-nav">
                            <li>
                                <a href="http://www.32watts.com/" title="Patient">Patient</a>
                            </li>
                            <li>
                                <a href="http://www.32watts.com/orthodontist.html" title="Orthodontist">Dentist</a>
                            </li>
                            <li>
                                <a href="http://www.32watts.com/about.html" title="About">About</a>
                            </li>
                            <li>
                                <a href="https://blog.32watts.com/" target="blank" title="Blog">Blog</a>
                            </li>
                        </ul>
                    </nav>
                    <a class="header-menu-toggle" href="#">
                        <span>Menu</span>
                    </a>
                </div>
            </div>
        </div>
    </header>


<br>
<br>


<div class="login-bg">
<div class="container">
<h1>Please login to continue</h1>
<div class="login-form">
<form action="servlet1" method="post">

<div class="form-group">
<!--<label style="font-weight: bolder;">Username</label>-->
<label>Username</label>
<input type="text" name="username" class="" placeholder="Enter Username">
</div>

<div class="form-group">
<!--<label style="font-weight: bolder;" >Password</label>-->
<label>Password</label>
<input type="password" name="password" class="form-control" placeholder="Enter Password">
<a data-target="#forgetpasswordmodal" data-toggle="modal" class="link-forgotpwd">Forgot your password?</a>
</div>
<div style="text-align: center;" >
<button type="submit" class="btn login-btn" name="loginButton" value="Login" >Login</button>

</div>

</form>
</div>
</div>
</div>


<footer>
        <div class="container">
            <div class="f-logo">
                <img src="images/logo-footer.jpg" alt="32 watts clear aligner" />
            </div>
            <div class="footer-links">
                <ul>
                    <li>
                        <a href="http://www.32watts.com/">Patient</a>
                    </li>
                    <li>
                        <a href="http://www.32watts.com/orthodontist.html">Orthodontists</a>
                    </li>
                    <li>
                        <a href="http://www.32watts.com/about.html">About</a>
                    </li>
                    <li>
                        <a href="https://blog.32watts.com/ target="blank" title="Blog">Blog</a>
                    </li>
                </ul>
            </div>
            <div class="copyright">
                <span>32 Watts</span>&copy; 2019 Alignwise Smile Technologies. All Rights Reserved
            </div>
            <div class="footer-social">
                <ul>
                    <li>
                        <a href="https://www.facebook.com/32watts">
                            <i class="fa fa-facebook" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.linkedin.com/company/alignwise-smile-technologies/">
                            <i class="fa fa-linkedin" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/alignwise_smile/">
                            <i class="fa fa-instagram" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.youtube.com/channel/UC2Xgt_zC4ZWvKISMzzwmMbg">
                            <i class="fa fa-youtube-play" aria-hidden="true"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </footer>


  <div class="modal  fade" id="errormodal" role="dialog">
    <div class="modal-dialog modal-sm"  >
    
      <!-- Modal content-->
      <div class="modal-content" >
       
        <div class="modal-body" style="text-align: center;" >
        <br>
        
        	<label style="font-size: large; ">  Invalid Credentials !</label>
           
        </div>
        <div class="modal-footer">
         <button class="btn btn-info" data-dismiss="modal" >Back</button>
        </div>
      </div>
      
    </div>
  </div>
  
<% 
if(session.getAttribute("error")!=null){
	
	if(session.getAttribute("error").equals("Invalid credentials!")){
		
		out.println("<script type=\"text/javascript\">console.log('not');  $('#errormodal').modal('show');</script>");
		session.removeAttribute("error");
	}
}

%>

<script type="text/javascript" src="script/bootstrap.js" ></script>
  <script src="script/jquery-2.1.3.min.js"></script>
  <script src="script/main.js"></script>
  
 </body>
</html>