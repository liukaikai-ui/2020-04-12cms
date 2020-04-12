package com.kiki.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.kiki.cms.domain.Article;
import com.kiki.cms.domain.Collect;
import com.kiki.cms.domain.User;
import com.kiki.cms.service.ArticleService;
import com.kiki.cms.service.CollectService;

@Controller
@RequestMapping("my")
public class MyController {

	
	@Autowired
	private ArticleService service;
	@Autowired
	private CollectService collectService;
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		return "my/index";
	}
	
	
	
	//显示我的文章
	@GetMapping("publish")
	public String publish() {
		
		return "my/publish";
		
	}
	
	/**
	 * 
	    * @Title: publishs
	    * @Description: TODO执行发布
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	
	@Value(value = "${filePath}")
	private String filePath;
	@PostMapping("publish")
	@ResponseBody
	public boolean publishs(Article article,MultipartFile file,HttpSession session) {
		//判断是否上传了文件
		if(null!=file && !file.isEmpty()) {
			//文件上传
			String path = filePath;
			//为了防止文件重名 使用UUID
			//获取原始名称
			String oldName = file.getOriginalFilename();
			String fileName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
			File f = new File(path, fileName);
			try {
				file.transferTo(f);
				article.setPicture(fileName);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		User user = (User) session.getAttribute("user");
		//文章作者
		article.setUserId(user.getId());
		article.setStatus(0);//文章状态写死
		article.setCreated(new Date());
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);
		article.setContentType("0");
		int insertArticle = service.insertArticle(article);
		if(insertArticle>0) {
			return true;
		}else {
			return false;
		}
		
		
	}
	@RequestMapping("articles")
	public String articles(Article article,@RequestParam(defaultValue = "1") Integer pageNum
			,@RequestParam(defaultValue = "3") Integer pageSize,Model model,HttpSession session) {
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		PageInfo<Article> info = service.queryArticles(article, pageNum, pageSize);
		model.addAttribute("info", info);
		return "my/articles";
	}
	
	@GetMapping("article")
	@ResponseBody
	public Article queryArticle(Integer id) {
		Article article = service.queryArticle(id);
		return article;
	}
	
	/**
	 * 
	    * @Title: collects
	    * @Description: 去收藏
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("collects")
	public String  collects(Collect collect,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "3") Integer pageSize,Model model,HttpSession session) {
		User user = (User) session.getAttribute("user");
		collect.setUserId(user.getId());
		PageInfo<Collect> info = collectService.queryCollects(collect, pageNum, pageSize);
		
		model.addAttribute("info", info);
		return "my/collects";
	}
	
	@RequestMapping("delCollect")
	@ResponseBody
	public boolean delCollect(Collect collect) {
		int i = collectService.delCollect(collect);
		return i>0;
	}

}
