package com.wang.thread.philosopher;

/**
 * 问题描述：一圆桌前坐着5位哲学家，两个人中间有一只筷子，桌子中央有面条。
 * 哲学家思考问题，当饿了的时候拿起左右两只筷子吃饭，必须拿到两只筷子才能吃饭。
 * 上述问题会产生死锁的情况，当5个哲学家都拿起自己右手边的筷子，准备拿左手边的筷子时产生死锁现象。
 * 解决办法：　　1、每个哲学家必须确定自己左右手的筷子都可用的时候，才能同时拿起两只筷子进餐，吃完之后同时放下两只筷子。(all or nothing)
 */

public class PhilosopherTest{
    public static void main(String[] args) {
        Fork fork = new Fork();
        for (int i = 0; i < 5; i++) {
            new Philosopher(String.valueOf(i), fork).start();
        }
    }
}

class Philosopher extends Thread{
    private String name;
    private Fork fork;

    public Philosopher(String name, Fork fork) {
        super(name);
        this.name = name;
        this.fork = fork;
    }

    public void eating(){
        System.out.println("i am "+name+",go eating");
    }

    public void thinking(){
        try {
            sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("i am "+name+",go thinking");
    }

    @Override
    public void run() {
        while (true) {
            thinking();
            fork.take();
            eating();
            fork.put();
        }
    }

}

class Fork{
    private boolean stauts[] = {false,false,false,false,false};

    public synchronized void take() {
        String name = Thread.currentThread().getName();
        int number = Integer.parseInt(name);
        while (stauts[number]||stauts[(number+1)%5]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("i am "+name+",toke");
        stauts[number] = true;
        stauts[(number+1)%5] = true;
    }

    public synchronized void put(){
        String name = Thread.currentThread().getName();
        int number = Integer.parseInt(name);
        stauts[number] = false;
        stauts[(number+1)%5] = false;
        System.out.println("i am "+name+",put");
        notifyAll();
    }

}
