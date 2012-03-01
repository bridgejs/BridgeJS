package com.eas.android.libraries.rapidjs.plugins.acceleratedCanvas2D;

import java.util.LinkedList;
import java.util.Queue;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class NativeCanvas {
	public Bitmap bitmap;
	private Canvas bmCanvas;
	public Bitmap nextBitmap;
	public Canvas canvas;
	public Paint fillStyle;
	public Paint strokeStyle;

	public Path path;

	public int x;
	public int y;
	
	public void update(){
		bmCanvas.drawBitmap(nextBitmap, new Matrix(), null);
	}

	public Queue<Path> paths;

	public NativeCanvas(int x, int y, int width, int height) {
		this.nextBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		this.canvas = new Canvas(this.nextBitmap);

		this.x = x;
		this.y = y;
		
		this.bitmap = Bitmap.createBitmap(nextBitmap);
		this.bmCanvas = new Canvas(this.bitmap);


		path = new Path();
		paths = new LinkedList<Path>();

		fillStyle = new Paint();
		strokeStyle = new Paint();
	}
}
