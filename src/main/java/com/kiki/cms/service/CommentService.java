package com.kiki.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kiki.cms.domain.Comment;

public interface CommentService {

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
	PageInfo<Comment> queryComments(Integer articleId,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	    * @Title: querycomCommentOrderByCommentNum
	    * @Description: 对该文章评论数做一个排行榜
	    * @param @param id
	    * @param @return    参数
	    * @return List<Comment>    返回类型
	    * @throws
	 */
	PageInfo<Comment> querycomCommentOrderByCommentNum(Integer id,Integer pageNum,Integer pageSize);
}
