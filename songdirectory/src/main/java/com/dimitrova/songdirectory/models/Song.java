package com.dimitrova.songdirectory.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//set up table and variables
@Entity
@Table(name="songs")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Song name cannot be empty!")
	@Size(min = 1, max = 400, message="")
	private String name;
	
	@NotEmpty(message="Artist name cannot be empty!")
	@Size(min = 1, max = 400, message="")
	private String artist;
	
	@NotEmpty(message="Genre cannot be empty!")
	@Size(min = 1, max = 400, message="")
	private String genre;
	
	@NotEmpty(message="Lyrics cannot be empty!")
	@Size(min = 1, max = 400,  message="")
	private String lyrics;
	
	@Column
	private int updatedTimes;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
		this.updatedTimes = updatedTimes + 1;
	}
	
//	establish relationship between models
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	 private User user;

//	generate constructor
	public Song() {}
	
//	populate constructor w/ variables
	public Song(String name, String artist, String genre, String lyrics) {
		this.name = name;
		this.artist= artist;
		this.genre = genre;
		this.lyrics = lyrics;
	}
	
//generate getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public int getUpdatedTimes() {
		return updatedTimes;
	}

	public void setUpdatedTimes(int updatedTimes) {
		this.updatedTimes = updatedTimes;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}