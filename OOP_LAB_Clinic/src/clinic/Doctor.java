package clinic;

import java.util.Map;
import java.util.TreeMap;

public class Doctor extends Patient {
	private int numBadge;
	private String spec;
	private Map<String,Patient> patients= new TreeMap<>();
	
	public Doctor(String first, String last, String ssn, int numBadge, String spec) {
		super(first, last, ssn);
		this.numBadge = numBadge;
		this.spec = spec;
	}

	/**
	 * @return the numBadge
	 */
	public int getNumBadge() {
		return numBadge;
	}

	/**
	 * @return the spec
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * @param numBadge the numBadge to set
	 */
	public void setNumBadge(int numBadge) {
		this.numBadge = numBadge;
	}

	/**
	 * @param spec the spec to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * @return the patients
	 */
	public Map<String, Patient> getPatients() {
		return patients;
	}
	
	public void addPatient(Patient p) {
		patients.put(p.getSsn(), p);
	}
	
}
