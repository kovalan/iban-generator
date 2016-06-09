package com.lendico.iban;

import java.util.EnumMap;

import com.lendico.iban.BbanFormatField.BbanFieldName;
import com.lendico.iban.BbanFormatField.BbanFieldType;

public final class BbanFormatForCountries {
	private static final EnumMap<CountryCode, BbanFormat> formatForCountries = new EnumMap<CountryCode, BbanFormat>(CountryCode.class);
    
    static {
    	
    	// Austria
    	formatForCountries.put(CountryCode.AT, new BbanFormat(new BbanFormatField(BbanFieldName.BANK_CODE, BbanFieldType.n, 5), new BbanFormatField(BbanFieldName.ACCOUNT_NUMBER, BbanFieldType.n, 11)));
    	
    	// Germany
    	formatForCountries.put(CountryCode.DE, new BbanFormat(new BbanFormatField(BbanFieldName.BANK_BRANCH_CODE, BbanFieldType.n, 8), new BbanFormatField(BbanFieldName.ACCOUNT_NUMBER, BbanFieldType.n, 10)));
    	
    	// Netherlands
    	formatForCountries.put(CountryCode.NL, new BbanFormat(new BbanFormatField(BbanFieldName.BANK_CODE, BbanFieldType.a, 4), new BbanFormatField(BbanFieldName.BANK_CODE, BbanFieldType.n, 10)));
    }
    
    
    public static BbanFormat getBbanFormatForCountry(CountryCode code) {
    	return formatForCountries.get(code);
    }
}
