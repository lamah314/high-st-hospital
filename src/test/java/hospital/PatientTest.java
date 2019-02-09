package hospital;

import org.junit.Assert;
import org.junit.Test;

public class PatientTest {

	@Test
	public void shouldCreatePatientObject() {
		Patient underPatient = new Patient("Sam");

		Assert.assertEquals("Sam", underPatient.getName());
	}
	
	@Test
	public void shouldHaveDefaultBloodLevelOf20() {
		Assert.assertEquals(20, Patient.getDefaultBloodLevel());
	}
	
	@Test
	public void shouldHaveDefaultHealthLevelOf10() {
		Assert.assertEquals(10, Patient.getDefaultHealthLevel());
	}
	
	@Test
	public void shouldHaveInitialBloodLevelOf20() {
		Patient underPatient = new Patient("Sam");

		Assert.assertEquals(20, underPatient.getBloodLevel());
	}
	
	@Test
	public void shouldHaveInitialHealthLevelOf10() {
		Patient underPatient = new Patient("Sam");

		Assert.assertEquals(10, underPatient.getHealthLevel());
	}
	
	@Test
	public void shouldLowerBloodLevelFrom10to9() {
		Patient underPatient = new Patient("Sam");
		int initialBlood = underPatient.getBloodLevel();
		underPatient.lowerBloodLevel(1);
		int finalBlood = underPatient.getBloodLevel();
		Assert.assertEquals(initialBlood - 1, finalBlood);
	}
	
	@Test
	public void shouldLowerHealthLevelFrom10to9() {
		Patient underPatient = new Patient("Sam");
		int initialHealth = underPatient.getHealthLevel();
		underPatient.lowerHealthLevel(1);
		int finalHealth = underPatient.getHealthLevel();
		Assert.assertEquals(initialHealth - 1, finalHealth);
	}
	
	@Test
	public void shouldRaiseBloodLevelFrom10to11() {
		Patient underPatient = new Patient("Sam");
		int initialBlood = underPatient.getBloodLevel();
		underPatient.raiseBloodLevel(1);
		int finalBlood = underPatient.getBloodLevel();
		Assert.assertEquals(initialBlood + 1, finalBlood);
	}
	
	@Test
	public void shouldRaiseHealthLevelFrom10to11() {
		Patient underPatient = new Patient("Sam");
		int initialHealth = underPatient.getHealthLevel();
		underPatient.raiseHealthLevel(1);
		int finalHealth = underPatient.getHealthLevel();
		Assert.assertEquals(initialHealth + 1, finalHealth);
	}
	
}


