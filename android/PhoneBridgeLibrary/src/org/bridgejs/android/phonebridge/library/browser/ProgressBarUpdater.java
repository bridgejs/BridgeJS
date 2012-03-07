package org.bridgejs.android.phonebridge.library.browser;

import android.os.Handler;
import android.widget.ProgressBar;

public class ProgressBarUpdater {

	private ProgressBar progressBar;

	private Handler handler;

	public ProgressBarUpdater(ProgressBar progressBar, Handler handler){
		this.progressBar = progressBar;
		this.handler = handler;
	}

	public void setProgress(final int progressPercent){
		handler.post(new Runnable(){
			public void run(){
				if (progressPercent >= 100)
					progressBar.setVisibility(ProgressBar.GONE);
				else 
					progressBar.setVisibility(ProgressBar.VISIBLE);
				progressBar.setProgress(progressPercent);
			}
		});
	}
}
