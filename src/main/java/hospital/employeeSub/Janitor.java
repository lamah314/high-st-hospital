package hospital.employeeSub;

import hospital.Employee;
import hospital.Patient;
import hospital.interfaces.BusyStatus;
import hospital.interfaces.CareForPatients;
import hospital.interfaces.Cleaner;

public class Janitor extends Employee implements BusyStatus, Cleaner{

	private boolean isSweeping;
	
	@Override
	public boolean getBusyStatus() {
		return isSweeping;
	}
	
	public Janitor(String name, int ID) {
		super(name, ID);
		super.payForJanitor();
	}

	public void toggleIsSweepingStatus() {
		isSweeping = !isSweeping;
	}
	
	public void sweepHospital() {
		
	}
}
