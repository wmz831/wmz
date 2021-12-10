package com.wmz.multiThread;

/**
 * @Author wangmingzhen
 * @Date 21/12/8 15:21
 *
 * 同步与死锁
 * 若干线程访问同一资源时，需要进行同步处理，而过多的同步会造成死锁
 */
public class MyTicket implements Runnable{

    /**
     * volatile关键字用于修饰属性字段，表示直接操作内存，不经过cache拷贝，更快速且同步
     */
    private volatile int ticket;

    public MyTicket(Integer ticket) {
        this.ticket = ticket;
    }

    /**
     * 同步方法，避免超卖
     * synchronized关键字用于修饰方法和代码块，表示只有一个线程可以运行，但性能会降低
     * synchronized(同步对象){
     *     同步代码块
     * }
     */
    private synchronized void sale(){
        if(ticket>0){
            try {
                //模拟网络延迟
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程 "+Thread.currentThread().getName() + " 出票，剩余：" + ticket-- + " " + System.currentTimeMillis());
        }else{
            System.out.println("票已售罄" + ticket);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            sale();
        }
    }

}

class MyTicketDemo{

    public static void main(String[] args) {
        Runnable run = new MyTicket(20);
        for (int i = 0; i < 5; i++) {
            new Thread(run,"thread-"+i).start();
        }
    }
}

