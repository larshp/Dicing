public class Controller {

	private Node splitNodes[];

	public static void init(String vocabulary, int length) {
		
		int nodes = 0;
		
		// 3 levels
		for(int i=1;i<=3;i++) {
			nodes = nodes + (int)Math.pow(length, i);
		}
		System.out.println(nodes);
		
	}
	
	public static boolean find(String str) {

		return false;
	}


	public static boolean insert(String str) {
		
		return false;
	}


	
	
}
