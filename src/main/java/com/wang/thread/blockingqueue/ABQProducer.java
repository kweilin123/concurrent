package com.wang.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABQProducer implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ABQConsumer.class);
	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	private BlockingQueue<String> queue;

	public ABQProducer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		String value = String.valueOf(atomicInteger.getAndIncrement());
		try {
			queue.put(value);
			// logger.info("product value :" + value);
		} catch (InterruptedException e) {
			logger.error("product err:", e);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

	}

}
