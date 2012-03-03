package com.eas.android.libraries.rapidjs.pluginmanager.activitymodifiers;

import android.content.Intent;

public interface ActivityResultCallback {
	public void onActivityResult(int requestCode, int resultCode, Intent i);
}
