package com.dimitrova.songdirectory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dimitrova.songdirectory.models.Song;
import com.dimitrova.songdirectory.repositories.SongRepository;

@Service
public class SongService {
//	add repository as dependency
	private final SongRepository songRepo;
	
	public SongService(SongRepository songRepo) {
		this.songRepo = songRepo;
	}
	
//	return all songs
	public List<Song> allSongs(){
		return songRepo.findAll();
	}
	
//	create a new song
	public Song createSong(Song song) {
		return songRepo.save(song);
	}
	
//	update song
	public Song updateSong(Song song) {
		return songRepo.save(song);
	}
	
//	find one song
	public Song findSong(Long id) {
			return this.songRepo.findById(id).orElse(null);
		}
	
//	delete song
	public void deleteSong(Long id) {
		Optional<Song> optionalSong = songRepo.findById(id);
		if(optionalSong.isPresent()) {
			songRepo.deleteById(id);
		}
	}

}

