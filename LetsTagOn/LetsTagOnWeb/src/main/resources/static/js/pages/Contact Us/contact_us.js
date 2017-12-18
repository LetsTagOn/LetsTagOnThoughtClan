var contactUsModule = angular.module('contact us',[]);
/**
	contact us controller
 **/
contactUsModule.controller('contactuscontroller',function($scope,$location){
	$scope.getContactUsPage = function(){
		$location.path('/contact us')
	}
});