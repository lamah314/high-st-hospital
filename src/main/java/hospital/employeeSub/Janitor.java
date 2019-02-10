package hospital.employeeSub;

import hospital.Employee;
import hospital.Hospital;
import hospital.Patient;
import hospital.employeeSub.janitorSub.VampireJanitor;
import hospital.interfaces.BusyStatus;
import hospital.interfaces.CareForPatients;
import hospital.interfaces.Cleaner;

public class Janitor extends Employee implements BusyStatus, Cleaner{

	private boolean isSweeping;
	private int sweepCounter;
	private String formatName = "%-16s%s%n";
	private Hospital homeHospital;
	double probabilityOfInfection = .5;
	
	@Override
	public boolean getBusyStatus() {
		return isSweeping;
	}
	
	@Override
	public String displayBusyStatus() {
		if (isSweeping) {
			return "Is Sweeping";
		} else {
			return "free";
		}
	}
	
	public Hospital getHomeHospital() {
		return homeHospital;
	}
	
	public Janitor(String name, int ID) {
		super(name, ID);
		super.payForJanitor();
	}
	
	public void displayStats() {
		System.out.printf(formatName, super.oneTurnIndicator() + "Janitor",getName()+"\t" + getID()+"\t\t\t" + displayBusyStatus());;
	}
	
	public void displayStatsNoJob() {
		System.out.println(super.oneTurnIndicator() + getName()+"\t" + getID()+"\t\t\t" + displayBusyStatus());;
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
	
	@Override
	public void tick() {
		if(isSweeping) {
		homeHospital.cleanHospital(2);
		}
		sweepCounter -= 1;
	}
	
	public void checkFree() {
		if (sweepCounter <= 0 && getBusyStatus() == true) {
			toggleIsSweepingStatus();
			System.out.println("Janitor " + getName() + " finished sweeping.");
		}
	}
	
	public void addHomeHospital(Hospital hospital) {
		homeHospital = hospital;
	}
	
	@Override
	public void cleanHospital(int duration) {
		toggleIsSweepingStatus();
		sweepDuration(duration); //it takes a janitor x amount of time to clean the hospital 2x units
	}

	public void careForBats() {
		if (Math.random() < probabilityOfInfection) {
		System.out.println(getName() + " lost focus due to Mary Jane.");
		System.out.println("A bat flew down and bit his neck!");
		System.out.println("He begins to sweep in a daze");
		homeHospital.addEmployee(new VampireJanitor(this.getName(), this.getID()));
		((VampireJanitor) homeHospital.getEmployee(this.getID())).addHomeHospital(this.getHomeHospital());
		((VampireJanitor) homeHospital.getEmployee(this.getID())).cleanHospital(6);
		} else {
			System.out.println(getName() + " cared for the bats and went home for the day.");
			System.out.println("Nothing significant happened.");
			probabilityOfInfection += .1;
		}
	}
}
