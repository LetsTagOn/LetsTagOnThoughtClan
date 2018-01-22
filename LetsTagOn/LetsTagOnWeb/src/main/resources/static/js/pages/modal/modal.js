var bootstrapModal = angular.module("bootstrapModal", [
    "ngLocalize",
    "localization"
]);

bootstrapModal.controller("BootstrapModalController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    $scope.showSuccessModal = false;
    $scope.showErrorModal = false;
    $scope.showSessionTimeout = false;
    $scope.showLtoSuccessModal = false;
    $scope.showLtoErrorModal = false;

    $rootScope.toggleSuccessModal = function() {
        $(".modal").modal("hide");
        $scope.showSuccessModal = !$scope.showSuccessModal;
    };
    $rootScope.toggleErrorModal = function() {
        $(".modal").modal("hide");
        $scope.showErrorModal = !$scope.showErrorModal;
    };
    $rootScope.toggleSessionTimeoutPopup = function() {
        $(".modal").modal("hide");
        $scope.showSessionTimeout = !$scope.showSessionTimeout;
    };
    $rootScope.toggleLtoSuccessModal = function() {
        $(".modal").modal("hide");
        $scope.showLtoSuccessModal = !$rootScope.showLtoSuccessModal;
    };
    $rootScope.toggleLtoErrorModal = function() {
        $(".modal").modal("hide");
        $scope.showLtoErrorModal = !$rootScope.showLtoErrorModal;
    };

    $scope.openRegistrationModal = function() {
        $("#modalLogin").modal("hide");
        $("#myModal").modal("show");
    };
});

bootstrapModal.directive("success", function() {
    return {
        template:
            '<div class="modal" id="modal-success" ng-controller="PasswordController">' +
            '<div class="modal-dialog registration-success-modal-dialog">' +
            '<div class="modal-content registration-success-modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
            '<h3 class="modal-title success" ng-modal="registrationSuccessHeader">{{registrationSuccessHeader}}</h3>' +
            "</div>" +
            '<div class="modal-body registration-success-modal-body">' +
            '<h3 ng-model="successMessage">{{successMessage}}</h3>' +
            '<h2 class="registration-success-title success" ng-model="successMessageText1">{{successMessageText1}}</h2>' +
            '<p class="registration-success-text" ng-model="successMessageText2">{{successMessageText2}}</p>' +
            '<p class="registration-success-text" ng-model="successMessageText3">{{successMessageText3}}</p>' +
            "</div> " +
            "</div>" +
            "</div>" +
            "</div>",
        restrict: "E",
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.$watch(attrs.visible, function(value) {
                if (value == true) $(element).modal("show");
                else $(element).modal("hide");
            });

            $(element).on("shown.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on("hidden.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});

bootstrapModal.directive("actionsuccess", function() {
    return {
        template:
            '<div class="modal" id="lto-modal-success">' +
            '<div class="modal-dialog lto-success-modal-dialog">' +
            '<div class="modal-content registration-success-modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
            '<h3 class="modal-title success" ng-modal="ltoSuccessMessage">{{ltoSuccessMessage}}</h3>' +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>",
        restrict: "E",
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.$watch(attrs.visible, function(value) {
                if (value == true) $(element).modal("show");
                else $(element).modal("hide");
            });

            $(element).on("shown.bs.modal", function() {
                if (!scope.$$phase) {
                    scope.$apply(function() {
                        scope.$parent[attrs.visible] = true;
                    });
                }
            });

            $(element).on("hidden.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});
bootstrapModal.directive("actionerror", function() {
    return {
        template:
            '<div class="modal" id="lto-modal-error">' +
            '<div class="modal-dialog lto-error-modal-dialog">' +
            '<div class="modal-content registration-error-modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
            '<h2 class="modal-title error" ng-modal="ltoErrorMessage">{{ltoErrorMessage}}</h2>' +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>",
        restrict: "E",
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.$watch(attrs.visible, function(value) {
                if (value == true) $(element).modal("show");
                else $(element).modal("hide");
            });

            $(element).on("shown.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on("hidden.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});

bootstrapModal.directive("error", function() {
    return {
        template:
            '<div class="modal" id="modal-error">' +
            '<div class="modal-dialog registration-error-modal-dialog">' +
            '<div class="modal-content registration-error-modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
            '<h2 class="modal-title error">Registration Error</h2>' +
            "</div>" +
            '<div class="modal-body error">' +
            '<h2 class="error" ng-model="authenticationError">{{authenticationError}}</h2>' +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>",
        restrict: "E",
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.$watch(attrs.visible, function(value) {
                if (value == true) $(element).modal("show");
                else $(element).modal("hide");
            });

            $(element).on("shown.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on("hidden.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});
bootstrapModal.directive("session", function() {
    return {
        template:
            '<div class="modal" id="modal-session-timeout">' +
            '<div class="modal-dialog session-error-modal-dialog">' +
            '<div class="modal-content registration-error-modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
            '<h2 class="modal-title error">Session Expired!</h2>' +
            "</div>" +
            '<div class="modal-body error">' +
            '<h2 class="" i18n = "{{' +
            "'common.lto_heading_sessionExpire'" +
            '}}"></h2>' +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>",
        restrict: "E",
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.$watch(attrs.visible, function(value) {
                if (value == true) $(element).modal("show");
                else $(element).modal("hide");
            });

            $(element).on("shown.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on("hidden.bs.modal", function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});
