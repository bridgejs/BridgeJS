package com.eas.android.libraries.rapidjs.pluginmanager;

import java.util.ArrayList;
import java.util.List;

/* I think this is a pretty good solution to the problem of adding code to the main activity */
public class ActivityEventsModifier {

	private List<Runnable> onResumeModifiers;
	private List<Runnable> onStopModifiers;
	private List<Runnable> onPauseModifiers;
	//TODO: Etc...

	public ActivityEventsModifier() {
		onResumeModifiers = new ArrayList<Runnable>();
		onStopModifiers = new ArrayList<Runnable>();
		onPauseModifiers = new ArrayList<Runnable>();
	}

	public void addToOnResumeModifier(final Runnable newAction) {
		onResumeModifiers.add(newAction);
	}

	public void addToOnStopModifier(final Runnable newAction) {
		onStopModifiers.add(newAction);
	}

	public void addToOnPauseModifier(final Runnable newAction) {
		onPauseModifiers.add(newAction);
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
		return flatten(onResumeModifiers);
	}

	public Runnable getOnStopModifier() {
		return flatten(onStopModifiers);
	}
}
