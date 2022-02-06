package com.wmz.demo;

class StrDemo{

	public static void main(String[] args){
		/**
		 * Java中对象常量池分为两种：
		 * 静态常量池：程序（*.class）在加载的时候会自动将程序中保存的字符串、常量、类和方法的信息等，全部进行分配
		 * 运行时常量池：程序（*.class）加载之后
		 */
		String a = "wmz";
		String b = "w" + "m" + "z";
		System.out.println("String test1 : "+ (a==b));//true

		String c = "wmzwmz";//a、b、c都在静态常量池中
		String d = "wmz" + b;//程序在加载时，不能确定 b 的内容，所以 d 放入运行时常量池中
		System.out.println("String test2 : "+ (c==d));//false
	}

}
