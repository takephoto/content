package com.yang.test;

import com.example.actionbartest.R;

import android.R.color;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UINagivationView extends LinearLayout {

	private Button btn_left;

	private Button btn_right;

	private TextView tv_title;
	
	
	private String strBtnLeft ;
	
	private String strBtnRight ;
	
	private String strTitle ;
	private int left_drawable;

	private int right_drawable;

	public UINagivationView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initContent() ;
	}

	public UINagivationView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initContent() ;
		
	}

	public UINagivationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		/*LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.nagv, null);
		btn_left = (Button) findViewById(R.id.btn_left);
		btn_right = (Button) findViewById(R.id.btn_right);
		tv_title = (TextView) findViewById(R.id.tv_title);*/
		initContent();
		initAttributeSet(attrs);
	}

	private void initAttributeSet(AttributeSet attrs) {
		// TODO Auto-generated method stub
		if(attrs != null){
			final int attrids[] = new int[]{R.attr.btn_leftText,R.attr.btn_rightText,R.attr.left_drawable,R.attr.right_drawable} ;
			Context context = getContext() ;
			TypedArray array = context.obtainStyledAttributes(attrs,attrids) ;
			CharSequence s1 = array.getText(0) ;
			CharSequence s2 = array.getText(1) ;
			CharSequence s3 = array.getText(2) ;
			left_drawable = array.getResourceId(3, 0) ;
			right_drawable = array.getResourceId(4, 0) ;
			array.recycle() ;
			if(null != s1){
				strBtnLeft = s1.toString() ;
			}
			
			if(null != s2){
				strBtnRight = s2.toString() ;
			}
			
			if(null != s3){
				strTitle = s3.toString() ;
			}
			
			
		}
	}

	private void initContent() {
		// TODO Auto-generated method stub
		setOrientation(HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);
		setBackgroundColor(color.background_light);
		Context context = getContext();
		btn_left = new Button(context);
		btn_left.setVisibility(View.INVISIBLE);
		if (left_drawable != 0) {
			btn_left.setBackgroundResource(left_drawable);
		} else {
			btn_left.setBackgroundColor(color.background_light) ;
		}
		btn_left.setTextColor(Color.WHITE) ;
		
		if(strBtnLeft != null){
			LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) ;
			param.setMargins(10, 0, 0, 0) ;
			btn_left.setLayoutParams(param) ;
			btn_left.setText(strBtnLeft) ;
			btn_left.setVisibility(View.VISIBLE) ;
		}else{
			btn_left.setLayoutParams(new LayoutParams(50, 50)) ;
		}
		
		addView(btn_left) ;
		
		btn_right = new Button(context) ;
		
 		btn_right.setVisibility(View.INVISIBLE) ;
 		
 		btn_right.setBackgroundColor(color.background_light) ;
 		
 		btn_right.setTextColor(Color.WHITE) ;
 		
 		if(right_drawable != 0){
 			btn_right.setBackgroundResource(right_drawable) ;
 		}else{
 			btn_right.setBackgroundColor(color.background_light) ;
 		}
 		
 		if(strBtnRight != null){
 			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) ;
 			params.setMargins(0, 0, 10, 0) ;
 			btn_right.setLayoutParams(params) ;
 			btn_right.setText(strBtnRight) ;
 		}else{
 			btn_right.setLayoutParams(new LayoutParams(50, 50)) ;
 		}
 		
 		addView(btn_right) ;
 		
	}

	public void setButtonLeftBackground(int resid) {
		if (btn_left != null) {
			btn_left.setBackgroundResource(resid);
		}
	}

	public void setButtonRightBackground(int resid) {
		if (btn_right != null) {
			btn_right.setBackgroundResource(resid);
		}
	}

	public void setTvTitle(int resid) {
		if (tv_title != null) {
			tv_title.setText(resid);
		}
	}

	public Button getBtn_left() {
		return btn_left;
	}

	public Button getBtn_right() {
		return btn_right;
	}

	public TextView getTv_title() {
		return tv_title;
	}

}
