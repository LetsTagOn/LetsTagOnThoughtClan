var opportunityManagement = angular.module("opportunityManagementModule", [
    "angularjs-dropdown-multiselect"
]);

opportunityManagement.controller("ManageOppApplicationsController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    // $scope.gPlace;
    var url = window.location.href.split("/opportunity/manageApplications");
    var oppID = url[1];
    $scope.oppID = oppID;
    $http({
        url: "/opportunity/" + oppID,
        dataType: "json",
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .success(function(response) {
            if (response.error == null) {
                $scope.oppDetails = response.data;
                $scope.checkIfEditToBeShown(oppID);
            }
        })
        .error(function(error) {
            console.log(
                "error while getting profile details of user with id:" +
                    $rootScope.userId
            );
        });

    $scope.participants = [];
    $scope.totalParticipants = 0;
    $scope.participantsPerPage = 10; // this should match
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
                "/opportunity/" +
                    $scope.oppID +
                    "/participantsByStatus?status=&size=10&page=" +
                    (pageNumber - 1)
            )
            .then(function(result) {
                $scope.participants = result.data.searchResult;
                $scope.totalParticipants = result.data.totalCount;
            });
    }

    $scope.checkIfEditToBeShown = function(oppID) {
        $http({
            url: "/opportunity/" + oppID + "/userDetails",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                // check if edit tab is to be showns
                if (response.error) {
                    // error
                    return;
                }
                if (response.data.partyParticipations) {
                    var participationList = response.data.partyParticipations;
                    for (var participation in participationList) {
                        var jobId =
                            participationList[participation].jobTypeBean.id;
                        $scope["apply_btn_" + jobId] = true;
                        $scope["applied_btn_" + jobId] = true;
                    }
                }
                // if admin show edit button
                if (response.data.isAdmin) {
                    $scope.manageOppButton = true;
                    $scope.editOppButton = true;
                }
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };

    //=============================================== Manage Application ==================================================

    $scope.manageAcceptance = function(participationID, accept) {
        $("#loading-indicator").show();
        $http({
            url: "/opportunity/participation/" + participationID + "/status",
            dataType: "json",
            method: "POST",
            params: {
                accept: accept
            },
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $("#loading-indicator").hide();
                if (response.error == null) {
                    $scope.participants.forEach(function(participant) {
                        if (participant.id == response.data.id) {
                            var index = $scope.participants.indexOf(
                                participant
                            );
                            $scope.participants.splice(index, 1);
                        }
                    });
                    if (accept == false) {
                        $rootScope.ltoSuccessMessage = "Application Rejected";
                    } else {
                        $rootScope.ltoSuccessMessage = "Application Accepted";
                    }
                    $rootScope.toggleLtoSuccessModal();
                    $(".lto-success-modal-dialog").css({
                        top: "200px"
                    });
                }
            })
            .error(function(error) {
                $("#loading-indicator").hide();
            });
    };

    $scope.paintUserProfileDetails = function(userId) {
        $location.path("/profile/user/" + userId);
    };
    $scope.showDetailPage = function(id) {
        console.info(id);
        $location.path("/view/opportunity/" + id);
    };
    //=============================================== End ==================================================================

    $scope.editOpp = function(opp) {
        // console.log("Opp id : "+oppID);
        if (opp.type == "PROGRAM") {
            window.location = "#/view/opportunity/program/edit/" + opp.id;
            return;
        }
        window.location = "#/view/opportunity/edit/" + opp.id;
    };
});

opportunity.controller("ManageOppAttendanceController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    // $scope.gPlace;
    var url = window.location.href.split("/opportunity/manageAttendance");
    var oppID = url[1];
    $scope.oppID = oppID;
    $http({
        url: "/opportunity/" + oppID,
        dataType: "json",
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .success(function(response) {
            if (response.error == null) {
                $scope.oppDetails = response.data;
                $scope.checkIfEditToBeShown(oppID);
            }
        })
        .error(function(error) {
            console.log(
                "error while getting profile details of user with id:" +
                    $rootScope.userId
            );
        });

    $scope.participants = [];
    $scope.totalParticipants = 0;
    $scope.participantsPerPage = 10; // this should match
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
                "/opportunity/" +
                    $scope.oppID +
                    "/participantsByAttendance?attendance=&size=10&page=" +
                    (pageNumber - 1)
            )
            .then(function(result) {
                $scope.participants = result.data.searchResult;
                $scope.totalParticipants = result.data.totalCount;
            });
    }

    $scope.checkIfEditToBeShown = function(oppID) {
        $http({
            url: "/opportunity/" + oppID + "/userDetails",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                // check if edit tab is to be shown

                if (response.error) {
                    // error
                    return;
                }

                if (response.data.partyParticipations) {
                    var participationList = response.data.partyParticipations;
                    for (var participation in participationList) {
                        var jobId =
                            participationList[participation].jobTypeBean.id;
                        $scope["apply_btn_" + jobId] = true;
                        $scope["applied_btn_" + jobId] = true;
                    }
                }

                // if admin show edit button
                if (response.data.isAdmin) {
                    $scope.manageOppButton = true;
                    $scope.editOppButton = true;
                }
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };

    //=============================================== Manage Attendence ====================================================
    $scope.manageAttendence = function(participationID, attended) {
        $("#loading-indicator").show();
        $http({
            url:
                "/opportunity/participation/" + participationID + "/attendance",
            dataType: "json",
            method: "POST",
            params: {
                attended: attended
            },
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $("#loading-indicator").hide();
                if (response.error == null) {
                    $scope.participants.forEach(function(participant) {
                        if (participant.id == response.data.id) {
                            var index = $scope.participants.indexOf(
                                participant
                            );
                            $scope.participants.splice(index, 1);
                        }
                    });
                    $rootScope.ltoSuccessMessage = "Attendence Marked";

                    $rootScope.toggleLtoSuccessModal();
                    $(".lto-success-modal-dialog").css({
                        top: "200px"
                    });
                }
            })
            .error(function(error) {
                $("#loading-indicator").hide();
            });
    };

    $scope.paintUserProfileDetails = function(userId) {
        $location.path("/profile/user/" + userId);
    };

    $scope.showDetailPage = function(id) {
        console.info(id);
        $location.path("/view/opportunity/" + id);
    };

    //=============================================== End ==================================================================

    $scope.editOpp = function(opp) {
        // console.log("Opp id : "+oppID);
        if (opp.type == "PROGRAM") {
            window.location = "#/view/opportunity/program/edit/" + opp.id;
            return;
        }
        window.location = "#/view/opportunity/edit/" + opp.id;
    };
});

opportunity
    .controller("ManageOppFeedbackController", function(
        $http,
        $scope,
        $rootScope,
        $location,
        $timeout
    ) {
        // $scope.gPlace;
        var url = window.location.href.split("/opportunity/manageFeedback");
        var oppID = url[1];
        $scope.oppID = oppID;
        $http({
            url: "/opportunity/" + oppID,
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response.error == null) {
                    $scope.oppDetails = response.data;
                    $scope.checkIfEditToBeShown(oppID);
                }
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });

        $scope.participants = [];
        $scope.totalParticipants = 0;
        $scope.participantsPerPage = 10; // this should match
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
                    "/opportunity/" +
                        $scope.oppID +
                        "/participantsByAttendance?attendance=true&size=10&page=" +
                        (pageNumber - 1)
                )
                .then(function(result) {
                    $scope.participants = result.data.searchResult;
                    $scope.totalParticipants = result.data.totalCount;
                });
        }
        $scope.checkIfEditToBeShown = function(oppID) {
            $http({
                url: "/opportunity/" + oppID + "/userDetails",
                dataType: "json",
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .success(function(response) {
                    // check if edit tab is to be shown

                    if (response.error) {
                        // error
                        return;
                    }

                    if (response.data.partyParticipations) {
                        var participationList =
                            response.data.partyParticipations;
                        for (var participation in participationList) {
                            var jobId =
                                participationList[participation].jobTypeBean.id;
                            $scope["apply_btn_" + jobId] = true;
                            $scope["applied_btn_" + jobId] = true;
                        }
                    }
                    // if admin show edit button
                    if (response.data.isAdmin) {
                        $scope.manageOppButton = true;
                        $scope.editOppButton = true;
                    }
                })
                .error(function(error) {
                    console.log(
                        "error while getting profile details of user with id:" +
                            $rootScope.userId
                    );
                });
        };
        $scope.rating = 5;
        $scope.ratingValue;
        // ================================ Submit Rating ====================================================
        $scope.rateFunction = function(rating, id) {
            //TO save ajax call
            $("#loading-indicator").show();
            $scope.participation = {};
            $scope.participation.rating = rating;
            $scope.participation.id = id;
            $http({
                url: "/opportunity/participation/rating",
                dataType: "json",
                method: "POST",
                data: $scope.participation,
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .success(function(response) {
                    $("#loading-indicator").hide();
                })
                .error(function(error) {
                    $("#loading-indicator").hide();
                });
        };
        // ================================== End =============================================================
        // ===============================  Submit FeedBack ===================================================
        $scope.submitFeedBack = function(participationID) {
            $("#loading-indicator").show();
            $scope.participation = {};
            $scope.participation.id = participationID;
            $scope.participation.review = $(
                "#feedback" + participationID
            ).val();
            $http({
                url: "/opportunity/participation/feedBack",
                dataType: "json",
                method: "POST",
                data: $scope.participation,
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .success(function(response) {
                    $("#loading-indicator").hide();
                    if (response.error == null) {
                        $rootScope.actionSuccess = "Save successfull";
                        $rootScope.success = true;
                        $rootScope.error = false;
                        $timeout(function() {
                            $rootScope.success = false;
                        }, 1500);
                    } else {
                        $rootScope.actionError = response.error.errorMessage;
                        $rootScope.error = true;
                        $timeout(function() {
                            $rootScope.error = false;
                        }, 1500);
                    }
                })
                .error(function(error) {
                    $("#loading-indicator").hide();
                    $rootScope.actionError = "Error while givinng feedback";
                    $rootScope.error = true;
                    $timeout(function() {
                        $rootScope.error = false;
                    }, 1500);
                });
        };

        $scope.paintUserProfileDetails = function(userId) {
            $location.path("/profile/user/" + userId);
        };

        $scope.showDetailPage = function(id) {
            console.info(id);
            $location.path("/view/opportunity/" + id);
        };

        // ========================================= End ====================================================
        $scope.editOpp = function(opp) {
            // console.log("Opp id : "+oppID);
            if (opp.type == "PROGRAM") {
                window.location = "#/view/opportunity/program/edit/" + opp.id;
                return;
            }
            window.location = "#/view/opportunity/edit/" + opp.id;
        };
    })
    .directive("starRating", function() {
        return {
            restrict: "A",
            template:
                '<ul class="rating">' +
                '   <li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">' +
                "\u2605" +
                "</li>" +
                "</ul>",
            scope: {
                ratingValue: "=",
                max: "=",
                onRatingSelected: "&"
            },
            link: function(scope, elem, attrs) {
                var updateStars = function() {
                    scope.stars = [];
                    for (var i = 0; i < scope.max; i++) {
                        scope.stars.push({
                            filled: i < scope.ratingValue
                        });
                    }
                };

                scope.toggle = function(index) {
                    scope.ratingValue = index + 1;
                    scope.onRatingSelected({
                        rating: index + 1
                    });
                };

                scope.$watch("ratingValue", function(oldVal, newVal) {
                    if (newVal) {
                        updateStars();
                    }
                });
            }
        };
    });
