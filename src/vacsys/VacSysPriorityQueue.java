package vacsys;
/**
 * Interface for our VacSysHeap
 * @author jamesarama
 *
 * @param <T>
 */
public interface VacSysPriorityQueue {	
	/**
	 * Queue's in a patient
	 * @param Patient patient
	 */
	public abstract void enqueue(Patient patient);
	/**
	 * De-queues the first patient from our heap
	 * @return Patient patient
	 */
	public abstract Patient dequeue();
	/**
	 * Checks whether our heap is empty
	 * @return boolean true|false
	 */
	public abstract boolean isEmpty();
	/**
	 * Clear's our heap
	 */
	public abstract void clear();
	/**
	 * Gets the size of our queue
	 * @return int size
	 */
	public abstract int getSize();

}
