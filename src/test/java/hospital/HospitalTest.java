package hospital;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hospital.employeeSub.Doctor;

public class HospitalTest {

	Hospital underHospital = new Hospital();
	
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
	
}
