package com.wang.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QTest {

	private static ExecutorService producerService = Executors.newFixedThreadPool(3);
	private static ExecutorService consumerService = Executors.newFixedThreadPool(3);
	private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

	public static void main(String[] args) {
		int i = 0;
		while (i++ < 100) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			producerService.execute(new ABQProducer(queue));
			consumerService.execute(new ABQConsumer(queue));
		}

	}

}
