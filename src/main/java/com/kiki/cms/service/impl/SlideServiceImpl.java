package com.kiki.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kiki.cms.dao.SlideDao;
import com.kiki.cms.domain.Slide;
import com.kiki.cms.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {

	@Resource
	private SlideDao slideDao;
	@Override
	public List<Slide> querySlide() {
		// TODO Auto-generated method stub
		return slideDao.querySlide();
	}

}
