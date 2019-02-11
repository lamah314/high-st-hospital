package hospital;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hospital.employeeSub.Doctor;
import hospital.employeeSub.Janitor;
import hospital.employeeSub.Nurse;

public class HospitalTest {

	Hospital underHospital;
	
	@Before
	public void initalObjects() {
	underHospital = new Hospital();
	underHospital.addEmployee(new Doctor("Phil", 901));
	underHospital.addEmployee(new Janitor("Jesus", 101));
	underHospital.addEmployee(new Nurse("Joy", 801));

	underHospital.addPatient(new Patient("Buster"));
	underHospital.addPatient(new Patient("Guster"));
	}
	
	@Test
	public void shouldHaveInitalCleanlinessOf100() {
		Assert.assertEquals(100, underHospital.getCleanliness());
	}
	
	@Test
	public void shouldIncreaseCleanlinessBy5() {
		int initialClean = underHospital.getCleanliness();
		underHospital.cleanHospital(5);
		int finalClean = underHospital.getCleanliness();
		Assert.assertEquals(initialClean + 5, finalClean);
	}
	
	@Test
	public void shouldIncreaseEmployeeSizeBy1() {
		int initialEmployeeSize = underHospital.getEmployeeListSize();
		underHospital.addEmployee(new Nurse("Happy", 802));
		int finalEmployeeSize = underHospital.getEmployeeListSize();
		Assert.assertEquals(initialEmployeeSize + 1, finalEmployeeSize);
	}
	
	@Test
	public void shouldIncreasePatientSizeBy1() {
		int initialPatientSize = underHospital.getPatientListSize();
		underHospital.addPatient(new Patient("Fuster"));
		int finalPatientSize = underHospital.getPatientListSize();
		Assert.assertEquals(initialPatientSize + 1, finalPatientSize);
	}
	
	@Test
	public void shouldGetEmployee() {
		int initialPatientSize = underHospital.getPatientListSize();
		underHospital.addPatient(new Patient("Fuster"));
		int finalPatientSize = underHospital.getPatientListSize();
		Assert.assertEquals(initialPatientSize + 1, finalPatientSize);
	}
	
}
