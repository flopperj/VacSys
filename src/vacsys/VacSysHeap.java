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
	 * Re-builds a heap of array lists containing patients starting from the
	 * specified index
	 * 
	 * @private
	 * @param index
	 */
	@SuppressWarnings("unchecked")
	private void reBuildHeap(int index) {

		// Get the current and parent queues
		ArrayList<T> currentQueue = (ArrayList<T>) this.getHeap().get(index);
		ArrayList<T> parentQueue = (ArrayList<T>) this.getHeap().get(index - 1);

		if (!currentQueue.isEmpty()) {

			// set our compare
			int compare = ((Patient) currentQueue.get(0))
					.compareTo((Patient) parentQueue.get(0));

			// check whether first patient from the current queue has priority
			// over
			// patient from parent's queue
			if (compare > 0) {
				// swap parent and current queue's positions in heap
				this.heap.set(index, (T) parentQueue);
				this.heap.set(index - 1, (T) currentQueue);

				// perform a recursive call till we hit the last queue in the
				// heap
				if (index - 1 > 0)
					reBuildHeap(index - 1);
			} else {
				// Add current queue to previous index if both first patient's
				// from
				// parent and current queue have similar priority and remove
				// current
				// queue from heap
				if (compare == 0) {
					((ArrayList<T>) this.heap.get(index - 1))
							.addAll(currentQueue);
					this.heap.remove(index);
				}
			}
		}

	}

	/**
	 * Queues in an individual patient
	 * 
	 * @public
	 * @param patient
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(T patient) {

		// Initialize a single patient queue
		ArrayList<T> newPatientQueue = new ArrayList<T>();
		newPatientQueue.add(patient);

		// enqueue the new queue
		this.heap.add((T)newPatientQueue);

		// rebuild our heap
		if (this.heap.size() > 1)
			this.reBuildHeap(this.heap.size() - 1);

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

		// get queue patients to dequeue from
		ArrayList<T> patientsToDequeueFrom = (ArrayList<T>) this.heap.get(0);

		// get first patient from our queue
		T patientToDequeue = patientsToDequeueFrom.remove(0);

		// rebuild our heap
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
	@SuppressWarnings("unchecked")
	@Override
	public int getSize() {

		int population = 0;

		// Calculate size of every queue to determine our total population
		for (T queue : this.getHeap()) {
			for (Patient patient : (ArrayList<Patient>) queue) {
				if (patient != null)
					population++;
			}
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
