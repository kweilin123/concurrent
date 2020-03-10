package com.wang.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountDownLatchThread extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(CountDownLatchThread.class);
	private String name;
	private CountDownLatch latch;

	public CountDownLatchThread(String name, CountDownLatch latch) {
		this.name = name;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep((long) (10000 * Math.random()));
			latch.countDown();
			logger.info("CountDownLatch finish" + name);
			// 执行完可继续往下运行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
