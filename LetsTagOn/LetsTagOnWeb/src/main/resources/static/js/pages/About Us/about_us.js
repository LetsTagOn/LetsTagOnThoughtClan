var aboutUsModule = angular.module("about us", []);

/**
 * Controller for About Us Module
 * 
 **/

aboutUsModule.controller("aboutcontroller", function(
	$scope,
	$location,
	$anchorScroll
) {
	$scope.getAboutUsPage = function() {
		$location.path("/about us");
		$anchorScroll("top");
	};
});
