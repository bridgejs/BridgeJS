package com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager.permissions;

import com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager.Permission;

public class AccelerometerPermission  implements Permission {

	public String getDescription() {
		return "Allows Program to Get Accelerometer Data";
	}

	public String getName() {
		return "Accelerometer Permission";
	}

	public int getPriority() {
		return Permission.PRIORITY_LOW;
	}

}
