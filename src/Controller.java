public class Controller {

	// 50 = 800 mb
	// 60 = 960 mb
	// 400 = 6400 mb
	// 800 = 12800 mb
	// 2000 = 32000 mb
	private static final int leaf_size = 60; // in bytes
	private static final int node_levels = 5; // plus one
	private static final int leaf_split = 20;
	
	private static int leaf_offset = 0;
	private static int leaves = 0;

	private static char nodedata[];
	private static byte[] leafdata[];

	public static void init() {

		boolean verbose = false;

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
		System.out.println("Leaves:\t" + leaves + " " + leaf_size/leaf_split);
		
		if (verbose == true) {
			System.out.println("Node size: 2 bytes");
			System.out.println("Nodes:\t" + total + "("
					+ (total * 2 / 1024 / 1024) + "mb)");
			System.out.println("Leaves:\t" + leaves + "("
					+ (leaves * leaf_size / 1024 / 1024) + "mb)");
			System.out.println("Bits per leaf:\t" + leaf_size * 8);
			System.out.println("Chars per leaf:\t" + leaf_size * 8 / 4
					+ ", 5 per value");
		}

		nodedata = new char[total]; // node size is 2 bytes = 1 char
		leafdata = new byte[leaf_size][leaves];

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

	public static void nodeDump(int num) {

		System.out.print("Node\t" + num + "\t[");

		for (int i = 0; i < Main.vocabulary.length(); i++) {
			char c = Main.vocabulary.charAt(i);
			char data = toData(c);
			if ((char) (nodedata[num] & data) == data) {
				System.out.print(c);
			} else {
				System.out.print(" ");
			}
		}
		System.out.println("]");
	}

	private static boolean leafInsert(int num, String str) {
		System.out.println("leaf insert\t" + num + "\t" + str.substring(6));
		
		int splits = leaf_size / leaf_split;
		int leaves_per_split = leaves / splits;
		System.out.println(leaves+" " + leaves_per_split);
		int index = num / leaves_per_split;
		int sub_start = 666;
		System.out.println(index + " "+sub_start);
//		leafdata[leaf_size / leaf_split][leaves * leaf_split];
		
		return false;
	}

	// return index of next node to visit
	private static int nodeInsert(int num, int level, char c) {
		int next;

		// System.out.println("Insert, node " + num + " level " + level +
		// " value " + c);

		int pos = toPos(c);
		char data = toData(c);

		if ((char) (nodedata[num] & data) == data) {
			// System.out.println("found");
		} else {
			// System.out.println("not found, inserting");
			nodedata[num] = (char) (nodedata[num] | data);
		}

		next = num * Main.vocabulary.length() + pos + 1;
		// System.out.println("Next: " + next);
		return next;
	}

	private static char toData(char c) {
		char data = 0x0001;

		for (int i = 0; i < Main.vocabulary.length(); i++) {
			if (c == Main.vocabulary.charAt(i)) {
				// System.out.println("found " + i + " ");
				return data;
			}
			data = (char) (data << 1); // shift 1 bit
		}
		System.out.println("error");
		return data;
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