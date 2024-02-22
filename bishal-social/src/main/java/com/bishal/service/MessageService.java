package com.bishal.service;

import java.util.List;

import com.bishal.models.Chat;
import com.bishal.models.Message;
import com.bishal.models.User;

public interface MessageService {
	
	public Message createMessage(User user,Integer chatId,Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	

}
