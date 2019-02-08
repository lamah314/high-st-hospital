package hospital.employeeSub.janitorSub;

import hospital.Patient;
import hospital.employeeSub.Janitor;
import hospital.interactions.BusyStatus;
import hospital.interactions.CareForPatients;

public class VampireJanitor extends Janitor implements BusyStatus, CareForPatients{
	
	public VampireJanitor(String name, int ID) {
		super(name, ID);
		super.payForJanitor();
	}
	
	@Override
	public void drawBlood(Patient patient) {
		patient.lowerBloodLevel(15);
	}

	@Override
	public void careForPatient(Patient patient) {
		patient.lowerHealthLevel(5); //lowers Health because Vampires are not working at hospitals to help patients. They are there to suck blood. 
	}

}
