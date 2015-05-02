package me.stevesite.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import me.stevesite.model.Courier;

/**
 * The CourierManager is used to start the Courier when Tomcat starts and attempt to destroy it on shutdown.
 * @see Courier
 */
public class CourierManager implements ServletContextListener {

	Courier c;
	Thread t;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		c = new Courier();
		t = new Thread(c);
		t.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		t.interrupt();
	}

}
