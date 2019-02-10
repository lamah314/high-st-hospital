package hospital.employeeSub;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import hospital.Employee;
import hospital.Patient;
import hospital.interfaces.CareForPatients;

public class Doctor extends Employee implements CareForPatients{
	
	private String specialty;
	
	public String getSpecialty() {
		return specialty;
	}
	
	public Doctor(String name, int ID) {
		super(name,ID);
		super.payForDoctor();
		specialty = randomlyAssignSpecialty();
	}
	
	public Doctor(String name, int ID, String specialty) {
		
		super(name,ID);
		super.payForDoctor();
		if (specialty.equalsIgnoreCase("Heart")){
			this.specialty = "Heart";
		} else if (specialty.equalsIgnoreCase("Brain")){
			this.specialty = "Brain";
		} else if (specialty.equalsIgnoreCase("Foot")){
			this.specialty = "Foot";
		}  
	}

	public void displayStats() {
		System.out.println(super.oneTurnIndicator() + "Doctor\t\t" + getName()+"\t" + getID()+"\t" + getSpecialty());;
	}
	
	public void displayStatsNoJob() {
		System.out.println(super.oneTurnIndicator() + getName()+"\t" + getID()+"\t" + getSpecialty());;
	}
	
	public void specialtyHeart() {
		specialty = "Heart";
	}

	public void specialtyBrain() {
		specialty = "Brain";
	}

	public void specialtyFoot() {
		specialty = "Foot";
	}
	
	@Override
	public String toString() {
		return (super.getID() + ": " + super.getName() + " is a Doctor at High St. Hospital");
	}

	@Override
	public void drawBlood(Patient patient) {
		patient.lowerBloodLevel(1); // minimal loss of blood
		patient.discoverSpecialty();
	}

	@Override
	public void giveBlood(Patient patient) {
		patient.raiseBloodLevel(1);
	}
	
	@Override
	public void careForPatient(Patient patient) {
		patient.raiseHealthLevel(10); //Great quality of care
	}
	

	
	
	
	
	
	
	

	public String randomlyAssignSpecialty() {
		List<String> givenList = Arrays.asList("Heart", "Brain", "Foot"); //half of patients will need a specialist Doctor to help them 
		Random rand = new Random();
	    String randomSpecialty = givenList.get(rand.nextInt(givenList.size()));
	    return randomSpecialty;
	}

}
