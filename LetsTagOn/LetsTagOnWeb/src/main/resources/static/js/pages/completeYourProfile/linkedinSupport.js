//Linkedin login and profile import support




// Setup an event listener to make an API call once auth is complete
function onLinkedInLoad() {
    IN.Event.on(IN, "auth", getProfileData);
}

// Handle the successful return from the API call
function onSuccess(data) {
    console.log(data);
}

// Handle an error response from the API call
function onError(error) {
    console.log(error);
}

// Use the API call wrapper to request the member's basic profile data
function getProfileData() {
    IN.API.Raw("/people/~:(id,num-connections,picture-url,email-address,first-name,last-name,location,summary,positions,specialties,public-profile-url)?format=json").result(onSuccess).error(onError);
}

