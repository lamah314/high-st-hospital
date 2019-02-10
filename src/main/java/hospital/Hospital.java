package hospital;

import java.util.HashMap;
import java.util.Scanner;

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

	public int calculateTotalPay() {
		int totalPay = 0;
		for (Employee specificEmployee : employeeList.values()) {
			totalPay += specificEmployee.calculatePay();
		}
		return totalPay;
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
		System.out.println("Choose a Patient by number.");
		userPatientChoice = numberWithPatient.get(Integer.parseInt(input.nextLine()));

		return userPatientChoice;
	}
	
	public void renewOneTurnAll() {
		for (Employee specificEmployee : employeeList.values()) {
			specificEmployee.renewOneTurn();
		}
		
	}

	public void tickAll() {
		dirtyHospital(Math.round(getPatientListSize()/3 + getEmployeeListSize()/5));
		
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Surgeon) {
				((Surgeon) specificEmployee).tick();
			}
		}

		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Receptionist) {
				((Receptionist) specificEmployee).tick();
			}
		}
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Janitor) {
				((Janitor) specificEmployee).tick();
			}
		}
		
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof VampireJanitor) {
				((Janitor) specificEmployee).tick(); //doubled by being a subclass of Janitor already
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
		}
	}
	
}
