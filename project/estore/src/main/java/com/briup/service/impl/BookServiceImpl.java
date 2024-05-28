package com.briup.service.impl;

import com.briup.bean.Book;
import com.briup.dao.BookMapper;
import com.briup.service.IBookService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookServiceImpl implements IBookService {

	// 定义mapper对象
	private final static BookMapper bookMapper;

	static {
		// 获取sqlSession
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
		// 通过sqlSession获取BookMapper对象
		bookMapper = sqlSession.getMapper(BookMapper.class);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookMapper.findAllBooks();
	}

	@Override
	public Book findBookById(Integer id) {
		return bookMapper.findBookById(id);
	}
}