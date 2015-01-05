package com.yang.test;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DialogActivity extends  Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		WindowManager.LayoutParams windowLP = getWindow().getAttributes();
        windowLP.alpha = 0.0f;
        getWindow().setAttributes(windowLP);
		Dialog dialog = new AlertDialog.Builder(this)
		.setMessage("ssssssssss")
		.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				MyAppWeigate.isOpen = false ;
				Intent t = new Intent(DialogActivity.this,MyAppWeigate.class) ;
				t.setAction("com.action.btnclick1") ;
				//t.putExtra("update","update") ;
				DialogActivity.this.sendBroadcast(t) ;
				finish() ;
			}
		}) 
		.setNeutralButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish() ;
			}
		})
		.create() ;
		dialog.show() ;
		
		
		/*dialog.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				finish() ;
			}
		}) ;
		
		dialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				// TODO Auto-generated method stub
				finish() ;
			}
		}) ;*/
	}
}
