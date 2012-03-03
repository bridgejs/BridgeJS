var device = {};

function __bindDeviceToAndroid(){
	device.name = __androidDevice.getDeviceName();
	device.phonegap = __androidDevice.getDevicePhonegap();
	device.rapid = __androidDevice.getDeviceRapid();
	device.platform = __androidDevice.getDevicePlatform();
	device.uuid = __androidDevice.getDeviceUUID();
	device.version = __androidDevice.getDeviceVersion();
}

__bindDeviceToAndroid();