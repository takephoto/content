package com.example.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluedemo.R;


public class MainActivity extends Activity {
	
	private BluetoothAdapter myBlueAdapter = null;
	
	private TextView blueToothInfo = null;
	
	private Spinner deviceNames = null ;
	
	private Switch openBlueTooth = null ;
	
	private ArrayAdapter devAdapter ;
	
	private List<String> allBondDevNames ;
	
	private BlueReceiver receiver = new BlueReceiver();
	
	
	private Button scanButton ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main) ;
		scanButton = (Button) findViewById(R.id.button1) ;
		scanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myBlueAdapter.startDiscovery() ;
			}
		}) ;
		openBlueTooth = (Switch) findViewById(R.id.switch1) ;
		deviceNames = (Spinner) findViewById(R.id.spinner1) ;
		blueToothInfo = (TextView) findViewById(R.id.bluetooth_info) ;
		myBlueAdapter = BluetoothAdapter.getDefaultAdapter() ;
		
		deviceNames.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Set<BluetoothDevice> bondDevices = myBlueAdapter.getBondedDevices() ;
				for(BluetoothDevice device : bondDevices){
					if(allBondDevNames.get(position).equals(device.getName())){
						blueToothInfo.setText(getBlueDeviceAllInfo(device)) ;
					}
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		}) ;
		if(myBlueAdapter.isEnabled()){
			openBlueTooth.setChecked(true) ;
			devAdapter = getSpinnerAdapter() ; 
			deviceNames.setAdapter(devAdapter) ;
		}else{
			openBlueTooth.setChecked(false) ;
		}
		
		openBlueTooth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(myBlueAdapter.isEnabled()){
					myBlueAdapter.disable() ;
				}else{
					myBlueAdapter.enable() ;
					devAdapter = getSpinnerAdapter() ;
					deviceNames.setAdapter(devAdapter) ;
					devAdapter.notifyDataSetChanged() ;
 				}
			}
		}) ;
		blueToothInfo.setText(getBlueDeviceAllInfo(myBlueAdapter)) ;
		this.registerReceiver(receiver, getRecevieFilter()) ;
		new BlueToothServer().start() ;
	}
	
	public ArrayAdapter<String> getSpinnerAdapter(){
		
		Set<BluetoothDevice> bondDevices = myBlueAdapter.getBondedDevices() ;
		
		allBondDevNames = new ArrayList<String>() ;
		for(BluetoothDevice devices : bondDevices){
			allBondDevNames.add(devices.getName()) ;
		}
		
		ArrayAdapter<String> deviceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, allBondDevNames) ;
		return deviceAdapter ;
	}
	public IntentFilter getRecevieFilter(){
		IntentFilter filter = new IntentFilter() ;
		filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED) ;
		filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED) ;
		filter.addAction(BluetoothDevice.ACTION_FOUND) ;
		filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED) ;
		return filter ;
	}
	
	public class BlueReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction() ;
			if(action.equals(BluetoothDevice.ACTION_FOUND)){
				BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) ;
				
				Toast.makeText(context, "**********"+scanDevice.getName()+"state"+scanDevice.getBondState()+" "+BluetoothDevice.BOND_NONE, Toast.LENGTH_LONG).show() ;
				int bindSate = scanDevice.getBondState() ; //
				
				switch(bindSate){
				case BluetoothDevice.BOND_NONE:
					
					try {
						
						Method method = BluetoothDevice.class.getDeclaredMethod("createBond") ;
						Toast.makeText(context, "**********bond", Toast.LENGTH_LONG).show() ;
						method.invoke(scanDevice) ;
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break ;
				case BluetoothDevice.BOND_BONDED:
					Toast.makeText(context, "**********connect", Toast.LENGTH_LONG).show() ;
					connect(scanDevice) ;
					
					break ;
				case BluetoothDevice.BOND_BONDING:
					break ;
				}
				
			}else if(action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)){
				BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) ;
				if(BluetoothDevice.BOND_BONDED == scanDevice.getBondState()){
					connect(scanDevice) ;
				}
			}else if(action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)){
				
			}else if(action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)){
				
			}
		}
		public void connect(BluetoothDevice device){
			UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") ;
			try {
				BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid) ;
				socket.connect() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 
	 */
	public String getBlueDeviceAllInfo(BluetoothAdapter blueAdapter){
		StringBuilder info = new StringBuilder() ;
		info.append("address:") ;
		info.append(blueAdapter.getAddress()+"\n") ;
		info.append("Name:") ;
		info.append(blueAdapter.getName()+"\n") ;
		info.append("scanMode:") ;
		info.append(blueAdapter.getScanMode()+"\n") ;
		info.append("state:") ;
		info.append(blueAdapter.getState()+"\n") ;
		return info.toString() ;	
	}
	
	public String getBlueDeviceAllInfo(BluetoothDevice blueDevice){
		StringBuilder info = new StringBuilder() ;
		info.append("address:") ;
		info.append(blueDevice.getAddress()+"\n") ;
		info.append("Name:") ;
		info.append(blueDevice.getName()+"\n") ;
		info.append("bondSate:") ;
		info.append(blueDevice.getBondState()+"\n") ;
		//info.append("type:") ;
		//info.append(blueDevice.getType()+"\n") ;
		//info.append("uuid") ;
		//info.append(blueDevice.getUuids().length+"\n") ;
		
		return info.toString() ;	
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver) ;
	}
	
	
	public class BlueToothServer extends Thread{
		
		BluetoothServerSocket ss ;
		
		List<MessageDealThread> allCient = new ArrayList<MessageDealThread>() ;
		public BlueToothServer(){
			final String myuuid = "00001101-0000-1000-8000-00805F9B34FB" ;
			UUID uuid = UUID.fromString(myuuid) ;
			try {
				ss = myBlueAdapter.listenUsingRfcommWithServiceRecord("yang", uuid) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				try {
					
					BluetoothSocket s = ss.accept() ;
					
					
					MessageDealThread deal = new MessageDealThread(s) ;
					deal.start() ;
					allCient.add(deal)  ;
					//Toast.makeText(MainActivity.this,"accept", Toast.LENGTH_LONG).show() ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class MessageDealThread extends Thread{
		
		public MessageDealThread(BluetoothSocket socket){
			
		}
		public void run(){
			while(true){
				
			}
		}
	}
}
