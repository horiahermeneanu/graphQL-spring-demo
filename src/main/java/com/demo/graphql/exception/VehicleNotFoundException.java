package com.demo.graphql.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class VehicleNotFoundException extends RuntimeException implements GraphQLError {

	@Override
	public synchronized Throwable getCause() {
		return null;
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return null;
	}

	private static final long serialVersionUID = -4152181936998340679L;
	private final String invalidField;

	public VehicleNotFoundException(String message, String invalidField) {
		super(message);
		this.invalidField = invalidField;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return null;
	}

	@Override
	public Map<String, Object> getExtensions() {
		return Collections.singletonMap("invalidField", invalidField);
	}
}