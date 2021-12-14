package ru.smolina.weather.exception;

public class InvalidTempValueException extends RuntimeException{
	public InvalidTempValueException(String message) {
		super(message);
	}
}
