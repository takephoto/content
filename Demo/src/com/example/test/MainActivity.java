package com.example.test;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo.R;


public class MainActivity extends Activity {
	int[] imageIds=new int[]{
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9,
    };
    AdapterViewFlipper flipper ;
    
    private MyService myservice ;
    private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			myservice = ((MyService.ServiceBinder)service).getMyService() ;
			myservice.initService(100) ;
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout) ;
		TestJNI test = new TestJNI() ;
		String str = test.stringTestNDK() ;
		
		Toast.makeText(this,""+str,Toast.LENGTH_LONG).show() ; 
		
		flipper=(AdapterViewFlipper)findViewById(R.id.flipper);
        //创建一个BaseAdapter对象，该对象负责提供Gallery所显示的列表项
        BaseAdapter adapter=new BaseAdapter()
        {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return imageIds.length;
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
            //该方法返回的View代表了每个列表项
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                //创建一个ImageView
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setImageResource(imageIds[position]);
                //设置ImageView的缩放类型
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //为ImageView设置布局参数
                imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
                return imageView;
            }
       
        };
        flipper.setAdapter(adapter);
        Intent intent = new Intent(this,MyService.class) ;
        bindService(intent, conn, Context.BIND_AUTO_CREATE) ;
	}
	 public void prev(View source)
	    {
	        //显示上一个组件
	        flipper.showPrevious();
	        //停止自动播放
	        flipper.stopFlipping();
	    }
	    
	    public void next(View source)
	    {
	        //显示一个组件
	        flipper.showNext();
	        //停止自动播放
	        flipper.stopFlipping();
	    }
	    
	    public void auto(View source)
	    {
	        //开始自动播放
	        flipper.startFlipping();
	        
	    } 
}
