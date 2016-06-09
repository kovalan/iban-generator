package com.lendico.iban;

public enum CountryCode {
	
	 AT,
	 DE,
	 NL;
	 
	 public static CountryCode getCountryCode(final String code) {
		 try {
	            return Enum.valueOf(CountryCode.class, code);
	        } catch (IllegalArgumentException e) {
	            return null;
	        }
	 }
}
