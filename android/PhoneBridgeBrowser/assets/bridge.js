function Bridge () {

	function browserIsAndroid(){
		return navigator.userAgent.match(/Android/i);
	}

	function browserIsRapid(){
		return (typeof ___androidExists !== "undefined");
	}

	function browserSupportsPhoneGap(){
		return (typeof device !== "undefined" &&
				typeof device.phonegap !== "undefined");
	}

	function recommendUserSwitchesToRapid(){
		if(confirm("Do you want to run this website with the PhoneBridge browser?")) {
			switchThisURLToRapid();
		}
	}

	function switchThisURLToRapid(){
		changeURLTo("bridge://" + document.URL);
	}

	function changeURLTo(newUrl){
            location.replace(newUrl);
	}

//=========================================
//client Android Switch to Rapid
//=========================================

	this.android = {};

    this.android.recommendNative = function () {
		if (browserIsAndroid() && !browserIsRapid()){
			recommendUserSwitchesToRapid();
        }
    };

    this.android.browserIsBridge = function() {
        return browserIsRapid();
    };

    this.android.requireNative = function () {
		if (browserIsAndroid() && !browserIsRapid()){
			switchThisURLToRapid();
        }
    };

//=========================================
//Accelerometer wrapper
//=========================================
//API:
//	bridge.accelerometer.getCurrentAcceleration(onSuccess, onError)
//	bridge.accelerometer.watchAcceleration(onSuccess, onError)
//=========================================
	this.accelerometer = {};

	function browserSupportsWebkitAccelerometer(){
		return true; //TODO: I don't know how to actually check this
	}

	if (browserIsRapid()){
		this.accelerometer.getCurrentAcceleration = navigator.accelerometer.getCurentAcceleration;
		this.accelerometer.watchAcceleration = navigator.accelerometer.watchAcceleration;
	}
	else if (browserSupportsPhoneGap()){
		this.accelerometer.getCurrentAcceleration = navigator.accelerometer.getCurentAcceleration;
		this.accelerometer.watchAcceleration = navigator.accelerometer.watchAcceleration;
	}
	else if (browserSupportsWebkitAccelerometer()){
		
		var fnsWatchingAccelerometerOnSuccess = {};
		var nextIDForFnsWatchingAccelerometerOnSuccess = 0;
		var fnsWatchingAccelerometerOnError = {};
		var nextIDForFnsWatchingAccelerometerOnFail = 0;

		var lastEvent = null;

		window.ondevicemotion = function(event){
			lastEvent = event;
			for (fnId in fnsWatchingAccelerometerOnSuccess) {
				fnsWatchingAccelerometerOnSuccess[fnId](event);
			}
		}
		this.accelerometer.getCurrentAcceleration = function(onSuccess, onError){
			if (typeof lastEvent !== null){
				onSuccess(lastEvent);
			}
			else {
				onError();
			}
		}
		this.accelerometer.watchAcceleration = function(onSuccess, onError){
			fnsWatchingAccelerometerOnSuccess[nextIDForFnsWatchingAccelerometerOnSuccess] = onSuccess
			nextIDForFnsWatchingAccelerometerOnSuccess += 1;
			fnsWatchingAccelerometerOnError[nextIDForFnsWatchingAccelerometerOnError] = onError
			nextIDForFnsWatchingAccelerometerOnSuccess += 1;
		}
	}
	else {
		console.log("Device does not accept accelerometer!");
	}
	//accelerometer.getCurrentAcceleration = 
	//accelerometer.watchAcceleration = 

//=========================================
//Button wrapper
//=========================================
//API:
//	bridge.android.on.backButton(isSuper, callback)
//  bridge.android.on.volumedownButton(isSuper, callback)
//  bridge.android.on.volumeupButton(isSuper, callback)
//  bridge.android.on.menuButton(isSuper, callback)
//  bridge.android.on.homeButton(isSuper, callback)
//=========================================
    this.android.on = navigator.on;


//=========================================
//Geolocation wrapper
//=========================================
//API:
//	bridge.geolocation.getCurrentPosition(onSuccess, onError)
//	bridge.geolocation.isEnabled()
//	bridge.geolocation.enableLocationService()
//	bridge.geolocation.disableLocationService()
//=========================================
    this.geolocation = {};

	function browserSupportsWebkitGeolocation(){
		return true; //TODO: I don't know how to actually check this
	}

    //TODO: Support the options parameter like in the HTML5 and PhoneGap spec
	if (browserIsRapid()){
		this.geolocation.getCurrentPosition = navigator.geolocation.getCurrentPosition;
		this.geolocation.isEnabled = navigator.geolocation.isEnabled;
		this.geolocation.enableLocationService = navigator.geolocation.enableLocationService;
		this.geolocation.disableLocationService = navigator.geolocation.disableLocationService;
	}
	else if (browserSupportsPhoneGap()){
		this.geolocation.getCurrentPosition = navigator.geolocation.getCurrentPosition;
		this.geolocation.isEnabled = navigator.geolocation.isEnabled;
		this.geolocation.enableLocationService = navigator.geolocation.enableLocationService;
		this.geolocation.disableLocationService = navigator.geolocation.disableLocationService;
	}

	else if (browserSupportsWebkitGeolocation()){
		this.geolocation.getCurrentPosition = navigator.geolocation.getCurrentPosition;
		this.geolocation.isEnabled = navigator.geolocation.isEnabled;
		this.geolocation.enableLocationService = navigator.geolocation.enableLocationService;
		this.geolocation.disableLocationService = navigator.geolocation.disableLocationService;
    }
	else {
		console.log("Device does not accept geolocation!");
	}
//=========================================
//Device wrapper
//=========================================
//API:
//	bridge.device.getCurrentPosition(onSuccess, onError)
//	bridge.geolocation.isEnabled()
//	bridge.geolocation.enableLocationService()
//	bridge.geolocation.disableLocationService()
//=========================================

//=========================================
//Orientation Wrapper
//=========================================
//API:
//	bridge.orientation.getCurrentOrientation(onSuccess,onError)
//		object returned contains:
//			.azimuth
//			.pitch
//			.roll
//			.timestamp
//=========================================
    this.orientation = {};

	function browserSupportsWebkitCompass(){
		return true; //TODO: I don't know how to actually check this
	}
    //TODO: Support the options parameter like in the HTML5 and PhoneGap spec
	if (browserIsRapid()){
		this.orientation.getCurrentOrientation = navigator.compass.getCurrentOrientation;
	}
	else if (browserSupportsPhoneGap()){
		this.orientation.getCurrentOrientation = navigator.compass.getCurrentOrientation;
	}
	else if (browserSupportsWebkitCompass()){
		this.orientation.getCurrentOrientation = navigator.compass.getCurrentOrientation;
	}
	else {
		console.log("Device does not accept Orientation!");
	}
}

var bridge = new Bridge();

