package com.briup.web.filter;

import com.briup.bean.Customer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author horry
 * @Description 登录过滤器，当用户为登录时，直接让用户跳转到登录界面
 * @date 2022/10/7-16:24
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//没有登录让其进入到登录页面
		//放开注册界面让其进行访问
		//放开首页让其进行浏览
		//放开图片、css、js等静态资源
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		//判断用户访问的是否是登录界面，若是则直接放行
		if (uri.contains("login") || uri.contains("register") || uri.contains("index") || uri.endsWith("/")) {
			chain.doFilter(req, resp);
		} else if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {
			chain.doFilter(req, resp);
		} else {
			//判断用户是否登录过，若登录过进行放行，没有登录则跳转到登录界面
			Customer user = (Customer) req.getSession().getAttribute("user");
			if (user == null) {
				req.setAttribute("msg", "更多详细信息,请登录后查看!");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {
				chain.doFilter(req, resp);
			}
		}
	}

	@Override
	public void destroy() {
	}
}
