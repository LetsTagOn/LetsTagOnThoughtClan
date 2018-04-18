/* 
 * JS File which inclused controllers for Opportunity - create,edit,view,upload banner image
 */

var opportunity = angular.module("opportunityModule", []);
opportunity.controller("UploadOportunityImageController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    $scope.myImage = "";
    $scope.myCroppedImage = "";

    var handleFileSelect = function(evt) {
        var file = evt.currentTarget.files[0];
        var reader = new FileReader();
        reader.onload = function(evt) {
            $scope.$apply(function($scope) {
                $scope.myImage = evt.target.result;
            });
        };
        reader.readAsDataURL(file);
    };
    angular
        .element(document.querySelector("#profilePic"))
        .on("change", handleFileSelect);

    var dataURItoBlob = function(dataURI) {
        var binary = atob(dataURI.split(",")[1]);
        var mimeString = dataURI
            .split(",")[0]
            .split(":")[1]
            .split(";")[0];
        var array = [];
        for (var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        return new Blob([new Uint8Array(array)], {
            type: mimeString
        });
    };
    $scope.cancelOppImageCapture = function() {
        $scope.myImage = "";
        $scope.myCroppedImage = "";
        $("#profilePic").val("");
    };
    $scope.saveOpportunityImage = function(file) {
        var blob = dataURItoBlob(file);
        $("#loading-indicator").show();
        var uploadfile = new FormData();
        uploadfile.append("file", blob);
        var path = $location.path();
        var url = "";
        if ($location.path().indexOf("/opportunity/program/edit/") > -1) {
            url = path.split("/opportunity/program/edit/");
        } else {
            url = path.split("/opportunity/edit/");
        }

        $http
            .post("/opportunity/uploadBannerImage/" + url[1], uploadfile, {
                transformRequest: angular.identity,
                headers: {
                    "Content-Type": undefined
                }
            })
            .success(function(response) {
                $("#loading-indicator").hide();
                $("#image-crop-modal").modal("hide");
                $("body").removeClass("modal-open");
                $(".modal-backdrop").remove();
                $(".lto-opp-banner-img").attr("src", "");
                $(".lto-opp-banner-img").attr(
                    "src",
                    $rootScope.baseUrl +
                        "/opportunity/getBannerImage?key=" +
                        response.data
                );
                $rootScope.ltoSuccessMessage = "Image Uploaded Successfully";
                $rootScope.toggleLtoSuccessModal();
                $scope.cancelOppImageCapture();
                $(".lto-success-modal-dialog").css({
                    top: "200px"
                });
            })
            .error(function(error) {
                $("#loading-indicator").hide();
                console.log(
                    "exception caught in catch while saving personal information" +
                        error +
                        " for user=" +
                        $rootScope.userId
                );
            });
    };
});

// =========================================== Controller to show all the created opportunities ==================================================
opportunity.controller("OpportunityController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout,
    $filter
) {
    // console.info("OpportunityController called");
    $scope.listOpportunity = {};
    $scope.getAllOpportunity = function() {
        $http({
            url: "/user/opportunity",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                console.log("listing opportunities: ", response.data);
                $scope.listOpportunity = response.data;
            })
            .error(function(error) {
                console.info("-- " + error);
            });
    };

    $scope.showDetailPage = function(id) {
        // console.info(id);
        $location.path("/view/opportunity/" + id);
    };

    $scope.paintUserProfileDetails = function(userId) {
        $location.path("/profile/user/" + userId);
    };
});

// ======================================================= End =====================================================================

// ======================================================= Controller to show the upcoming opportunites ============================
opportunity.controller("UpcomingOpportunityController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout,
    $filter
) {
    $scope.listOpportunity = {};
    $scope.getAllOpportunity = function() {
        $http({
            url: "/opportunity/upcoming?status=true&size=100",
            dataType: "json",
            method: "GET",

            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.participationDetailList = response.searchResult;
            })
            .error(function() {
                console.info("-- " + error);
            });
    };

    $scope.showDetailPage = function(id) {
        console.info(id);
        $location.path("/view/opportunity/" + id);
    };

    $scope.paintUserProfileDetails = function(userId) {
        $location.path("/profile/user/" + userId);
    };
});

//======================================================= End =====================================================================

//======================================================= Controller to Create opportunity =====================================================================
opportunity.controller("CreateOpportunityController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    $scope.gPlace;
    $scope.event = {};
    /* Highlight Element on focus and blur */
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
    $scope.showCreateOpportunityModal = false;
    $scope.toggleCreateOpportunityModal = function() {
        $scope.showCreateOpportunityModal = !$scope.showCreateOpportunityModal;
    };

    $scope.eventClick = "program";
    // $("#startDateEvent").datetimepicker({});
    $("#eventStartDate")
        .datepicker({
            format: "dd/mm/yyyy"
        })
        .on("changeDate", function(e) {
            $(this).datepicker("hide");
        });
    // $("#endDateEvent").datetimepicker({});
    $("#eventEndDate")
        .datepicker({
            format: "dd/mm/yyyy"
        })
        .on("changeDate", function(e) {
            $(this).datepicker("hide");
        });
    $scope.displayStartDate = function() {
        $scope.event.dateStart = $("#eventStartDate").val();
    };
    $scope.displayEndDate = function() {
        $scope.event.dateEnd = $("#eventEndDate").val();
    };
    // save call to create a program
    $scope.program = {};
    $scope.createProgram = function() {
        console.info("create Program called");
        // console.info($scope.program.name + " = " + $scope.program.description);
        var program = new Object();
        program.name = $scope.program.name;
        program.description = $scope.program.description;
        program.type = "PROGRAM";
        program.createdBy  = $rootScope.userId;

        $http({
            url: "/opportunity",
            dataType: "json",
            method: "POST",
            data: program,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                console.log("successfully created program...");
                console.log("received response...", response);
                if (response && response.data.id) {
                    console.log("redirecting to program edit page");
                    $location.path(
                        "/view/opportunity/program/edit/" + response.data.id
                    );
                } else {
                    $scope.programSuccess = false;
                    $scope.programError = true;
                }
            })
            .error(function(error) {
                console.info("-- " + error);
            })
            .finally(function() {
                $timeout(function() {
                    $scope.programSuccess = false;
                    $scope.programError = false;
                }, 3000);
            });
    };
    //  End

    $scope.event.addressBean = {};
    $scope.createEvent = function() {
        console.info("create Event called");
        var event = new Object();
        event.name = $scope.event.name;
        event.description = $scope.event.description;
        event.dateStart = new Date($("#startDateEvent").val());
        event.dateEnd = new Date($("#endDateEvent").val());
        event.type = "EVENT";
        var address = new Object();
        address.city = $scope.event.addressBean.city;
        address.country = $scope.event.addressBean.country;
        address.postalCode = $scope.event.addressBean.postalCode;
        address.state = $scope.event.addressBean.state;
        address.street = $scope.event.addressBean.street;
        event.addressBean = address;
        $http({
            url: "/opportunity",
            dataType: "json",
            method: "POST",
            data: event,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response.data.id != undefined)
                    $location.path(
                        "/view/opportunity/edit/" + response.data.id
                    );
                else console.info("somthing went wrong");
            })
            .error(function() {});
    };
});
//======================================================= End =====================================================================

//======================================================= Controller for edit functionality on event =====================================================================
opportunity.controller("OpportunityEditController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout,
    $filter
) {
    var url = window.location.href.split("/view/opportunity/edit/");
    var oppID = url[1];

    var urlProg = window.location.href.split("/view/opportunity/program/edit/");
    var programID = urlProg[1] ? urlProg[1] : "";
    $scope.eventShow = false;

    $scope.opportunityJobTypes = [];
    $scope.jobSectionDisplay = false;
    $scope.saveJobRole = "create";
    $scope.areaFound = true;
    $scope.manualAddress = false;
    $scope.isField = true;
    $scope.modeSelected = false;
    $scope.address = new Object();

    $scope.ShowAddress = function(value) {
        //If DIV is visible it will be hidden and vice versa.
        $scope.isField = value == "Y";

        $scope.isField
            ? ($scope.address = {
                  name: "onfield"
              })
            : ($scope.address = {
                  name: "virtual"
              });
    };

    $scope.EnterManualAddress = function() {
        $scope.areaFound = false;
        document.getElementById("latitude").value = "";
        document.getElementById("longitude").value = "";
        $scope.manualAddress = true;
        $scope.isField = false;
        // $scope.areaFound = true;
    };

    $scope.GetAddressFromGoogleApi = function() {
        $scope.areaFound = true;
        document.getElementById("latitude").value = "";
        document.getElementById("longitude").value = "";
        $scope.manualAddress = false;
        $scope.isField = true;
    };

    $scope.addJobSectionDisplays = function() {
        console.info("---");
        $scope.jobSectionDisplay = true;
    };

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
    $("#eventStartDate")
        .datepicker({})
        .on("changeDate", function(e) {
            $scope.event.dateStart = $(this).val();
            $(this).focus();
            $(this).datepicker("hide");
        });
    $scope.displayStartDate = function() {
        $("#eventStartDate")
            .datepicker({})
            .on("changeDate", function(e) {
                $scope.event.dateStart = $(this).val();
                $(this).focus();
                $(this).datepicker("hide");
            });
    };
    $("#eventEndDate")
        .datepicker({})
        .on("changeDate", function(e) {
            $scope.event.dateEnd = $(this).val();
            $(this).focus();
            $(this).datepicker("hide");
        });
    $scope.displayEndDate = function() {
        $("#eventEndDate")
            .datepicker({})
            .on("changeDate", function(e) {
                $scope.event.dateEnd = $(this).val();
                $(this).focus();
                $(this).datepicker("hide");
            });

        $scope.event.dateEnd = $("#eventEndDate").val();
    };

    $scope.addJobToObject = function(jobStatus) {
        var selectedJob = new Object();
        if ($scope.jobType.id != null) {
            selectedJob.id = $scope.jobType.id;
        }
        selectedJob.numberOfPositions = $scope.jobType.numberOfPositions;
        selectedJob.hours = $scope.jobType.hours;
        selectedJob.selectionCriteria = $scope.jobType.selectionCriteria;
        selectedJob.status = jobStatus;

        var jobType = new Object();
        jobType.id = $scope.selectedJobType;
        jobType.name = $("#jobType option:selected").html();
        selectedJob.jobTypeBean = jobType;
        console.info(selectedJob);

        $http({
            url: "/opportunity/" + oppID + "/jobType",
            dataType: "json",
            method: "POST",
            data: selectedJob,
            headers: {
                "Content-Type": "application/json"
            },
            cache: false
        })
            .success(function(response) {
                console.info(response);
                $scope.opportunityJobTypes = response.data.opportunityJobTypes;
                $scope.removeJobFromMasterJobType();
            })
            .error(function(error) {
                console.info(error);
            });

        $scope.jobType = {
            numberOfPositions: "",
            hours: "",
            selectionCriteria: ""
        };
    };

    $scope.removeJobFromMasterJobType = function() {
        for (i in $scope.opportunityJobTypes) {
            for (j in $scope.masterJobTypeList) {
                if (
                    $scope.masterJobTypeList[j].id ==
                    $scope.opportunityJobTypes[i].jobTypeBean.id
                ) {
                    // console.info(
                    //     "comparing : " +
                    //         $scope.masterJobTypeList[j].name +
                    //         " --- > " +
                    //         $scope.opportunityJobTypes[i].jobTypeBean.name
                    // );
                    if ($scope.opportunityJobTypes[i].status) {
                        // console.info("index -- > " + j);
                        $scope.masterJobTypeList.splice(j, 1);
                    }
                }
            }
        }
        if (
            !$scope.masterJobTypeList == undefined &&
            $scope.masterJobTypeList.length > 0
        )
            $scope.selectedJobType = $scope.masterJobTypeList[0].id;
    };

    $scope.cancelJobOperation = function() {
        $scope.editCancelFunction = false;
        $scope.jobSectionDisplay = false;
        $scope.jobType = {
            numberOfPositions: "",
            hours: "",
            selectionCriteria: "",
            id: null
        };
    };

    $scope.jobOperation = function() {
        switch ($scope.saveJobRole) {
            case "create":
                console.info("create");
                $scope.addJobToObject(1);
                break;
            case "edit":
                console.info("edit");
                $scope.addJobToObject(1);
                break;
            case "delete":
                console.info("delete");
                $scope.addJobToObject(0);
                break;
            default:
                console.info("not found");
        }
        $scope.cancelJobOperation();
    };

    $scope.iterateJob = function() {
        for (i in $scope.opportunityJobTypes) {
            console.info(
                " -- > " + $scope.opportunityJobTypes[i].numberOfPositions
            );
        }
    };

    $scope.saveEditEvent = function() {
        console.info("in saveEditEvent");
        $("#loading-indicator").show();
        var event = new Object();
        event.id = oppID;
        event.name = $scope.event.name;
        event.description = $scope.event.description;
        event.dateStart = new Date($("#eventStartDate").val());
        event.dateEnd = new Date($("#eventEndDate").val());
        event.type = "EVENT";

        var address = new Object();
        if ($scope.isField || $scope.manualAddress) {
            if ($scope.manualAddress) {
                address.city = $("#locality").val();
                address.country = $("#country").val();
                address.postalCode = $("#postal_code").val();
                address.state = $("#administrative_area_level_1").val();
                address.street = $("#street_number").val();
                address.formattedAddress = null;
            } else {
                var fullAddress = $("input[name='formattedAddress']").val();
                address.formattedAddress = fullAddress;
            }
        }

        event.addressBean = address;
        if (programID) {
            var programId = new Object();
            programId.id = programID;
            event.parentProgram = programId;
            event.createdBy = $rootScope.userId;
        }
        console.log("event obj before getting lat long is: ", event);
        console.log("getting latlong from google api for: ", fullAddress);
        $scope.isField &&
            getLatitudeLongitude(fullAddress)
                .then(function(latlng) {
                    console.log("found matching lat long");
                    event.latLong = latlng.lat + "," + latlng.lng;
                    $scope.areaFound = true;
                    console.log("saveEdit isField: ", $scope.isField);
                    console.log("saveEdit areaFound: ", $scope.areaFound);
                    $scope.saveEventInDB(event);
                })
                .catch(function(err) {
                    console.log("no matching lat long found");
                    document.getElementById("latitude").value = "";
                    $scope.areaFound = false;
                    // $scope.isField = false;
                    $("#loading-indicator").hide();
                    console.log("saveEdit isField: ", $scope.isField);
                    console.log("saveEdit areaFound: ", $scope.areaFound);
                });
        console.log("event data to be saved in db: ", event);
        !$scope.isField && $scope.saveEventInDB(event);

        // setTimeout(function() {
        //     if ($("#latitude").val() === "ZERO_RESULTS") {
        //         document.getElementById("latitude").value = "";
        //         $scope.areaFound = false;
        //         $scope.isVisible = false;
        //         $("#loading-indicator").hide();
        //         $scope.modeSelected = false;
        //     } else {
        //         event.latLong =
        //             $("#latitude")
        //                 .val()
        //                 .toString() +
        //             "," +
        //             $("#longitude")
        //                 .val()
        //                 .toString();
        //         $http({
        //             url: "/opportunity",
        //             dataType: "json",
        //             method: "PUT",
        //             data: event,
        //             headers: {
        //                 "Content-Type": "application/json"
        //             },
        //             cache: false
        //         })
        //             .success(function(response) {
        //                 $("#loading-indicator").hide();
        //                 // console.info(response);
        //                 $scope.eventActionSuccess =
        //                     "Changes Successfully done!!";
        //                 $scope.eventSuccess = true;
        //             })
        //             .error(function(error) {
        //                 $("#loading-indicator").hide();
        //                 $scope.eventActionError =
        //                     "Something went wrong!!Please refresh page";
        //                 $scope.eventError = false;
        //                 $scope.areaFound = false;
        //                 $("input[name='formattedAddress']").value = "";
        //             })
        //             .finally(function() {
        //                 $("#loading-indicator").hide();
        //                 $timeout(function() {
        //                     $scope.eventSuccess = false;
        //                     $scope.eventError = false;
        //                 }, 2000);
        //             });
        //     }
        // }, 3000);
    };

    $scope.saveEventInDB = function(event) {
        $http({
            url: "/opportunity",
            dataType: "json",
            method: "PUT",
            data: event,
            headers: {
                "Content-Type": "application/json"
            },
            cache: false
        })
            .success(function(response) {
                $("#loading-indicator").hide();
                // console.info(response);
                $scope.eventActionSuccess = "Changes Successfully done!!";
                $scope.eventSuccess = true;
                if (response && programID)
                    $location.path(
                        "/view/opportunity/edit/" + response.data.id
                    );
            })
            .error(function(error) {
                $("#loading-indicator").hide();
                $scope.eventActionError =
                    "Something went wrong!!Please refresh page";
                $scope.eventError = false;
                $scope.areaFound = false;
                $("input[name='formattedAddress']").value = "";
            })
            .finally(function() {
                $("#loading-indicator").hide();
                $timeout(function() {
                    $scope.eventSuccess = false;
                    $scope.eventError = false;
                }, 2000);
            });
    };

    $scope.editProgram = function() {
        // console.info("edit program");
        $("#opportunityCreateModal").modal("hide");
        $("body").removeClass("modal-open");
        $(".modal-backdrop").remove();

        $scope.event = {};
        $scope.masterCauseList = [];
        $scope.userCauses = [];
        $scope.jobType = {};

        // ======================================causes====================================================

        $http({
            url: "/opportunity/cause/edit/" + oppID,
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
            cache: false
        })
            .success(function(response) {
                console.log("editing event...", response.data.opportunityDTO);
                $scope.event = response.data.opportunityDTO;
                $scope.event.street = $scope.event.addressBean.street;
                console.log($scope.event);
                $scope.event.dateStart = $filter("date")(
                    $scope.event.dateStart,
                    "yyyy-MM-dd"
                );
                $scope.event.dateEnd = $filter("date")(
                    $scope.event.dateEnd,
                    "yyyy-MM-dd"
                );
                $scope.event.addressBean.postalCode = parseInt(
                    $scope.event.addressBean.postalCode
                );
                if (
                    $scope.event.addressBean.country ||
                    $scope.event.addressBean.state ||
                    $scope.event.addressBean.city ||
                    $scope.event.addressBean.formattedAddress
                ) {
                    $scope.address = {
                        name: "onfield"
                    };
                    $scope.isField = true;
                } else {
                    $scope.address = {
                        name: "virtual"
                    };
                    $scope.isField = false;
                }
                $scope.masterCauseList = response.data.causeDTO;
                // console.info($scope.masterCauseList);
                $scope.masterJobTypeList = response.data.jobTypeDTO;

                response.data.opportunityDTO.opportunityCauseXrefs.forEach(
                    function(userCause) {
                        $scope.masterCauseList.forEach(function(masterCause) {
                            if (userCause.causeBean.id == masterCause.id) {
                                masterCause.status = userCause.causeBean.active;
                            }
                        });

                        $scope.opportunityJobTypes =
                            response.data.opportunityDTO.opportunityJobTypes;
                        $scope.removeJobFromMasterJobType();
                    }
                );
                console.log("inital load isField: ", $scope.isField);
                console.log("initial load areaFound: ", $scope.areaFound);
            })
            .error(function(error) {});
    };

    // TO check and uncheck the boxes
    $scope.checkAllCauses = function() {
        $scope.masterCauseList.forEach(function(cause) {
            cause.status = !$scope.selectedALLCause;
        });
        $scope.selectedALLCause = !$scope.selectedALLCause;
    };

    $scope.deleteOpportunityCause = function(checkedCause) {
        console.info("deleting cause");
        $http({
            url: "/opportunity/" + oppID + "/cause/" + checkedCause.id,
            dataType: "json",
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                console.info("success");
            })
            .error(function(error) {
                console.info("error");
            });
    };

    // TO save skills

    $scope.saveCauseList = function(checkedCause) {
        if (!checkedCause.status) {
            $scope.deleteOpportunityCause(checkedCause);
            return;
        }

        $("#loading-indicator").show();
        var opportunityCauseXrefDTO = new Object();
        var cause = new Object();
        cause.id = checkedCause.id;
        opportunityCauseXrefDTO.causeBean = cause;
        $http({
            url: "/opportunity/" + oppID + "/cause",
            dataType: "json",
            method: "POST",
            data: opportunityCauseXrefDTO,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $("#loading-indicator").hide();
            })
            .error(function(error) {
                $("#loading-indicator").hide();
                $scope.error = error;
            });
    };

    $scope.editJobFromMasterJobType = function(job) {
        console.info("--- > " + job.id);

        $scope.jobType = {};
        $scope.addJobSectionDisplays();
        $scope.jobType.id = job.id;
        $scope.jobType.numberOfPositions = parseInt(job.numberOfPositions);
        $scope.jobType.hours = parseInt(job.hours);
        $scope.jobType.selectionCriteria = job.selectionCriteria;
        $scope.removeJobFromMasterJobType();
        $scope.masterJobTypeList.unshift(job.jobTypeBean);
        $scope.selectedJobType = job.jobTypeBean.id;
        $scope.selectedJobTypeName = job.jobTypeBean.name;
        $scope.saveJobRole = "edit";

        $scope.editCancelFunction = true;
        // $scope.addJobToObject( 1);
    };

    $scope.createJobFromMasterJobType = function() {
        $scope.saveJobRole = "create";
        $scope.jobType.id = null;
        $scope.jobType = {
            numberOfPositions: "",
            hours: "",
            selectionCriteria: "",
            id: null
        };
        $scope.removeJobFromMasterJobType();
        $scope.addJobSectionDisplays();
    };

    $scope.deleteJobFromMasterJobType = function(job) {
        console.info("--- > " + job.id);
        $scope.saveJobRole = "delete";
        var selectedJob = new Object();
        selectedJob.id = job.id;
        selectedJob.status = 0;
        $http({
            url: "/opportunity/" + oppID + "/jobType",
            dataType: "json",
            method: "DELETE",
            data: selectedJob,
            headers: {
                "Content-Type": "application/json"
            },
            cache: false
        })
            .success(function(response) {
                console.info(response);
                $scope.opportunityJobTypes = response.data.opportunityJobTypes;
                $scope.cancelJobOperation();
                $scope.masterJobTypeList.push(job.jobTypeBean);
            })
            .error(function(error) {
                console.info(error);
            });
    };

    $scope.showDetailPage = function(id) {
        console.info(id);
        $location.path("/view/opportunity/" + id);
    };

    $scope.hideEventForm = function() {
        $scope.eventShow = false;
        $scope.event.name = "";
        $scope.event.description;
        $("#eventStartDate").val("");
        $("#eventEndDate").val("");
        $scope.event.addressBean.city = "";
        $scope.event.addressBean.country = "";
        $scope.event.addressBean.postalCode = "";
        $scope.event.addressBean.state = "";
        $scope.event.addressBean.street = "";
        $scope.eventSuccess = false;
    };
});
//======================================================= End =====================================================================
//======================================================= Controller for edit functionality on program =====================================================================
opportunity.controller("OpportunityProgramEditController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    // console.info("here ------------");
    $scope.areaFound = true;
    $scope.isField = true;
    $scope.manualAddress = false;
    $scope.address = new Object();

    $scope.ShowAddress = function(value) {
        //If DIV is visible it will be hidden and vice versa.
        $scope.isField = value == "Y";

        $scope.isField
            ? ($scope.address = {
                  name: "onfield"
              })
            : ($scope.address = {
                  name: "virtual"
              });
    };
    $scope.changeAreaFoundStatus = function() {
        $scope.areaFound = $scope.areaFound ? false : true;
        document.getElementById("latitude").value = "";
        document.getElementById("longitude").value = "";
    };
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
    $("#opportunityCreateModal").modal("hide");
    $("body").removeClass("modal-open");
    $(".modal-backdrop").remove();

    var url = window.location.href.split("/view/opportunity/program/edit/");
    var programID = url[1];
    console.log("initializing view for programID: ", programID);
    $scope.eventShow = false;

    $scope.editOpp = function(opp) {
        // console.log("Opp id : "+oppID);
        if (opp.type == "PROGRAM") {
            window.location = "#/view/opportunity/program/edit/" + opp.id;
            return;
        }
        window.location = "#/view/opportunity/edit/" + opp.id;
    };

    $scope.showEvent = function() {
        $scope.eventShow = !$scope.eventShow;
    };

    $scope.hideEventForm = function() {
        $scope.eventShow = false;
        $scope.event.name = "";
        $scope.event.description;
        $("#eventStartDate").val("");
        $("#eventEndDate").val("");
        $scope.event.addressBean.city = "";
        $scope.event.addressBean.country = "";
        $scope.event.addressBean.postalCode = "";
        $scope.event.addressBean.state = "";
        $scope.event.addressBean.street = "";
        $scope.eventSuccess = false;
    };
    $("#eventStartDate").datepicker({ autoclose: true });
    $scope.displayStartDate = function() {
        $scope.event.dateStart = $("#eventStartDate").val();
    };
    $("#eventEndDate").datepicker({ autoclose: true });
    $scope.displayEndDate = function() {
        $scope.event.dateEnd = $("#eventEndDate").val();
    };

    $scope.createEventUsingProgram = function() {
        console.info("create Event called");
        var event = new Object();
        var programId = new Object();
        programId.id = programID;
        event.parentProgram = programId;
        event.name = $scope.event.name;
        event.description = $scope.event.description;
        event.dateStart = new Date($("#eventStartDate").val());
        event.dateEnd = new Date($("#eventEndDate").val());
        event.type = "EVENT";
        var address = new Object();
        address.city = $("#locality").val();
        address.country = $("#country").val();
        address.postalCode = $("#postal_code").val();
        address.state = $("#administrative_area_level_1").val();
        address.street = $("#street_number").val();
        address.formattedAddress = $("input[name='formattedAddress']").val();
        event.addressBean = address;
        var fullAddress;

        if ($("input[name='formattedAddress']").val() == "") {
            fullAddress =
                address.street +
                "," +
                address.city +
                "," +
                address.postalCode +
                "," +
                address.state +
                "," +
                address.country;
            address.formattedAddress = fullAddress;
        } else {
            fullAddress = $("input[name='formattedAddress']").val();
        }
        getLatitudeLongitude(fullAddress);
        setTimeout(function() {
            event.latLong =
                $("#latitude")
                    .val()
                    .toString() +
                "," +
                $("#longitude")
                    .val()
                    .toString();
            $http({
                url: "/opportunity",
                dataType: "json",
                method: "POST",
                data: event,
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .success(function(response) {
                    if (response.data.id)
                        $location.path(
                            "/view/opportunity/edit/" + response.data.id
                        );
                    else console.info("something went wrong");
                })
                .error(function() {
                    console.info("somthing went wrong");
                });
        }, 3000);
    };

    $scope.editProgramWithCreateEvent = function() {
        $scope.program = {};
        $scope.program.linkedEvents = [];
        $http({
            url: "/opportunity/cause/edit/" + programID,
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
            cache: false
        })
            .success(function(response) {
                console.log(
                    "successfully received linkedEvents..",
                    response.data
                );
                $scope.program = response.data.opportunityDTO;
                $scope.program.linkedEvents =
                    response.data.opportunityDTO.linkedEvents;
                // console.info($scope.program.linkedEvents);
            })
            .error(function(error) {
                console.log("unable to get program details: ", error);
            });
    };

    $scope.editProgramWithEvent = function() {
        if ($scope.submitted != undefined && $scope.submitted == false) {
            $location.path("/view/opportunity/" + $scope.program.id);
            return;
        }
        console.info("Edit Program called");
        console.info($scope.program.name + " = " + $scope.program.description);
        var program = new Object();
        program.id = programID;
        program.name = $scope.program.name;
        program.description = $scope.program.description;
        program.type = "PROGRAM";

        $http({
            url: "/opportunity",
            dataType: "json",
            method: "PUT",
            data: program,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response != undefined && response.data.id != undefined) {
                    $scope.programSuccess = true;
                    $scope.programError = false;
                    $scope.programActionSuccess = "Update Sucessfull!!";
                } else {
                    $scope.programSuccess = false;
                    $scope.programError = true;
                    $scope.programActionSuccess = "Something went wrong";
                }
            })
            .error(function() {
                console.info("-- " + error);
            })
            .finally(function() {
                $timeout(function() {
                    $scope.programSuccess = false;
                    $scope.programError = false;
                }, 3000);
            });
        $scope.showDetailPage = function(id) {
            console.info(id);
            $location.path("/view/opportunity/" + id);
        };
    };
});

//======================================================= End =====================================================================
//======================================================= Controller for view functionality on opportunity =====================================================================

opportunity.controller("ViewOpportunityController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    // $scope.gPlace;
    var url = window.location.href.split("/view/opportunity/");
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
            console.log("oppDetails: ", response.data);
            if (response.error == null && response.data) {
                console.log("got event details:...", response.data);
                $scope.oppDetails = response.data;
                $scope.compareDate(response.data.dateEnd);
                $scope.checkIfEditToBeShown(oppID);
                $scope.alreadyAppliedJobs(response.data.opportunityJobTypes);
                $scope.oppDetails.latLong &&
                    google.maps.event.addDomListener(
                        window,
                        "load",
                        initMap($scope.oppDetails.latLong)
                    );
            }
            else $scope.oppDetails = null;
        })
        .error(function(error) {
            console.log(
                "error while getting profile details of user with id:" +
                    $rootScope.userId
            );
            
        });
    $scope.compareDate = function(dateEnd) {
        $scope.eventComplete = false;

        var response = moment(dateEnd).diff(Date.now(), "day");
        if (response < 1) {
            $scope.eventComplete = true;
        }
    };

    $scope.alreadyAppliedJobs = function(opportunityJobTypes) {
        if (opportunityJobTypes == undefined || opportunityJobTypes == null)
            return;
        var listedJob = new Array();
        for (i in opportunityJobTypes) {
            listedJob.push(opportunityJobTypes[i].jobTypeBean.description);
        }
        // console.info(listedJob);
        $http({
            url: "opportunity/" + oppID + "/participantsByStatus?status=true",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                var results = response.searchResult;

                var jACounter = new Array();
                for (i in listedJob) {
                    var counter = 0;
                    var job = new Object();
                    job.name = listedJob[i];
                    for (j in results) {
                        if (
                            listedJob[i] == results[j].jobTypeBean.description
                        ) {
                            counter++;
                        }
                    }
                    job.appliedPosition = counter;
                    jACounter.push(job);
                    // console.info(jACounter);
                }
                $scope.jobAppliedCounter = jACounter;
            })
            .error(function(error) {});
    };

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

    //console.log("Maps initialised");

    $scope.redirectOpp = function(oppID) {
        // console.log("Opp id : "+oppID);
        window.location = "#/view/opportunity/" + oppID;
    };

    $scope.editOpp = function(opp) {
        // console.log("Opp id : "+oppID);
        if (opp.type == "PROGRAM") {
            window.location = "#/view/opportunity/program/edit/" + opp.id;
            return;
        }
        window.location = "#/view/opportunity/edit/" + opp.id;
    };

    $scope.manageOpp = function(oppID) {
        // console.log("Opp id : "+oppID);
        window.location = "#/opportunity/manageApplications/" + oppID;
    };

    $scope.applyForOppOnLogin = function(opp, JobType) {
        $rootScope.$on("ApplyForJob", function() {
            $scope.applyForOpp(opp, JobType);
        });
    };

    $scope.applyForOpp = function(opp, jobType) {
        // console.log("Opp : " + opp.id + " , job type : " + jobType.id);
        $("#loading-indicator").show();
        $http({
            url: "/opportunity/" + opp.id + "/party/0/jobType/" + jobType.id,
            dataType: "json",
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $("#loading-indicator").hide();
                $scope["apply_btn_" + jobType.id] = true;
                $scope["applied_btn_" + jobType.id] = true;

                $rootScope.ltoSuccessMessage =
                    "Job Application successfully sent.";
                $rootScope.toggleLtoSuccessModal();
                $(".lto-success-modal-dialog").css({
                    top: "200px"
                });
            })
            .error(function(error) {
                $("#loading-indicator").hide();
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };
    ($scope.filteredTodos = []),
        ($scope.currentPage = 1),
        ($scope.numPerPage = 10),
        ($scope.maxSize = 5);

    $scope.makeTodos = function() {
        $scope.todos = [];
        for (var i = 1; i <= 1000; i++) {
            $scope.todos.push({
                text: "todo " + i,
                done: false
            });
        }
    };
    $scope.makeTodos();

    $scope.$watch("currentPage + numPerPage", function() {
        var begin = ($scope.currentPage - 1) * $scope.numPerPage,
            end = begin + $scope.numPerPage;

        $scope.filteredTodos = $scope.todos.slice(begin, end);
    });

    $scope.paintUserProfileDetails = function(userId) {
        $location.path("/profile/user/" + userId);
    };

    // fetch posts of opportunity

    $scope.oppPostsList = [];
    $scope.postListSize = 5;
    $scope.postCount = 0;
    getOppPostsList();

    $scope.reloadOppPosts = function() {
        $scope.oppPost.content = "";
        $scope.oppPostsList = [];
        $scope.postListSize = 5;
        $scope.postCount = 0;
        getOppPostsList();
    };

    function getOppPostsList() {
        $http({
            url: "/post/findAllByPostedForOpportunity/" + $scope.oppID,
            dataType: "json",
            method: "GET",
            params: {
                size: $scope.postListSize
            },
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.oppPostsList = [];
                $scope.oppPostsList = response.searchResult;
                $scope.totalPostsCount = response.totalCount;
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    }

    $scope.getNextPostList = function() {
        $scope.postListSize = $scope.postListSize + 5;
        getOppPostsList();
    };
    // getSuggestionlist();
    $scope.fetchConnectionList = function() {
        $scope.postCount = $scope.postCount + $scope.postListSize;
        getOppPostsList();
    };

    $scope.oppPost = {};
    $scope.oppPost.content = "";
    // post regarding opportunity
    $scope.postForEvent = function() {
        var post = {};
        post.content = $scope.oppPost.content;

        $http({
            url: "/post/opportunity/" + $scope.oppID,
            dataType: "json",
            data: post,
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.reloadOppPosts();
            })
            .error(function(error) {
                console.log(
                    "error while getting profile details of user with id:" +
                        $rootScope.userId
                );
            });
    };
});

//======================================================= End =====================================================================

//======================================================= Controller for inviting volunteers for opportunity =====================================================================
opportunity.controller("InviteVolunteerController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout
) {
    var url = window.location.href.split("/view/opportunity/");
    var oppID = url[1];
    $scope.oppID = oppID;
    $scope.volunteerSuggestionList = [];
    $scope.inviteVolunteerList = [];
    $rootScope.searchSize = 6;

    $scope.findOne = function(element) {
        for (var i = 0; i < $scope.inviteVolunteerList.length; i++) {
            if ($scope.inviteVolunteerList[i].invitedParty.id == element.id) {
                return true;
            }
        }
        return false;
    };

    // ============================= Search volunteers functionallity ====================================
    $scope.fetchVolunteers = function() {
        var name = "";

        $http({
            url: "/opportunity/" + $scope.oppID + "/volunteerSuggesstionList",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
            params: {
                name: name,
                size: $rootScope.searchSize
            }
        })
            .success(function(response) {
                $scope.volunteerSuggestionList = [];
                $scope.inviteVolunteerList = [];
                $scope.volunteerSuggestionList = response.data.searchResult;
                $scope.inviteVolunteerList = response.data.invitedVolunteerList;
                if (response.data.invitedVolunteerList.length > 0) {
                    $scope.volunteerSuggestionList.forEach(function(party) {
                        if ($scope.findOne(party)) {
                            party.invitedStatus = true;
                        } else {
                            party.invitedStatus = false;
                        }
                    });
                } else {
                    $scope.volunteerSuggestionList.forEach(function(party) {
                        party.invitedStatus = false;
                    });
                }
            })
            .error(function() {
                console.log(
                    "Something went wronf while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };

    // =========================== End ================

    $scope.getNextVolunteerSuggestionList = function() {
        $rootScope.searchSize = $rootScope.searchSize + 4;
        $scope.fetchVolunteers();
    };

    $scope.searchVolunteers = function(name) {
        if (name == undefined) {
            name = $scope.search.name;
        }

        $http({
            url: "/opportunity/" + $scope.oppID + "/volunteerSuggesstionList",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
            params: {
                name: name
            }
        })
            .success(function(response) {
                $scope.volunteerSuggestionList = [];
                $scope.inviteVolunteerList = [];
                $scope.volunteerSuggestionList = response.data.searchResult;
                $scope.inviteVolunteerList = response.data.invitedVolunteerList;
                if (response.data.invitedVolunteerList.length > 0) {
                    $scope.volunteerSuggestionList.forEach(function(party) {
                        if ($scope.findOne(party)) {
                            party.invitedStatus = true;
                        } else {
                            party.invitedStatus = false;
                        }
                    });
                } else {
                    $scope.volunteerSuggestionList.forEach(function(party) {
                        party.invitedStatus = false;
                    });
                }
            })
            .error(function() {
                console.log(
                    "Something went wronf while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.inviteVolunteer = function(party, oppDetails) {
        $http({
            url:
                "/opportunity/" +
                oppDetails.id +
                "/invitedBy/" +
                $rootScope.userId +
                "/invite/" +
                party.id,
            dataType: "json",
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                var index = $scope.volunteerSuggestionList.indexOf(party);
                $scope.volunteerSuggestionList.splice(index, 1);
                party.invitedStatus = true;
                $scope.volunteerSuggestionList.unshift(party);
            })
            .error(function() {
                console.log(
                    "Something went wronf while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };
});

//======================================================= End =====================================================================
