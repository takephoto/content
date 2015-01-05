package com.yang.test;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.appwidget.R;


public class MyAppWeigate extends AppWidgetProvider {
	
	public static boolean isOpen = false ;
	
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.appwidgetlayout)  ;
		Intent intent = new Intent(context,MyAppWeigate.class) ;
		intent.setAction("com.action.btnclick") ;
		PendingIntent pendIntent = PendingIntent.getBroadcast(context, 0, intent, 0) ;
		remoteView.setOnClickPendingIntent(R.id.wton, pendIntent) ;
		
		for(int i = 0; i < appWidgetIds.length ; i++){
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteView) ;
		}
	}
	
	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}
	
	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		/*if(intent.getStringExtra("update").equals("update")){
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context); 
			RemoteViews remoteViews  = new RemoteViews(context.getPackageName(),R.layout.appwidgetlayout);
			ComponentName componentName = new ComponentName(context,MyAppWeigate.class); 
            //
            //更新appwidget 
			if(isOpen){
				remoteViews.setImageViewResource(R.id.wton, R.drawable.image523)  ;
			}else{
				isOpen = true ;
				remoteViews.setImageViewResource(R.id.wton, R.drawable.image544) ;
			}
            appWidgetManager.updateAppWidget(componentName, remoteViews);
            return ;
		}*/
		if(intent.getAction().equals("com.action.btnclick")){
			Log.w("appppppp", "qqqqqqqqqqqqqqqqqqqq") ;
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context); 
			RemoteViews remoteViews  = new RemoteViews(context.getPackageName(),R.layout.appwidgetlayout);
			remoteViews.setTextViewText(R.id.text01, "sssssssssss") ;
			
			
			if(isOpen){
				Intent t = new Intent(context,DialogActivity.class)  ;
				t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
				context.startActivity(t) ;
				//isOpen = false ;
				//remoteViews.setImageViewResource(R.id.wton, R.drawable.image544) ;
			}else{
				isOpen = true ;
				remoteViews.setImageViewResource(R.id.wton, R.drawable.image544) ;
			}
			
			//remoteViews.setImageViewResource(R.id.wton, R.drawable.image523) ;
			//
            //相当于获得所有本程序创建的appwidget 
            ComponentName componentName = new ComponentName(context,MyAppWeigate.class); 
            //
            //更新appwidget 
            appWidgetManager.updateAppWidget(componentName, remoteViews);
		}
		if(intent.getAction().equals("com.action.btnclick1")){
			Log.w("appppppp", "qqqqqqqqqqqqqqqqqqqq") ;
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context); 
			RemoteViews remoteViews  = new RemoteViews(context.getPackageName(),R.layout.appwidgetlayout);
			remoteViews.setTextViewText(R.id.text01, "sssssssssss") ;
			//remoteViews.setImageViewResource(R.id.wton, R.drawable.image523) ;
			//
            //相当于获得所有本程序创建的appwidget 
            ComponentName componentName = new ComponentName(context,MyAppWeigate.class); 
            if(isOpen){
				remoteViews.setImageViewResource(R.id.wton, R.drawable.image544) ;
			}else{
				remoteViews.setImageViewResource(R.id.wton, R.drawable.image523) ;
			}
            //
            //更新appwidget 
            appWidgetManager.updateAppWidget(componentName, remoteViews);
		}
	}
	
}
