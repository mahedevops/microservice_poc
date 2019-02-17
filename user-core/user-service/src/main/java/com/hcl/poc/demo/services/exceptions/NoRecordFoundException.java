package com.hcl.poc.demo.services.exceptions;

public class NoRecordFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	public NoRecordFoundException(String message) {
		super(message);
	}
	public NoRecordFoundException() {
	}
}