
public class Main {

	public static void main(String[] args) {

		String vocabulary = "0123456789ABCDEF";
		int length = 16;

		Controller.init(vocabulary, length);
		Controller.insert("23423");
		
	}

}
