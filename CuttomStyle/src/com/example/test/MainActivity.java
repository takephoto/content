package com.example.test;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {
	CCDirector director ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) ;
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		CCGLSurfaceView gl = new CCGLSurfaceView(this) ;
		setContentView(gl) ;
		director = CCDirector.sharedDirector() ;
		
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft) ;
		
		director.attachInView(gl) ;
		CCScene sc = createSences() ;
		
		director.setAnimationInterval(1.0f/60) ;
		director.setDisplayFPS(true) ;
		director.pushScene(sc) ;
		director.runWithScene(sc) ;
		
	}
	
	
	public CCScene createSences(){
		CCScene sc = CCScene.node() ;
		GameLayer gamely = new GameLayer() ;
		sc.addChild(gamely) ;
		/*GameMap map = new GameMap() ;
		sc.addChild(map) ;*/
		return sc ;
	}
}
