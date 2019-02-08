package hospital.employeeSub;

import hospital.Employee;
import hospital.Patient;
import hospital.interactions.BusyStatus;
import hospital.interactions.CareForPatients;

public class Janitor extends Employee implements BusyStatus, CareForPatients{

	private boolean isSweeping;
	
	@Override
	public boolean getBusyStatus() {
		return isSweeping;
	}
	
	public Janitor(String name, int ID) {
		super(name, ID);
		super.payForJanitor();
	}

	public void toggleOnPhoneStatus() {
		isSweeping = !isSweeping;
	}
	
	@Override
	public void drawBlood(Patient patient) {
		patient.lowerBloodLevel(4);
	}

	@Override
	public void careForPatient(Patient patient) {
		patient.raiseHealthLevel(1); //Janitors do not know how to care for patients. But they do know how to care for floors. 
	}
}
