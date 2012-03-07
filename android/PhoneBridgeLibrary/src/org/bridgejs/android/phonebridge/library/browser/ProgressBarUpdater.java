package org.bridgejs.android.phonebridge.library.browser;

import android.widget.ProgressBar;

public class ProgressBarUpdater {
	
	private ProgressBar progressBar;
	
	public ProgressBarUpdater(ProgressBar progressBar){
		this.progressBar = progressBar;
	}
	
	public void setProgress(int progressPercent){
		progressBar.setProgress(progressPercent);
		if (progressPercent >= 100)
			progressBar.setVisibility(ProgressBar.GONE);
//		else 
//			progressBar.setVisibility(ProgressBar.VISIBLE);
		progressBar.invalidate();
	}
}
