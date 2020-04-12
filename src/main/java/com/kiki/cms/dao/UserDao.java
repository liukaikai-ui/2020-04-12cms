package com.kiki.cms.dao;
/**
 * 
    * @ClassName: UserDao
    * @Description: 用户信息
    * @author kiki
    * @date 2020年4月7日
    *
 */

import java.util.List;

import com.kiki.cms.domain.User;

public interface UserDao {
	/**
	 * 
	    * @Title: queryUser
	    * @Description: 查询所有的用户
	    * @param @param user
	    * @param @return    参数
	    * @return List<UserDao>    返回类型
	    * @throws
	 */
	List<User> queryUser(User user);
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
	int insertUser(User user);
	
	/**
	 * 
	    * @Title: selectUser
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param name
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	User selectUser(String name);
}
