import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

	// public static String vocabulary = "0123456789ABCDEFGHKMNPRSTVWXYZ";
	public static final String vocabulary = "0123456789ABCDEF";
	public static final int depth = 10;
	public static boolean persist = true;

	// variables
	public static volatile int running = 0; // todo

	
	public static void main(String[] args) throws InterruptedException {
		setup();
		info();
	}

	public static void setup() {
		int max = 10000000;
		int producers = 2;
		int consumers = 2;

		BlockingQueue<String> q = new ArrayBlockingQueue<String>(50000);

		Controller.init();

		for (int i = 0; i < producers; i++) {
			RandomProducer p = new RandomProducer(max / producers, q);
			new Thread(p).start();
		}

		for (int i = 0; i < consumers; i++) {
			Consumer c = new Consumer(q);
			new Thread(c).start();
		}

	}

	public static void info() throws InterruptedException {

		long startTime = System.currentTimeMillis();

		Thread.sleep(100);
		while (running > 0) {
			Thread.sleep(100);
		}

		long stopTime = System.currentTimeMillis();
		long elapsedSec = (stopTime - startTime) / 1000;
		System.out.println("Element count: " + Controller.element_count.get());
		System.out.println("Elapsed: " + elapsedSec + " sec");
		if (elapsedSec != 0) {
			System.out.println("Elements/sec: "
					+ Controller.element_count.get() / elapsedSec);
		}

		// memInfo();
		System.out.println("Done");
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
