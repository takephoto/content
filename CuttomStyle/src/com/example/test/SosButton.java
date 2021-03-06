package com.example.test;

import android.graphics.RectF;
import android.view.MotionEvent;

public class SosButton {
	
	public Listener listener ;
	
	
	public RectF frame ;
	
	private Boolean isClick = false ;
	
	public SosButton(RectF r){
		this.frame = r ;
	}
	
	
	public RectF getFrame() {
		return frame;
	}


	public Boolean getIsClick() {
		return isClick;
	}


	public void touch(MotionEvent event){
		
		if(listener != null){
			if(frame.contains(event.getX(), event.getY())) {
				isClick = true ;
				listener.click() ;
			}
			isClick = false ;
		}
	}
	
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}


	public interface Listener{
		public void click() ;
	}
}
