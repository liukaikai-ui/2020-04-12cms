package com.kiki.cms.dao;

import java.util.List;

import com.kiki.cms.domain.FriendlyLink;

public interface FriendlyLinkDao {

	/**
	 * 
	    * @Title: queryFriendlyLinks
	    * @Description: 查询所有的友情链接
	    * @param @return    参数
	    * @return List<FriendlyLink>    返回类型
	    * @throws
	 */
	List<FriendlyLink> queryFriendlyLinks();
}
