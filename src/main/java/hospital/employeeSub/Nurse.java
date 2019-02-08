package hospital.employeeSub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import hospital.Employee;
import hospital.Patient;
import hospital.interactions.CareForPatients;

public class Nurse extends Employee implements CareForPatients{

	private Collection<Patient> patientList = new ArrayList<Patient>();
	
	public Collection<Patient> getPatientList() {
		return patientList;
	}

	public Nurse(String name, int ID) {
		super(name, ID);
		super.payForNurse();
	}

	public void addNewPatient(String name) {
		patientList.add(new Patient(name));
	}
	
	public void addExistingPatient(Patient existingPatient) {
		patientList.add(existingPatient);
	}
	
	public void removePatient(String existingPatientName) {
		for (Patient patient : patientList) {
			if (existingPatientName.equalsIgnoreCase(patient.getName())) {
				patientList.remove(patient);
				return; //exits method before leaving for loop
			}
		} 
		
		System.out.println("There are no patients named " + existingPatientName);
	}
	
	public void displayPatientListNames() {
		for (Patient patient : patientList) {
			patient.displayStats();
		}
	}

	public boolean checkIfAPatientIsOnList(String name) {
		for (Patient patient : patientList) {
			if (name.equalsIgnoreCase(patient.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void drawBlood(Patient patient) {
		patient.lowerBloodLevel(2); //Long hours and careless mistakes
	}

	@Override
	public void careForPatient(Patient patient) {
		patient.raiseHealthLevel(11); //expertise in caring for Patients
	}

}

