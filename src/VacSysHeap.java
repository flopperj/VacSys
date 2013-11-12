import java.util.ArrayList;

public class VacSysHeap implements VacSysPriorityQueue {

	private ArrayList<Patient> heap;

	public VacSysHeap() {
		this.heap = new ArrayList<Patient>(0);
	}

	public ArrayList<Patient> getHeap() {
		return this.heap;
	}

	@Override
	public void enqueue(Patient patient) {

		ArrayList<Patient> temp;

		if (patient != null) {
			temp = new ArrayList<Patient>(this.getSize() + 1);

			// rebuild the heap
			for (Patient patientInLine : this.getHeap()) {
				temp.add(patientInLine);
			}

			// add our queued item
			temp.add(this.getSize(), patient);

			// set our temp as the new heap
			this.heap = temp;
		}

	}

	@Override
	public Patient dequeue() {

		// patient to dequeue
		Patient patientToDequeue = this.heap.get(0);
		ArrayList<Patient> temp;

		if (patientToDequeue != null) {
			temp = new ArrayList<Patient>(this.getSize() - 1);

			for (Patient patientInLine : this.getHeap()) {
				if (patientInLine != patientToDequeue)
					temp.add(patientInLine);
			}

		} else
			return null;

		return patientToDequeue;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.getSize() == 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.heap.size();
	}

	@Override
	public void clear() {
		this.heap = new ArrayList<Patient>(0);
	}

}
