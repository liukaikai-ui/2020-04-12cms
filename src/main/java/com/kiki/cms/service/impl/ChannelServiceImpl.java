package com.kiki.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.cms.dao.ChannelDao;
import com.kiki.cms.domain.Category;
import com.kiki.cms.domain.Channel;
import com.kiki.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDao dao;
	@Override
	public List<Channel> queryChannel() {
		// TODO Auto-generated method stub
		return dao.queryChannel();
	}

	@Override
	public List<Category> queryCategoryByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return dao.queryCategoryByChannelId(channelId);
	}

}
