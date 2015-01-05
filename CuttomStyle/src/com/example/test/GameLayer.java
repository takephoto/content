package com.example.test;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.utils.CCFormatter;

import android.util.Log;

public class GameLayer extends CCLayer {
	private static final String TAG = "GameLayer";
	CCSprite hero;

	public GameLayer() {
		// TODO Auto-generated constructor stub
		this.setContentSize(CCDirector.sharedDirector().getWinSize());

		createHero();

		createHero2();
		
		move();

		diedFrames();
	}

	private void move() {
		// TODO Auto-generated method stub
		CCMoveTo moveTo = CCMoveTo.action(2, CGPoint.ccp(300, 200));
		CCScaleTo scaleTo = CCScaleTo.action(2, 0.5f);

		CCMoveBy by = CCMoveBy.action(2, CGPoint.ccp(200, 200));
		CCMoveBy byto = by.reverse();
		// CCMoveTo backTo = (CCMoveTo) moveTo.reverse() ;
		CCSequence seq = CCSequence.actions(by, byto);

		CCRepeatForever rep = CCRepeatForever.action(seq);
		hero.runAction(rep);

		//
	}

	public void createHero() {
		hero = CCSprite.sprite("Image544.png");
		hero.setAnchorPoint(0.5f, 0);
		// hero.setFlipX(true) ;
		// hero.setPosition(100, 200) ;
		this.addChild(hero);
	}

	public void createHero2() {
		CCSprite shero = CCSprite.sprite("Image523.png");
		shero.setAnchorPoint(0.5f, 0);
		shero.setPosition(100, 200);

		this.addChild(shero);
	}

	public void diedFrames() {
		CCSprite heroDied = CCSprite.sprite("Image258.png");
		heroDied.setPosition(100, 100);
		this.addChild(heroDied, 1) ;
		CCAnimation animation = CCAnimation.animation("deied");
		for( int i=1;i<15;i++)
			animation.addFrame(String.format("Image_deied_%02d.png", i));

		CCAnimate action = CCAnimate.action(3, animation, false);
		CCAnimate action_back = action.reverse();

		heroDied.runAction(action);
		//CCAnimate animate = CCAnimate.action(3,animation,false) ;
		//heroDied.runAction(animate) ;
		
		/*
		 * List<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
		 * 
		 * String filename = "Image%.3d.png" ; //CCSpriteFrame frame =
		 * CCSprite.sprite("Image258.png").displayedFrame() ;
		 * 
		 * for(int i = 258; i <= 268; i++){ Log.i(TAG,
		 * String.format("Image%.3d.png", i)) ; CCSpriteFrame frame =
		 * CCSprite.sprite(String.format(filename, i)).displayedFrame() ;
		 * frames.add(frame) ; } CCAnimation animation =
		 * CCAnimation.animation("",0.2f,(ArrayList<CCSpriteFrame>) frames) ;
		 * CCAnimate amimate = CCAnimate.action(animation) ;
		 * heroDied.runAction(amimate) ;
		 */
	}
}
