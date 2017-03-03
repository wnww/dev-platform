package com.yhhl.launcher;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderLauncher {

	private final static Logger log = Logger.getLogger(ProviderLauncher.class);

	public static final String SHUTDOWN_HOOK_KEY = "server.shutdown.hook";
	private static volatile boolean running = true;

	public static void main(String[] args) {
		long l = System.currentTimeMillis();
		try {
			log.info(ProviderLauncher.class.getName() + " 初始化...");
			final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[]{"classpath:spring.xml","classpath:spring-mybatis.xml","dubbo-provider.xml","tcc-transaction.xml"});

			if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
				Runtime.getRuntime().addShutdownHook(new Thread() {

					@Override
					public void run() {
						try {
							context.stop();
							log.warn("服务关闭！");
						} catch (Exception e) {
							log.error(e.getMessage(), e);
						} catch (Throwable t) {
							log.error(t.getMessage());
						}
						synchronized (ProviderLauncher.class) {
							running = false;
							ProviderLauncher.class.notify();
						}
						super.run();
					}

				});
			}

			// 拉起服务
			context.start();
			log.info(ProviderLauncher.class.getName() + " 启动结束，用时："
					+ (System.currentTimeMillis() - l) + " ms");
		} catch (RuntimeException e) {
			log.error(e.getMessage(), e);
		}
		synchronized(ProviderLauncher.class){
			while(running){
				try {
					ProviderLauncher.class.wait();
				} catch (Throwable e) {
					//e.printStackTrace();
				}
			}
		}
		
	}

}
