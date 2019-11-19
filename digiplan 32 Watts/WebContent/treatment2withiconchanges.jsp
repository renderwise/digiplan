<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
    <TITLE>Alignwise Smile</TITLE>
    <script type="text/javascript" src="scripts/jsc3d.js"></script>
    <script type="text/javascript" src="scripts/jsc3d.webgl.js"></script>
    <script type="text/javascript" src="scripts/jsc3d.touch.js"></script>
    <script type="text/javascript" src="scripts/libgif.js"></script>
		<script type="text/javascript" src="scripts/rubbable.js"></script>
		
        <link href="CSS/pe-icon-7-stroke.css" rel="stylesheet" />
        <link href="CSS/font-awesome.css" rel="stylesheet" />
        <link href="CSS/modalstyle.css" rel="stylesheet" />
        <link href="CSS/slider.css" rel="stylesheet" />
	
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="CSS/bootstrap.css">
  <link rel="stylesheet" href="CSS/viewslidermobile.css">
  <script src="script/jquery.js"></script>

  <script src="scripts/bootstrap.min.js"></script>
  <script type="text/javascript" src="script/angular.min.js"></script>
  <script src="script/angular-route.js"></script>
  <script src="script/angular-animate.js"></script>
  <script type="text/javascript" src="scripts/MatchMedia.js" ></script>
  <script src="https://hammerjs.github.io/dist/hammer.js"></script>
<style type="text/css">

.containerimage {
    position: relative;
    background-color: black;
    box-shadow:2px 2px 10px gray;
}
.image {
  opacity: 1;
  display: block;
  
 	width:100%; height: 200px;
  transition: .5s ease;
  backface-visibility: hidden;
}

.middle {
  transition: .5s ease;
  opacity: 0;
  position: absolute;
  top: 0%;
  left: 90%;
  
}

.containerimage:hover .image {
  opacity: 0.6;
}

.containerimage:hover .middle {
  opacity: 1;
}

.text {
  background-color: #4CAF50;
  color: white;
  font-size: 16px;
  padding: 16px 32px;
}
@media only screen and (min-width: 769px) {
#gridsize{
    height: 440px;
 	margin-left:20px;   
    }

.paddingleftzero{
   padding:20px;
    padding-left: 80px;
 
    }
     #my-content { display: none; }   /* hide it elsewhere */
     #negativemycontent{display: block;}
     #bk{display: block;}
     #alignernumber{margin-top: -10px;}
     #swipe{display: none;}
    #ngviewreplacement{display: block;}
    }
    
    
@media only screen and (max-width: 425px) {
    /* For mobile phones: */
    .mobile {
        width: 100%;
    }
    .paddingleftzero{
    padding:20px;
    padding-left: 10px;
    
    }
    #gridsize{
    height: 400px;
    margin-top: 10px;
    }
    #my-content { display: block; }  /* show it on small screens */
    #negativemycontent{display: none;}
    #bk{display: none;}
    #alignernumber{margin-top: 0px;}
   #swipe{display: block;}
    #ngviewreplacement{display: none;}
}
@media only screen and (min-width: 426px) and (max-width: 768px)  {
    /* For mobile phones: */
    .mobile {
        width: 100%;
    }
    .paddingleftzero{
    padding:20px;
    padding-left: 10px;
    
    }
    #gridsize{
    height: 400px;
    margin-top: 10px;
    }
    #my-content { display: block; }  /* show it on small screens */
    #negativemycontent{display: none;}
    #bk{display: none;}
    #alignernumber{margin-top: 0px;}
    #swipe{display: block;}
    #ngviewreplacement{display: none;}
}
  





</style>

</HEAD>
<% 

if(request.getParameter("patientid")==null){
	response.sendRedirect("index.jsp");
} 
else{
	out.println("<div ng-init=\"patientid='"+request.getParameter("patientid")+"'; startpage(); \" >  </div>");
	//out.println("<div ng-init=\"password='"+session.getAttribute("password")+"'; formdata.password=password; \" >  </div>");
}
if((session.getAttribute("password")==null && session.getAttribute("supportpassword")==null) ||(session.getAttribute("username")==null && session.getAttribute("supportuser")==null) ||session.getAttribute("username")==session.getAttribute("supportuser")){
	response.sendRedirect("index.jsp");
} 
else{
	if(session.getAttribute("username")==null){
	out.println("<div ng-init=\"user='"+session.getAttribute("supportuser")+"'; formdata.user=user; commentjson.user=user; \" >  </div>");
	out.println("<div ng-init=\"password='"+session.getAttribute("supportpassword")+"'; formdata.password=password; \" >  </div>");
	}
	else{
		out.println("<div ng-init=\"user='"+session.getAttribute("username")+"'; formdata.user=user; commentjson.user=user; \" >  </div>");
		out.println("<div ng-init=\"password='"+session.getAttribute("password")+"'; formdata.password=password; \" >  </div>");
			
	}
	}

%>



<BODY ng-app="app" ng-controller="cont" style="" ng-init="" onload="changecanvassize()"  onresize="changecanvassize()" >

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div  style="position: fixed;
    bottom: 0;
    width: 100%; z-index: 100;"><i class="fa fa-copyright "></i> All Rights Are Reserved With <strong> RenderWise Solution.</strong> </div>
<nav class="navbar navbar-inverse" style="box-shadow:0px 0px 20px grey">
  <div class="container-fluid" >
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" onclick="window.history.back()" style="margin-top: 10px;" ><img alt="" src="http://alignwisesmile.com/images/logo.png" width="90%"> </a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
       <ul id="negativemycontent" class="nav navbar-nav" style="margin-top: 5px;" >
                    <li  style="margin-top: 2px; text-align: center;">
                        <a ng-click=" drawboard()">
                           <i> <img src="icons/gridicon.png" width="25px" ></i>
                            <p style="margin-top: 5px;">Grid</p>
                        </a>
                    </li>
	
                    <!-- <li style="text-align: center;">
                        <a ng-click=" changescene('d.stl');">
                           <i> <img src="icons/lowergum.png" width="50px" height="42px" ></i>
                            <p style="margin-top: -10px;">Lower</p>
                        </a>
                    </li>  -->
                    <!-- <li style="text-align: center;" >
                         <a ng-click=" changescene('x.stl')">
                           <i> <img src="icons/uppergum.png" style="margin-top: -5px;" width="50px"height="42px" ></i>
                            <p style="margin-top: -5px;">Upper</p>
                        </a>
                          
                    </li> -->
                    
                    <li style="text-align: center;" >
                         <a ng-click=" showsplimage(); urlarray=[];" >
                           <i class="fa fa-slack" style="font-size:33px; color:white; font-weight: lighter;" > </i>
                            <p style="margin-top: 0px;">Analysis</p>
                        </a>
                        
                    </li>
                    <li style="text-align: center;">
                    <a ng-click=" showfrontimage(); urlarray=[]; " >
                           <i> <img src="icons/front.png" style="margin-top: 5px;" width="50px"height="30px" ></i>
                            <p style="margin-top: 0px;">Front</p>
                        </a>
                          
                    </li>
                     <li style="text-align: center;">
                    <a ng-click=" showIPRimage(); " >
                           <i> <img src="icons/front.png" style="margin-top: 5px;" width="50px"height="30px" ></i>
                            <p style="margin-top: 0px;">IPR</p>
                        </a>
                          
                    </li>
                     <li class="dropdown" style="text-align: center;">
                    <button class="dropdown-toggle btn-link" data-toggle="dropdown" href="#" >
                           <i> <img src="icons/front.png" style="margin-top: 17px;" width="50px"height="30px" ></i>
                            <p style="margin-top: 0px;">3D View <span class="caret"></span> </p>
                        
                          </button>
                          <ul class="dropdown-menu">
			          <li style="margin-left: 10px;" ng-repeat="things in categories" ><a id="tog" ng-click="$parent.category=things; show3dview();" >{{things}}</a></li>
			        </ul>
                    </li>
                   
            	 </ul >
            	 
            	 <ul class="nav navbar-nav navbar-right " style="margin-top: 3px;" >
            	 		
            	 		<li id="bk" ><a style="text-align: center; color:white;" onclick="window.history.back()"> <i class="fa fa-arrow-circle-o-left fa-3x" ></i> <br>Back </a> </li>
			        	
			       <li class="dropdown" >
					  
					  <a class=" dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" style="color:white; text-align: center;" >
					  <i class="fa fa-user fa-3x" ></i><br>
					    Welcome {{user}} <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu dropdown-menu-right" >
					   <li> <a class="dropdown-item" href="index.jsp">Logout</a><li>
					  </ul>
					
			    	
			       
			      </li>
            	 
            	 </ul>
              
    </div>
  </div>
</nav>
<!-- 
<ul id="my-content" class="list-inline" style="width: 100vw;  background-color: black; text-align: center; color: white; padding-top: 10px;   " >
                    <li style="margin-top: 2px; text-align: center;">
                        <a ng-click=" drawboard()">
                           <i> <img src="icons/gridicon.png" width="25px" ></i>
                            <p style="margin-top: 5px; color:white;">Grid</p>
                        </a>
                    </li>
                     <li style="text-align: center;" >
                         <a ng-click=" showsplimage(); urlarray=[];" >
                           <i class="fa fa-image" style="font-size:33px; color:white; font-weight: lighter;" > </i>
                            <p style="margin-top: 0px; color:white;">Gifs</p>
                        </a> 
                        
                    </li>
	
                    <li style="text-align: center;">
                        <a ng-click=" changescene('d.stl');">
                           <i> <img src="icons/lowergum.png" width="50px" height="42px" ></i>
                            <p style="margin-top: -10px;">Lower</p>
                        </a>
                    </li> 
                    <li style="text-align: center;" >
                         <a ng-click=" changescene('x.stl')">
                           <i> <img src="icons/uppergum.png" style="margin-top: -5px;" width="50px"height="42px" ></i>
                            <p style="margin-top: -5px;">Upper</p>
                        </a>
                          
                    </li>
                    
                    <li style="text-align: center;" >
                         <a ng-click=" showsplimage(); urlarray=[];" >
                           <i class="fa fa-slack" style="font-size:33px; color:white; font-weight: lighter;" > </i>
                            <p style="margin-top: 0px; color:white;">Analysis</p>
                        </a> 
                        
                    </li>
                    <li style="text-align: center;">
                    <a ng-click=" showfrontimage(); urlarray=[]; " >
                           <i> <img src="icons/front.png" style="margin-top: 5px;" width="50px"height="30px" ></i>
                            <p style="margin-top: 0px; color:white;">Front</p>
                        </a>
                          
                    </li>
                     <li style="text-align: center;">
                    <a ng-click=" showIPRimage(); " >
                           <i> <img src="icons/front.png" style="margin-top: 5px;" width="50px"height="30px" ></i>
                            <p style="margin-top: 0px; color:white;">IPR</p>
                        </a>
                          
                    </li>
                     <li class="dropdown" style="text-align: center;">
                    <a class="dropdown-toggle btn-link" data-toggle="dropdown" href="#" >
                           <i> <img src="icons/front.png"  width="50px"height="30px" ></i>
                            <p style="margin-top: 0px; color:white;">3D View <span class="caret"></span> </p>
                        
                          </a>
                          <ul class="dropdown-menu">
			          <li style="margin-left: 10px;" ng-repeat="things in categories" ><a id="tog" ng-click="$parent.category=things; show3dview();" >{{things}}</a></li>
			        </ul>
                    </li>
                   
                   	<li ><a style="text-align: center; color:white;" onclick="window.history.back()"> <i class="fa fa-arrow-circle-o-left fa-3x" ></i> <br>Back </a> </li>
                   
                   
            	 </ul >
  -->       
<style>
.activelink{

/* font-size: x-large; */
    /* box-shadow: 0px 0px 20px #ffffff; */
    /* text-shadow: 0px 0px 20px #ffffff; */
    color: #79e5ff;
   
}
.activelink > p{
color: #79e5ff;
transition: all .2s ease-in-out;
transform: scale(1.2);
text-decoration: underline;
 text-decoration-color:#79e5ff; 
   
}
.activelink > i > img{
transition: all .2s ease-in-out;
transform: scale(1.2);
}
</style>        
<ul id="my-content"  class="list-inline" style="text-justify: auto; width: 100vw;  background-color: black; text-align: center; color: white; padding-top: 10px;   " >
                    <!-- <li style="margin-top: 2px; text-align: center;">
                        <a ng-click=" drawboard()">
                           <i> <img src="icons/gridicon.png" width="25px" ></i>
                            <p style="margin-top: 5px; color:white;">Grid</p>
                        </a>
                    </li> -->
                     <li style="text-align: center;" >
                         <a href="#!main" id="main" ng-class="{activelink : activeLink == 'main'} " >
                         <!--   <i class="fa fa-image" style="font-size:33px; color:white; font-weight: lighter;" > </i> -->
                            <p style="margin-top: 0px; color:white;">Gifs</p>
                        </a> 
                        
                    </li>
                    
                    <li style="text-align: center;" >
                         <a href="#!Analysis" id="Analysis" ng-class="{activelink: activeLink == 'Analysis'} " >
                           <!-- <i class="fa fa-slack" style="font-size:33px; color:white; font-weight: lighter;" > </i> -->
                            <p style="margin-top: 0px; color:white;">Analysis</p>
                        </a> 
                        
                    </li>
                    <li style="text-align: center;">
                    <a href="#!Front" id="Front" ng-class="{activelink : activeLink == 'Front'} " >
                          <!--  <i> <img src="icons/front.png" style="margin-top: 5px;" width="50px"height="30px" ></i> -->
                            <p style="margin-top: 0px; color:white;">Front</p>
                        </a>
                          
                    </li>
                     <li style="text-align: center;">
                    <a href="#!IPR" id="IPR" ng-class="{activelink : activeLink == 'IPR'} " >
                           <!-- <i> <img src="icons/front.png" style="margin-top: 5px;" width="50px"height="30px" ></i> -->
                            <p style="margin-top: 0px; color:white;">IPR</p>
                        </a>
                          
                    </li>
                    <!--  <li style="text-align: center;">
                    	<a href="#!3dview" id="3dview" ng-class="{activelink : activeLink == '3dview'} " >
                           <i> <img src="icons/front.png"  width="50px"height="30px" ></i>
                            <p style="margin-top: 0px; color:white;">3D View </p>
                        
                          </a>
                         
                    </li> -->
                   <li ><a style="text-align: center; color:white;" href="#!thumbnailview" > <i class="fa fa-th-large fa-2x" ></i></a> </li>
                   <li ><a style="text-align: center; color:white;" > <i class="fa fa-cube fa-2x" ></i></a> </li>
                   	<li ><a style="text-align: center; color:white;" onclick="window.history.back()"> <i class="fa fa-arrow-left fa-2x" ></i> </a> </li>
                   
                   
            	 </ul >




<div class="row" ng-show="patientproperties" style="text-align: center; margin-top: -10px;">
<label class="" >Patient Name :&nbsp{{patientproperties.patientName}} &nbsp&nbsp </label>
<label class="" >Case ID : &nbsp{{patientproperties.CaseID}} &nbsp&nbsp </label>
<label class="" >Number of Aligner : &nbsp{{patientproperties.numberOfAligner}} &nbsp&nbsp </label>
<label class="" >Doctor Name : &nbsp{{patientproperties.doctorName}} &nbsp&nbsp </label>
<label class="" >Date : &nbsp{{patientproperties.Date}}  </label>
</div>
<div id="swipe" style="z-index: 100; position: relative; height: 70vh; overflow: auto;" >
<div  ng-if="isScreenSize('md,sm,xs')" >
<div ng-view class="fade" ></div>
</div>
</div>
<div  ng-if="isScreenSize('lg')" >
<div class="container paddingleftzero" style="width:100vw;  text-align:center; background-color: #889dccd9;" >
<div class="col-lg-7 mobile"  id="grid" style="box-shadow: 0px 0px 20px black; background-color: black;"  >
	
	<div id="canvasesdiv" style="position:relative; width:100%; text-align: center; "  >
<!-- <canvas ng-mouseover="stoptimer();" ng-mouseleave="replacebytime();callintervalonplay();" id="layer1"
style="z-index: 1;
position:relative;" width="400px" height="400px" >
This text is displayed if your browser does not support HTML5 Canvas.
</canvas> -->

<!-- 
<canvas id="layer2"
style="z-index: 2;
position:absolute ;
left:0px;
top:0px;

" >
This text is displayed if your browser does not support HTML5 Canvas.
</canvas> -->
<div class="row ">

<div class="col-lg-6 col-sm-6" style="padding: 10px;" id="urlarrayrepeat" ng-repeat="item in urlarrayforgif" >

<div class="containerimage">
  <img src="{{item.url}}" alt="Avatar" class="img-responsive image" style="">
	<!-- <img id="gifid{{$index}}" src="{{item.url}}" rel:animated_src="{{item.url}}" rel:auto_play="0" class="img-responsive"  ng-mouseover="try3d('gifid'+$index);" /> -->  
  <div class="middle">
    <div class="btn btn-sm btn-success" ng-click="imgonclick(item)"><i class="fa fa-arrows-alt"></i></div>
    <div class="btn btn-sm btn-success" style="margin-top: 10px;" ng-click="cmntonclick(item.imagename);"><i class="fa fa-comment"></i></div>
    
  </div>
  <div class="middle" style="top: 90% ; left: 45%;" > <label style="color: white;">{{item.imagename}}</label> </div>
</div>
</div>




		
<!-- 
<div class="col-lg-6" style="padding: 10px;">
<div class="containerimage">
  <img src="users/{{patientid}}/Gifs/RIGHT.gif" alt="Avatar" class="img-responsive image" style="">
  <div class="middle">
    <div class="btn btn-info"  ng-click="imgonclick('RIGHT.gif')"><i class="fa fa-arrows-alt"></i></div>
    <div class="btn btn-info" style="margin-top: 10px;"><i class="fa fa-comment"></i></div>
  </div>
</div>
</div>
<div class="col-lg-6" style="padding: 10px;" >
<div class="containerimage">
  <img src="users/{{patientid}}/Gifs/LEFT.gif" alt="Avatar" class="img-responsive image" >
  <div class="middle">
    <div class="btn btn-info" ng-click="imgonclick('LEFT.gif')"><i class="fa fa-arrows-alt"></i></div>
    <div class="btn btn-info" style="margin-top: 10px;"><i class="fa fa-comment"></i></div>
  </div>
</div>
</div>
</div>
<div class="row">
<div class="col-lg-6" style="padding: 10px;" >
<div class="containerimage">
  <img id="myImg" src="users/{{patientid}}/Gifs/UPPER.gif"  alt="Avatar" class="img-responsive image" >
  <div class="middle">
    <div class="btn btn-info" ng-click="imgonclick('UPPER.gif')" ><i class="fa fa-arrows-alt"></i></div>
    <div class="btn btn-info" style="margin-top: 10px;"><i class="fa fa-comment"></i></div>
  </div>
</div>
</div>
<div class="col-lg-6"  style="padding: 10px;">
<div class="containerimage">
  <img src="users/{{patientid}}/Gifs/LOWER.gif"  alt="Avatar" class="img-responsive image" >
  <div class="middle">
    <div class="btn btn-info" ng-click="imgonclick('LOWER.gif')"><i class="fa fa-arrows-alt"></i></div>
    <div class="btn btn-info" style="margin-top: 10px;"><i class="fa fa-comment"></i></div>
  </div>
</div>
</div>
 -->
</div>

</div>

      
</div>

<div id="gridsize" class="col-lg-4 " style="box-shadow: 0px 0px 20px black; background-color: #f1f1f1f5;">
	
	<div id="canvasesdiv3d" ng-show="urlarray.length>0" style="position:relative; width:100%; height:100%; text-align: center; "  >
<canvas ng-mouseover="stoptimer();" ng-mouseleave="callintervalonplaywithoutpause();" id="layer1"
style="z-index: 1;
position:relative;" width="400px" height="400px"  >
This text is displayed if your browser does not support HTML5 Canvas.
</canvas>  

<canvas align="center" id="layer2"
style="z-index: 2;  pointer-events: none;
position:absolute ;
left:0px;
top:0px;" width="400px" height="400px"  >
This text is displayed if your browser does not support HTML5 Canvas.
</canvas>

<canvas align="center" id="layer3"
style="z-index: 2;
position:absolute ;  pointer-events: none;
left:0px;
top:0px;" width="400px" height="400px"  >
This text is displayed if your browser does not support HTML5 Canvas.
</canvas>

<div id="loadingSpinner" style="z-index: 5; display:none;
position:absolute; pointer-events: none; top:45% ; left: 40%" ><i class="fa fa-circle-o-notch fa-spin fa-5x"></i></div>

</div>
	<img id="splimage" ng-show="urlarray.length==0" class="img-responsive" style="border: none;">

</div>
</div>
    <!--  <div style="text-align: center;"><label>Timer :</label> <button class="badge" ng-click=" replacebytime();callinterval();" > > </button> <button ng-click="stoptimer();" class="badge" > || </button> </div> -->

 <div ng-show="urlarray.length>0" class="player row" style="background-color:black; margin-top:-15px; padding: 10px; ">
 	<div class="col-lg-3">
    <button type="button" id="button_fbw" class="btn" onclick='' ng-click="$parent.urlarrayindex=0; replacebytimewithoutpause();callintervalonplaywithoutpause();">
      <i class="fa fa-fast-backward"></i>
    </button>
    
    <button type="button" id="button_bw" class="btn" onclick='' ng-click="$parent.urlarrayindex=$parent.urlarrayindex-2; replacebytimewithoutpause();callintervalonplaywithoutpause();">
      <i class="fa fa-backward"></i>
    </button>
    
    <button type="button" id="button_play" class="btn" ng-click="replacebytimewithoutpause();callintervalonplaywithoutpause(); " >
      <i class="fa fa-play"></i>
    </button>
    
    <button type="button" id="button_stop" class="btn"  ng-click="stoptimer();">
      <i class="fa fa-stop"></i>
    </button>
    
    <button type="button" id="button_fw" class="btn"  ng-click="replacebytimewithoutpause();callintervalonplaywithoutpause();" >
      <i class="fa fa-forward"></i>
    </button>
    
    <button type="button" id="button_ffw" class="btn" onclick='' ng-click="$parent.urlarrayindex=$parent.urlarray.length-1; replacebytimewithoutpause();callintervalonplaywithoutpause();" >
      <i class="fa fa-fast-forward"></i>
    </button>    
    </div>
    
    <div class="col-lg-9" >
   
	<ul style="display: flex; 
    align-items: stretch; /* Default */
    justify-content: space-between;
    width: 99%;
    background:;
    
    margin-left:-10px;
    padding: 0;" id="alignernumber"> 
	<li style=" display: block;
    flex: 0 1 auto; /* Default */
    list-style-type: none;
   	 
   	font-weight: bold;" ng-style="slidervalue == $index ? { color:  'white'  } : { color : 'grey' }" ng-repeat="item in urlarray">{{$index+1}}</li>
	 </ul>
    <input style=" margin-top: -5px; margin-left:-15px; width: 100%" type="range" class="slider" ng-change="slidervaluechanged($parent.slidervalue); showcommenttextarea=true;"  ng-model="$parent.slidervalue"   min="0" max="{{urlarray.length-1}}" >
    
    </div>
    
    
  </div> 
  </div>
<br>
   <div class="row" >
  <div class="col-lg-3" ></div>
  <div class="col-lg-9" style="text-align: right;" ng-show="showcommenttextarea" >
  <textarea class="form-control"  ng-model="comment" placeholder="Add Comment at {{category}} Stage {{urlarrayindex}}"></textarea>
  <br>
  <button  class="btn btn-primary" ng-click="openimage=category+'_Stage_'+urlarrayindex; savejson();" >Add Comment</button>
  </div>
  
  </div> 
 <div class="row" >
  <div class="col-lg-3" ></div>
  <div class="col-lg-9" style="text-align: right;" ng-show="!showcommenttextarea" >
  <textarea class="form-control"  ng-model="comment" placeholder="General Comment"></textarea>
  <br>
  <button  class="btn btn-primary" ng-click="openimage='General'; savejson();" >Add Comment</button>
  </div>
  
  </div>
 
  <div class="row" style="padding: 30px; overflow-y:scroll; " >
<label>Comments:</label>
  <table class="table table-condensed table-striped table-bordered"  ng-init="fetchjson();" style="text-align: center;"> 
  <thead style="background-color: black; color: white;">
	<tr>
	<th style="text-align: center;">Stage</th>
	<th style="text-align: center;" >User</th>
	<th style="text-align: center;" >Comment</th>
	<th style="text-align: center;" >DateTime</th>
	</tr>  
  </thead>
  <tbody>
  <tr ng-repeat="item in commentdata">
  <td style="max-width: 20%;">{{item.stage}}</td>
  <td style="max-width: 10% " >{{item.user}}</td>
  <td style="min-width: 40%;" > {{item.comment}}</td>
  <td style="max-width: 30%" >{{item.timestamp}}</td> 
  </tr>
  </tbody>
  
  </table>
  </div>
  
  
 

    <script type="text/javascript">
    
    var app=angular.module("app", ['ngRoute','matchMedia','ngAnimate']);
    
    app.config(function($routeProvider,$locationProvider) {
    	
    	
    	$routeProvider
        .when("/", {
        	redirectTo: "/main"
        	
        	})
        	.when("/main", {
        		templateUrl : "treatmentviews/main.html"
        })
        .when("/3dview", {
            templateUrl : "treatmentviews/3dview.html"
        })
        .when("/Analysis", {
            templateUrl : "treatmentviews/Analysis.html"
        }).when("/Front", {
            templateUrl : "treatmentviews/Frontview.html"
        }).when("/IPR", {
            templateUrl : "treatmentviews/IPR.html"
        }).when("/thumbnailview", {
        	
            templateUrl : "treatmentviews/thumbnailview.html"
        })
        
        ;
    });
    
    app.controller("cont", function($scope,$http,$interval,screenSize) {
    	//$scope.patientid="user2";
    	$scope.slidervalue=0;
    	$scope.showcommenttextarea=false;
    	  $scope.isScreenSize = screenSize.is;
    	
    	/* var canvas = document.getElementById('layer1');
        var viewer = new JSC3D.Viewer(canvas);
        var theScene = new JSC3D.Scene;
        var numOfLoaded = 0;
        	
		       
            viewer.setParameter('BackgroundColor1', '#00FFFFFF');
            viewer.setParameter('BackgroundColor2', '#00FFFFFF');
            viewer.setParameter('Renderer', 'webgl');
           	viewer.setRenderMode('texturesmooth');
           	//viewer.setDefinition('high');
           //	viewer.generateBackground();
        	viewer.setParameter('Definition', 'high');
        	viewer.setParameter("Background", 'Off'); 
            viewer.init();
            viewer.update();
            
           
        
        $scope.changescene=function(scenurl){
        	 $interval.cancel(intervalvar);
        	viewer.replaceSceneFromUrl(scenurl);
        	$scope.urlarrayindex=$scope.urlarray.indexOf(scenurl)+1;
        	
        }
      
       
      */
        
        $scope.showsplimage=function(){
        	document.getElementById("Analysisimage").src="users/"+$scope.patientid+"/number.jpg";
        	angular.element('#AnalysisModal').modal('show');
        }
        $scope.showsplimageformobile=function(){
        	document.getElementById("Analysisimagemobile").src="users/"+$scope.patientid+"/number.jpg";
        	//angular.element('#AnalysisModal').modal('show');
        }
      
      $scope.showfrontimage=function(){
      	document.getElementById("splimage").height=430;
      	document.getElementById("splimage").src="users/"+$scope.patientid+"/Gifs/FRONT.gif";
      }
      
      $scope.showfrontimagemobile=function(){
        	document.getElementById("splimagemobile").height=430;
        	document.getElementById("splimagemobile").src="users/"+$scope.patientid+"/Gifs/FRONT.gif";
        }
      
        $scope.userpathforstl="users/"+$scope.patientid+"/stl/";
       
        $scope.urlarrayforgif=[];
        $scope.openIPR=function(selectedIPR){
        	document.getElementById("IPRimage").src=selectedIPR;	
        }
		
        $scope.openIPRmobile=function(selectedIPR){
        	document.getElementById("IPRimagemobile").src=selectedIPR;	
        }
        
        $scope.changescene=function(scenurl){
       	 $interval.cancel(intervalvar);
       	viewer.replaceSceneFromUrl(scenurl);
       	$scope.urlarrayindex=$scope.urlarray.indexOf(scenurl)+1;
       	
       }
        

        $scope.replacebytime=function() {
        	$scope.showcommenttextarea=false;
            	if($scope.urlarray.length<1){
            		return;
            	}
        		
            	if($scope.urlarrayindex<$scope.urlarray.length){
            	viewer.replaceSceneFromUrl($scope.urlarray[$scope.urlarrayindex]);
            	viewer.setParameter('ModelColor', '#888888');
            
            	//$scope.changeslidervalue($scope.urlarrayindex);
            	//alert($scope.slidervalue);
            	$scope.slidervalue=$scope.urlarrayindex;
            	$scope.urlarrayindex++;
            	console.log('replacebytime '+$scope.urlarrayindex);
				viewer.onloadingcomplete=function(){
            		$scope.replacebytime();
            		return;
            	};	
            	
            	}
            	else{
					
            		$scope.urlarrayindex=0;
            		//$scope.replacebytime();
            		$scope.callintervalonplaywithoutpause();
            		return;
            	}
            }
        
        $scope.replacebytimewithoutpause=function() {
        	$scope.showcommenttextarea=false;
            	if($scope.urlarray.length<1){
            		return;
            	}
        		console.log($scope.urlarrayindex);
            	if($scope.urlarrayindex<$scope.urlarray.length){
            	viewer.replaceSceneFromUrl($scope.urlarray[$scope.urlarrayindex]);
            	viewer.setParameter('ModelColor', '#888888');
            
            	//$scope.changeslidervalue($scope.urlarrayindex);
            	//alert($scope.slidervalue);
            	$scope.slidervalue=$scope.urlarrayindex;
            	$scope.urlarrayindex++;
            	console.log('replacebytimewithoutpause');
            	viewer.onloadingcomplete=null;
            	}
            	else{
					
            		$scope.urlarrayindex=0;
            		//$scope.replacebytime();
            		$scope.replacebytimewithoutpause();
            	}
            }
        
        $scope.callinterval=function(){
        	console.log('callinterval');
        	intervalvar=$interval($scope.replacebytimewithoutpause, 5000);
            }
            
            $scope.callintervalonplay=function(){
            /* if(typeof intervalvar !== 'undefined'){	$interval.cancel(intervalvar);}
            intervalvar=$interval($scope.replacebytime,); */
            $scope.replacebytime();
            }
            $scope.callintervalonplaywithoutpause=function(){
            	document.getElementById("loadingSpinner").style.display="none";
            	console.log('callintervalonplaywithoutpause')
                if(typeof intervalvar !== 'undefined'){	$interval.cancel(intervalvar);}
                intervalvar=$interval($scope.replacebytimewithoutpause, 5000);
             }
            
            $scope.stoptimer=function(){
            $interval.cancel(intervalvar);
            }
        
            $scope.slidervaluechanged=function(slidervalue){
            	//alert(slidervalue);
            	$scope.changescene($scope.urlarray[slidervalue]);
            }
        $scope.show3dview=function(){
        	
        	/* var canvasdiv=document.getElementById("splcanvas");
        	canvasdiv.innerHTML="<div id=\"canvasesdiv\" style=\"position:relative; width:100%; text-align: center; \" onmouseover=\"document.getElementById('layer1').style.zIndex='3';\" onmouseout=\"document.getElementById('layer1').style.zIndex='1';\" >"
        	+	"<canvas ng-mouseover=\"stoptimer();\" ng-mouseleave=\"replacebytime();callintervalonplay();\" id=\"layer1\""+
        	"	style=\"z-index: 1;"+
        	"	position:relative;\" width=\"400px\" height=\"400px\" >"+
        		"This text is displayed if your browser does not support HTML5 Canvas."+
        		"</canvas> </div> ";
        	  */ canvas = document.getElementById('layer1');
             viewer = new JSC3D.Viewer(canvas);
             theScene = new JSC3D.Scene;
             numOfLoaded = 0;
            	
                
                viewer.setParameter('BackgroundColor1', '#00FFFFFF');
                viewer.setParameter('BackgroundColor2', '#00FFFFFF');
                viewer.setParameter('Renderer', 'webgl');
               	viewer.setRenderMode('texturesmooth');
               	//viewer.setDefinition('high');
               //	viewer.generateBackground();
            	viewer.setParameter('Definition', 'high');
            	viewer.setParameter("Background", 'Off'); 
                viewer.init();
                viewer.update();
                
               
            
            
          
            $scope.userpathforstl="users/"+$scope.patientid+"/stl/"+$scope.category+"/";

            $scope.urlarray=[];
            
            $http.post("rest/stlfiles", JSON.stringify({"user":$scope.patientid,"category":$scope.category})).then(function(value) {
            	
            	for(i=0;i<value.data.stlfiles.length;i++){
            		$scope.urlarray.push($scope.userpathforstl+value.data.stlfiles[i]);
            		
            	}
            	
            	viewer.setParameter("SceneUrl",$scope.urlarray[0]);
            	viewer.setParameter('ModelColor', '#888888');
            	viewer.init();
            	document.getElementById("loadingSpinner").style.display="block";
            	$scope.callintervalonplay();
            }, function(reason) {
            	
            }, function(value) {
            	
            })
            
            $scope.urlarrayindex=0;
           	
             document.getElementById("splimage").src=null;
            
          	 
        }
        
        $scope.startpage=function(){
        	 $scope.userpathforgifs="users/"+$scope.patientid+"/Gifs/";
        $http.post("rest/giffiles", JSON.stringify({"user":$scope.patientid})).then(function(value) {
        	$scope.totalarray=value.data.gifs;
        	for(i=0;i<value.data.gifs.length;i++){
        		if(value.data.gifs[i].toString()!='FRONT.gif'){
        		$scope.urlarrayforgif.push({"url":$scope.userpathforgifs+value.data.gifs[i],"imagename":value.data.gifs[i].split(".")[0]});
        		}
        	}
        	/* viewer.setParameter("SceneUrl",$scope.urlarray[0]);
        	viewer.setParameter('ModelColor', '#888888');
        	viewer.init(); */
        }, function(reason) {
        	angular.element('#notreatmentmodal').modal('show');
        });
        
		$http.post("rest/patientproperties", JSON.stringify({"user":$scope.patientid})).then(function(value) {
        	
        		$scope.patientproperties=value.data;
        	        	
        	
        }, function(reason) {
        	
        }, function(value) {
        	
        });
    	
		 $http.post("rest/3dcategories", JSON.stringify({"user":$scope.patientid})).then(function(value) {
         	
			 $scope.categories=value.data.categories;
			 
         }, function(reason) {
         	
         }, function(value) {
         	
         });
		 
		 
        }
        
        
        $scope.showIPRimage=function(){
        	$scope.IPRarray=[];
       	 $scope.userpathforIPR="users/"+$scope.patientid+"/IPR/";
       $http.post("rest/IPRfiles", JSON.stringify({"user":$scope.patientid})).then(function(value) {
       	$scope.IPRfiles=value.data.IPRfiles;
       	for(i=0;i<value.data.IPRfiles.length;i++){
       		
       		$scope.IPRarray.push({"url":$scope.userpathforIPR+value.data.IPRfiles[i],"imagename":value.data.IPRfiles[i].split(".")[0]});
       		
       	}
       	document.getElementById("IPRimage").src=$scope.IPRarray[0].url;
       	/* viewer.setParameter("SceneUrl",$scope.urlarray[0]);
       	viewer.setParameter('ModelColor', '#888888');
       	viewer.init(); */
       	angular.element('#IPRModal').modal('show');	
       }, function(reason) {
       	
       }, function(value) {
       	
       })
       }
        
        $scope.showIPRimagemobile=function(){
        	$scope.IPRarray=[];
       	 $scope.userpathforIPR="users/"+$scope.patientid+"/IPR/";
       $http.post("rest/IPRfiles", JSON.stringify({"user":$scope.patientid})).then(function(value) {
       	$scope.IPRfiles=value.data.IPRfiles;
       	for(i=0;i<value.data.IPRfiles.length;i++){
       		
       		$scope.IPRarray.push({"url":$scope.userpathforIPR+value.data.IPRfiles[i],"imagename":value.data.IPRfiles[i].split(".")[0]});
       		
       	}
       	document.getElementById("IPRimagemobile").src=$scope.IPRarray[0].url;
       	/* viewer.setParameter("SceneUrl",$scope.urlarray[0]);
       	viewer.setParameter('ModelColor', '#888888');
       	viewer.init(); */
       //	angular.element('#IPRModal').modal('show');	
       }, function(reason) {
       	
       }, function(value) {
       	
       })
       }
        
             	
       
        var modal = document.getElementById('myModal');
    	
   	 // Get the image and insert it inside the modal - use its "alt" text as a caption
   	 
   	 var modalImg = document.getElementById("img01");
   	 var captionText = document.getElementById("caption");
   	 $scope.imgonclick = function(source){
   		 document.getElementById("changegif").innerHTML=" <img id=\"example1\" src=\"./example_gifs/rub_test_preview.jpg\" rel:animated_src=\"\" rel:auto_play=\"1\" width=\"10\" height=\"200\" rel:rubbable=\"1\" \/>";
   	    
   	    // modalImg.src = source.url;
   	     document.getElementById("example1").setAttribute('rel:animated_src', source.url); 
   	     captionText.innerHTML = source.imagename;
   	  loadgif();
   	 modal.style.display = "block";
   	 }
   	
   	 // Get the <span> element that closes the modal
   	 var span = document.getElementsByClassName("closebutton")[0];
   	
   	 // When the user clicks on <span> (x), close the modal
   	 span.onclick = function() { 
   	     modal.style.display = "none";
   	 }
	
   	 $scope.cmntonclick = function(imgname){
   		$scope.openimage=imgname;
   		angular.element('#commentModal').modal('show');
   	     //modalImg.src = source.url;
   	     
   	     //captionText.innerHTML = source.imagename;
   	 }
   
   		
        $scope.savejson=function(){
            //intervalvar=$interval($scope.replacebytime, 5000);
            $scope.commentjson={};
            $scope.commentjson.patient=$scope.patientid;
            //$scope.commentjson.user="LoginUser1";
            $scope.commentjson.stage=$scope.openimage;
            $scope.commentjson.timestamp=new Date();
            $scope.commentjson.comment=$scope.comment;
            
             $http.post("rest/savejson", JSON.stringify($scope.commentjson)).then(function(value) {
        	
            	 $scope.commentdata=value.data;
        	
        }, function(reason) {
        	
        }, function(value) {
        	
        })
            
         }
        
        $scope.fetchjson=function(){
        	$scope.commentjson={};
            $scope.commentjson.patient=$scope.patientid;
            $scope.commentjson.user="LoginUser1";
         $http.post("rest/fetchjson", JSON.stringify($scope.commentjson)).then(function(value) {
       	
           	 $scope.commentdata=value.data;
       	
       }, function(reason) {
       	
       }, function(value) {
       	
       })
        	
         }
    
     
        gridnumber=0;
        $scope.drawboard=function(){
       			
        	
        	//alert("changing width");
           		var p = 0;
           	
           		canvas = document.getElementById("layer2");
               
               var bw =canvas.width;
               // Box height
               var bh = canvas.height;
               var canvas1 = document.getElementById("layer3");
        	   var ctx = canvas1.getContext("2d");
        	  
               var context = canvas.getContext("2d");
               if(gridnumber>0){
            	   context.clearRect(0, 0, canvas.width, canvas.height );
               context.save();
               gridnumber=0;
           //	changecanvassize();
               canvas.style.border = "";
               canvas1 = document.getElementById("layer3");
        	   ctx = canvas1.getContext("2d");
        	   ctx.clearRect(0, 0, canvas1.width, canvas1.height );
               ctx.save();
               
               }
               else{
            	   canvas1 = document.getElementById("layer3");
            	   ctx = canvas1.getContext("2d");
            	   ctx.font = "bold 10px Arial";
            	   ctx.fillText("1mm",10,10);
           	  for(var x=0.5;x<bw;x+=3.7795275591) {
                     context.moveTo(x,0);
                     context.lineTo(x,bh);
                   }

                   for(var y=0.5; y<bh; y+=3.7795275591) {
                     context.moveTo(0,y);
                     context.lineTo(bw,y);

                 }
                  
           context.strokeStyle = "hsla(349, 72%, 62%, 0.4)";
           context.stroke();
           
           gridnumber=1;
           canvas.style.border = "1px solid hsla(349, 72%, 62%,0.4)";
           }
        }
    

    })

    </script>
    
    <script type="text/javascript">
    
    var changecanvassize=function(){
 	
         var ctrl2 = document.getElementById("layer1");
        var ctrl = document.getElementById("layer2");
        var ctrl3 = document.getElementById("layer3");
        
        //parentSize = document.getElementById("gridsize");
       
        
        
		ctrl.width  = $("#gridsize").width()
    	ctrl.height = $("#gridsize").height()-5;  
		ctrl2.width  = $("#gridsize").width()
    	ctrl2.height = $("#gridsize").height()-5; 
		ctrl3.width  = $("#gridsize").width()
    	ctrl3.height = $("#gridsize").height()-5; 
		gridnumber=0;
    	angular.element(document.getElementById('layer2')).scope().drawboard();
    }
	 
 
    
    
    
    </script>
    <script type="text/javascript">
	var loadgif=function(){
	sup1 = new RubbableGif({ gif: document.getElementById('example1'),max_width:''+(0.6*$(window).width()),progressbar_height :'5',progressbar_foreground_color :'#03a9f48c' });
	sup1.load();    
	
	}
	var element=document.getElementById("swipe");

	var hm=new Hammer(element);
	viewnumber=0;
	
	hm.on("swipeleft",function(event){
		if(viewnumber==viewarr.length-1){
			viewnumber=0;
		}
		else{
			viewnumber++;
		}
		window.location.href="#!"+viewarr[viewnumber];
		
	});
	 hm.on("swiperight",function(event){
		 if(viewnumber==0){
				viewnumber=viewarr.length-1;
			}
			else{
				viewnumber--;
			}
		window.location.href="#!"+viewarr[viewnumber];
		
	}); 
	viewarr=["main","Analysis","Front","IPR","3dview"];
	
	
	/* $('.nav a').on('click', function(){
	    if($(window).width()<768)
	    $('.navbar-toggle').click() //bootstrap 3.x by Richard
	});
	$('#tog').on('click', function(){
	    if($(window).width()<768)
	    $('.navbar-toggle').click() //bootstrap 3.x by Richard
	});
	 */
    </script>
			
<div id="myModal" class="modal">
  <span class="closebutton">x</span>
  <!-- <img class="modal-content" style="margin:auto; margin-top:-50px; width:65%; max-width: 100vw" id="img01"> -->
 
  <center id="changegif" style="">
   <img id="example1" src="" rel:animated_src="" rel:auto_play="1" width="10" height="200" rel:rubbable="1" />
  
  </center>
  <center style="position:relative; margin-top: -30px;" >
  			<a href="javascript:;" onmousedown="sup1.move_relative(-1); return false;"><i class="fa fa-backward fa-2x" ></i> </a>&nbsp
  			<a href="javascript:;" onmousedown="sup1.pause(); return false;" ><i class="fa fa-pause-circle fa-2x" ></i> </a> &nbsp
			<a href="javascript:;" onmousedown="sup1.play(); return false;"><i class="fa fa-play-circle fa-2x" ></i> </a> &nbsp
			<a href="javascript:;" onmousedown="sup1.move_relative(1); return false;"><i class="fa fa-forward fa-2x" ></i> </a> &nbsp 
			<a href="javascript:;" onmousedown="sup1.move_to(0); sup1.play(); return false;"><i class="fa fa-refresh fa-2x" ></i> </a>
			
  </center>
  <div id="caption"></div>
</div>


 <!-- Modal -->
  <div class="modal fade" id="commentModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Comments</h4>
        </div>
        <div class="modal-body">
           <textarea class="form-control" ng-model="comment" placeholder="Add Comment at {{openimage}}" ></textarea>
        </div>
        <div class="modal-footer">
          <div style="text-align: right;"><button class="btn btn-primary" ng-click="savejson();" data-dismiss="modal" >Add Comment</button> </div>
        </div>
      </div>
      
    </div>
  </div>
    
  <div class="modal  fade" id="IPRModal" role="dialog">
    <div class="modal-dialog modal-lg" style="width: 100vw; margin-top: -5%;" >
    
      <!-- Modal content-->
      <div class="modal-content" style="max-width: 60%; background-color:white;">
       
        <div class="modal-body" >
        	<div data-dismiss="modal" style="left: 99%; top: -10px; border-radius:50%; height:width; padding:5px; position: absolute; background-color:white;  color: red;" ><i  class="fa fa-2x fa-close"></i></div>
          <img style="margin-left: auto;
	margin-right: auto;
	display: block;" alt="IPR NOT AVAILABLE" class="img-responsive" id="IPRimage" src="">
        </div>
        <div class="modal-footer">
          <div style="text-align: center ;"> <div ng-show="IPRarray"><select class="form-control" ng-model="selectedIPR" ng-change="openIPR(selectedIPR)"> <option value="">--select--</option>
	<option ng-repeat="item in IPRarray"  value="{{item.url}}" >{{item.imagename}}</option>
	 </select> </div> </div>
        </div>
      </div>
      
    </div>
  </div> 
    
    
    <div class="modal  fade" id="AnalysisModal" role="dialog">
      
    
    <div class="modal-dialog modal-lg" style="width: 100vw; margin-top: -5%;" >
    <span data-dismiss="modal" style="margin-top: -50px;" class="closebutton">x</span>
      <!-- Modal content-->
      <div class="modal-content" style="max-width: 60%; background-color: #ffffff00" >
       
        <div class="modal-body" >
        	
           <img style="margin-left: auto; margin-right: auto;" alt="" class="img-responsive" id="Analysisimage" src="">
        </div>
       
      </div>
      
    </div>
  </div>
    
    <div class="modal  fade" id="notreatmentmodal" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg" style="width: 100vw; margin-top: -5%;" >
    
      <!-- Modal content-->
      <div class="modal-content" style="max-width: 60%">
       
        <div class="modal-body" style="text-align: center;" >
        <br>
        
        	<label style="font-size: large; ">  No Treatment Plan Available</label>
           
        </div>
        <div class="modal-footer">
         <button class="btn btn-info" onclick="window.history.back()" >Back</button>
        </div>
      </div>
      
    </div>
  </div>
    
</BODY>
</HTML>
