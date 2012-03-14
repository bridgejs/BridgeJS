
function __gotCurrentOrientation(callbackID, azimuth,pitch,roll,timestamp){
	directions = {};
	directions.azimuth = azimuth;
	directions.pitch = pitch;
	directions.roll = roll;
	directions.timestamp = timestamp;
	___retrieveCallback(callbackID)(directions);
};

function __bindOrientationToAndroid(){
	navigator.orientation = {};
	navigator.orientation.getCurrentOrientation = function(onSuccess, onError){
		__androidOrientation.getCurrentOrientation(
				___storeCallback(onSuccess), 
				___storeCallback(onError)
		);
	};
};

__bindOrientationToAndroid();
console.log("compass loaded");