package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.Message;
import com.sdtower.common.bean.MessagePage;

public interface MessageService {

	public List<Message> getMessages(MessagePage MessagePage);
	
	public int getMessagesCount(MessagePage MessagePage);

	public Message getMessage(int ID);

	public int deleteMessage(Map map);

	public int changeMessage(Map map);

	public int addMessage(Map map);
	
	
}
