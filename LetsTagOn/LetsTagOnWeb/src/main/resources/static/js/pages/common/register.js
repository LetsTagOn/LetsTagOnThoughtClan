/*
 * JS File to register user 
 * 
 */
letsTagOn.controller('RegisterController', function($http, $scope, $rootScope, $location, $timeout) {
    //User Registration
    $scope.customer = {};

    //Common on registartion and complete profile step_1
    $('#myTermsofUse').on('shown.bs.modal', function() {
      	$('#myModal').unbind();
    });
    $('#myTermsofUse').on('hidden.bs.modal', function() {
      	//$('#myModal').bind();
    });
    
    $('#myPolicy').on('shown.bs.modal', function() {
      	$('#myModal').unbind();
    });
    $('#myPolicy').on('hidden.bs.modal', function() {
      	$('#myModal').bind();
    });
    
    $('#registerclose').on('click', function() {
      	$('#myModal').modal('hide')
    });

    $("#dateOfBirth").datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true,
        endDate: new Date(),
    }).on('changeDate', function(e) {
        $scope.customer.dateOfBirth = $(this).val();
        $(this).focus();
        $(this).datepicker('hide');
    });

    /*Highlight Element on focus and blur*/
    $("input").on("focus", function() {
        $(this).prev().addClass("focus-effect");
        $(this).addClass("focus-effect-input");
        $(this).next().addClass("focus-effect");
    });
    $("input").on("blur", function() {
        $(this).prev().removeClass("focus-effect");
        $(this).removeClass("focus-effect-input");
        $(this).next().removeClass("focus-effect");
    });
    //save call on registration
    $scope.registerForm = function(submitted) {
        $scope.customer.name = $scope.customer.firstName + " " + $scope.customer.lastName;
        var address = new Object();
        address.country = $scope.customer.country;
        address.postalCode = $scope.customer.postalCode;
        $scope.customer.addressBean = address;
        $scope.customer.userRole = "VLNTR";
        var userType = new Object();
        userType.id = 1;
        $scope.customer.userTypeBean = userType;
        $scope.customer.dateOfBirth = $("#dateOfBirth").val();
        $('#loading-indicator').show();
        $http({
            url: 'register/customer',
            dataType: 'json',
            method: 'POST',
            data: $scope.customer,
            headers: {
                "Content-Type": "application/json"
            }
        }).success(function(response) {
            $('#loading-indicator').hide();
            if (response.error == null) {
                $scope.customer = '';
                $scope.register.$setPristine();
                $scope.register.$setUntouched();
                $scope.submitted = false;
                $scope.showSuccessModal = false;
                $('#myModal').modal('hide');
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                $rootScope.registrationSuccessHeader = "Registration successfully completed";
                $rootScope.successMessageText1 = "You're almost there!";
                $rootScope.successMessageText2 = "A registration confirmation mail has been sent to your specified email.";
                $rootScope.successMessageText3 = "Kindly login with the registered emailId and password.";
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
            $rootScope.authenticationError = response.error.errorMessage;
            $('#modal-error').modal('show');
        });
    };
}).directive("passwordVerify", function() {
    return {
        require: "ngModel",
        scope: {
            passwordVerify: '='
        },
        link: function(scope, element, attrs, ctrl) {
            scope.$watch(function() {
                var combined;

                if (scope.passwordVerify || ctrl.$viewValue) {
                    combined = scope.passwordVerify + '_' + ctrl.$viewValue;
                }
                return combined;
            }, function(value) {
                if (value) {
                    ctrl.$parsers.unshift(function(viewValue) {
                        var origin = scope.passwordVerify;
                        if (origin !== viewValue) {
                            ctrl.$setValidity("passwordVerify", false);
                            return undefined;
                        } else {
                            ctrl.$setValidity("passwordVerify", true);
                            return viewValue;
                        }
                    });
                }
            });
        }
    };
});