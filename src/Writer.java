import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

	public static void write(ArrayList<String> list) {
		FileWriter writer = null;

		try {
			writer = new FileWriter("output/output_" + Thread.currentThread().getId()
					+ "_" + System.currentTimeMillis() + ".txt");
			for (String str : list) {
				writer.write(str + "\r\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

}
