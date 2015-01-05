package com.yang.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.http.RequestManager;
import com.android.http.RequestManager.RequestListener;
import com.android.volley.RequestQueue;
import com.example.actionbartest.R;

public class ActionBarTest extends FragmentActivity {
	RequestQueue quene ;
	
	Button mGetData ;
	
	EditText mShowData ;
	
	TextView mIP ;
	TextView mCountry ;
	Animation ainim ;
	ImageView clock ;
	ClockAnimManager mClockAnim ;
	
	ViewPager viewPager ;
	Bundle mRingtoneTitleCache ;
	FragmentAdapter adapter ;
	
	
	TextView mName ;
	TextView mSex ;
	TextView mAddress ;
	List<Map<String,Object>>  gridDatas = new ArrayList<Map<String,Object>>() ;
	
	public ProgressDialog mProgress ;
	private static final int REQUEST_CODE_RINGTONE = 1;
	private static final String KEY_RINGTONE_TITLE_CACHE = "ringtoneTitleCache" ; 
	static final String url = "http://apistore.baidu.com/microservice/iplookup?ip=117.89.35.58" ;
	
	static final String baseIDCardURL = "http://apistore.baidu.com/microservice/icardinfo?id=" ;
	
	String mIdCard = "" ;
	
	GridView mGridView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main) ;
		mGridView = (GridView) findViewById(R.id.gridView) ;
		mGridView.setAdapter(new MyGridAdapter()) ;
		/*getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.huise)) ;
		;
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO) ;
		getActionBar().setTitle("") ;
		//getActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back)) ;
		View v = LayoutInflater.from(this).inflate(R.layout.nagv, new LinearLayout(this),false) ;
		getActionBar().setCustomView(v) ;*/
		//mRingtoneTitleCache = savedInstanceState.getBundle(KEY_RINGTONE_TITLE_CACHE) ;
		/*ActionBar bar = (ActionBar)findViewById(R.id.actionBar) ;
		bar.setType(Type.Normal) ;
		bar.setTitle("测试一下actionbar") ;*/
		
		/*viewPager = (ViewPager) findViewById(R.id.viewpager) ;
		
		
		List<Fragment> fragments = new ArrayList<Fragment>() ;
		fragments.add(new FragmentOne()) ;
		fragments.add(new FragmentTwo()) ;
		adapter = new FragmentAdapter(getSupportFragmentManager(),fragments) ;
		viewPager.setAdapter(adapter) ;
		viewPager.setCurrentItem(0) ;*/
		 mName = (TextView) findViewById(R.id.name) ;
		mSex = (TextView) findViewById(R.id.sex) ;
		mAddress = (TextView) findViewById(R.id.address) ;
		Button btn = (Button) findViewById(R.id.button) ;
		final Bundle bundle = savedInstanceState ;
		mProgress = new ProgressDialog(this) ;
		mProgress.setMessage("loading......") ;
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent t = new Intent(ActionBarTest.this,RepeatClock.class) ;
				
				//startActivity(t) ;
				
				//getFragmentManager().putFragment(bundle, "oneFragment", new TestFragment()) ;
				//launchRingTonePicker(new Alarm()) ;
				mProgress.show() ;
				RequestManager.getInstance().init(getApplicationContext()) ;
				
				RequestManager.getInstance().get("http://apistore.baidu.com/microservice/icardinfo?id=610321199101061113", new RequestListener() {
					
					@Override
					public void onSuccess(String response, String url, int actionId) {
						// TODO Auto-generated method stub
						mProgress.cancel() ;
						try {
							JSONObject obj = new JSONObject(response) ;
							
							JSONObject user = obj.getJSONObject("retData") ;
							
							mSex.setText(user.getString("sex")) ;
							mName.setText(user.getString("birthday")) ;
							mAddress.setText(user.getString("address"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show() ;
					}
					
					@Override
					public void onRequest() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onError(String errorMsg, String url, int actionId) {
						// TODO Auto-generated method stub
						
					}
				}, 1) ;
			}
		}) ;
	}
	
	public void initGridData(){
		Map<String,Object> mMap = new HashMap<String, Object>() ;
		mMap.put("resource", R.drawable.mail) ;
		mMap.put("title", "短信") ;
		gridDatas.add(mMap) ;
	}
	
	public class MyGridAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return gridDatas.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return gridDatas.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Map<String,Object> mm = gridDatas.get(position) ;
			View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.grid_item, null) ;
			ViewHolder vh = new ViewHolder() ;
			vh.mBackground = (Button)v.findViewById(R.id.cell_icon) ;
			vh.title = (TextView)v.findViewById(R.id.cell_title) ;
			//v.setTag(0, vh) ;
			Object res =  mm.get("resource") ;
			String title = (String) mm.get("title") ;
			if(position == 0){
				vh.mBackground.setBackgroundResource((Integer) res) ;
				vh.title.setText(mm.get("title")) ;
			}
			return v;
		}
		
		class ViewHolder{
			TextView title ;
			Button mBackground; 
		}
	}
	
	
	private void launchRingTonePicker(Alarm alarm) {
        //mSelectedAlarm = alarm;
       // Log.i("ringdow", "2222222222222========="+alarm);

        Uri oldRingtone = alarm.alert ;
        final Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, oldRingtone);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, false);
        startActivityForResult(intent, 0);
    }
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 0:
                    saveRingtoneUri(data);
                    break;
                default:
                   ;
            }
        }
    }
	private String getRingToneTitle(Uri uri) {
        // Try the cache first
        String title = null ;
        if (title == null) {
            // This is slow because a media player is created during Ringtone object creation.
            Ringtone ringTone = RingtoneManager.getRingtone(this, uri);
            title = ringTone.getTitle(this);
            Toast.makeText(this, title, Toast.LENGTH_LONG).show() ;
            if (title != null) {
                //mRingtoneTitleCache.putString(uri.toString(), title);
            }
        }
        return title;
    }
	private void saveRingtoneUri(Intent intent) {
        Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
        if (uri == null) {
           // uri = Alarm.NO_RINGTONE_URI;
        }
        
        Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show() ;
        getRingToneTitle(uri) ;
        /*mSelectedAlarm.alert = uri;

        // Save the last selected ringtone as the default for new alarms
        if (!Alarm.NO_RINGTONE_URI.equals(uri)) {
            ///M: Don't set the ringtone to the system, just to the preference @{
            //RingtoneManager.setActualDefaultRingtoneUri(
            //        getActivity(), RingtoneManager.TYPE_ALARM, uri);
            setDefaultRingtone(uri.toString());
            ///@}
            Log.v("saveRingtoneUri = " + uri.toString());
        }
        asyncUpdateAlarm(mSelectedAlarm, false);*/
    }
}
