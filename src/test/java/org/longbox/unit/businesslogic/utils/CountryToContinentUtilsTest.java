package org.longbox.unit.businesslogic.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.CountryToContinentUtils;

class CountryToContinentUtilsTest {

	@Test
	void testContinentNA() {
		assertEquals(CountryToContinentUtils.getNA(), CountryToContinentUtils.getContinent("Canada"));
	}
	
	@Test
	void testContinentSA() {
		assertEquals(CountryToContinentUtils.getSA(), CountryToContinentUtils.getContinent("Brazil"));
	}
	
	@Test
	void testContinentEU() {
		assertEquals(CountryToContinentUtils.getEU(), CountryToContinentUtils.getContinent("Poland"));
	}
	
	@Test
	void testContinentAS() {
		assertEquals(CountryToContinentUtils.getAS(), CountryToContinentUtils.getContinent("Japan"));
	}
	
	@Test
	void testContinentAF() {
		assertEquals(CountryToContinentUtils.getAF(), CountryToContinentUtils.getContinent("Egypt"));
	}
	
	@Test
	void testContinentOC() {
		assertEquals(CountryToContinentUtils.getOC(), CountryToContinentUtils.getContinent("Australia"));
	}
	
	@Test
	void testContinentAT() {
		assertEquals(CountryToContinentUtils.getAT(), CountryToContinentUtils.getContinent("Antarctica"));
	}
	
	@Test
	void testCountryDNE() {
		// Auto assign non existing countries to North_America
		assertEquals(CountryToContinentUtils.getNA(), CountryToContinentUtils.getContinent("Kastol Catholic Republic"));
	}

}
