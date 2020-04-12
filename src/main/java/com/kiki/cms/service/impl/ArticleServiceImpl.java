package com.kiki.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kiki.cms.dao.ArticleDao;
import com.kiki.cms.domain.Article;
import com.kiki.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao dao;
	
	@Override
	public int insertArticle(Article article) {
		// TODO Auto-generated method stub
		return dao.insertArticle(article);
	}

	@Override
	public PageInfo<Article> queryArticles(Article article,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> queryArticle = dao.queryArticles(article);
		PageInfo<Article> info = new PageInfo<Article>(queryArticle);
		return info;
	}

	@Override
	public Article queryArticle(Integer id) {
		System.err.println(dao.updArticleHit(id));
		return dao.queryArticle(id);
	}

	@Override
	public int upd(Article article) {
		// TODO Auto-generated method stub
		return dao.upd(article);
	}

	@Override
	public int updArticleHit(Integer id) {
		// TODO Auto-generated method stub
		return dao.updArticleHit(id);
	}

}
