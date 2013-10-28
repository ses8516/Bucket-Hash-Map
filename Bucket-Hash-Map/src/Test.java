import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Test extends Thread{

	private static final int PER_THREAD = 10;
	private static final int NUM_THREADS = 10;
	private static final int NUM_BUCKETS = 5;
	
	private static ConcurrentBucketHashMap<Integer , String> MAP = new ConcurrentBucketHashMap<Integer , String>(NUM_BUCKETS);
	
	private static volatile int KEY = 1;
	
	public Test(String name) {
		super(name);
	}

	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream("Trace.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("Trace.txt file not found.");
			return;
		}
		
		ArrayList<Thread> threads = new ArrayList<Thread>(NUM_THREADS);
		
		for(int i = 0; i < NUM_THREADS; i++){
			threads.add(new Test(""+i));
		}
		
		for(Thread t : threads){
			t.start();
		}
	}

	public void run(){
		// Add Elements to bucket
		for(int i = 0; i < PER_THREAD; i++){
			MAP.put(KEY, ""+KEY);
			KEY++;
		}
		
		while(true){
			int option = (int) (Math.random() * 5);
			
			if(option == 0){
				MAP.put(KEY, ""+KEY);
				KEY++;
			}
			else if(option == 1){
				MAP.containsKey((int) (Math.random() * KEY));
			}
			else if(option == 2){
				MAP.size();
			}
			else if(option == 3){
				MAP.get((int) (Math.random() * KEY));
			}
			else if(option == 4){
				MAP.remove((int) (Math.random() * KEY));
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
