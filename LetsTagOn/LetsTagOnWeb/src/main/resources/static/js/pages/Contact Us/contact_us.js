var contactUsModule = angular.module('contact us',[]);
/**
	contact us controller
 **/
contactUsModule.controller('contactuscontroller',function($http, $scope, $rootScope, $location, $timeout){
	$scope.customer = {};
	
	$scope.getContactUsPage = function(){
		$location.path('/contact us')
	}
	$scope.contactUs = function(submitted) {
		$scope.customer.userName =	$scope.user.name;
		$scope.customer.emailAddress =	$scope.user.email;
		$scope.customer.summary =	$scope.user.message;
		$('#loading-indicator').show();
		
        $http({
            url: 'contact/us',
            dataType: 'json',
            method: 'POST',
            data: $scope.customer,
            headers: {
                "Content-Type": "application/json"
            }
        }).success(function(response) {
        	$('#loading-indicator').hide();
            if (response.error == null) {
            	$scope.user = '';
            	$('#successmessage').css({
                    display: "block",
                });
            }
        }).error(function(error) {
            console.log("error while getting notification list for the userID" + $rootScope.userId);
        });
    }
});