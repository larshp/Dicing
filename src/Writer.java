import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

	public static void write(ArrayList<String> list) {

		if (Main.persist == false) {
			return;
		}

		FileWriter file = null;

		try {
			file = new FileWriter("output/output_"
					+ Thread.currentThread().getId() + "_"
					+ System.currentTimeMillis() + ".txt");
			BufferedWriter writer = new BufferedWriter(file, 1024*64);

			for (String str : list) {
				writer.write(str + "\r\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
