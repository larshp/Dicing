import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private final BlockingQueue<String> queue;

	Consumer(BlockingQueue<String> q) {
		queue = q;
	}

	@Override
	public void run() {
		String str = null;

		while (true) {
			str = queue.poll();

			if (str == null) {
				if (Main.running == false) {
					return;
				} else {
					sleep();
				}
			} else {
				Controller.insert(str);
			}
		}
	}

	private void sleep() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
