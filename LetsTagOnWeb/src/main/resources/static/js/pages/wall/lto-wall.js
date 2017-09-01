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

        });