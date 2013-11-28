public class Controller {

	private static byte nodedata[];

	public static void init() {

		int total = 0;
		int bytes;
		
		System.out.println("Vocabulary length: " + Main.vocabulary.length());
		bytes = (int)Math.ceil(Main.vocabulary.length() / 8.0);
		System.out.println("Node size: " + bytes + " bytes");
		
		// calculate levels
		for (int i = 0; i <= 7; i++) {
			int local = (int) Math.pow(Main.vocabulary.length(), i);
			System.out.println("Level: "+i + ", nodes: " + local);
			total = total + local;
		}
		System.out.println("Nodes: " + total);

		nodedata = new byte[total*bytes];
		
		System.out.println("Allocated");
	}

	public static boolean find(String str) {
		
		
		return false;
	}

	public static boolean insert(String str) {
		
		
		return false;
	}

}
