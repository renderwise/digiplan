app.controller("cont", function($scope,$http,$location,Upload, $timeout) {
	$scope.a=2;
	$scope.formdata={};
	$scope.contactusform={};
	$scope.showsubmit=true;
	$scope.adminuser=null;
	$scope.draftId=null;
	$scope.formdata.date=moment().format("YYYY-MM-DD");
	$scope.viewtreatment=function(serialnumber){
		window.location.href="treatment2.jsp?serialnumber="+serialnumber;
	}
	$scope.required=false;

	$scope.slideimages=["download1.jpg","download2.jpg","download3.jpg","download4.jpg","download5.jpg","download6.jpg","download7.jpg","download8.jpg"]
	/* $http.get("rest/return").then(function(value) {
		alert(value.data);
		$scope.formdata=value.data;
	}, function(reason) {
		
	}, function(value) {
		
	}) */
	//$locationProvider.html5Mode(true);
	
	/*$scope.showDoctorName=function(){
		$scope.userjson=JSON.parse($scope.userjson);
		$scope.formdata.DoctorName=$scope.userjson.firstname+' '+$scope.userjson.lastname;	
	}*/
	
	/*$scope.haha=function(){
		
		
		
	            if ($("#Implant1").is(":checked")) {
	                $("#checkbox1").show();
					$("#checkbox2").hide();
					$("#checkbox3").hide();
					$("#checkbox4").hide();
					
	            } else {
				
				 if (!($("#Implant1").is(":checked")||$("#Crown1").is(":checked")||$("#Prosthesis1").is(":checked")||$("#Restoration1").is(":checked"))) {
				
				
	                $("#checkbox1").hide();
					$("#checkbox2").hide();
					$("#checkbox3").hide();
					$("#checkbox4").hide();
					
	            }
	       } 
			 
	            if ($("#Crown1").is(":checked")) {
	                $("#checkbox2").show();
					$("#checkbox1").hide();
					$("#checkbox3").hide();
					$("#checkbox4").hide();
	            } else {
				
	            	if (!($("#Implant1").is(":checked")||$("#Crown1").is(":checked")||$("#Prosthesis1").is(":checked")||$("#Restoration1").is(":checked"))) {
	        				
	     			
				
	                $("#checkbox1").hide();
					$("#checkbox2").hide();
					$("#checkbox3").hide();
					$("#checkbox4").hide();
					
	            }
	            //    $("#checkbox2").hide();
	            }
	        
			
	            if ($("#Prosthesis1").is(":checked")) {
	                $("#checkbox3").show();
					$("#checkbox1").hide();
					$("#checkbox2").hide();
					$("#checkbox4").hide();
	            } else {
	            	if (!($("#Implant1").is(":checked")||$("#Crown1").is(":checked")||$("#Prosthesis1").is(":checked")||$("#Restoration1").is(":checked"))) {
	        				
				
	                $("#checkbox1").hide();
					$("#checkbox2").hide();
					$("#checkbox3").hide();
					$("#checkbox4").hide();
					
	            }
	              //  $("#checkbox2").hide();
	            }
	        
			
	            if ($("#Restoration1").is(":checked")) {
	                $("#checkbox4").show();
					$("#checkbox1").hide();
					$("#checkbox2").hide();
					$("#checkbox3").hide();
	            } else {
	            	if (!($("#Implant1").is(":checked")||$("#Crown1").is(":checked")||$("#Prosthesis1").is(":checked")||$("#Restoration1").is(":checked"))) {
	        				
	     			
	                $("#checkbox1").hide();
					$("#checkbox2").hide();
					$("#checkbox3").hide();
					$("#checkbox4").hide();
					
	            }
	                //$("#checkbox2").hide();
	            }
	        
		
		
		
		
	}*/
	
	
	
	$scope.boxDisable=function(){
		
		if(document.getElementById("implant1").checked||document.getElementById("implant2").checked||document.getElementById("implant3").checked||document.getElementById("implant4").checked){
			
			$("#hideshow").show();
			
			
		}
		else{
			
			$("#hideshow").hide();
		}
		
		
		
	}
	
	
$scope.boxDisable1=function(){
		
		if(document.getElementById("implant5").checked||document.getElementById("implant6").checked||document.getElementById("implant7").checked||document.getElementById("implant8").checked){
			
			$("#hideshow1").show();
			
			
		}
		else{
			
			$("#hideshow1").hide();
		}
		
		
		
	}
	
	
	
	
	
	$scope.forgetPassword=function(){
		$http.post("rest/forgetPassword", JSON.stringify($scope.forgetPasswordJson)).then(function(value) {
			alert("Password Changed");
			window.location.reload();
		}, function(value) {
			alert("wrong user name or mobile number");
			window.location.reload();
		})
	}
	
	$scope.noupload=function(){
		if($scope.adminuser!=null && $scope.adminuser!=""){
		
			
			window.location.href="adminpanel.jsp?refno="+$scope.serialnumber;
		}
		else{
			window.location.href="patientpresicriptionform.jsp?refno="+$scope.serialnumber;
		}
	}
	
	$scope.passwordbuttondisable=true;
	$scope.userform={};
	
	$scope.checkoldpass=function(){
		if($scope.password!=$scope.userform.oldpassword){
			$scope.matcholdpass=true;
			$scope.passwordbuttondisable=true;
		}
		else{
			$scope.matcholdpass=false;
			$scope.checkpassword();
		}
		
	}
	
	
	
	$scope.checkpassword=function(){
		
		if($scope.userform.newpassword==$scope.userform.confirmnewpassword  ){
			$scope.passwordbuttondisable=false;
			$scope.matches=false;
		}
		else{
		
			$scope.passwordbuttondisable=true;
			$scope.matches=true;
		}
	}
	
$scope.checkpasswordotheruser=function(){
		
		if($scope.selectedjson.newpassword==$scope.selectedjson.confirmnewpassword  ){
			$scope.passwordbuttondisable=false;
			$scope.matches=false;
		}
		else{
		
			$scope.passwordbuttondisable=true;
			$scope.matches=true;
		}
	}
	
	
	$scope.changepassword=function(){
		$http.post("rest/changepassword", JSON.stringify($scope.userform)).then(function(value) {
			$('#passwordchangedmodal').modal('show');
		}, function(value) {
			
		})
	}
	

	$scope.changepasswordotheruser=function(){
		$scope.selectedjson.oldpassword=$scope.selectedjson.password;
		$('#changepasswordmodal').modal('hide');
		$http.post("rest/changepassword", JSON.stringify($scope.selectedjson)).then(function(value) {
			$('#passwordchangedmodalforotheruser').modal('show');
		}, function(value) {
			
		})
	}
	
	
	$scope.logout=function(){
		
	}
	
	$scope.contactussubmitclick=function(){
		
		
		$http.post("rest/contactus", JSON.stringify($scope.contactusform)).then(function(value) {
			$('#contactusmodal').modal('show');
		}, function(value) {
			
		})
	}
	
	$scope.formdisable=false;
	
	
	
	
	$scope.calculateage=function(){
		
		dob=$scope.formdata.DOB;
		var diff_ms = Date.now() - dob.getTime();
	    var age_dt = new Date(diff_ms); 
	  
	    $scope.formdata.Age= Math.abs(age_dt.getUTCFullYear() - 1970);
	    $scope.formdata.DOB= moment($scope.formdata.DOB).format("YYYY-MM-DD");
	}
	
	$scope.newtabclickedgenform=function(){
		alert("done");
		location.href="#!generatenewform";
		window.location.reload();
	}
	/*$scope.loadform=function(x){
		$scope.formdata.user=$scope.user;
		$scope.formdata.fetchdate=x;
		
		$http.post("rest/return",JSON.stringify($scope.formdata)).then(function(value) {
			$scope.formdata=value.data;
		//	$scope.formdata.DOB=$scope.formdata.DOB.split("T")[0];
			
			$scope.formdisable=true;
			
			$scope.showurl='';
			$scope.showsubmit='None';
			location.href="#!generatenewform";
			$scope.uploadfileurl=$scope.formdata.fileurl;
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}*/
	$scope.loadform=function(x){
		$scope.formdata.user=$scope.user;
		$scope.formdata.fetchdate=x;
		$scope.formdata=x.data;
		
			
			$scope.formdisable=true;
			$scope.wizardForm=false;
			$scope.showurl='';
			$scope.showsubmit='None';
			location.href="#!generatenewform";
			$scope.uploadfileurl=$scope.formdata.fileurl;
			
		
	}
	
	
	
	$scope.loadform1=function(x){
		
		
		$http.post("rest/Showform",x).then(function(value) {
			
			//alert(JSON.stringify(value.data));
			
			$scope.formdata=value.data;
			$scope.formdisable=true;
			$scope.wizardForm=false;
			$scope.showurl='';
			$scope.showsubmit='None';
			location.href="#!generatenewform";
			
	
		//JSON.stringify()
		
		}, function(reason) {
			
		}, function(value) {
			
		})
		
		
	}
	
	
	
	$scope.fetchpreviousdatesforuser=function(){
		$scope.formdata.user=$scope.user;
		$http.post("rest/Previousdatesforuser",JSON.stringify($scope.formdata)).then(function(value) {
			$scope.previousdates=value.data;
			
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}
	
	
	$scope.viewincomplete=function(){
		
		$("#alreadyexists").hide();
		
		$("#createcasemodal").modal("hide");
		
		$http.post("rest/Viewincomplete",JSON.stringify()).then(function(value) {
			$scope.incompletedates=value.data;
			
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}
	
	
	$scope.createcasejava=function(){
		$("#alreadyexists").hide();
		
		if(!$scope.formdata.createcaseid){
			console.log("hdnfjdf");
		}
		else{
		
		
		$http.post("rest/createcaseid1",$scope.formdata.formid+"~"+$scope.formdata.createcaseid).then(function(value) {
			
			//alert(typeof value.data);
			if(value.data==2){
				
				$("#alreadyexists").show();
				
				
			}
			else{
				$("#alreadyexists").hide();
				
				alert("case generated successfully");
				
				$http.post("rest/Mail1",$scope.formdata.formid+"~"+$scope.formdata.createcaseid).then(function(value) {
				console.log("mail sent");
					
					
					//$scope.x=value.data;
				}, function(reason) {
					
				}, function(value) {
					
				})
				
				
				
				
				location.href="#!caseuser";
				window.location.reload();
			}
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
		}
		
		
	}
	
	
	$scope.viewtreatmentongoclick1=function(){
		
		
		$scope.formdata.plannumber=$scope.searchKeywordPatientName1;
		$scope.formdata.searchserialnumber1=$scope.searchKeywordSerialNumber1;
		
		if($scope.formdata.plannumber!=null ){
			
			if($scope.formdata.searchserialnumber1!=null ){
				
				$.ajax({
					

				    type : 'POST',
					data : {plannumber:$scope.formdata.plannumber,caseid:$scope.formdata.searchserialnumber1},
					
					url : 'modifyplan1',
					success : function(responseText) {
						var x=responseText;
						
						alert(x);
						
					}

				  
					 
				});
				
				
			}
			
			else{
				
				alert("Please Enter Case Id");
				
			}
		}
	
		else{
			
			alert("Please Enter Plan Number");
		}
	}
	
	
	
	
	
	
		
	
	//change for alert
	$scope.viewtreatmentongoclick=function(){
		$scope.formdata.user=$scope.user;
	
		$scope.formdata.searchpatientname=$scope.searchKeywordPatientName;
		$scope.formdata.searchserialnumber=$scope.searchKeywordSerialNumber;
	if($scope.formdata.searchpatientname!=null || $scope.formdata.searchserialnumber!=null)
	{
		$http.post("rest/viewtreatmentforuser",JSON.stringify($scope.formdata)).then(function(value) {
			$scope.previousdates=value.data;
			
			if($scope.previousdates.length==1){
				document.getElementById("patientidvalue").value=$scope.previousdates[0].serialnumber;
				//document.getElementById("clicktoviewtreatment").click();
			}
			else{
				window.location.href="#!viewprevious"
			}
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}
	else{
		alert("Enter atleast Name or CaseId !");
	}
	}
	
	
	
	
	
	
	
	
	
	/*$scope.viewtreatmentongoclick=function(){
		$scope.formdata.user=$scope.user;
	
		$scope.formdata.searchpatientname=$scope.searchKeywordPatientName;
		$scope.formdata.searchserialnumber=$scope.searchKeywordSerialNumber;
		window.location.href="#!viewprevious";
		$http.post("rest/viewtreatmentforuser",JSON.stringify($scope.formdata)).then(function(value) {
			$scope.previousdates=value.data;
			
			if($scope.previousdates.length==1){
				document.getElementById("patientidvalue").value=$scope.previousdates[0].serialnumber;
				document.getElementById("clicktoviewtreatment").click();
			}
			else{
				
			}
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}*/
	
	$scope.previousdates={};
	$scope.fetchpreviousdates=function(){
		$scope.formdata.user=$scope.user;
		$http.post("rest/Previousdates",JSON.stringify($scope.formdata)).then(function(value) {
			$scope.previousdates=value.data;
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
		
	}
	$scope.newtabclicked=function(){
		formdata={}; formdisable=false;
		
		location.href="#!generatenewform";
		window.location.reload();
	} 
	
	
	$scope.email=function(){
		
		
		//$scope.formdisable=true;
		//document.getElementById("DOB").value=document.getElementById("DOB").value.toString().split("T")[0];
		
		$scope.formdata.form={};
		 $scope.formdata.timestamp= $scope.timestamp;
		$scope.formdata.form.html=
				"<fieldset disabled='disabled'>" +document.getElementById("sendtobackend").innerHTML+"</fieldset>" ;
		console.log($scope.formdata.form.html);
		
		$http.post("rest/Sendemail", JSON.stringify($scope.formdata)).then(function(value) {
			if($scope.adminuser!=null && $scope.adminuser!=""){
				
				document.getElementById("adminname").value=$scope.adminuser;
			}
			document.getElementById("hiddenfoldername").value=$scope.formdata.PatientName+"_"+$scope.formdata.timestamp;
			//angular.element('#fileuploadmodal').modal('show');
			
			document.getElementById("uploadimages").click();
		}, function(value) {
			
		})
		
	}
	
	$scope.fetchSamples=function(){
		$scope.formdata.user=$scope.user;
		$http.post("rest/Samples",JSON.stringify($scope.formdata)).then(function(value) {
			$scope.samples=value.data;
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}
	
	$scope.showurl='None';
	$scope.dosubmit=function(){
	/* $http.post("rest/hello",JSON.stringify($scope.formdata),{responseType: 'arraybuffer'}).then(function(value) {
		var file = new Blob([value.data], { type: 'application/pdf' });
        saveAs(file, '11529164.pdf');
        
		//$scope.x=value.data;
	}, function(reason) {
		
	}, function(value) {
		
	}) */
	//$scope.data={"data":document.getElementById("sendtobackend").innerHTML};
		/*$scope.formdata();*/
		$scope.formdata.user=$scope.user;
		
		$scope.showurl='';
		$scope.showsubmit='None';
		
		$('#previewmodal').modal('hide');
		$('#loadingmodal').modal('show');
	$http.post("rest/hello",JSON.stringify($scope.formdata)).then(function(value) {
		
		$scope.serialnumber=value.data.serialnumber;
		
		$scope.timestamp=value.data.timestamp;
		document.getElementById("hiddenfoldername").value=$scope.formdata.PatientName+"_"+$scope.timestamp;
		//document.getElementById("fileurl").href="\\\\192.168.85.43/c$/customapp/uploads/"+document.getElementById("hiddenfoldername").value+"/";
		document.getElementById("fileurl").href="http://localhost:8081/new/viewuploads.html?name="+document.getElementById("hiddenfoldername").value;
		document.getElementById("serialnumber").value=$scope.serialnumber;
		if($scope.adminuser!=null && $scope.adminuser!=""){
			
			document.getElementById("adminname").value=$scope.adminuser;
		}
		if($scope.supportuser!=null && $scope.supportuser!=""){
			
			document.getElementById("supportname").value=$scope.supportuser;
		}
		document.getElementById("hiddenfoldername").value=$scope.formdata.PatientName+"_"+$scope.timestamp;
		//angular.element('#fileuploadmodal').modal('show');
		//$scope.email();
		document.getElementById("uploadimages").click();
		
		//$scope.x=value.data;
	}, function(reason) {
		
	}, function(value) {
		
	})
	
	//auto isactive after click submit
	if($scope.draftId!= null){
		$scope.deletedraft($scope.draftId);	
	}
	
	}
	
	
	
	
	
	
	
	$scope.openpreviewmodal=function(){
		$('#uploadview').hide();
		$('#remarksview').show();
		$('#noupload1').hide();
		$('#noupload2').show();
		
		//document.getElementById("modalform").innerHTML=document.getElementById("insideform1").innerHTML;
		//$('#previewmodal').modal('show');
	}
	
	$scope.openpreviewmodal2=function(){
 
		document.getElementById("modalform").innerHTML=document.getElementById("insideform1").innerHTML;
		$('#previewmodal').modal('show');
	
		
	}
	
	
	$scope.openpreviewmodal1=function(){
		$('#remarksview').hide();
		$('#uploadview').show();
		$('#testingSandeep').hide();
		document.getElementById("modalform").innerHTML=document.getElementById("insideform1").innerHTML;
		$('#previewmodal').modal('show');
		
	}
	
	
	$scope.openfileuploadmodal=function(){
		$('#fileuploadmodal').modal('show');
	}
	
	$scope.getsrc=function(id,file){
		var reader = new FileReader();
		reader.onload = function(event) {
			 
			  console.log(event.target.result);
		   document.getElementById(id).src= event.target.result;
		    
		  }
		  // when the file is read it triggers the onload event above.
		  reader.readAsDataURL(file);
	}
	$scope.checkreport=function(sno){
		$.ajax({
		    type: 'HEAD',
		    url: 'users/'+sno+'/Report.pdf',
		success: function() {
		        // page exists
			window.open("users/"+sno+"/Report.pdf");
		},
		error: function() {
		        // page does not exist
			$('#nofilemodal').modal('show');
		}
		});
		
	}
	
	 $scope.setFile = function(element) {
		 
		  $scope.currentFiles = element.files;
		  
		}
	
	 $scope.modifyplan=function(){
		 
			$scope.formdata.user=$scope.user;
			$http.post("rest/lastcaseforuser",JSON.stringify($scope.formdata)).then(function(value) {
				//$scope.formdata=value.data;
				//	$scope.formdata.DOB=$scope.formdata.DOB.split("T")[0];
					
					//$scope.formdisable=true;
					//$scope.showurl='';
					//$scope.showsubmit='None';
					//location.href="#!generatenewform";
					//$scope.uploadfileurl=$scope.formdata.fileurl;
					document.body.innerHTML=document.body.innerHTML+"<form action=\"treatment2.jsp\" method=\"post\"> <input  type=\"hidden\" name=\"patientid\" value=\""+value.data.serialnumber+"\" ><button style=\"display:none\" class=\"btn btn-link\" id=\""+value.data.serialnumber+"\"  type=\"submit\" >View Treatment</button> </form>";
					document.getElementById(value.data.serialnumber).click();
				//$scope.x=value.data;
			}, function(reason) {
				
			}, function(value) {
				
			})
	 }
	 
	 
		
	$scope.adddoctorsubmit=function(){
		
		$scope.formdata.addoctorname=document.getElementById("addoctorname").value;
		$scope.formdata.adphonenumber=document.getElementById("adphonenumber").value;
		$scope.formdata.adcity=document.getElementById("adcity").value;
		$scope.formdata.adfirstname=document.getElementById("adfirstname").value;
		$scope.formdata.adlastname=document.getElementById("adlastname").value;
		$scope.formdata.addoctoremail=document.getElementById("addoctoremail").value;
		$scope.formdata.adcreatedby=document.getElementById("adcreatedby").value;
		
			
		$http.post("rest/Adddoctor", JSON.stringify($scope.formdata)).then(function(value) {
			
			$scope.show=value.data;
			//alert(typeof value.data);
			if(value.data==2){
				
				alert("Doctor with this username already exists")
				
				
			}
			else{
			
			alert("doctor Added");
			location.href="#!generatenewform";}
			
		}, function(value) {
			
		})
			
		
		
		
	}	 
	 
	 $scope.showlatestcase=function(){
		 
			$scope.formdata.user=$scope.user;
			$http.post("rest/lastcaseforuser",JSON.stringify($scope.formdata)).then(function(value) {
				//$scope.formdata=value.data;
				//	$scope.formdata.DOB=$scope.formdata.DOB.split("T")[0];
					
					//$scope.formdisable=true;
					//$scope.showurl='';
					//$scope.showsubmit='None';
					//location.href="#!generatenewform";
					//$scope.uploadfileurl=$scope.formdata.fileurl;
					document.body.innerHTML=document.body.innerHTML+"<form action=\"treatment2.jsp\" method=\"post\"> <input  type=\"hidden\" name=\"patientid\" value=\""+value.data.serialnumber+"\" ><button style=\"display:none\" class=\"btn btn-link\" id=\""+value.data.serialnumber+"\"  type=\"submit\" >View Treatment</button> </form>";
					document.getElementById(value.data.serialnumber).click();
				//$scope.x=value.data;
			}, function(reason) {
				
			}, function(value) {
				
			})
	 }
	 
	$scope.saveasdraft= function(){
		if($scope.draftId!=null)
			{
			$scope.formdata.draftId=$scope.draftId;
		$http.post("rest/saveapiupdate",JSON.stringify($scope.formdata)).then(function(value) {
			
			$scope.formdata=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
		
		}
		else
		{
			$http.post("rest/saveapi",JSON.stringify($scope.formdata)).then(function(value) {
			
			$scope.formdata=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
		}
			//var draftId=$scope.draftId;
			
			
		window.location = 'patientpresicriptionform.jsp';
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	$scope.deletedraftcheck=function(message){
		
		
		
		  var txt;
		  var r = confirm("Confirm delete this Draft !");
		  if (r == true) {
			  $scope.deletedraft(message);
		  } else {
			  $(this).remove();
		  }
		  /*window.location = '#!viewdrafts';*/
		  window.location.reload();
		
		}

	
	$scope.fetchcities=function(){
		
		$http.post("rest/fetchcities",JSON.stringify($scope.formdata)).then(function(value) {
			
			$scope.Cities=value.data;
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
		
	}
	
	
	
	
	
	
	
	
	
$scope.deletedraft=function(draftId){
	$scope.draftId=draftId;
	$scope.formdata.draftId=$scope.draftId;
	$http.post("rest/deletepiupdate",JSON.stringify($scope.formdata)).then(function(value) {
			
			//$scope.formdata=value.data;
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
		
		/*window.location = 'patientpresicriptionform.jsp';*/
	}
	
	



	
	
	
	
	$scope.createcase=function(x){
		
		$scope.formdata.formid=x;
		$("#createcasemodal").modal("show");
		
	
		
		//alert("amit");
	}
	
	$scope.closemodal=function(){
		
		location.href="#!caseuser";
		window.location.reload();
		
	}
	
	
	$scope.fetchdoctornames=function(){
		
		$http.post("rest/fetchdoctornames",JSON.stringify($scope.formdata)).then(function(value) {
			
			$scope.DoctorNames=value.data;
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}
	
	$scope.changepasswordopenmodal=function(selectedjson){
		$scope.selectedjson=selectedjson
		$('#changepasswordmodal').modal('show');
	}
	
	$scope.fetchusers=function(){
	$http.post("rest/fetchusers",JSON.stringify($scope.formdata)).then(function(value) {
			
			$scope.previoususers=value.data;
			
			//$scope.x=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}
	
	$scope.viewdrafts=function(){
		
			
			$http.post("rest/viewdrafts",JSON.stringify($scope.formdata)).then(function(value) {
				$scope.drafts=value.data;
				
				//$scope.x=value.data;
			}, function(reason) {
				
			}, function(value) {
				
			})
	}
	
	$scope.loaddraft=function(x){
	$scope.formdata.user=$scope.user;
	$scope.formdata.fetchdate=x;
	$scope.formdata=x.formData;
	//$scope.userid=x.Id;
	$scope.draftId=x.draftId;
	

	location.href="#!draft";
	$scope.uploadfileurl=$scope.formdata.fileurl;
	$scope.formdata.DOB=$scope.formdata.DOB.split("T")[0];
	
	/*$http.post("rest/return",JSON.stringify($scope.formdata)).then(function(value) {
		$scope.formdata=value.data;
	//	$scope.formdata.DOB=$scope.formdata.DOB.split("T")[0];
		
		
		
		location.href="#!draft";
		$scope.uploadfileurl=$scope.formdata.fileurl;
		
		//$scope.x=value.data;
	}, function(reason) {
		
	}, function(value) {
		
	})*/
	}
	
	$scope.fetchUserGroups=function(){
		$http.post("rest/fetchUserGroup").then(function(value) {
			$scope.userGroups=value.data;
		}, function(reason) {
			
		}, function(value) {
			
		})
	}
	
	
	//wizard form methods 
	$scope.wizardForm=true;
	
	$scope.requiredFlag=true;
	$scope.required=true;
	
	$scope.stepProgressBarJsonArray = [  {
		"id" : "1",
		"data-toggle":"pill", 
		"data-target":"#clinicalInformationTab",
		"currentActive":true,
		"tabId":"clinicalInformationTab",
		"tabContent":"wizardFormHtml/clinicalInformation.html",
		"text":"CLINIC INFORMATION",
		"iconSrc":"icons/clinicInformation.png"
	}, {
		"id" : "2",
		"data-toggle":"pill", 
		"data-target":"#menu1",
		"currentActive":false,
		"tabId":"menu1",
		"tabContent":"wizardFormHtml/patientInformation.html",
		"text":"number2",
		"iconSrc":"icons/patientInformation.png"
	}, {
		"id" : "3",
		"data-toggle":"pill", 
		"data-target":"#menu2",
		"currentActive":false,
		"tabId":"menu2",
		"tabContent":"wizardFormHtml/clinicalData.html",
		"text":"number3",
		"iconSrc":"icons/clinicalData.png"
	}, 
	{
		"id" : "4",
		"data-toggle":"pill", 
		"data-target":"#menu4",
		"currentActive":false,
		"tabId":"menu4",
		"tabContent":"wizardFormHtml/clinicalData1.html",
		"text":"number4",
		"iconSrc":"icons/clinicalData.png"
	},
	{
		"id" : "5",
		"data-toggle":"pill", 
		"data-target":"#menu3",
		"currentActive":false,
		"tabId":"menu3",
		"tabContent":"wizardFormHtml/otherClinicalConditions.html",
		"text":"number5",
		"iconSrc":"icons/otherClinicalConditions.png"
	}
	, {
		"id" : "6",
		"data-toggle":"pill", 
		"data-target":"#suggestedTreatmentInstructionsTab",
		"currentActive":false,
		"tabId":"suggestedTreatmentInstructionsTab",
		"tabContent":"wizardFormHtml/suggestedTreatmentInstructions.html",
		"text":"number6",
		"iconSrc":"icons/treatment.png"
	},
	{
		"id" : "7",
		"data-toggle":"pill", 
		"data-target":"#suggestedTreatmentInstructionsTab1",
		"currentActive":false,
		"tabId":"suggestedTreatmentInstructionsTab1",
		"tabContent":"wizardFormHtml/suggestedTreatmentInstructions1.html",
		"text":"number7",
		"iconSrc":"icons/treatment.png"
	}
	
	
	
	];

	
	$scope.badgeClicked=function(itemId){
		$scope.changeWidthProgressBarWizard(itemId,$scope.stepProgressBarJsonArray);
		$scope.changeTranformScale(itemId,$scope.stepProgressBarJsonArray);
		$scope.changeCurrentActive(itemId,$scope.stepProgressBarJsonArray);
		 
	}
	
	$scope.changeWidthProgressBarWizard=function(itemId){
		var selectedIndex=null;
		for(var i in $scope.stepProgressBarJsonArray ){
			if($scope.stepProgressBarJsonArray[i].id==itemId)selectedIndex=i;
		}
		if(selectedIndex!=null)
		document.getElementById('progressBarFormWizard').style.width=100/($scope.stepProgressBarJsonArray.length-1)*selectedIndex+"%";
	}
	
	$scope.changeTranformScale=function(itemId){
		for(var i in $scope.stepProgressBarJsonArray){
			if($scope.stepProgressBarJsonArray[i].id==itemId){
				$scope.changeScale($scope.stepProgressBarJsonArray[i].id,2);
			}
			else{
				$scope.changeScale($scope.stepProgressBarJsonArray[i].id,1.2);
			}
		}
	}
	$scope.changeScale=function(elementId,scale){
		document.getElementById(elementId+"span").style.WebkitTransform = "scale("+scale+")"; 
	  	document.getElementById(elementId+"span").style.msTransform = "scale("+scale+")"; 
	    document.getElementById(elementId+"span").style.transform = "scale("+scale+")";
	}
	$scope.changeCurrentActive=function(itemId){
		for(var i in $scope.stepProgressBarJsonArray){
			if($scope.stepProgressBarJsonArray[i].id==itemId){
				$scope.stepProgressBarJsonArray[i].currentActive=true;
			}
			else{
				$scope.stepProgressBarJsonArray[i].currentActive=false;
			}
		}
	}
	
	
	
	
	$scope.nextprev1=function(flag)
	{   
		var currentActiveIndex=null;
		var futureActiveIndex=null;
		var submitFlag=false;
		for(var i in $scope.stepProgressBarJsonArray){
			if($scope.stepProgressBarJsonArray[i].currentActive==true){
				currentActiveIndex=i;
			}
		}
		
		if(currentActiveIndex!=null){
			currentActiveIndex=Number(currentActiveIndex);
		if(flag==1){ 
			if(currentActiveIndex<$scope.stepProgressBarJsonArray.length-1)
				futureActiveIndex=currentActiveIndex+1
			else if(currentActiveIndex==$scope.stepProgressBarJsonArray.length-1){
				futureActiveIndex=null;
				submitFlag=true;
			}
				//futureActiveIndex=0;
		}
		else if (flag==0){
			
			if(currentActiveIndex>0)
				futureActiveIndex=currentActiveIndex-1
			else if(currentActiveIndex==0)
				futureActiveIndex=null
				//futureActiveIndex=$scope.stepProgressBarJsonArray.length-1;
		}
		}
		if(futureActiveIndex!=null){
			$scope.stepProgressBarJsonArray[currentActiveIndex].currentActive=false;
		$scope.stepProgressBarJsonArray[futureActiveIndex].currentActive=true;
		$scope.badgeClicked($scope.stepProgressBarJsonArray[futureActiveIndex].id,$scope.stepProgressBarJsonArray);
		
		$timeout(function(){ 
			  $scope.$apply()
			  document.getElementById($scope.stepProgressBarJsonArray[futureActiveIndex].id).click(); 
			},0)
		
		}
		else if (submitFlag){
			$scope.openfileuploadmodal();
		}
		
	}
	
	
	var currentActiveIndex=null;
	
	
	
	

	$scope.nextprev=function(flag)
	{   
		
		
		
		
		
		
		var futureActiveIndex=null;
		var submitFlag=false;
		for(var i in $scope.stepProgressBarJsonArray){
			if($scope.stepProgressBarJsonArray[i].currentActive==true){
				currentActiveIndex=i;
			}
		}
		
	var stat=true;	
if(currentActiveIndex==1){
			
			
			if(document.getElementById("PatientName").value==""){
				
				alert("Please enter patient name");
				$("#PatientName").prop('required',true);
				
				stat=false;
				
			}
			else if(document.getElementById("Age").value=="")
				{
				alert("please Enter Age")
				stat=false;	
				
				}
			else if ($('input[name="Gender"]:checked').length == 0) {
				alert("please Select Gender");
				stat=false;	
				
				}
			else {
				
				
				
			}
			
			
			
		}







if(currentActiveIndex==2){
			
			
			if($scope.formdata.patientCategoryCode==undefined){
				alert("please enter patient category");
				
				stat=false;
			}
			else if(document.getElementById("ChiefComplaint").value==""){
				
				alert("Please Enter Chieg Complaint");
				
				stat=false;
				
			}
			else if ($('input[name="Crowding"]:checked').length == 0) {
				alert("please Select Upper Crowding");
				stat=false;	
				
				}
			else if ($('input[name="Spacing"]:checked').length == 0) {
				alert("please Select Upper Spacing");
				stat=false;	
				
				}
			else if ($('input[name="Rotation"]:checked').length == 0) {
				alert("please Select Upper Rotation");
				stat=false;	
				
				}
			else if ($('input[name="ArchForm"]:checked').length == 0) {
				alert("please Select Upper ArchForm");
				stat=false;	
				
				}
			else if(document.getElementById("MalformationOfTeeth").value==""){
				
				alert("Please Enter Upper Malformation of teeth");
				
				stat=false;
				
			}
			
			else if(document.getElementById("ToothNumber").value==""){
				
				alert("Please Enter Upper Tooth Number");
				
				stat=false;
				
			}
			else if ($('input[name="MissingTeeth"]:checked').length == 0) {
				alert("please Select Upper Missing Teeth");
				stat=false;	
				
				}
			
			else if($scope.formdata.MissingTeeth=='Yes'){
				
				if(document.getElementById("MissingToothNumber").value==""){
					
					alert("please enter missing tooth number in upper");
					stat=false;
				}
				
			else if($('input[name="MissingTeethCure"]:checked').length == 0){
					
					alert("please Select maintain space or close space in upper");
					stat=false;	
				}
			
				
			}
			
			
			
			
			
			
			else if ($('input[name="LCrowding"]:checked').length == 0) {
				alert("please Select Lower Crowding");
				stat=false;	
				
				}
			else if ($('input[name="LSpacing"]:checked').length == 0) {
				alert("please Select Lower Spacing");
				stat=false;	
				
				}
			else if ($('input[name="LRotation"]:checked').length == 0) {
				alert("please Select Lower Rotation");
				stat=false;	
				
				}
			else if ($('input[name="LArchForm"]:checked').length == 0) {
				alert("please Select Lower ArchForm");
				stat=false;	
				
				}
			else if(document.getElementById("LLMalformationOfTeeth").value==""){
				
				alert("Please Enter Lower Malformation of teeth");
				
				stat=false;
				
			}
			
			else if(document.getElementById("LLToothNumber").value==""){
				
				alert("Please Enter Lower Tooth Number");
				
				stat=false;
				
			}
			else if ($('input[name="LMissingTeeth"]:checked').length == 0) {
				alert("please Select Lower Missing Teeth");
				stat=false;	
				
				}
			
			else if($scope.formdata.LMissingTeeth=='Yes'){
				
				if(document.getElementById("LMissingToothNumber").value==""){
					
					alert("please enter missing tooth number in Lower");
					stat=false;
				}
				
			else if($('input[name="LMissingTeethCure"]:checked').length == 0){
					
					alert("please Select maintain space or close space in Lower");
					stat=false;	
				}
			
				
			}
			
			
			
			
			
			
			
			
			
		}





if(currentActiveIndex==3){
	
	
	
	if($('input[name="MolarRelation"]:checked').length == 0){
		
		alert("please Select Inter Arch Molar Relation");
		stat=false;	
	}
	else if($('input[name="CaninRelation"]:checked').length == 0){
			
			alert("please Select Inter Arch Canine Relation");
			stat=false;	
		}
	else if($('input[name="Proclined"]:checked').length == 0){
		
		alert("please Select Proclined");
		stat=false;	
	}
	
	else if($('input[name="Retroclined"]:checked').length == 0){
		
		alert("please Select Retroclined");
		stat=false;	
	}
	else if($('input[name="Overjet"]:checked').length == 0){
		
		alert("please Select Overjet");
		stat=false;	
	}
	else if($('input[name="Overbite"]:checked').length == 0){
		
		alert("please Select Overjet");
		stat=false;	
	}
	else if($('input[name="MidlinesUpper"]:checked').length == 0){
		
		alert("please Select Midline / Upper");
		stat=false;	
	}
	else if($('input[name="MidlinesLower"]:checked').length == 0){
		
		alert("please Select Midline / Lower");
		stat=false;	
	}
	else if(document.getElementById("MidlinesSpecify").value==""){
		
		alert("please Specify (mm) in upper or lower in midlines");
		stat=false;
	}
	
	else if($('input[name="LMolarRelation"]:checked').length == 0){
		
		alert("please Select Inter Arch Molar Relation");
		stat=false;	
	}
	else if($('input[name="LCaninRelation"]:checked').length == 0){
			
			alert("please Select Inter Arch Canine Relation");
			stat=false;	
		}

	else if($('input[name="LCrossBite"]:checked').length == 0){
		
		alert("please Select Inter Arch Crossbite");
		stat=true;	
	}
	
	
	
}
if(currentActiveIndex==4){
	
	
if(document.getElementById("DentalTreatment").value==""){
		
		alert("please Enter any other clinical condtions");
		stat=false;
	}
	
	
	
}
if(currentActiveIndex==5){
	
	
	 if($('input[name="Treat"]:checked').length == 0){
			
			alert("please Specify Treat");
			stat=false;	
		}
	 else if($('input[name="CorrectCrowdingby"]:checked').length == 0){
				
				alert("please Specify Correct Crowding By");
				stat=false;	
			}
	 else if($('input[name="CorrectSpacing"]:checked').length == 0){
			
			alert("please Specify Correct Spacing");
			stat=false;	
		}
	 else if($('input[name="CorrectRotation"]:checked').length == 0){
			
			alert("please Specify Correct Rotation");
			stat=false;	
		}
		
	
	
}
if(currentActiveIndex==6){
	
	
	if($('input[name="ArchFormCorrection"]:checked').length == 0){
		
		alert("please Specify Arch Form");
		stat=false;	
	}
	 else if($('input[name="OverJetCorrection"]:checked').length == 0){
			
			alert("please Specify Overjet");
			stat=false;	
		}
	 else if($('input[name="OverbiteCorrection"]:checked').length == 0){
			
			alert("please Specify Overbite");
			stat=false;	
		}
	 else if($('input[name="PosteriorCrossBiteCorrection"]:checked').length == 0){
			
			alert("please Specify Posterior Crossbite");
			stat=false;	
		}
	
	 else if($('input[name="CanineRelationCorrection"]:checked').length == 0){
			
			alert("please Specify Canine Relation");
			stat=false;	
		}
	
	 else if($('input[name="MolarRelationCorrection"]:checked').length == 0){
			
			alert("please Specify Molar Relation");
			stat=false;	
		}
	
	 else if($('input[name="IncisorRelationCorrection"]:checked').length == 0){
			
			alert("please Specify Incisor Relation");
			stat=false;	
		}
	 else if(document.getElementById("SpecialInstruction").value==""){
			
			alert("please Specify Any special instruction");
			stat=false;
		}
	
}






		
		if(stat){

		if(currentActiveIndex!=null){
			currentActiveIndex=Number(currentActiveIndex);
		if(flag==1){ 
			if(currentActiveIndex<$scope.stepProgressBarJsonArray.length-1)
				futureActiveIndex=currentActiveIndex+1
			else if(currentActiveIndex==$scope.stepProgressBarJsonArray.length-1){
				futureActiveIndex=null;
				submitFlag=true;
			}
				//futureActiveIndex=0;
		}
		else if (flag==0){
			
			if(currentActiveIndex>0)
				futureActiveIndex=currentActiveIndex-1
			else if(currentActiveIndex==0)
				futureActiveIndex=null
				//futureActiveIndex=$scope.stepProgressBarJsonArray.length-1;
		}
		}
		if(futureActiveIndex!=null){
			$scope.stepProgressBarJsonArray[currentActiveIndex].currentActive=false;
		$scope.stepProgressBarJsonArray[futureActiveIndex].currentActive=true;
		$scope.badgeClicked($scope.stepProgressBarJsonArray[futureActiveIndex].id,$scope.stepProgressBarJsonArray);
		
		$timeout(function(){ 
			  $scope.$apply()
			  document.getElementById($scope.stepProgressBarJsonArray[futureActiveIndex].id).click(); 
			},0)
		
		}
		else if (submitFlag){
			$scope.openfileuploadmodal();
		}
		}
		
	}
			
			$scope.clickCurrentActive=function(){
				for(var i in $scope.stepProgressBarJsonArray){
					if($scope.stepProgressBarJsonArray[i].currentActive==true){
						$scope.changeTranformScale($scope.stepProgressBarJsonArray[i].id, $scope.stepProgressBarJsonArray);
					}
				}
			}
			
			$scope.clickCurrentActiveInNgRepeat=function(itemId){
				$scopechangeTranformScale(itemId, $scope.stepProgressBarJsonArray);
			}
			

			
			//wizard form methods ends
			
			
			
		})

