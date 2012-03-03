package com.eas.android.libraries.rapidjs.pluginmanager.activitymodifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/* I think this is a pretty good solution to the problem of adding code to the main activity */
public class ActivityEventsModifier {

	private Modifiers modifiers;
	private Activity activity;
	private SpawnActivityForResult spawnActivityInstance;
	
	//TODO: Etc...

	public ActivityEventsModifier(Activity activity) {
		modifiers = new Modifiers();
		this.activity = activity;
	}

	public void addToOnResumeModifier(final Runnable newAction) {
		modifiers.onResumeModifiers.add(newAction);
	}

	public void addToOnStopModifier(final Runnable newAction) {
		modifiers.onStopModifiers.add(newAction);
	}

	public void addToOnStartModifier(final Runnable newAction) {
		modifiers.onStartModifiers.add(newAction);
	}
	
	public void addToOnDestroyModifier(final Runnable newAction) {
		modifiers.onDestroyModifiers.add(newAction);
	}
	
	public void addToOnRestartModifier(final Runnable newAction) {
		modifiers.onRestartModifiers.add(newAction);
	}
	
	public void addToOnPauseModifier(final Runnable newAction) {
		modifiers.onPauseModifiers.add(newAction);
	}
	
	
	
	public void addToOnMenuButtonModifier(final Runnable newAction) {
		modifiers.onMenuKeyModifiers.add(newAction);
	}
	
	public void addToOnBackButtonModifier(final Runnable newAction) {
		modifiers.onBackKeyModifiers.add(newAction);
	}
	
	public void addToOnVolumeDownButtonModifier(final Runnable newAction) {
		modifiers.onVolumeDownKeyModifiers.add(newAction);
	}
	
	public void addToOnVolumeUpButtonModifier(final Runnable newAction) {
		modifiers.onVolumeUpKeyModifiers.add(newAction);
	}
	
	public void addToOnHomeButtonModifier(final Runnable newAction) {
		modifiers.onHomeKeyModifiers.add(newAction);
	}
	
	
	public void spawnActivityForResult(SpawnActivityForResult spawnActivityInstance) {
		this.spawnActivityInstance = spawnActivityInstance;
		
		int requestCode = spawnActivityInstance.getRequestCode();
		Intent startIntent = spawnActivityInstance.getStartIntent();
		
		activity.startActivityForResult(startIntent, requestCode);
	}
	
	private Runnable flatten(final List<Runnable> runnables) {
		return new Runnable() {
			public void run() {
				for (Runnable r: runnables) {
					r.run();
				}
			}
		};
	}

	public Runnable getOnResumeModifier() {
		return flatten(modifiers.onResumeModifiers);
	}

	public Runnable getOnStopModifier() {
		return flatten(modifiers.onStopModifiers);
	}
	
	public Runnable getOnStartModifier() {
		return flatten(modifiers.onStartModifiers);
	}
	
	public Runnable getOnDestroyModifier() {
		return flatten(modifiers.onStartModifiers);
	}
	
	public Runnable getOnRestartModifier() {
		return flatten(modifiers.onStartModifiers);
	}
	
	public Runnable getOnPauseModifier() {
		return flatten(modifiers.onStartModifiers);
	}
	
	
	public Runnable getOnMenuButtonModifier() {
		return flatten(modifiers.onMenuKeyModifiers);
	}
	
	public Runnable getOnBackButtonModifier() {
		return flatten(modifiers.onBackKeyModifiers);
	}
	
	public Runnable getOnVolumeDownButtonModifier() {
		return flatten(modifiers.onVolumeDownKeyModifiers);
	}
	
	public Runnable getOnVolumeUpButtonModifier() {
		return flatten(modifiers.onVolumeUpKeyModifiers);
	}
	
	public Runnable getOnHomeButtonModifier() {
		return flatten(modifiers.onHomeKeyModifiers);
	}
	
	public SpawnActivityForResult getSpawnActivityInstance() {
		return spawnActivityInstance;
	}
}
