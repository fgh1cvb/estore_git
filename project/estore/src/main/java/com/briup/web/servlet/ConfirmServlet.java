package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.ShopAddress;
import com.briup.service.IShopAddressService;
import com.briup.service.impl.ShopAddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author horry
 * @Description TODO
 * @date 2024/1/4-9:23
 */
@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {

	private final IShopAddressService shopAddressService = new ShopAddressServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取当前登录用户的地址列表
		Customer user = (Customer) req.getSession().getAttribute("user");
		List<ShopAddress> addressList = shopAddressService.findAddressByCustomerId(user.getId());

		// 将地址列表放到请求中
		req.setAttribute("addressList", addressList);

		// 进行页面跳转
		req.getRequestDispatcher("confirm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
