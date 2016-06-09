package com.lendico.iban;

public class UnsupportedCountryException extends IbanException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1679722829664111399L;
	private String countryCode;

	public UnsupportedCountryException(String msg) {
		super(msg);
	}
	
	public UnsupportedCountryException(String countryCode, String msg) {
		super(msg);
		this.countryCode = countryCode;
	}
	
	public String getCountryCode() {
		return this.countryCode;
	}

}
