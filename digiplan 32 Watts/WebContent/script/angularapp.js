var app=angular.module("myapp", ['ngRoute','ngFileUpload','ngAnimate']);


app.config(function($routeProvider,$locationProvider) {
	
	
	$routeProvider
    .when("/", {
    	templateUrl : "views/NewFile2.html",
    	
    	})
    	
    	.when("/red", {
        
    })
    
    .when("/blue", {
        templateUrl : "blue.htm"
    }).when("/contactus", {
        templateUrl : "views/contactus.html"
    }).when("/adddoctor", {
        templateUrl : "views/adddoctor.html"
    }).when("/changepassword", {
        templateUrl : "views/changepassword.html"
    }).when("/viewdrafts", {
        templateUrl : "views/viewdrafts.html"
    }).when("/draft", {
        templateUrl : "views/NewFile2.html"
    }).when("/viewprevious", {
        templateUrl : "views/viewpreviousforuser.html"
    }).when("/viewtreatment", {
        templateUrl : "views/viewtreatmentforuser.html"
    }).when("/viewtreatment1", {
        templateUrl : "views/modifyplan.html"
    }).when("/generatenewform", {
        templateUrl : "views/NewFile2.html"
   }).when("/samples", {
        templateUrl : "views/viewsample.html"
    }).when("/caseuser", {
        templateUrl : "views/viewincomplete.html"
    })
    
    
    
    ;
});

app.filter('Stringdate', function() {
    return function(x) {
        
        
        
        return x.split("_")[0];
    };
});

app.filter('PatientDOB', function() {
    return function(x) {
        if(x==null){return x;}
        
        if(x.toString().indexOf("T")!=-1){return x.split("T")[0];}
        return x;
    };
});

app.filter('PatientDOBfilter', function() {
    return function(x) {
        if(x==null){return x;}
        else{ y=x.toString().split("-"); 
        return y[2]+"-"+y[1]+"-"+y[0];
        }
      
    };
});

