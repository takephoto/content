package com.example.yang;

import org.cocos2d.actions.CCActionManager;
import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCRotateTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemLabel;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPointSize;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;


import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {
	private CCGLSurfaceView view;
	Bitmap bitmap = null ;
	static Resources res  ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = getResources() ;
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN) ;
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) ;
		
		view = new CCGLSurfaceView(this) ;
		CCDirector direct = CCDirector.sharedDirector() ;
		direct.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft) ;
		direct.attachInView(view) ;
		setContentView(view) ;
		
		CCDirector.sharedDirector().setDisplayFPS(true) ;
		CCDirector.sharedDirector().setAnimationInterval(1.0f/30) ;
		CCScene scene = CCScene.node() ;
		/*CCLayer layer1 = CCLayer.node() ;
		layer1.setContentSize(300, 200) ;
		layer1.setPosition(10, 10) ;*/
		
		CCColorLayer colore1 = CCColorLayer.node(ccColor4B.ccc4(0, 0, 0, 255), CCDirector.sharedDirector().displaySize().width, CCDirector.sharedDirector().displaySize().height);
		colore1.setPosition(0, 0) ;
		@SuppressWarnings("deprecation")
		CCSprite sprite1 = CCSprite.sprite(BitmapFactory.decodeResource(getResources(), com.example.gametest.R.drawable.ic_launcher)) ;
		sprite1.setAnchorPoint(0, 0) ;
		sprite1.setPosition(100,100) ;
		CCRotateTo actionTo = CCRotateTo.action(2, 45);
		CCRotateTo actionTo2 = CCRotateTo.action(2, -45);
		CCRotateTo actionTo0 = CCRotateTo.action(2, 0);
		colore1.addChild(sprite1) ;
		sprite1.runAction(CCSequence.actions(actionTo, actionTo0)) ;
		scene.addChild(colore1) ;
		/*CCLabel label = CCLabel.makeLabel("this is c program", "宋体", 30) ;
		label.setColor(ccColor3B.ccBLACK) ;
		label.setPosition(200, 200) ;
		scene.addChild(label) ;*/
		
		scene.addChild(new Layer2()) ;
		CCScene scene2 = CCScene.node() ;
		CCColorLayer colore2 = CCColorLayer.node(ccColor4B.ccc4(0, 0, 0, 255), CCDirector.sharedDirector().displaySize().width, CCDirector.sharedDirector().displaySize().height);
		colore2.setPosition(0, 0) ;
		scene2.addChild(colore2) ;
		
		CCDirector.sharedDirector().pushScene(scene2) ;
		CCDirector.sharedDirector().runWithScene(scene) ;
		
	}
	static class Layer2 extends CCLayer{
		public Layer2(){
			CCMenuItemLabel item1 = CCMenuItemLabel.item("单人游戏", this, "onePersonHouse") ;
			CCMenuItemLabel item2 = CCMenuItemLabel.item("多人游戏", this, "manyPersonHouse") ;
			CCMenuItemLabel item3 = CCMenuItemLabel.item("退出游戏", this, "exitGame") ;
			CCMenu menu = CCMenu.menu(item1,item2,item3) ;
			
			menu.alignItemsVertically() ;
			//menu.alignItemsHorizontally() ;
			
			addChild(menu) ;

		}
		
		public void onePersonHouse(Object sender){
			CCScene waitScene = CCScene.node() ;
			waitScene.addChild(new GameLayer()) ;
			CCDirector.sharedDirector().pushScene(waitScene) ;
		}
		
		public void manyPersonHouse(Object sender){
			
			CCScene waitScene = CCScene.node() ;
			waitScene.addChild(new WaitHouse()) ;
			CCDirector.sharedDirector().pushScene(waitScene) ;
		}
		
		public void exitGame(Object sender){
			System.exit(0) ;
		}
	}
	static class WaitHouse extends CCLayer{
		public WaitHouse(){
			CCColorLayer background = CCColorLayer.node(ccColor4B.ccc4(0, 0, 0, 255)) ;
			background.setContentSize(CCDirector.sharedDirector().winSize()) ;
			CCColorLayer playColor1 = CCColorLayer.node(ccColor4B.ccc4(255, 0, 0, 255)) ;
			playColor1.setPosition(CGPoint.ccp(30, 200)) ;
			playColor1.setContentSize(CGSize.make(100, 100)) ;
			background.addChild(playColor1) ;
			CCColorLayer playColor2 = CCColorLayer.node(ccColor4B.ccc4(255, 0, 0, 255)) ;
			playColor2.setPosition(CGPoint.ccp(130, 100)) ;
			playColor2.setContentSize(CGSize.make(100, 100)) ;
			background.addChild(playColor2) ;
			CCColorLayer playColor3 = CCColorLayer.node(ccColor4B.ccc4(255, 0, 0, 255)) ;
			playColor3.setPosition(CGPoint.ccp(230, 200)) ;
			playColor3.setContentSize(CGSize.make(100, 100)) ;
			background.addChild(playColor3) ;
			CCColorLayer playColor4 = CCColorLayer.node(ccColor4B.ccc4(255, 0, 0, 255)) ;
			
			playColor4.setPosition(CGPoint.ccp(130, 300)) ;
			playColor4.setContentSize(CGSize.make(100, 100)) ;
			background.addChild(playColor4) ;
			CCMenuItemLabel item1 = CCMenuItemLabel.item("准备", this, "readyGame") ;
			CCMenuItemLabel item2 = CCMenuItemLabel.item("返回", this, "backToUp") ;
			CCMenu menu = CCMenu.menu(item1,item2) ;
			menu.alignItemsVertically() ;
			menu.setPosition(CGPoint.ccp(CCDirector.sharedDirector().winSize().width - 200, 70)) ;
			background.addChild(menu) ; 
			
			
			this.addChild(background) ;
			
			
 			this.setPosition(0, 0) ;
		}
		
		public void readyGame(Object sender){
			CCMenuItemLabel label = (CCMenuItemLabel) sender ;
			label.setString("Start Game") ;
			CCScene scene3 = CCScene.node() ;
			scene3.addChild(new GameLayer()) ;
			CCDirector.sharedDirector().pushScene(scene3) ;
		}
		public void backToUp(Object sender){
			CCDirector.sharedDirector().popScene() ;
		}
	}
	
	static class GameLayer extends CCLayer{
		public GameLayer(){
			CCColorLayer background = CCColorLayer.node(ccColor4B.ccc4(0, 0, 0, 255)) ;
			background.setContentSize(CCDirector.sharedDirector().winSize()) ;
			CCColorLayer qipanBG = CCColorLayer.node(ccColor4B.ccc4(255, 255, 255, 255)) ;
			qipanBG.setContentSize(CGSize.make(400, 400)) ;
			qipanBG.setPosition(CGPoint.ccp((CCDirector.sharedDirector().winSize().width - 400)/2, (CCDirector.sharedDirector().winSize().height - 400)/2)) ;
			background.addChild(qipanBG) ;
			CCMenuItemLabel item1 = CCMenuItemLabel.item("返回", this, "backToUp") ;
			CCMenu menu = CCMenu.menu(item1) ;
			menu.setPosition(CGPoint.ccp(CCDirector.sharedDirector().winSize().width - 100, 70)) ;
			background.addChild(menu) ;
			this.addChild(background) ;
		}
		public void backToUp(Object sender){
			CCDirector.sharedDirector().popScene() ;
		}
	}
}
