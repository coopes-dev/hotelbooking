package database;
import java.util.Vector;

public class Patient extends PersonMultipleAddress {
	
	private int id;
	private String nhsNumber;		// MHS unique identifier
	private BloodGroup bloodGroup;
	private Vector <Doctor> doctorList=new Vector<Doctor>(); // Doctor's assigned to the Patient
	
	public Patient(String surname, String forename1, String forename2, DateOfBirth dateOfBirth,Doctor doctor) {
		super(surname, forename1, forename2, dateOfBirth);
		addDoctor(doctor);
	}

	public String getNhsNumber() {
		return nhsNumber;
	}

	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber = nhsNumber;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Vector<Doctor> getDoctorList() {
		return doctorList;
	}
	
	public void removeDoctor(Doctor doctor) {
		if (doctorList.size()==1) { // if only 1 Doctor assigned to Patient enforce limit of at least 1 Doctor per patient
			// Not allowed to remove the last Patient
			throw new IllegalStateException("Patient must have at least 1 Doctor");
		}
		Vector <Doctor> removeList = new Vector<Doctor>();
		synchronized (doctorList) {
			doctorList.forEach((doctorInList) -> {
				if (doctor.equals(doctorInList)) {
					removeList.add(doctorInList);
				}
			});
		}
		doctorList.removeAll(removeList);
		
	}
	
	public void addDoctor(Doctor doctor) {
		doctorList.add(doctor);		// add in doctor to Patients list		
	}
	
	
	public boolean equals(Patient patient) {
		if (!super.equals(patient)) { // failed Person rquals so must be false already
			return(false);
		}
		if (!nhsNumber.equals(patient.nhsNumber)) {
			return(false);
		}
		return(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
