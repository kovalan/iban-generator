package com.lendico.iban;


public class BbanFormat {
	
	 private final BbanFormatField[] bbanItems;

	 public BbanFormat(final BbanFormatField... items) {
		 this.bbanItems = items;
	 }
	 
	 public BbanFormatField[] getBbanItems() {
		 return this.bbanItems;
	 }

}
