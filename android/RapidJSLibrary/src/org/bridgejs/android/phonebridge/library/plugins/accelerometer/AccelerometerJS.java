package org.bridgejs.android.phonebridge.library.plugins.accelerometer;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class AccelerometerJS {

	private PluginRequests requests;
	private final AccelerometerListener accelerationListener;

	public AccelerometerJS(PluginRequests requests){
		this.requests = requests;

		final SensorManager sensorManager = (SensorManager) requests.getSystemService(Activity.SENSOR_SERVICE);
		final Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		accelerationListener = new AccelerometerListener();

		sensorManager.registerListener(accelerationListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);

		requests.addToOnResume(new Runnable(){
			public void run() {
				sensorManager.registerListener(accelerationListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
			}
		});

		requests.addToOnStop(new Runnable() {
			public void run() {
				sensorManager.unregisterListener(accelerationListener);
			}
		});
	}

	public void getCurrentAcceleration(int onSuccess, int onError){
		long timestamp = System.currentTimeMillis();
		requests.postJavascript("__gotCurrentAcceleration(" + onSuccess + "," + accelerationListener.accelerationX + "," + accelerationListener.accelerationY + "," + accelerationListener.accelerationZ + "," + timestamp +");");
	}

}
