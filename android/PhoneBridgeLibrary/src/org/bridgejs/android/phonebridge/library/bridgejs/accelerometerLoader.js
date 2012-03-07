
function __gotCurrentAcceleration(callbackID, x,y,z,timestamp){
	acceleration = {};
	acceleration.x = x;
	acceleration.y = y;
	acceleration.z = z;
	acceleration.timestamp = timestamp;
	___retrieveCallback(callbackID)(acceleration);
};

function __bindAccelerometerToAndroid(){
	navigator.accelerometer = {};
	navigator.accelerometer.getCurrentAcceleration = function(onSuccess, onError){
		__androidAccelerometer.getCurrentAcceleration(
				___storeCallback(onSuccess), 
				___storeCallback(onError)
		);
	};
	navigator.accelerometer.watchAcceleration = function(onSuccess, onError, options){
		setInterval(function(){
			navigator.accelerometer.getCurrentAcceleration(onSuccess, onError);
		}, options.frequency);
	};

//	window.mWatch("ondevicemotion", function(id, oldval, newval){
//	navigator.accelerometer.watchAcceleration(function(acceleration){
//	var event = {};
//	event.accelerationIncludingGravity = {};
//	event.accelerationIncludingGravity.x = acceleration.x;
//	event.accelerationIncludingGravity.y = acceleration.y;
//	event.accelerationIncludingGravity.z = acceleration.z;
//	newval(event);
//	}, new function(){ }, {frequency: 50});
//	return newval;
//	});
	__watch(window, "ondevicemotion", function(id, oldval, newval){
		navigator.accelerometer.watchAcceleration(function(acceleration){
			var event = {};
			event.accelerationIncludingGravity = {};
			event.accelerationIncludingGravity.x = acceleration.x;
			event.accelerationIncludingGravity.y = acceleration.y;
			event.accelerationIncludingGravity.z = acceleration.z;
			newval(event);
		}, new function(){ }, {frequency: 50});
		return newval;
	});
};

function __watch(obj, prop, handler){
	var oldval = obj[prop], newval = oldval,
	getter = function () {
		return newval;
	},
	setter = function (val) {
		oldval = newval;
		return newval = handler.call(obj, prop, oldval, val);
	};
	delete obj[prop];
	if (Object.defineProperty) {
		Object.defineProperty(obj, prop, {
			get: getter,
			set: setter,
			enumerable: false,
			configurable: true
		});
	}
};

//if (!Object.prototype.mWatch) {
//Object.prototype.mWatch = function (prop, handler) {
//var oldval = this[prop], newval = oldval,
//getter = function () {
//return newval;
//},
//setter = function (val) {
//oldval = newval;
//return newval = handler.call(this, prop, oldval, val);
//};
//if (delete this[prop]) {
//if (Object.defineProperty) {
//Object.defineProperty(this, prop, {
//get: getter,
//set: setter,
//enumerable: false,
//configurable: true
//});
//} else if (Object.prototype.__defineGetter__ && Object.prototype.__defineSetter__) {
//Object.prototype.__defineGetter__.call(this, prop, getter);
//Object.prototype.__defineSetter__.call(this, prop, setter);
//};
//};
//};
//};

__bindAccelerometerToAndroid();
console.log("accelerometer loaded");