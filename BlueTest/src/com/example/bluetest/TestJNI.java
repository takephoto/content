package com.example.bluetest;

public class TestJNI {
	public native void stringTestNDK() ;
	public native void stringTestNDK2() ;
	static{
		System.loadLibrary("testNDK") ;
	}
}
