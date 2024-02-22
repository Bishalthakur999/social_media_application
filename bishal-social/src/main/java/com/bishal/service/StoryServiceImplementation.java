package com.bishal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishal.models.Story;
import com.bishal.models.User;
import com.bishal.repository.StoryRepository;

@Service
public class StoryServiceImplementation implements StoryService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private StoryRepository storyRepository;
	@Override
	public Story createStory(Story story, User user) {
		Story createdStory=new Story();
		createdStory.setCaption(story.getCaption());
		createdStory.setImage(story.getImage());
		createdStory.setUser(user);
		createdStory.setTimeStamp(LocalDateTime.now());
		
		return storyRepository.save(createdStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		User user=userService.findUserById(userId);
		
		return storyRepository.findByUserId(userId);
	}

}
