package vacsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VacSys {
	@SuppressWarnings("rawtypes")
	private VacSysHeap queue;
	private int tpop;
	private ArrayList<String> zipcodes;
	private Map<Integer, ArrayList<Patient>> sortedPatients;

	/**
	 * Calculates the population at a given zipcode
	 * 
	 * @param String
	 *            zipcode
	 * @return int population
	 */
	private int getZipPopulation(String zipcode) {
		int population = 0;
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
		queue = new VacSysHeap<ArrayList<Patient>>();
		tpop = 0;
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
		queue = new VacSysHeap<ArrayList<Patient>>();
		tpop = 0;
		zipcodes = new ArrayList<String>();
		sortedPatients = new HashMap<Integer, ArrayList<Patient>>();

		try {
			FileReader file = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(file);
			String line;

			// Extract data from file
			while ((line = buffer.readLine()) != null) {

				String[] information = line.split(",");
				Patient patientInLine = new Patient();

				patientInLine.setName(information[0]);
				patientInLine.setAge(Integer.parseInt(information[1].trim()));
				patientInLine.setZip(information[2]);
				zipcodes.add(information[2]);
				int zpop = this.getZipPopulation(information[2]);
				tpop++;
				patientInLine.setPriority(zpop, zpop);

				int tempPriority = patientInLine.getPriority();
				
				// create array list for patients based on our priority
				if (!this.sortedPatients.containsKey(tempPriority))
					this.sortedPatients.put(tempPriority,
							new ArrayList<Patient>());

				this.sortedPatients.get(tempPriority).add(patientInLine);

			}
			file.close();

			tpop = zipcodes.size();

			// Prepare our patients for so that we can add them to the queue
			for (int priority : this.sortedPatients.keySet()) {
				ArrayList<Patient> patients = (ArrayList<Patient>) sortedPatients
						.get(priority);
				// sort our patients based on their priorities
				// Collections.sort(patients);
				this.queue.enqueue(patients);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds a new request from the system
	 * 
	 * @param name
	 * @param age
	 * @param zip
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean insert(String name, int age, String zip) {
		Patient patient = new Patient();
		patient.setName(name);
		patient.setAge(age);
		patient.setZip(zip);

		int zpop = this.getZipPopulation(zip);
		patient.setPriority(zpop, this.tpop);

		// create array list for patients based on our priority
		if (!this.sortedPatients.containsKey(patient.getPriority()))
			this.sortedPatients.put(patient.getPriority(),
					new ArrayList<Patient>());
		
		// add the new patient to the line of patients
		this.sortedPatients.get(patient.getPriority()).add(patient);

		// Prepare our patients for so that we can add them to the queue
		for (int priority : this.sortedPatients.keySet()) {
			ArrayList<Patient> patients = (ArrayList<Patient>) sortedPatients
					.get(priority);
			// sort our patients based on their priorities
			// Collections.sort(patients);
			this.queue.enqueue(patients);
			this.sortedPatients.remove(patients);
		}

		return true;
	}

	/**
	 * Removes the next request from the system
	 * 
	 * @return
	 */
	public String remove() {
		Patient patient = (Patient) this.queue.dequeue();
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
			File file = new File(filename);
			if (!file.exists())
				file.createNewFile();

			FileWriter writer = new FileWriter(file.getAbsoluteFile(), true);
			for (int i = 0; i < num; i++) {
				String patientInfo = this.remove();
				writer.write(patientInfo + "\n");

				System.out.println("Removed: " + patientInfo);
				System.out.println("Size: " + this.queue.getSize());
			}
			writer.close();

		} catch (Exception e) {
		}
		return false;
	}
}
