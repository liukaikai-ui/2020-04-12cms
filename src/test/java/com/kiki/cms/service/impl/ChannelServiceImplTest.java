package com.kiki.cms.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kiki.cms.domain.Category;
import com.kiki.cms.domain.Channel;
import com.kiki.cms.service.ChannelService;

public class ChannelServiceImplTest extends TestUtil{

	@Autowired
	private ChannelService service;
	@Test
	public void testQueryChannel() {
		List<Channel> list = service.queryChannel();
		for (Channel channel : list) {
			System.out.println(channel);
		}
	}

	@Test
	public void testQueryCategoryByChannelId() {
		List<Category> list = service.queryCategoryByChannelId(1);
		for (Category category : list) {
			System.out.println(category);
		}
	}

}
