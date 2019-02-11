package hospital;

import org.junit.Assert;
import org.junit.Test;

import hospital.employeeSub.Doctor;
import hospital.employeeSub.Janitor;
import hospital.employeeSub.Nurse;
import hospital.employeeSub.Receptionist;
import hospital.employeeSub.doctorSub.Surgeon;
import hospital.employeeSub.janitorSub.VampireJanitor;

public class EmployeeTest {

	//************Subclasses****************//
	@Test
	public void shouldCreateEmployeeObjectUsingDoctor() {
		Employee underEmployee = new Doctor("Phil", 123);

		Assert.assertEquals("Phil", underEmployee.getName());
	}
	
	@Test
	public void shouldCreateEmployeeObjectUsingNurse() {
		Employee underEmployee = new Nurse("Phil", 123);

		Assert.assertEquals("Phil", underEmployee.getName());
	}
	
	@Test
	public void shouldCreateEmployeeObjectUsingSurgeon() {
		Employee underEmployee = new Surgeon("Phil", 123);

		Assert.assertEquals("Phil", underEmployee.getName());
	}
	
	@Test
	public void shouldCreateEmployeeObjectUsingReceptionist() {
		Employee underEmployee = new Receptionist("Phil", 123);

		Assert.assertEquals("Phil", underEmployee.getName());
	}
	
	@Test
	public void shouldCreateEmployeeObjectUsingJanitor() {
		Employee underEmployee = new Janitor("Phil", 123);

		Assert.assertEquals("Phil", underEmployee.getName());
	}
	
	@Test
	public void shouldCreateEmployeeObjectUsingVampireJanitor() {
		Employee underEmployee = new VampireJanitor("Phil", 123);

		Assert.assertEquals("Phil", underEmployee.getName());
	}
	
	//************Check Properties****************//
	
		//***ID***//
	@Test
	public void shouldSetIDForDoctor() {
		Employee underEmployee = new Doctor("Phil",123);

		Assert.assertEquals(123, underEmployee.getID());
	}
	
	@Test
	public void shouldSetIDForNurse() {
		Employee underEmployee = new Nurse("Phil",123);

		Assert.assertEquals(123, underEmployee.getID());
	}
	
	   //***Pay***//
	
	@Test
	public void shouldCalculatePayforDoctor() {
		Employee underEmployee = new Doctor("Phil", 123);

		Assert.assertEquals(90000, underEmployee.calculatePay());
	}	
	
	@Test
	public void shouldCalculatePayforNurse() {
		Employee underEmployee = new Nurse("Phil", 123);

		Assert.assertEquals(50000, underEmployee.calculatePay());
	}	
	
	@Test
	public void shouldCalculatePayforSurgeon() {
		Employee underEmployee = new Surgeon("Phil", 123);

		Assert.assertEquals(120000, underEmployee.calculatePay());
	}	
	
	@Test
	public void shouldCalculatePayforReceptionist() {
		Employee underEmployee = new Receptionist("Phil", 123);

		Assert.assertEquals(45000, underEmployee.calculatePay());
	}
	
	@Test
	public void shouldCalculatePayforJanitor() {
		Employee underEmployee = new Janitor("Phil", 123);

		Assert.assertEquals(40000, underEmployee.calculatePay());
	}
	
		//***Doctor Specialty***//
	@Test
	public void shouldSetDoctorSpecialtyToHeart() {
		Doctor underEmployee = new Doctor("Phil", 123);
		underEmployee.specialtyHeart();
		Assert.assertEquals("Heart", underEmployee.getSpecialty());
	}
	
	@Test
	public void shouldSetDoctorSpecialtyToBrain() {
		Doctor underEmployee = new Doctor("Phil", 123);
		underEmployee.specialtyBrain();
		Assert.assertEquals("Brain", underEmployee.getSpecialty());
	}
	
	@Test
	public void shouldSetDoctorSpecialtyToFoot() {
		Doctor underEmployee = new Doctor("Phil", 123);
		underEmployee.specialtyFoot();
		Assert.assertEquals("Foot", underEmployee.getSpecialty());
	}
	
		//***Surgeon Operating***//
	@Test
	public void shouldCheckIfSurgeonIsOperating() {
		Surgeon underEmployee = new Surgeon("Phil", 123);
		Assert.assertEquals(false, underEmployee.getBusyStatus());
	}
	
	@Test
	public void shouldToggleOperatingStatus() {
		Surgeon underEmployee = new Surgeon("Phil", 123);
		underEmployee.toggleOperating();
		Assert.assertEquals(true, underEmployee.getBusyStatus());
		
		underEmployee.toggleOperating();
		Assert.assertEquals(false, underEmployee.getBusyStatus());	
	}
	
		//***Nurse collection of patients***//
	@Test
	public void shouldAddToPatientList() {
		Nurse underPatient = new Nurse("Phil", 123);
		underPatient.addPatient(new Patient("Danny"));
		Assert.assertEquals(true, underPatient.checkIfAPatientIsOnList("Danny"));
	}
	
	@Test
	public void shouldRemovePatientFromPatientList() {
		Nurse underEmployee = new Nurse("Phil", 123);
		underEmployee.addPatient(new Patient("Danny"));
		underEmployee.removePatient("Danny");
		Assert.assertEquals(false, underEmployee.checkIfAPatientIsOnList("Danny"));
	}
	
		//***Receptionist On Phone***//
	@Test
	public void shouldCheckIfReceptionistIsOnPhone() {
		Receptionist underEmployee = new Receptionist("Phil", 123);
		Assert.assertEquals(false, underEmployee.getBusyStatus());
	}
	
	@Test
	public void shouldTogglebusyStatus() {
		Receptionist underEmployee = new Receptionist("Phil", 123);
		underEmployee.toggleOnPhoneStatus();
		Assert.assertEquals(true, underEmployee.getBusyStatus());
		
		underEmployee.toggleOnPhoneStatus();
		Assert.assertEquals(false, underEmployee.getBusyStatus());	
	}	
	
		//***Janitor is Sweeping***//
	@Test
	public void shouldCheckIfJanitorIsSweeping() {
		Janitor underEmployee = new Janitor("Phil", 123);
		Assert.assertEquals(false, underEmployee.getBusyStatus());
	}
	
	@Test
	public void shouldToggleSweeping() {
		Receptionist underEmployee = new Receptionist("Phil", 123);
		underEmployee.toggleOnPhoneStatus();
		Assert.assertEquals(true, underEmployee.getBusyStatus());
		
		underEmployee.toggleOnPhoneStatus();
		Assert.assertEquals(false, underEmployee.getBusyStatus());	
	}	
	
	//**************Caring for patients**********************//
	@Test
	public void shouldDraw1BloodUnitByDoctor() {
		Doctor underEmployee = new Doctor("Phil", 123);
		Patient underPatient = new Patient("Sam");
		int initialBlood = underPatient.getBloodLevel();
		underEmployee.drawBlood(underPatient);
		int finalBlood = underPatient.getBloodLevel();
		Assert.assertEquals(initialBlood - 1, finalBlood);
	}
	
	@Test
	public void shouldDraw2BloodUnitsByNurse() {
		Nurse underEmployee = new Nurse("Phil", 123);
		Patient underPatient = new Patient("Sam");
		int initialBlood = underPatient.getBloodLevel();
		underEmployee.drawBlood(underPatient);
		int finalBlood = underPatient.getBloodLevel();
		Assert.assertEquals(initialBlood - 2, finalBlood);
	}
	
	@Test
	public void shouldDraw15BloodUnitsByVampireJanitor() {
		VampireJanitor underEmployee = new VampireJanitor("Phil", 123);
		Patient underPatient = new Patient("Sam");
		int initialBlood = underPatient.getBloodLevel();
		underEmployee.drawBlood(underPatient);
		int finalBlood = underPatient.getBloodLevel();
		Assert.assertEquals(initialBlood - 15, finalBlood);
	}
	
}
