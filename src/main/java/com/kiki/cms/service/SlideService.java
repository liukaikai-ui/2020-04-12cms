package com.kiki.cms.service;

import java.util.List;

import com.kiki.cms.domain.Slide;

public interface SlideService {

	/**
	 * 
	    * @Title: querySlide
	    * @Description: 查询所有的广告
	    * @param @return    参数
	    * @return List<Slide>    返回类型
	    * @throws
	 */
	List<Slide> querySlide();
}
