var ___callbacks = {};
var ___nextCallbackID = 0;

function ___storeCallback(callback){
	var id = ___nextCallbackID;
	___callbacks[id] = callback;
	___nextCallbackID += 1;
	return id;
};

function ___retrieveCallback(id){
	var callback = ___callbacks[id];
	delete ___callbacks[id];
	return callback;
};

console.log("callbacks loaded");