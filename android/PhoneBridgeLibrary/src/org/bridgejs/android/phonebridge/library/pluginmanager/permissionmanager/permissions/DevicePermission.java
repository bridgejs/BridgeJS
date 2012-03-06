package org.bridgejs.android.phonebridge.library.pluginmanager.permissionmanager.permissions;

import org.bridgejs.android.phonebridge.library.pluginmanager.permissionmanager.Permission;

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
