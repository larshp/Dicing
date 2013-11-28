public class Controller {

	private static byte nodedata[];

	private static byte[] foo1;
	private static byte[] foo2;
	private static byte[] foo3;
	private static byte[] foo4;
	
	private static final int node_levels = 5;
	
//   50 =   800 mb	
//   60 =   960 mb
// 2000 = 32000 mb
	private static final int leaf_length = 60;
	
	
	public static void init() {

		int total = 0;
		int last = 0;
		long leafnodes = 0;
		int bytes = 0;

		System.out.println("Vocabulary length: " + Main.vocabulary.length());
		System.out.println("Depth: " + Main.depth);

		bytes = (int) Math.ceil(Main.vocabulary.length() / 8.0);
		System.out.println("Node size: " + bytes + " bytes");

		// calculate levels
		for (int i = 0; i <= node_levels; i++) {
			last = (int) Math.pow(Main.vocabulary.length(), i);
			System.out.println("Level: " + i + ", nodes: " + last);
			total = total + last;
		}
		leafnodes = last * Main.vocabulary.length();

		System.out.println("Nodes:\t" + total + "("
				+ (total * bytes / 1024 / 1024) + "mb)");
		System.out.println("Leaves:\t" + leafnodes + "("
				+ (leafnodes * leaf_length / 1024 / 1024) + "mb)");
		System.out.println("Bits per leaf:\t" + leaf_length * 8);
		System.out.println("Chars per leaf:\t" + leaf_length * 8 / 4); // todo 4 = 16 voc length

		nodedata = new byte[total * bytes];
//		leafdata = new byte[leafnodes * leaf_length];

		foo1 = new byte[16777216*40];		
		foo2 = new byte[16777216*10];
		foo3 = new byte[16777216*10];
		
		System.out.println("Allocated");

	}

	// returns
	// true if inserted successfully
	// false if already exists
	public static boolean insert(String str) {

		return false;
	}

}
