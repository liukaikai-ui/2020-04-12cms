package com.kiki.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.kiki.cms.domain.Collect;

public interface CollectService {
	/**
	 * 
	    * @Title: insertCollect
	    * @Description: 增加
	    * @param @param collect
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	boolean insertCollect(Collect collect);
	
	/**
	 * 
	    * @Title: queryCollects
	    * @Description: 查询
	    * @param @param collect
	    * @param @return    参数
	    * @return List<Collect>    返回类型
	    * @throws
	 */
	PageInfo<Collect> queryCollects(Collect collect,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	    * @Title: queryCount
	    * @Description: 查询注册用户是否收藏过
	    * @param @param text
	    * @param @param userId
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int queryCount(@Param("text")String text,@Param("userId")Integer userId);
	
	/**
	 * 
	    * @Title: delCollect
	    * @Description: 删除收藏
	    * @param @param id
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int delCollect(Collect collect);
}
