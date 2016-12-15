package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rocketDomain.RateDomainModel;

public class rate_test {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDomainModel() {
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		assertTrue(rates.get(0) instanceof RateDomainModel);
	}

}
