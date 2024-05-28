package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.service.ICustomerService;
import com.briup.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author horry
 * @Description TODO
 * @date 2024/1/2-9:08
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final ICustomerService customerService;

	static {
		customerService = new CustomerServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收请求资源
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String zipCode = req.getParameter("zipCode");
		String address = req.getParameter("address");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		// 将参数封装成对象
		Customer customer = new Customer();
		customer.setName(name);
		customer.setPassword(password);
		customer.setAddress(address);
		customer.setZipCode(zipCode);
		customer.setTelephone(telephone);
		customer.setEmail(email);

		// 将数据存入到数据库中
		String path = "login.jsp";
		String msg = "注册成功";
		try {
			customerService.register(customer);
		} catch (Exception e) {
			// 遇到异常 说明注册失败,需要跳转到注册界面
			path = "register.jsp";
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