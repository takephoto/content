package com.example.test;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment {
	int mCurCheckPosition = 0 ;
	int mShownCheckPosition = -1 ;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,new String[]{"1","2","3","4"})) ;
		if(savedInstanceState != null){
			mCurCheckPosition = savedInstanceState.getInt("curChoise",0) ;
			mShownCheckPosition = savedInstanceState.getInt("shownChoice",-1) ;
		}
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE) ;
		showDetails(mCurCheckPosition) ;
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition) ;
		outState.putInt("shownChoice", mShownCheckPosition) ; 
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		showDetails(position) ;
	}
	void showDetails(int index){
		mCurCheckPosition = index ;
		getListView().setItemChecked(index, true) ;
		
		if(mShownCheckPosition != mCurCheckPosition){
			
		}
	}
}
