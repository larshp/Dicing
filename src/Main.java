
public class Main {

//	public static String vocabulary = "0123456789ABCDEFGHKMNPRSTVWXYZ";
	public static String vocabulary = "0123456789ABCDEF";
	
	public static void main(String[] args) {

		Controller.init();
		Controller.insert("ABC2384756192784");
		
//		memInfo();
	}

	
    public static void memInfo() {                   
    	int mb = 1024*1024;                  
    	//Getting the runtime reference from system        
    	Runtime runtime = Runtime.getRuntime();                
    	System.out.println("##### Heap utilization statistics [MB] #####");     
    	//Print used memory        
    	System.out.println("Used Memory:"         
    	+ (runtime.totalMemory() - runtime.freeMemory()) / mb);      
    	//Print free memory      
    	System.out.println("Free Memory:"     
    	+ runtime.freeMemory() / mb);        
    	//Print total available memory      
    	System.out.println("Total Memory:" + runtime.totalMemory() / mb);          
    	//Print Maximum available memory        
    	System.out.println("Max Memory:" + runtime.maxMemory() / mb);     
    }
	
}
