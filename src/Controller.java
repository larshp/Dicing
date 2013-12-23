public class Controller {

	// 50 = 800 mb
	// 60 = 960 mb
	// 400 = 6400 mb
	// 800 = 12800 mb
	// 2000 = 32000 mb
	private static final int leaf_size = 70; // in bytes, increase with 10
	private static final int node_levels = 5; // plus one

	private static int leaf_offset = 0;
	private static int leaves = 0;

	private static byte[] leafdata[];

	private static int element_count = 0;

	
	public static void init() {

		boolean verbose = true;

		int total = 0;
		int last = 0;

		// calculate levels
		for (int i = 0; i <= node_levels; i++) {
			last = (int) Math.pow(Main.vocabulary.length(), i);
			// System.out.println("Level: " + i + ", nodes: " + last);
			total = total + last;
		}
		leaf_offset = total;
		leaves = last * Main.vocabulary.length();

		if (verbose == true) {
			System.out.println("Leaves:\t" + leaves + "("
					+ (leaves * leaf_size / 1024 / 1024) + "mb)");
			System.out.println("Bits per leaf:\t" + leaf_size * 8);
			System.out.println("Chars per leaf:\t" + leaf_size * 8 / 4
					+ ", 4 per value");
		}

		leafdata = new byte[leaf_size + 1][leaves];

		System.out.println("Memory Allocated");

	}

	// returns
	// true if inserted successfully
	// false if already exists
	public static boolean insert(String str) {

		int next_node = 0;
		char c;

		if (str.length() != Main.depth) {
			System.out.println("error");
			return false;
		}

		for (int i = 0; i <= node_levels; i++) {
			c = str.charAt(i);
			next_node = nodeInsert(next_node, i, c);
		}

		return leafInsert(next_node - leaf_offset, str);
	}

	private static boolean leafInsert(int num, String str) {
//		System.out.println("Leaf insert\t" + num + "\t\t" + str.substring(6));

		int per = leaves / leaf_size;
		int index = num / per;
		int sub = (num - index * per) * leaf_size;

		// System.out.println("index " + index + " sub " + sub);
		countInc();

		for (int i = sub; i < sub + leaf_size; i++) {
			byte c = leafdata[index][sub];
			if (leafdata[index][i] == 0x00) {
				leafdata[index][i] = 0x0B;
			} else {
				leafdata[index][i] = 0x00;
			}
		}

		return false;
	}

	private static synchronized void countInc() {
		element_count = element_count + 1;
	}
	
	public static synchronized int count() {
		return element_count;
	}
	
	// return index of next node to visit
	private static int nodeInsert(int num, int level, char c) {
		int next;
		int pos = toPos(c);

		next = num * Main.vocabulary.length() + pos + 1;
		return next;
	}

	private static int toPos(char c) {
		for (int i = 0; i < Main.vocabulary.length(); i++) {
			if (c == Main.vocabulary.charAt(i)) {
				return i;
			}
		}
		System.out.println("error");
		return 0;
	}

}