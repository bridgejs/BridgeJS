package org.bridgejs.android.phonebridge.library.plugins.orientation;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.plugins.accelerometer.AccelerometerListener;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class OrientationJS {
	private PluginRequests requests;
	private final OrientationListener compassListener;
	
	public OrientationJS(PluginRequests requests){
		this.requests = requests;
		
		final SensorManager sensorManager = (SensorManager) requests.getSystemService(Activity.SENSOR_SERVICE);
		final Sensor compassSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		compassListener = new OrientationListener();

		sensorManager.registerListener(compassListener, compassSensor, SensorManager.SENSOR_DELAY_GAME);

		requests.addToOnResume(new Runnable(){
			public void run() {
				sensorManager.registerListener(compassListener, compassSensor, SensorManager.SENSOR_DELAY_GAME);
			}
		});

		requests.addToOnStop(new Runnable() {
			public void run() {
				sensorManager.unregisterListener(compassListener);
			}
		});
		
	}
	public void getCurrentOrientation(int onSuccess, int onError){
		long timestamp = System.currentTimeMillis();
		requests.postJavascript("__gotCurrentOrientation(" + onSuccess +"," + compassListener.azimuth+","+compassListener.pitch+","+compassListener.roll+","+timestamp+");",this);
	}
	
}