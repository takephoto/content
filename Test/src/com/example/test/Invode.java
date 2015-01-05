package com.example.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.plaf.SliderUI;

public class Invode {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InterruptedException {
		/*Class<?> cl = Class.forName("com.example.test.Person") ;
		Field f[] = cl.getFields() ;
		Object
		for(Field ff : f){
			System.out.println(ff.getName()) ;
		}*/
		/*Object obj = (Object)cl.newInstance() ;
		Class<?> param[] = new Class<?>[1] ;
		param[0] = "11111".getClass() ;
		Method m = (Method) cl.getMethod("say",param) ;
		m.invoke(obj, "qqqqqq") ;*/
		
		//m.invoke(obj, "qqqqqqq") ;
		/*Method m[] = cl.getMethods() ;
		for(Method mt : m){
			System.out.println(mt.getName()) ;
		}
		Object obj = (Object)cl.newInstance() ;
		m[0].invoke(obj, "ssss") ;*/
		Person p = new Person() ;
		Man man = new Man() ;
		p.setListener(man) ;
		Thread.sleep(2000) ;
		
		man.click() ;
		
		while(true){
			Thread.sleep(1000) ;
		}
	}
}
