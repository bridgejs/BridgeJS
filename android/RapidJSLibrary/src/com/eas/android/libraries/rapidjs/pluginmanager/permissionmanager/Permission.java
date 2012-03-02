package com.eas.android.libraries.rapidjs.pluginmanager.permissionmanager;

public interface Permission {
	public static final int PRIORITY_LOW = 0;
	public static final int PRIORITY_HIGH = 10;

	public abstract String getDescription();
	public abstract String getName();
	public abstract int getPriority();
}
