/* 
 * Controller for complete profile step2 - Volunteering Histroy
 * 
 */
completeProfile.controller('InterestsController', function($http, $scope, $document, $rootScope, $location, $timeout, $filter, $anchorScroll) {
   	$anchorScroll("top");
    $scope.availabiltyList = [];
    $scope.userType = {};
    $scope.volPreferredTimeList = [{
        time: "10:00 AM - 01:00 PM",
        status: [{
            addClass: "",
            day: "Mon",
            startTime: "10:00:00",
            endTime: "13:00:00"
        }, {
            addClass: "",
            day: "Tue",
            startTime: "10:00:00",
            endTime: "13:00:00"
        }, {
            addClass: "",
            day: "Wed",
            startTime: "10:00:00",
            endTime: "13:00:00"
        }, {
            addClass: "",
            day: "Thu",
            startTime: "10:00:00",
            endTime: "13:00:00"
        }, {
            addClass: "",
            day: "Fri",
            startTime: "10:00:00",
            endTime: "13:00:00"
        }, {
            addClass: "",
            day: "Sat",
            startTime: "10:00:00",
            endTime: "13:00:00"
        }, {
            addClass: "",
            day: "Sun",
            startTime: "10:00:00",
            endTime: "13:00:00"
        }, {
            addClass: "",
            day: "General Holiday",
            startTime: "10:00:00",
            endTime: "13:00:00"
        }]
    }, {
        time: "01:00 PM - 04:00 PM",
        status: [{
            addClass: "",
            day: "Mon",
            startTime: "13:00:00",
            endTime: "16:00:00"
        }, {
            addClass: "",
            day: "Tue",
            startTime: "13:00:00",
            endTime: "16:00:00"
        }, {
            addClass: "",
            day: "Wed",
            startTime: "13:00:00",
            endTime: "16:00:00"
        }, {
            addClass: "",
            day: "Thu",
            startTime: "13:00:00",
            endTime: "16:00:00"
        }, {
            addClass: "",
            day: "Fri",
            startTime: "13:00:00",
            endTime: "16:00:00"
        }, {
            addClass: "",
            day: "Sat",
            startTime: "13:00:00",
            endTime: "16:00:00"
        }, {
            addClass: "",
            day: "Sun",
            startTime: "13:00:00",
            endTime: "16:00:00"
        }, {
            addClass: "",
            day: "General Holiday",
            startTime: "13:00:00",
            endTime: "16:00:00"
        }]
    }, {
        time: "04:00 PM - 08:00 PM",
        status: [{
            addClass: "",
            day: "Mon",
            startTime: "16:00:00",
            endTime: "20:00:00"
        }, {
            addClass: "",
            day: "Tue",
            startTime: "16:00:00",
            endTime: "20:00:00"
        }, {
            addClass: "",
            day: "Wed",
            startTime: "16:00:00",
            endTime: "20:00:00"
        }, {
            addClass: "",
            day: "Thu",
            startTime: "16:00:00",
            endTime: "20:00:00"
        }, {
            addClass: "",
            day: "Fri",
            startTime: "16:00:00",
            endTime: "20:00:00"
        }, {
            addClass: "",
            day: "Sat",
            startTime: "16:00:00",
            endTime: "20:00:00"
        }, {
            addClass: "",
            day: "Sun",
            startTime: "16:00:00",
            endTime: "20:00:00"
        }, {
            addClass: "",
            day: "General Holiday",
            startTime: "16:00:00",
            endTime: "20:00:00"
        }]
    }];
    $scope.masterSkills = [];
    $scope.userSkills = [];
    $scope.selectedAll = true;
    $scope.masterCauseList = [];
    $scope.userCauses = [];
    $scope.selectedALLCause = true;
    $scope.userTypeMasterList = [];
    $scope.userTypeList = [];
    $http({
        url: '/profile/interests/user/' + $rootScope.userId,
        dataType: 'json',
        method: 'GET',
        headers: {
            "Content-Type": "application/json"
        },
        cache: false

    }).success(function(response) {
        if (response.error == null) {

            //volunteer location preference
            $scope.locationPrefDTO = response.data.locationPreference;
            if (typeof(response.data.locationPreference) != undefined && response.data.locationPreference != null) {
                $scope.includeSurroundingAreas = response.data.locationPreference.includeSurroundingAreas;
            }

            //volunteer availablity
            $scope.availabiltyList = response.data.availabiltyList;
            $scope.updateVolunteerAvailabiltyList($scope.availabiltyList);

            //skills
            $scope.masterSkills = response.data.skills.masterSkills;

            //TO set the flag
            response.data.skills.userSkills.forEach(function(skill) {
                $scope.masterSkills.forEach(function(masterSkill) {
                    if (skill.jobTypeBean.name == masterSkill.name) {
                        masterSkill.status = skill.status;
                    }
                });
            });
            //TO set the flag
            $scope.masterSkills.forEach(function(skill) {
                if (skill.name == "ALL") {
                    $scope.selectedAll = skill.status;
                }
            });
            //to set user skill list
            response.data.skills.userSkills.forEach(function(skill) {
                if (skill.name != "ALL" && skill.status) {
                    $scope.userSkills.push(skill);
                }
            });

            //causes
            $scope.masterCauseList = response.data.causes.masterCauses;

            //TO set the flag
            response.data.causes.userCauses.forEach(function(cause) {
                $scope.masterCauseList.forEach(function(masterCause) {
                    if (cause.causeBean.name == masterCause.name) {
                        masterCause.active = cause.status;
                    }
                });
            });
            //TO set the flag
            $scope.masterCauseList.forEach(function(cause) {
                if (cause.name == "ALL") {
                    $scope.selectedALLCause = cause.active;
                }
            });
            //add userCauseList
            response.data.causes.userCauses.forEach(function(cause) {
                if (cause.causeBean.name != "ALL" && cause.status) {
                    $scope.userCauses.push(cause);
                }
            });


        } else {
            $scope.actionError = response.error.errorMessage;
            $scope.error = true;
        }

    }).error(function(error) {
        $scope.error = error;
    });

    $scope.updateVolunteerAvailabiltyList = function(responseList) {
        $scope.volPreferredTimeList.forEach(function(list) {
            list.status.forEach(function(presentDayStatus) {
                responseList.forEach(function(response) {
                    if (presentDayStatus.startTime == response.startTime && presentDayStatus.endTime == response.endTime && presentDayStatus.day == response.day) {
                        if (response.isAvailable) {
                            presentDayStatus.addClass = "active";
                        } else {
                            presentDayStatus.addClass = "";
                        }
                    }
                    if (response.day == "com") {
                        if (response.commitment == 2)
                        	$scope.inperson = true;
                        else if (response.commitment == 1) 
                        	$scope.remote = true;
                        else if (response.commitment == 3) {
                        	$scope.inperson = true;
                        	$scope.remote = true;
                        }
                    }
                });
            });
        });
    };
    $scope.updateCurrentAvailabilityStatus = function(response) {
        $scope.volPreferredTimeList.forEach(function(list) {
            list.status.forEach(function(presentDayStatus) {
                if (presentDayStatus.startTime == response.startTime && presentDayStatus.endTime == response.endTime && presentDayStatus.day == response.day) {
                    if (response.isAvailable) {
                        presentDayStatus.addClass = "active";
                    } else {
                        presentDayStatus.addClass = "";
                    }
                }
            });
        });
    };

    /*$scope.changeCauseALLStatus = function(){
    	var flag = true;
    	$scope.masterCauseList.forEach(function(cause) {
        	if(!cause.active && cause.name != "ALL"){
        		flag = false;
        	} 
        	if(cause.name == "ALL"){
        		cause.active = flag;
        	}
        });
        $scope.selectedALLCause = flag;
    };
	
    $scope.changeJobTypeALLStatus = function(){
    	var flag = true;
    	$scope.masterSkills.forEach(function(skill) {
        	if(!skill.status){
        		flag = false;
        		if(skill.name == "ALL"){
    	    		skill.status = flag;
    	    	}
        	} 
        	
        });
        $scope.selectedAll = flag;
    };*/

    // ===============================Capture of volunteer availabilty changes=========
    $scope.saveVolAvailability = function(availabilty) {
        $scope.volAvail = new Object();
        var user = new Object();
        user.id = $rootScope.userId;
        $scope.volAvail.user = user;
        $scope.volAvail.day = availabilty.day;
        $scope.volAvail.startTime = availabilty.startTime;
        $scope.volAvail.endTime = availabilty.endTime;
        if (availabilty.addClass == "active") {
            $scope.volAvail.isAvailable = false;
        } else {
            $scope.volAvail.isAvailable = true;
        }

        $('#loading-indicator').show();
        $http({
            url: '/profile/availability/user/' + $scope.volAvail.user.id + '/add',
            dataType: 'json',
            method: 'POST',
            data: $scope.volAvail,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            if (response.errror == null) {
                $scope.updateCurrentAvailabilityStatus(response.data);
            } else {
                $scope.availabilityErrorMessage = response.error.errorMessage;
                $scope.availableError = true;
                $timeout(function() {
                    $scope.availableError = false;
                }, 1500);
            }

        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.error = error;
        });
    };

    // ===============================capture preferred location changes=========================
    $scope.includeSurroundingAreas = true;

    $scope.saveLocationPrefDetails = function() {

        var user = new Object();
        user.id = $rootScope.userId;
        $scope.locationPrefDTO.user = user;
        $scope.locationPrefDTO.includeSurroundingAreas = $scope.includeSurroundingAreas;
        $('#loading-indicator').show();
        $http({
            url: '/profile/availability/location/user/' + $scope.locationPrefDTO.user.id + '/preferences',
            dataType: 'json',
            method: 'POST',
            data: $scope.locationPrefDTO,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            if (response.errror == null) {
                $scope.locationSuccessMessage = "Save successfull";
                $scope.locationSuccess = true;
                $timeout(function() {
                    $scope.locationSuccess = false;
                }, 1500);
            } else {
                $scope.locationErrorMessage = response.error.errorMessage;
                $scope.locationError = true;
                $timeout(function() {
                    $scope.locationError = false;
                }, 1500);
            }
        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.error = error;
        });

    };

    //===========================================Skills Section===============================================

    //TO check and uncheck the boxes
    $scope.checkAll = function() {
        $scope.masterSkills.forEach(function(skill) {
            skill.status = !$scope.selectedAll;
        });
        $scope.selectedAll = !$scope.selectedAll;
    };

    $scope.uncheckALL = function() {
        $scope.selectedAll = false;
    };

    //TO save skills

    $scope.saveSkills = function() {
        $('#loading-indicator').show();
        $scope.userSkillsList = [];
        $scope.masterSkills.forEach(function(skill) {
            var partyJobTypeXref = new Object();
            var partyBean = new Object();
            var user = new Object();
            user.id = $rootScope.userId;
            partyBean.userBean = user;
            partyJobTypeXref.partyBean = partyBean;
            var JobTypeDTO = new Object();
            JobTypeDTO.name = skill.name;
            JobTypeDTO.id = skill.id;
            JobTypeDTO.description = skill.description;
            partyJobTypeXref.jobTypeBean = JobTypeDTO;
            partyJobTypeXref.status = skill.status;
            $scope.userSkillsList.push(partyJobTypeXref);
        });
        $http({
            url: '/profile/user/save/skills',
            dataType: 'json',
            method: 'POST',
            data: $scope.userSkillsList,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            if (response.error == null) {
                $scope.userSkills = [];
                response.data.forEach(function(response) {
                    if (response.name != "ALL" && response.status) {
                        $scope.userSkills.push(response);
                    }
                });
                $rootScope.skillSuccessMessage = "Save successfull";
                $rootScope.skillSuccess = true;
                $timeout(function() {
                    $rootScope.skillSuccess = false;
                }, 1500);
            } else {
                $rootScope.skillErrorMessage = response.error.errorMessage;
                $rootScope.skillError = true;
                $timeout(function() {
                    $rootScope.skillError = false;
                }, 1500);
            }
        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.error = error;
        });
    };

    //======================================causes====================================================


    //TO check and uncheck the boxes
    $scope.checkAllCauses = function() {
        $scope.masterCauseList.forEach(function(cause) {
            cause.active = !$scope.selectedALLCause;
        });
        $scope.selectedALLCause = !$scope.selectedALLCause;
    };
    
    $scope.checkCom = function() {
      var checked = 0;
      if( $scope.inperson &&  $scope.remote)
    	  checked = 3;
      if( $scope.inperson &&  !$scope.remote)
    	  checked = 2;
      if( !$scope.inperson &&  $scope.remote)
    	  checked = 1;
      $scope.volAvail = new Object();
      var user = new Object();
      user.id = $rootScope.userId;
      $scope.volAvail.user = user;
      $scope.volAvail.day = "com";
      $scope.volAvail.startTime = "10:00:00";
      $scope.volAvail.endTime = "13:00:00";
      $scope.volAvail.commitment = checked;

      $('#loading-indicator').show();
      $http({
          url: '/profile/availability/user/' + $scope.volAvail.user.id + '/add',
          dataType: 'json',
          method: 'POST',
          data: $scope.volAvail,
          headers: {
              "Content-Type": "application/json"
          }

      }).success(function(response) {
          $('#loading-indicator').hide();
      }).error(function(error) {
          $('#loading-indicator').hide();
          $scope.error = error;
      });
    };


    //TO save skills

    $scope.saveCauseList = function() {
        $('#loading-indicator').show();
        $scope.userCausesDetailsList = [];
        $scope.masterCauseList.forEach(function(cause) {
            var partyCauseTypeXref = new Object();
            var user = new Object();
            user.id = $rootScope.userId;
            var partyBean = new Object();
            partyBean.userBean = user;
            partyCauseTypeXref.partyBean = partyBean;
            var causeBean = new Object();
            causeBean.name = cause.name;
            causeBean.id = cause.id;
            causeBean.description = cause.description;
            partyCauseTypeXref.causeBean = causeBean;
            partyCauseTypeXref.status = cause.active;
            $scope.userCausesDetailsList.push(partyCauseTypeXref);
        });
        $http({
            url: '/profile/user/save/causes',
            dataType: 'json',
            method: 'POST',
            data: $scope.userCausesDetailsList,
            headers: {
                "Content-Type": "application/json"
            }

        }).success(function(response) {
            $('#loading-indicator').hide();
            if (response.error == null) {
                $scope.userCauses = [];
                response.data.forEach(function(response) {
                    if (response.name != "ALL" && response.status) {
                        $scope.userCauses.push(response);
                    }
                });
                $rootScope.causeSuccessMessage = "Save successfull";
                $rootScope.causeSuccess = true;
                $timeout(function() {
                    $rootScope.causeSuccess = false;
                }, 1500);
            } else {
                $rootScope.causeErrorMessage = response.error.errorMessage;
                $rootScope.causeError = true;
                $timeout(function() {
                    $rootScope.causeError = false;
                }, 1500);
            }
        }).error(function(error) {
            $('#loading-indicator').hide();
            $scope.error = error;
        });
    };
});