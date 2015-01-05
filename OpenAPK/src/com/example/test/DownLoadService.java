package com.example.test;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class DownLoadService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run(){
				while(true){
					System.out.println("***********************") ;
				}
			}
		}.start() ;
		return null;
	}

}
