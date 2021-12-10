package com.wmz.multiThread;

/**
 * @Author wangmingzhen
 * @Date 21/12/10 11:26
 *
 * 生产者-消费者 模型
 * public final void wait()：等待
 * public final void notify()：唤醒一个等待中的线程
 * public final void notifyAll()：唤醒所有
 *
 * synchronized + wait + notify
 * 在其他线程中o.notify()，就可以唤醒在o上wait的线程。可是如果o上有多个线程wait，是没有办法唤醒“指定”的某个线程的
 *
 * Lock + Condition 实现唤醒指定的部分线程
 */
public class Message {

    private String message;

    private boolean full = false;

    public boolean isFull() {
        return full;
    }

    public synchronized void push(String message) {
        System.out.println("push方法执行");
        if(full){
            try {
                System.out.println("有消息，push方法wait");
                super.wait();
                System.out.println("push continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("推送消息 >> "+message);
        //设置数据
        this.message = message;
        this.full=true;
        //唤醒一个线程
        super.notify();
    }

    public synchronized String poll() {
        System.out.println("poll方执行");
        if(!full){
            System.out.println("没有消息，poll方法wait");
            try {
                super.wait();
                System.out.println("poll continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费消息 << "+ message);
        //设置数据
        this.message = "空";
        this.full=false;
        //唤醒一个线程
        super.notify();
        return message;
    }

}

class MessageDemo {

    static boolean flag = true;

    public static void main(String[] args) {
        Message msg = new Message();
        new Thread(new Proudcer(msg)).start();
        new Thread(new Consumer(msg)).start();

        Runnable run = () -> {
            flag=false;
            System.out.println(false);
        };
        new Thread(run).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                flag=true;
//            }
//        });
    }

}

class Proudcer implements Runnable {

    private Message message;

    public Proudcer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            message.push("第"+ i +"条消息");
        }
    }
}

class Consumer implements Runnable {

    private Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            message.poll();
        }
    }
}