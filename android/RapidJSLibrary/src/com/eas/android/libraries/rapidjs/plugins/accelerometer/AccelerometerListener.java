package com.eas.android.libraries.rapidjs.plugins.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class AccelerometerListener implements SensorEventListener {
	
	public float accelerationX;
	public float accelerationY;
	public float accelerationZ;

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			accelerationX = event.values[0];
			accelerationY = event.values[1];
			accelerationZ = event.values[2];
		}
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}
