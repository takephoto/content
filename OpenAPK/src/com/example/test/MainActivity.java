package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		StartReceiver receiver = new StartReceiver() ;
		IntentFilter filter = new IntentFilter() ;
		filter.addAction(Intent.ACTION_BOOT_COMPLETED) ;
		filter.addCategory(Intent.CATEGORY_DEFAULT) ;
		this.registerReceiver(receiver, filter) ;
		Toast.makeText(this, "YYYYYYYYYYYYYYYYYYYYYYYYYY", Toast.LENGTH_LONG).show() ;
	}
}
