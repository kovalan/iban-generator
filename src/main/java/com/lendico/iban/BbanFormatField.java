package com.lendico.iban;

public class BbanFormatField {

	private final BbanFieldName name;
    private final BbanFieldType type;
    private final int length;
    
    public BbanFormatField(BbanFieldName name, BbanFieldType type, int len) {
    	this.name = name;
    	this.type = type;
    	this.length = len;
    }
    
    public enum BbanFieldName {
    	BANK_CODE,
    	BANK_BRANCH_CODE,
    	ACCOUNT_NUMBER
    }
    
    public enum BbanFieldType {
    	a,
    	n
    }
    
    public BbanFieldName getName() {
    	return this.name;
    }
    
    public BbanFieldType getType() {
    	return this.type;
    }
    
    public int getLength() {
    	return this.length;
    }
    
}
