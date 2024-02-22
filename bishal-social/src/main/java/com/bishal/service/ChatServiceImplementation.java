package com.bishal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishal.models.Chat;
import com.bishal.models.User;
import com.bishal.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService{
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat isExist=chatRepository.findChatByUsersId(user2, reqUser);
	
		if(isExist!=null) {
			return isExist;
		}
		
		Chat chat=new Chat();
		chat.getUsers().add(user2);
	    chat.getUsers().add(reqUser);
	    chat.setTimeStamp(LocalDateTime.now());
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> opt=chatRepository.findById(chatId);
		
		if(opt.isEmpty()) {
			throw new Exception("chat not found with this id "+chatId);
		}
		return opt.get() ;
	}

	@Override
	public List<Chat> findUsersChat(Integer UserId) {
		// TODO Auto-generated method stub
		return chatRepository.findByUsersId(UserId);
	}

}
