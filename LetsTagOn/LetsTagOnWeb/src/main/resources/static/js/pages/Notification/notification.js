/*
 * JS File For managing Notification
 * UserNotificationController - To get Unread notifications
 */
var notification = angular.module("notificationModule", ['infinite-scroll']);
notification.controller('UserNotificationController', function($http, $scope, $rootScope, $location, $timeout) {
    if ($rootScope.authenticated) {
        $scope.unreadNotificationList = [];
        $rootScope.listSize = 10;
        $scope.notificationLength = "";

        // ================================== Code to get all unread notifications ================================================
        var ready = true;
        setInterval(function() {
            $scope.getAllUnreadNotifications();
        }, 600 * 1000);
        $scope.pageSize = 0;
        $scope.getAllUnreadNotifications = function() {

            $http({
                url: '/notification/party/' + $rootScope.userId + '/isRead',
                dataType: 'json',
                method: 'GET',
                headers: {
                    "Content-Type": "application/json"
                },
                params: {
                    "size": $rootScope.listSize,
                    "page": $scope.pageSize
                }
            }).success(function(response) {
                response.searchResult.forEach(function(response) {
                    try {
                        response.params = JSON.parse(response.params);
                        $scope.unreadNotificationList.push(response);
                    } catch (e) {

                    }
                });
                $scope.notificationLength = $scope.unreadNotificationList.length;
                ready = true;
            }).error(function(error) {

                console.log("error while getting profile details of user with id:" + $rootScope.userId);
            });

        };

        $("#notification-scroll-container").on("scroll", function(e) {
            e.preventDefault();
            if (ready) {
                ready = false;
                $scope.getNextNotificationList();
            }
        });

        $scope.getNextNotificationList = function() {
            $scope.pageSize = $scope.pageSize + 1;
            $scope.getAllUnreadNotifications();

        };

        // ================================================ Function to mark one notification as read ==============================================================
        $scope.markNotificationAsRead = function(notification) {
            if (!$("#notification" + notification.id).hasClass("lto-pending-notification-drop-row-read")) {
                $http({
                    url: '/notification/notificationId/' + notification.id + '/markNotification',
                    dataType: 'json',
                    method: 'PUT',
                    headers: {
                        "Content-Type": "application/json"
                    }
                }).success(function(response) {
                    if (response.error == null) {
                        if (!$(".lto-pending-notification-user-dropdownList").hasClass("hide")) {
                            $(".lto-pending-notification-user-dropdownList").addClass("block");
                        }

                        $scope.unreadNotificationList.forEach(function(notification) {
                            if (notification.id == response.data.id) {
                                var index = $scope.unreadNotificationList.indexOf(notification);
                                if (index > -1) {
                                	$scope.unreadNotificationList.splice(index, 1);
                                	$("#notification" + response.data.id).addClass("ng-hide");
                                	$scope.notificationLength = $scope.unreadNotificationList.length - 1;
                                    if ($scope.notificationLength == 0) {
                                        $("#notificationLength").hide();
                                    }
                                }
                            }
                        });
                    }
                }).error(function(error) {
                    console.log("error while getting profile details of user with id:" + $rootScope.userId);
                });
            }

        };
        // ================================================ End =============================================================================
        //To be cleanedup
        $scope.actionOnNotification = function(notification) {
            if (notification.type == "ConnectionRequestEvent" || notification.type == "ConnectionAcceptEvent") {
                $location.path("/profile/user/" + notification.params.senderUserId);
            } else if (notification.type == "OpportunityInviteEvent" || notification.type == "OppAppStatusChangeEvent" || notification.type == "OppAttChangeEvent" || notification.type == "OppFeedBackEvent" || notification.type == "OppAppSentEvent") {
                $location.path("/view/opportunity/" + notification.params.opportunityId);
            } else {
                $location.path("/view/notifications");
            }
        };

        //============================= Get All Notifications ==============================
        $scope.allNotificationList = [];
        $scope.viewAllNotificationOfUser = function() {

            $http({
                url: '/notification/party/' + $rootScope.userId,
                dataType: 'json',
                method: 'GET',
                headers: {
                    "Content-Type": "application/json"
                }
            }).success(function(response) {
                $location.path("/view/notifications");
                response.searchResult.forEach(function(response) {
                    try {
                        response.params = JSON.parse(response.searchResult.params);
                    } catch (e) {

                    }
                });
                $scope.allNotificationList = response.searchResult;
            }).error(function(error) {
                console.log("error while getting notification list for the userID" + $rootScope.userId);
            });

        };

        // ============================================ Pagination for View all notifications ================================
        $scope.notifications = [];
        $scope.totalNotifications = 0;
        $scope.notificationsPerPage = 5; // this should match
        // however
        // many results your API puts on
        // one page
        getResultsPage(1);

        $scope.pagination = {
            current: 1
        };

        $scope.pageChanged = function(newPage) {
            getResultsPage(newPage);
        };

        function getResultsPage(pageNumber) {
            // this is just an example, in reality this stuff should
            // be in a service
            $http
                .get(
                    '/notification/party/' + $rootScope.userId + '?size=' +
                    $scope.notificationsPerPage +
                    '&page=' + (pageNumber - 1))
                .then(
                    function(result) {
                        result.data.searchResult.forEach(function(response) {
                            try {
                                response.params = JSON.parse(response.params);
                            } catch (e) {

                            }
                        });
                        $scope.notifications = result.data.searchResult;
                        $scope.totalNotifications = result.data.totalCount;
                    });
        }

        $scope.pagination = {
            current: 1
        };
    }

    // ============================================== Mark all notification as read =========================================================

    $scope.markAllNotificationAsRead = function() {
        $http({
            url: '/notification/markAll/read/party/' + 0,
            dataType: 'json',
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            }
        }).success(function(response) {
            if (response.error == null) {
                $scope.unreadNotificationList = [];
            }
        }).error(function(error) {
            console.log("error while getting notification list for the userID" + $rootScope.userId);
        });
    };

    // =========================================== End ===================================================================================
});