import java.util.Date;
import java.util.Vector;

public class Doctor extends PersonMultipleAddress {

	private Date qualificationDate;
	Vector<Patient> patientList = new Vector<Patient>(); // patients assigned to the Doctor		

	public Doctor(String surname, String forename1, String forename2, DateOfBirth dateOfBirth) {
		super(surname, forename1, forename2, dateOfBirth);	// chain to super class
	}
	
	
	public Date getQualificationDate() {
		return qualificationDate;
	}
	public void setQualificationDate(Date qualificationDate) {
		this.qualificationDate = qualificationDate;
	}

	
	public void addPatientToList(Patient patient) {
		patientList.add(patient);
		patient.addDoctor(this);	// reflect addition to the other list
	}
	
	public void removePatient(Patient patient) {
		Vector<Patient> removeList = new Vector<Patient>();
		synchronized (patientList) {
			patientList.forEach((patientInList) -> {
				if (patient.equals(patientInList)) {
					removeList.add(patientInList);
				}
			});
		}
		patientList.removeAll(removeList);
		// We now remove this Doctor from the associated Patient.. the other way round
		patient.removeDoctor(this);
	}
	

	public Vector<Patient> getPatientList() {
		return patientList;
	}

}
