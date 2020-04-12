package com.kiki.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kiki.cms.dao.FriendlyLinkDao;
import com.kiki.cms.domain.FriendlyLink;
import com.kiki.cms.service.FriendlyLinkService;

@Service
public class FriendlyLinkServiceImpl implements FriendlyLinkService {

	@Resource
	private FriendlyLinkDao friendlyLinkDao;
	@Override
	public List<FriendlyLink> queryFriendlyLinks() {
		// TODO Auto-generated method stub
		return friendlyLinkDao.queryFriendlyLinks();
	}

}
