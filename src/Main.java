import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

	// public static String vocabulary = "0123456789ABCDEFGHKMNPRSTVWXYZ";
	public static final String vocabulary = "0123456789ABCDEF";
	public static final int depth = 10;

	// variables
	public static volatile boolean running = true;

	public static void main(String[] args) throws InterruptedException {

		/*
		 * Controller.insert("000000AAAAA"); Controller.insert("002345AAAAA");
		 * Controller.insert("88888888888"); Controller.insert("FFFFFEFFFFF");
		 * Controller.insert("FFFFFFFFFFF");
		 */

		running = true;

		Controller.init();

		BlockingQueue<String> q = new ArrayBlockingQueue<String>(1024);
		int count = 1000000;
		RandomProducer p = new RandomProducer(count, q);
		Consumer c = new Consumer(q);
		new Thread(p).start();
		new Thread(c).start();

		long startTime = System.currentTimeMillis();

		while (running == true) {
			Thread.sleep(100);
		}

		long stopTime = System.currentTimeMillis();
		long elapsedSec = (stopTime - startTime) / 1000;
//		int count = Controller.element_count;
		System.out.println("Element count: " + count);
		System.out.println("Elapsed: " + elapsedSec + " sec");
		if (elapsedSec != 0) {
			System.out.println("Elements/sec: " + count / elapsedSec);
		}

		/*
		 * Controller.nodeDump(0); Controller.nodeDump(1);
		 * Controller.nodeDump(2); Controller.nodeDump(17);
		 * Controller.nodeDump(273); Controller.nodeDump(4369);
		 * Controller.nodeDump(69904); Controller.nodeDump(69905); // next level
		 * Controller.nodeDump(69906); Controller.nodeDump(69907);
		 */

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
