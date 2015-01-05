package com.yang.test;



import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.actionbartest.R;


public class RepeatClock extends Activity{
	
	
	
	private final int[] DAY_ORDER = new int[] {
            Calendar.SUNDAY,
            Calendar.MONDAY,
            Calendar.TUESDAY,
            Calendar.WEDNESDAY,
            Calendar.THURSDAY,
            Calendar.FRIDAY,
            Calendar.SATURDAY,
    };
	private String[] mShortWeekDayStrings ;
	
	private RepeatAdapter listAdapter ;
	
	private ListView mListView ;
	
	private List<String> selectDayIds = new ArrayList<String>() ;
	private boolean isAllSelect = false ;
	CheckBox allSelect ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().hide() ;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repeat_clock);
		DateFormatSymbols dfs = new DateFormatSymbols();
        mShortWeekDayStrings = dfs.getShortWeekdays();
        
        mListView = (ListView)findViewById(R.id.listviewrepeat) ;
       
        listAdapter = new RepeatAdapter() ; 
        
        mListView.setAdapter(listAdapter);
        
        allSelect = (CheckBox)findViewById(R.id.all_select_repeat) ;
        
        
        allSelect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				listAdapter.notifyDataSetChanged() ; 
			}
		}) ;
        TextView alerttSaves = (TextView) findViewById(R.id.alarm_save);
		alerttSaves.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Intent intents=new Intent(RepeatClock.this,AlarmClock.class);
				StringBuffer reapDay = new StringBuffer() ;
				
				for(int i = 0; i < selectDayIds.size(); i++){
					reapDay.append(selectDayIds.get(i)) ;
				}
				Log.d("whell", reapDay.toString()) ;
				//intents.putExtra("reapDay", reapDay.toString()) ;
                //startActivity(intents); 
			
			}
		});
		TextView deletes = (TextView) findViewById(R.id.alarm_delete);
		deletes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});

	}
	
	public class RepeatAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return DAY_ORDER.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;  
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.repeat_list_item, null) ;
			
			/*if(convertView == null){
				convertView = v ;
			}*/
			
			TextView weekDay = (TextView)convertView.findViewById(R.id.weekday) ;
			
			weekDay.setText(mShortWeekDayStrings[DAY_ORDER[position]]) ;
		
			final CheckBox checkDay = (CheckBox)convertView.findViewById(R.id.rep_day_sel) ;
			
			if(allSelect.isChecked()){
				checkDay.setChecked(true) ;
				//checkDay.setBackgroundResource(R.drawable.selected) ;
				selectDayIds.add(mShortWeekDayStrings[DAY_ORDER[position]]) ;
				Log.d("week",DAY_ORDER[position]+"") ;
			}else{
				checkDay.setChecked(false) ;
				//checkDay.setBackgroundResource(R.drawable.select) ;
				if(selectDayIds.size() != 0 ){
					selectDayIds.clear() ;
				}
			}
			final int po = position ;
			checkDay.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						selectDayIds.add(mShortWeekDayStrings[DAY_ORDER[po]]) ;
						
					}else{
						if(selectDayIds.contains(mShortWeekDayStrings[DAY_ORDER[po]])){
							selectDayIds.remove(mShortWeekDayStrings[DAY_ORDER[po]]) ;
						}
					}
					
				}
			}) ;
			
			Log.d("postion ",""+position) ;
			return convertView;
		}
	}
}