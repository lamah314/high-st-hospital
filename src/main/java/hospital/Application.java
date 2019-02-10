package hospital;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		boolean employeeMenu = false;
		boolean jobMenu = false;
		boolean employeeActionMenu = false;
		boolean PatientMenu;

		String jobPick = "";
		String actionInput;
		String PatientChoice;
		int IDPick;
		int fireEmployeeID;
		
		String userName;
		String specialtyPick;
		
		Doctor doctorAdd; //these are for when adding an employee
		Surgeon surgeonAdd;
		Receptionist receptionistAdd;
		Janitor janitorAdd;
		Nurse nurseAdd;

		/////////////////// starting hospital
		hospital.addEmployee(new Doctor("Phil", 900, "Heart"));
		hospital.addEmployee(new Surgeon("Who", 950, "Brain"));
		hospital.addEmployee(new Nurse("Joy", 800));
		hospital.addEmployee(new Receptionist("Kim", 100));
		hospital.addEmployee(new Janitor("Jesus", 200));

		hospital.addPatient(new Patient("Sam"));
		hospital.addPatient(new Patient("Ham"));
		hospital.addPatient(new Patient("Sham"));

		// set homeHospital for workers that affect it
		((Receptionist) hospital.getEmployee(100)).addHomeHospital(hospital);
		((Janitor) hospital.getEmployee(200)).addHomeHospital(hospital);

		// add all patients to the one nurse
		for (Patient patient : hospital.getPatientList().values()) {
			((Nurse) hospital.getEmployee(800)).addPatient(patient);
		}
		////////////////////////////////////
		System.out.println("Welcome to High Street Hospital TYCOON");
		System.out.println(
				"Your goal is to grow your hospital through taking care of incoming patients and hiring more employees!");
		System.out.println();
		System.out.println("Tips:");
		System.out.println(" • Every employee, if not busy, gets to perform one action per turn");
		System.out.println("   - A \"*\" in front of an employee means they still have a move available for that turn");
		System.out.println(" • Draw blood to identify any specialty needs of patients");
		System.out.println(" • Use Surgeons with the currect specialty to heal specialty needs of patients");
		System.out.println(" • Bring in more patients by seeking the help of your Receptionist(s)");
		System.out.println(" • More patients = More money => more employees => more patients");

		System.out.println("Press Enter to view your starting hospital");
		input.nextLine();
		hospital.displayAllStats();
		pauseOneSec();

		System.out.println();
		while (true) {
			System.out.println("***************");
			System.out.println("***Main Menu***");
			System.out.println("***************");
			System.out.println("Hospital Budget: " + hospital.getBudgetTotal() + "\t\tCost of Employees: "
					+ hospital.calculateTotalPay() + "\tExtra budget: " + hospital.extraBudget());
			System.out.println("Please use a number to make your choice.");
			System.out.println();
			System.out.println(" 1. Employees");
			System.out.println(" 2. Patients");
			System.out.println(" 3. Hospital");
			System.out.println(" 4. End Turn");
			System.out.println(" 5. Exit");
			userInput = input.nextLine();

			switch (userInput) {
			case "1":
				employeeMenu = true;
				while (employeeMenu) {
					hospital.displayEmployeeStats();
					System.out.println();
					hospital.displayNurseStatsNoJob();
					System.out.println();
					pauseOneSec();

					System.out.println(" 1. Select an Employee");
					System.out.println(" 2. Add an Employee");
					System.out.println(" 3. Fire an Employee");
					System.out.println(" 4. Go Back");
					userInput = input.nextLine();

					switch (userInput) {
					case "1":
						jobMenu = employeeActions(input, hospital, jobMenu, jobPick);
						break;
					case "2":
						System.out.println("What is the new employee's name?");
						userName = input.nextLine();
						
						System.out.println("Your budget is " + hospital.extraBudget());
						System.out.println("Which Employee Job?");
						System.out.println(" 1. Doctor");
						System.out.println(" 2. Surgeon");
						System.out.println(" 3. Receptionist");
						System.out.println(" 4. Janitor");
						System.out.println(" 5. Nurse");
						jobPick = input.nextLine();
						switch(jobPick) {
						case "1":
							System.out.println("What specialty?");
							System.out.println(" 1. Heart");
							System.out.println(" 2. Brain");
							System.out.println(" 3. Foot");
							specialtyPick = input.nextLine();
							switch(specialtyPick) {
							case "1":
								doctorAdd = new Doctor(userName, 900 + hospital.getDoctorIDCounter(), "Heart");
								break;
							case "2":
								doctorAdd = new Doctor(userName, 900 + hospital.getDoctorIDCounter(), "Brain");
								break;
							default:
								doctorAdd = new Doctor(userName, 900 + hospital.getDoctorIDCounter(), "Foot");
								break;
							}
							if (((Employee)doctorAdd).calculatePay() > hospital.extraBudget()) {
								System.out.println("You can't afford this Employee");
								System.out.println("Your budget: "+ hospital.extraBudget());
								System.out.println("Employee Pay: " + ((Employee)doctorAdd).calculatePay());
								pauseOneSec();
								pauseOneSec();
								break;
							} else {
								hospital.addEmployee(doctorAdd);
								hospital.addDoctorIDCounter();
								System.out.println("Welcome Doctor " + doctorAdd.getName() + " to the team!");
								pauseOneSec();
								break;
							}
						case "2":
							System.out.println("What specialty?");
							System.out.println(" 1. Heart");
							System.out.println(" 2. Brain");
							System.out.println(" 3. Foot");
							specialtyPick = input.nextLine();
							switch(specialtyPick) {
							case "1":
								surgeonAdd = new Surgeon(userName, 900 + hospital.getSurgeonIDCounter(), "Heart");
								break;
							case "2":
								surgeonAdd = new Surgeon(userName, 900 + hospital.getSurgeonIDCounter(), "Brain");
								break;
							default:
								surgeonAdd = new Surgeon(userName, 900 + hospital.getSurgeonIDCounter(), "Foot");
								break;
							}
							if (((Employee)surgeonAdd).calculatePay() > hospital.extraBudget()) {
								System.out.println("You can't afford this Employee");
								System.out.println("Your budget: "+ hospital.extraBudget());
								System.out.println("Employee Pay: " + ((Employee)surgeonAdd).calculatePay());
								pauseOneSec();
								pauseOneSec();
								break;
							} else {
								hospital.addEmployee(surgeonAdd);
								hospital.addSurgeonIDCounter();
								System.out.println("Welcome Surgeon" + surgeonAdd.getName() + " to the team!");
								pauseOneSec();
								break;
							}
						case "3":
							receptionistAdd = new Receptionist(userName, 100 + hospital.getReceptionistIDCounter());
							if (((Employee)receptionistAdd).calculatePay() > hospital.extraBudget()) {
								System.out.println("You can't afford this Employee");
								System.out.println("Your budget: "+ hospital.extraBudget());
								System.out.println("Employee Pay: " + ((Employee)receptionistAdd).calculatePay());
								pauseOneSec();
								pauseOneSec();
								break;
							} else {
								hospital.addEmployee(receptionistAdd);
								hospital.addSurgeonIDCounter();
								System.out.println("Welcome Receptionist" + receptionistAdd.getName() + " to the team!");
								pauseOneSec();
								break;
							}
						case "4":
							receptionistAdd = new Receptionist(userName, 100 + hospital.getReceptionistIDCounter());
							if (((Employee)receptionistAdd).calculatePay() > hospital.extraBudget()) {
								System.out.println("You can't afford this Employee");
								System.out.println("Your budget: "+ hospital.extraBudget());
								System.out.println("Employee Pay: " + ((Employee)receptionistAdd).calculatePay());
								pauseOneSec();
								pauseOneSec();
								break;
							} else {
								hospital.addEmployee(receptionistAdd);
								hospital.addSurgeonIDCounter();
								System.out.println("Welcome Receptionist" + receptionistAdd.getName() + " to the team!");
								pauseOneSec();
								break;
							}
						case "5":
							//Nurse
							break;
						}
						break;
					case "3":
						firePrompt(input, hospital);
						break;
					case "4":
						employeeMenu = false;
						break;
					}

				}
				break;
			case "2":
				hospital.displayPatientStats();
				System.out.println();
				pauseOneSec();
				///
				break;
			case "3":
				hospital.displayAllStats();
				System.out.println();
				System.out.println(" 1. Have all Nurses care for all patients");
				System.out.println(" 2. Send all Janitors out to sweep");
				System.out.println(" 3. Have all Receptionists call for patients");
				System.out.println(" 4. Calculate pay for employees");
				break;
			case "4":
				hospital.renewOneTurnAll();
				hospital.tickAll();
				break;
			case "5":
				System.out.println("Thanks for playing!");
				System.exit(0);
			}
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static boolean employeeActions(Scanner input, Hospital hospital, boolean jobMenu, String jobPick) {
		boolean employeeActionMenu;
		String actionInput;
		String PatientChoice;
		int IDPick;
		int cleaningTime;

		System.out.println("Which Employee Job?");
		System.out.println(" 1. Doctor");
		System.out.println(" 2. Surgeon");
		System.out.println(" 3. Receptionist");
		System.out.println(" 4. Janitor");
		System.out.println(" 5. Nurse");
		System.out.println(" 6. Go Back");
		jobPick = input.nextLine();
		boolean nurseMenu;

		switch (jobPick) {
		case "1":
			hospital.displayDoctorStatsNoJob();
			System.out.println();
			pauseOneSec();

			System.out.println("Please choose a Doctor by ID number");
			IDPick = Integer.parseInt(input.nextLine());

			if (!hospital.getEmployee(IDPick).getOneTurn()) {
				noTurnMessage(hospital, IDPick);
				break;
			}

			System.out.println("What would you like " + hospital.getEmployee(IDPick).getName() + " to do?");
			System.out.println(" 1. Draw blood from a patient");
			System.out.println(" 2. Give blood to a patient");
			System.out.println(" 3. Care for patient");
			System.out.println(" 4. Go back");
			actionInput = input.nextLine();

			switch (actionInput) {
			case "1":
				PatientChoice = hospital.choosePatient(input);
				((Doctor) hospital.getEmployee(IDPick)).drawBlood(hospital.getPatient(PatientChoice));
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			case "2":
				PatientChoice = hospital.choosePatient(input);
				((Doctor) hospital.getEmployee(IDPick)).giveBlood(hospital.getPatient(PatientChoice));
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			case "3":
				PatientChoice = hospital.choosePatient(input);
				((Doctor) hospital.getEmployee(IDPick)).careForPatient(hospital.getPatient(PatientChoice));
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			default:
				break;

			}
			break;
		case "2":
			hospital.displaySurgeonStatsNoJob();
			System.out.println();
			pauseOneSec();

			System.out.println("Please choose a Surgeon by ID number");
			IDPick = Integer.parseInt(input.nextLine());
			if (((Surgeon) hospital.getEmployee(IDPick)).getBusyStatus()) {
				System.out.println("Surgeon " + hospital.getEmployee(IDPick).getName() + " is busy operating.");
				System.out.println();
				pauseOneSec();
				break;
			}

			if (!hospital.getEmployee(IDPick).getOneTurn()) {
				noTurnMessage(hospital, IDPick);
				break;
			}

			System.out.println("What would you like " + hospital.getEmployee(IDPick).getName() + " to do?");
			System.out.println(" 1. Draw blood from a patient");
			System.out.println(" 2. Give blood to a patient");
			System.out.println(" 3. Care for patient");
			System.out.println(" 4. Operate on patient");
			System.out.println(" 5. Go back");
			actionInput = input.nextLine();

			switch (actionInput) {
			case "1":
				PatientChoice = hospital.choosePatient(input);
				((Surgeon) hospital.getEmployee(IDPick)).drawBlood(hospital.getPatient(PatientChoice));
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			case "2":
				PatientChoice = hospital.choosePatient(input);
				((Surgeon) hospital.getEmployee(IDPick)).giveBlood(hospital.getPatient(PatientChoice));
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			case "3":
				PatientChoice = hospital.choosePatient(input);
				((Surgeon) hospital.getEmployee(IDPick)).careForPatient(hospital.getPatient(PatientChoice));
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			case "4":
				PatientChoice = hospital.choosePatient(input);
				((Surgeon) hospital.getEmployee(IDPick)).operate(hospital.getPatient(PatientChoice));
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			default:
				break;
			}

			break;
		case "3":
			hospital.displayReceptionistStatsNoJob();
			System.out.println();
			pauseOneSec();

			System.out.println("Please choose a Receptionist by ID number");
			IDPick = Integer.parseInt(input.nextLine());
			if (((Receptionist) hospital.getEmployee(IDPick)).getBusyStatus()) {
				System.out.println("Receptionist " + hospital.getEmployee(IDPick).getName() + " is on the phone.");
				System.out.println();
				pauseOneSec();
				break;
			}

			if (!hospital.getEmployee(IDPick).getOneTurn()) {
				noTurnMessage(hospital, IDPick);
				break;
			}

			System.out.println("What would you like " + hospital.getEmployee(IDPick).getName() + " to do?");
			System.out.println(" 1. File nails and chew gum");
			System.out.println(" 2. Call for new patient");
			System.out.println(" 3. Go back");
			actionInput = input.nextLine();

			switch (actionInput) {
			case "1":
				System.out.println("Nothing to see here...");
				((Receptionist) hospital.getEmployee(IDPick)).fileNailsAndChewGum();
				pauseOneSec();
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			case "2":
				((Receptionist) hospital.getEmployee(IDPick)).startPhoning();
				hospital.getEmployee(IDPick).doOneTurn();
				pauseOneSec();
				break;
			default:
				break;
			}
			break;
		case "4":
			hospital.displayJanitorStatsNoJob();
			System.out.println();
			pauseOneSec();

			System.out.println("Please choose a Janitor by ID number");
			IDPick = Integer.parseInt(input.nextLine());
			if (((Janitor) hospital.getEmployee(IDPick)).getBusyStatus()) {
				System.out.println("Janitor " + hospital.getEmployee(IDPick).getName() + " is sweeping.");
				System.out.println();
				pauseOneSec();
				break;
			}

			if (!hospital.getEmployee(IDPick).getOneTurn()) {
				noTurnMessage(hospital, IDPick);
				break;
			}

			System.out.println("What would you like " + hospital.getEmployee(IDPick).getName() + " to do?");
			System.out.println(" 1. Sweep the Hospital");
			System.out.println(" 2. Care for genetically-enhanced bats");
			System.out.println(" 3. Go back");
			actionInput = input.nextLine();

			switch (actionInput) {
			case "1":
				System.out.println("How long would you like to clean?");
				cleaningTime = Integer.parseInt(input.nextLine());
				((Janitor) hospital.getEmployee(IDPick)).cleanHospital(cleaningTime);
				pauseOneSec();
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			case "2":
				((Janitor) hospital.getEmployee(IDPick)).careForBats();
				pauseOneSec();
				pauseOneSec();
				hospital.getEmployee(IDPick).doOneTurn();
				break;
			default:
				break;
			}
			break;
		case "5":
			hospital.displayNurseStatsNoJob();
			System.out.println();
			pauseOneSec();

			System.out.println("Please choose a Nurse by ID number");
			IDPick = Integer.parseInt(input.nextLine());

			if (!hospital.getEmployee(IDPick).getOneTurn()) {
				noTurnMessage(hospital, IDPick);
				break;
			}

			System.out.println("What would you like " + hospital.getEmployee(IDPick).getName() + " to do?");
			System.out.println(" 1. Draw blood from a patient");
			System.out.println(" 2. Give blood to a patient");
			System.out.println(" 3. Care for patient");
			System.out.println(" 4. Add Patients (No Turn Penalty)");
			System.out.println(" 5. Remove Patients (No Turn Penalty)");
			System.out.println(" 6. Go back");
			actionInput = input.nextLine();
			nurseMenu = true;
			while (nurseMenu) {
				switch (actionInput) {
				case "1":
					PatientChoice = hospital.choosePatientNurse(input, ((Nurse) hospital.getEmployee(IDPick)));
					((Nurse) hospital.getEmployee(IDPick)).drawBlood(hospital.getPatient(PatientChoice));
					hospital.getEmployee(IDPick).doOneTurn();
					nurseMenu = false;
					break;
				case "2":
					PatientChoice = hospital.choosePatientNurse(input, ((Nurse) hospital.getEmployee(IDPick)));
					((Nurse) hospital.getEmployee(IDPick)).giveBlood(hospital.getPatient(PatientChoice));
					hospital.getEmployee(IDPick).doOneTurn();
					nurseMenu = false;
					break;
				case "3":
					PatientChoice = hospital.choosePatientAllNurse(input, ((Nurse) hospital.getEmployee(IDPick)));
					if (PatientChoice.equalsIgnoreCase("all")) {
						((Nurse) hospital.getEmployee(IDPick)).careForAllPatientsFromList();
					} else {
						((Nurse) hospital.getEmployee(IDPick)).careForPatient(hospital.getPatient(PatientChoice));
					}
					hospital.getEmployee(IDPick).doOneTurn();
					nurseMenu = false;
					break;
				case "4":
					List<Patient> additionalPatients = hospital.getPatientList().values().stream()
	                .filter(not(new HashSet<>(((Nurse) hospital.getEmployee(IDPick)).getPatientList())::contains))
	                .collect(Collectors.toList());
					
					PatientChoice = hospital.addPatientAllNurse(input, ((Nurse) hospital.getEmployee(IDPick)));
					if (PatientChoice.equals("")) {
						break;
					} else if (PatientChoice.equalsIgnoreCase("all")){
						for (Patient specificPatients: additionalPatients) {
							((Nurse) hospital.getEmployee(IDPick)).addPatient(hospital.getPatient(PatientChoice));
						}
					} else {
						((Nurse) hospital.getEmployee(IDPick)).addPatient(hospital.getPatient(PatientChoice));
					}
					pauseOneSec();
					break;
				case "5":
					PatientChoice = hospital.choosePatientNurse(input, ((Nurse) hospital.getEmployee(IDPick)));
					((Nurse) hospital.getEmployee(IDPick)).removePatient(hospital.getPatient(PatientChoice).getName());
					break;
				default:
					nurseMenu = false;
					break;
				}
			}
			break;
		default:
			jobMenu = false;
			break;
		}
		return jobMenu;
	}

	private static void noTurnMessage(Hospital hospital, int IDPick) {
		System.out.println(hospital.getEmployee(IDPick).getClass().getSimpleName() + " "
				+ hospital.getEmployee(IDPick).getName() + " already did something this turn.");
		System.out.println();
		pauseOneSec();
	}

	private static void firePrompt(Scanner input, Hospital hospital) {
		int fireEmployeeID;
		hospital.displayAllBasicEmployees();
		System.out.println();
		pauseOneSec();
		System.out.println("Enter ID of Employee to fire");
		fireEmployeeID = Integer.parseInt(input.nextLine());
		System.out.println(hospital.getEmployee(fireEmployeeID).getName() + ", the "
				+ hospital.getEmployee(fireEmployeeID).getClass().getSimpleName() + " has been fired.");
		hospital.fireEmployee(fireEmployeeID);
		// pause the game for 1 second to display output
		pauseOneSec();
	}

	private static void pauseOneSec() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static <T> Predicate<T> not(Predicate<T> predicate) {
	    return predicate.negate();
	}

}
