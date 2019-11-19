<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Alignwise Smile</title>


 <link rel="stylesheet" type="text/css" href="CSS/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="CSS/viewslider.css">
 <link rel="stylesheet" type="text/css" href="CSS/tableanimation.css">
   
<script type="text/javascript" src="script/angular.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload-shim.min.js" ></script>
<script type="text/javascript" src="script/FileSaver.js" ></script>
<script src="script/angular-route.js"></script>
 <script src="script/angular-animate.js"></script>
<script src="script/jquery.js" ></script>
<script type="text/javascript" src="script/admin/adminapp.js"></script>
<script type="text/javascript" src="script/moment.js" ></script>
<script type="text/javascript" src="script/controller.js"></script>

<script src="script/tether.js"></script>
<script src="scripts/bootstrap.min.js"></script>

<style type="text/css">
.dropdown:hover>.dropdown-menu {
  display: block;
}

</style>


</head>
<body ng-app="adminapp" ng-controller="cont" style="background-image: url('image/background.jpg');  background-position: center;
    background-repeat: no-repeat;
    background-attachment: fixed; background-size:cover; " >

<% 

if(session.getAttribute("adminpassword")==null||session.getAttribute("adminusername")==null){
	response.sendRedirect("adminindex.jsp");
} 
else{
	out.println("<div ng-init=\"adminuser='"+session.getAttribute("adminusername")+"'; formdata.adminuser=adminuser; formdata.user=adminuser; \" >  </div>");
	out.println("<div ng-init=\"adminpassword='"+session.getAttribute("adminpassword")+"'; formdata.adminpassword=password; \" >  </div>");
}

%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div  style="position: fixed; font-size:small;
    bottom: 0;
    width: 100%; z-index: 100; color: white; background-color: black; "><i class="fa fa-copyright "></i> All Rights Are Reserved With <strong> RenderWise Solution.</strong> </div>

<!-- <nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse "   style="box-shadow: 0px 3px 5px gray" >
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand col-lg-2 col-4" ng-click="newtabclickedgenform();" ><img alt="" src="http://alignwisesmile.com/images/logo.png" width="90%"> </a>
  
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto" >
    <li class="nav-item">
   	 <a class="nav-link" style="color:white;"   href="#!existing" >Existing Users</a>
  	</li>
    	<li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="tab" href="#!" role="tab"  >Register User</a>
  	</li>
    	<li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="tab" href="#!previous" role="tab">View Previous Data</a>
  	</li>
 	 <li class="nav-item">
   	 <a class="nav-link" style="color:white;"   ng-click="newtabclickedgenform();" >Generate new form</a>
  	</li>
    	<li class="nav-item">
		   <div class="dropdown nav-link">
		  <a class=" dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" style="color:white;" >
		    Welcome admin
		  </a>
		  <div class="dropdown-menu" >
		    <a class="dropdown-item" href="adminindex.jsp">Logout</a>
		    <a class="dropdown-item" href="#!viewdrafts">View drafts</a>
		    
		  </div>
		</div>
    	
       
      </li>
    </ul>
     
   
  </div>
</nav> -->


<nav class="navbar navbar-inverse" style="min-height: 65px;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse"  data-target="#navbarNav">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="#!"  ng-click="newtabclicked();" ><img alt="" src="http://alignwisesmile.com/images/logo.png" width="70%"> </a>
    </div>
    <div class="collapse navbar-collapse" id="navbarNav" style="    padding-top: 5px;">
      
      <ul class="nav navbar-nav navbar-right">
         <li class="">
   	 <a class="" style="color:white;"  data-toggle="collapse"   data-target="#navbarNav" href="#!existing" >Existing Users</a>
  	</li>
    	<li class="">
 	 <a class="" style="color:white;"  data-toggle="collapse"  data-target="#navbarNav" href="#!" role="tab"  >Register User</a>
  	</li>
    	<li class="">
 	 <a class="" style="color:white;"  data-toggle="collapse"  data-target="#navbarNav" href="#!previous" role="tab">View Previous Data</a>
  	</li>
 	 <li class="">
   	 <a class="" style="color:white;"   ng-click="newtabclickedgenform();" >Generate new form</a>
  	</li>
    	
       <li class="dropdown">
          <a class="dropdown-toggle" style="color:white;" data-toggle="dropdown" href=""> Welcome {{user}}<span class="caret"></span></a>
          <ul class="dropdown-menu">
             <li> <a class="" href="adminindex.jsp">Logout</a></li>
		    <li><a class="" data-toggle="collapse"  data-target="#navbarNav" href="#!viewdrafts">View drafts</a></li>
          </ul>
        </li>
      
      
      </ul>
    </div>
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
				<input type="hidden" name="foldername"  id="hiddenfoldername">
				<input type="hidden" name="serialnumber"  id="serialnumber">
				<input type="hidden" name="adminname"  id="adminname">
				<input type="file" name="file1" multiple="multiple" onchange="angular.element(this).scope().setFile(this)" class="form-control" >
				
			
        </div>
       
        <div class="modal-footer">
        	<button type="button" class="btn btn-default" ng-click="openpreviewmodal();" >upload</button>
          <button type="submit" style="display: none;" id="uploadimages" class="btn btn-default" >upload</button>
          <a type="button" class="btn btn-danger" ng-click="openpreviewmodal();">No Upload</a>
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
  
  <div class="modal fade" id="previewmodal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        
        <div class="modal-body"   >
        <div class="scrollbar" id="style-4" style="max-height: 100vh; overflow-y: auto; " >
			<div style="text-align: center;">
			<img alt="Alignwise" src="http://alignwisesmile.com/images/logo.png" width="60%" >
			<br><br>
			<label><b><h4 style="">Verify following details :</h4> </b> </label>
			</div>
            <form id="" ng-submit="dosubmit(); ">
            <fieldset  disabled="disabled">
            <div id="modalform"></div>
            </fieldset>
            <div class="row"  style="background: background-color: #c0f9f9; padding-bottom: 20px; " > <div class="col-6" style="padding: 10px; " ng-repeat="imagefile in currentFiles"> <img  alt="as" id="image{{$index}}" height="300px" width="300px;" style="box-shadow:0px 0px 10px;" ng-init="getsrc('image'+$index,imagefile)" class="img-fluid"> </div></div>
            <div class="" style="text-align: center;  background-color: #c0f9f9; padding-bottom: 20px;"><button class="btn btn-primary" type="submit" >Submit</button>&nbsp&nbsp&nbsp&nbsp<button class="btn btn-danger" data-dismiss="modal" type="button" >Cancel</button></div>
            </form>
        </div>
        </div>
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