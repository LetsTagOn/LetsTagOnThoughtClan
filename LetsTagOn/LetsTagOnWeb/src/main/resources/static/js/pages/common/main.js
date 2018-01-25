/*
 * JS File to authenticate user 
 * Main application controller MainController
 * 
 */
var letsTagOn = angular.module('main', ['ui.bootstrap', 'ngMessages', 'bootstrapModal', 'completeProfile']);
letsTagOn.controller('MainController', function($rootScope, $http, $location, $route, $scope, $timeout) {

    //TO get baseUrl
    var url = $location.absUrl();
    var arr = url.split("#");
    $rootScope.baseUrl = arr[0];

    $rootScope.unrestrictedUrls = ["/resetPassword", "/search", ];

    var authenticate = function(callback) {
        $http.get('user').success(function(data) {
            if (data.name) {
                $rootScope.authenticated = true;
                $('#modalLogin').modal('hide');
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                $rootScope.authenticated = true;
                sessionStorage.authenticated = true;
                $rootScope.logout = true;
                $scope.error = false;
                $rootScope.userId = data.principal.id;
                $rootScope.userObj = data.principal.user;
                $rootScope.profilePicture = data.principal.user.profilePicture;
            } else {
                $rootScope.authenticated = false;
                sessionStorage.authenticated = false;
                //check if url allowed though not authenticated

                var allowed = false;
                for (i in $rootScope.unrestrictedUrls) {
                    var url = $rootScope.unrestrictedUrls[i];
                    if ($location.absUrl().indexOf(url) >= 0) {
                        allowed = true;
                        break;
                    }
                }

                if (!allowed)
                    $location.path("/welcome");
            }
            callback && callback();
        }).error(function() {
            $rootScope.authenticated = false;
            sessionStorage.authenticated = false;
            callback && callback();
        });
    };
    authenticate();
    $scope.credentials = {};
    $scope.login = function() {
        $http.post('login', $.param($scope.credentials), {
            headers: {
                "content-type": "application/x-www-form-urlencoded"
            }
        }).success(function(data) {
            authenticate(function() {
                if ($rootScope.authenticated) {
                	$location.path("/"); // Sourabh: added to redirect to home page on successful login
                    $scope.error = false;
                    $scope.submitted = false;
                    $rootScope.showWallHeader = true;
                } else {
                 // Sourabh: Fixed issue of rerouting to home page. This was tryng to relocate to welcome page. Hence commented.   $location.path("/");
                    $scope.error = true;
                    $scope.authenticationError = "Invalid credentails";
                }
            });
        }).error(function(data) {
            $location.path("/welcome");
            $scope.error = true;
            $rootScope.authenticated = false;
            sessionStorage.authenticated = false;
        });
    };
    $scope.logout = function() {
        $http.post('logout', {}).success(function() {
            $scope.redirect("/welcome");
            $route.reload();
        }).error(function(data) {
            $scope.redirect("/welcome");
        });

    };


    $scope.redirect = function(redirectLocation) {
        $location.path(redirectLocation);
    };

    $scope.getProfileImage = function(userImage) {
        return $rootScope.baseUrl + "userProfile/getProfilePic?key=" + userImage;
    };

    $scope.getBannerImage = function(bannerImage) {
        return "https://s3-us-west-2.amazonaws.com/ltoopporimages/" + bannerImage;
    };

    $scope.showAllEvents = function() {
        if (!$rootScope.authenticated) {
            $scope.redirect("/search/opportunity/");
        }
    };

    /*(function () { 
        var minutes = true; // change to false if you'd rather use seconds
        var interval = minutes ? 60000 : 1000; 
        var IDLE_TIMEOUT = 30; // 10 minutes is the idle timeout
        var idleCounter = 0;

        document.onclick = document.onmousemove = document.onkeypress = function () {
            idleCounter = 0;
        };

        window.setInterval(function () {
            if (++idleCounter >= IDLE_TIMEOUT) {
                if($rootScope.authenticated){
            	 $scope.logout(); 
                }
            }
        }, interval);
    }());*/
});
letsTagOn.directive('noSpecialChar', function() {
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function(scope, element, attrs, modelCtrl) {
            modelCtrl.$parsers.push(function(inputValue) {
                if (inputValue == null)
                    return ''
                cleanInputValue = inputValue.replace(/[^a-z0-9 .\-]/gi, '');
                if (cleanInputValue != inputValue) {
                    modelCtrl.$setViewValue(cleanInputValue);
                    modelCtrl.$render();
                }
                return cleanInputValue;
            });
        }
    }
});