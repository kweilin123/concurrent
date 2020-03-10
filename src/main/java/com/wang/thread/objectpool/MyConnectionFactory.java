package com.wang.thread.objectpool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接池工厂
 * 
 * @author Guilin Wang
 *
 */
public class MyConnectionFactory implements PooledObjectFactory<MyConnection> {

	private static final Logger logger = LoggerFactory.getLogger(MyConnectionFactory.class);
	private int poolnum = 0;

	@Override
	public void activateObject(PooledObject<MyConnection> pool) throws Exception {

	}

	@Override
	public void destroyObject(PooledObject<MyConnection> pool) throws Exception {

	}

	@Override
	public PooledObject<MyConnection> makeObject() throws Exception {
		String name = new String(poolnum++ + "-" + System.currentTimeMillis());
		logger.info("init:" + name);
		MyConnection connection = new MyConnection(name);
		return new DefaultPooledObject<MyConnection>(connection);
	}

	@Override
	public void passivateObject(PooledObject<MyConnection> pool) throws Exception {

	}

	@Override
	public boolean validateObject(PooledObject<MyConnection> pool) {
		return false;
	}

}
