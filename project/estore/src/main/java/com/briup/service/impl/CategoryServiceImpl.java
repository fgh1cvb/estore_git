package com.briup.service.impl;

import com.briup.bean.Category;
import com.briup.dao.CategoryMapper;
import com.briup.service.ICategoryService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {

	// 定义dao层对象 将该属性定义为静态变量 保证整个类的所有对象都使用同一个mapper对象
	private final static CategoryMapper categoryMapper;

	// 静态代码块 随着类的加载而加载的 并且只会执行一次
	static {
		// 获取sqlSession
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
		// 通过sqlSession获取categoryMapper对象
		categoryMapper = sqlSession.getMapper(CategoryMapper.class);
	}

	@Override
	public List<Category> findAllCategorys() {
		return categoryMapper.findAllCategorys();
	}
}
