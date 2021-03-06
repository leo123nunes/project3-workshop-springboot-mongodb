package com.leonardonunes.workshopmongo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardonunes.workshopmongo.entities.Post;
import com.leonardonunes.workshopmongo.repositories.PostRepository;
import com.leonardonunes.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public  List<Post> findAll(){
		return repository.findAll();
	}
	
	public Post findById(String id) {
		Post post = repository.findById(id).orElse(null);
		if(post==null) {
			throw new ObjectNotFoundException("Id not found.");
		}
		return post;
	}
	
	public List<Post> findByTitle(String text){
		return repository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24*60*60*1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
