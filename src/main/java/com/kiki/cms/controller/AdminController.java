package com.kiki.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kiki.cms.domain.Article;
import com.kiki.cms.domain.User;
import com.kiki.cms.service.ArticleService;
import com.kiki.cms.service.UserService;

/**
 * 
    * @ClassName: AdminController
    * @Description: 管理员中心
    * @author kiki
    * @date 2020年4月3日
    *
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ArticleService service;
	@Autowired
	private UserService userService;
	/**
	 * 
	    * @Title: index
	    * @Description: 管理员首页
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		return "admin/index";
	}
	
	/**
	 * 
	    * @Title: articles
	    * @Description: 文章列表
	    * @param @param article
	    * @param @param pageNum
	    * @param @param model
	    * @param @param pageSize
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("articles")
	public String articles(Article article,@RequestParam(defaultValue = "1") Integer pageNum,Model model,
			@RequestParam(defaultValue = "8")Integer pageSize) {
		//第一次查询文章的状态默认为待审
		if(null == article.getStatus()) {
			article.setStatus(0);//默认待审
		}
		
		PageInfo<Article> info = service.queryArticles(article, pageNum, pageSize);
		model.addAttribute("info", info);
		model.addAttribute("article", article);
		return "admin/articles";
	}
	
	@ResponseBody
	@RequestMapping("article")
	public Object article(Integer id) {
		Article article = service.queryArticle(id);
		return article;
	}
	
	@ResponseBody
	@RequestMapping("upd")
	public Object upd(Article article) {
		return service.upd(article);
	}
	
	@ResponseBody
	@RequestMapping("lupd")
	public Object lupd(User user) {
		return userService.updUser(user);
	}
	
	@RequestMapping("users")
	public String users(User user,@RequestParam(defaultValue = "1") Integer pageNum,Model model,
			@RequestParam(defaultValue = "4")Integer pageSize) {
		System.err.println("maxinru ");
		if(user.getLocked()==null) {
			user.setLocked(0);
		}
		PageInfo<User> info = userService.queryUser(user, pageNum, pageSize);
		model.addAttribute("info", info);
		model.addAttribute("user", user);
		return "admin/users";
	}
	
}
