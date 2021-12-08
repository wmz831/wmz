package com.wmz.multiThread;

/**
 * @Author wangmingzhen
 * @Date 21/12/8 11:04
 */
public class MyThread implements Runnable{

    private String str="test：";

    private void doOut(){
        for(int i=0 ; i<10 ;i++){
            System.out.println(str + i);
        }
    }

    @Override
    public void run() {
        doOut();
    }
}

class MyThreadDemo{

    public static void main(String[] args) {
        for(int i=0 ; i<3 ;i++){
            //类实现Runnable接口，作为Thread构造器的入参传入
            new Thread(new MyThread()).start();
        }

        //Runnable使用函数式接口定义，利用lambda表达式完成
        //Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10 ;i++){
                    System.out.println("run + " + i);
                }
            }
        }).start();

        Runnable run = ()->{
            System.out.println("done");
        };
        new Thread(run).start();
    }
}


