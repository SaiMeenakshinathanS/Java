package com.hexaware.junit;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;
import com.hexaware.dao.JunitClasses;
import com.hexaware.entity.Artwork;
import com.hexaware.entity.Gallery;
import com.hexaware.entity.User;
import com.hexaware.util.DBConnectionUtil;
import com.hexaware.util.DBPropertyUtil;
import com.hexaware.dao.IVirtualArtGallery;
import com.hexaware.dao.VirtualArtGalleryServiceImpl;

public class TestArtworkManagement {
	JunitClasses juc = new JunitClasses();

	IVirtualArtGallery ivag = new VirtualArtGalleryServiceImpl();

	VirtualArtGalleryServiceImpl vagsi = new VirtualArtGalleryServiceImpl();

	Scanner sc = new Scanner(System.in);
	
	// JUNIT TESTING
	// Test Cases in Artwork Management
	// 1) Test the ability to upload a new artwork to the gallery.

	@Test
	public void testAddArtworkToGallery() {
		System.out.print("Enter the artwork id: ");
		int artworkId = sc.nextInt();

		System.out.print("Enter the gallery id: ");
		int galleryId = sc.nextInt();

		boolean aatg = juc.addArtworkToGallery(artworkId, galleryId);
		Assert.assertEquals(true, aatg);
	}

	// 2) Verify that updating artwork details works correctly

	/*@Test
	public void testUpdateArtworkToGallery() {
		System.out.print("Enter the artwork id: ");
		int artworkId = sc.nextInt();

		System.out.print("Enter the gallery id: ");
		int galleryId = sc.nextInt();

		boolean tuag = juc.updateArtworkToGallery(artworkId, galleryId);
		Assert.assertEquals(true, tuag);
	}

	// 3) Test removing an artwork from the gallery

	@Test
	public void testRemoveArtworkFromGallery() {
		System.out.print("Enter your artwork id: ");
		int artworkId = sc.nextInt();

		Assert.assertEquals(true, juc.removeArtworkFromGallery(artworkId));
	}

	// 4) Check if searching for artworks returns the expected results

	@Test
	public void testSearchForArtworkInGallery() {
		System.out.print("Enter the artwork id to search: ");
		int artworkId = sc.nextInt();

		boolean sfaig = juc.searchForArtworkInGallery(artworkId);
		Assert.assertEquals(true, sfaig);
	}

	// Test Cases in Gallery Management
	// 1) Test creating a new gallery

	@Test
	public void testCreateGallery() {
		System.out.print("Enter gallery id: ");
		int galleryId = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter the gallery name: ");
		String name = sc.nextLine();

		System.out.print("Enter the description: ");
		String description = sc.nextLine();

		System.out.print("Enter the location: ");
		String location = sc.nextLine();

		System.out.print("Enter the curator(artist id): ");
		int curator = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter the opening hours: ");
		String openingHours = sc.nextLine();

		Gallery gallery = new Gallery(galleryId, name, description, location, curator, openingHours);

		Assert.assertEquals(true, juc.insertDetailsIntoGallery(gallery));
	}

	// 2) Verify that updating gallery information works correctly

	@Test
	public void testUpdateGallery() {
		System.out.print("Enter gallery id: ");
		int galleryId = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter the gallery name: ");
		String name = sc.nextLine();

		System.out.print("Enter the description: ");
		String description = sc.nextLine();

		System.out.print("Enter the location: ");
		String location = sc.nextLine();

		System.out.print("Enter the curator(artist id): ");
		int curator = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter the opening hours: ");
		String openingHours = sc.nextLine();

		Gallery gallery = new Gallery(galleryId, name, description, location, curator, openingHours);

		Assert.assertEquals(true, juc.updateGallery(gallery));
	}

	// 3) Test removing a gallery from the system

	@Test
	public void testRemoveGallery() {
		System.out.print("Enter the gallery id you want to remove: ");
		int galleryId = sc.nextInt();

		Assert.assertEquals(true, juc.removeGallery(galleryId));
	}

	// 4) Check if searching for galleries returns the expected results

	@Test
	public void testSearchFromGallery() {
		System.out.print("Enter the gallery id you want to search: ");
		int galleryId = sc.nextInt();

		boolean sfg = juc.searchFromGallery(galleryId);
		Assert.assertEquals(true, sfg);
	}

	// Running the test cases for VirtualArtGalleryServiceImpl
	// Artwork
	// 1) Test for creating a new artwork

	@Test
	public void testAddArtwork() {
		System.out.print("Enter your artwork id: ");
		int artworkId = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter your title: ");
		String title = sc.nextLine();

		System.out.print("Enter the description: ");
		String description = sc.nextLine();

		System.out.print("Enter the creation date in (yyyy--mm--dd) format: ");
		String date = sc.next();
		LocalDate creationDate = LocalDate.parse(date);
		sc.nextLine();

		System.out.print("Enter the medium: ");
		String medium = sc.nextLine();

		System.out.print("Enter the Image URL: ");
		String imageURL = sc.nextLine();

		Artwork artwork = new Artwork(artworkId, title, description, creationDate, medium, imageURL);

		Assert.assertEquals(true, ivag.addArtwork(artwork));
	}

	// 2) Verify that updating artwork information works correctly.

	@Test
	public void updateArtwork() {

		System.out.print("Enter your title: ");
		String title = sc.nextLine();

		System.out.print("Enter the description: ");
		String description = sc.nextLine();

		System.out.print("Enter the creation date in (yyyy--mm--dd) format: ");
		String date1 = sc.next();
		LocalDate creationDate = LocalDate.parse(date1);
		sc.nextLine();

		System.out.print("Enter the medium: ");
		String medium = sc.nextLine();

		System.out.print("Enter the Image URL: ");
		String imageURL = sc.nextLine();

		System.out.print("Enter your artwork id: ");
		int artworkId = sc.nextInt();

		Artwork artwork = new Artwork(artworkId, title, description, creationDate, medium, imageURL);

		Assert.assertEquals(true, ivag.updateArtwork(artwork));
	}

	// 3) Verify that removing artwork information works correctly

	@Test
	public void testRemoveArtwork() {

		System.out.print("Enter the artwork id: ");
		int artworkId = sc.nextInt();

		Assert.assertEquals(true, ivag.removeArtwork(artworkId));
	}

	// 4) Verify that get a specific artwork by giving artwork id works correctly

	@Test
	public void testGetArtworkById() {
		System.out.print("Enter your artwork id: ");
		int artworkId = sc.nextInt();

		Artwork artwork = ivag.getArtworkById(artworkId);

		boolean gabi = false;

		if (artwork != null) {
			gabi = true;
		}

		Assert.assertEquals(true, gabi);
	}

	// 5) Verify that searching an artwork with the keyword you have entered works
	// correctly

	@Test
	public void testSearchArtworks() {
		System.out.print("Enter the keyword to search: ");
		String keyword = sc.next();

		List<Artwork> artwork = ivag.searchArtworks(keyword);

		boolean sa = false;

		if (artwork != null) {
			for (Artwork a : artwork) {
				System.out.println("Artwork Id: " + a.getArtworkId());
				System.out.println("Title: " + a.getTitle());
				System.out.println("Description: " + a.getDescription());
				System.out.println("Creation Date: " + a.getCreationDate());
				System.out.println("Medium: " + a.getMedium());
				System.out.println("Image URL: " + a.getImageURL());
				System.out.println("-----------------------------");
				sa = true;
			}
		}

		Assert.assertEquals(true, sa);
	}

	// User Favorites
	// 6) Verify that adding an artwork to User_Favorite_Artworks works correctly
	@Test
	public void testAddArtworkToFavorites() {
		boolean aatf = false;

		System.out.print(
				"Enter 'Yes' if you are new to virtual art gallery or \nEnter 'No' if you have already enterted your user details: ");
		String userOpt2 = sc.next();

		if (userOpt2.equalsIgnoreCase("yes")) {
			System.out.println("Before entering your favorite artworks, Enter details about you...");

			System.out.print("Enter user id: ");
			int userId1 = sc.nextInt();
			sc.nextLine();

			System.out.print("Enter your user name: ");
			String userName = sc.next();

			System.out.print("Enter your password: ");
			String password = sc.next();

			System.out.print("Enter your email: ");
			String email = sc.next();
			sc.nextLine();

			System.out.print("Enter your first name: ");
			String firstName = sc.nextLine();

			System.out.print("Enter your last name:");
			String lastName = sc.next();
			sc.nextLine();
			
			System.out.print("Enter your date of birth in yyyy--mm--dd format: ");
			String dateOfBirth = sc.next();

			System.out.print("Add your profile picture link: ");
			String profilePicture = sc.next();

			// Calling the insertDetailsIntoUser() method from the method method using an
			// object which I have created above
			User user = new User(userId1, userName, password, email, firstName, lastName, LocalDate.parse(dateOfBirth),
					profilePicture);

			vagsi.insertDetailsIntoUser(user);

			System.out.print("Enter your user id: ");
			int userId = sc.nextInt();

			System.out.print("Enter the artwork id you liked: ");
			int artworkId4 = sc.nextInt();

			// Calling the addArtworkToFavorite() method from the main method using an
			// object which I have created above
			if (userId1 == userId) {
				aatf = ivag.addArtworkToFavorite(userId, artworkId4);
			}
		} else {
			System.out.print("Enter your user id which you have entered in User Details: ");
			int userId2 = sc.nextInt();

			System.out.print("Enter your user id: ");
			int userId = sc.nextInt();

			System.out.print("Enter the artwork id you liked: ");
			int artworkId4 = sc.nextInt();

			// Calling the addArtworkToFavorite() method from the main method using an
			// object which I have created above
			if (userId2 == userId) {
				aatf = ivag.addArtworkToFavorite(userId, artworkId4);
			}
		}

		Assert.assertEquals(true, aatf);
	}

	// 7) Verify that removing an artwork from User_Favorite_Artworks works
	// correctly
	@Test
	public void testRemoveArtworkFromFavorites() {
		System.out.print("Enter your user id: ");
		int userId = sc.nextInt();

		System.out.print("Enter your artwork id which you want to remove: ");
		int artworkId = sc.nextInt();

		boolean raff = ivag.removeArtworkFromFavorite(userId, artworkId);

		Assert.assertEquals(true, raff);
	}

	// 8) Verify that getting user favorite artwork from User_Favorite_Artworks
	// works correctly
	@Test
	public void testGetUserFavoriteArtworks() {
		System.out.print("Enter the user id: ");
		int userId = sc.nextInt();

		boolean gufa = ivag.getUserFavoriteArtworks(userId);

		Assert.assertEquals(true, gufa);
	}*/

}
