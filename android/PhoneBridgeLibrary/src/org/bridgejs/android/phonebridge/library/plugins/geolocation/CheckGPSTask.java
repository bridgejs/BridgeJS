package org.bridgejs.android.phonebridge.library.plugins.geolocation;

import android.location.Location;
import android.os.AsyncTask;

public class CheckGPSTask extends AsyncTask<Long, Void, Location> {

	private CurrentLocationCallback callback;
	private GeolocationListener gpsListener;
	
	public CheckGPSTask(GeolocationListener gpsListener, long timeout, CurrentLocationCallback callback) {
		this.gpsListener = gpsListener;
		this.callback = callback;
		this.execute(timeout);
	}
	
	@Override
	protected Location doInBackground(Long... timeout) {
		long runningTime = 0;
		
		Location currentLocation;
		do {
			System.out.println("Checking the location again");
			currentLocation = gpsListener.getCurrentLocation();
			if (currentLocation != null)
				return currentLocation;
			
			runningTime += 100;
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (runningTime < timeout[0]);
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Location location) {
		callback.onCurrentLocation(location);
	}

}
