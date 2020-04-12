package com.kiki.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kiki.cms.dao.UserDao;
import com.kiki.cms.domain.User;

public interface UserService {

	/**
	 * 
	    * @Title: queryUser
	    * @Description: 查询所有的用户
	    * @param @param user
	    * @param @return    参数
	    * @return List<UserDao>    返回类型
	    * @throws
	 */
	PageInfo<User> queryUser(User user,Integer pageNum,Integer pageSize);
	/**
	 * 
	    * @Title: updUser
	    * @Description: 修改用户
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int updUser(User user);
	
	/**
	 * 
	    * @Title: insertUser
	    * @Description: 增加用户
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	boolean insertUser(User user);
	
	/**
	 * 
	    * @Title: selectUser
	    * @Description: 登陆
	    * @param @param name
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	User selectUser(String name);
	
	/**
	 * 
	    * @Title: login
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param user
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	User login(User user);
}
