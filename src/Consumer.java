import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private final BlockingQueue<String> queue;
	private final int buffer_size = 50000; 
	private ArrayList<String> buffer = new ArrayList<String>(buffer_size);

	Consumer(BlockingQueue<String> q) {
		queue = q;
	}

	@Override
	public void run() {
		String str = null;

		while (true) {
			str = queue.poll();

			if (str != null) {
				bufferInsert(str);
				Controller.insert(str);
			} else if (Main.running == 0) {
				return;
			} else {
				sleep();
			}
		}
	}

	private void bufferInsert(String str) {
		buffer.add(str);
		
		if(buffer.size() == buffer_size) {
			Writer.write(buffer);
			buffer.clear();
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
