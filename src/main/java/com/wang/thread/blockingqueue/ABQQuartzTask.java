package com.wang.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ABQQuartzTask {
	private static final Logger logger = LoggerFactory.getLogger(ABQQuartzTask.class);

	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	private static ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);

	/**
	 * 消费者
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	@Async
	public void consume() {
		String value = null;

		try {
			value = blockingQueue.poll(1, TimeUnit.SECONDS);
			logger.info("consume value :" + value);
		} catch (InterruptedException e) {
			logger.error("consume err:", e);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 消费者
	 */
	@Scheduled(cron = "0/2 * * * * ?")
	@Async
	public void product() {
		String value = String.valueOf(atomicInteger.getAndIncrement());
		try {
			blockingQueue.put(value);
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
