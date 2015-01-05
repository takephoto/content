package com.example.test;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;

import android.util.Log;

public class GameMap extends CCLayer {
	private static final String TAG = "GameMap";


	public GameMap() {
		// TODO Auto-generated constructor stub
		LoadMap() ;
		//
		//diedFrames() ;
	}
	
	public void LoadMap() {
		CCTMXTiledMap map = CCTMXTiledMap.tiledMap("oox.tmx") ;
		
		this.addChild(map,0) ;
	}
	
	
	public void diedFrames(){
		CCSprite heroDied = CCSprite.sprite("Image258.png") ;
		heroDied.setPosition(100, 100) ;
		this.addChild(heroDied,1) ;
		/*List<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
		
		String filename = "Image%d.png" ;
		//CCSpriteFrame frame = CCSprite.sprite("Image258.png").displayedFrame() ;
		
		for(int i = 258; i <= 268; i++){
			Log.i(TAG, String.format("Image%.3d.png", i)) ;
			CCSpriteFrame frame = CCSprite.sprite(String.format(filename, i)).displayedFrame() ;
			frames.add(frame) ;
		}
		CCAnimation animation = CCAnimation.animation("",0.2f,(ArrayList<CCSpriteFrame>) frames) ;
		CCAnimate amimate = CCAnimate.action(animation) ;
		heroDied.runAction(amimate) ;*/
	}
}
