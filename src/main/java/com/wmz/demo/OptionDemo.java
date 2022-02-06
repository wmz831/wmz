package com.wmz.demo;

import java.util.Optional;

public class OptionDemo {

	public static void main(String[] args){
//		IMessage im = new IMessage();
		IMessage im = null;
		System.out.println(MSGUtil.getStr(im));
	}
}

class MSGUtil{
	public static IMessage getStr(IMessage msg) {

		/**
		 * Optional应该用于底层提供方法的地方，从而保证上层调用无顾虑
		 * of()方法生产Optional类实例，get()方法校验是否为空
		 */
		return Optional.of(msg).get();
	}
}

class IMessage{

	public String getMessage(){
		return "This is IMessage";
	}

}
