public class Controller {

	private static char[] splitNodes[];

	public static void init() {

		int nodes = 0;

		// calculate levels
		for (int i = 1; i <= 6; i++) {
			nodes = nodes + (int) Math.pow(Main.length, i);
		}
		System.out.println("Nodes: " + nodes);

		splitNodes = new char[nodes][4];
	}

	public static boolean find(String str) {
		if (str.length() != Main.length) {
			return false;
		}
		
		
		return false;
	}

	public static boolean insert(String str) {
		
		int next = 0;
		
		if (str.length() != Main.length) {
			return false;
		}
		
		for(int i=0;i<Main.length;i++) {
			char c = str.charAt(i);
			System.out.println("At: " + i + " " + c);
	//		next = splitFind(next)
		}
		
		return false;
	}

	
	private static int splitFind(int node, char c) {
// todo, raise not found		
		return 1;
	}
}
