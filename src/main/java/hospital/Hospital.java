package hospital;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import hospital.employeeSub.Doctor;
import hospital.employeeSub.Janitor;
import hospital.employeeSub.Nurse;
import hospital.employeeSub.Receptionist;
import hospital.employeeSub.doctorSub.Surgeon;
import hospital.employeeSub.janitorSub.VampireJanitor;
import hospital.interfaces.hasTick;

public class Hospital {

	private int cleanliness;
	private int budgetTotal;
	
	private int doctorIDCounter = 1; //Y2K Style baby
	private int surgeonIDCounter = 1;
	private int receptionistIDCounter = 1;
	private int janitorIDCounter = 1;
	private int nurseIDCounter = 1;
	
	private String formatName = "%-16s%s%n"; //counter + name gives varying tab distances, so fixed with format

	private HashMap<Integer, Employee> employeeList = new HashMap<Integer, Employee>();
	private HashMap<String, Patient> patientList = new HashMap<String, Patient>();

	public int getCleanliness() {
		return cleanliness;
	}

	public HashMap<Integer, Employee> getEmployeeList() {
		return employeeList;
	}

	public HashMap<String, Patient> getPatientList() {
		return patientList;
	}

	public Employee getEmployee(Integer ID) {
		return employeeList.get(ID);
	}

	public Patient getPatient(String Name) {
		return patientList.get(Name);
	}

	public int getEmployeeListSize() {
		return employeeList.size();
	}

	public int getPatientListSize() {
		return patientList.size();
	}

	public int getBudgetTotal() {
		return budgetTotal;
	}

	public int getDoctorIDCounter() {
		return doctorIDCounter;
	}
	
	public int getSurgeonIDCounter() {
		return surgeonIDCounter;
	}
	
	public int getReceptionistIDCounter() {
		return receptionistIDCounter;
	}
	
	public int getJanitorIDCounter() {
		return janitorIDCounter;
	}
	
	public int getNurseIDCounter() {
		return nurseIDCounter;
	}

	
	public Hospital() {
		cleanliness = 30;
		budgetTotal = 500000;
	}

	public void dirtyHospital(int amount) {
		cleanliness -= amount;
	}
	
	public void cleanHospital(int amount) {
		cleanliness += amount;
	}

	public void addEmployee(Employee EmployeeToAdd) {
		employeeList.put(EmployeeToAdd.getID(), EmployeeToAdd);
	}

	public void fireEmployee(Integer IDofEmployee) {
		employeeList.remove(IDofEmployee);
	}

	public void addPatient(Patient PatientToAdd) {
		patientList.put(PatientToAdd.getName(), PatientToAdd);
	}
	
	public void removePatient(Patient PatientToAdd) {
		patientList.remove(PatientToAdd.getName());
	}
	

	public int calculateTotalPay() {
		int totalPay = 0;
		for (Employee specificEmployee : employeeList.values()) {
			totalPay += specificEmployee.calculatePay();
		}
		return totalPay;
	}
	
	public void addDoctorIDCounter() {
		doctorIDCounter += 1;
	}
	
	public void addSurgeonIDCounter() {
		surgeonIDCounter += 1;
	}
	
	public void addReceptionistIDCounter() {
		receptionistIDCounter += 1;
	}
	
	public void addJanitorIDCounter() {
		janitorIDCounter += 1;
	}
	
	public void addNurseIDCounter() {
		nurseIDCounter += 1;
	}

	public int extraBudget() {
		return (budgetTotal - calculateTotalPay());
	}

	public void displayAllStats() {
		displayEmployeeStats();
		System.out.println();

		displayNurseStatsNoJob();
		System.out.println();

		displayPatientStats();
	}

	public void displayEmployeeStats() {
		System.out.println("Employees");
		System.out.println("Job\t\t" + "Name\t" + "ID\t" + "Specialty\t" + "Busy");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee.getClass().getSimpleName().equalsIgnoreCase("Doctor")) { // avoid pulling surgeons
				((Doctor) specificEmployee).displayStats(); // no idea why surgeon pulls surgeon method when cast as
															// doctor
			}
		}
		for (Employee specificEmployee : employeeList.values()) { // display was done this way to group by occupation
			if (specificEmployee instanceof Surgeon) {
				((Surgeon) specificEmployee).displayStats();
			}
		}
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Receptionist) {
				((Receptionist) specificEmployee).displayStats();
			}
		}
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Janitor) {
				((Janitor) specificEmployee).displayStats();
			}
		}
	}

	// might need more of these
	public void displayDoctorStats() {
		System.out.println("Employees");
		System.out.println("Job\t\t" + "Name\t" + "ID\t" + "Specialty\t" + "Busy");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee.getClass().getSimpleName().equalsIgnoreCase("Doctor")) {
				((Doctor) specificEmployee).displayStats();
			}
		}
	}

	public void displayNurseStats() {
		System.out.println("Nurses");
		System.out.println("Name\t" + "ID\t" + "Patients");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Nurse) {
				((Nurse) specificEmployee).displayStats();
			}
		}
	}

	public void displayNurseStatsNoJob() {
		System.out.println("Nurses");
		System.out.println("Name\t" + "ID\t" + "Patients");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Nurse) {
				((Nurse) specificEmployee).displayStatsNoJob();
			}
		}
	}

	public void displayDoctorStatsNoJob() {
		System.out.println("Doctors");
		System.out.println("Name\t" + "ID\t" + "Specialty\t" + "Busy");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee.getClass().getSimpleName().equalsIgnoreCase("Doctor")) {
				((Doctor) specificEmployee).displayStatsNoJob();
			}
		}
	}

	public void displaySurgeonStatsNoJob() {
		System.out.println("Surgeons");
		System.out.println("Name\t" + "ID\t" + "Specialty\t" + "Busy");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee.getClass().getSimpleName().equalsIgnoreCase("Surgeon")) {
				((Surgeon) specificEmployee).displayStatsNoJob();
			}
		}
	}

	public void displayReceptionistStatsNoJob() {
		System.out.println("Receptionists");
		System.out.println("Name\t" + "ID\t" + "Specialty\t" + "Busy");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee.getClass().getSimpleName().equalsIgnoreCase("Receptionist")) {
				((Receptionist) specificEmployee).displayStatsNoJob();
			}
		}
	}

	public void displayJanitorStatsNoJob() {
		System.out.println("Janitors");
		System.out.println("Name\t" + "ID\t" + "Specialty\t" + "Busy");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee.getClass().getSimpleName().equalsIgnoreCase("Janitor")) {
				((Janitor) specificEmployee).displayStatsNoJob();
			}
		}
	}

	public void displayAllBasicEmployees() { // only name and ID
		System.out.println("Employees");
		System.out.println("Name\t\t" + "ID");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee.getClass().getSimpleName().equalsIgnoreCase("Doctor")) {
				((Employee) specificEmployee).displayBasicStats();
			}
		}
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Surgeon) {
				((Employee) specificEmployee).displayBasicStats();
			}
		}

		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Receptionist) {
				((Employee) specificEmployee).displayBasicStats();
			}
		}
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Janitor) {
				((Employee) specificEmployee).displayBasicStats();
			}
		}
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Nurse) {
				((Employee) specificEmployee).displayBasicStats();
			}
		}
	}

	public void displayPatientStats() {
		System.out.println("Patients");
		System.out.println("Name\t\t" + "Blood Level\t" + "Health Level\t" + "Specialty Need");
		for (Patient specificPatient : patientList.values()) {
			specificPatient.displayStats();

		}
	}

	public String choosePatient(Scanner input) {
		int counter;
		String userPatientChoice;
		HashMap<Integer, String> numberWithPatient = new HashMap<Integer, String>();

		counter = 1;
		System.out.println("Name\t\t" + "Blood Level\t" + "Health Level\t" + "Specialty Need");
		for (Patient specificPatient : patientList.values()) {
			System.out.printf(formatName, " " + counter + ". " + specificPatient.getName(),specificPatient.getBloodLevel()
					+ "\t\t" + specificPatient.getHealthLevel() + "\t\t" + specificPatient.getSpecialtyNeedDisplay());
			numberWithPatient.put(counter, specificPatient.getName());
			counter++;
		}
		System.out.println();
		System.out.println("Choose a Patient by number.");
		userPatientChoice = numberWithPatient.get(Integer.parseInt(input.nextLine()));

		return userPatientChoice;
	}
	
	public String choosePatientAll(Scanner input) {
		int counter;
		String userPatientChoice;
		HashMap<Integer, String> numberWithPatient = new HashMap<Integer, String>();

		counter = 1;
		System.out.println("Name\t\t" + "Blood Level\t" + "Health Level\t" + "Specialty Need");
		for (Patient specificPatient : patientList.values()) {
			System.out.printf(formatName, " " + counter + ". " + specificPatient.getName(),specificPatient.getBloodLevel()
					+ "\t\t" + specificPatient.getHealthLevel() + "\t\t" + specificPatient.getSpecialtyNeedDisplay());
			numberWithPatient.put(counter, specificPatient.getName());
			counter++;
		}
		System.out.println(" " + counter + ". All");
		numberWithPatient.put(counter, "All");

		System.out.println();
		System.out.println("Choose by number.");
		userPatientChoice = numberWithPatient.get(Integer.parseInt(input.nextLine()));

		return userPatientChoice;
	}
	
	public String choosePatientNurse(Scanner input, Nurse nurse) {
		int counter;
		String userPatientChoice;
		HashMap<Integer, String> numberWithPatient = new HashMap<Integer, String>();

		counter = 1;
		System.out.println("Name\t\t" + "Blood Level\t" + "Health Level\t" + "Specialty Need");
		for (Patient specificPatient : nurse.getPatientList()) {
			System.out.printf(formatName, " " + counter + ". " + specificPatient.getName(),specificPatient.getBloodLevel()
					+ "\t\t" + specificPatient.getHealthLevel() + "\t\t" + specificPatient.getSpecialtyNeedDisplay());
			numberWithPatient.put(counter, specificPatient.getName());
			counter++;
		}
		System.out.println();
		System.out.println("Choose a Patient by number.");
		userPatientChoice = numberWithPatient.get(Integer.parseInt(input.nextLine()));

		return userPatientChoice;
	}
	
	public String addPatientAllNurse(Scanner input, Nurse nurse) {
		int counter;
		String userPatientChoice;
		HashMap<Integer, String> numberWithPatient = new HashMap<Integer, String>();
		counter = 1;
		
		//grab all patients in hospital but not on nurse's list
		List<Patient> additionalPatients = patientList.values().stream()
                .filter(not(new HashSet<>(nurse.getPatientList())::contains))
                .collect(Collectors.toList());
		
		if (additionalPatients.size() <= 0) {
			System.out.println("Nurse " + nurse.getName() + " has all the " );
			return "";
		} else {
		System.out.println("Name\t\t" + "Blood Level\t" + "Health Level\t" + "Specialty Need");
		for (Patient specificPatient : additionalPatients) {
			System.out.printf(formatName, " " + counter + ". " + specificPatient.getName(),specificPatient.getBloodLevel()
					+ "\t\t" + specificPatient.getHealthLevel() + "\t\t" + specificPatient.getSpecialtyNeedDisplay());
			numberWithPatient.put(counter, specificPatient.getName());
			counter++;
		}
		System.out.println(" " + counter + ". All");
		numberWithPatient.put(counter, "All");
		
		System.out.println();
		System.out.println("Choose by number.");
		userPatientChoice = numberWithPatient.get(Integer.parseInt(input.nextLine()));

		return userPatientChoice;
		}
	}
	
	
	
	public String choosePatientAllNurse(Scanner input, Nurse nurse) {
		int counter;
		String userPatientChoice;
		HashMap<Integer, String> numberWithPatient = new HashMap<Integer, String>();

		counter = 1;
		System.out.println("Name\t\t" + "Blood Level\t" + "Health Level\t" + "Specialty Need");
		for (Patient specificPatient : nurse.getPatientList()) {
			System.out.printf(formatName, " " + counter + ". " + specificPatient.getName(),specificPatient.getBloodLevel()
					+ "\t\t" + specificPatient.getHealthLevel() + "\t\t" + specificPatient.getSpecialtyNeedDisplay());
			numberWithPatient.put(counter, specificPatient.getName());
			counter++;
		}
		System.out.println(" " + counter + ". All");
		numberWithPatient.put(counter, "All");

		System.out.println();
		System.out.println("Choose by number.");
		userPatientChoice = numberWithPatient.get(Integer.parseInt(input.nextLine()));

		return userPatientChoice;
	}
	
	public void renewOneTurnAll() {
		for (Employee specificEmployee : employeeList.values()) {
			specificEmployee.renewOneTurn();
		}
		
	}

	public void tickAll() {
		dirtyHospital(Math.round(getPatientListSize()/2 + getEmployeeListSize()/6));
		
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Surgeon) {
				((Surgeon) specificEmployee).tick();
				((Surgeon) specificEmployee).checkFree();
			}
		}

		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Receptionist) {
				((Receptionist) specificEmployee).tick();
				((Receptionist) specificEmployee).checkFree();
			}
		}
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Janitor) {
				((Janitor) specificEmployee).tick();
				((Janitor) specificEmployee).checkFree();
			}
		}
		
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof VampireJanitor) {
				((Janitor) specificEmployee).tick(); //doubled by being a subclass of Janitor already
				((Janitor) specificEmployee).checkFree();
			}
		}
		//trying to auto-cast to instantiated class...no luck
//		for (Employee specificEmployee : employeeList.values()) {
//			if (specificEmployee instanceof hasTick) {
//				((specificEmployee.getClass().getSimpleName()) specificEmployee).tick();
//			}
//		}
		
		for (Patient specificPatient : patientList.values()) {
			specificPatient.tick();
			specificPatient.deathCheck();
			
			specificPatient.deathWarning();
		}
	}
	
	public void removeDeadPatients() {
		List<Patient> toRemove = new ArrayList<Patient>();

		for (Patient specificPatient : patientList.values()) {
			if (specificPatient.getDeathFlag()) {
				toRemove.add(specificPatient);
			}
		}
		if (toRemove != null) {
			for (Patient deadNames : toRemove) {
				removePatient(deadNames);
			}
		}
	}
	
	//used to find difference between nurse patient list and hospital patient list
	private static <T> Predicate<T> not(Predicate<T> predicate) {
	    return predicate.negate();
	}		
	
}
