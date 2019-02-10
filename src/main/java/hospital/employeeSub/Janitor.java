package hospital.employeeSub;

import hospital.Employee;
import hospital.Hospital;
import hospital.Patient;
import hospital.interfaces.BusyStatus;
import hospital.interfaces.CareForPatients;
import hospital.interfaces.Cleaner;

public class Janitor extends Employee implements BusyStatus, Cleaner{

	private boolean isSweeping;
	private int sweepCounter;
	
	@Override
	public boolean getBusyStatus() {
		return isSweeping;
	}
	
	public Janitor(String name, int ID) {
		super(name, ID);
		super.payForJanitor();
	}
	
	public void displayStats() {
		System.out.println("Janitor\t\t" + getName()+"\t" + getID()+"\t\t\t" + getBusyStatus());;
	}
	
	@Override
	public String toString() {
		return (super.getID() + ": " + super.getName() + " is a Janitor at High St. Hospital");
	}

	public void toggleIsSweepingStatus() {
		isSweeping = !isSweeping;
	}
	
	public void sweepDuration(int time) {
		sweepCounter = time;
	}
	
	public void sweepTick(int time) {
		sweepCounter -= time;
	}
	
	public void startSweeping(int time) {
		toggleIsSweepingStatus();
		sweepDuration(time);
	}
	
	public void checkStopSweeping(int time) {
		if (sweepCounter <= 0 && getBusyStatus() == true) {
			toggleIsSweepingStatus();
		}
	}
	
	@Override
	public void CleanHospital(Hospital hospital, int amount) {
		hospital.cleanHospital(amount);
		startSweeping(Math.round(amount/2)); //it takes a janitor x amount of time to clean the hospital 2x units
	}
}
