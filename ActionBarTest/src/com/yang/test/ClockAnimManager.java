package com.yang.test;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class ClockAnimManager {
	
	private boolean isRing ;//是否响铃
	
	private ImageView mAlermImage = null ; //闹铃图片视图
	
	private Context context ;
	
	Animation alarmAnim = null; //闹铃动画
	
	MediaPlayer mePlayer = null ; 
	
	int rota = 10; //闹钟左右摆动的角度
	
	
	
	public ClockAnimManager(){
		
	}
	
	public ClockAnimManager(Context context,ImageView mAlermImage){
		this(context,mAlermImage,false,5) ;
	}
	
	public ClockAnimManager(Context context,ImageView mAlermImage,boolean isRing){
		this(context,mAlermImage,isRing,5) ;
	}
	
	public ClockAnimManager(Context context,ImageView mAlermImage,boolean isRing,int rota){
		
		this.context = context ;
		this.mAlermImage = mAlermImage ;
		this.isRing = isRing ;
		this.rota = rota ;
		createAnimation() ;
	}

	
	private void createAnimation(){
		alarmAnim = new RotateAnimation(-rota,rota,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,1.0f) ;
		
		alarmAnim.setRepeatCount(-1) ; //无限重复
		alarmAnim.setRepeatMode(Animation.REVERSE) ; //动画翻转
		alarmAnim.setDuration(200) ; //动画每个周期的时间
	}
	
	public void stopAnimation(){ //开始颤抖
		mAlermImage.clearAnimation() ;
		if(isRing){
			if(mePlayer != null) mePlayer.stop() ;
		}
	}
	
	
	public void startAnimation(){//停止颤抖
		mAlermImage.startAnimation(alarmAnim) ;
		if(isRing){
			Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM) ;
			mePlayer = new MediaPlayer() ;
			try {
				mePlayer.setDataSource(context, uri) ;
				final AudioManager audio = (AudioManager) context.getSystemService(context.AUDIO_SERVICE) ;
				
				if(audio.getStreamVolume(AudioManager.STREAM_ALARM) != 0){
					mePlayer.setAudioStreamType(AudioManager.STREAM_ALARM) ;
					mePlayer.setLooping(true) ;
					mePlayer.prepare() ;
					mePlayer.start() ;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isRing() {
		return isRing;
	}

	public void setRing(boolean isRing) {
		this.isRing = isRing;
	}
	
	
}
