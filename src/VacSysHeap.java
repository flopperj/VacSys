import java.util.ArrayList;


public class VacSysHeap<T> implements VacSysPriorityQueue<T> {
	private ArrayList<T> heap;
	
	public VacSysHeap(){
		this.heap = new ArrayList<T>();
	}
	
	public ArrayList<T> getHeap(){
		return this.heap;
	}

	@Override
	public void enqueue(Patient<T> patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Patient<T> dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
