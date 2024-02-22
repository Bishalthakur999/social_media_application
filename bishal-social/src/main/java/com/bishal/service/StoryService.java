package com.bishal.service;

import java.util.List;

import com.bishal.models.Story;
import com.bishal.models.User;

public interface StoryService {
	
	public Story createStory(Story story,User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
