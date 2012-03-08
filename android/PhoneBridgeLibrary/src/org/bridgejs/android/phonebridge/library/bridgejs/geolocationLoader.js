
function __gotCurrentLocation(callbackID, latitude, longitude, accuracy, altitude, bearing, provider, speed, time, timestamp) {
	
	console.log("In ___gotCurrentLocation");
	console.log(latitude + ", " + longitude + ", " + accuracy + ", " + altitude + ", " + bearing + ", " + provider + ", " + speed + ", " + time + ", " + timestamp);
	coords = {};
	coords.latitude = latitude;
	coords.longitude = longitude;
	coords.accuracy = accuracy;
	coords.altitude = altitude;
	coords.bearing = bearing;
	coords.provider = provider;
	coords.speed = speed;
	coords.time = time;
	
	position = {};
	position.coords = coords;
	position.timestamp = timestamp;
	
	___retrieveCallback(callbackID)(position);
};

function __notGotCurrentLocation(callbackID) {
	___retrieveCallback(callbackID)();
};

function __bindGeolocationToAndroid() {
	console.log("Bound Geolocation to android");
	alert("TESTING ALRET");
	navigator.geolocation = {};
	navigator.geolocation.enableLocationService = function(minTimeBetweenUpdates) {
		__androidGeolocation.enableLocationService(minTimeBetweenUpdates);
	};
	navigator.geolocation.disableLocationService = function() {
		__androidGeolocation.disableLocationService();
	};
	navigator.geolocation.isEnabled = function() {
		__androidGeolocation.isEnabled();
	};
	
	/* A call to getCurrentLocation implicitly turns on the location service */
	navigator.geolocation.getCurrentPosition = function(onSuccess, onError){
		console.log("I called getCurrentPosition");
		
		if (!navigator.geolocation.isEnabled()) {
			navigator.geolocation.enableLocationService();
		}
		
		console.log("Before the native call after enabled");
		
		__androidGeolocation.getCurrentPosition(
				___storeCallback(onSuccess), 
				___storeCallback(onError)
		);
		
		console.log("After the native call");
	};
};

__bindGeolocationToAndroid();
console.log("geolocation loaded");