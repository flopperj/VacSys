package vacsys;

/**
 * Represents a patient with their name, age, zip and priority value
 * 
 * @author jamesarama
 */
public class Patient implements Comparable<Patient> {
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
		this.priority = 0;
	}

	/**
	 * sets the name of our patient
	 * 
	 * @public
	 * @param _name
	 */
	public void setName(String _name) {
		this.name = _name;
	}

	/**
	 * Sets the age of our patient
	 * 
	 * @public
	 * @param _age
	 */
	public void setAge(int _age) {
		this.age = _age;
	}

	/**
	 * Sets the zip code of our patient
	 * 
	 * @public
	 * @param _zip
	 */
	public void setZip(String _zip) {
		this.zip = _zip;
	}

	/**
	 * Sets the priority value of our patient
	 * 
	 * @public
	 * @param zpop
	 *            - the population of patients in the system in the zipcode of
	 *            request
	 * @param tpop
	 *            - the total population of patients in the system
	 */
	public void setPriority(int zpop, int tpop) {
		this.priority = (int) (Math.abs(35 - this.age) / 5)
				+ ((zpop / tpop) * 10);
	}

	/**
	 * Gets patient's name
	 * 
	 * @public
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets patient's age
	 * 
	 * @public
	 * @return age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Gets patient's zip code
	 * 
	 * @public
	 * @return zip
	 */
	public String getZipCode() {
		return this.zip;
	}

	/**
	 * Gets patients priority value
	 * 
	 * @public
	 * @return priority
	 */
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Return string representation of patient with all their info
	 * 
	 * @public
	 * @return String
	 */
	public String toString() {
		return "{ Name: " + this.name + ", Age: " + this.age + ", ZipCode: "
				+ this.zip + ", Priority: " + this.priority + " }";
	}

	/**
	 * Our comparator to compare other patients based on their priority
	 * 
	 * @public
	 * @param patient
	 * @return 1|0|-1
	 */
	public int compareTo(Patient patient) {
		if (this.priority > patient.priority)
			return 1;
		else if (this.priority < patient.priority)
			return -1;
		else if (this.priority == patient.priority
				&& this.name == patient.getName()
				&& this.age == patient.getAge() && this.zip == patient.getZipCode())
			return 0;

		return 0;
	}

}
