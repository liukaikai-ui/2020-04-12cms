package com.kiki.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.kiki.cms.dao.CollectDao;
import com.kiki.cms.domain.Collect;
import com.kiki.cms.service.CollectService;
import com.kiki.cms.util.CMSException;

@Service
public class CollectServiceImpl implements CollectService {

	@Resource
	private CollectDao collectDao;

	@Override
	public boolean insertCollect(Collect collect) {
		// TODO Auto-generated method stub
		if(!com.kiki.comcom.utils.StringUtil.isHttpUrl(collect.getUrl())) {
			throw new CMSException("地址不合法");
		}
		return collectDao.insertCollect(collect)>0;
	}

	@Override
	public PageInfo<Collect> queryCollects(Collect collect, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Collect> list = collectDao.queryCollects(collect);
		return new PageInfo<Collect>(list);
	}

	@Override
	public int queryCount(String text, Integer userId) {
		// TODO Auto-generated method stub
		return collectDao.queryCount(text, userId);
	}

	@Override
	public int delCollect(Collect collect) {
		// TODO Auto-generated method stub
		return collectDao.delCollect(collect);
	}



}
