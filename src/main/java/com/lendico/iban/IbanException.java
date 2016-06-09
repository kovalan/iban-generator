package com.lendico.iban;

public class IbanException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8925422122328163153L;

	public IbanException(final String msg) {
        super(msg);
    }

    public IbanException(final String msg, final Throwable ex) {
        super(msg, ex);
    }

    public IbanException(final Throwable ex) {
        super(ex);
    }
}
