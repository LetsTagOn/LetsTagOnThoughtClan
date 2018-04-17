var ltoMessagesModule = angular.module("messageModule", []);

/*
 * Controller for Messages
 * 
 */
ltoMessagesModule.controller("LtoMessagesController", function(
    $http,
    $scope,
    $rootScope,
    $location,
    $timeout,
    $anchorScroll
) {
    $scope.conversationsSize = 5;
    $scope.pageNumber = 0;
    var ready = true;
    // ================================================ Method to fetch conversation contact list =====================================================
    $scope.contactList = [];
    $scope.getContactList = function() {
        $http({
            url: "/conversationContactsList",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.contactList = [];
                $scope.contactList = response.searchResult;
                $scope.contactList &&
                    $scope.getConversation($scope.contactList[0]);
            })
            .error(function() {
                console.log(
                    "Something went wrong while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.addClassSelected = function(contact) {
        $(".lto-msg-contact-list").removeClass("lto-mess-usr-selected");
        $(".lto-msg-usr-name").removeClass("lto-mess-usr-label");
        $("#contact" + contact.id).addClass("lto-mess-usr-selected");
        $("#contact" + contact.userBean.firstName).addClass(
            "lto-mess-usr-label"
        );
        $("#contactM" + contact.id).addClass("lto-mess-usr-selected");
        $("#contactM" + contact.userBean.firstName).addClass(
            "lto-mess-usr-label"
        );
    };

    $scope.goDown = function() {
        $anchorScroll();
    };
    // ================================================ End ======================================================
    // ================================================ Method to fetch conversations with particular party ======================================================
    $scope.conversationList = [];
    $scope.currentConversationPartyId = "";
    $scope.getConversation = function(party) {
        $http({
            url: "/conversations/" + party.id,
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.conversationList = [];
                $scope.conversationList = response.searchResult.reverse();
                $scope.currentConversationPartyId = party.id;
                $scope.addClassSelected(party);
                $scope.conversationsSize = 5;
                $scope.pageNumber = 0;
                ready = true;
            })
            .error(function() {
                console.log(
                    "Something went wronf while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.loadConversationsOnScroll = function(currentConversationPartyId) {
        if (
            currentConversationPartyId != undefined ||
            currentConversationPartyId != ""
        ) {
            $scope.pageNumber = $scope.pageNumber + 1;
            $http({
                url: "/conversations/" + currentConversationPartyId,
                dataType: "json",
                method: "GET",
                params: {
                    page: $scope.pageNumber,
                    size: $scope.conversationsSize
                },
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .success(function(response) {
                    if (response.searchResult.length > 0) {
                        response.searchResult.forEach(function(response) {
                            $scope.conversationList.unshift(response);
                        });
                        $scope.currentConversationPartyId = currentConversationPartyId;
                        $(".lto-message-cont-section").scrollTop(0);
                    }
                    ready = true;
                    //$scope.addClassSelected(party);
                })
                .error(function() {
                    console.log(
                        "Something went wrong while fetching conversation for the logged in user id:" +
                            $rootScope.userId
                    );
                });
        }
    };

    $(".lto-message-cont-section").on("scroll", function(e) {
        e.preventDefault();
        if (ready) {
            ready = false;
            $scope.loadConversationsOnScroll($scope.currentConversationPartyId);
        }
    });

    // ================================================ End ======================================================

    // =============================================== Function to get contact suggestion list ===================
    $scope.isAddContacts = true;

    $scope.showSearchBox = function() {
        $scope.isAddContacts = $scope.isAddContacts ? false : true;
    };

    $scope.suggestionListOfUserByNames = [];
    $scope.search = {};
    $scope.getChatSuggestionList = function(name) {
        if (name == undefined) {
            name = $scope.search.name;
        }
        $http({
            url: "/conversation/contactsListSearch",
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
                $scope.suggestionListOfUserByNames = [];
                $scope.suggestionListOfUserByNames = response.data.searchResult;
            })
            .error(function() {
                console.log(
                    "Something went wronf while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };

    $scope.startConversationWithUser = function(party) {
        //To add the contact to contact list and
        //open conversation container with him
        $scope.contactList.forEach(function(contact) {
            if (party.id == contact.id) {
                var index = $scope.contactList.indexOf(contact);
                $scope.contactList.splice(index, 1);
            }
        });
        $scope.contactList.unshift(party);
        $scope.getConversation(party);
    };
    // =========================================== End ====================================================================================

    // =========================================== Function to start conversation/start message with party ================================
    $scope.message = {};
    $scope.conversationList = [];
    $scope.sendMessageToParty = function() {
        var message = new Object();
        message.messageText = $scope.message.messageText;
        var party = new Object();
        party.id = $scope.currentConversationPartyId;
        message.toParty = party;
        $http({
            url: "/message",
            dataType: "json",
            method: "POST",
            data: message,
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response.error == null) {
                    $scope.message.messageText = "";
                    $scope.conversationList.push(response.data);
                    $(".lto-message-cont-section").scrollTop(
                        $(".lto-message-cont-section")[0].scrollHeight
                    );
                }
            })
            .error(function() {
                console.log(
                    "Something went wronf while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };
    // ======================================= End =============================================================================================

    // ================================================== Function to delete conversation between parties ======================================

    $scope.deleteConversation = function(toPartyId) {
        $http({
            url: "/deleteConversation/withParty/" + toPartyId,
            dataType: "json",
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                //TO remove contact and conversation from the list respectively
                $scope.contactList.forEach(function(contact) {
                    if (contact.id == toPartyId) {
                        var index = $scope.contactList.indexOf(contact);
                        $scope.contactList.splice(index, 1);
                    }
                });
                $scope.conversationList = [];
                $scope.getConversation($scope.contactList[0]);
            })
            .error(function() {
                console.log(
                    "Something went wronf while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };
    //======================================= End =============================================================================================

    //======================================= Function to getUnread Messages =============================================================================================
    $scope.unreadMessagelist = [];

    // $scope.getUnreadMessages();

    $scope.totalUnreadMessagesPerPage = 10;
    $scope.PageNumberForUm = 0;
    var ready = true;
    $scope.getUnreadMessages = function() {
        $http({
            url: "/getUnreadMessages",
            dataType: "json",
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
            params: {
                page: $scope.PageNumberForUm,
                size: $scope.totalUnreadMessagesPerPage
            }
        })
            .success(function(response) {
                console.log("got unread messages: ", response);
                response.searchResult &&
                    response.searchResult.forEach(function(response) {
                        $scope.unreadMessagelist.push(response);
                    });
                ready = true;
            })
            .error(function() {
                console.log(
                    "Something went wrong while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };

    $("#message-scroll-container").on("scroll", function(e) {
        e.preventDefault();
        if (ready) {
            ready = false;
            $scope.PageNumberForUm = $scope.PageNumberForUm + 1;
            $scope.getUnreadMessages();
        }
    });

    //======================================= End =============================================================================================
    //======================================= Function to mark message as read =============================================================================================

    
    $scope.markMessageAsRead = function(message) {
        $location.path("/view/myMessages");
        $http({
            url: "/mark/message/read/" + message.id,
            dataType: "json",
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                $scope.unreadMessagelist.forEach(function(contact) {
                    if (contact.id == response.data.id) {
                        var index = $scope.unreadMessagelist.indexOf(contact);
                        $scope.unreadMessagelist.splice(index, 1);
                    }
                });
                $scope.startConversationWithUser(response.data.fromParty);
                $timeout(function() {
                    $scope.startConversationWithUser(response.data.fromParty);
                }, 1000);
            })
            .error(function() {
                console.log(
                    "Something went wrong while fetching conversation for the logged in user id:" +
                        $rootScope.userId
                );
            });
    };
    //===================Function to mark all messages as Read =================================
    $scope.markAllMessagesAsRead = function() {
        //need to get the correct api end point for this
        $http({
            url: "/message/markAll/read/0",
            dataType: "json",
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .success(function(response) {
                if (response.error == null) {
                    $rootScope.unreadMessagelist = [];
                }
            })
            .error(function(error) {
                console.log(
                    "error while getting message list for the userID" +
                        $rootScope.userId
                );
            });
    };
});

//======================================= End =============================================================================================
