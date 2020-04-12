package com.kiki.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kiki.cms.domain.Category;
import com.kiki.cms.domain.Channel;
import com.kiki.cms.service.ChannelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
    * @ClassName: ChannelController
    * @Description: 栏目及分类
    * @author kiki
    * @date 2020年4月2日
    *
 */
@Controller
@RequestMapping("channel")
public class ChannelController {

	@Autowired
	private ChannelService service;
	
	/**
	 * 
	    * @Title: queryChannels
	    * @Description: 所有的栏目
	    * @param @return    参数
	    * @return List<Channel>    返回类型
	    * @throws
	 */
	@RequestMapping("channels")
	@ResponseBody
	public List<Channel> queryChannels(){
		List<Channel> list = service.queryChannel();
		return list;
	}
	
	@RequestMapping("categories")
	@ResponseBody
	public List<Category> queryCategoryById(Integer channelId){
		List<Category> list = service.queryCategoryByChannelId(channelId);
		return list;
	}
}
