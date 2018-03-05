var profileModal = angular.module("profile", ["connectionModule", "search"]);

profileModal.controller("ProfileController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout,
    $anchorScroll
) {
    $anchorScroll("top");
    var url = window.location.href.split("profile/user/");
    var userId = url[1];
    if (userId == "" || userId == null) {
        userId = $rootScope.userId;
    }
    // call to get details to be rendered in profile page
    $scope.displayUserDetails = {};
    $scope.educationDetailsList = [];
    $scope.professionalDetailsList = [];
    $scope.volunteeringDetailsList = [];
    $scope.userSkillsList = [];
    $scope.userCausesList = [];
    $scope.userConnectionsList = [];
    $scope.ifConnected = false;
    $scope.ifPending = false;
    $scope.tagOn = false;
    $scope.pendingResponse = false;
    $scope.showPhoneNumber = true;
    $scope.showUserProfileDetails = true;
    $http({
        url: "/profile/user/info/" + userId,
        dataType: "json",
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .success(function(response) {
            console.log("profilejs: ", response);
            if (
                response.error == null &&
                response.data != undefined &&
                response.data != null
            ) {
                $scope.displayUserDetails = response.data.userDetails;
                $scope.educationDetailsList =
                    response.data.userExperience.educationDetails;
                $scope.professionalDetailsList =
                    response.data.userExperience.professionalDetails;
                $scope.volunteeringDetailsList =
                    response.data.userExperience.volunteeringDetails;
                $scope.userSkillsList = response.data.skills;
                $scope.userCausesList = response.data.causes;
                $scope.volunteerOpportunityCount =
                    response.data.volunteerOpportunityCount;
                $scope.volunteerConnectionCount =
                    response.data.volunteerConnectionCount;
                $scope.showConnectionStatus();
                $scope.applyPrivacySettings(response.data.privacySettingData);
            }
        })
        .error(function(error) {
            console.log(
                "error while getting profile details of user with id:" +
                    $rootScope.userId
            );
        });

    // ============= Function to handle users privacy settings =============
    $scope.applyPrivacySettings = function(privacySettings) {
        if (
            privacySettings != null &&
            privacySettings != "" &&
            typeof privacySettings != "undefined"
        ) {
            if (privacySettings.user.id != $rootScope.userId) {
                if (privacySettings.mobileNumberVisibility == false) {
                    $scope.showPhoneNumber = false;
                }
                if (privacySettings.profileDetailsVisibility == false) {
                    $scope.showUserProfileDetails = false;
                }
            }
        }
    };

    // ============== End ==========================

    $scope.showConnectionStatus = function() {
        $http({
            url: "/party/0/checkConnection/" + userId,
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response.data) {
                    connection = response.data;

                    if (
                        connection.party1.userBean.id == $rootScope.userId &&
                        connection.party2.userBean.id == userId &&
                        connection.connected
                    ) {
                        $scope.ifConnected = true;
                        $scope.tagOn = false;
                    } else if (
                        connection.party2.userBean.id == $rootScope.userId &&
                        connection.party1.userBean.id == userId &&
                        connection.connected
                    ) {
                        $scope.ifConnected = true;
                        $scope.ifPending = false;
                        $scope.tagOn = false;
                    } else if (
                        connection.party1.userBean.id == $rootScope.userId &&
                        connection.party2.userBean.id == userId &&
                        connection.connected == null
                    ) {
                        $scope.ifPending = true;
                        $scope.tagOn = false;
                    } else if (
                        connection.party2.userBean.id == $rootScope.userId &&
                        connection.party1.userBean.id == userId &&
                        connection.connected == null
                    ) {
                        $scope.pendingResponse = true;
                        $scope.ifPending = false;
                        $scope.tagOn = false;
                        $scope.ifConnected = false;
                    }
                } else {
                    $scope.tagOn = true;
                }
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.sendInvite = function(displayUserDetails) {
        $http({
            url:
                "/party/" +
                $rootScope.userId +
                "/connection/" +
                displayUserDetails.id,
            dataType: "json",
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                // var index =
                // $rootScope.displayConnectionSuggestionList.indexOf(response);
                $scope.ifPending = true;
                $scope.tagOn = false;
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.rejectInvite = function(user) {
        $http({
            url: "/user/" + $rootScope.userId + "/connection/reject/" + user.id,
            dataType: "json",
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.pendingResponse = false;
                $scope.ifPending = false;
                $scope.tagOn = true;
                $scope.ifConnected = false;
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.acceptInvite = function(user) {
        $http({
            url: "/user/" + $rootScope.userId + "/connection/accept/" + user.id,
            dataType: "json",
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.ifConnected = true;
                $scope.tagOn = false;
                $scope.pendingResponse = false;
                $scope.ifPending = false;
                $rootScope.ltoSuccessMessage =
                    "You are successfully connected to " + user.name + ".";
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

    // ============================================ TO Calculate Profile completion status ===========================================
    $scope.ProfileCompletionStatusDTO = {};
    $scope.getProfileCompletionStatus = function() {
        $http({
            url: "/profile/user/completion",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response.error == null) {
                    $scope.ProfileCompletionStatusDTO = response.data;
                    var width =
                        $scope.ProfileCompletionStatusDTO.completion * 100 +
                        "%";
                    $("#profileProgressBar").css({
                        width: width
                    });
                }
            })
            .error(function() {});
    };
    // ============================================ End ==============================================================================
});
