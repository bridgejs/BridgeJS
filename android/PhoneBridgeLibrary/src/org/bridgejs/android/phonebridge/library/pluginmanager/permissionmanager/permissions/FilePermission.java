package org.bridgejs.android.phonebridge.library.pluginmanager.permissionmanager.permissions;

import org.bridgejs.android.phonebridge.library.pluginmanager.permissionmanager.Permission;

public class FilePermission implements Permission {

	public String getDescription() {
		return "Allows Program to Get Local Files";
	}

	public String getName() {
		return "File Permission";
	}

	public int getPriority() {
		return Permission.PRIORITY_HIGH;
	}

}
