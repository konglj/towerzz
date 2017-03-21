package com.sdtower.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdtower.common.bean.AdminManagerInfo;
import com.sdtower.common.bean.BindUser;
import com.sdtower.common.bean.BindUserPage;
import com.sdtower.common.bean.ManagerPage;
import com.sdtower.common.bean.ManagerPower;
import com.sdtower.common.bean.Message;
import com.sdtower.common.bean.MessagePage;
import com.sdtower.common.bean.Power;
import com.sdtower.mapper.ManagerMapper;
import com.sdtower.mapper.MessageMapper;
import com.sdtower.service.ManagerService;
import com.sdtower.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageMapper messageMapper;

	@Override
	@Transactional
	public List<Message> getMessages(MessagePage MessagePage) {
		// TODO Auto-generated method stub
		return messageMapper.getMessages(MessagePage);
	}

	@Override
	@Transactional
	public int getMessagesCount(MessagePage MessagePage) {
		// TODO Auto-generated method stub
		return messageMapper.getMessagesCount(MessagePage);
	}

	@Override
	@Transactional
	public Message getMessage(int ID) {
		// TODO Auto-generated method stub
		return messageMapper.getMessage(ID);
	}

	@Override
	@Transactional
	public int deleteMessage(Map map) {
		int count = 0;
		count = messageMapper.delMessage(map);
		if (count == 0)
			throw new RuntimeException();
		return 1;
	}

	@Override
	public int changeMessage(Map map) {
		int count = 0;
		count = messageMapper.changeMessage(map);
		if (count == 0)
			throw new RuntimeException();
		return 1;
	}

	@Override
	public int addMessage(Map map) {
		int count = 0;
		count = messageMapper.addMessage(map);
		if (count == 0)
			throw new RuntimeException();
		return 1;
	}

}
