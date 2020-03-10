package com.wang.thread.objectpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyConnection {
	private static final Logger logger = LoggerFactory.getLogger(MyConnection.class);
	private String name;
	private boolean connected;

	public MyConnection(String name) {
		this.name = name;
	}

	public void connect() {
		this.connected = true;
		logger.info(name + ": " + connected);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

}
