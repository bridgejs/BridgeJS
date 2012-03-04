
function __gotCurrentLocation(callbackID, latitude, longitude, accuracy, altitude, bearing, provider, speed, time, timestamp) {
	
	coords = {};
	coords.latitude = latitude;
	coords.longitude = longitude;
	coords.accuracy = accuracy;
	coords.bearing = bearing;
	coords.provider = provider;
	coords.speed = speed;
	coords.time = time;
	
	position = {};
	position.coords = coords;
	position.timestamp = timestamp;
	
	___retrieveCallback(callbackID)(position);
};

function __bindGeolocationToAndroid() {
	navigator.geolocation = {};
	navigator.geolocation.enableLocationService = function(minTimeBetweenUpdates) {
		__androidGeolocation.enableLocationService(minTimeBetweenUpdates);
	};
	navigator.geolocation.disableLocationService = function() {
		__androidGeolocation.disableLocationService();
	};
	navigator.geolocation.isEnabled = function() {
		__androidGeolocation.isEnabled(); //TODO: Do I have to explicitly inject JS code to return something?
	};
	
	/* A call to getCurrentLocation implicitly turns on the location service */
	navigator.geolocation.getCurrentLocation = function(onSuccess, onError){
		if (!navigator.geolocation.isEnabled()) {
			navigator.geolocation.enableLocationService();
		}
		
		__androidGeolocation.getCurrentLocation(
				___storeCallback(onSuccess), 
				___storeCallback(onError)
		);
	};
};

__bindGeolocationToAndroid();