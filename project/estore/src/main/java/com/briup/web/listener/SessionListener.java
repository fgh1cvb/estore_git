package com.briup.web.listener;


import com.briup.bean.ShopCar;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 当session被创建时 即用户登录时, session被创建
		HttpSession session = se.getSession();
		// 创建购物车 将 购物车放入到 session中
		ShopCar shopCar = new ShopCar();
		session.setAttribute("shopCar", shopCar);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

	}
}
