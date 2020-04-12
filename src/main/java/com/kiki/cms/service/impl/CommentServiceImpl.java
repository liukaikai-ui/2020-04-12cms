package com.kiki.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kiki.cms.dao.CommentDao;
import com.kiki.cms.domain.Comment;
import com.kiki.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentDao commentDao;
	@Override
	public int insertComment(Comment comment) {
		try {
			commentDao.insertComment(comment);//增加评论
			commentDao.updArticle(comment.getArticleId());
			return 1;
		} catch (Exception e) {
			throw new RuntimeException("评论失败");
		}
	}

	@Override
	public PageInfo<Comment> queryComments(Integer articleId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Comment> list = commentDao.queryComments(articleId);
		return new PageInfo<Comment>(list);
	}

	@Override
	public PageInfo<Comment> querycomCommentOrderByCommentNum(Integer id, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Comment> list = commentDao.querycomCommentOrderByCommentNum(id);
		return new PageInfo<Comment>(list);
	}

}
