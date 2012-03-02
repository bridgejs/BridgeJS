package com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager.permissions;

import com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager.Permission;

public class CameraPermission implements Permission {

	public String getDescription() {
		return "Allows Program to Get a Picture From the Camera";
	}

	public String getName() {
		return "Camera Permission";
	}

	public int getPriority() {
		return Permission.PRIORITY_HIGH;
	}

}