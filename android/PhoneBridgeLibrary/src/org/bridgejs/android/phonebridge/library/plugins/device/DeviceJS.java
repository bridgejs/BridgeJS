package org.bridgejs.android.phonebridge.library.plugins.device;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;


public class DeviceJS {
	
	private String deviceName;
	private String devicePhonegap;
	private String deviceRapid;
	private String devicePlatform;
	private String deviceUUID;
	private String deviceVersion;
	
	public DeviceJS(PluginRequests requests){
		setDeviceInformation(requests);
	}
	
	private void setDeviceInformation(PluginRequests requests){
		deviceName = Build.DEVICE;
		devicePhonegap = "-1"; // TODO Should represent the version of PhoneGap's API that we're supporting.
							   // This probably isn't the right way to do this
		deviceRapid = "0.0.1"; // TODO This should probably be defined in a metainfo file somewhere
		devicePlatform = "Android";
		
		final TelephonyManager telephonyManager = (TelephonyManager) requests.getSystemService(Context.TELEPHONY_SERVICE);
		deviceUUID = telephonyManager.getDeviceId();

		deviceVersion = Integer.toString(Build.VERSION.SDK_INT);
	}
	
	public String getDeviceName(){
		return deviceName;
	}
	
	public String getDevicePhonegap(){
		return devicePhonegap;
	}
	
	public String getDeviceRapid(){
		return deviceRapid;
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
