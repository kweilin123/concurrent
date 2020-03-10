package com.wang.thread.objectpool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * my pool继承Pool
 * 
 * @author Guilin Wang
 * @param <T>
 *
 */
public class MyObjectPool<T> extends ObjectPool<T> {

	public MyObjectPool() {

	}

	public MyObjectPool(PooledObjectFactory<T> pooledObjectFactory, GenericObjectPoolConfig conf) {
		super(pooledObjectFactory, conf);
	}
}
