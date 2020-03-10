package com.wang.thread.objectpool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyConnectionPoolTest {
	private static final Logger logger = LoggerFactory.getLogger(MyConnectionPoolTest.class);

	public static void main(String[] args) {

		GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
		conf.setMaxTotal(10);
		GenericObjectPool<MyConnection> pool = new GenericObjectPool<MyConnection>(new MyConnectionFactory(), conf);
		for (int i = 0; i < 100; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						MyConnection connection = pool.borrowObject();
						Thread.sleep((long) (2000 * Math.random()));
						logger.info("name:" + connection.getName());
						pool.returnObject(connection);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}).start();
		}
	}

}
