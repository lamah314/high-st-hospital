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
	
	public void displayStats() {
		System.out.println("Receptionist\t" + getName()+"\t" + getID()+"\t\t\t" + getBusyStatus());;
	}
	
	
	@Override
	public String toString() {
		return (super.getID() + ": " + super.getName() + " is a Receptionist at High St. Hospital");
	}

	public void toggleOnPhoneStatus() { //will mark the arrival of new patients
		onPhoneStatus = !onPhoneStatus;
	}

}
