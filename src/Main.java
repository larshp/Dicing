// http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CountDownLatch.html

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

	// public static String vocabulary = "0123456789ABCDEFGHKMNPRSTVWXYZ";
	public static final String vocabulary = "0123456789ABCDEF";
	public static final int depth = 10;

	// variables
	public static volatile int running = 0;

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<String> q = new ArrayBlockingQueue<String>(1024);

		Controller.init();
		
		RandomProducer p = new RandomProducer(25000000, q);
		new Thread(p).start();
		p = new RandomProducer(25000000, q);
		new Thread(p).start();
		
		Consumer c = new Consumer(q);
		new Thread(c).start();
		c = new Consumer(q);
		new Thread(c).start();
		
		long startTime = System.currentTimeMillis();

		Thread.sleep(100);
		while (running > 0) {
			Thread.sleep(100);
		}

		long stopTime = System.currentTimeMillis();
		long elapsedSec = (stopTime - startTime) / 1000;
		System.out.println("Element count: " + Controller.count());
		System.out.println("Elapsed: " + elapsedSec + " sec");
		if (elapsedSec != 0) {
			System.out.println("Elements/sec: " + Controller.count() / elapsedSec);
		}

		// memInfo();
	}

	public static void memInfo() {
		int mb = 1024 * 1024;

		Runtime runtime = Runtime.getRuntime();
		System.out.println("##### Heap utilization statistics [MB] #####");
		System.out.println("Used Memory: "
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
		System.out.println("Free Memory: " + runtime.freeMemory() / mb);
		System.out.println("Total Memory: " + runtime.totalMemory() / mb);
		System.out.println("Max Memory: " + runtime.maxMemory() / mb);
	}

}
