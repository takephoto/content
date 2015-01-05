package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		if(intent.getAction() == Intent.ACTION_BOOT_COMPLETED){
			Intent t = new Intent(context,DownLoadService.class) ;
			t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
			context.startService(t) ;
		}
	}

}
