function __gotBackButtonCallback(callbackID, x,y,z,timestamp){
	acceleration = {};
	acceleration.x = x;
	acceleration.y = y;
	acceleration.z = z;
	acceleration.timestamp = timestamp;
	___retrieveCallback(callbackID)(acceleration);
};

function __bindButtonToAndroid() {
	on = {};
	on.backButton = function(isSuper, callback) {
		if (!isSuper) {
			__androidButton.registerNoSuperBackButtonDownCallback(
					___storeCallback(callback) 
			);
		}
		else {
			__androidButton.registerSuperBackButtonDownCallback(
					___storeCallback(callback) 
			);
		}
	};
};

__bindButtonToAndroid();
console.log("button loaded");