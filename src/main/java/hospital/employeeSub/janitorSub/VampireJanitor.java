package hospital.employeeSub.janitorSub;

import hospital.Patient;
import hospital.employeeSub.Janitor;
import hospital.interfaces.BusyStatus;
import hospital.interfaces.CareForPatients;
import hospital.interfaces.Cleaner;

public class VampireJanitor extends Janitor implements BusyStatus, CareForPatients, Cleaner{
	
	public VampireJanitor(String name, int ID) {
		super(name, ID);
		super.payForJanitor();
	}
	
	public void tick() {
		super.tick();
		super.tick(); //Vampires do their sweeping duties twice as fast as humans
	}
	
	@Override
	public void drawBlood(Patient patient) {
		patient.lowerBloodLevel(15);
		patient.discoverSpecialty();
	}

	@Override
	public void giveBlood(Patient patient) {
		patient.raiseBloodLevel(1);
	}
	
	@Override
	public void careForPatient(Patient patient) {
		patient.lowerHealthLevel(5); //lowers Health because Vampires are not working at hospitals to help patients. They are there to suck blood. 
	}
	
	

}
