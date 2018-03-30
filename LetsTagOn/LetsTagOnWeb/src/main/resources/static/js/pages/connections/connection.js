var connection = angular.module("connectionModule", []);

connection.controller("ConnectionController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    var url = window.location.href.split("/user/");
    var userId = url[1];
    $scope.userList = [];
    $scope.viewUserDetails = false;
    $http({
        url: "/connection/" + userId + "/list",
        dataType: "json",
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .success(function(response) {
            if (response.error == null) {
                $scope.userList = response.searchResult;
            }
        })
        .error(function(error) {
            console.log(
                "error while getting profile details of user with id:" +
                    $rootScope.userId
            );
        });

    $scope.connects = [];
    $scope.totalConnects = 0;
    $scope.connectsPerPage = 10; // this should match
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
                "/connection/" +
                    userId +
                    "/list?size=" +
                    $scope.connectsPerPage +
                    "&page=" +
                    (pageNumber - 1)
            )
            .then(function(result) {
                $scope.connects = result.data.searchResult;
                $scope.totalConnects = result.data.totalCount;
            });
    }

    $scope.pagination = {
        current: 1
    };

    $scope.showUserProfile = function(user) {
        if (user.party2.userBean.id == $rootScope.userId) {
            $location.path("/profile/user/" + user.party1.userBean.id);
        } else {
            $location.path("/profile/user/" + user.party2.userBean.id);
        }
    };
});
letsTagOn.controller("ConnectionSugestionController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    $scope.size = 6;
    $scope.connectionPageNumber = 0;
    $scope.displayConnectionSuggestionList = [];
    $rootScope.currentUser = {};
    getConnectionSuggestionList();

    function getConnectionSuggestionList() {
        $http({
            url: "/profile/user/" + $rootScope.userId + "/suggestion",
            dataType: "json",
            method: "GET",
            params: {
                size: $scope.size,
                page: $scope.connectionPageNumber
            },
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                response.searchResult &&
                    response.searchResult.forEach(function(response) {
                        $scope.displayConnectionSuggestionList.push(response);
                    });
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    }

    $scope.getNextConnectionSuggestionList = function() {
        $scope.connectionPageNumber = $scope.connectionPageNumber + 1;
        getConnectionSuggestionList();
    };
    // getSuggestionlist();
    $scope.fetchConnectionList = function() {
        $rootScope.count = $rootScope.count + $rootScope.size;
        getSuggestionlist();
    };

    $scope.connect = function(user) {
        $http({
            url:
                "/party/" +
                $rootScope.userId +
                "/connection/" +
                user.userBean.id,
            dataType: "json",
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.displayConnectionSuggestionList.forEach(function(
                    currentConnention
                ) {
                    if (currentConnention.id == response.party2.id) {
                        var index = $scope.displayConnectionSuggestionList.indexOf(
                            currentConnention
                        );
                        $scope.displayConnectionSuggestionList.splice(index, 1);
                    }
                });
                $rootScope.ltoSuccessMessage = "Invite Successfully sent";
                $rootScope.toggleLtoSuccessModal();
                $(".lto-success-modal-dialog").css({
                    top: "200px"
                });
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };
});

letsTagOn.controller("NotificationsController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    $scope.connects = [];
    $scope.pendingConnections = [];
    $scope.totalConnects = 0;
    $scope.connectsPerPage = 10;
    $scope.pendingConnectionsPerPage = 5;
    $scope.pendingConnectionsPageNo = 1;
    var ready = true;

    $("#connection-scroll-container").on("scroll", function(e) {
        e.preventDefault();
        if (ready) {
            ready = false;
            $scope.PageNumberForUm = $scope.PageNumberForUm + 1;
            $scope.getPendingConnections();
        }
    });

    $scope.getPendingConnections = function() {
        console.log("getting pending connections");
        getPendingConnectionsPerPage($scope.pendingConnectionsPageNo);
        $scope.pendingConnectionsPageNo = $scope.pendingConnectionsPageNo + 1;
    };

    function getPendingConnectionsPerPage(pageNumber) {
        // this is just an example, in reality this stuff should
        // be in a service
        console.log("making call to /connection/userID/openRequests...");
        $http
            .get(
                "/connection/" +
                    $rootScope.userId +
                    "/openRequests?size=" +
                    $scope.pendingConnectionsPerPage +
                    "&page=" +
                    (pageNumber - 1)
            )
            .then(function(result) {
                console.log("got pending connections: ", result.data);
                result.data.searchResult &&
                    result.data.searchResult.forEach(function(response) {
                        $scope.pendingConnections.push(response);
                    });
                ready = true;
            })
            .catch(err => {
                console.log("unable to get pending conns: ", err);
            });
    }

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
                "/connection/" +
                    $rootScope.userId +
                    "/openRequests?size=" +
                    $scope.connectsPerPage +
                    "&page=" +
                    (pageNumber - 1)
            )
            .then(function(result) {
                $scope.connects = result.data.searchResult;
                $scope.totalConnects = result.data.totalCount;
            });
    }

    $scope.pagination = {
        current: 1
    };

    $scope.connectionReject = function(connection) {
        $http({
            url:
                "/party/" +
                connection.party1.id +
                "/connection/reject/" +
                connection.party2.id,
            dataType: "json",
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $rootScope.pendingConnectionList.forEach(function(
                    currentConnention
                ) {
                    if (currentConnention.party2.id == connection.party2.id) {
                        var index = $rootScope.pendingConnectionList.indexOf(
                            currentConnention
                        );
                        $rootScope.pendingConnectionList.splice(index, 1);
                    }
                });
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.connectionAcceptence = function(connection) {
        $http({
            url:
                "/party/" +
                connection.party1.id +
                "/connection/accept/" +
                connection.party2.id,
            dataType: "json",
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $rootScope.pendingConnectionList.forEach(function(
                    currentConnention
                ) {
                    if (currentConnention.party2.id == connection.party2.id) {
                        var index = $rootScope.pendingConnectionList.indexOf(
                            currentConnention
                        );
                        $rootScope.pendingConnectionList.splice(index, 1);
                    }
                });
                $rootScope.ltoSuccessMessage =
                    "You are successfully connected to " +
                    connection.party1.userBean.name +
                    ".";

                $rootScope.toggleLtoSuccessModal();
                $(".lto-success-modal-dialog").css({
                    top: "200px"
                });
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.showPendingConnections = function() {
        $location.path("/pending/connection/user/" + $rootScope.userId);
    };

    $scope.showUserProfile = function(user) {
        if (user.party2.userBean.id == $rootScope.userId) {
            $location.path("/profile/user/" + user.party1.userBean.id);
        } else {
            $location.path("/profile/user/" + user.party2.userBean.id);
        }
    };
});
