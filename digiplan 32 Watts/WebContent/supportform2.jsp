<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Alignwise Smile</title>


 <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="CSS/viewslider.css">
  <link rel="stylesheet" type="text/css" href="CSS/tableanimation.css">
  
<script type="text/javascript" src="script/angular.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload-shim.min.js" ></script>
<script type="text/javascript" src="script/FileSaver.js" ></script>
<script src="script/angular-route.js"></script>
 <script src="script/angular-animate.js"></script>
<script src="script/jquery.js" ></script>
<script type="text/javascript" src="script/supportapp/supportapp.js"></script>
<script type="text/javascript" src="script/moment.js" ></script>
<script type="text/javascript" src="script/controller.js"></script>

<script src="script/tether.js"></script>
<script src="script/bootstrap.js"></script>

<style type="text/css">
.dropdown:hover>.dropdown-menu {
  display: block;
}

</style>


</head>
<body ng-app="supportapp" ng-controller="cont" style="background-image: url('image/b.jpg');  background-position: center;
    background-repeat: no-repeat;
    background-attachment: fixed; background-size:cover; " >

<% 

if(session.getAttribute("supportpassword")==null||session.getAttribute("supportuser")==null){
	response.sendRedirect("index.jsp");
} 
else{
	out.println("<div ng-init=\"user='"+session.getAttribute("supportuser")+"'; formdata.user=user; \" >  </div>");
	out.println("<div ng-init=\"password='"+session.getAttribute("supportpassword")+"'; formdata.password=password; \" >  </div>");
}

%>

<nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse"  style="box-shadow: 0px 3px 5px gray" >
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand" href="#" >ALIGNWISE</a>
  
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto" >
    	
    	<li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="tab" href="#!" role="tab">View Previous Data</a>
  	</li>
 	 
    	<li class="nav-item">
		   <div class="dropdown nav-link">
		  <a class=" dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" style="color:white;" >
		    Welcome {{user}}
		  </a>
		  <div class="dropdown-menu" >
		    <a class="dropdown-item" href="index.jsp">Logout</a>
		    
		  </div>
		</div>
    	
       
      </li>
    </ul>
     
   
  </div>
</nav>
<br>
<ng-view class="slide"></ng-view>


<!-- Modal -->
  <div class="modal fade" id="fileuploadmodal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">Upload File</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         
        </div>
        <form action="Uploadfile" method="post" enctype="multipart/form-data" >
        <div class="modal-body">
        		
        		<label>Upload Your documents</label>
        		<input type="hidden" name="adminname"  id="adminname">
				<input type="hidden" name="foldername"  id="hiddenfoldername">
				<input type="hidden" name="serialnumber"  id="serialnumber">
				<input type="file" name="file1" multiple="multiple" class="form-control" >
				
			
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-default" >upload</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="window.location.reload();">No Upload</button>
        </div>
        </form>
      </div>
      
    </div>
  </div>
  
 <div class="modal fade" id="fileuploadedmodal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">Submited</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         
        </div>
        <form action="Uploadfile" method="post" enctype="multipart/form-data" >
        <div class="modal-body">
        		
        		<label>Prescription Added with reference number <span id="serialnumber1" ></span>.</label>
				
			
        </div>
        <div class="modal-footer">
         
          <a type="button" class="btn btn-danger"  href="adminpanel.jsp">ok</a>
        </div>
        </form>
      </div>
      
    </div>
  </div>
  <script>
var matches = /refno=([^&#=]*)/.exec(window.location.search);
var refid = matches[1];
if(refid!=null){
	document.getElementById("serialnumber1").innerHTML=refid;
	$('#fileuploadedmodal').modal('show');
}

</script>




</body>
</html>