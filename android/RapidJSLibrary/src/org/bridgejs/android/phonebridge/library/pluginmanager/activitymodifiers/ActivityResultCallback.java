package org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers;

import android.content.Intent;

public interface ActivityResultCallback {
	public void onActivityResult(int requestCode, int resultCode, Intent i);
}
