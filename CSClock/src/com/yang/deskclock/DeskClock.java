package com.yang.deskclock;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.AlarmClock;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.csclock.R;

public class DeskClock extends FragmentActivity {
	
	private ListView alarmList ;
	
	private Button mNewColock ;
	
	public List<Map<String,String>> allAlarms = new ArrayList<Map<String,String>>() ;
	
	
	public Button mMenuBtn ;
	
	
	public AlarmAdapter alarmAdapter ;
	
	
	public static final int UPDATE = 0 ;
	
	Handler handle = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch(msg.what){
			case UPDATE:
				alarmAdapter.notifyDataSetChanged() ;
				break ;
			}
			
		}
		
	} ;
	
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		setContentView(R.layout.desk_clock) ;
		mMenuBtn = (Button) findViewById(R.id.menu) ;
		mMenuBtn.setVisibility(View.VISIBLE) ;
 		alarmList = (ListView) findViewById(R.id.list_alarm) ;
		mNewColock = (Button) findViewById(R.id.new_clock) ;
		
		final Bundle bundle = arg0 ;
		mNewColock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent t = new Intent(DeskClock.this,AlarmSet.class) ;
				
				
				startActivityForResult(t, 1) ;
				
			}
		}) ;
		Map<String,String> map = new HashMap<String, String>() ;
		
		
		allAlarms.add(map) ;
		
		initAlarmHeadView() ;
		alarmAdapter = new AlarmAdapter() ;
		alarmList.setAdapter(alarmAdapter) ;
		
	}
	
	public void initAlarmHeadView(){
		View v = LayoutInflater.from(this).inflate(R.layout.alarm_head, null) ;
		alarmList.addHeaderView(v) ;
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case 1:
			
			String seltime = arg2.getStringExtra("seltime") ;
			String repdays = arg2.getStringExtra("repdays") ;
			Map<String,String> map = new HashMap<String, String>() ;
			map.put("time", seltime) ;
			map.put("repdays", repdays) ;
			allAlarms.add(map) ;
			alarmAdapter.notifyDataSetChanged() ;
 			break;

		default:
			break;
		}
		super.onActivityResult(arg0, arg1, arg2);
	}
	public class AlarmAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return allAlarms.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return allAlarms.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Map<String, String> map = allAlarms.get(position) ;
			View v = (View)LayoutInflater.from(DeskClock.this).inflate(R.layout.alarm_item, null) ;
			
			
			//CheckBox alarmOn = (CheckBox) v.findViewById(R.id.alarm_on) ; 
			TextView time = (TextView)v.findViewById(R.id.alarm_time) ;
			time.setText(map.get("time")) ;
			TextView repday = (TextView)v.findViewById(R.id.time_distances) ;
			repday.setText(map.get("repdays")) ;
			return v;
		}
		
	}
}
