package hospital;

import java.util.Scanner;

import hospital.employeeSub.Doctor;
import hospital.employeeSub.Janitor;
import hospital.employeeSub.Nurse;
import hospital.employeeSub.Receptionist;
import hospital.employeeSub.doctorSub.Surgeon;

public class Application {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Hospital hospital = new Hospital();
		String userInput;
		
		///////////////////starting hospital
		hospital.addEmployee((Employee) new Doctor("Phil", 901, "Heart"));
		hospital.addEmployee((Employee) new Surgeon("Who", 951, "Brain"));
		hospital.addEmployee((Employee) new Nurse("Joy", 801));
		hospital.addEmployee((Employee) new Receptionist("Kim", 101));
		hospital.addEmployee((Employee) new Janitor("Jesus", 201));
		
		hospital.addPatient(new Patient("Sam"));
		hospital.addPatient(new Patient("Ham"));
		hospital.addPatient(new Patient("Sham"));
		
		for (Patient patient : hospital.getPatientList().values()) {
		((Nurse)hospital.getEmployee(801)).addPatient(patient);
		}
		////////////////////////////////////
		System.out.println("Welcome to High Street Hospital TYCOON");
		System.out.println("Your goal is to grow your hospital through taking care of incoming patients and hiring more employees!");
		System.out.println("Press Enter to view your starting hospital");
		input.nextLine();
		hospital.displayAllStats();
		
		System.out.println();
		System.out.println("***************");
		System.out.println("***Main Menu***");
		System.out.println("***************");
		System.out.println("Hospital Budget: " + hospital.getBudgetTotal() + "\tCost of Employees: " + hospital.calculateTotalPay() + "\tExtra budget: " + hospital.extraBudget());
		System.out.println("Please use a number to make your choice.");
		System.out.println("1. Employees");
		System.out.println("2. Patients");
		System.out.println("3. Exit");
		userInput = input.nextLine();
		
		switch (userInput) {
		case "1":
			//more choices
			break;
		case "2":
			//more patient stuff
			break;
		case "3":
			System.exit(0);
		
		}
	}

}
