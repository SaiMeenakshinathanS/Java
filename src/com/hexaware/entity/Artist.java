package com.hexaware.entity;
import java.time.LocalDate;

//Create Entity
//Attributes Declaration
public class Artist {
	private int artistId;
	private String name;
	private String biography;
	private LocalDate birthDate;
	private String nationality;
	private String website;
	private String contactInformation;
	
	//Default Constructor
	public Artist() {
		artistId=0;
		name="default name";
		biography="Famous painter";
		birthDate=LocalDate.now();
		nationality="Indian";
		website="https://www.default_name.com";
		contactInformation="9808954632";
	}
	
	//Parameterized Constructor 
	public Artist(int artistId, String name, String biography, LocalDate birthDate, String nationality, String website,
			String contactInformation) {
		super();
		this.artistId = artistId;
		this.name = name;
		this.biography = biography;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.website = website;
		this.contactInformation = contactInformation;
	}
	
	//Getters and Setters
	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	//Print all the above attributes using toString()
	public String toString() {
		return "Artist Id: "+artistId+
				"Name: "+name+
				"Biography: "+biography+
				"Birth Date: "+birthDate+
				"Nationality: "+nationality+
				"Website: "+website+
				"Contact Information: "+contactInformation;
	}
}
