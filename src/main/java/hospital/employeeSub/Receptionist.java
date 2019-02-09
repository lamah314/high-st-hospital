package hospital.employeeSub;

import hospital.Employee;
import hospital.Patient;
import hospital.interfaces.BusyStatus;
import hospital.interfaces.CareForPatients;

public class Receptionist extends Employee implements BusyStatus{

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

}
