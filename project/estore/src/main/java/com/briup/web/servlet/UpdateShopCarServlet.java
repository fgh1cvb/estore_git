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

/**
 * @author horry
 * @Description TODO
 * @date 2024/1/3-14:13
 */
@WebServlet("/updateShopCar")
public class UpdateShopCarServlet extends HttpServlet {

	private final IBookService bookService = new BookServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取书籍id和需要修改的数量
		String bookId = req.getParameter("bookId");
		String num = req.getParameter("num");

		//去购物车中修改对应的书籍数量即可
		// 根据书籍id查询书籍信息
		int id = Integer.parseInt(bookId);
		Book book = bookService.findBookById(id);

		ShopCar shopCar = (ShopCar) req.getSession().getAttribute("shopCar");
		// 修改购物车的书籍数量 应该是 覆盖数量 而不是追加
		shopCar.removeOrderLine(id);
		// 移除掉原有数据后重新添加
		shopCar.add(book, Integer.valueOf(num));

		// 修改完毕进行页面跳转
		req.getRequestDispatcher("shopCart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
