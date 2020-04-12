package com.kiki.cms.dao;

import java.util.List;

import com.kiki.cms.domain.Comment;

/**
 * 
    * @ClassName: CommentDao
    * @Description: 评论
    * @author kiki
    * @date 2020年4月11日
    *
 */
public interface CommentDao {

	/**
	 * 
	    * @Title: insertComment
	    * @Description: 添加
	    * @param @param comment
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insertComment(Comment comment);
	
	/**
	 * 
	    * @Title: queryComments
	    * @Description: 根据id查找评论的
	    * @param @param articleId
	    * @param @return    参数
	    * @return List<Comment>    返回类型
	    * @throws
	 */
	List<Comment> queryComments(Integer articleId);
	
	/**
	 * 
	    * @Title: updArticle
	    * @Description: 增加文章的文章数量
	    * @param @param id
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int updArticle(Integer id);
	
	/**
	 * 
	    * @Title: querycomCommentOrderByCommentNum
	    * @Description: 对该文章评论数做一个排行榜
	    * @param @param id
	    * @param @return    参数
	    * @return List<Comment>    返回类型
	    * @throws
	 */
	List<Comment> querycomCommentOrderByCommentNum(Integer id);
}
