package com.bishal.service;

import java.util.List;

import com.bishal.models.Chat;
import com.bishal.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser,User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer UserId);
	
	

}
