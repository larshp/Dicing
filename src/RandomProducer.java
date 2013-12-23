import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class RandomProducer implements Runnable {

	private final BlockingQueue<String> queue;
	private final int count;

	RandomProducer(int c, BlockingQueue<String> q) {
		count = c;
		queue = q;
	}

	@Override
	public void run() {
		Main.running = Main.running + 1;

		for (int i = 0; i < count; i++) {
			try {
				queue.put(random());
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		Main.running = Main.running - 1;
	}

	public static String random() {
		String ret = "";
		for (int i = 0; i < Main.depth; i++) {

			ret = ret
					+ Main.vocabulary.charAt((int) Math.ceil(ThreadLocalRandom
							.current().nextDouble(1)
							* Main.vocabulary.length()
							- 1));
		}
		return ret;
	}
}