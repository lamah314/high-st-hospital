package hospital.interfaces;

import hospital.Patient;

public interface CareForPatients {

	public void drawBlood(Patient patient);
	
	public void giveBlood(Patient patient); //only needed for emergencies
	
	public void careForPatient(Patient patient);
	
}
