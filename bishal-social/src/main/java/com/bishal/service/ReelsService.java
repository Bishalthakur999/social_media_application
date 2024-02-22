package com.bishal.service;

import java.util.List;

import com.bishal.models.Reels;
import com.bishal.models.User;

public interface ReelsService {
	
	public Reels createReels(Reels reel,User user);
	public List<Reels> findAllReels();
	public List<Reels> findUserReels(Integer userId) throws Exception;

}
