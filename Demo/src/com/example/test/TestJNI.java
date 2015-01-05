package com.example.test;

public class TestJNI {
	public native String stringTestNDK() ;
	public native String stringTestNDK2() ;
	static{
		System.loadLibrary("testNDK") ;
	}
}
