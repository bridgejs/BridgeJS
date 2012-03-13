package org.bridgejs.android.phonebridge.library.plugins.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class CompassListener implements SensorEventListener {

	public float azimuth;
	public float pitch;
	public float roll;

	public void onSensorChanged(SensorEvent event) {
	
		if(event.sensor.getType() == Sensor.TYPE_ORIENTATION)
		{
			azimuth = event.values[0];
			pitch = event.values[1];
			roll = event.values[2];
		}
	}
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		
	}
}
