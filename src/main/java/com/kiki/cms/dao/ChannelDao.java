package com.kiki.cms.dao;

import java.util.List;

import com.kiki.cms.domain.Category;
import com.kiki.cms.domain.Channel;

/**
 * 
    * @ClassName: ChannelDao
    * @Description: 所有的栏目
    * @author kiki
    * @date 2020年4月2日
    *
 */
public interface ChannelDao {
	/**
	 * 
	    * @Title: queryChannel
	    * @Description: 查询所有的栏目
	    * @param @return    参数
	    * @return List<Channel>    返回类型
	    * @throws
	 */
	List<Channel> queryChannel();
	/**
	 * 
	    * @Title: queryCategoryByChannelId
	    * @Description: 根据栏目的id查询所属的分类
	    * @param @param channelId
	    * @param @return    参数
	    * @return List<Category>    返回类型
	    * @throws
	 */
	List<Category> queryCategoryByChannelId(Integer channelId);
}
