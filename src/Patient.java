/**
 * Represents a patient with their name, age, zip and priority value
 * 
 * @author jamesarama
 * 
 * @param <T>
 */
public class Patient<T> implements Comparable<Patient<T>> {
	/**
	 * @property String name - Name of patient
	 */
	private String name;
	/**
	 * @property int age - Age of patient
	 */
	private int age;
	/**
	 * @propert String zip - Zip code of patient
	 */
	private String zip;
	/**
	 * @property int priority - Priority value of patient
	 */
	private int priority;

	/**
	 * Default Constructor
	 */
	public Patient() {
		this.setAge(0);
		this.setName("");
		this.setZip("");
	}

	/**
	 * sets the name of our patient
	 * 
	 * @param String
	 *            _name
	 */
	public void setName(String _name) {
		this.name = _name;
	}

	/**
	 * Sets the age of our patient
	 * 
	 * @param _age
	 */
	public void setAge(int _age) {
		this.age = _age;
	}

	/**
	 * Sets the zip code of our patient
	 * 
	 * @param String
	 *            _zip
	 */
	public void setZip(String _zip) {
		this.zip = _zip;
	}

	/**
	 * Sets the priority value of our patient
	 * 
	 * @param int zpop - the population of patients in the system in the zipcode
	 *        of request
	 * @param int tpop - the total population of patients in the system
	 */
	public void setPriority(int zpop, int tpop) {
		this.priority = (int) (Math.abs(35 - this.age) / 5)
				+ ((zpop / tpop) * 10);
	}

	/**
	 * Gets patient's name
	 * 
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets patient's age
	 * 
	 * @return int age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Gets patient's zip code
	 * 
	 * @return String zip
	 */
	public String getZip() {
		return this.zip;
	}

	/**
	 * Gets patients priority value
	 * 
	 * @return int priority
	 */
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Our comparator to compare other patients based on their priority
	 * 
	 * @param Patient patient
	 * @return int 1|0|-1
	 */
	public int compareTo(Patient<T> patient) {
		if (this.priority > patient.priority)
			return 1;
		else if (this.priority < patient.priority)
			return -1;
		return 0;
	}

}
