package com.lendico.iban;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generator class that generates random Iban for a given country.
 * Currently supported country codes are AT, DE, NL
 * @author kvenkate
 *
 */
public class IbanGenerator {
		
		private List<String> generatedIbans = new ArrayList<String>();
		
		private static final Random randomGenerator = new Random();
		private static final String DEFAULT_CHECK_DIGITS = "00";
		
		public IbanGenerator countryCode(String strCountryCode) {
			return this;
		}
		
		/**
		 * Generate a random Iban for a random country.
		 * Currently supported country codes are AT, DE, NL.
		 * @return
		 * @throws IbanException
		 */
		public synchronized String generate() throws IbanException {
			
			// Get a random country code, if the client hasn't specified any country code.
			CountryCode[] countryCodes = CountryCode.values();
			CountryCode countryCode = countryCodes[randomGenerator.nextInt(countryCodes.length)];
			
			return generate(countryCode);
		}
		
		/**
		 * Generate a random Iban for a given country code.
		 * Currently supported country codes are AT, DE, NL.
		 * @return
		 * @throws IbanException
		 */
		public synchronized String generate(String strCountryCode) throws IbanException {

			CountryCode countryCode = null;
			if (strCountryCode != null) {
				countryCode = CountryCode.getCountryCode(strCountryCode.toUpperCase());
			}
			return generate(countryCode);
		}
		
		/**
		 * Generate a random Iban for a given country code.
		 * @return
		 * @throws IbanException
		 */
		private String generate(CountryCode countryCode) throws IbanException {
			
			String ibanValue = null;
			
			// Retrieve the bban format structure for the country.
			BbanFormat bbanFormat = BbanFormatForCountries.getBbanFormatForCountry(countryCode);
			
			if (bbanFormat == null) {
				throw new UnsupportedCountryException(countryCode != null ? countryCode.toString() : "", "Iban generation is not supported for this country");
			}
					
			// Generate BBAN based on the bban format for the given country.
			String  bban = generateBban(bbanFormat);
			
			// Calculate the check digits
			String checkDigits = calculateCheckDigits(countryCode, bban);
			
			// Derive iban from country code, check digit and bban
			ibanValue = countryCode.name() + checkDigits + bban;
			
			// If the iban is already generated by the tool, re-generate again, till a new unique iban is generated.
			if (generatedIbans.contains(ibanValue)) {
				ibanValue = generate(countryCode);
			} else {
				generatedIbans.add(ibanValue);
			}
				
			return ibanValue;
		}
		
		
		/**
		 * Generate bban based on the format.
		 */
		private String generateBban(BbanFormat bbanFormat) {
			
			String bban = "";
			for (BbanFormatField field : bbanFormat.getBbanItems()) {
				bban +=IbanUtil.getRandomChars(field);
			}
			return bban;
		}
		
		/**
		 * Calculate check digits.
		 * @param countryCode
		 * @param bban
		 * @return
		 */
		private String calculateCheckDigits(CountryCode countryCode, String bban) {

			String numericValue = IbanUtil.getNumericValue(bban + countryCode.name() + DEFAULT_CHECK_DIGITS);
			int mod97 = IbanUtil.mod97(numericValue);
			int checkDigit = 98-mod97;
			return String.format("%02d", checkDigit);
		}
}