package com.briup.service.impl;

import com.briup.bean.Customer;
import com.briup.dao.CustomerMapper;
import com.briup.service.ICustomerService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

public class CustomerServiceImpl implements ICustomerService {

	private static final CustomerMapper customerMapper;

	static {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
		customerMapper = sqlSession.getMapper(CustomerMapper.class);
	}

	@Override
	public void register(Customer customer) {
		// 1、校验用户名和密码是否可用
		String name = customer.getName();
		if (checkStr(name) || checkStr(customer.getPassword())) {
			throw new RuntimeException("用户名及密码不能为空");
		}

		// 2、校验用户名是否以及存在
		Customer customer2 = findCustomerByName(name);
		if (customer2 != null) {
			throw new RuntimeException("当前用户名已经存在:" + name);
		}

		// 3、用户名不存在,直接将数据写入到数据库中
		customerMapper.saveCustomer(customer);
	}

	@Override
	public Customer findCustomerByName(String name) {
		return customerMapper.findCustomerByName(name);
	}

	@Override
	public Customer login(String name, String password) {
		// 1、校验用户名和密码是否可用
		if (checkStr(name) || checkStr(password)) {
			throw new RuntimeException("用户名及密码不能为空!");
		}
		// 2、校验用户名是否存在
		Customer customer = findCustomerByName(name);
		if (customer == null) {
			throw new RuntimeException("该用户不存在:" + name);
		}

		// 3、校验用户输入的密码和数据库中存储的密码 是否一致
		if (!password.equals(customer.getPassword())) {
			throw new RuntimeException("密码有误,请重新输入!");
		}

		// 将密码置空后 返回
		customer.setPassword(null);
		return customer;
	}

	private boolean checkStr(String str) {
		return str == null || str.trim().isEmpty();
	}
}
