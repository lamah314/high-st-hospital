package hospital.employeeSub;

import hospital.Employee;
import hospital.Patient;
import hospital.interactions.BusyStatus;
import hospital.interactions.CareForPatients;

public class Janitor extends Employee implements BusyStatus{

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
	
}
