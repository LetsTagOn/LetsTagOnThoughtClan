completeProfile.controller('PrivacySettingsController', function($http, $scope, $rootScope, $location, $timeout) {
    $scope.privacy = {};
    $.fn.bootstrapSwitch.defaults.size = 'small';

    $http({
        url: '/privacy/user/' + $rootScope.userId,
        dataType: 'json',
        method: 'GET',
        headers: {
            "Content-Type": "application/json"
        }

    }).success(function(response) {
        $('#loading-indicator').hide();
        if (response.error == null) {
            if (response.data != null) {
                if (response.data.emailAlertsOn == null || !response.data.emailAlertsOn) {
                    $('#emailAlertsOn').bootstrapSwitch('state', false);
                } else {
                    $('#emailAlertsOn').bootstrapSwitch('state', true);
                }
                if (response.data.emailNotificationFrequency == null || !response.data.emailNotificationFrequency) {
                    $('#emailNotificationFrequency').bootstrapSwitch('state', false);
                } else {
                    $('#emailNotificationFrequency').bootstrapSwitch('state', true);
                }
                if (response.data.mobileNumberVisibility == null || !response.data.mobileNumberVisibility) {
                    $('#mobileNumberVisibility').bootstrapSwitch('state', false);
                } else {
                    $('#mobileNumberVisibility').bootstrapSwitch('state', true);
                }
                if (response.data.profileDetailsVisibility == null || !response.data.profileDetailsVisibility) {
                    $('#profileDetailsVisibility').bootstrapSwitch('state', false);
                } else {
                    $('#profileDetailsVisibility').bootstrapSwitch('state', true);
                }
            } else {
                $('.BSswitch').bootstrapSwitch('state', true);
            }

        }
    }).error(function(error) {
        $('#loading-indicator').hide();
        $scope.error = error;
    });

    $("#mobileNumberVisibility").on('change', function(event, state) {
        $scope.saveUserPrivacySettings($scope.privacy);
    });
    $("#profileDetailsVisibility").on('change', function(event, state) {
        $scope.saveUserPrivacySettings($scope.privacy);
    });
    $("#emailAlertsOn").on('change', function(event, state) {
        $scope.saveUserPrivacySettings($scope.privacy);
    });
    $("#emailNotificationFrequency").on('change', function(event, state) {
        $scope.saveUserPrivacySettings($scope.privacy);
    });

    $scope.redirectProfilePage = function() {
        $location.path("/profile/user/" + $rootScope.userId);
    }

    $scope.saveUserPrivacySettings = function(privacy) {
        if (typeof(privacy.mobileNumberVisibility) == "undefined" || privacy.mobileNumberVisibility == null) {
            privacy.mobileNumberVisibility = false;
        }
        if (typeof(privacy.profileDetailsVisibility) == "undefined" || privacy.profileDetailsVisibility == null) {
            privacy.profileDetailsVisibility = false;
        }
        if (typeof(privacy.emailAlertsOn) == "undefined" || privacy.emailAlertsOn == null) {
            privacy.emailAlertsOn = false;
        }
        if (typeof(privacy.emailNotificationFrequency) == "undefined" || privacy.emailNotificationFrequency == null) {
            privacy.emailNotificationFrequency = false;
        }
        $scope.privacy = privacy;
        var user = new Object();
        user.id = $rootScope.userId;
        $scope.privacy.user = user;
        $http({
            url: '/privacy/user/saveOrUpdate/settings',
            dataType: 'json',
            method: 'POST',
            data: $scope.privacy,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            console.log("success on save of privacy settings");

        }).error(function(error) {
            console.log("error on save of privacy settings");
            $scope.error = error;
        });
    }
});