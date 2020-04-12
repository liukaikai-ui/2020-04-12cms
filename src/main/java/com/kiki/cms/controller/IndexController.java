package com.kiki.cms.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.chart.TickRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kiki.cms.domain.Article;
import com.kiki.cms.domain.Category;
import com.kiki.cms.domain.Channel;
import com.kiki.cms.domain.Collect;
import com.kiki.cms.domain.Comment;
import com.kiki.cms.domain.FriendlyLink;
import com.kiki.cms.domain.Slide;
import com.kiki.cms.domain.User;
import com.kiki.cms.service.ArticleService;
import com.kiki.cms.service.ChannelService;
import com.kiki.cms.service.CollectService;
import com.kiki.cms.service.CommentService;
import com.kiki.cms.service.FriendlyLinkService;
import com.kiki.cms.service.SlideService;
import com.kiki.comcom.utils.DateUtil;

import sun.awt.RepaintArea;

@Controller
public class IndexController {
	
	@Resource
	private ChannelService channelService;
	@Resource
	private ArticleService articleService;
	@Resource
	private SlideService slideService;
	@Resource
	private CollectService collectService;
	@Resource
	private CommentService commentService;
	@Resource
	private FriendlyLinkService friendlyLinkService;
	@RequestMapping(value = {"","/","index"})
	public String index(Model model,Article article,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "5")Integer pageSize) {
		//只查询审核通过的文章
		article.setStatus(1);
		//只查询未删除的
		article.setDeleted(0);
		
		model.addAttribute("article", article);
		
		Thread t1;
		Thread t2;
		Thread t3;
		Thread t4;
		Thread t5;
		t1= new Thread(new Runnable() {
			
			@Override
			public void run() {
				//查询所有的栏目
				List<Channel> channels = channelService.queryChannel();
				model.addAttribute("channels", channels);
			}
		});
		
		t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				//如果栏目不为空 则查询栏目下的分类
				if(null!=article.getChannelId()) {
					//根据栏目id  查询分类
					List<Category> categories = channelService.queryCategoryByChannelId(article.getChannelId());
					model.addAttribute("categories", categories);
					//查询该栏目下的所有文章或者分类下的文章
					PageInfo<Article> info = articleService.queryArticles(article, pageNum, pageSize);
					model.addAttribute("info", info);
				}

			}
		});
		t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//如果栏目为空  则查询广告
				if(null==article.getChannelId()) {
					List<Slide> slides = slideService.querySlide();
					model.addAttribute("slides", slides);
					Article hotArticle = new Article();
					hotArticle.setStatus(1);
					hotArticle.setHot(1);
					hotArticle.setDeleted(0);
					PageInfo<Article> info = articleService.queryArticles(hotArticle, pageNum, pageSize);
					model.addAttribute("info", info);
				}
				
			}
		});
		t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//右侧边栏显示最新的文章
				Article lastArticle1 = new Article();
				lastArticle1.setStatus(1);
				lastArticle1.setDeleted(0);
				PageInfo<Article> lastArticle = articleService.queryArticles(lastArticle1, 1, 5);
				model.addAttribute("lastArticle", lastArticle);
				
				//24小时热文
				Article article2 = new Article();
				article2.setStatus(1);//热文是审核过的
				article2.setHot(1);//热文是热点文章
				article2.setDeleted(0);
				article2.setCreated(DateUtil.getDateByBefore());
				PageInfo<Article> rwArticle = articleService.queryArticles(article2, 1, 4);
				model.addAttribute("rwArticle", rwArticle);
				
			}
		});
		t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//显示友情链接
				List<FriendlyLink> list = friendlyLinkService.queryFriendlyLinks();
				model.addAttribute("flist", list);
				
			}
		});
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		//先让子线程执行完 再返回页面展示
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index/index";
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	    * @Title: detail
	    * @Description: 文章详情
	    * @param @param id
	    * @param @param model
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("detail")
	public String detail(Integer id,Model model,HttpSession session,
			@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "3")Integer pageSize,HttpServletResponse response) throws UnsupportedEncodingException {
		Article article = articleService.queryArticle(id);
		model.addAttribute("article", article);
		
		int i = 0;
		//查询改文章是否被收藏过
		User user = (User) session.getAttribute("user");
		if(null!=user) {
			//如果已经登陆
			i = collectService.queryCount(article.getTitle(), user.getId());
		}
		//是否收藏过
		model.addAttribute("flag", i);
		
		//查询文章的评论
		PageInfo<Comment> info = commentService.queryComments(id, pageNum, pageSize);
		model.addAttribute("info", info);
		
		//查询该文章的评论排行榜
		PageInfo<Comment> querycomCommentOrderByCommentNum = commentService.querycomCommentOrderByCommentNum(id, pageNum, pageSize);
		model.addAttribute("rank", querycomCommentOrderByCommentNum);
		
		/*
		 * if(null!=id) { Cookie cookie = new Cookie("a",
		 * URLEncoder.encode(id.toString(), "utf-8")); cookie.setMaxAge(60);
		 * cookie.setPath("/"); response.addCookie(cookie); }
		 */
		return "index/article";
	}
	

	/**
	 * 
	    * @Title: collect
	    * @Description: 收藏
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@RequestMapping("collect")
	@ResponseBody
	public boolean collect(Collect collect,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null !=user) {//如果已经登陆 则执行收藏
			collect.setUserId(user.getId());
			collect.setCreated(new Date());
			return collectService.insertCollect(collect);
		}
		//没有登陆不执行收藏
		return false;
	}
	
	/**
	 * 
	    * @Title: delCollect
	    * @Description: 取消收藏
	    * @param @param collect
	    * @param @param session
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@RequestMapping("delCollect")
	@ResponseBody
	public boolean delCollect(Collect collect,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null !=user) {//如果已经登陆 则可以取消收藏
			collect.setUserId(user.getId());
			return collectService.delCollect(collect)>0;
		}
		//没有登陆不执行收藏
		return false;
	}
	/**
	 * 
	    * @Title: comment
	    * @Description: 添加评论
	    * @param @param comment
	    * @param @param session
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@RequestMapping("addComment")
	@ResponseBody
	public boolean comment(Comment comment,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null !=user) {//如果已经登陆 则执行评论
			comment.setUserId(user.getId());
			comment.setCreated(new Date());
			return commentService.insertComment(comment)>0;
		}
		//没有登陆不执行评论
		return false;
	}
	
	
	
}
