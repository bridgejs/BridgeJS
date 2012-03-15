package org.bridgejs.android.phonebridge.library.browser;

import java.util.LinkedList;

import android.util.Pair;

public class WebHistoryStack {
	private LinkedList<WebContent> historyList;
	private int maxSize;
	private WebContent currentlyViewing;
	private boolean hasFirstPopSincePushHappened;
	
	public WebHistoryStack(int maxSize) {
		this.maxSize = maxSize;
		historyList = new LinkedList<WebContent>();
		currentlyViewing = null;
		this.hasFirstPopSincePushHappened = true;
	}
	
	private void evictOldest() {
		historyList.removeFirst();
	}
	
	public synchronized void push(WebContent content) {
		if (historyList.size() >= maxSize) {
			evictOldest();
		}
		addLastSite();
		currentlyViewing = content;
		hasFirstPopSincePushHappened = false;
	}
	
	private void addLastSite() {
		if (currentlyViewing != null) {
			historyList.add(currentlyViewing);
		}
	}
	
	public synchronized boolean doesHistoryExist() {
		return historyList.size() > 0;
	}
	
	public synchronized WebContent pop() {
		if (doesHistoryExist()) {
			WebContent lastVisited = historyList.removeLast();
			currentlyViewing = lastVisited;
			return lastVisited;
		}
		else
			return null;
	}
}
