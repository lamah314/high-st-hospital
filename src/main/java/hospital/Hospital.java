package hospital;

import java.util.HashMap;

import virtualpet.VirtualPet;

public class Hospital {

	private int Cleanliness = 100;

	private HashMap<Integer, Employee> employeeList = new HashMap<Integer, Employee>();
	private HashMap<String, Patient> patientList = new HashMap<String, Patient>();

	public int getCleanliness() {
		return Cleanliness;
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
	
	public Hospital() {

	}

	public void cleanHospital(int amount) {
		Cleanliness += amount;
	}
	
	public void addEmployee(Employee EmployeeToAdd) {
		employeeList.put(EmployeeToAdd.getID(), EmployeeToAdd);
	}

	public void fireEmployee(Integer IDofEmployee) {
		employeeList.remove(IDofEmployee);
	}
	
	public void displayEmployeeStats() {
		for (Employee specificEmployee : employeeList.values()) {
			specificEmployee.displayStats();
		}
	}
	
	public void displayPatientStats() {
		for (Patient specificPatient : patientList.values()) {
			specificPatient.displayStats();

		}
	}
	
}
