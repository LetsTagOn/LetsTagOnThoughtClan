
/* 
 * JS File which inclused controllers for Opportunity - create,edit,view
 */
var wallModule = angular.module('wallModule', []);

wallModule
.controller(
		'WallController',
		function($http, $scope, $rootScope, $location, $timeout) {

			// fetch posts of opportunity

			$scope.postsList = [];
			$scope.postListSize = 5;
			$scope.postCount = 0;
			getOppPostsList();

			function getOppPostsList() {
				$http({
					url: '/post/findDirectedAndOpportunityBasedPostsForUser/0',
					dataType: 'json',
					method: 'GET',
					params: {
						size: $scope.postListSize
					},
					headers: {
						"Content-Type": "application/json"
					}
				})
				.success(
						function(response) {
							$scope.postsList = [];
							$scope.postsList = response.searchResult;
							$scope.totalPostsCount = response.totalCount;
						})
						.error(
								function(error) {
									console
									.log("error while getting profile details of user with id:" +
											$rootScope.userId);
								});
			}

			$scope.getNextPostList = function() {
				$scope.postListSize = $scope.postListSize + 5;
				getOppPostsList();
			};
			// getSuggestionlist();
			$scope.fetchConnectionList = function() {
				$scope.postCount = $scope.postCount +
				$scope.postListSize;
				getOppPostsList();
			};

			// view post in detail
			
			$scope.statusList = [];
			$scope.statusCount = 0;
			fetchStatusList();
			function fetchStatusList(){
				alert('fetch');
				$http({
					url: '/status/findAllConnectionStatus/0',
					dataType: 'json',
					method: 'GET',
					params: {
						size: $scope.postListSize
					},
					headers: {
						"Content-Type": "application/json"
					}
				})
				.success(
					function success(){
						$scope.statusList = [];
						$scope.statusList = response.searchResult;
						$scope.totalStatusCount = response.totalCount;
					}
				)
				.error(
					function error(){
						console.log("error while getting status:" + $rootScope.userId);
					}	
				)
			}
			
			$scope.postStatus = function(){
				console.log('in post status')
				var post = new Object();
				post.content = $("#content").val();
				$http({
					url: '/status/save',
					dataType: 'json',
					method: 'POST',
					data: post,
					headers: {
						"Content-Type": "application/json"
					}
				}).success(function(response) {
					if (response.error == null) {
						console.log('error in success!');
					} else {
						console.log('success!');
					}
				}).error(function(error) {
					console.log('error!');
				});
			};

		});