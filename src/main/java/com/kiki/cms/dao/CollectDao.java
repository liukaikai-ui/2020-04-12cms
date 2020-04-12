package com.kiki.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kiki.cms.domain.Collect;

/**
 * 
    * @ClassName: CollectDao
    * @Description: 文章收藏
    * @author kiki
    * @date 2020年4月11日
    *
 */
public interface CollectDao {

	/**
	 * 
	    * @Title: insertCollect
	    * @Description: 增加
	    * @param @param collect
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insertCollect(Collect collect);
	
	/**
	 * 
	    * @Title: queryCollects
	    * @Description: 查询
	    * @param @param collect
	    * @param @return    参数
	    * @return List<Collect>    返回类型
	    * @throws
	 */
	List<Collect> queryCollects(Collect collect);
	
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
