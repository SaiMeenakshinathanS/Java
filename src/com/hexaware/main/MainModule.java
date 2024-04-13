package com.hexaware.main;

import com.hexaware.dao.IVirtualArtGallery;
import com.hexaware.dao.VirtualArtGalleryServiceImpl;
import com.hexaware.entity.Artist;
import com.hexaware.entity.Artwork;
import com.hexaware.entity.Gallery;
import com.hexaware.entity.User;
import com.hexaware.exception.ArtworkNotFoundException;
import com.hexaware.exception.UserNotFoundException;
import com.hexaware.dao.JunitClasses;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainModule {

	public static void main(String[] args) {
		// Creating an object for the Implementation method
		IVirtualArtGallery ivag = new VirtualArtGalleryServiceImpl();

		// Creating an object only for inserting details into User class as it doesn't
		// have in interface
		VirtualArtGalleryServiceImpl vagsi = new VirtualArtGalleryServiceImpl();

		// Creating an object only for inserting details into Gallery class and Artist
		// class
		JunitClasses juc = new JunitClasses();

		Scanner sc = new Scanner(System.in);
		// Creating an user interface
		System.out.println("----------------------------------------------------------");
		System.out.println("|                                                        |");
		System.out.println("|            WELCOME TO THE VIRTUAL ART GALLERY          |");
		System.out.println("|                                                        |");
		System.out.println("----------------------------------------------------------");

		// Creating a while loop until user wants to 'Exit'
		while (true) {
			System.out.println("\n--------------MENU--------------");
			System.out.println("1) Add an artwork");
			System.out.println("2) Update an artwork");
			System.out.println("3) Remove an artwork");
			System.out.println("4) View an artwork");
			System.out.println("5) Search an artwork");
			System.out.println("6) Add an artwork to User-Favorites");
			System.out.println("7) Remove an artwork from User-Favorites");
			System.out.println("8) View the favorite artworks");

			// These are the user interfaces just for JUNIT Testing
			System.out.println("9) Add an artwork to Artwork-Gallery");
			System.out.println("10) Update an artwork to Artwork-Gallery");
			System.out.println("11) Remove an artwork from Artwork Gallery");
			System.out.println("12) Search an artwork from Artwork Gallery");

			System.out.println("13) Create a new gallery");
			System.out.println("14) Update a gallery");
			System.out.println("15) Remove a gallery");
			System.out.println("16) View a gallery");
			System.out.println("17) Exit");
			System.out.print("Enter your choice: ");

			int userOpt = sc.nextInt();
			sc.nextLine();
			// Using a switch statement to display methods depending upon user's choice
			switch (userOpt) {
			case 1:
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

				// Calling the addArtwork() method from main method using an object which I have
				// created above
				boolean aA = ivag.addArtwork(artwork);

				if (aA) {
					System.out.println("Your artwork has been added successfully.");
				} else {
					System.out.println("Sorry. Your artwork hasn't been created.");
				}
				break;

			case 2:
				System.out.print("Enter your title: ");
				String title1 = sc.nextLine();

				System.out.print("Enter the description: ");
				String description1 = sc.nextLine();

				System.out.print("Enter the creation date in (yyyy--mm--dd) format: ");
				String date1 = sc.next();
				LocalDate creationDate1 = LocalDate.parse(date1);
				sc.nextLine();

				System.out.print("Enter the medium: ");
				String medium1 = sc.nextLine();

				System.out.print("Enter the Image URL: ");
				String imageURL1 = sc.nextLine();

				System.out.print("Enter your artwork id: ");
				int artworkId1 = sc.nextInt();

				Artwork artwork1 = new Artwork(artworkId1, title1, description1, creationDate1, medium1, imageURL1);

				// Calling the updateArtwork() method from the main method using an object which
				// I have created above
				boolean uA = ivag.updateArtwork(artwork1);

				if (uA) {
					System.out.println("Your artwork has been updated successfully.");
				} else {
					System.out.println("Sorry. Your artwork hasn't been updated.");
				}
				break;

			case 3:
				System.out.print("Enter the artwork id: ");
				int artworkId2 = sc.nextInt();

				// Calling the removeArtwork() method from the main method using an object which
				// I have created above
				boolean rA = ivag.removeArtwork(artworkId2);

				if (rA) {
					System.out.println("The artwork id has been removed successfully.");
				} else {
					// Exception - Throwing a runtime exception if the user enters an invalid
					// artwork id
					// Exception handled in main method
					try {
						throw new ArtworkNotFoundException(
								"Invalid artwork id - The entered artwork id is not found in the database.");
					} catch (ArtworkNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;

			case 4:
				System.out.print("Enter the artwork id: ");
				int artworkId3 = sc.nextInt();

				// Calling the getArtworkById() method from the main method using an object
				// which I have created above
				Artwork artwork2 = ivag.getArtworkById(artworkId3);

				if (artwork2 != null) {
					System.out.println("Artwork Id: " + artwork2.getArtworkId());
					System.out.println("Title: " + artwork2.getTitle());
					System.out.println("Description: " + artwork2.getDescription());
					System.out.println("Creation Date: " + artwork2.getCreationDate());
					System.out.println("Medium: " + artwork2.getMedium());
					System.out.println("Image URL: " + artwork2.getImageURL());
				} else {
					try {
						// Exception - Throwing a runtime exception if the user enters an invalid
						// artwork id
						// Exception handled in main method
						throw new ArtworkNotFoundException(
								"Invalid artwork id - The entered artwork id is not found in the database.");
					} catch (ArtworkNotFoundException e) {
						e.printStackTrace();
					}
				}

				break;

			case 5:
				System.out.print("Enter the keyword to search: ");
				String keyword = sc.next();

				// Calling the searchArtworks() method from the main method using an object
				// which we have created above
				List<Artwork> artwork3 = ivag.searchArtworks(keyword);

				boolean sa = false;

				if (artwork3 != null) {
					for (Artwork a : artwork3) {
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
				
				if(sa) {
					System.out.println("All the artworks which match the keyword which you have entered is listed above.");
				}
				else {
					System.out.println("Sorry. The keyword is not matched with the title or description. Please try again.");
				}
				break;

			case 6:
				boolean aAtF = false;
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

					System.out.print("Enter your date of birth in yyyy--mm--dd format: ");
					String dateOfBirth = sc.next();

					System.out.print("Add your profile picture link: ");
					String profilePicture = sc.next();

					// Calling the insertDetailsIntoUser() method from the method method using an
					// object which I have created above
					User user = new User(userId1, userName, password, email, firstName, lastName,
							LocalDate.parse(dateOfBirth), profilePicture);
					vagsi.insertDetailsIntoUser(user);

					System.out.print("Enter your user id: ");
					int userId = sc.nextInt();

					System.out.print("Enter the artwork id you liked: ");
					int artworkId4 = sc.nextInt();

					// Calling the addArtworkToFavorite() method from the main method using an
					// object which I have created above
					if (userId1 == userId) {
						aAtF = ivag.addArtworkToFavorite(userId, artworkId4);
					}

					if (aAtF) {
						System.out.println("Your favorite artwork has been added successfully.");
					} else {
						// Exception - Throwing a runtime exception if the user enters an invalid user
						// id
						// Exception handled in main method
						try {
							throw new UserNotFoundException("The entered user id is not found in the database.");
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
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
						aAtF = ivag.addArtworkToFavorite(userId, artworkId4);
					}

					if (aAtF) {
						System.out.println("Your favorite artwork has been added successfully.");
					} else {
						// Exception - Throwing a runtime exception if the user enters an invalid user
						// id
						// Exception handled in main method
						try {
							throw new UserNotFoundException("The entered user id is not found in the database.");
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
					}
				}

				break;

			case 7:
				System.out.print("Enter your user id: ");
				int userId2 = sc.nextInt();

				System.out.print("Enter your artwork id which you want to remove: ");
				int artworkId5 = sc.nextInt();

				// Calling the removeArtworkFromFavorite() method from the main method using an
				// object which I have created above
				boolean rAfF = ivag.removeArtworkFromFavorite(userId2, artworkId5);

				if (rAfF) {
					System.out
							.println("The entered artwork have been removed from your favorite artworks successfully.");
				} else {
					// Exception - Throwing a runtime exception if the user enters an invalid user
					// id
					// Exception handled in main method
					try {
						throw new UserNotFoundException("The entered user id is not found in the database.");
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;

			case 8:
				System.out.print("Enter your user id: ");
				int userId3 = sc.nextInt();

				// Calling the getUserFavoriteArtworks() from the main method using an object
				// which I have created above
				boolean gufa = ivag.getUserFavoriteArtworks(userId3);
				if (gufa) {
					System.out.println("Your favorite artworks have been listed above.");
				} else {
					// Exception - Throwing a runtime exception if the user enters an invalid user
					// id
					// Exception handled in main method
					try {
						throw new UserNotFoundException("The entered user id is not found in the database.");
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;

			case 9:
				System.out.print(
						"Enter 'Yes' if you are new to virtual art gallery or \nEnter 'No' if you have already enterted your artist and gallery details: ");
				String userOpt3 = sc.next();

				if (userOpt3.equalsIgnoreCase("Yes")) {
					System.out.println("Before entering your artwork into gallery, Enter details about gallery...");
					int artistId = 0;

					System.out.print("Enter your gallery id: ");
					int galleryId = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter your gallery name: ");
					String galleryName = sc.nextLine();

					System.out.print("Enter description about your gallery: ");
					String galleryDescription = sc.nextLine();

					System.out.print("Enter the location: ");
					String location = sc.nextLine();

					System.out.println("Before entering your artist id(curator), enter artist details...");

					System.out.print("Enter your artist id: ");
					artistId = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter your name: ");
					String artistName = sc.nextLine();

					System.out.print("Enter your biography: ");
					String biography = sc.nextLine();

					System.out.print("Enter your date of birth in yyyy--mm--dd format: ");
					String dob = sc.next();
					LocalDate dateOfBirth = LocalDate.parse(dob);
					sc.nextLine();

					System.out.print("Enter your nationality: ");
					String nationality = sc.nextLine();

					System.out.print("Enter your website: ");
					String website = sc.nextLine();

					System.out.print("Enter your contact information: ");
					String contactInformation = sc.nextLine();

					Artist artist = new Artist(artistId, artistName, biography, dateOfBirth, nationality, website,
							contactInformation);
					juc.insertDetailsIntoArtist(artist);

					System.out.print("Enter the opening hours: ");
					String openingHours = sc.nextLine();

					Gallery gallery = new Gallery(galleryId, galleryName, galleryDescription, location, artistId,
							openingHours);
					juc.insertDetailsIntoGallery(gallery);

					System.out.print("Enter your artwork id: ");
					int artworkId11 = sc.nextInt();

					System.out.print("Enter your gallery id: ");
					int galleryId3 = sc.nextInt();

					// Calling the addArtworkToGallery() method from the main method using an object
					// which I have created above
					boolean aatg = juc.addArtworkToGallery(artworkId11, galleryId3);

					if (aatg) {
						System.out.println("Your artwork has been inserted to gallery successfully.");
					}

					else {
						// Exception - Throwing a runtime exception if the user enters an invalid user
						// id
						// Exception handled in main method
						try {
							throw new UserNotFoundException("The entered artwork id is not found in the database.");
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
					}
				}

				else {
					System.out.print("Enter your artwork id: ");
					int artworkId6 = sc.nextInt();

					System.out.print("Enter your gallery id: ");
					int galleryId = sc.nextInt();

					// Calling the addArtworkToGallery() method from the main method using an object
					// which I have created above
					boolean aatg = juc.addArtworkToGallery(artworkId6, galleryId);
					if (aatg) {
						System.out.println("Your artwork has been inserted to gallery successfully.");
					}

					else {
						// Exception - Throwing a runtime exception if the user enters an invalid user
						// id
						// Exception handled in main method
						try {
							throw new UserNotFoundException("The entered artwork id is not found in the database.");
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
					}
				}

				break;

			case 10:
				System.out.print("Enter the artwork id: ");
				int artworkId7 = sc.nextInt();

				System.out.print("Enter your gallery id: ");
				int galleryId1 = sc.nextInt();

				// Calling the updateArtworkToGallery() method from the main method using an
				// object which I have created above
				boolean uatg = juc.updateArtworkToGallery(artworkId7, galleryId1);

				if (uatg) {
					System.out.println("Your artwork gallery has been updated successfully.");
				} else {
					// Exception - Throwing a runtime exception if the user enters an invalid user
					// id
					// Exception handled in main method
					try {
						throw new UserNotFoundException("The entered artwork id is not found in the database.");
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
				}

				break;

			case 11:
				System.out.print("Enter the artwork id you want to remove: ");
				int artworkId8 = sc.nextInt();

				// Calling the removeArtworkFromGallery() from the main method using an abject
				// which I have created above
				boolean ratg = juc.removeArtworkFromGallery(artworkId8);

				if (ratg) {
					System.out.println("Your artwork id has been removed from artwork gallery successfully.");
				} else {
					// Exception - Throwing a runtime exception if the user enters an invalid user
					// id
					// Exception handled in main method
					try {
						throw new UserNotFoundException("The entered artwork id is not found in the database.");
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
				}

				break;

			case 12:
				System.out.print("Enter the artwork id you want to search: ");
				int artworkId9 = sc.nextInt();

				// Calling the searchToArtworkInGallery() from the main method using an object
				// which I have created above
				boolean sfaig = juc.searchForArtworkInGallery(artworkId9);

				if (sfaig) {
					System.out.println("Your searched artwork has been listed above.");
				}

				else {
					// Exception - Throwing a runtime exception if the user enters an invalid user
					// id
					// Exception handled in main method
					try {
						throw new UserNotFoundException("The entered artwork id is not found in the database.");
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
				}

				break;

			case 13:
				System.out.print("Enter gallery id: ");
				int galleryId = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter the gallery name: ");
				String name = sc.nextLine();

				System.out.print("Enter the description: ");
				String description2 = sc.nextLine();

				System.out.print("Enter the location: ");
				String location = sc.nextLine();

				System.out.print("Enter the curator(artist id): ");
				int curator = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter the opening hours: ");
				String openingHours = sc.nextLine();

				Gallery gallery = new Gallery(galleryId, name, description2, location, curator, openingHours);

				// Calling the insertDetailsIntoGallery() method from the main method using an
				// object which I have created above
				juc.insertDetailsIntoGallery(gallery);

				break;

			case 14:
				System.out.print("Enter gallery id: ");
				int galleryId2 = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter the gallery name: ");
				String name1 = sc.nextLine();

				System.out.print("Enter the description: ");
				String description3 = sc.nextLine();

				System.out.print("Enter the location: ");
				String location1 = sc.nextLine();

				System.out.print("Enter the curator(artist id): ");
				int curator1 = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter the opening hours: ");
				String openingHours1 = sc.nextLine();

				Gallery gallery1 = new Gallery(galleryId2, name1, description3, location1, curator1, openingHours1);

				// Calling the updateGallery() method from the main method using an object which
				// I have created above
				boolean ug = juc.updateGallery(gallery1);

				if (ug) {
					System.out.println("Your details have been updated successfully.");
				} else {
					System.out.println("Sorry. Your details haven't been updated. Please try again.");
				}

				break;

			case 15:
				System.out.print("Enter the gallery id you want to remove: ");
				int galleryId3 = sc.nextInt();

				// Calling the removeGallery() method from the main method using an object which
				// I have created above
				boolean rg = juc.removeGallery(galleryId3);

				if (rg) {
					System.out.println("The entered gallery has been removed successfully.");
				} else {
					System.out.println("Sorry. The entered gallery hasn't been removed. Please try again.");
				}

				break;

			case 16:
				System.out.print("Enter the gallery id: ");
				int galleryId4 = sc.nextInt();

				// Calling the searchFromGallery() method from the main method using an object
				// which I have created above
				boolean sfg = juc.searchFromGallery(galleryId4);

				if (sfg) {
					System.out.println("The details about gallery which you need is listed above.");
				} else {
					System.out.println("Sorry. The entered gallery is invalid. Please try again.");
				}

				break;

			case 17:
				System.out.println("---------------------------------THANK YOU---------------------------------------");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>WELCOME YOU AGAIN<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.exit(0);

			default:
				System.out.println("Invalid choice. Please try again");
				break;
			}
		}
	}
}
