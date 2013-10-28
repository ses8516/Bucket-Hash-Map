import java.util.ArrayList;

public class Test extends Thread{

	private static final int PER_THREAD = 5;
	private static final int NUM_THREADS = 5;
	
	private static ConcurrentBucketHashMap<Integer , String> MAP = new ConcurrentBucketHashMap<Integer , String>(5);
	
	private static volatile int ELEMENT = 1;
	
	public Test(String name) {
		super(name);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
			MAP.put(ELEMENT, ""+ELEMENT);
		}
		
		
	}
}
