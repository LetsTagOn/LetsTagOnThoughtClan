//JS FILE  -  Google address autocomplete and cordinates calc using address and google map display
var placeSearch, autocomplete;
var componentForm = {
    street_number: "long_name",
    /*  route: 'long_name', */
    locality: "long_name",
    administrative_area_level_1: "long_name",
    country: "long_name",
    postal_code: "long_name"
};

function initAutocomplete() {
    // Create the autocomplete object, restricting the search to geographical
    // location types.
    autocomplete = new google.maps.places
        .Autocomplete(
        /** @type {!HTMLInputElement} */
        (document.getElementById("autocomplete")),
        {
            types: ["geocode"]
        }
    );

    // When the user selects an address from the dropdown, populate the address
    // fields in the form.
    autocomplete.addListener("place_changed", fillInAddress);
}

function fillInAddress() {
    // Get the place details from the autocomplete object.
    var place = autocomplete.getPlace();
    document.getElementsByName("formattedAddress").value =
        place.formatted_address;
    for (var component in componentForm) {
        document.getElementById(component).value = "";
        document.getElementById(component).disabled = false;
    }

    // Get each component of the address from the place details
    // and fill the corresponding field on the form.
    for (var i = 0; i < place.address_components.length; i++) {
        var addressType = place.address_components[i].types[0];
        if (componentForm[addressType]) {
            var val = place.address_components[i][componentForm[addressType]];
            document.getElementById(addressType).value = val;
        }
    }
}

// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var geolocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            document.getElementById("latitude").value = "";
            document.getElementById("longitude").value = "";
            document.getElementById("latitude").value = geolocation.lat;
            document.getElementById("longitude").value = geolocation.lng;
            var circle = new google.maps.Circle({
                center: geolocation,
                radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
        });
    }
}

var map;
var latitude;
var longitude;

function initMap(latLng) {
    console.log("Maps initialised");
    var directions;
    var myLatLng;
    if (latLng) {
        directions = latLng.split(",");
        console.log("directions", directions);
        myLatLng = {
            lat: Number(directions[0]),
            lng: Number(directions[1])
        };
        console.log("myLatLng: ", myLatLng);
    } else {
        myLatLng = {
            lat: -34.397,
            lng: 150.644
        };
    }

    console.log("typeof lat :", typeof myLatLng.lat);

    var latlngOption = new google.maps.LatLng(myLatLng.lat, myLatLng.lng);
    var map = new google.maps.Map(document.getElementById("map"), {
        center: latlngOption,
        zoom: 15
    });

    var marker = new google.maps.Marker({
        position: latlngOption,
        map: map,
        title: "Hello World!"
    });
}

/*function initMap(latLng) {
     var myLatLng = {lat: -25.363, lng: 131.044};

     var map = new google.maps.Map(document.getElementById('map'), {
       zoom: 4,
       center: myLatLng
     });

     var marker = new google.maps.Marker({
       position: myLatLng,
       map: map,
       title: 'Hello World!'
     });
}
*/
function getLatitudeLongitude(address) {
    // If adress is not supplied, use default value 'Ferrol, Galicia, Spain'

    return new Promise(function(resolve, reject) {
        address = address;
        // console.log("address in getLatLong", address);
        // Initialize the Geocoder
        geocoder = new google.maps.Geocoder();
        if (geocoder) {
            geocoder.geocode(
                {
                    address: address
                },
                function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        var latlng = {
                            lat: results[0].geometry.location.lat(),
                            lng: results[0].geometry.location.lng()
                        };
                        resolve(latlng);
                        // document.getElementById(
                        //     "latitude"
                        // ).value = results[0].geometry.location.lat();
                        // document.getElementById(
                        //     "longitude"
                        // ).value = results[0].geometry.location.lng();
                        // console.log(
                        //     "latitude" + document.getElementById("latitude").value
                        // );
                        // console.log(
                        //     "longitude" + document.getElementById("longitude").value
                        // );
                    } else {
                        reject("no match");
                        // throw new Error("no matching lat long found");
                        // document.getElementById("latitude").value = status;
                    }
                }
            );
        }
    });
}
