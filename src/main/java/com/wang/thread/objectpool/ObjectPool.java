package com.wang.thread.objectpool;

import java.io.IOException;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义pool: 定义自己的获取资源与释放资源的方法
 * 
 * @author Guilin Wang
 * @param <T>
 *
 */
public class ObjectPool<T> implements Cloneable {

	private static final Logger logger = LoggerFactory.getLogger(ObjectPool.class);

	public GenericObjectPool<T> innerPool;

	public ObjectPool() {
	}

	public ObjectPool(PooledObjectFactory<T> pooledObjectFactory, GenericObjectPoolConfig conf) {
		initPool(pooledObjectFactory, conf);
	}

	/**
	 * 初始化
	 * 
	 * @param conf
	 * @param pooledObjectFactory
	 */
	public void initPool(PooledObjectFactory<T> pooledObjectFactory, GenericObjectPoolConfig conf) {
		if (this.innerPool != null) {
			logger.info("innerPool is not null,destroy it..");
			innerPool.close();
		}
		this.innerPool = new GenericObjectPool<T>(pooledObjectFactory, conf);
	}

	public void close() throws IOException {
		innerPool.close();
	}

	/**
	 * 借出对象
	 * 
	 * @return
	 */
	public T getResource() {
		T t = null;
		try {
			t = this.innerPool.borrowObject();
		} catch (Exception e) {
			logger.error("innerPool borrowObject err...", e);
		}
		return t;
	}

	/**
	 * 归还对象
	 * 
	 * @param resource
	 */
	public void returnResourceObject(final T resource) {
		if (resource == null) {
			return;
		}
		innerPool.returnObject(resource);
	}

}
