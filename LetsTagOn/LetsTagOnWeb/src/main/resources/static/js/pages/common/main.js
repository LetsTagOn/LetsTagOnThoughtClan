/*
 * JS File to authenticate user 
 * Main application controller MainController
 * 
 */
var letsTagOn = angular.module("main", [
    "ui.bootstrap",
    "ngMessages",
    "bootstrapModal",
    "completeProfile"
]);
letsTagOn.controller("MainController", function(
    $rootScope,
    $http,
    $location,
    $route,
    $scope,
    $timeout
) {
    //TO get baseUrl
    var url = $location.absUrl();
    var arr = url.split("#");
    $rootScope.baseUrl = arr[0];

    $rootScope.unrestrictedUrls = ["/resetPassword", "/search", "/verifyToken"];

    var authenticate = function(callback) {
        $http
            .get("user")
            .success(function(data) {
                // console.log("user data received: ", data);
                if (data.name) {
                    $rootScope.authenticated = true;
                    $("#modalLogin").modal("hide");
                    $("body").removeClass("modal-open");
                    $(".modal-backdrop").remove();

                    sessionStorage.authenticated = true;
                    $rootScope.logout = true;
                    $scope.error = false;
                    console.log("userObj received: ", data);
                    $rootScope.userId = data.id;
                    $rootScope.userObj = data;
                    $rootScope.profilePicture =
                        data.profilePicture;
                } else {
                    $rootScope.authenticated = false;
                    sessionStorage.authenticated = false;
                    //check if url allowed though not authenticated
                    // console.log("checking if url allowed");
                    var allowed = false;
                    for (i in $rootScope.unrestrictedUrls) {
                        var url = $rootScope.unrestrictedUrls[i];
                        // console.log("URL TO CHECK: ", url);
                        // console.log("ABSURL: ", $location.absUrl());
                        if ($location.absUrl().indexOf(url) >= 0) {
                            // console.log("URL FOUND IN ABSURL");
                            if (
                                $location.absUrl().indexOf("/verify") >= 0 ||
                                $location.absUrl().indexOf("/reset") >= 0
                            ) {
                                // console.log("FOUND VERIFY IN URL");
                                if (
                                    $rootScope.$$listenerCount
                                        .$locationChangeStart == 1
                                ) {
                                    // console.log("locationChangeStart IS 1 ");
                                    // console.log(
                                    //     "LISTERNERCOUNT",
                                    //     $rootScope.$$listenerCount
                                    // );
                                    $rootScope.$$listenerCount.$locationChangeStart = 2;
                                    $route.reload();
                                } else {
                                    // console.log("locationChangeStart IS NOT 1");
                                    // console.log(
                                    //     "LISTERNERCOUNT",
                                    //     $rootScope.$$listenerCount
                                    // );
                                    $rootScope.$$listenerCount.$locationChangeStart = 1;
                                }
                            }
                            allowed = true;
                            break;
                        }
                    }

                    if (!allowed) $location.path("/welcome");
                }
                callback && callback();
            })
            .error(function() {
                $rootScope.authenticated = false;
                sessionStorage.authenticated = false;
                callback && callback();
            });
    };
    authenticate();
    $scope.credentials = {};
    $scope.login = function() {
        // console.log('in login function');
        $scope.error = false;
        $scope.submitted = false;
        
        $http
            .post("login", $.param($scope.credentials), {
                headers: {
                    "content-type": "application/x-www-form-urlencoded"
                }
            })
            .success(function(data) {
                authenticate(function() {
                    // console.log("authenticated: ?", $rootScope.authenticated);
                    if ($rootScope.authenticated) {
                        if ($location.path() === "/welcome") {
                            // console.log("user data received on login: ", user);
                            //redirection to search page should happen provided login is happening from landing page
                            $location.path("/search/opportunity/");
                            // $location.path("/view/connection/user/167");
                        } else {
                            //need to reload the page so that features which were hidden when user was not authenticated
                            //can be shown once the user is authenticated
                            $route.reload();
                            // Sourabh: added to redirect to home page on successful login
                            $rootScope.$emit("ApplyForJob"); //ensure the application for the job is done on successful login
                        }
                        $scope.error = false;
                        $scope.submitted = false;
                        $rootScope.showWallHeader = true;
                    } else {
                        // Sourabh: Fixed issue of rerouting to home page. This was tryng to relocate to welcome page. Hence commented.   $location.path("/");
                        $scope.error = true;
                        $scope.authenticationError = "Invalid credentials";
                    }
                });
            })
            .error(function(data) {
                $location.path("/welcome");
                $scope.error = true;
                $rootScope.authenticated = false;
                sessionStorage.authenticated = false;
            });
    };
    $scope.logout = function() {
        $http
            .post("logout", {})
            .success(function() {
                $scope.redirect("/welcome");
                $route.reload();
            })
            .error(function(data) {
                $scope.redirect("/welcome");
            });
    };

    $scope.redirect = function(redirectLocation) {
        $location.path(redirectLocation);
    };

    $scope.getProfileImage = function(userImage) {
        return (
            $rootScope.baseUrl + "userProfile/getProfilePic?key=" + userImage
        );
    };

    $scope.getBannerImage = function(bannerImage) {
        return (
            "https://s3-us-west-2.amazonaws.com/ltoopporimages/" + bannerImage
        );
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
letsTagOn.directive("noSpecialChar", function() {
    return {
        require: "ngModel",
        restrict: "A",
        link: function(scope, element, attrs, modelCtrl) {
            modelCtrl.$parsers.push(function(inputValue) {
                if (inputValue == null) return "";
                cleanInputValue = inputValue.replace(/[^a-z0-9 .\-]/gi, "");
                if (cleanInputValue != inputValue) {
                    modelCtrl.$setViewValue(cleanInputValue);
                    modelCtrl.$render();
                }
                return cleanInputValue;
            });
        }
    };
});
