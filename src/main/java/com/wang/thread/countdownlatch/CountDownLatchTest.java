package com.wang.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			new CountDownLatchThread(String.valueOf(i), latch).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("latch all end");
	}

}
