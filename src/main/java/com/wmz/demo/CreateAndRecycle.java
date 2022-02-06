package com.wmz.demo;

/**
 * 对象的创建和回收过程
 */
public class CreateAndRecycle {

	public void test1(){
		/**
		 * 普通代码块，作用是对一方法进行拆分，避免相同名称的变量互相影响
		 */
		{
			int i=10;
		}
		int i=100;
	}

	public CreateAndRecycle() {
		System.out.println("构造方法执行，Test 诞生");
	}

	/**
	 * 构造块，执行顺序优先于构造方法
	 */
	{
		System.out.println("构造块执行");
	}
	/**
	 * 静态代码块，优先于构造代码块，且只会执行一次，用于初始化静态数据
	 */
	static {
		System.out.println("静态代码块执行");
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("回收方法执行, Test 回收");
//		super.finalize();
	}

}

class TestRun{

	/**
	 * 静态代码块执行
	 * 构造块执行
	 * 构造方法执行，Test 诞生
	 * 构造块执行
	 * 构造方法执行，Test 诞生
	 * 回收方法执行, Test 回收
	 */
	public static void main(String[] args){
		CreateAndRecycle test = new CreateAndRecycle();
		test = null;
		CreateAndRecycle test2 = new CreateAndRecycle();
		System.gc();
	}

}
