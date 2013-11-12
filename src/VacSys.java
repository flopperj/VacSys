import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VacSys {
	public VacSysHeap queue;
	private int tpop;
	private ArrayList<String> zipcodes;

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
		queue = new VacSysHeap();
		tpop = 0;
		zipcodes = new ArrayList<String>();
	}

	/**
	 * Overloaded constructor to create system loaded with requests from the
	 * batch file given by the filename
	 * 
	 * @param filename
	 */
	public VacSys(String filename) throws IOException {
		queue = new VacSysHeap();
		tpop = 0;
		zipcodes = new ArrayList<String>();

		ArrayList<String[]> patients = new ArrayList<String[]>();

		try {
			FileReader file = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(file);
			String line;

			// Extract data from file
			while ((line = buffer.readLine()) != null) {
				
				String[] information = line.split(",");

				// String name = information[0];
				// int age = Integer.parseInt(information[1]);
				String zipcode = information[2];
				zipcodes.add(zipcode);
				patients.add(information);
			}
			file.close();
			
			tpop = zipcodes.size();

			// Prepare our patients for so that we can add them to the queue
			for (String[] information : patients) {
				String name = information[0];
				int age = Integer.parseInt(information[1].trim());
				String zip = information[2];
				this.insert(name, age, zip);
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
	public boolean insert(String name, int age, String zip) {
		Patient patient = new Patient();
		patient.setName(name);
		patient.setAge(age);
		patient.setZip(zip);

		int zpop = this.getZipPopulation(zip);
		patient.setPriority(zpop, this.tpop);

		this.queue.enqueue(patient);
		
		// sort our heap
		Collections.sort(this.queue.getHeap());
		
		return true;
	}

	/**
	 * Removes the next request from the system
	 * 
	 * @return
	 */
	public String remove() {
		Patient patient = this.queue.dequeue();
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
		try{
			File file = new File(filename);
			if(!file.exists())
				file.createNewFile();
			
			
			FileWriter writer = new FileWriter(file.getAbsoluteFile(), true);			
			for (int i = 0; i < num; i++){
				String patientInfo = this.remove();
				writer.write(patientInfo + "\n");
				
				System.out.println("Removed: " + patientInfo);
			}
			writer.close();
			
		}catch(Exception e){
		}
		return false;
	}
}
