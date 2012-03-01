package com.eas.android.libraries.rapidjs.plugins.acceleratedCanvas2D;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Queue;

import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewRequests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;

public class CanvasUnifier {

	private Hashtable<String, NativeCanvas> nativeCanvasesByID;
	private Hashtable<String, Bitmap> images;

	private Paint whitePaint;
	private Paint transparentPaint;
	
	private AcceleratedWebViewRequests requests;

	public CanvasUnifier(Hashtable<String, NativeCanvas> nativeCanvasesById, AcceleratedWebViewRequests requests){
		this.nativeCanvasesByID = nativeCanvasesById;
		
		this.requests = requests;

		this.images = new Hashtable<String, Bitmap>();

		whitePaint = new Paint();
		whitePaint.setColor(Color.WHITE);

		transparentPaint = new Paint();
		transparentPaint.setColor(Color.argb(0,255,255,255));
	}

	private void redraw(){
		requests.postInvalidate();
	}

	private Path getPath(String id){

		return nativeCanvasesByID.get(id).path;
	}

	private void setPath(String id, Path path){

		nativeCanvasesByID.get(id).path = path;
	}

	private Queue<Path> getPaths(String id){

		return nativeCanvasesByID.get(id).paths;
	}

	private void pathFinished(String id){
		getPaths(id).add(getPath(id));
		setPath(id, new Path());
	}

	private Canvas getCanvas(String id){
		return nativeCanvasesByID.get(id).canvas;
	}

	private Matrix getMatrix(String id){

		return nativeCanvasesByID.get(id).canvas.getMatrix();
	}

	private void updateMatrix(String id, Matrix matrix){

		nativeCanvasesByID.get(id).canvas.setMatrix(matrix);
	}

	private Paint getFillStyle(String id){
		return nativeCanvasesByID.get(id).fillStyle;
	}

	private Paint getStrokeStyle(String id){
		return nativeCanvasesByID.get(id).strokeStyle;
	}

	private float radsToDegs(float rads){
		return (float) (rads*180/Math.PI);
	}

	///////////////////////////////////////////////////////////////////
	/////////////////////////    REDRAW
	///////////////////////////////////////////////////////////////////


	public void redrawAll(){
		Iterator<NativeCanvas> canvases = nativeCanvasesByID.values().iterator();


		while (canvases.hasNext()){
			NativeCanvas nativeCanvas = canvases.next();
			nativeCanvas.update();
		}
		redraw();
	}

	///////////////////////////////////////////////////////////////////
	/////////////////////////   STATE UPDATES
	///////////////////////////////////////////////////////////////////
	
	public void gotFillStyle(String fillStyle, String id){

		//		System.out.println(fillStyle + " " + id);
		Paint nextFillStyle = new Paint();
		nextFillStyle.setColor(Color.parseColor(fillStyle));
		if (nativeCanvasesByID.containsKey(id))
			nativeCanvasesByID.get(id).fillStyle = nextFillStyle;
	}

	public void gotCanvas (String id, int x, int y, int width, int height){
		nativeCanvasesByID.remove(id);
		nativeCanvasesByID.put(id, new NativeCanvas(x, y, width, height));
	}


	///////////////////////////////////////////////////////////////////
	/////////////////////////    MATRICES
	///////////////////////////////////////////////////////////////////

	public void save(String id){

		getCanvas(id).save();
	}

	public void restore(String id){

		getCanvas(id).restore();
	}

	public void scale(String id, float x, float y){

		Matrix matrix = getMatrix(id);
		matrix.preScale(x, y);
		updateMatrix(id, matrix);
	}

	public void rotate(String id, float rads){

		Matrix matrix = getMatrix(id);
		matrix.preRotate(radsToDegs(rads));
		updateMatrix(id, matrix);
	}

	public void translate(String id, float x, float y){

		Matrix matrix = getMatrix(id);
		matrix.preTranslate(x, y);
		updateMatrix(id, matrix);
	}


	///////////////////////////////////////////////////////////////////
	/////////////////////////    RAW OPS
	///////////////////////////////////////////////////////////////////


	public void clearRect(String id, int x, int y, int w, int h) {

		getCanvas(id).drawRect(x, y, x+w, y+h, transparentPaint);
//		getCanvas(id).drawRect(x, y, x+w, y+h, whitePaint);
	}

	public void fillRect(String id, int x, int y, int w, int h) {

		Canvas canvas = getCanvas(id);
		canvas.drawRect(x, y, x + w, y + h, getFillStyle(id));
	}


	public void strokeRect(String id, int x, int y, int w, int h){

		Canvas canvas = getCanvas(id);
		canvas.drawRect(x, y, x + w, y + h, getStrokeStyle(id));
	}

	public void drawImage(String id, String src, int x, int y){
		if (!images.containsKey(src)){
			Bitmap b = getBitmapFromURL(src);
			images.put(src, b);
		}
		getCanvas(id).drawBitmap(images.get(src), x, y, null);
	}

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	///////////////////////////////////////////////////////////////////
	/////////////////////////    PATHS
	///////////////////////////////////////////////////////////////////

	public void fill(String id) {

		while (getPaths(id).size() > 0){
			Path path = getPaths(id).poll();
			getCanvas(id).drawPath(path, getFillStyle(id));
		}
	}

	public void beginPath(String id) {

		setPath(id, new Path());
	}

	public void moveTo(String id, float x, float y){

		getPath(id).moveTo(x, y);
	}

	public void closePath(String id){

		pathFinished(id);
	}

	public void lineTo(String id, float x, float y){

		getPath(id).lineTo(x, y);
	}

	public void arc(String id, float x, float y, float radius, float start, float end, boolean counterClockwise){

		if (counterClockwise)
			end *= -1;
		float startDeg = radsToDegs(start);
		float endDeg = radsToDegs(end);
		getPath(id).addArc(new RectF(x-radius, y-radius, x+radius,y+radius), startDeg, endDeg);
	}

	public void rect(String id, int x, int y, int w, int h) {

		getPath(id).addRect(x, y, x+w, h+y, Direction.CCW);
	}

}
