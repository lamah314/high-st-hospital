package hospital.employeeSub.doctorSub;

import hospital.Patient;
import hospital.employeeSub.Doctor;
import hospital.interfaces.BusyStatus;
import hospital.interfaces.CareForPatients;

public class Surgeon extends Doctor implements BusyStatus, CareForPatients{
	
	private boolean operatingStatus; //default is false

	@Override
	public boolean getBusyStatus() {
		return operatingStatus;
	}
	
	public Surgeon(String name, int ID) {
		super(name, ID);
		super.payForSurgeon();
	}

	public void toggleOperating() {
		operatingStatus = !operatingStatus;
	}
	
	@Override
	public void careForPatient(Patient patient) {
		patient.raiseHealthLevel(7); //Busy surgeons know how to care, but not as good as Doctors
	}

}
