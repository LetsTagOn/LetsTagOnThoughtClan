/* 
 * Controller for complete profile step2 - experiences
 * 
 */
completeProfile.controller('ExperienceController', function($http, $scope, $rootScope, $location, $timeout, $filter) {
    $scope.userEducation = {};
    $scope.userExperience = {};
    $scope.userVolunteerExperience = {};
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
    $scope.isExpVisible = false;
    $scope.isEduVisible = false;
    $scope.isVolExpVisible = false;

    $scope.showVolHiddenContainer = function() {
        $scope.volunnteerExperience = "";
        $scope.isVolExpVisible = $scope.isVolExpVisible ? false : true;
        $scope.userVolunteerExperience = "";
    };
    $scope.showHiddenContainer = function() {
        $scope.isExpVisible = $scope.isExpVisible ? false : true;
        $scope.userExperience = "";
    };
    $scope.cancelSaveOrUpdateOfExperience = function() {
        $scope.isExpVisible = false;
        $scope.userExperience = "";
    };
    $scope.showEducationConatiner = function() {
        $scope.isEduVisible = $scope.isEduVisible ? false : true;
        $scope.userEducation = "";
    };
    $scope.cancelSaveOrUpdateOfEducation = function() {
        $scope.isEduVisible = false;
        $scope.userEducation = "";
    };
    //Appending datepicker for startDate and EndDate for experience and education
    $("#eduStartDate").datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true
    }).on('changeDate', function(e) {

        $scope.userEducation.startDate = $(this).val();
        $(this).focus();
        $(this).datepicker('hide');
    });
    $("#eduEndDate").datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true
    }).on('changeDate', function(e) {

        $scope.userEducation.endDate = $(this).val();
        $(this).focus();
        $(this).datepicker('hide');
    });
    $("#expStartDate").datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true
    }).on('changeDate', function(e) {

        $scope.userExperience.startDate = $(this).val();
        $(this).focus();
        $(this).datepicker('hide');
    });
    $("#expEndDate").datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true
    }).on('changeDate', function(e) {

        $scope.userExperience.endDate = $(this).val();
        $(this).focus();
        $(this).datepicker('hide');
    });

    $("#volExpStartDate").datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true
    }).on('changeDate', function(e) {
        $scope.userVolunteerExperience.startDate = $(this).val();
        $(this).focus();
        $(this).datepicker('hide');
    });
    $("#volExpEndDate").datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true
    }).on('changeDate', function(e) {
        $scope.userVolunteerExperience.endDate = $(this).val();
        $(this).focus();
        $(this).datepicker('hide');
    });

    /*$("#expEndDate").datepicker({
        format: "MM yyyy",
        startView: "year",
        autoclose : true,
        minViewMode: "months"
    });*/
    //Experience details
    //To get details of users if exists
    $scope.educationDetailsList = [];
    $scope.professionalDetailsList = [];
    $scope.volunteeringDetailsList = [];
   
    $scope.getUserExperienceDetailList = function() {
        $http({
            url: '/profile/experience/user/' + $rootScope.userId,
            dataType: 'json',
            method: 'GET',
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            if (response.error == null) {
                $scope.educationDetailsList = response.data.educationDetails;
                $scope.professionalDetailsList = response.data.professionalDetails;
                //Volunteer Histroy
                $scope.volunteeringDetailsList = response.data.volunteeringDetails;
            } else {
                $rootScope.actionError = response.error.errorMessage;
                $rootScope.error = true;
            }

        }).error(function(error) {
            $scope.error = error;
        });

    };

    // TO edit professional experience details
    $scope.editCurrentExperienceDetail = function(experience) {
        $scope.userExperience = new Object();
        $scope.userExperience.organizationName = experience.organizationName;
        $scope.userExperience.title = experience.title;
        $scope.userExperience.location = experience.location;
        $scope.userExperience.startDate = $filter('date')(experience.startDate, 'MM-dd-yyyy');
        $scope.userExperience.endDate = $filter('date')(experience.endDate, 'MM-dd-yyyy');
        $scope.userExperience.description = experience.description;
        $scope.userExperience.id = experience.id;
        $scope.isExpVisible = true;
    };

    //TO save experience details of user
    $scope.saveExperienceDetails = function() {
        $('#loading-indicator').show();
        $scope.userExperience.startDate = new Date($("#expStartDate").val());
        $scope.userExperience.endDate = new Date($("#expEndDate").val());
        var user = new Object();
        user.id = $rootScope.userId;
        $scope.userExperience.userBean = user;
        $scope.userExperience.type = "PRFEXP";
        $http({
            url: '/profile/user/saveOrUpdate/userExperience',
            dataType: 'json',
            method: 'POST',
            data: $scope.userExperience,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            $scope.profForm.$setPristine();
            $scope.profForm.$setUntouched();
            $scope.submitted = false;
            if (response.errror == null) {

                $scope.professionalDetailsList.forEach(function(experience) {
                    if (experience.id == response.data.id) {
                        var index = $scope.professionalDetailsList.indexOf(experience);
                        $scope.professionalDetailsList.splice(index, 1);
                    }
                });
                $scope.professionalDetailsList.unshift(response.data);
                $scope.experienceSuccessMessage = "Save successfull";
                $scope.professionalExpSuccess = true;
                $timeout(function() {
                    $scope.professionalExpSuccess = false;
                    $scope.isExpVisible = false;
                }, 1500);
            } else {
                $scope.experienceErrorMessage = response.error.errorMessage;
                $scope.professionalExpError = true;
                $timeout(function() {
                    $scope.professionalExpError = false;
                }, 1500);
            }

        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.error = error;
        });
    };
    // TO edit education details
    $scope.editCurrentEducationDetail = function(education) {
        $scope.userEducation = new Object();
        $scope.userEducation.organizationName = education.organizationName;
        $scope.userEducation.course = education.course;
        $scope.userEducation.degree = education.degree;
        $scope.userEducation.startDate = $filter('date')(education.startDate, 'MM-dd-yyyy');
        $scope.userEducation.endDate = $filter('date')(education.endDate, 'MM-dd-yyyy');
        $scope.userEducation.description = education.description;
        $scope.userEducation.id = education.id;
        $scope.isEduVisible = true;
    };

    //TO save educational details of user
    $scope.saveEducationDetails = function() {
        $('#loading-indicator').show();
        $scope.userEducation.startDate = new Date($("#eduStartDate").val());
        $scope.userEducation.endDate = new Date($("#eduEndDate").val());
        var user = new Object();
        user.id = $rootScope.userId;
        $scope.userEducation.userBean = user;
        $scope.userEducation.type = "EDUEXP";
        $http({
            url: '/profile/user/saveOrUpdate/userExperience',
            dataType: 'json',
            method: 'POST',
            data: $scope.userEducation,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            $scope.eduForm.$setPristine();
            $scope.eduForm.$setUntouched();
            $scope.submitted = false;
            if (response.error == null) {
                $scope.userEducation = "";

                $scope.educationDetailsList.forEach(function(education) {
                    if (education.id == response.data.id) {
                        var index = $scope.educationDetailsList.indexOf(education);
                        $scope.educationDetailsList.splice(index, 1);
                    }
                });
                $scope.educationDetailsList.unshift(response.data);
                $rootScope.educationSuccessMessage = "Save successfull";
                $rootScope.educationSuccess = true;
                $timeout(function() {
                    $rootScope.educationSuccess = false;
                    $scope.isEduVisible = false;
                }, 1500);
            } else {
                $rootScope.educationErrorMessage = response.error.errorMessage;
                $rootScope.educationError = true;
                $timeout(function() {
                    $rootScope.educationError = false;
                }, 1500);
            }

        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.error = error;
        });
    };
    // ==================== Capture Volunteer Histroy details ==============================


    // TO edit professional experience details
    $scope.editCurrentVolunteerExperienceDetail = function(experience) {
        $scope.userVolunteerExperience = new Object();
        $scope.userVolunteerExperience.organizationName = experience.organizationName;
        $scope.userVolunteerExperience.title = experience.title;
        $scope.userVolunteerExperience.cause = experience.cause;

        $scope.userVolunteerExperience.startDate = $filter('date')(experience.startDate, 'MM-dd-yyyy');
        $scope.userVolunteerExperience.endDate = $filter('date')(experience.endDate, 'MM-dd-yyyy');
        $scope.userVolunteerExperience.description = experience.description;
        $scope.userVolunteerExperience.id = experience.id;
        $scope.isVolExpVisible = true;
    };
    //TO save experience details of user
    $scope.saveVolunteerExperienceDetails = function() {

        $scope.userVolunteerExperience.startDate = new Date($("#volExpStartDate").val());
        $scope.userVolunteerExperience.endDate = new Date($("#volExpEndDate").val());
        var user = new Object();
        user.id = $rootScope.userId;
        $scope.userVolunteerExperience.userBean = user;
        $scope.userVolunteerExperience.type = "VLNTREXP";
        $('#loading-indicator').show();
        $http({
            url: '/profile/user/saveOrUpdate/userExperience',
            dataType: 'json',
            method: 'POST',
            data: $scope.userVolunteerExperience,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            $scope.volunteerExperience.$setPristine();
            $scope.volunteerExperience.$setUntouched();
            $scope.submitted = false;
            if (response.errror == null) {

                $scope.userExperience = "";
                $scope.volunteeringDetailsList.forEach(function(experience) {
                    if (experience.id == response.data.id) {
                        var index = $scope.volunteeringDetailsList.indexOf(experience);
                        $scope.volunteeringDetailsList.splice(index, 1);
                    }

                });
                $scope.volunteeringDetailsList.unshift(response.data);

                $scope.actionSuccess = "Save successfull";
                $scope.success = true;
                $timeout(function() {
                    $scope.success = false;
                    $scope.isVolExpVisible = false;
                }, 1500);
            } else {
                $scope.actionError = response.error.errorMessage;
                $scope.error = true;
                $timeout(function() {
                    $scope.error = false;
                }, 1500);
            }

        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.error = error;
        });
    };

    // =============================== End ============================================

});

completeProfile.controller('ProfessionalDetailsController', function($http, $scope, $rootScope, $location, $timeout, $filter) {
    $scope.linkedInProfessionalExperience = [];
    $scope.checkForLinkedInProfessionalDetails = function(){
	$http.get('/linkedin/importProfessionalDetails/second').success(function(data) {
	    if(data != "expired"){
		 $scope.getProfessionalData();
	    }else {
		$scope.requestAccessFromLinkedIn();
	    }	   
	});
    };
    
    $scope.requestAccessFromLinkedIn = function(){
	$http.get('/linkedin/professionalDetail').success(function(data) {
	    
	});
    };
    
    $scope.saveProfessionalDetails = function(){
	$scope.selectedList = [];
	 $scope.linkedInProfessionalExperience.forEach(function(experience){
	     if(experience.selected){
		 $scope.selectedList.push(experience);
	     }
	 });
	 
	 $http({
	            url: '/profile/linkedin/save/professionalDetail',
	            dataType: 'json',
	            method: 'POST',
	            data: $scope.selectedList,
	            headers: {
	                "Content-Type": "application/json"
	            }

	        }).success(function(response) {
	            
	           
	            if (response.errror == null) {
	                $location.path("/cp/experiences");
	            } 

	        }).error(function(error) {
	            $('#loading-indicator').hide();
	            $scope.error = error;
	        });
    };
    
    $scope.getProfessionalData = function(){
	
	$http({
	        url: '/profile/linkedin/session/professionalDetail',
	        dataType: 'json',
	        method: 'GET',
	        headers: {
	            "Content-Type": "application/json"
	        }
	    }).success(function(response) {
	        if (response.error == null) {
	            $scope.linkedInProfessionalExperience = response.data;
	        }

	    }).error(function(error) {
	        $scope.error = error;
	    });
	
    };
    

});

