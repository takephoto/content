package com.yang.jni;


public class Demo {
	public static void main(String[] args) {
		SayHellotoCPP saCpp = new SayHellotoCPP() ;
		saCpp.sayHello("qqqq");
		//System.out.println(System.getProperty("java.library.path")); 
		//System.load("/usr/local/lib/main.so");
	}
}
