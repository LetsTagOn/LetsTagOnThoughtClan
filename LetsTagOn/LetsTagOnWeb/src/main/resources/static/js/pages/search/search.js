var searchModule = angular.module("search", []);

/*
 * Controller for Search Functionality
 * 
 */
searchModule
    .controller("SearchController", function(
        $http,
        $scope,
        $rootScope,
        $location,
        $timeout,
        $route,
        searchSvc
    ) {
        $scope.searchList = [];
        $scope.masterCauseList = [];
        $scope.masterSkillList = [];
        $scope.systemLocationData = [];
        $scope.search = {};
        $scope.isSearchBoxVisible = true;
        $scope.showOppDateRange = false;
        $rootScope.dateRange = false;
        $scope.results = searchSvc.getSearchResults();
        $scope.totalResults = -1; // Sourabh: made -1 from 0 to fix search issue. Code is checking if totalResults = 0
        // Function to set the maximum resilt size
        $scope.searchString;
        console.log("search string is: ", $scope.searchString);
        // if (url[1])
        $scope.getResultsPerPage = function() {
            return 10;
        };
        $scope.resultsPerPage = $scope.getResultsPerPage(); // this
        $scope.searchMode = "users";
        $scope.showUserResultSet = true;
        $scope.showOppResultSet = false;
        $rootScope.startEndDateRange = false;
        $scope.pagination = {
            current: searchSvc.getCurrentPageNumber()
        };
        $;

        $("#opportunityStartDate")
            .datepicker({
                format: "yyyy/mm/dd",
                todayHighlight: true
            })
            .on("changeDate", function(e) {
                $(this).datepicker("hide");
                // $scope.pageChanged(1);
            });
        $("#opportunityEndDate")
            .datepicker({
                format: "yyyy/mm/dd",
                todayHighlight: true
            })
            .on("changeDate", function(e) {
                $(this).datepicker("hide");
                // $scope.pageChanged(1);
            });

        $scope.ClearResults = function() {
            searchSvc.clearResults();

            $route.reload();
        };

        $scope.$watch("pagination.current", function(val) {
            $scope.pageChanged(val);
        });
        //  init method called on page load to load master data
        $scope.getMasterData = function() {
            var keyword = $scope.getSearchKeyWordFromUrl();
            // $scope.pageChanged($scope.pagination.current);
            console.log("got keyword from url: ", keyword);

            if (keyword) {
                $scope.searchString = keyword;
                $scope.getResultsPage(1); // Sourabh: uncommented this line to fix search issue
            }

            $http({
                url: "/master/data",
                dataType: "json",
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .success(function(response) {
                    if (response.error == null) {
                        response.data.causeList.forEach(function(cause) {
                            cause.selected = !cause.active;
                            $scope.masterCauseList.push(cause);
                        });
                        response.data.skillList.forEach(function(skill) {
                            skill.selected = !skill.status;
                            $scope.masterSkillList.push(skill);
                        });
                    }

                    $scope.getFacets(1);
                })
                .error(function(error) {
                    console.log(
                        "error while getting search details of user with id:" +
                            $rootScope.userId
                    );
                });
        };

        // Function to get keyword from url
        $scope.getSearchKeyWordFromUrl = function() {
            var url;
            var keyword = "";
            if (window.location.href.indexOf("/search/user/") > -1) {
                url = window.location.href.split("/search/user/");
                keyword = url[1];
            } else if (
                window.location.href.indexOf("/search/opportunity/") > -1
            ) {
                url = window.location.href.split("/search/opportunity/");
                keyword = url[1];
                document.getElementById("searchModeOpps").checked = true;
                $scope.searchMode = "opportunity";
            }
            return keyword;
        };

        // Function to redirect to profile view of user from search result
        $scope.paintUserProfileDetails = function(userId) {
            $location.path("/profile/user/" + userId);
        };

        // Function to redirect to opportunity view from search result
        $scope.paintOppDetails = function(oppID) {
            $location.path("/view/opportunity/" + oppID);
        };

        // Function to search opportunity based on dateRange
        // Default: TO get upcoming opportunitites on check of getpast events then get all events
        $scope.getOppDateRange = function(pageNumber) {
            $rootScope.dateRange = $scope.dateRange;
            $("#searchPgBtnPrimary").click();
        };
        // Function to get the selected cause list
        $scope.getSelectedCausesList = function() {
            $scope.selectedCauseMasterList = [];
            $scope.masterCauseList.forEach(function(masterCause) {
                if (masterCause.selected) {
                    $scope.selectedCauseMasterList.push(masterCause.name);
                }
            });
            return $scope.selectedCauseMasterList;
        };
        // Function to get the selected Location list only list of location name
        $scope.getSelectedLocation = function() {
            $scope.selectedLocationList = [];
            $scope.systemLocationData.forEach(function(location) {
                if (location.selected) {
                    $scope.selectedLocationList.push(location.name);
                }
            });
            return $scope.selectedLocationList;
        };
        // Function to get the selected locationObject list
        $scope.getSelectedLocationObject = function() {
            $scope.selectedLocationList = [];
            $scope.systemLocationData.forEach(function(location) {
                if (location.selected) {
                    $scope.selectedLocationList.push(location);
                }
            });
            return $scope.selectedLocationList;
        };
        // Function to get the selected skill list
        $scope.getSelectedSkillMasterList = function() {
            $scope.selectedSkillMasterList = [];
            $scope.masterSkillList.forEach(function(masterSkill) {
                if (masterSkill.selected) {
                    $scope.selectedSkillMasterList.push(masterSkill.name);
                }
            });
            return $scope.selectedSkillMasterList;
        };

        // Function called to redirect to search page from anywhere in the application
        $scope.redirectToSearchPage = function() {
            var keyword =
                $(".lto-pre-login-search").val() ||
                $(".lto-header-search-adj").val();
            console.log("value in headerSearch: ", keyword);
            $location.path("/search/opportunity/" + keyword);
            // debugger;
        };
        // Pagination Function called to get the search results from solr
        $scope.pageChanged = function(newPage) {
            searchSvc.saveCurrentPageNumber(newPage);
            $scope.getResultsPage(newPage);
        };
        // Function called to set the facets counts to the search master data
        $scope.setFacetCounts = function(facetCounts) {
            var causeCounts = facetCounts.facet_fields.cause;
            var jobTypeCounts = facetCounts.facet_fields.jobtype;
            var locationCounts = facetCounts.facet_fields.address_city;

            for (i in $scope.masterCauseList) {
                var cause = $scope.masterCauseList[i];
                cause.count = 0;
            }
            if (causeCounts) {
                for (var j = 0; j < causeCounts.length; j = j + 2) {
                    for (i in $scope.masterCauseList) {
                        var cause = $scope.masterCauseList[i];
                        // cause.count=0;
                        if (
                            cause.name.toLowerCase() ===
                            causeCounts[j].toLowerCase()
                        ) {
                            cause.count = causeCounts[j + 1];
                            break;
                        }
                    }
                }
            }
            $scope.locationFacetAvailable = false;
            $scope.selectedLocation = $scope.getSelectedLocationObject();
            $scope.systemLocationData = [];
            if (locationCounts) {
                for (var j = 0; j < locationCounts.length; j = j + 2) {
                    var location = new Object();
                    location.count = locationCounts[j + 1];
                    location.name = locationCounts[j];
                    $scope.selectedLocation.forEach(function(selectedLocation) {
                        if (
                            location.name == selectedLocation.name &&
                            location.count == selectedLocation.count
                        ) {
                            location.selected = true;
                        }
                    });
                    $scope.systemLocationData.push(location);
                }

                if ($scope.systemLocationData.length > 0) {
                    $scope.locationFacetAvailable = true;
                }
            }
            $scope.causeFacetAvailable = false;
            for (i in $scope.masterCauseList) {
                var cause = $scope.masterCauseList[i];
                if (cause.count > 0) {
                    $scope.causeFacetAvailable = true;
                    break;
                }
            }

            for (i in $scope.masterSkillList) {
                var jobType = $scope.masterSkillList[i];
                jobType.count = 0;
            }

            if (jobTypeCounts) {
                for (var j = 0; j < jobTypeCounts.length; j = j + 2) {
                    for (i in $scope.masterSkillList) {
                        var jobType = $scope.masterSkillList[i];
                        // jobType.count=0
                        if (
                            jobType.name.toLowerCase() ===
                            jobTypeCounts[j].toLowerCase()
                        ) {
                            jobType.count = jobTypeCounts[j + 1];
                            break;
                        }
                    }
                }
            }
            $scope.jobTypeFacetAvailable = false;
            for (i in $scope.masterSkillList) {
                var jobType = $scope.masterSkillList[i];
                if (jobType.count > 0) {
                    $scope.jobTypeFacetAvailable = true;
                    break;
                }
            }
        };

        $scope.upComingEvents = [];

        // Function called to get upcoming events
        $scope.getUpcomingEvents = function() {
            $http
                .get($scope.generateUpcomingEventsQuery(1))
                .then(function(result) {
                    $scope.upComingEvents = result.data.response.docs;
                    console.log(result.data.response.docs);
                });
        };

        // Function called to get Search results from solr
        $scope.getResultsPage = function(pageNumber) {
            console.log("getting results from page: ", pageNumber);
            // this is just an example, in reality this stuff should
            // be in a service
            //get results from DB only if there is no previous search result or there is the default search result from initial page load
            console.log("scope results length is: ", $scope.results.length);
            if (
                $scope.results.length === 0 ||
                $scope.results.length <= $scope.resultsPerPage
            ) {
                console.log(
                    "query string is: ",
                    $scope.generateSolrSearchQuery(pageNumber)
                );
                $http
                    .get($scope.generateSolrSearchQuery(pageNumber))
                    .then(function(result) {
                        $scope.results = [];
                        $scope.results = result.data.response.docs;
                        console.log("results received: ", $scope.results);
                        searchSvc.saveSearch($scope.results);
                        if (result.data.response.numFound > 0)
                            // Sourabh: added this code to set searchresults

                            $scope.totalResults = result.data.response.numFound;
                        else $scope.totalResults = 0;
                        if (result.data.facet_counts) {
                            // set facet counts

                            $scope.setFacetCounts(result.data.facet_counts);
                        }

                        if ($scope.getCoreName() === "users") {
                            $scope.showConnectionStatus($scope.results);
                        }
                    });
            }
        };

        $scope.getBannerImage = function(bannerImage) {
            return (
                "https://s3-us-west-2.amazonaws.com/ltoopporimages/" +
                bannerImage
            );
        };

        // Function called to get facets
        $scope.getFacets = function(pageNumber) {
            // this is just an example, in reality this stuff should
            // be in a service
            $http
                .get($scope.generateFacetQuery(pageNumber))
                .then(function(result) {
                    if (result.data.facet_counts) {
                        // set facet counts
                        $scope.setFacetCounts(result.data.facet_counts);
                    }
                });
        };

        // Function to generate query string to get upcoming events in pre login page
        $scope.generateUpcomingEventsQuery = function(pageNumber) {
            if (pageNumber < 0) pageNumber = 1;

            if ($scope.resultsPerPage == undefined) {
                $scope.resultsPerPage = 10;
            }
            $scope.searchMode = "opportunity";
            $rootScope.dateRange = false;
            return (
                $scope.getBaseUrl() +
                $scope.getCoreName() +
                "/select?wt=json&rows=" +
                $scope.resultsPerPage +
                "&start=" +
                $scope.resultsPerPage * (pageNumber - 1) +
                "&" +
                $scope.getFacetQuery($scope.getSimpleFacetFields()) +
                $scope.getQueryString() + 
                "&sort=dateStart desc"
            );
            //   Modified by Ravi
            //  $scope.getFilterQueryString();
        };

        // Function to generate search query in search page
        $scope.generateSolrSearchQuery = function(pageNumber) {
            if (pageNumber < 0) pageNumber = 1;

            if ($scope.resultsPerPage == undefined) {
                $scope.resultsPerPage = 10;
            }
            //=====================search query without filters===========
            let name;  
            // console.log($scope.getFacetQuery($scope.getSimpleFacetFields()));
            // console.log($scope.getQueryString());
            if ($scope.searchString) {
                name = "*"+$scope.searchString+"*";
            }
            else if ($("#searchByName").val()) {
                name = "*" + $("#searchByName").val() + "*";
            } else {
                name = "*";
            }
            return (
                $scope.getBaseUrl() +
                $scope.getCoreName() +
                "/select?q=" +
                name +
                "&rows=" +
                $scope.resultsPerPage +
                "&start=" +
                $scope.resultsPerPage * (pageNumber - 1)
                //==============search query with filters=====================
                // $scope.getBaseUrl() +
                // $scope.getCoreName() +
                // "/select?wt=json&rows=" +
                // $scope.resultsPerPage +
                // "&start=" +
                // $scope.resultsPerPage * (pageNumber - 1) +
                // "&" +
                // $scope.getFacetQuery($scope.getSimpleFacetFields()) +
                // $scope.getQueryString()
            );
            //   Modified by Ravi
            //   $scope.getFilterQueryString();
        };

        // Function to generate facet query string
        $scope.generateFacetQuery = function(pageNumber) {
            if (pageNumber < 0) pageNumber = 1;

            if ($scope.resultsPerPage == undefined) {
                $scope.resultsPerPage = 10;
            }

            return (
                $scope.getBaseUrl() +
                $scope.getCoreName() +
                "/select?wt=json&rows=" +
                $scope.resultsPerPage +
                "&start=" +
                $scope.resultsPerPage * (pageNumber - 1) +
                "&" +
                $scope.getFacetQuery($scope.getSimpleFacetFields()) +
                $scope.getQueryString()
            );
        };

        // Function to get base url
        $scope.getBaseUrl = function() {
            //return "http://ec2-54-187-229-255.us-west-2.compute.amazonaws.com:8983/solr/";
            return "http://34.239.2.92:8983/solr/";
        };

        // Function to set the local flags based on search mode
        $scope.changeCoreName = function(name) {
            if (name == "users") {
                $scope.showUserResultSet = true;
                $scope.showOppResultSet = false;
            } else if ((name = "opportunity")) {
                $scope.showUserResultSet = false;
                $scope.showOppResultSet = true;
                $scope.showOppDateRange = true;
                $scope.searchByDateRange = true;
            } else {
                $scope.searchMode = "users";
            }

            $scope.searchMode = name;
        };
        // Function to get search mode based on name
        $scope.getCoreName = function() {
            $scope.searchMode = $("input[name=searchMode]:checked").val();
            $scope.changeCoreName($scope.searchMode);

            if ($scope.searchMode == undefined || $scope.searchMode === "") {
                $scope.searchMode = "users";
            }

            return $scope.searchMode;
        };
        // Function which return the facets fields for solr search
        $scope.getSimpleFacetFields = function() {
            return ["cause", "jobtype", "address_city"];
        };

        // Function to generate facet query
        $scope.getFacetQuery = function(facetFields) {
            var facetString = "&facet=on&";
            for (var i in facetFields) {
                facetString += "&facet.field=" + facetFields[i];
            }

            return facetString;
        };

        // Function  to get filter query
        $scope.getFilterQueryString = function() {
            var skills = $scope.getSelectedSkillMasterList();
            var causes = $scope.getSelectedCausesList();
            var locations = $scope.getSelectedLocation();
            var fq = "&fq=";

            var causesFq = "";
            var jobTypeFq = "";
            var locationFq = "";

            if (skills.length > 0) {
                for (i in skills) {
                    jobTypeFq += " jobtype:" + skills[i];
                    if (i != skills.length - 1) {
                        jobTypeFq += " OR ";
                    }
                }
                jobTypeFq = "( " + jobTypeFq + " )";
                fq += jobTypeFq;
            }
            if (causes.length > 0) {
                for (i in causes) {
                    causesFq += " cause:" + causes[i];
                    if (i != causes.length - 1) {
                        causesFq += " OR ";
                    }
                }
                causesFq = "( " + causesFq + " )";
                fq += causesFq;
            }
            if (locations.length > 0) {
                for (i in locations) {
                    locationFq += " address_city:" + locations[i];
                    if (i != locations.length - 1) {
                        locationFq += " OR ";
                    }
                }
                locationFq = "( " + locationFq + " )";
                fq += locationFq;
            }
            if (
                skills.length > 0 &&
                causes.length > 0 &&
                locations.length > 0
            ) {
                fq =
                    "&fq=( " +
                    jobTypeFq +
                    " AND " +
                    causesFq +
                    "AND" +
                    locationFq +
                    ")";
            }

            return fq;
        };

        // Function  to get query String
        $scope.getQueryString = function() {
            var name = "*";
            if ($("#headerSearch").val() != "") {
                name = $("#headerSearch").val();
            } else {
                name = "*";
            }

            if (undefined != $scope.searchString && $scope.searchString != "") {
                if ($scope.searchMode == "users") {
                    // if searching users

                    if ($rootScope.userId > 0) {
                        return (
                            "&q=(fullName:" +
                            $scope.searchString +
                            " AND -id:" +
                            $rootScope.userId +
                            ")"
                        );
                    }
                    return "&q=fullName:" + $scope.searchString;
                } else if ($scope.searchMode == "opportunity") {
                    var subQuery = "";
                    var dateRangeQuery = "";
                    var startDate = "";
                    var endDate = "";
                    // Reference to why added as filter query
                    // If adding in a custom expression into the solr parameter '_wcf.search.expr', the parameter is added using the default operator. This is generally set to OR. This may not be the desired behaviour. For example, say a customer is searching for 'coffee'. You have a requirement on your site to add in a search parameter, to only include results if the products have a custom field 'available' set to 'true'.
                    // If adding to _wcf.search.expr, it's likely the search will end up like so:
                    // &q=((name:coffee) OR (available:true))
                    // This would return products with the name coffee in it, or products that are available: too many products. For this, it makes more sense to add this as a filter query. filter queries come with their own '&', and so are considered separate and mandatory. &q=coffee&fq=available:true will give you right results.
                    //https://www.ibm.com/developerworks/community/blogs/CommerceSearch/entry/adding_query_or_filter_query_parametes_to_the_final_solr_query?lang=en
                    if ($rootScope.startEndDateRange) {
                        if (
                            $("#opportunityEndDate").val() != "" &&
                            $("#opportunityStartDate").val() != ""
                        ) {
                            endDate = new Date(
                                $("#opportunityEndDate").val()
                            ).toISOString();
                            startDate = new Date(
                                $("#opportunityStartDate").val()
                            ).toISOString();
                            dateRangeQuery =
                                "&fq=dateStart:[" +
                                startDate +
                                " TO " +
                                endDate +
                                "]" +
                                "&fq=dateEnd:[" +
                                startDate +
                                " TO " +
                                endDate +
                                "]";
                        } else if (
                            $("#opportunityEndDate").val() == "" &&
                            $("#opportunityStartDate").val() != ""
                        ) {
                            startDate = new Date(
                                $("#opportunityStartDate").val()
                            ).toISOString();
                            dateRangeQuery =
                                "&fq=dateStart:[" + startDate + " TO *]";
                        } else if (
                            $("#opportunityEndDate").val() != "" &&
                            $("#opportunityStartDate").val() == ""
                        ) {
                            endDate = new Date(
                                $("#opportunityEndDate").val()
                            ).toISOString();
                            dateRangeQuery =
                                "&fq=dateEnd:[* TO " + endDate + "]";
                        }
                    } else {
                        if (!$rootScope.dateRange) {
                            subQuery = "&fq=dateEnd:[NOW TO NOW%2B1YEAR]";
                        }
                    }
                    // if searching users
                    //modified by Ravi
                    // return "&q=name:" + $scope.searchString + "" + subQuery + "" + dateRangeQuery;
                    return "&q=name:" + $scope.searchString;
                }
            } else {
                if ($scope.searchMode == "users") {
                    if ($rootScope.userId > 0) {
                        return "&q=-id:" + $rootScope.userId;
                    } else {
                        return "&q=*:*";
                    }
                } else if ($scope.searchMode == "opportunity") {
                    var subQuery = "";
                    var dateRangeQuery = "";
                    if ($rootScope.startEndDateRange) {
                        var startDate = "";
                        var endDate = "";
                        if (
                            $("#opportunityEndDate").val() != "" &&
                            $("#opportunityStartDate").val() != ""
                        ) {
                            endDate = new Date(
                                $("#opportunityEndDate").val()
                            ).toISOString();
                            startDate = new Date(
                                $("#opportunityStartDate").val()
                            ).toISOString();
                            dateRangeQuery =
                                "&fq=dateStart:[" +
                                startDate +
                                " TO " +
                                endDate +
                                "]" +
                                "&fq=dateEnd:[" +
                                startDate +
                                " TO " +
                                endDate +
                                "]";
                        } else if (
                            $("#opportunityEndDate").val() == "" &&
                            $("#opportunityStartDate").val() != ""
                        ) {
                            startDate = new Date(
                                $("#opportunityStartDate").val()
                            ).toISOString();
                            dateRangeQuery =
                                "&fq=dateStart:[" + startDate + " TO *]";
                        } else if (
                            $("#opportunityEndDate").val() != "" &&
                            $("#opportunityStartDate").val() == ""
                        ) {
                            endDate = new Date(
                                $("#opportunityEndDate").val()
                            ).toISOString();
                            dateRangeQuery =
                                "&fq=dateEnd:[* TO " + endDate + "]";
                        }

                        subQuery = "&q=*:*" + dateRangeQuery;
                    } else {
                        if (!$rootScope.dateRange) {
                            //Modified by Ravi
                            //   subQuery = subQuery + "&q=dateEnd:[NOW TO NOW%2B1YEAR]";
                        }
                    }

                    if (subQuery == "") {
                        subQuery = "&q=*:*";
                    }

                    return subQuery;
                } else {
                    return "&q=*:*";
                }
            }
        };

        // Sourabh: commented this as this is being called before the field value is passed   $scope.getResultsPage(1);

        // Function to get the connection status for each search result
        $scope.checkConnection = function(userID) {
            $http({
                url: "/party/0/checkConnection/" + userID,
                dataType: "json",
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            }).success(function(response) {
                var resultItemObj = null;
                // fetch the correct result item
                $scope.results.forEach(function(resultItem) {
                    if (userID == resultItem.id) {
                        resultItemObj = resultItem;
                        return;
                    }
                });

                // show buttons appropriately
                if (response.data) {
                    connection = response.data;

                    if (
                        connection.party1.userBean.id == $rootScope.userId &&
                        connection.party2.userBean.id == userID &&
                        connection.connected
                    ) {
                        resultItemObj.ifConnected = true;
                        resultItemObj.tagOn = false;
                    } else if (
                        connection.party2.userBean.id == $rootScope.userId &&
                        connection.party1.userBean.id == userID &&
                        connection.connected
                    ) {
                        resultItemObj.ifConnected = true;
                        resultItemObj.ifPending = false;
                        resultItemObj.tagOn = false;
                    } else if (
                        connection.party1.userBean.id == $rootScope.userId &&
                        connection.party2.userBean.id == userID &&
                        connection.connected == null
                    ) {
                        resultItemObj.ifPending = true;
                        resultItemObj.tagOn = false;
                    } else if (
                        connection.party2.userBean.id == $rootScope.userId &&
                        connection.party1.userBean.id == userID &&
                        connection.connected == null
                    ) {
                        resultItemObj.pendingResponse = true;
                        resultItemObj.ifPending = false;
                        resultItemObj.tagOn = false;
                        resultItemObj.ifConnected = false;
                    }
                } else {
                    resultItemObj.tagOn = true;
                }
            });
        };

        // Function to show the connection status result
        $scope.showConnectionStatus = function(usersList) {
            // check if user loggedin
            if ($rootScope.userId < 0 || !$rootScope.authenticated) {
                return;
            }
            usersList.forEach(function(user) {
                $scope.checkConnection(user.id);
            });
        };

        // Function to send connection invitation from search result
        $scope.sendInviteOnLogin = function(volunteer) {
            console.log('waiting for login to complete before sending invite for: ', volunteer)
            $rootScope.$on("TagOnVolunteer", function(){
                console.log('Login completed. Sending Invite for: ', volunteer);
                (volunteer.ifConnected==="false") && 
                (volunteer.ifPending==="false") && 
                $scope.sendInvite(volunteer);
            })
        }
        $scope.sendInvite = function(displayUserDetails) {
            console.log('sending Invite to: ', displayUserDetails );
            $rootScope.userId !== displayUserDetails.id && $http({
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
                    console.log('successfully sent connection');
                    var resultItemObj = null;
                    // fetch the correct result item
                    $scope.results.forEach(function(resultItem) {
                        if (displayUserDetails.id == resultItem.id) {
                            resultItemObj = resultItem;
                            return;
                        }
                    });

                    resultItemObj.ifPending = true;
                    resultItemObj.tagOn = false;
                })
                .error(function(error) {
                    console.log(
                        "error while getting profile details of user with id:" +
                            $rootScope.userId
                    );
                });
        };

        // Function to reject invite status from search results
        $scope.rejectInvite = function(user) {
            $http({
                url:
                    "/user/" +
                    $rootScope.userId +
                    "/connection/reject/" +
                    user.id,
                dataType: "json",
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .success(function(response) {
                    var resultItemObj = null;
                    // fetch the correct result item
                    $scope.results.forEach(function(resultItem) {
                        if (user.id == resultItem.id) {
                            resultItemObj = resultItem;
                            return;
                        }
                    });

                    resultItemObj.pendingResponse = false;
                    resultItemObj.ifPending = false;
                    resultItemObj.tagOn = true;
                    resultItemObj.ifConnected = false;
                })
                .error(function(error) {
                    console.log(
                        "error while getting profile details of user with id:" +
                            $rootScope.userId
                    );
                });
        };

        // Function to accept invite from search result
        $scope.acceptInvite = function(user) {
            $http({
                url:
                    "/user/" +
                    $rootScope.userId +
                    "/connection/accept/" +
                    user.id,
                dataType: "json",
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .success(function(response) {
                    var resultItemObj = null;
                    // fetch the correct result item
                    $scope.results.forEach(function(resultItem) {
                        if (user.id == resultItem.id) {
                            resultItemObj = resultItem;
                            return;
                        }
                    });
                    resultItemObj.ifConnected = true;
                    resultItemObj.tagOn = false;
                    resultItemObj.pendingResponse = false;
                    resultItemObj.ifPending = false;
                    $rootScope.ltoSuccessMessage =
                        "You are successfully connected to " +
                        resultItemObj.fullName +
                        ".";
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

        // Jquery change event to trigger the solr search on change of search mode
        $("input[name=searchMode]").change(function() {
            var coreName = $("input[name=searchMode]:checked").val();
            if ($scope.searchMode == coreName) {
                // same
                return;
            }
            $scope.searchMode = coreName;
            // console.log("Core name : " +
            // coreName);
            $("#searchPgBtnPrimary").click();
        });

        $("#searchModeOppsDateRange").change(function() {
            $("#opportunityStartDate").val("");
            $("#opportunityEndDate").val("");
            $rootScope.startEndDateRange = $(
                "input[name=searchModeOppsDateRange]:checked"
            ).val();
            $scope.showOppDateRange = !$rootScope.startEndDateRange;
            $("#searchPgBtnPrimary").click();
        });

        // Jquery change event to trigger the solr search on change of get past events checkbox
        $("#searchModeOppsDate").change(function() {
            $rootScope.dateRange = $(
                "input[name=searchModeOppsDate]:checked"
            ).val();

            // console.log("Core name : " +
            // coreName);
            $("#searchPgBtnPrimary").click();
        });
    })
    .directive("ltoSearchDirective", function() {
        return {
            template:
                '<div class="lto-search-container" ng-show="!authenticated"' +
                'ng-controller="SearchController">' +
                '<input type="text" name="trade" ' +
                'class="form-control lto-pre-login-search" ' +
                'ng-model="searchString" ' +
                'placeholder="Search for volunteering opportunities" ' +
                'ng-keypress="($event.which === 13)?redirectToSearchPage():0"><span ' +
                'class="glyphicon glyphicon-search cursor" ' +
                'ng-click="redirectToSearchPage()"></span>' +
                "</div>"
        };
    });
