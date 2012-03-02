package com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager.permissions;

import com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager.Permission;

public class DevicePermission implements Permission {

	public String getDescription() {
		return "Allows Program to Get Device Information";
	}

	public String getName() {
		return "Device Permission";
	}

	public int getPriority() {
		return Permission.PRIORITY_LOW;
	}

}
