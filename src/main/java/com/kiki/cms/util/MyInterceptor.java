package com.kiki.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
    * @ClassName: MyInterceptor
    * @Description: 个人中心拦截器
    * @author kiki
    * @date 2020年4月10日
    *
 */
public class MyInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//不拦截规则  如果用户已经登陆 则不拦截
		HttpSession session = request.getSession(false);
		if(session!=null) {
			Object user = session.getAttribute("user");
			if(null!=user) {
				return true;
			}
		}
		request.setAttribute("msg", "请登录后再试");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);;
		return false;
	}
}
