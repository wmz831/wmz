package com.wmz.demo;

public class ThreadLocalDemo {

	public static void main(String[] args){
		new Thread(() -> {
			Message a = new Message();
			a.setMsg("A消息");
			Channel.setMessage(a);
			Channel.send();
		},"线程A").start();
		new Thread(() -> {
			Message a = new Message();
			a.setMsg("B消息");
			Channel.setMessage(a);
			Channel.send();
		},"线程B").start();
		new Thread(() ->{
			Message a = new Message();
			a.setMsg("C消息");
			Channel.setMessage(a);
			Channel.send();
		},"线程C").start();
	}
}

class Channel{
	/**
	 * ThreadLocal一般很少允许重新实例化，因为容易引发多线程空指针，所以直接用final修饰
	 * ThreadLocal结构为（线程对象，数据对象），所以可以避免串流
	 */
	private final static ThreadLocal<Message> local= new ThreadLocal<Message>();
	private Channel(){}
	public static void setMessage(Message msg){
		local.set(msg);
	}
	public static void send(){
		System.err.println(Thread.currentThread().getName() +" 发送信息："+ local.get().getMsg());
	}

}
class Message{
	private String msg;
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
}