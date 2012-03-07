package org.bridgejs.android.phonebridge.library.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class StylizedProgressBarFactory{
	public static ProgressBar build(Context context){
		ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
		
		setColor(progressBar, Color.argb(200, 30, 144, 255));
		
		setLayout(progressBar);
		
		return progressBar;
	}
	
	private static void setColor(ProgressBar progressBar, int color){
		ShapeDrawable progressDrawable = new ShapeDrawable(new RectShape());
		progressDrawable.getPaint().setColor(color);
		ClipDrawable progressClip = new ClipDrawable(progressDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
		progressBar.setProgressDrawable(progressClip);  
	}
	
	private static void setLayout(ProgressBar progressBar){
		FrameLayout.LayoutParams progressBarLayout = 
				new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.FILL_PARENT, 
						FrameLayout.LayoutParams.WRAP_CONTENT);
		progressBarLayout.height = 10;
		progressBar.setLayoutParams(progressBarLayout);
	}
}
