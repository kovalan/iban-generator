package com.lendico.iban;

import java.util.Random;

import com.lendico.iban.BbanFormatField.BbanFieldType;

/**
 * Contains utilities for Iban generator.
 * @author kvenkate
 *
 */
public class IbanUtil {
	
	static final char[] alphabetCharSeq;
	static final char[] numberCharSeq;
	
	private static final Random randomGenerator = new Random();
	
	static {
		StringBuilder charBuilder = new StringBuilder();
	    for (char ch = 'A'; ch <= 'Z'; ++ch) {
	    	charBuilder.append(ch);
	    }
	    alphabetCharSeq = charBuilder.toString().toCharArray();
	    
	    charBuilder = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch) {
	    	charBuilder.append(ch);
	    }
	    numberCharSeq = charBuilder.toString().toCharArray();
	    
	    
	}

	/**
	 * Return numeric value of a given string.
	 * @param str
	 * @return
	 */
	public static String getNumericValue(String str) {
		StringBuffer charSequence = new StringBuffer();
		for(int i=0; i <str.length(); i++) {
			charSequence.append(Character.getNumericValue(str.charAt(i)));
		}
		
		return charSequence.toString();
	}
	
	/**
	 * Return mod 97 of a big number in string format
	 * @param number
	 * @return
	 */
	public static int mod97(String number) {
		
		int modValue = mod97(Integer.parseInt(number.substring(0, 9)));
		
		int startIndex = 9;
		int endIndex = 0;
		String nextSeq = null;
		
		while(startIndex < number.length()) {
			
			endIndex = number.length() < (startIndex + 7) ? number.length() : (startIndex+7);
			nextSeq = modValue+number.substring(startIndex, endIndex);
			modValue = mod97(Integer.parseInt(nextSeq));
			startIndex = endIndex;
			
		}
		return modValue;
	}
	
	/**
	 * Return mod 97 of an integer
	 * @param number
	 * @return
	 */
	public static int mod97(Integer num) {
		return num % 97;
	}
	
	/**
	 * Get random character string of given length and type as in the format field
	 * @param BbanFormatField
	 * @return
	 */	
	public static String getRandomChars(BbanFormatField field) {
		if(field.getType() == BbanFieldType.a) {
			return getRandomAlphabets(field.getLength());
		} else {
			return getRandomDigits(field.getLength());
		}
	}
	
	/**
	 * Get random numeric string of given length.
	 * @param len
	 * @return
	 */
	public static String getRandomDigits(int len) {
		
		StringBuffer digitBuffer = new StringBuffer();
		for(int i=0; i < len; i++) {
			digitBuffer.append(numberCharSeq[randomGenerator.nextInt(9)]);
		}
		return digitBuffer.toString();
	}
	
	/**
	 * Get random alphabetic string of given length.
	 * @param len
	 * @return
	 */
	public static String getRandomAlphabets(int len) {
		
		StringBuffer alphabetBuffer = new StringBuffer();
		for(int i=0; i < len; i++) {
			alphabetBuffer.append(alphabetCharSeq[randomGenerator.nextInt(26)]);
		}
		return alphabetBuffer.toString();
	}
}
 