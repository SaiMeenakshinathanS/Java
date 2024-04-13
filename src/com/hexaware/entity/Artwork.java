package com.hexaware.entity;

import java.time.LocalDate;

//Create entity
//Attributes Declaration
public class Artwork {
	private int artworkId;
	private String title;
	private String description;
	private LocalDate creationDate;
	private String medium;
	private String imageURL;
	
	//Default Constructor
	public Artwork() {
		artworkId=0;
		title="default title";
		description="this is an artwork";
		creationDate=LocalDate.now();
		medium="default medium";
		imageURL="image.jpg";
	}

	//Parameterized Constructor
	public Artwork(int artworkId, String title, String description, LocalDate creationDate, String medium,
			String imageURL) {
		super();
		this.artworkId = artworkId;
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
		this.medium = medium;
		this.imageURL = imageURL;
	}

	public Artwork(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
	//Getters and Setters
	public int getArtworkId() {
		return artworkId;
	}

	public void setArtworkId(int artworkId) {
		this.artworkId = artworkId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	//Print all the above attributes using toString()
	public 	String toString() {
		return "Artwork ID: "+artworkId+
				"Title: "+title+
				"Description: "+description+
				"Creation Date: "+creationDate+
				"Medium: "+medium+
				"Image URL: "+imageURL;
	}
}
