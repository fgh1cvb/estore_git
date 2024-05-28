package com.briup.web.servlet;

import com.briup.bean.Book;
import com.briup.bean.ShopCar;
import com.briup.service.IBookService;
import com.briup.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addShopCart")
public class AddShopCartServlet extends HttpServlet {

	private final IBookService bookService = new BookServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取参数 如 书籍的id 还有书籍的数量
		String bookId = req.getParameter("bookId");
		String num = req.getParameter("num");

		// 根据书籍id查询书籍信息
		Book book = bookService.findBookById(Integer.valueOf(bookId));

		// 将书籍放入到购物车中
		ShopCar shopCar = (ShopCar) req.getSession().getAttribute("shopCar");
		shopCar.add(book, Integer.valueOf(num));

		// 添加完之后 页面跳转到购物车页面
		req.getRequestDispatcher("shopCart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
