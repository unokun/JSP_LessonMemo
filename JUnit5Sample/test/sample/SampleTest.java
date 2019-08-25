package sample;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SampleTest {
	Sample sample;

	@BeforeEach
	public void init() {
		sample = new Sample();
	}

	@AfterEach
	public void teardown() {

	}
	@Test
	@DisplayName("足し算")
	public void testSum() {
		try {
			assertEquals(5, sample.sum(2,3));
		} catch (Exception e) {
			fail();
		}
	}

}
