package com.bishal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishal.models.Reels;
import com.bishal.models.User;
import com.bishal.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService {

	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;
	@Override
	public Reels createReels(Reels reel, User user) {
		Reels createReels=new Reels();
		createReels.setTitle(reel.getTitle());
		createReels.setUser(user);
		createReels.setVideo(reel.getVideo());
		
		return reelsRepository.save(createReels);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReels(Integer userId) throws Exception {
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
