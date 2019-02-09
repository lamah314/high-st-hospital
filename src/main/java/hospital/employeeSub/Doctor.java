package hospital.employeeSub;

import hospital.Employee;
import hospital.Patient;
import hospital.interfaces.CareForPatients;

public class Doctor extends Employee implements CareForPatients{

	String specialty;
	
	public Object getSpecialty() {
		return specialty;
	}
	
	public Doctor(String name, int ID) {
		super(name,ID);
		super.payForDoctor();
	}
	
	public void specialtyHeart() {
		specialty = "Heart";
	}

	public void specialtyBrain() {
		specialty = "Brain";
	}

	public void specialtyFoot() {
		specialty = "Foot";
	}

	@Override
	public void drawBlood(Patient patient) {
		patient.lowerBloodLevel(1); // minimal loss of blood
	}

	@Override
	public void careForPatient(Patient patient) {
		patient.raiseHealthLevel(10); //Great quality of care
	}


}
