package com.briup.web.servlet;

import com.briup.bean.ShopCar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author horry
 * @Description TODO
 * @date 2024/1/3-14:13
 */
@WebServlet("/removeShopCar")
public class RemoveShopCarServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取需要移除的书籍 id
		String bookId = req.getParameter("bookId");

		// 在购物车中进行移除即可
		ShopCar shopCar = (ShopCar) req.getSession().getAttribute("shopCar");
		shopCar.removeOrderLine(Integer.parseInt(bookId));

		// 移除完之后 进行页面跳转 跳回 到 购物车页面
		req.getRequestDispatcher("shopCart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
