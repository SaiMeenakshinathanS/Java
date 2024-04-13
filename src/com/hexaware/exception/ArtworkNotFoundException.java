package com.hexaware.exception;

public class ArtworkNotFoundException extends RuntimeException{
	//Throw this exception when user enters an invalid artworkId which does not exist in the database
	public ArtworkNotFoundException(String message) {
		super(message);
	}
}
