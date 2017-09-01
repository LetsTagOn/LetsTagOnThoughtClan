letsTagOn.controller('PasswordController', function($http, $scope, $rootScope, $location, $timeout) {
    $scope.changePassword = function() {
        $('#loading-indicator').show();
        var url = window.location.href.split("?name=");
        url = url[1].split("&tid=");
        var resetPassordToken = url[1];
        var userName = url[0];
        $scope.user.userName = userName;
        $scope.user.resetPassordToken = resetPassordToken;
        $http({
            url: '/password/resetPassword',
            dataType: 'json',
            method: 'POST',
            data: $scope.user,
            headers: {
                "Content-Type": "application/json"
            }
        }).success(function(response) {
            $('#loading-indicator').hide();
            if (response.error == null) {
                $scope.user = '';
                $scope.changePassForm.$setPristine();
                $scope.changePassForm.$setUntouched();
                $scope.submitted = false;
                $scope.redirect("/");
                $rootScope.registrationSuccessHeader = "Password Changed Successfull.";
                $rootScope.successMessageText1 = "Your password has been updated successfully.";
                $rootScope.successMessageText2 = "Kindly login with the registered emailId and password.";
                $rootScope.toggleSuccessModal();
                $('.registration-success-modal-dialog').css({
                    top: "200px",
                });
            } else {
                $scope.showErrorModal = false;
                $rootScope.authenticationError = response.error.errorMessage;
                $rootScope.toggleErrorModal();
                $('.registration-error-modal-dialog').css({
                    top: "200px"
                });
                $("#modal-error").addClass("registration-error-modal");
            }
        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.showErrorModal = false;
            $rootScope.authenticationError = response.error.errorMessage;
            $rootScope.toggleErrorModal();
            $('.registration-error-modal-dialog').css({
                top: "200px"
            });
            $("#modal-error").addClass("registration-error-modal");
        });
    };
    $scope.submitted = false;

    $scope.interacted = function(field) {
        return $scope.submitted || field.$dirty;
    };
    $scope.userDetails = {};
    $scope.forgotPassword = function() {
        $('#loading-indicator').show();
        $scope.submitted = true;

        $http({
            url: '/password/forgotPassword',
            dataType: 'json',
            method: 'POST',
            data: $scope.userDetails


        }).success(function(response) {
            $('#loading-indicator').hide();
            if (response.error == null) {
                $scope.userDetails = '';
                $scope.forgetPasswordForm.$setPristine();
                $scope.forgetPasswordForm.$setUntouched();
                $scope.submitted = false;
                $rootScope.isSuccess = true;
                $rootScope.isError = false;
                $rootScope.successMessage = "A link has been sent to specified email . Please verify and change password before link expires";
            } else {
                $rootScope.isError = true;
                $rootScope.isSuccess = false;
                $rootScope.errorMessage = response.error.errorMessage;
            }
        }).error(function(error) {
            $('#loading-indicator').hide();
            $rootScope.isError = true;
            $rootScope.errorMessage = response.error.errorMessage;
        });
    };
}).directive("passwordCheck", function() {
    return {
        require: "ngModel",
        scope: {
            passwordCheck: '='
        },
        link: function(scope, element, attrs, ctrl) {
            scope.$watch(function() {
                var combined;

                if (scope.passwordCheck || ctrl.$viewValue) {
                    combined = scope.passwordCheck + '_' + ctrl.$viewValue;
                }
                return combined;
            }, function(value) {
                if (value) {
                    ctrl.$parsers.unshift(function(viewValue) {
                        var origin = scope.passwordCheck;
                        if (origin !== viewValue) {
                            ctrl.$setValidity("passwordCheck", false);
                            return undefined;
                        } else {
                            ctrl.$setValidity("passwordCheck", true);
                            return viewValue;
                        }
                    });
                }
            });
        }
    };
});