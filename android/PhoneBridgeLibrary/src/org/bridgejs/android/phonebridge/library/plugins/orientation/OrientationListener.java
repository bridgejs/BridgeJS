package org.bridgejs.android.phonebridge.library.plugins.orientation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class OrientationListener implements SensorEventListener {

	public float azimuth; //standard 2-d compass
	public float pitch;   //upwards and downwards
	public float roll;	//left and right tilt

	public void onSensorChanged(SensorEvent event) {
	
		if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
			azimuth = event.values[0];
			pitch = event.values[1];
			roll = event.values[2];
		}
	}
	
	public void onAccuracyChanged(Sensor arg0, int arg1) {		
	}
	
}
