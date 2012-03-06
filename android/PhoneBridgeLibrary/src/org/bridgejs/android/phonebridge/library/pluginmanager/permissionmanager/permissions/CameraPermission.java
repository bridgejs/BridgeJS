package org.bridgejs.android.phonebridge.library.pluginmanager.permissionmanager.permissions;

import org.bridgejs.android.phonebridge.library.pluginmanager.permissionmanager.Permission;

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