package com.eas.android.libraries.rapidjs.pluginmanager.activitymodifiers;

import android.content.Intent;

public class SpawnActivityForResult {
	
	private Intent startActivityIntent;
	private ActivityResultCallback activityResultCallback;
	private int requestCode;

	public SpawnActivityForResult(int requestCode, Intent startActivityIntent, ActivityResultCallback activityResultCallback) {
		this.requestCode = requestCode;
		this.startActivityIntent = startActivityIntent;
		this.activityResultCallback = activityResultCallback;
	}
	
	public Intent getStartIntent() {
		return startActivityIntent;
	}
	
	public ActivityResultCallback getActivityResultCallback() {
		return activityResultCallback;
	}
	
	public int getRequestCode() {
		return requestCode;
	}
}
