package com.wang.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABQConsumer implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ABQConsumer.class);

	private BlockingQueue<String> queue;

	public ABQConsumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		String value = null;
		try {
			value = queue.poll(1, TimeUnit.SECONDS);
			logger.info("consume value :" + value);
		} catch (InterruptedException e) {
			logger.error("consume err:", e);
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
	}

}
