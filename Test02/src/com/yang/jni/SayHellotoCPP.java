package com.yang.jni;

public class SayHellotoCPP {
	static{
		//System.load("/usr/local/lib/main.so");
		System.out.println(System.getProperty("java.library.path")); 
		System.loadLibrary("main.so");
	}
	public SayHellotoCPP(){}
	public native void sayHello(String name) ;
}
