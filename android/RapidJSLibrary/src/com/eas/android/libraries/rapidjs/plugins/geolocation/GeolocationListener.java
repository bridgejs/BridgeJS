package com.eas.android.libraries.rapidjs.plugins.geolocation;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GeolocationListener implements LocationListener {

	private Location currentLocation;
	
	public GeolocationListener() {
		currentLocation = null;
	}
	
	public void onLocationChanged(Location location) {
		currentLocation = location;
	}

	public void onProviderDisabled(String provider) {
		
	}

	public void onProviderEnabled(String provider) {
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
}
