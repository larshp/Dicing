import java.util.concurrent.BlockingQueue;


public class RandomProducer implements Runnable{

	private final BlockingQueue<String> queue;
	private final int count;
	
	RandomProducer(int c, BlockingQueue<String> q) {
		count = c;
		queue = q; 
	}	
	
	public static String random() {
		String ret = "";
		for (int i = 0; i < Main.depth; i++) {
			ret = ret
					+ Main.vocabulary.charAt((int) Math.ceil(Math.random()
							* Main.vocabulary.length() - 1));
		}
		return ret;
	}

	@Override
	public void run() {
		for(int i=0;i<count;i++) {
			try {
				queue.put(random());
				System.out.println(queue.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		Main.running = false;
	}
	
}
