package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.hexaware.entity.Artwork;
import com.hexaware.entity.User;
import com.hexaware.util.DBConnectionUtil;
import com.hexaware.util.DBPropertyUtil;

//Implementing the interfaces using Java DataBase Connectivity
public class VirtualArtGalleryServiceImpl implements IVirtualArtGallery {

	//Establish the connection from Java to MYSQL through JDBC
	String conString = DBPropertyUtil.getConnectionString("resources\\Application.properties");
	String userName = DBPropertyUtil.getUserName("resources\\Application.properties");
	String password = DBPropertyUtil.getPassword("resources\\Application.properties");
	Connection con = DBConnectionUtil.getDBConnection(conString, userName, password);

	// Implementing the interfaces in Artwork Management
	// Implementing the method addArtwork()
	// Creating (or) Adding all the values of attributes required to create an artwork

	@Override
	public boolean addArtwork(Artwork artwork) {
		String query = "INSERT INTO Artwork VALUES(?,?,?,?,?,?)";

		PreparedStatement pst = null;
		int rows = 0;
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, artwork.getArtworkId());
			pst.setString(2, artwork.getTitle());
			pst.setString(3, artwork.getDescription());
			pst.setDate(4, java.sql.Date.valueOf(artwork.getCreationDate()));
			pst.setString(5, artwork.getMedium());
			pst.setString(6, artwork.getImageURL());

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
			return true;
		} else {
			return false;
		}
	}

	// Implementing the method updateArtwork()
	// Updating any column in Artwork table
	
	@Override
	public boolean updateArtwork(Artwork artwork) {
		String query = "UPDATE Artwork SET Title=?,Description=?,CreationDate=?,Medium=?,ImageURL=? WHERE ArtworkID=?";

		PreparedStatement pst = null;
		int rows = 0;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, artwork.getTitle());
			pst.setString(2, artwork.getDescription());
			pst.setDate(3, java.sql.Date.valueOf(artwork.getCreationDate()));
			pst.setString(4, artwork.getMedium());
			pst.setString(5, artwork.getImageURL());
			pst.setInt(6, artwork.getArtworkId());

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
			return true;
		} else {
			return false;
		}
	}

	// Implementing the method removeArtwork()
	// Removing an artwork by giving an arworkID
	@Override
	public boolean removeArtwork(int artworkID) {
		String query = "DELETE FROM Artwork WHERE ArtworkID=" + artworkID;

		// I am using 'Statement' as I am not accepting any input parameters
		Statement st = null;
		int rows = 0;
		boolean ra=false;
		
		try {
			st = con.createStatement();
			
			rows = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rows > 0) {
			ra=true;
		} 
		
		return ra;
	}

	// Implementing the method getArtworkById()
	// View (or) retrieve an artwork by giving an artworkID
	@Override
	public Artwork getArtworkById(int artworkId) {
		String query = "SELECT * FROM Artwork WHERE ArtworkID=" + artworkId;
		Artwork artwork = null;

		// I am using 'Statement' as I am not accepting any input parameters
		Statement st = null;
		ResultSet rst = null;
		try {
			st = con.createStatement();
			rst = st.executeQuery(query);
			
			while(rst.next()) {
				artwork = new Artwork(rst.getInt(1),
						rst.getString(2),
						rst.getString(3),
						rst.getDate(4).toLocalDate(),
						rst.getString(5), 
						rst.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return artwork;
	}

	// Implementing the method searchArtworks()
	// View (or) retrieve the particular artwork by title or description must be same in all the artwork
	@Override
	public List<Artwork> searchArtworks(String keyword) {
		String query = "SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?";

		List<Artwork> artworks = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rst = null;
		Artwork artwork=null;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");

			rst = pst.executeQuery();
			while(rst.next()){
				artwork = new Artwork(rst.getInt(1), 
						rst.getString(2),
						rst.getString(3),
						rst.getDate(4).toLocalDate(),
						rst.getString(5), 
						rst.getString(6));
				
				artworks.add(artwork);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return artworks;
	}

	// Implementing the interfaces in User Favorites
	// Implementing the method addArtworkToFavorite()
	// Add Artwork to User_Favorite_Artworks by giving userId and artworkId
	@Override
	public boolean addArtworkToFavorite(int userId, int artworkId) {
		String query = "INSERT INTO User_Favorite_Artwork VALUES(?,?)";
		PreparedStatement pst = null;
		int rows = 0;

		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			pst.setInt(2, artworkId);

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
			return true;
		} else {
			return false;
		}
	}

	// Implementing the method removeArtworkFromFavorite()
	// Remove the Artwork from User_Favorite_Artworks by giving userId and artworkId
	@Override
	public boolean removeArtworkFromFavorite(int userId, int artworkId) {
		String query = "DELETE FROM User_Favorite_Artwork WHERE userID=? AND artworkID=?";

		PreparedStatement pst = null;
		int rows = 0;
		try {
			pst = con.prepareStatement(query);

			pst.setInt(1, userId);
			pst.setInt(2, artworkId);

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
			return true;
		} else {
			return false;
		}
	}

	// Implementing the method getUserFavoriteArtworks()
	// View (or) retrieve the specific user favorite artwork by giving userId
	@Override
	public boolean getUserFavoriteArtworks(int userId) {
		List<Integer> artworks = new ArrayList<>();
		List<Artwork> al=new ArrayList<>();
		Artwork artwork=null;
		
		boolean gufa=false;
		String query = "SELECT UFA.ArtworkID,A.Title,A.Description FROM User_Favorite_Artwork UFA "
				+ "JOIN User U ON UFA.UserID=U.UserID JOIN Artwork A ON UFA.ArtworkID=A.ArtworkID WHERE U.UserID="+ userId;
		
		// I am using 'Statement' as I am not accepting any input parameters
		Statement st = null;
		ResultSet rst = null;

		try {
			st = con.createStatement();
			rst = st.executeQuery(query);
            
			while(rst.next()) {
				artworks.add(rst.getInt(1));
				artwork=new Artwork(rst.getString(2),
						rst.getString(3));
				

				al.add(artwork);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        
		if(artworks!=null) {
			Iterator<Integer> it=artworks.iterator();
			//Iterator<Artwork> it1=al.iterator();
			while(it.hasNext()) {
					for(Artwork a1:al) {
				System.out.println("Your Favorite artwork: "+it.next());
				System.out.println("Your title: "+a1.getTitle());
				System.out.println("Your description: "+a1.getDescription());
				
				gufa=true;
					}
			}
			/*for(Integer a:artworks) {
				for(Artwork a1:al) {
					System.out.println("Your Favorite artwork: "+a);
					System.out.println("Your title: "+a1.getTitle());
					System.out.println("Your description: "+a1.getDescription());
				}
			}*/
		}
		return gufa;
	}
	
	//Inserting values into User table as User_Favorite_Artworks requires data from User table
	public void insertDetailsIntoUser(User user) {
		String query="INSERT INTO User (UserId,UserName,Password,Email,FirstName,LastName,DateOfBirth,ProfilePicture) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement pst=null;
		int rows=0;
		
		try {
			pst=con.prepareStatement(query);
			
			pst.setInt(1, user.getUserId());
			pst.setString(2, user.getUserName());
			pst.setString(3, user.getPassword());
			pst.setString(4,user.getEmail());
			pst.setString(5, user.getFirstName());
			pst.setString(6, user.getLastName());
			pst.setDate(7, java.sql.Date.valueOf(user.getDateOfBirth()));
			pst.setString(8, user.getProfilePicture());
			
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
			System.out.println("Your details have been inserted successfully.");
		}
		else {
			System.out.println("Sorry. Your details haven't been inserted.");
		}
	}
}
