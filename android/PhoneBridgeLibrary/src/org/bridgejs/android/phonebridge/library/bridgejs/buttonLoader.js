function __gotBackButtonCallback(callbackID){
	___retrieveCallback(callbackID)();
};

function __bindBackButton() {
	navigator.on.backButton = function(isSuper, callback) {
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

function __bindButtonToAndroid() {
	navigator.on = {};
	__bindBackButton();
};

__bindButtonToAndroid();
console.log("button loaded");