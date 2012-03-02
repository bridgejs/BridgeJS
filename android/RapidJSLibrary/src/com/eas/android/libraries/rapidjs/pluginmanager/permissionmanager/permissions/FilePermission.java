package com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager.permissions;

import com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager.Permission;

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
