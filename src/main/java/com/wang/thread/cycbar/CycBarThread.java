package com.wang.thread.cycbar;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CycBarThread extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(CycBarThread.class);
	private String name;
	private CyclicBarrier cyclicBarrier;

	public CycBarThread(String name, CyclicBarrier cyclicBarrier) {
		super();
		this.name = name;
		this.cyclicBarrier = cyclicBarrier;
	}

	public CyclicBarrier getCyclicBarrier() {
		return cyclicBarrier;
	}

	public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		try {
			Thread.sleep((long) (10000 * Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			cyclicBarrier.await();
			logger.info("线程组任务1" + name + "结束，其他任务继续");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

		// cyclicBarrier 复用
		try {
			Thread.sleep((long) (10000 * Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			cyclicBarrier.await();
			logger.info("线程组任务2" + name + "结束，其他任务继续");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}
}
