package com.wmz.multiThread;

/**
 * @Author wangmingzhen
 * @Date 21/12/10 17:58
 */
public class AddAndSubtract {

    private volatile int resouce;
    private volatile boolean flag=false;

    public synchronized void add() throws InterruptedException{
        //判断
        if(this.flag==false){
            super.wait();
        }
        //延迟
        Thread.sleep(100);
        //操作
        this.resouce++;
        System.out.println(Thread.currentThread().getName() +" +1，结果："+ resouce);
        this.flag=false;
        //唤醒
        super.notifyAll();
    }

    public synchronized void subtract() throws InterruptedException {
        //判断
        if(this.flag==true){
            super.wait();
        }
        //操作
        this.resouce--;
        System.out.println(Thread.currentThread().getName() +" -1，结果："+ resouce);
        this.flag=true;
        //延迟
        Thread.sleep(100);
        //唤醒
        super.notifyAll();
    }

}

class Demo{
    public static void main(String[] args) {

        AddAndSubtract aas = new AddAndSubtract();
        for (int i = 0; i < 2; i++) {
            new Thread(new AddThread(aas), "加法线程"+i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new SubtractThread(aas), "减法线程"+i).start();
        }
    }
}

class AddThread implements Runnable{

    private AddAndSubtract aas;

    public AddThread(AddAndSubtract aas) {
        this.aas = aas;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                aas.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SubtractThread implements Runnable{

    private AddAndSubtract aas;

    public SubtractThread(AddAndSubtract aas) {
        this.aas = aas;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                aas.subtract();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

