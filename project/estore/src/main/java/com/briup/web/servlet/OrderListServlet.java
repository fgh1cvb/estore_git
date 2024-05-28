package com.briup.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author horry
 * @Description TODO
 * @date 2024/1/4-9:44
 */
@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取订单的地址  1、选择已有地址 2、选择新增地址
		// 根据订单数据 生成订单
		// 跳转到订单列表页面
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
