package hospital.employeeSub;

import java.util.Scanner;

import hospital.Employee;
import hospital.Hospital;
import hospital.Patient;
import hospital.interfaces.BusyStatus;
import hospital.interfaces.CareForPatients;

public class Receptionist extends Employee implements BusyStatus{

	private boolean onPhoneStatus;
	private int onPhoneCounter;
	private String patientName;
	private Scanner input = new Scanner(System.in); 
	private int phoningSkill;
	
	@Override
	public boolean getBusyStatus() {
		return onPhoneStatus;
	}
	
	@Override
	public String displayBusyStatus() {
		if (onPhoneStatus) {
			return "On Phone";
		} else {
			return "free";
		}
	}
	
	public int getPhoningSkill() {
		return phoningSkill;
	}

	public Receptionist(String name, int ID) {
		super(name, ID);
		super.payForReceptionist();
	}
	
	public void displayStats() {
		System.out.println(super.oneTurnIndicator() + "Receptionist\t" + getName()+"\t" + getID()+"\t\t\t" + displayBusyStatus());;
	}
	
	public void displayStatsNoJob() {
		System.out.println(super.oneTurnIndicator() + getName()+"\t" + getID()+"\t\t\t" + displayBusyStatus());;
	}
	
	public void onPhoneDuration(int time) {
		onPhoneCounter = time;
	}
	
	public void tick() {
		onPhoneCounter -= 1;
	}
	
	public void fileNailsAndChewGum() {
		skillz += 1;
	}
	
	public void startPhoning() {
		System.out.println("What would you like your patient's name to be?");
		patientName = input .nextLine();
		System.out.println(getName() + ": \"I'll get right on it!\"");
		toggleOnPhoneStatus();
		onPhoneDuration(Math.max(1, 5 - phoningSkill)); //duration is inversely related to Phoning skills
	}
	
	public void checkStopPhone(Hospital hospital, int time) {
		if (onPhoneCounter <= 0 && getBusyStatus() == true) {
			toggleOnPhoneStatus();
			hospital.addPatient(new Patient(patientName));
			System.out.println(getName() + " found a patient named " + patientName);
		}
	}
	
	
	@Override
	public String toString() {
		return (super.getID() + ": " + super.getName() + " is a Receptionist at High St. Hospital");
	}

	public void toggleOnPhoneStatus() { //will mark the arrival of new patients
		onPhoneStatus = !onPhoneStatus;
	}

}
