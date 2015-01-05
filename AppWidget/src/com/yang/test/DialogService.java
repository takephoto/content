package com.yang.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DialogService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Dialog dialog = new AlertDialog.Builder(this)
		
		.setMessage("sssssss")
		.create() ;
		dialog.show() ;
		return null;
	}

}
