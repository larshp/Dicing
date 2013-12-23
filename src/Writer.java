import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

	@SuppressWarnings("unused")
	public static void write(ArrayList<String> list) {

		if (1 == 2 + 8) {
			FileWriter writer = null;

			try {
				writer = new FileWriter("output/output_"
						+ Thread.currentThread().getId() + "_"
						+ System.currentTimeMillis() + ".txt");
				for (String str : list) {
					writer.write(str + "\r\n");
				}
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
