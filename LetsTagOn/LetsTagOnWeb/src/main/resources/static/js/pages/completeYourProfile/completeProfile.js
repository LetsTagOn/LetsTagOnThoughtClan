/* 
 * JS File which inclused controllers for completeProfile ALL steps
 */
var completeProfile = angular.module('completeProfile', ['ngImgCrop']);

/* 
 * Controller for completeProfile step1 - Personal information
 * 
 */
completeProfile.controller('PersonalInformationController', function($http, $scope, $rootScope, $location, $timeout,$filter) {
    
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
    $("textarea").on("focus", function() {
        $(this).prev().addClass("focus-effect");
        $(this).addClass("focus-effect-input");
        $(this).next().addClass("focus-effect");
    });
    $("textarea").on("blur", function() {
        $(this).prev().removeClass("focus-effect");
        $(this).removeClass("focus-effect-input");
        $(this).next().removeClass("focus-effect");
    });
    // ======================================== TO prepoulate user data in the page =============================
    $scope.user = {};
    $scope.userTypeBean = {};
    $scope.attribute = {};
    $scope.userTypeMasterList = [];
    $scope.userTypeList = [];
    $scope.additionalAttributeQuestionList = [];
    $http({
        url: '/profile/user/' + $rootScope.userId,
        dataType: 'json',
        method: 'GET',
        headers: {
            "Content-Type": "application/json"
        }

    }).success(function(response) {
        if (response.error == null) {

            $scope.user = response.data.user;
            var name = $scope.user.name.split(" ");
            $scope.user.firstName = name[0];
            $scope.user.lastName = name[1];
            $scope.user.dateOfBirth =  $filter('date')($scope.user.dateOfBirth, 'MM-dd-yyyy');
           
            //UserType list			
            $scope.userTypeList = response.data.userTypeList;

            if ($scope.userTypeList.length > 0) {
                response.data.userTypeMasterList.forEach(function(masterData) {
                    $scope.userTypeList.forEach(function(userData) {
                        if (masterData.name == userData.userType.name && userData.active) {
                            masterData.addClass = "selected";
                        }
                        var index = $scope.userTypeMasterList.indexOf(masterData);
                        if (index == -1) {
                            $scope.userTypeMasterList.push(masterData);
                        }
                    });
                });
            } else {
                $scope.userTypeMasterList = response.data.userTypeMasterList;
            }
            
            $scope.userTypeTemplateSelected =	$scope.userTypeMasterList[$scope.user.userTypeBean.id-1];
            
            $scope.user.userType = value;
            //Additional Questions
            $scope.additionalAttributeQuestionList = [];
            $scope.additionalAttributeQuestionList = response.data.additionalAttribute;
            $scope.prefillAdditionalAttributeValues(response.data.userAdditionalAttribute);

        } else {
            $rootScope.actionError = response.error.errorMessage;
            $rootScope.error = true;
        }

    }).error(function(error) {
        $rootScope.actionError = response.error.errorMessage;
        $rootScope.error = true;
    });

    $scope.prefillAdditionalAttributeValues = function(userAttributeList) {
        if (userAttributeList.length > 0) {
            $scope.additionalAttributeQuestionList.forEach(function(additionalAttribute){
        	userAttributeList.forEach(function(userAttribute) {
        	    if(additionalAttribute.id == userAttribute.additionalProfileAttribute.id){
        		additionalAttribute.value = userAttribute.value;
        	    }
        	});
            });
        }
    };
    
    $("#userDateOfBirth").datepicker({
        format: "mm/dd/yyyy",
        todayHighlight: true
    }).on('changeDate', function(e) {
        $scope.user.dateOfBirth = $(this).val();
        $(this).focus();
        $(this).datepicker('hide');
    });
    
    //================================ capture usertype ===============================
    $scope.$watch('userTypeTemplateSelected', function() {
    	$scope.userTypeBean	= new Object();
        $scope.userTypeBean	= $scope.userTypeTemplateSelected;
    });
    $scope.saveUserType = function(userType) {
    	var type = $("#userType option:selected").html();
    	$scope.userTypeBean	= new Object();
    	$scope.userTypeBean.id =	userType.id;
    	$scope.userTypeBean.name=	userType.name;
    	$scope.userTypeBean.userTypeAttributeXrefs = "";
    	$scope.userTypeBean.description=	userType.description;
//        $scope.userTypeXref = new Object();
//        $scope.userTypeXref.userType = userType;
//        if (userType.addClass == "selected") {
//            $scope.userTypeXref.active = false;
//        } else {
//            $scope.userTypeXref.active = true;
//        }
//
//        $('#loading-indicator').show();
//        $http({
//            url: '/profile/user/save/userType/' + $rootScope.userId,
//            dataType: 'json',
//            method: 'POST',
//            data: $scope.userTypeXref,
//            headers: {
//                "Content-Type": "application/json"
//            }
//
//        }).success(function(response) {
//            $('#loading-indicator').hide();
//            $scope.updateUserTypeMasterList(response.data.userType);
//            $scope.additionalAttributeQuestionList = [];
//            $scope.additionalAttributeQuestionList = response.data.additionalAttribute;
//            $scope.prefillAdditionalAttributeValues(response.data.userAdditionalAttribute);
//        }).error(function(error) {
//            $('#loading-indicator').hide();
//            $scope.error = error;
//        });
    };

    $scope.updateUserTypeMasterList = function(response) {
        $scope.userTypeMasterList.forEach(function(list) {
            if (response.userType.name == list.name && response.active) {
                list.addClass = "selected";
            } else if (response.userType.name == list.name && !response.active) {
                list.addClass = "";
            }
        });
    };
    //================================ End =============================================


    //================================ capture additionalAttribute ===============================
    $scope.saveAdditionalAttributeList = function() {
        var attributeList = [];
        $scope.additionalAttributeQuestionList.forEach(function(attribute) {
            var additionalPropertyAttribute = new Object();
            additionalPropertyAttribute.value = $("#attribute" + attribute.id).val();
            additionalPropertyAttribute.additionalProfileAttribute = attribute;
            attributeList.push(additionalPropertyAttribute);
        });

        var user = new Object();
        user.id = $rootScope.userId;
        user.userAdditionalProfileAttributes = attributeList;
        $('#loading-indicator').show();
        $http({
            url: '/profile/user/save/additional/attributes',
            dataType: 'json',
            method: 'POST',
            data: user,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            if (response.error == null) {
                $rootScope.attributeSuccessMessage = "Save successfull";
                $rootScope.attributeSuccess = true;
                $rootScope.attributeError = false;
                $timeout(function() {
                    $rootScope.attributeSuccess = false;
                }, 1500);
            } else {
                $rootScope.attributeErrorMessage = response.error.errorMessage;
                $rootScope.attributeError = true;
                $rootScope.attributeSuccess = false;
                $timeout(function() {
                    $rootScope.attributeError = false;
                }, 1500);
            }
        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.error = error;
        });
    };



    //=============================== End ========================================================
    //Common on registartion and complete profile step_1
    $("#userDateOfBirth").datepicker({
        format: "dd/mm/yyyy",
        todayHighlight: true,
        endDate: "today"
    });
    
    $scope.changedValue = function() {
    	   console.log($scope.userTypeTemplateSelected);
    	   $scope.userTypeBean	= $scope.userTypeTemplateSelected;
    }


    // ================================= Save user personal Information =========================================
    $scope.completeProfileForm_step1 = function() {
        $scope.user.name = $scope.user.firstName + " " + $scope.user.lastName;
        var address = new Object();
        address.id = $scope.user.addressBean.id;
        address.country = $scope.user.addressBean.country;
        address.postalCode = $scope.user.addressBean.postalCode;
        address.city = $scope.user.addressBean.city;
        address.state = $scope.user.addressBean.state;
        address.street = $scope.user.addressBean.street;
        address.formattedAddress = address.street + " " + address.city + " " +address.state+" "+ address.country+" " +address.postalCode
        $scope.user.addressBean = address;
        $scope.user.userTypeBean = $scope.userTypeBean;
        var parts = $("#userDateOfBirth").val().split("/");
        $scope.user.dateOfBirth = new Date(parts[2],parts[0]-1,parts[1]); 
        

        $('#loading-indicator').show();
        $http({
            url: '/profile/user/saveOrUpdate/personalInformation',
            dataType: 'json',
            method: 'POST',
            data: $scope.user,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            $scope.user.dateOfBirth = $filter('date')(new Date($("#userDateOfBirth").val()), 'MM-dd-yyyy');
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

        }).error(function(error) {
            $('#loading-indicator').hide();
            console.log("exception caught in catch while saving personal information" + error + " for user=" + $rootScope.userId);
        });
    };
});
// ======================================= Upload Profile PIc COntroller ===================================
completeProfile.controller("UploadProfilePicController", function($http, $scope, $rootScope, $location, $timeout) {


    $scope.showCropContainer = false;
    $scope.toggleCropContainer = function() {
        $scope.showCropContainer = !$scope.showCropContainer;
    };
    $scope.myImage = '';
    $scope.myCroppedImage = '';

    var handleFileSelect = function(evt) {
        $scope.toggleCropContainer();
        var file = evt.currentTarget.files[0];
        var reader = new FileReader();
        reader.onload = function(evt) {
            $scope.$apply(function($scope) {
                $scope.myImage = evt.target.result;
            });
        };
        reader.readAsDataURL(file);
    };
    angular.element(document.querySelector('#profilePic')).on('change', handleFileSelect);

    var dataURItoBlob = function(dataURI) {
        var binary = atob(dataURI.split(',')[1]);
        var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
        var array = [];
        for (var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        return new Blob([new Uint8Array(array)], {
            type: mimeString
        });
    };

    $scope.saveProfilePic = function(file) {
        var blob = dataURItoBlob(file);
        $('#loading-indicator').show();
        var uploadfile = new FormData();
        uploadfile.append('file', blob);

        $http.post('/userProfile/uploadPic', uploadfile, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).success(function(response) {
            $('#loading-indicator').hide();
            $('#image-crop-modal').modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
            $(".user-pic").attr('src', '');
            $(".user-pic").attr('src', $rootScope.baseUrl + "userProfile/getProfilePic?key=" + response.data);
            $rootScope.profilePicture = response.data;
        }).error(function(error) {
            $('#loading-indicator').hide();
            console.log("exception caught in catch while saving personal information" + error + " for user=" + $rootScope.userId);
        });

    };
}).directive('imagecrop', function() {
    return {
        template: '<div class="modal fade image-crop-modal" id="image-crop-modal">' +
            '<div class="modal-dialog image-crop-modal-dialog">' +
            '<div class="modal-content image-crop-modal-content">' +
            '<div class="modal-header image-crop-modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-label="">' +
            '<span>&times;</span>' +
            '</button>' +
            '<h2 class="col-md-10 col-md-offset-4 lto-login-header">Change Picture</h2>' +
            '</div>' +
            '<form name="uploadProfilePic" id="uploadProfilePic" enctype="multipart/form-data">' +
            '<div class="cropArea">' +
            '<img-crop image="myImage" result-image="myCroppedImage" data-result="myCroppedImage"></img-crop>' +
            '</div>' +
            '<div class="text-center image-crop-modal-btn-container">' +
            '<button class="btn btn-primary btn--block-centered image-crop-modal-btn" ng-click="saveProfilePic(myCroppedImage)">Save</button>' +
            '<button class="btn btn-primary btn--block-centered image-crop-modal-btn" data-dismiss="modal">Cancel</button>' +
            '</div>' +
            '</form>' +
            '</div>' +
            '</div>' +
            '</div>',
        restrict: 'E',
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.$watch(attrs.visible, function(value) {
                if (value == true)
                    $(element).modal('show');
                else
                    $(element).modal('hide');
            });

            $(element).on('shown.bs.modal', function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on('hidden.bs.modal', function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});