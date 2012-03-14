
function __gotCurrentOrientation(callbackID, azimuth,pitch,roll,timestamp){
	directions = {};
	directions.azimuth = azimuth;
	directions.pitch = pitch;
	directions.roll = roll;
	directions.timestamp = timestamp;
	___retrieveCallback(callbackID)(directions);
};

function __bindCompassToAndroid(){
	navigator.compass = {};
	navigator.compass.getCurrentOrientation = function(onSuccess, onError){
		__androidCompass.getCurrentOrientation(
				___storeCallback(onSuccess), 
				___storeCallback(onError)
		);
	};
};

__bindCompassToAndroid();
console.log("compass loaded");