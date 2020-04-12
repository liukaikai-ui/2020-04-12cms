package com.kiki.cms.service.impl;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.kiki.cms.domain.Article;
import com.kiki.cms.service.ArticleService;

public class ArticleServiceImplTest extends TestUtil{

	@Autowired
	private ArticleService service;
	@Test
	public void testInsertArticle() {
		Article article = new Article();
		article.setTitle("NBA");
		
		int i = service.insertArticle(article);
	}

	@Test
	public void testQueryArticle() {
		PageInfo<Article> info = service.queryArticles(new Article(), 1, 10);
		System.out.println(info);
	}

}
