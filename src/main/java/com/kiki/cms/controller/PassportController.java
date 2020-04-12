package com.kiki.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kiki.cms.util.CMSResult;
import com.kiki.cms.domain.User;
import com.kiki.cms.service.UserService;
import com.kiki.cms.util.CMSException;

@Controller
@RequestMapping("passport")
public class PassportController {

	@Resource
	private UserService userService;
	
	/**
	 * 
	    * @Title: reg
	    * @Description: 去注册
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("reg")
	private String reg() {
		
		return "passport/reg";
	}
	
	
	/**
	 * 
	    * @Title: reg
	    * @Description: 执行注册
	    * @param @param user
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@ResponseBody
	@PostMapping("reg1")
	public CMSResult<User> reg1(User user,Model model) {
		CMSResult<User> result = new CMSResult<User>();
		try {
			userService.insertUser(user);
			result.setCode(200);//状态码
			result.setMsg("恭喜注册成功,请登录");
		}catch (CMSException e) {//捕获自定义异常
			e.printStackTrace();
			result.setCode(300);//状态码
			result.setMsg(e.message);
		}catch (Exception e) {
			e.printStackTrace();
			result.setCode(400);//状态码
			result.setMsg("未知错误 请联系管理员");
		}
		return result;
		
	}
	
	/**
	 * 
	    * @Title: login
	    * @Description: 去登陆
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@GetMapping("login")
	private String login() {
		
		return "passport/login";
	}
	
	/**
	 * 
	    * @Title: login
	    * @Description: 执行登陆
	    * @param @param user
	    * @param @param model
	    * @param @return    参数
	    * @return CMSResult<User>    返回类型
	    * @throws
	 */
	@ResponseBody
	@PostMapping("login1")
	public CMSResult<User> login(User user,Model model,HttpSession session) {
		CMSResult<User> result = new CMSResult<User>();
		try {
			User u = userService.login(user);
			result.setCode(200);
			result.setMsg("登陆成功");
			//result.setData(u);
			//登陆成功 存入session中
			session.setAttribute("user", u);
		} catch (CMSException e) {//捕获自定义异常
			e.printStackTrace();
			result.setCode(300);//状态码
			result.setMsg(e.message);
		}catch (Exception e) {
			e.printStackTrace();
			result.setCode(400);//状态码
			result.setMsg("未知错误 请联系管理员");
		}
		System.err.println(result);
		return result;
		
	}
	
	/**
	 * 
	    * @Title: logout
	    * @Description: 注销
	    * @param @param session
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	/**
	 * 
	    * @Title: checkedUsername
	    * @Description: 注册时检查用户是否被注册
	    * @param @param username
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@RequestMapping("checkedUsername")
	@ResponseBody
	public boolean checkedUsername(String username) {
		User user = userService.selectUser(username);
		return user==null;
	}
	
	
	/**
	 * 
	    * @Title: loginAdmin
	    * @Description: 管理员登陆
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping(value = {"admin/login","admin/","admin"})
	public String loginAdmin() {
		
		return "passport/admin_login";
	}
	
	@RequestMapping("login_admin")
	@ResponseBody
	public CMSResult<User> loginAdmin(User user,Model model,HttpSession session) {
		CMSResult<User> result = new CMSResult<User>();
		try {
			User u = userService.login(user);
			result.setCode(200);
			result.setMsg("登陆成功");
			//普通用户就不能登陆
			if(u.getRole().equals("0")) {//1代表管理员 0是普通用户
				throw new CMSException("该用户没有登陆权限");
			}
			//result.setData(u);
			//登陆成功 存入session中
			session.setAttribute("admin", u);
		} catch (CMSException e) {//捕获自定义异常
			e.printStackTrace();
			result.setCode(300);//状态码
			result.setMsg(e.message);
		}catch (Exception e) {
			e.printStackTrace();
			result.setCode(400);//状态码
			result.setMsg("未知错误 请联系管理员");
		}
		return result;
		
	}
	
}
