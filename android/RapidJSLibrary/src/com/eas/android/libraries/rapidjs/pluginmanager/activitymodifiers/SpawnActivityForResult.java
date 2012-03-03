package com.eas.android.libraries.rapidjs.pluginmanager.activitymodifiers;

import android.content.Intent;

public class SpawnActivityForResult {
	
	private Intent startActivityIntent;
	private ActivityResultCallback activityResultCallback;

	public SpawnActivityForResult(Intent startActivityIntent, ActivityResultCallback activityResultCallback) {
		this.startActivityIntent = startActivityIntent;
		this.activityResultCallback = activityResultCallback;
	}
	
	public Intent getStartIntent() {
		return startActivityIntent;
	}
	
	public ActivityResultCallback getActivityResultCallback() {
		return activityResultCallback;
	}
}
