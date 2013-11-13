package vacsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents our vacination System
 * 
 * @author jamesarama
 * 
 */
public class VacSys {
	/**
	 * @private
	 * @property VacSysHeap priorityHeap
	 */
	@SuppressWarnings("rawtypes")
	private VacSysHeap priorityHeap;
	/**
	 * @private
	 * @property int totalPopulation
	 */
	private int totalPopulation;
	/**
	 * @private
	 * @property ArrayList<String> zipcodes
	 */
	private ArrayList<String> zipcodes;
	/**
	 * @private
	 * @property Map<Integer, ArrayList<Patient>> sortedPatients
	 */
	private HashMap<Integer, ArrayList<Patient>> sortedPatients;

	/**
	 * Calculates the population at a given zipcode
	 * 
	 * @public
	 * @param zipcode
	 * @return population
	 */
	private int getZipcodePopulation(String zipcode) {

		int population = 0;

		// whenever we have a matching zipcode in our list of zipcode tally up
		// the population
		for (String zip : this.zipcodes) {
			if (zip.equals(zipcode))
				population++;
		}

		return population;
	}

	/**
	 * Default Constructor Creates a system with an empty priority queue
	 */
	public VacSys() {
		priorityHeap = new VacSysHeap<ArrayList<Patient>>();
		totalPopulation = 0;
		zipcodes = new ArrayList<String>();
	}

	/**
	 * Overloaded constructor to create system loaded with requests from the
	 * batch file given by the filename
	 * 
	 * @param filename
	 */
	@SuppressWarnings("unchecked")
	public VacSys(String filename) throws IOException {
		// initialize all private members necessary
		priorityHeap = new VacSysHeap<ArrayList<Patient>>();
		totalPopulation = 0;
		zipcodes = new ArrayList<String>();
		sortedPatients = new HashMap<Integer, ArrayList<Patient>>();

		try {
			// Open the file that we passed during construction of the System
			FileReader file = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(file);

			// line in file
			String line;

			// Loop through all the lines in the file and extract the data that
			// we need
			while ((line = buffer.readLine()) != null) {

				// Information that will be in our line is in the format of
				// <name, age, zip> so we want to split the line based on a coma
				String[] information = line.split(",");

				// create a temporary patient object that we'll use to create
				// our queues
				Patient patientInLine = new Patient();

				patientInLine.setName(information[0]);
				patientInLine.setAge(Integer.parseInt(information[1].trim()));
				patientInLine.setZip(information[2]);

				// add zip code to zipcodes list. This will be helpful when
				// determining total population at a given zipcode
				zipcodes.add(information[2]);
				int zipcodePopulation = this
						.getZipcodePopulation(information[2]);
				totalPopulation++;

				// set patient's priority based on zipcode population and total
				// population
				patientInLine.setPriority(zipcodePopulation,
						this.totalPopulation);

				int tempPriority = patientInLine.getPriority();

				// create array list queue for patients based on their priority
				if (!this.sortedPatients.containsKey(tempPriority))
					this.sortedPatients.put(tempPriority,
							new ArrayList<Patient>());

				// add patient to our hashmap of patient queues at their
				// respected priority
				this.sortedPatients.get(tempPriority).add(patientInLine);

			}
			file.close();

			// Set total population to the size of the zipcodes list since it
			// contains everyone's zipcode
			totalPopulation = zipcodes.size();

			// Prepare our patients so that we can add them to the priority
			// queue
			for (int priority : this.sortedPatients.keySet()) {
				ArrayList<Patient> patientsQueue = (ArrayList<Patient>) sortedPatients
						.get(priority);

				// queue in individual patient from patients queue
				for (Patient patient : patientsQueue)
					this.priorityHeap.enqueuePatient(patient);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds a new request to the system
	 * 
	 * @param name
	 * @param age
	 * @param zip
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean insert(String name, int age, String zip) {

		// instantiate our new patient and set all their necessasry information
		Patient newPatient = new Patient();
		newPatient.setName(name);
		newPatient.setAge(age);
		newPatient.setZip(zip);

		// set new patient's priorty value
		int zipcodePopulation = this.getZipcodePopulation(zip);
		newPatient.setPriority(zipcodePopulation, this.totalPopulation);

		// create array list for patients based on our priority if none exists
		if (!this.sortedPatients.containsKey(newPatient.getPriority()))
			this.sortedPatients.put(newPatient.getPriority(),
					new ArrayList<Patient>());

		// TODO: Remove this debug
		System.out.println(this.priorityHeap.getSize()
				+ " <=== Size Before Insertion");

		// add the new patient to queue that contains the specified priority
		this.sortedPatients.get(newPatient.getPriority()).add(newPatient);

		// Enqueue new patient to our priority heap
		this.priorityHeap.enqueuePatient(newPatient);

		return true;
	}

	/**
	 * Removes the next request from the system
	 * 
	 * @return patient
	 */
	public String remove() {
		Patient patient = (Patient) this.priorityHeap.dequeue();
		return patient.toString();
	}

	/**
	 * Remove num requests and store them in CVS format in filename
	 * 
	 * @param num
	 * @param filename
	 * @return
	 */
	public boolean remove(int num, String filename) {
		try {
			// Open requested file to write to.
			File file = new File(filename);

			// if none exist, we'll create a brand new one
			if (!file.exists())
				file.createNewFile();

			// Initialize our file write with the file object's absolute file
			// path. We also set the second parameter to true because we want to
			// append to the file instead of overwriting it.
			FileWriter writer = new FileWriter(file.getAbsoluteFile(), true);
			for (int i = 0; i < num; i++) {

				// Remove first request from heap of queues and get patient info
				String patientInfo = this.remove();

				// write the patient's infor to the specified file
				writer.write(patientInfo + "\n");

				// print removed patient's info and the size of the heap after
				// the deletion
				System.out.println("Removed: " + patientInfo);
				System.out.println("Size: " + this.priorityHeap.getSize());
			}
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
