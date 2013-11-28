public class Main {

	// public static String vocabulary = "0123456789ABCDEFGHKMNPRSTVWXYZ";
	public static String vocabulary = "0123456789ABCDEF";
	public static int depth = 11;

	public static void main(String[] args) {

		Controller.init();
		Controller.insert("000000AAAAA");
		Controller.insert("112345AAAAA");
		Controller.insert("007345AAAAA");
		Controller.insert("FFFFFFFFFFF");

		/*
		Controller.nodeDump(0);
		Controller.nodeDump(1);
		Controller.nodeDump(2);
		Controller.nodeDump(17);
		Controller.nodeDump(17*16+1);
		Controller.nodeDump(4369);
		*/
	//	memInfo();
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
