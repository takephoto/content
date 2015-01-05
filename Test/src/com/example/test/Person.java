package com.example.test;

import org.w3c.dom.ls.LSInput;

public class Person {
	ClickListener listener ;
	private String name ;
	
	public int age ;
	
	public void say(String name){
		System.out.println("Hello ------"+name) ;
	}
	
	public void move(){
		listener.click() ;
		System.out.println("move -------") ;
	}
	public void setListener(ClickListener lt){
		listener = lt ;
	}
	
	public interface ClickListener{
		void click() ;
	}
}
