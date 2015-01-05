package com.yang.test;

import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.appwidget.R;

public class MainActivity extends Activity {
	private Spinner mSpinner ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main) ;
		mSpinner = (Spinner) findViewById(R.id.spinner1) ;
		mSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"13227975035","13571755191"})) ;
		Resources res = getResources() ;
		Configuration config = res.getConfiguration() ;
		config.locale = Locale.US ;
		DisplayMetrics dm = res.getDisplayMetrics() ;
		res.updateConfiguration(config, dm) ;
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		}) ;
	}
}
