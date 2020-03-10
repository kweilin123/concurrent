package com.wang.thread.cycbar;

import java.util.concurrent.CyclicBarrier;

public class CycBarTest {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {

			@Override
			public void run() {
				System.out.println("cyclicBarrier all done");
			}
		});

		for (int i = 0; i < 10; i++) {
			new CycBarThread(String.valueOf(i), cyclicBarrier).start();
		}
		System.out.println("for task1 done");

		// cyclicBarrier 可复用
		/*
		 * for (int i = 0; i < 10; i++) { new CycBarThread(String.valueOf(i),
		 * cyclicBarrier).start(); } System.out.println("for task2 done");
		 */
	}

}
