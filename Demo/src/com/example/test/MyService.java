package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
	private IBinder mbind = new ServiceBinder()  ;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mbind;
	}
	public void initService(int index){
		System.out.println("index = "+index) ;
	}
	public class ServiceBinder extends Binder{
		MyService getMyService(){
			return MyService.this ;
		}
	}
}
