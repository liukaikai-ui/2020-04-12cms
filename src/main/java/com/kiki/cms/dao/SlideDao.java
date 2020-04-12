package com.kiki.cms.dao;

import java.util.List;

import com.kiki.cms.domain.Slide;

public interface SlideDao {

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
