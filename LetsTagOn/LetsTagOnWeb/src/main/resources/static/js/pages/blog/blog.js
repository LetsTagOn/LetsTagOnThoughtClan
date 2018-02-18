var blogsModule = angular.module("blogs", []);

/**
 * Controller for Blogs Module
 * 
 **/

blogsModule.controller("blogcontroller", function(
	$scope,
	$location,
	$anchorScroll
) {
	$scope.getBlogsPage = function() {
		$location.path("/blog");
		$anchorScroll("blog-top");
	};
});
