package hospital.employeeSub.doctorSub;

import hospital.Patient;
import hospital.employeeSub.Doctor;
import hospital.interfaces.BusyStatus;
import hospital.interfaces.CareForPatients;

public class Surgeon extends Doctor implements BusyStatus, CareForPatients{
	
	private boolean operatingStatus; //default is false
	private int operatingCounter;
	private Patient operatingPatient;
	private String formatName = "%-16s%s%n";
	
	@Override
	public boolean getBusyStatus() {
		return operatingStatus;
	}
	
	@Override
	public String displayBusyStatus() {
		if (operatingStatus) {
			return "Is Operating";
		} else {
			return "free";
		}
	}
	
	public Surgeon(String name, int ID) {
		super(name, ID);
		super.payForSurgeon();
	}
	
	public Surgeon(String name, int ID, String specialty) {
		super(name, ID, specialty);
		super.payForSurgeon();
	}

	public void displayStats() {
		System.out.printf(formatName, super.oneTurnIndicator() + "Surgeon" , getName()+"\t" + getID()+"\t" + getSpecialty()+"\t\t" + displayBusyStatus());;
	}
	
	public void displayStatsNoJob() {
		System.out.println(super.oneTurnIndicator() + getName()+"\t" + getID()+"\t" + getSpecialty()+"\t\t" + displayBusyStatus());;
	}
	
	public void toggleOperating() {
		operatingStatus = !operatingStatus;
	}
	
	public void operate(Patient patient) {
		if (((String) super.getSpecialty()).equalsIgnoreCase(patient.getSpecialtyNeed())){
			startOperating(3);
		} else {
			startOperating(10);
		}
		operatingPatient = patient;
		
	}
	
	public void operatingDuration(int time) {
		operatingCounter = time;
	}
	
	@Override
	public void tick() {
		operatingCounter -= 1;
	}
	
	public void startOperating(int time) {
		toggleOperating();
		operatingDuration(time);
	}
	
	public void checkFree() {
		if (operatingCounter <= 0 && getBusyStatus() == true) {
			toggleOperating();
			operatingPatient.healSpecialty();
			System.out.println(getName() + " is done operating on " + operatingPatient.getName() + ". It was a success!");
		}
	}
	
	@Override
	public String toString() {
		return (super.getID() + ": " + super.getName() + " is a Surgeon at High St. Hospital");
	}
	
	@Override
	public void careForPatient(Patient patient) {
		patient.raiseHealthLevel(7); //Busy surgeons know how to care, but not as good as Doctors
	}

}
