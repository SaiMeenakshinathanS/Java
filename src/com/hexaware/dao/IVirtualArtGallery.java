package com.hexaware.dao;

import com.hexaware.entity.Artwork;
import java.util.List;

//Create an interface IVirtualArtGallery

public interface IVirtualArtGallery {
	//Artwork Management
	
	public boolean addArtwork(Artwork artwork);
	
	public boolean updateArtwork(Artwork artwork);
	
	public boolean removeArtwork(int artworkId);
	
	public Artwork getArtworkById(int artworkId);

	public List<Artwork> searchArtworks(String keyword);
	
	//User Favorites
	
	public boolean addArtworkToFavorite(int userId,int artworkId);
	
	public boolean removeArtworkFromFavorite(int userId,int artworkId);
	
	//public boolean getUserFavoriteArtworks(int userId);
    public boolean getUserFavoriteArtworks(int userId);
	}
