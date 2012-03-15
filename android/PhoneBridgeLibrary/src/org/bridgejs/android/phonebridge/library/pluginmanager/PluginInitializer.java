package org.bridgejs.android.phonebridge.library.pluginmanager;

import java.util.ArrayList;

import org.bridgejs.android.phonebridge.library.plugins.acceleratedCanvas2D.AcceleratedCanvas2DPlugin;
import org.bridgejs.android.phonebridge.library.plugins.accelerometer.AccelerometerPlugin;
import org.bridgejs.android.phonebridge.library.plugins.button.ButtonPlugin;
import org.bridgejs.android.phonebridge.library.plugins.callback.CallbackPlugin;
import org.bridgejs.android.phonebridge.library.plugins.compass.CompassPlugin;
import org.bridgejs.android.phonebridge.library.plugins.core.CorePlugin;
import org.bridgejs.android.phonebridge.library.plugins.device.DevicePlugin;
import org.bridgejs.android.phonebridge.library.plugins.geolocation.GeolocationJS;
import org.bridgejs.android.phonebridge.library.plugins.geolocation.GeolocationPlugin;

import android.webkit.WebView;


public class PluginInitializer {
	public static void init(ArrayList<BridgeJSPlugin> plugins, PluginManager pluginManager, WebView webView, boolean accelerateCanvas){
		plugins.add(new CorePlugin());
		plugins.add(new CallbackPlugin());
		if (accelerateCanvas)
			plugins.add(new AcceleratedCanvas2DPlugin());
		
		plugins.add(new AccelerometerPlugin());
		plugins.add(new DevicePlugin());
		plugins.add(new GeolocationPlugin());
		plugins.add(new ButtonPlugin());
		plugins.add(new CompassPlugin());
	}
}
