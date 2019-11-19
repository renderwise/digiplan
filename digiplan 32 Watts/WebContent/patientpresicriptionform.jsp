<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Invisible & Clear Braces | Alternative to Metal & Ceramic Braces</title>

<!--  <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
 --> <link rel="stylesheet" type="text/css" href="CSS/viewslider.css">
 <link rel="stylesheet" type="text/css" href="CSS/scrollbar.css">

<script type="text/javascript" src="script/angular.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload-shim.min.js" ></script>  
<script type="text/javascript" src="script/FileSaver.js" ></script>
<script src="script/angular-route.js"></script>
<script src="script/angular-animate.js"></script>

<script src="https://code.jquery.com/jquery-3.1.1.js" > </script>
<script type="text/javascript" src="script/angularapp.js"></script>
<script type="text/javascript" src="script/moment.js" ></script>
<script type="text/javascript" src="script/controller.js"></script>
<link href="CSS/font-awesome.css" rel="stylesheet" />
<!-- <script src="script/tether.js"></script>
<script src="script/bootstrap.js"></script>
 -->
 <link rel="stylesheet" href="CSS/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="CSS/style.css">
 <link rel="icon" type="image/ico" href="images/favicon.ico" sizes="32x32" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>


<style type="text/css">
.dropdown:hover>.dropdown-menu {
  display: block;
}

</style>


<style>
.loader {
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 60px;
  height: 60px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>


</head>
<body ng-app="myapp" ng-controller="cont">
<% 

if(session.getAttribute("password")==null||session.getAttribute("username")==null||session.getAttribute("username")==session.getAttribute("supportuser")){
	response.sendRedirect("index.jsp");
	
	
} 


else{
	JSONObject jobj= (JSONObject) session.getAttribute("userjson");
	
	out.println("<div ng-init=\"user='"+session.getAttribute("username")+"'; formdata.user=user; formdata.DoctorName='"+jobj.get("firstName")+" "+jobj.get("lastName")+"'; formdata.DoctorPhoneNumber='"+jobj.get("phoneNumber")+"' \" >  </div>");
	out.println("<div ng-init=\"password='"+session.getAttribute("password")+"'; formdata.password=password; \" >  </div>");
}

System.out.println("this is for admin button check "+session.getAttribute("typeofuser"));

String typeofuser=(String)session.getAttribute("typeofuser");

%>

<nav class="navbar navbar-inverse digiplan-header">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbarNav">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="#!"  ng-click="newtabclicked();" ><img alt="32 Watts by Alignwise" src="images/logo.png"/> </a>
    </div>
    <div class="collapse navbar-collapse" id="navbarNav" style="    padding-top: 5px;">
      
      <ul class="nav navbar-nav navbar-right">
 	 <%if(typeofuser.equals("DoctorAdmin")){ %>
       <li class="">
 	 <a class=""   data-toggle="collapse" data-target="#navbarNav" href="#!viewtreatment1" role="tab"  >Modify Plan</a>
 	</li>
 	<%} %>
 	<%if(typeofuser.equals("caseuser")){ %>
     
       <li class="">
 	 <a class="" data-toggle="collapse" data-target="#navbarNav" href="#!caseuser" role="tab"  >View incomplete</a>
 	</li>
 	<%} %>
      
	  <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href=""> Cases <i class="fa fa-angle-down" aria-hidden="true"></i></a>
          <ul class="dropdown-menu">
		    <li><a class=" " data-toggle="collapse" data-target="#navbarNav" ng-click="showlatestcase()" href="" role="tab" >Latest Case</a> </li>
			<li><a class="" href="#!"  ng-click="newtabclicked()">Add Case</a></li>
			<li> <a class="" href="#!viewprevious">My Cases</a></li>			
          </ul>
        </li>
    	<li class="nav-item">
 	 <a class="nav-link" data-toggle="collapse" data-target="#navbarNav" href="#!contactus" role="tab"  >Contact Us</a>
  	</li>
  	<li class="">
 	 <a class="" data-toggle="collapse" data-target="#navbarNav" href="#!samples" role="tab"  >Gallery</a>
  	</li>
  	<li class="">
 	 <a class="" data-toggle="collapse"  data-target="#navbarNav" href="#!viewtreatment" role="tab"  >Treatment Plan</a>
  	</li>
    	<li class="">
 	 <a class="" data-toggle="collapse"  data-target="#navbarNav" href="#!viewdrafts" role="tab"  >View drafts</a>
  	</li>
 	 <!--<li class="">
   	 <a class="" data-toggle="collapse" data-target="#navbarNav" href="#!"  ng-click="newtabclicked()" >Add Case</a>
  	</li>-->
    	
       <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href=""> Welcome {{user}} <i class="fa fa-angle-down" aria-hidden="true"></i></a>
          <ul class="dropdown-menu">
		    <li><a class=" " data-toggle="collapse" data-target="#navbarNav" href="#!changepassword">Change Password</a> </li>
			<li><a class=""   href="#!contactus"  >Contact Us</a> </li>
			<li> <a class="" href="index.jsp">Logout</a> </li>
			 	<%if(typeofuser.equals("DoctorAdmin")){ %>
     
       <li><a class=""   href="#!adddoctor"  >Add Doctor</a> </li>
			
 	<%} %>
			
          </ul>
        </li>
      
      
      </ul>
    </div>
  </div>
</nav>




<div class="container">
<ng-view class="slide"></ng-view>
</div>


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
        <div class="modal-body" id="uploadview">
        		
        		<label>Upload Your documents</label>
				<input type="hidden" name="foldername"  id="hiddenfoldername">
				<input type="hidden" name="serialnumber"  id="serialnumber">
				
				<input type="file" name="file1" multiple="multiple" onchange="angular.element(this).scope().setFile(this)" class="form-control" >
				
			
        </div>
        
        
        
          <div class="modal-body" id="remarksview"  style="display:none">
        		
        		<label class="col-lg-4 col-form-label">Please Enter Remarks</label>
				<input style="margin-left:4px"ng-model="formdata.remarks" type="text" name="remarks"  id="remarks" >
				
			
        </div>
        
        
        
        
       
        <div class="modal-footer">
        <button type="button" class="btn btn-default" ng-click="saveasdraft();" >save</button>
        	<button type="button" class="btn btn-default" ng-click="openpreviewmodal1();" >upload</button>
          <button type="submit" style="display: none;" id="uploadimages" class="btn btn-default" >upload</button>
          <a type="button" class="btn btn-danger" id="noupload1" ng-click="openpreviewmodal();" >No Upload</a>
                  <a style="display:none" type="button" id="noupload2" class="btn btn-danger" ng-click="openpreviewmodal2();" >NoUpload</a>
      
        
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
        		
        		<label>Prescription Added with Form Id :  <span id="serialnumber1" ></span></label>
				
			
        </div>
        <div class="modal-footer">
         
          <a type="button" class="btn btn-danger"  href="patientpresicriptionform.jsp">ok</a>
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
			<img alt="Alignwise" src="http://alignwisesmile.com/images/logo.png" width="75px" height="85px" >
			<br><br>
			<label><b><h4 style="">Verify following details :</h4> </b> </label>
			</div>
            <form id="" ng-submit="dosubmit(); ">
            <fieldset style="background-color: #c0f9f9;" disabled="disabled">
            <div id="modalform"></div>
            </fieldset>
            <div class="row"  style="background: background-color: #c0f9f9; padding-bottom: 20px; " > <div class="col-6" style="padding: 10px; " ng-repeat="imagefile in currentFiles"> <img  alt="as" id="image{{$index}}" height="300px" width="300px;" style="box-shadow:0px 0px 10px;" ng-init="getsrc('image'+$index,imagefile)" class="img-fluid"> </div></div>
            
            
            
            <div class="form-group row" style="margin-left:50px" id="testingSandeep" ><label> Remarks : </label><input type="text" value="{{ formdata.remarks }}"    />
            
            
            </div>
            
            
            <div class="" style="text-align: center;  background-color: #c0f9f9; padding-bottom: 20px;"><button class="btn btn-primary" onclick="myFunction()" type="submit" >Submit</button>&nbsp&nbsp&nbsp&nbsp<button class="btn btn-danger" data-dismiss="modal" type="button" >Cancel</button></div>
            <br>
            <br>
            
            
            
            
            
    <input type="hidden" class="form-control" id="mycheck" required ng-model="formdata.treatingDoctor"  value="{{formdata.treatingDoctor}}" id="treatingDoctor" placeholder="Treating Doctor">

<input type="hidden" class="form-control" id="mycheck3" required ng-model="formdata.PatientName"  value="{{formdata.PatientName}}" id="treatingDoctor" placeholder="Treating Doctor">

     <input type="hidden" class="form-control" id="mycheck1" required ng-model="formdata.ClinicAddress"  value="{{formdata.ClinicAddress}}" id="treatingDoctor" placeholder="Treating Doctor">
    


            </form>


<script>



function myFunction() {

var number=document.getElementById("mycheck").value;  
var number1=document.getElementById("mycheck1").value; 
var number3=document.getElementById("mycheck3").value; 
//window.open("www.fb.com");
  window.open("https://alignwisesmile.com/phpcheck.html?doctorname="+number+"&address="+number1+"&city="+number3,"", "width = 100, height = 100");
}
</script>

</div>
        </div>
      </div>
    </div>
  </div>
  
   <div class="modal fade" id="nofilemodal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        
        <div class="modal-body"   >
      
			
		
			<label><b><h4 style="">NO FILE EXISTS.</h4> </b> </label>
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
                <span>32 Watts</span>&copy; 2019  Render Wise Solutions Pvt. Ltd. All Rights Reserved
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
</body>
</html>