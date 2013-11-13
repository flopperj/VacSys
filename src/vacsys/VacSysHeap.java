package vacsys;

import java.util.ArrayList;

/**
 * Implementation of VacSys Heap
 * 
 * @author jamesarama
 * 
 * @param <T>
 */
public class VacSysHeap<T> implements VacSysPriorityQueue<T> {

	/**
	 * @private
	 * @property ArrayList<T> heap
	 */
	private ArrayList<T> heap;

	/**
	 * Default constructor
	 */
	public VacSysHeap() {
		this.heap = new ArrayList<T>();
	}

	/**
	 * Gets our heap
	 * 
	 * @return heap
	 */
	public ArrayList<T> getHeap() {
		return this.heap;
	}

	/**
	 * Re-builds a heap of array lists containing patients
	 * 
	 * @private
	 * @param index
	 */
	@SuppressWarnings("unchecked")
	private void reBuildHeap(int index) {
		ArrayList<T> currentHeap = (ArrayList<T>) this.getHeap().get(index);
		ArrayList<T> parentHeap = (ArrayList<T>) this.getHeap().get(index - 1);

		int compare = ((Patient) currentHeap.get(0))
				.compareTo((Patient) parentHeap.get(0));

		if (compare > 0) {
			this.heap.set(index, (T) parentHeap);
			this.heap.set(index - 1, (T) currentHeap);

			if (index - 1 > 0)
				reBuildHeap(index - 1);
		} else {
			if (compare == 0) {
				((ArrayList<T>) this.heap.get(index - 1)).addAll(currentHeap);
				this.heap.remove(index);
			}
		}

	}

	/**
	 * Queue's in a queue of patients
	 * 
	 * @public
	 * @param patient
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(ArrayList<T> patients) {
		this.heap.add((T) patients);
	}

	/**
	 * De-queues the first patient from our heap
	 * 
	 * @public
	 * @return patient
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T dequeue() {

		// patients to dequeue
		ArrayList<T> patientsToDequeueFrom = (ArrayList<T>) this.heap.get(0);
		T patientToDequeue = patientsToDequeueFrom.remove(0);
		reBuildHeap(heap.size() - 1);

		return patientToDequeue;
	}

	/**
	 * Checks whether our heap is empty
	 * 
	 * @public
	 * @return true|false
	 */
	@Override
	public boolean isEmpty() {
		return this.getSize() == 0;
	}

	/**
	 * Gets the size of our heap
	 * 
	 * @public
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public int getSize() {
		
		int population = 0;
		
		for(T queue : this.getHeap())
		{
			for(Patient patient: (ArrayList<Patient>)queue)
				population++;
		}
		
		return population;
	}

	/**
	 * Clear's our heap
	 * 
	 * @public
	 */
	@Override
	public void clear() {
		this.heap = new ArrayList<T>(0);
	}

}
