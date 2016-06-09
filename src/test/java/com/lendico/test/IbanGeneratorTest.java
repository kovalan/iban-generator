package com.lendico.test;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lendico.iban.IbanException;
import com.lendico.iban.IbanGenerator;
import com.lendico.iban.IbanUtil;
import com.lendico.iban.UnsupportedCountryException;

public class IbanGeneratorTest {

	IbanGenerator generator = null;
	
	@Before
	public void setUp() throws Exception {
		generator = new IbanGenerator();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testIbanGeneratorCreation() {
		assertNotNull(generator);
	}
	
	@Test
	public void testIbanNotNull() throws IbanException{
		assertNotNull(generator.generate());
	}
	
	@Test
	public void testUniqueIbanGeneration() throws IbanException{
		String iban1 = generator.generate();
		String iban2 = generator.generate();
		assertNotEquals(iban1, iban2);
	}
	
	
	@Test
	public void testValidIban() throws IbanException{
		
		String iban = generator.generate();
		String rearrangedIban = iban.substring(4) + iban.substring(0, 4);
		String numericIban = IbanUtil.getNumericValue(rearrangedIban);
		BigInteger ibanNum = new BigInteger(numericIban);
		BigInteger mod  = ibanNum.mod(new BigInteger("97"));
		assertTrue(mod.equals(new BigInteger("1")));
	}
	
	@Test(expected=UnsupportedCountryException.class)
	public void testUnsupportedCountry() throws IbanException{		
		generator.generate("XYZ");
	}
	
	@Test
	public void testIbanFormatForGermany()  throws IbanException{
		
		
		String iban = generator.generate("DE");
		assertEquals(22, iban.length());
		
		String formatForGermany = "^DE[0-9]{20}$";
		assertTrue(iban.matches(formatForGermany));
		
	}
	
	@Test
	public void testIbanFormatForAustria()  throws IbanException{
		
		String iban = generator.generate("AT");
		assertEquals(20, iban.length());
		
		String formatForAustria = "^AT[0-9]{18}$";
		assertTrue(iban.matches(formatForAustria));
		
	}
	
	
	@Test
	public void testIbanFormatForNetherlands()  throws IbanException{
		
		
		String iban = generator.generate("NL");
		assertEquals(18, iban.length());
		
		String formatForAustria = "^NL[0-9]{2}[A-Z]{4}[0-9]{10}$";
		assertTrue(iban.matches(formatForAustria));
		
	}
	
	
}
