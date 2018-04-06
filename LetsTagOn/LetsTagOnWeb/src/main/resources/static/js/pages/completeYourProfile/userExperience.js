/* 
 * Controller for complete profile step2 - experiences
 * 
 */
completeProfile.controller("ExperienceController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout,
    $filter,
    $anchorScroll
) {
    $anchorScroll("top");
    $scope.userEducation = {};
    $scope.userExperience = {};
    $scope.userVolunteerExperience = {};
    $scope.masterSkills = [];
    $scope.masterCauseList = [];
    $scope.isRequired = false;
    $scope.value = false;
    $scope.validEndDate=true;
    $scope.formReady = false;
   
    $http({
        url: "/profile/interests/user/" + $rootScope.userId,
        dataType: "json",
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
        cache: false
    })
        .success(function(response) {
            console.log("in user experience: ", response);
            if (!response.error && response.data) {
                //skills
                $scope.masterSkills = response.data.skills.masterSkills;

                //causes
                $scope.masterCauseList = response.data.causes.masterCauses;

                $scope.formReady = true;
            } else {
                $scope.actionError = response.error.errorMessage;
                console.log("error is: ", $scope.actionError);
                $scope.error = true;
            }
        })
        .error(function(error) {
            $scope.error = error;
        });
    /*Highlight Element on focus and blur*/
    $("input").on("focus", function() {
        $(this)
            .prev()
            .addClass("focus-effect");
        $(this).addClass("focus-effect-input");
        $(this)
            .next()
            .addClass("focus-effect");
    });
    $("input").on("blur", function() {
        $(this)
            .prev()
            .removeClass("focus-effect");
        $(this).removeClass("focus-effect-input");
        $(this)
            .next()
            .removeClass("focus-effect");
    });
    $("textarea").on("focus", function() {
        $(this)
            .prev()
            .addClass("focus-effect");
        $(this).addClass("focus-effect-input");
        $(this)
            .next()
            .addClass("focus-effect");
    });
    $("textarea").on("blur", function() {
        $(this)
            .prev()
            .removeClass("focus-effect");
        $(this).removeClass("focus-effect-input");
        $(this)
            .next()
            .removeClass("focus-effect");
    });

    $("#volExpStartDate").on('focus', function(){
        if (!$scope.formReady) {
     
            $(this).val('');
        }
        
    })
    $("#volExpEndDate").on('focus', function(){
        if (!$scope.formReady) {
           
            $(this).val('');
        }
    })
    $scope.isExpVisible = false;
    $scope.isEduVisible = false;
    $scope.isVolExpVisible = false;

    $scope.$watchGroup(['userVolunteerExperience.cause', 'userVolunteerExperience.skill'], function(){
        // console.log('replacing &');
        if ($scope.userVolunteerExperience.cause) {
            $scope.userVolunteerExperience.cause=$scope.userVolunteerExperience.cause.replace("&amp;", "&");
        }
        if ($scope.userVolunteerExperience.skill) {
            $scope.userVolunteerExperience.skill=$scope.userVolunteerExperience.skill.replace("&amp;", "&");
        }
        
    })
    $scope.redirectProfilePage = function() {
        $location.path("/profile/user/" + $rootScope.userId);
    };
    $scope.validateEndDate = function() {
        console.log('validating end date');
        
        // $scope.userVolunteerExperience.startDate = $scope.expStartDate;
        // $scope.userVolunteerExperience.endDate = $scope.expEndDate;
        if (Date.parse($scope.userVolunteerExperience.startDate) >  Date.parse($scope.userVolunteerExperience.endDate)) {
            $scope.validEndDate = false;
            return;
        }
        $scope.validEndDate = true;

    }
    $scope.showVolHiddenContainer = function() {
        // $scope.volunteerExperience = "";
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
    $("#eduStartDate")
        .datepicker({
            format: "yyyy-mm-dd",
            todayHighlight: true
        })
        .on("changeDate", function(e) {
            $scope.userEducation.startDate = $(this).val();
            $(this).focus();
            $(this).datepicker("hide");
        });
    $("#eduEndDate")
        .datepicker({
            format: "yyyy-mm-dd",
            todayHighlight: true
        })
        .on("changeDate", function(e) {
            $scope.userEducation.endDate = $(this).val();
            $(this).focus();
            $(this).datepicker("hide");
        });
    $("#expStartDate")
        .datepicker({
            format: "yyyy-mm-dd",
            todayHighlight: true
        })
        .on("changeDate", function(e) {
            $scope.userExperience.startDate = $(this).val();
            $(this).focus();
            $(this).datepicker("hide");
        });
    $("#expEndDate")
        .datepicker({
            format: "yyyy-mm-dd",
            todayHighlight: true,
           
        })
        .on("changeDate", function(e) {
            $scope.userVolunteerExperience.endDate = $(this).val();
            $(this).focus();
            $(this).datepicker("hide");
            
        });

    $("#volExpStartDate")
        .datepicker({
            format: "yyyy-mm-dd",
            todayHighlight: true
           
        })
        .on("changeDate", function(e) {
          
             angular.element($('#volExpStartDate')).triggerHandler('input');
            
            $(this).focus();
            $(this).datepicker("hide");
        });
    $("#volExpEndDate")
        .datepicker({
            format: "yyyy-mm-dd",
            todayHighlight: true
        })
        .on("changeDate", function(e) {
           angular.element($('#volExpEndDate')).triggerHandler('input');
            $(this).focus();
            $(this).datepicker("hide");
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
            url: "/profile/experience/user/" + $rootScope.userId,
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (!response.error && response.data) {
                    $scope.educationDetailsList =
                        response.data.educationDetails;
                    $scope.professionalDetailsList =
                        response.data.professionalDetails;
                    //Volunteer Histroy
                    $scope.volunteeringDetailsList =
                        response.data.volunteeringDetails;
                } else {
                    $rootScope.actionError = response.error.errorMessage;
                    $rootScope.error = true;
                }
            })
            .error(function(error) {
                $scope.error = error;
            });
    };

    // TO edit professional experience details
    $scope.editCurrentExperienceDetail = function(experience) {
        $scope.userExperience = new Object();
        $scope.userExperience.organizationName = experience.organizationName;
        $scope.userExperience.title = experience.title;
        $scope.userExperience.location = experience.location;
        $scope.userExperience.startDate = $filter("date")(
            experience.startDate,
            "MM-dd-yyyy"
        );
        $scope.userExperience.endDate = $filter("date")(
            experience.endDate,
            "MM-dd-yyyy"
        );
        $scope.userExperience.description = experience.description;
        $scope.userExperience.id = experience.id;
        $scope.isExpVisible = true;
    };

    $scope.GetSkill = function() {
        var skillDes = $("#skillarea option:selected").html();
        $scope.userVolunteerExperience.skill = skillDes;
        if (
            $scope.userVolunteerExperience.skill == "Other" ||
            $scope.userVolunteerExperience.skill == "Hobby"
        ) {
            $scope.isRequired = true;
            $("#comment").attr("readonly", false);
        } else {
            $("#comment").attr("readonly", true);
            $("#comment").val("");
            $scope.isRequired = false;
        }
    };

    $scope.GetCause = function() {
        var causeDes = $("#causearea option:selected").html();
        $scope.userVolunteerExperience.cause = causeDes;
        if ($scope.userVolunteerExperience.cause == "Other") {
            $("#other").attr("readonly", false);
            $scope.value = true;
        } else {
            $("#other").attr("readonly", true);
            $("#other").val("");
            $scope.value = false;
        }
    };

    //TO save experience details of user
    $scope.saveExperienceDetails = function() {
        $("#loading-indicator").show();
        $scope.userExperience.startDate = new Date($("#expStartDate").val());
        $scope.userExperience.endDate = new Date($("#expEndDate").val());
        var user = new Object();
        user.id = $rootScope.userId;
        $scope.userExperience.userBean = user;
        $scope.userExperience.type = "PRFEXP";
        $http({
            url: "/profile/user/saveOrUpdate/userExperience",
            dataType: "json",
            method: "POST",
            data: $scope.userExperience,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $("#loading-indicator").hide();
                $scope.profForm.$setPristine();
                $scope.profForm.$setUntouched();
                $scope.submitted = false;
                if (response.errror == null) {
                    $scope.professionalDetailsList.forEach(function(
                        experience
                    ) {
                        if (experience.id == response.data.id) {
                            var index = $scope.professionalDetailsList.indexOf(
                                experience
                            );
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
            })
            .error(function(error) {
                $("#loading-indicator").hide();
                $scope.error = error;
            });
    };
    // TO edit education details
    $scope.editCurrentEducationDetail = function(education) {
        $scope.userEducation = new Object();
        $scope.userEducation.organizationName = education.organizationName;
        $scope.userEducation.course = education.course;
        $scope.userEducation.degree = education.degree;
        $scope.userEducation.startDate = $filter("date")(
            education.startDate,
            "MM-dd-yyyy"
        );
        $scope.userEducation.endDate = $filter("date")(
            education.endDate,
            "MM-dd-yyyy"
        );
        $scope.userEducation.description = education.description;
        $scope.userEducation.id = education.id;
        $scope.isEduVisible = true;
    };

    //TO save educational details of user
    $scope.saveEducationDetails = function() {
        $("#loading-indicator").show();
        $scope.userEducation.startDate = new Date($("#eduStartDate").val());
        $scope.userEducation.endDate = new Date($("#eduEndDate").val());
        var user = new Object();
        user.id = $rootScope.userId;
        $scope.userEducation.userBean = user;
        $scope.userEducation.type = "EDUEXP";
        $http({
            url: "/profile/user/saveOrUpdate/userExperience",
            dataType: "json",
            method: "POST",
            data: $scope.userEducation,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $("#loading-indicator").hide();
                $scope.eduForm.$setPristine();
                $scope.eduForm.$setUntouched();
                $scope.submitted = false;
                if (response.error == null) {
                    $scope.userEducation = "";

                    $scope.educationDetailsList.forEach(function(education) {
                        if (education.id == response.data.id) {
                            var index = $scope.educationDetailsList.indexOf(
                                education
                            );
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
                    $rootScope.educationErrorMessage =
                        response.error.errorMessage;
                    $rootScope.educationError = true;
                    $timeout(function() {
                        $rootScope.educationError = false;
                    }, 1500);
                }
            })
            .error(function(error) {
                $("#loading-indicator").hide();
                $scope.error = error;
            });
    };
    // ==================== Capture Volunteer Histroy details ==============================

    // TO edit professional experience details
    $scope.editCurrentVolunteerExperienceDetail = function(experience) {
        console.log('volexperience to be edited: ', experience);
        $scope.userVolunteerExperience = new Object();
        $scope.userVolunteerExperience.organizationName =
            experience.organizationName;
        $scope.userVolunteerExperience.title = experience.title;
        $scope.userVolunteerExperience.cause = experience.cause;
        $scope.userVolunteerExperience.skill = experience.skill;
        if ($scope.userVolunteerExperience.cause == "Other") {
            $("#other").attr("readonly", false);
            $scope.value = true;
        } else {
            $("#other").attr("readonly", true);
            $("#other").val("");
            $scope.value = false;
        }
        if (
            $scope.userVolunteerExperience.skill == "Other" ||
            $scope.userVolunteerExperience.skill == "Hobby"
        ) {
            $scope.isRequired = true;
            $("#comment").attr("readonly", false);
        } else {
            $("#comment").attr("readonly", true);
            $("#comment").val("");
            $scope.isRequired = false;
        }
        // $scope.userVolunteerExperience.causearea = experience.causearea;
        // $scope.userVolunteerExperience.skillarea = experience.skillarea;
        $scope.userVolunteerExperience.comment = experience.comment;
        $scope.userVolunteerExperience.other = experience.other;
        $scope.userVolunteerExperience.hours = experience.hours;

        $scope.userVolunteerExperience.startDate = $filter("date")(
            experience.startDate,
            "MM-dd-yyyy"
        );
        $scope.userVolunteerExperience.endDate = $filter("date")(
            experience.endDate,
            "MM-dd-yyyy"
        );
        $scope.userVolunteerExperience.description = experience.description;
        $scope.userVolunteerExperience.id = experience.id;
        $scope.isVolExpVisible = true;

        //since the edit form opens on the top, the user should be taken there when he clicks on edit
       $anchorScroll("volExpForm");
    };
    //TO save experience details of user
    $scope.saveVolunteerExperienceDetails = function() {
        console.log('saving vol experience');
        
        $scope.userVolunteerExperience.startDate = new Date(
            $("#volExpStartDate").val()
        );
        $scope.userVolunteerExperience.endDate = new Date(
            $("#volExpEndDate").val()
        );

        if (!$scope.validEndDate) return;
        
        var user = new Object();
        user.id = $rootScope.userId;
        $scope.userVolunteerExperience.userBean = user;
        $scope.userVolunteerExperience.type = "VLNTREXP";
        // $scope.userVolunteerExperience.cause.replace("&amp", "&");
        // $scope.userVolunteerExperience.cause.skill("&amp", "&");
        console.log('userVolunteerExperience obj being saved: ', $scope.userVolunteerExperience);
        $("#loading-indicator").show();
        $http({
            url: "/profile/user/saveOrUpdate/userExperience",
            dataType: "json",
            method: "POST",
            data: $scope.userVolunteerExperience,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $("#loading-indicator").hide();
                $scope.volunteerExperience.$setPristine();
                $scope.volunteerExperience.$setUntouched();
                $scope.submitted = false;
                if (response.errror == null) {
                    $scope.userExperience = "";
                    $scope.volunteeringDetailsList.forEach(function(
                        experience
                    ) {
                        if (experience.id == response.data.id) {
                            var index = $scope.volunteeringDetailsList.indexOf(
                                experience
                            );
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
            })
            .error(function(error) {
                $("#loading-indicator").hide();
                $scope.error = error;
            });
    };

    // =============================== End ============================================
});

completeProfile.controller("ProfessionalDetailsController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout,
    $filter
) {
    $scope.linkedInProfessionalExperience = [];
    $scope.checkForLinkedInProfessionalDetails = function() {
        $http
            .get("/linkedin/importProfessionalDetails/second")
            .success(function(data) {
                if (data != "expired") {
                    $scope.getProfessionalData();
                } else {
                    $scope.requestAccessFromLinkedIn();
                }
            });
    };

    $scope.requestAccessFromLinkedIn = function() {
        $http.get("/linkedin/professionalDetail").success(function(data) {});
    };

    $scope.saveProfessionalDetails = function() {
        $scope.selectedList = [];
        $scope.linkedInProfessionalExperience.forEach(function(experience) {
            if (experience.selected) {
                $scope.selectedList.push(experience);
            }
        });

        $http({
            url: "/profile/linkedin/save/professionalDetail",
            dataType: "json",
            method: "POST",
            data: $scope.selectedList,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response.errror == null) {
                    $location.path("/cp/experiences");
                }
            })
            .error(function(error) {
                $("#loading-indicator").hide();
                $scope.error = error;
            });
    };

    $scope.getProfessionalData = function() {
        $http({
            url: "/profile/linkedin/session/professionalDetail",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response.error == null) {
                    $scope.linkedInProfessionalExperience = response.data;
                }
            })
            .error(function(error) {
                $scope.error = error;
            });
    };
});
