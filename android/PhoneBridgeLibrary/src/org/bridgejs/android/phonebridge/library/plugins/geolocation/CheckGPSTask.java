package org.bridgejs.android.phonebridge.library.plugins.geolocation;

import java.util.concurrent.atomic.AtomicBoolean;

import android.location.Location;
import android.os.AsyncTask;

public class CheckGPSTask extends AsyncTask<Long, Void, Location> {

	private CurrentLocationCallback callback;
	private GeolocationListener gpsListener;
	private AtomicBoolean isPaused;
	
	public CheckGPSTask(GeolocationListener gpsListener, long timeout, AtomicBoolean isPaused, CurrentLocationCallback callback) {
		this.gpsListener = gpsListener;
		this.callback = callback;
		this.execute(timeout);
		this.isPaused = isPaused;
	}
	
	@Override
	protected Location doInBackground(Long... timeout) {
		long runningTime = 0;
		
		Location currentLocation;
		do {
			//if paused just return immediately
			if (isPaused.get())
				return null;
			
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
		if (isPaused.get())
			return; //if we are paused don't call callback (injecting js will cause NullPointerException)
		callback.onCurrentLocation(location);
	}

}
