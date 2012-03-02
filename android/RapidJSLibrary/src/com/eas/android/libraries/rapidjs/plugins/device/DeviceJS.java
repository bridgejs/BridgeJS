package com.eas.android.libraries.rapidjs.plugins.device;

import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewRequests;

public class DeviceJS {
	
	private AcceleratedWebViewRequests requests;
	
	private String deviceName;
	private String devicePhonegap;
	private String devicePlatform;
	private String deviceUUID;
	private String deviceVersion;
	
	public DeviceJS(AcceleratedWebViewRequests requests){
		this.requests = requests;
		
		

	}
	
	private void setDeviceInformation(){
		
		
	}
	
	public String getDeviceName(){
		return deviceName;
	}
	
	public String getDevicePhonegap(){
		return devicePhonegap;
	}
	
	public String getDevicePlatform(){
		return devicePlatform;
	}
	
	public String getDeviceUUID(){
		return deviceUUID;
	}
	
	public String getDeviceVersion(){
		return deviceVersion;
	}
	
	

}
