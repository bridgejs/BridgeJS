package org.bridgejs.android.phonebridge.library.browser;

import org.bridgejs.android.phonebridge.library.ui.HandlerWithLog;

import android.os.Handler;
import android.widget.ProgressBar;

public class ProgressBarUpdater {

	private ProgressBar progressBar;

	private HandlerWithLog handler;

	public ProgressBarUpdater(ProgressBar progressBar, HandlerWithLog handler){
		this.progressBar = progressBar;
		this.handler = handler;
	}

	public void setProgress(final int progressPercent){
		handler.post(this, "Updating the progress of the progress bar", new Runnable(){
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
