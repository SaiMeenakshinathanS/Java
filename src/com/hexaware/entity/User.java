package com.hexaware.entity;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

//Create entity
//Attributes Declaration
public class User {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String profilePicture;
	private List<Integer> favoriteArtworks;
	
	//Default Constructor
	public User() {
		userId=0;
		userName="default@01";
		password="def555@";
		email="default@gmail.com";
		firstName="default firstName";
		lastName="defalut lastName";
		dateOfBirth=LocalDate.now();
		profilePicture="def.jpg";
		favoriteArtworks=new ArrayList<Integer>();;
	}

	//Parameterized Constructor
	public User(int userId, String userName, String password, String email, String firstName, String lastName,
			LocalDate dateOfBirth, String profilePicture, List<Integer> favoriteArtworks) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.profilePicture = profilePicture;
		this.favoriteArtworks = favoriteArtworks;
	}
	
	public User(int userId, String userName, String password, String email, String firstName, String lastName,
			LocalDate dateOfBirth, String profilePicture) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.profilePicture = profilePicture;
	}

	//Getters and Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getProfilePicture() {
		return profilePicture;
	}
	
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public List<Integer> getFavoriteArtworks() {
		return favoriteArtworks;
	}

	public void setFavoriteArtworks(List<Integer> favoriteArtworks) {
		this.favoriteArtworks = favoriteArtworks;
	}
	
	//Print all the attributes using toString()
	public String toString() {
		return "User Id: "+userId+
				"Username: "+userName+
				"Password: "+password+
				"Email: "+email+
				"First Name: "+firstName+
				"Last Name: "+lastName+
				"Date of Birth: "+dateOfBirth+
				"Profile Picture: "+profilePicture+
				"Favorite Artworks: "+favoriteArtworks;
	}
}
