package hospital.employeeSub;

import hospital.Employee;
import hospital.Patient;
import hospital.interactions.BusyStatus;
import hospital.interactions.CareForPatients;

public class Receptionist extends Employee implements BusyStatus, CareForPatients{

	private boolean onPhoneStatus;
	
	@Override
	public boolean getBusyStatus() {
		return onPhoneStatus;
	}

	public Receptionist(String name, int ID) {
		super(name, ID);
		super.payForReceptionist();
	}

	public void toggleOnPhoneStatus() {
		onPhoneStatus = !onPhoneStatus;
	}
	
	@Override
	public void drawBlood(Patient patient) {
		patient.lowerBloodLevel(4); //Not very good at drawing blood.
	}

	@Override
	public void careForPatient(Patient patient) {
		patient.raiseHealthLevel(1); //A nice pleasant voice is all they can offer. 
	}

}
