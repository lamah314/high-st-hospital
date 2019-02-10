package hospital;

import java.util.HashMap;

import hospital.employeeSub.Doctor;
import hospital.employeeSub.Janitor;
import hospital.employeeSub.Nurse;
import hospital.employeeSub.Receptionist;
import hospital.employeeSub.doctorSub.Surgeon;

public class Hospital {

	private int cleanliness;
	private int budgetTotal;

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

		displayNurseStats();
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

	public void displayNurseStats() {
		System.out.println("Nurses");
		System.out.println("Name\t" + "ID\t" + "Patients");
		for (Employee specificEmployee : employeeList.values()) {
			if (specificEmployee instanceof Nurse) {
				((Nurse) specificEmployee).displayStats();
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

}
