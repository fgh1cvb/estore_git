package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.service.ICustomerService;
import com.briup.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final ICustomerService customerService;

	static {
		customerService = new CustomerServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收请求资源
		String name = req.getParameter("name");
		String password = req.getParameter("password");


		// 进行登录校验
		String path = "index.jsp";
		String msg = "登录成功";
		try {
			Customer customer = customerService.login(name, password);
			// 将登录好的用户数据存储起来
			// 获取session对象
			HttpSession session = req.getSession();
			session.setAttribute("user", customer);

		} catch (Exception e) {
			// 遇到异常 说明登录失败,需要跳转回登录界面
			path = "login.jsp";
			// 将异常信息 转为 注册失败的原因给用户查看
			msg = e.getMessage();
		}

		// 数据处理完后,还需要进行页面跳转
		req.setAttribute("msg", msg);
		req.getRequestDispatcher(path).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
