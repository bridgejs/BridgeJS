package org.bridgejs.android.phonebridge.library.ui;

import android.os.Handler;

public class HandlerWithLog extends Handler {
	
	public void post(final Object context, final String message, final Runnable uiTask) {
		Runnable taskWithLog = generateLogRunnable(context, message, uiTask);
		super.post(taskWithLog);
	}
	
	public Runnable generateLogRunnable(final Object context, final String message, final Runnable task) {
		return new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(context.getClass() + ", msg: " + message);
				task.run();
			}
		};
	}
	
	public void postDelayed(Object context, String message, Runnable uiTask, long delay) {
		Runnable taskWithLog = generateLogRunnable(context, message, uiTask);
		super.postDelayed(taskWithLog, delay);
	}
}
