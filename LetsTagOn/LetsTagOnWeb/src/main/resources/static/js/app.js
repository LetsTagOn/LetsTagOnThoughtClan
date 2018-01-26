/*
 * JS File for histroy support , angular injection , routing
 */
angular
    .module("letstagon", [
        "ngRoute",
        "ngMessages",
        "home",
        "main",
        "completeProfile",
        "search",
        "profile",
        "bootstrapModal",
        "connectionModule",
        "opportunityModule",
        "opportunityManagementModule",
        "about us",
        "contact us",
        "angularUtils.directives.dirPagination",
        "notificationModule",
        "ngLocalize",
        "localization",
        "messageModule",
        "wallModule",
        "angularMoment"
    ])
    .config(function($routeProvider, $httpProvider, $locationProvider) {
        $routeProvider
            .when("/welcome", {
                templateUrl: "./js/pages/common/welcome.html",
                controller: "MainController",
                controllerAs: "controller"
            })
            .when("/", {
                templateUrl: "./js/pages/wall/wall.html",
                controller: "WallController",
                controllerAs: "controller"
            })
            .when("/resetPassword", {
                templateUrl: "./js/pages/password/change_password.html",
                controller: "MainController",
                controllerAs: "controller"
            })
            .when("/cp/personalInformation", {
                templateUrl:
                    "./js/pages/completeYourProfile/personal_information.html",
                controller: "PersonalInformationController",
                controllerAs: "controller"
            })
            .when("/cp/experiences", {
                templateUrl: "./js/pages/completeYourProfile/experience.html",
                controller: "ExperienceController",
                controllerAs: "controller"
            })
            .when("/cp/interests", {
                templateUrl: "./js/pages/completeYourProfile/interests.html",
                controller: "InterestsController",
                controllerAs: "controller"
            })
            .when("/cp/privacy", {
                templateUrl:
                    "./js/pages/completeYourProfile/privacy_settings.html",
                controller: "PrivacySettingsController",
                controllerAs: "controller"
            })
            .when("/profile/user/:userId", {
                templateUrl: "./js/pages/profile/profile_page.html",
                controller: "ProfileController",
                controllerAs: "controller"
            })
            .when("/search/user/:keyword", {
                templateUrl: "./js/pages/search/search.html",
                controller: "SearchController",
                controllerAs: "controller"
            })
            .when("/search/user/", {
                templateUrl: "./js/pages/search/search.html",
                controller: "SearchController",
                controllerAs: "controller"
            })
            .when("/search/opportunity/", {
                templateUrl: "./js/pages/search/search.html",
                controller: "SearchController",
                controllerAs: "controller"
            })
            .when("/view/connection/user/:userId", {
                templateUrl: "./js/pages/connections/connection_listing.html",
                controller: "ConnectionController",
                controllerAs: "controller"
            })
            .when("/pending/connection/user/:userId", {
                templateUrl:
                    "./js/pages/connections/pending_connection_listing.html",
                controller: "NotificationsController",
                controllerAs: "controller"
            })
            .when("/view/opportunity/:oppId", {
                templateUrl: "./js/pages/opportunity/opportunity_detail.html",
                controller: "ViewOpportunityController",
                controllerAs: "controller"
            })
            .when("/view/createdOpportunities", {
                templateUrl: "./js/pages/opportunity/opportunity_listing.html",
                controller: "OpportunityController",
                controllerAs: "controller"
            })
            .when("/view/upcomingOpportunities", {
                templateUrl:
                    "./js/pages/opportunity/opportunity_listing_my_upcoming.html",
                controller: "UpcomingOpportunityController",
                controllerAs: "controller"
            })
            .when("/view/opportunity/edit/:oppID", {
                templateUrl: "./js/pages/opportunity/opportunity_edit.html",
                controller: "OpportunityEditController",
                controllerAs: "controller"
            })
            .when("/opportunity/manageApplications/:oppId", {
                templateUrl:
                    "./js/pages/opportunity/manage/opportunity_manageApplications.html",
                controller: "ManageOppApplicationsController",
                controllerAs: "controller"
            })
            .when("/opportunity/manageFeedback/:oppId", {
                templateUrl:
                    "./js/pages/opportunity/manage/opportunity_manageFeedback.html",
                controller: "ManageOppFeedbackController",
                controllerAs: "controller"
            })
            .when("/opportunity/manageAttendance/:oppId", {
                templateUrl:
                    "./js/pages/opportunity/manage/opportunity_manageAttendance.html",
                controller: "ManageOppAttendanceController"
            })
            .when("/view/opportunity/program/edit/:programID", {
                templateUrl:
                    "./js/pages/opportunity/opportunity_program_event.html",
                controller: "OpportunityProgramEditController",
                controllerAs: "controller"
            })
            .when("/view/myMessages", {
                templateUrl: "./js/pages/messages/messages.html",
                controller: "LtoMessagesController",
                controllerAs: "controller"
            })
            .when("/view/notifications", {
                templateUrl:
                    "./js/pages/notification/view_all_notifications.html",
                controller: "UserNotificationController",
                controllerAs: "controller"
            })
            .when("/linkedIn/professionalDetails", {
                templateUrl:
                    "./js/pages/completeYourProfile/import_professional_details.html",
                controller: "ProfessionalDetailsController",
                controllerAs: "controller"
            })
            .when("/about us", {
                templateUrl: "./js/pages/About Us/about_us.html",
                controller: "aboutcontroller",
                controllerAs: "controller"
            })
            .when("/contact us", {
                templateUrl: "./js/pages/Contact Us/contact_us.html",
                controller: "contactuscontroller",
                controllerAs: "controller"
            })
            .when("/blog", {
                templateUrl: "./js/pages/blog/home.html",
                controller: "BlogController"
                // controllerAs: "controller"
            })
            .otherwise("/");

        $httpProvider.defaults.headers.common["X-Requested-With"] =
            "XMLHttpRequest";
    })
    .run([
        "$rootScope",
        "$location",
        function($rootScope, $location, $scope) {
            $rootScope.$on("$routeChangeStart", function(event) {
                if (
                    $(".lto-pending-notification-user-dropdownList").hasClass(
                        "block"
                    )
                ) {
                    $(".lto-pending-notification-user-dropdownList").addClass(
                        "hide"
                    );
                }

                var path = $location.path();
                var isAuth = sessionStorage.authenticated;
                $rootScope.ifConnections = false;
                $rootScope.showProfileInfo = false;

                // If user is not authenticated
                // then redirect to home page
                if (typeof isAuth == undefined || !isAuth) {
                    event.preventDefault();
                    $location.path("/welcome");
                }

                // If the userInfoTobe displayed
                if (isAuth && path.indexOf("/profile/user/") > -1) {
                    $rootScope.showProfileInfo = true;
                } else if (!isAuth && path.indexOf("/profile/user/") > -1) {
                    $rootScope.showProfileInfo = true;
                } else {
                    $rootScope.showProfileInfo = false;
                }
            });
        }
    ]);
