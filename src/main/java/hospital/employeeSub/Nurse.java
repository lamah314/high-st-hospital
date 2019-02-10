package hospital.employeeSub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import hospital.Employee;
import hospital.Patient;
import hospital.interfaces.CareForPatients;

public class Nurse extends Employee implements CareForPatients {

	private Collection<Patient> patientList = new ArrayList<Patient>();

	public Collection<Patient> getPatientList() {
		return patientList;
	}

	public Nurse(String name, int ID) {
		super(name, ID);
		super.payForNurse();
	}

	//Does not align with Hospital patient list
//	public void addNewPatient(String name) {
//		patientList.add(new Patient(name));
//	}

	public void displayStats() {
		System.out.println(getName()+"\t" + getID()+"\t" + displayPatientListNamesString());;
	}
	
	public void addPatient(Patient existingPatient) {
		patientList.add(existingPatient);
	}

	public void removePatient(String existingPatientName) {
		for (Patient patient : patientList) {
			if (existingPatientName.equalsIgnoreCase(patient.getName())) {
				patientList.remove(patient);
				return; // exits method before leaving for loop
			}
		}

		System.out.println("There are no patients named " + existingPatientName);
	}

	public void displayPatientListNamesList() {
		for (Patient patient : patientList) {
			System.out.println(patient.toString());
		}
	}
	
	public String displayPatientListNamesString() {
		boolean notFirstCounter = false;
		String patientListString = "";
		for (Patient patient : patientList) {
			if (notFirstCounter) {
				patientListString += ", ";
			}
			patientListString += patient.getName();
			notFirstCounter = true;
		}
		return patientListString;
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
		patient.lowerBloodLevel(2); // Long hours and careless mistakes
		patient.discoverSpecialty();
	}
	
	@Override
	public void giveBlood(Patient patient) {
		patient.raiseBloodLevel(1);
	}

	@Override
	public void careForPatient(Patient patient) {
		patient.raiseHealthLevel(12); // expertise in caring for Patients
	}

	public void careForAllPatientsFromList() {
		for (Patient patient : patientList) {
			patient.raiseHealthLevel(Math.round(12 / patientList.size())); // Care is spread out amongst Patients
		}
	}
	
	@Override
	public String toString() {
		return (super.getID() + ": " + super.getName() + " is a Nurse at High St. Hospital");
	}

}
