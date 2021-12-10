package com.wmz.multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author wangmingzhen
 * @Date 21/12/8 15:54
 */
public class MyCall implements Callable<Integer> {

    private Integer ticket;

    public MyCall(Integer ticket) {
        this.ticket = ticket;
    }

    private synchronized Integer doSomething(){
        if(ticket>0){
            System.out.println("线程 "+Thread.currentThread().getName() + " 出票，剩余：" + --ticket + " " + System.currentTimeMillis());
        }else{
            System.out.println("票已售罄" + ticket);
        }
        return ticket;
    }

    @Override
    public Integer call() throws Exception {
        return doSomething();
    }
}

class MyCallDemo{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCall myCall = new MyCall(5);
        FutureTask future;
        for (int i = 0; i < 3; i++) {
            future = new FutureTask(myCall);
            new Thread(future,"thread-"+i).start();
            System.out.println(future.get());
        }
    }

}
