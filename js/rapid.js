function Rapid () {

	function browserIsAndroid(){
		return navigator.userAgent.match(/Android/i);
	}

	function browserIsRapid(){
		return (typeof ___androidExists !== "undefined");
	}

	function browserIsPhoneGap(){

	}

	function recommendUserSwitchesToRapid(){
		if(confirm("Do you want to run this website in Rapid?")) {
			switchThisURLToRapid();
		}
	}

	function switchThisURLToRapid(){
		changeURLTo("rapid://" + document.URL);
	}

	function changeURLTo(newURL){
            location.replace(newUrl);
	}

//=========================================
//client Android Switch to Rapid
//=========================================

	this.android = {};

    android.recommendNative = function () {
		if (browserIsAndroid() && !browserIsRapid()){
			recommendUserSwitchesToRapid();
        }
    };

    android.requireNative = function () {
		if (browserIsAndroid() && !browserIsRapid()){
			switchThisURLToRapid();
        }
    };

//=========================================
//Accelerometer wrapper
//=========================================
	this.accelerometer = {};
	//accelerometer.getCurrentAcceleration = 
	//accelerometer.watchAcceleration = 

}

var rapid = new Rapid();

