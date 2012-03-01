package com.eas.android.libraries.rapidjs.plugins.accelerometer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewRequests;

public class AccelerometerJS {
	
	private AcceleratedWebViewRequests requests;
	
	private float accelX;
	private float accelY;
	private float accelZ;
	
	public AccelerometerJS(AcceleratedWebViewRequests requests){
		this.requests = requests;
		
		final SensorManager sensorManager = (SensorManager) requests.getSystemService(Activity.SENSOR_SERVICE);
		final Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
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
		requests.postJavascript("__gotCurrentAcceleration(" + onSuccess + "," + accelX + "," + accelY + "," + accelZ + "," + timestamp +");");
	}
	
	private SensorEventListener accelerationListener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				accelX = event.values[0];
				accelY = event.values[1];
				accelZ = event.values[2];
			}
		}
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};
	
}
