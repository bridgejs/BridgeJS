package org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Modifiers {
	/* Activity Lifecycle */
	public List<Runnable> onResumeModifiers;
	public List<Runnable> onStopModifiers;
	public List<Runnable> onPauseModifiers;
	public List<Runnable> onStartModifiers;
	public List<Runnable> onDestroyModifiers;
	public List<Runnable> onRestartModifiers;
	
	/* Buttons on Phone */
	public List<Runnable> onBackKeyModifiers;
	public List<Runnable> onMenuKeyModifiers;
	public List<Runnable> onVolumeDownKeyModifiers;
	public List<Runnable> onVolumeUpKeyModifiers;
	public List<Runnable> onHomeKeyModifiers;
	
	public ActivityResultCallback onActivityResultModifier;
	
	public Modifiers() {
		this.onResumeModifiers = new ArrayList<Runnable>();
		this.onStopModifiers = new ArrayList<Runnable>();
		this.onPauseModifiers = new ArrayList<Runnable>();
		this.onStartModifiers = new ArrayList<Runnable>();
		this.onDestroyModifiers = new ArrayList<Runnable>();
		this.onRestartModifiers = new ArrayList<Runnable>();
		
		this.onBackKeyModifiers = new ArrayList<Runnable>();
		this.onMenuKeyModifiers = new ArrayList<Runnable>();
		this.onVolumeDownKeyModifiers = new ArrayList<Runnable>();
		this.onVolumeUpKeyModifiers = new ArrayList<Runnable>();
		this.onHomeKeyModifiers = new ArrayList<Runnable>();
		
		this.onActivityResultModifier = null;
	}
}
