package com.eas.android.libraries.rapidjs.plugins.geolocation;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import android.app.Activity;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSRequests;

public class GeolocationJS {

	private RapidJSRequests requests;
	
	private final GeolocationListener networkListener;
	private final GeolocationListener gpsListener;
	
	private final LocationManager locationManager;
	
	private AtomicBoolean isEnabled;
	private AtomicLong minTimeBetweenUpdates;
	
	public GeolocationJS(RapidJSRequests requests) {
		this.requests = requests;
		
		isEnabled.set(false);
		
		networkListener = new GeolocationListener();
		gpsListener = new GeolocationListener();
		
		locationManager = (LocationManager) requests.getSystemService(Activity.LOCATION_SERVICE);
		
		requests.addToOnPause(new Runnable() {
			public void run() {
				if (isEnabled.get())
					turnOffLocationService();
			}
		});
		
		requests.addToOnResume(new Runnable() {
			public void run() {
				if (isEnabled.get())
					turnOnLocationService(minTimeBetweenUpdates.get());
			}
		});
	}
	
	public void getCurrentLocation(int onSuccess, int onError) {
		long timestamp = System.currentTimeMillis();
		
		Location currentLocation = gpsListener.getCurrentLocation();
		if (currentLocation == null)
			currentLocation = networkListener.getCurrentLocation();
		
		String javascriptCallback = "__gotCurrentLocation(" + onSuccess + "," + currentLocation.getLatitude() + 
									"," + currentLocation.getLongitude() + "," + currentLocation.getAccuracy() + 
									", " + currentLocation.getAltitude() + ", " + currentLocation.getBearing() +
									", " + currentLocation.getProvider() + ", " + currentLocation.getSpeed() +
									", " + currentLocation.getTime() + ", " + timestamp +");";
		requests.postJavascript(javascriptCallback);
	}
	
	public boolean isEnabled() {
		return isEnabled.get();
	}
	
	public void enableLocationService(long minTimeBetweenUpdates) {
		this.isEnabled.set(true);
		this.minTimeBetweenUpdates.set(minTimeBetweenUpdates);
		turnOnLocationService(minTimeBetweenUpdates);
	}
	
	public void disableLocationService() {
		isEnabled.set(false);
		turnOffLocationService();
	}
	
	private void turnOffLocationService() {
		locationManager.removeUpdates(networkListener);
		locationManager.removeUpdates(gpsListener);
	}
	
	private void turnOnLocationService(long minTimeBetweenUpdates) {
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTimeBetweenUpdates, 0, networkListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTimeBetweenUpdates, 0, gpsListener);
	}
	
}
