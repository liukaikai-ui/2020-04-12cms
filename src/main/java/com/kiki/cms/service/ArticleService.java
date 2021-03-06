package com.kiki.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kiki.cms.domain.Article;

public interface ArticleService {

	/**
	 * 
	    * @Title: insertArticle
	    * @Description: 对文章就行添加
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insertArticle(Article article);
	
	/**
	 * 
	    * @Title: queryArticle
	    * @Description: 查询所有的文章
	    * @param @param article
	    * @param @return    参数
	    * @return List<Article>    返回类型
	    * @throws
	 */
	PageInfo<Article> queryArticles(Article article,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	    * @Title: queryArticle
	    * @Description: 单个文章查询
	    * @param @param id
	    * @param @return    参数
	    * @return Article    返回类型
	    * @throws
	 */
	Article queryArticle(Integer id);
	
	/**
	 * 
	    * @Title: upd
	    * @Description: 修改
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int upd(Article article);
	
	/**
	 * 
	    * @Title: updArticleHit
	    * @Description: 文章的点击量 
	    * @param @param id
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int updArticleHit(Integer id);
}
