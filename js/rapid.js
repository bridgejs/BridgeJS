function Rapid () {

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
		if(confirm("Do you want to run this website in Rapid?")) {
			switchThisURLToRapid();
		}
	}

	function switchThisURLToRapid(){
		changeURLTo("rapid://" + document.URL);
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

    this.android.requireNative = function () {
		if (browserIsAndroid() && !browserIsRapid()){
			switchThisURLToRapid();
        }
    };

//=========================================
//Accelerometer wrapper
//=========================================
//API:
//	rapid.accelerometer.getCurrentAcceleration(onSuccess, onError)
//	rapid.accelerometer.watchAcceleration(onSuccess, onError)
//=========================================
	this.accelerometer = {};

	function browserSupportsWebkitAccelerometer(){
		return true; //TODO: I don't know how to actually check this
	}

	if (browserIsRapid()){
		this.accelerometer.getCurrentAcceleration = navigator.accelerometer.getCurentAcceleration;
		this.accelerometer.watchAcceleration = navigator.accelerometer.watchAcceleration;
	}
	if (browserSupportsPhoneGap()){
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

}

var rapid = new Rapid();

