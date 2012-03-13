function __gotButtonCallback(callbackID){
	___retrieveCallback(callbackID)();
};

function __bindButtons() {
	navigator.on.backButton = __makeOnInterface("BackButton");
	navigator.on.volumedownButton = __makeOnInterface("VolumedownButton");
	navigator.on.volumeupButton = __makeOnInterface("VolumeupButton");
	navigator.on.menuButton = __makeOnInterface("MenuButton");
	navigator.on.homeButton = __makeOnInterface("HomeButton");
};

function __makeOnInterface(whichButton) {
	//YES, THIS IS SELF-MODIFYING CODE!
	eval("patchedFunction = " + __macroButton.toString().replace("QQ", whichButton).replace("QQ", whichButton));
	return patchedFunction;
};

function __macroButton(isSuper, callback) {
	if (!isSuper) {
		__androidButton.registerNoSuperQQDownCallback(
				___storeCallback(callback) 
		);
	}
	else {
		__androidButton.registerSuperQQDownCallback(
				___storeCallback(callback) 
		);
	}
};

function __bindButtonToAndroid() {
	navigator.on = {};
	__bindButtons();
};

__bindButtonToAndroid();
console.log("button loaded");