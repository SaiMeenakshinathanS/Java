package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

import com.hexaware.entity.Artist;
import com.hexaware.entity.Gallery;
import com.hexaware.util.DBConnectionUtil;
import com.hexaware.util.DBPropertyUtil;

public class JunitClasses {
	Scanner sc = new Scanner(System.in);

	// Establish the connection from Java to MYSQL through JDBC
	String conString = DBPropertyUtil.getConnectionString("resources\\Application.properties");
	String userName = DBPropertyUtil.getUserName("resources\\Application.properties");
	String password = DBPropertyUtil.getPassword("resources\\Application.properties");
	Connection con = DBConnectionUtil.getDBConnection(conString, userName, password);

	// The below methods are creating to check the JUNIT Test Cases
	// These are the methods which are not in the services part

	// Add artwork to gallery
	public boolean addArtworkToGallery(int artworkId, int galleryId) {
		String query = "INSERT INTO Artwork_Gallery VALUES(?,?)";

		PreparedStatement pst = null;
		int rows = 0;

		boolean aatg = false;

		try {
			pst = con.prepareStatement(query);

			pst.setInt(1, artworkId);
			pst.setInt(2, galleryId);

			rows = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rows > 0) {
			aatg = true;
		}

		return aatg;
	}

	// Update an artwork
	public boolean updateArtworkToGallery(int artworkId, int galleryId) {
		String query = "UPDATE Artwork_Gallery SET artworkId=? WHERE galleryID=?";

		PreparedStatement pst = null;
		int rows = 0;
		
		boolean uatg=false;
		
		try {
			pst = con.prepareStatement(query);

			pst.setInt(1, artworkId);
			pst.setInt(2, galleryId);

			rows = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rows > 0) {
			uatg=true;
		} 
		
		return uatg;
	}

	public boolean removeArtworkFromGallery(int artworkId) {
		String query = "DELETE FROM Artwork_Gallery WHERE ArtworkId= " + artworkId;

		// I am using 'Statement' as I am not accepting any input parameters
		Statement s = null;
		int rows = 0;
		
		boolean ratg=false;
		
		try {
			s = con.createStatement();

			rows = s.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rows > 0) {
			ratg=true;
		} 
		
		return ratg;
	}

	// Searching for an artwork in gallery
	public boolean searchForArtworkInGallery(int artworkId) {
		String query = "SELECT * FROM Artwork_Gallery WHERE ArtworkId=" + artworkId;
		List<Integer> artworkIds = new ArrayList<>();
		List<Integer> galleryIds = new ArrayList<>();

		// I am using 'Statement' as I am not accepting any input parameters
		Statement s = null;
		ResultSet rst = null;
		
		boolean sfaig=false;
		
		try {
			s = con.createStatement();

			rst = s.executeQuery(query);

			while (rst.next()) {
				artworkIds.add(rst.getInt(1));
				galleryIds.add(rst.getInt(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(artworkIds!=null && galleryIds!=null) {
			Iterator<Integer> it = artworkIds.iterator();
			while (it.hasNext()) {
				for (Integer g : galleryIds) {
					System.out.println("Gallery Id: " + g);
					System.out.println("Artwork id: " + it.next());
				}
				sfaig=true;
			}
		}
		
		return sfaig;
	}

	// Inserting details into Gallery table as Artwork-Gallery requires galleryId
	//It also comes under one test cases in Gallery Management
	public boolean insertDetailsIntoGallery(Gallery gallery) {
		String query = "INSERT INTO Gallery VALUES(?,?,?,?,?,?)";

		PreparedStatement pst = null;
		int rows = 0;
		
		boolean idig=false;
		
		try {
			pst = con.prepareStatement(query);

			pst.setInt(1, gallery.getGalleryId());
			pst.setString(2, gallery.getName());
			pst.setString(3, gallery.getDescription());
			pst.setString(4, gallery.getLocation());
			pst.setInt(5, gallery.getCurator());
			pst.setString(6, gallery.getOpeningHours());

			rows = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rows > 0) {
			System.out.println("Your details have been inserted successfully.");
			idig=true;
		} else {
			System.out.println("Sorry. Your details haven't been inserted. Please try again.");
		}
		
		return idig;
	}

	// Inserting details into Artist table as Gallery table requires artistId
	public void insertDetailsIntoArtist(Artist artist) {
		String query = "INSERT INTO Artist VALUES(?,?,?,?,?,?,?)";

		PreparedStatement pst = null;
		int rows = 0;

		try {
			pst = con.prepareStatement(query);

			pst.setInt(1, artist.getArtistId());
			pst.setString(2, artist.getName());
			pst.setString(3, artist.getBiography());
			pst.setDate(4, java.sql.Date.valueOf(artist.getBirthDate()));
			pst.setString(5, artist.getNationality());
			pst.setString(6, artist.getWebsite());
			pst.setString(7, artist.getContactInformation());

			rows = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rows > 0) {
			System.out.println("Your details have been inserted successfully.");
		} else {
			System.out.println("Sorry. Your details haven't been inserted. Please try again.");
		}
	}
	
	//Update details into gallery
	public boolean updateGallery(Gallery gallery) {
		String query="UPDATE Gallery SET Name=?,Description=?,Location=?,Curator=?,OpeningHours=? WHERE GalleryID=?";
		
		PreparedStatement pst=null;
		int rows=0;
		boolean ug=false;
		
		try {
			pst=con.prepareStatement(query);
			
			pst.setString(1,gallery.getName());
			pst.setString(2, gallery.getDescription());
			pst.setString(3, gallery.getLocation());
			pst.setInt(4, gallery.getCurator());
			pst.setString(5, gallery.getOpeningHours());
			pst.setInt(6, gallery.getGalleryId());
			
			rows=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(rows>0) {
			ug=true;
		}
		
		return ug;
	}
	
	public boolean removeGallery(int galleryId) {
		String query="DELETE FROM Gallery WHERE galleryId="+galleryId;
		
		// I am using 'Statement' as I am not accepting any input parameters
		Statement s = null;
		int rows=0;
		boolean rg=false;
		try {
			s=con.createStatement();
			
			rows=s.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(rows>0) {
			rg=true;
		}
		
		return rg;
	}
	
	public boolean searchFromGallery(int galleryId) {
		Gallery gallery=null;
		
		String query="SELECT * FROM Gallery WHERE GalleryId="+galleryId;
		
		// I am using 'Statement' as I am not accepting any input parameters
		Statement s = null;
		ResultSet rst=null;
		boolean sfg=false;
		
		try {
			s=con.createStatement();
			
			rst=s.executeQuery(query);
			while(rst.next()){
				gallery=new Gallery(
						rst.getInt(1),
						rst.getString(2),
						rst.getString(3),
						rst.getString(4),
						rst.getInt(5),
						rst.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				s.close();
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
			if(gallery!=null) {
				System.out.println("Gallery Id: "+gallery.getGalleryId());
				System.out.println("Gallery Name: "+gallery.getName());
				System.out.println("Description: "+gallery.getDescription());
				System.out.println("Location: "+gallery.getLocation());
				System.out.println("Curator: "+gallery.getCurator());
				System.out.println("Opening Hours: "+gallery.getOpeningHours());
				
				sfg=true;
			}
			
			return sfg;
	}
}
