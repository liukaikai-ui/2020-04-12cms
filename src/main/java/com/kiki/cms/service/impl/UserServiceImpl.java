package com.kiki.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.kiki.cms.dao.UserDao;
import com.kiki.cms.domain.User;
import com.kiki.cms.service.UserService;
import com.kiki.cms.util.CMSException;
import com.kiki.cms.util.Md5Util;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Override
	public PageInfo<User> queryUser(User user,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userDao.queryUser(user);
		
		return new PageInfo<User>(list);
	}

	@Override
	public int updUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updUser(user);
	}

	@Override
	public boolean insertUser(User user) {
		//使用工具类处理注册的业务逻辑
		//用户名不能为空
		if(!com.kiki.comcom.utils.StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		if(!(user.getUsername().length()>=2 && user.getUsername().length()<=6)) {
			throw new CMSException("用户名长度在2-6之间");
		}
		if(selectUser(user.getUsername())!=null) {
			throw new CMSException("该用户已被注册");
		}
		
		if(!com.kiki.comcom.utils.StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		if(!(user.getPassword().length()>=6 && user.getPassword().length()<=12)) {
			throw new CMSException("密码长度在6-12之间");
		}
		if(!com.kiki.comcom.utils.StringUtil.hasText(user.getRepassword())) {
			throw new CMSException("确认密码不能为空");
		}
		if(!user.getRepassword().equals(user.getPassword())) {
			throw new CMSException("两次密码不一致");
		}
		//对注册用户的密码进行加密 
		user.setPassword(Md5Util.encode(user.getPassword()));
		
		return userDao.insertUser(user)>0;
	}

	@Override
	public User selectUser(String name) {
		// TODO Auto-generated method stub
		return userDao.selectUser(name);
	}

	@Override
	public User login(User user) {
		if(!com.kiki.comcom.utils.StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		if(!com.kiki.comcom.utils.StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		//比较密码
		User u = this.selectUser(user.getUsername());
		if(null==u) {
			throw new CMSException("用户名不正确");
		}
		if(!u.getPassword().equals(Md5Util.encode(user.getPassword()))) {
			throw new CMSException("密码不正确");
		}
		if(u.getLocked()==1) {
			throw new CMSException("账户被禁用");
		}
		return u;
	}

}
