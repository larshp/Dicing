import java.util.Date;

public class Main {

	// public static String vocabulary = "0123456789ABCDEFGHKMNPRSTVWXYZ";
	public static String vocabulary = "0123456789ABCDEF";
	public static int depth = 10;

	public static void main(String[] args) {

		Controller.init();

		/*
		 * Controller.insert("000000AAAAA"); Controller.insert("002345AAAAA");
		 * Controller.insert("88888888888"); Controller.insert("FFFFFEFFFFF");
		 * Controller.insert("FFFFFFFFFFF");
		 */
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 50000000; i++) {
			Controller.insert(random());
		}
		long stopTime = System.currentTimeMillis();
		long elapsedSec = (stopTime - startTime) / 1000;
		System.out.println("Element count: " + Controller.element_count);		
		System.out.println("Elapsed: " + elapsedSec + " sec");
		if (elapsedSec != 0) {
			System.out.println("Elements/sec: " + Controller.element_count
					/ elapsedSec);
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

	public static String random() {
		String ret = "";

		for (int i = 0; i < Main.depth; i++) {
			ret = ret
					+ Main.vocabulary.charAt((int) Math.ceil(Math.random()
							* Main.vocabulary.length() - 1));
		}
		return ret;
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
